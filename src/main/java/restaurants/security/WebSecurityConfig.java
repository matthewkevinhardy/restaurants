package restaurants.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/restaurant/save").hasRole("SYS_ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/v1/restaurant/*/update").hasRole("SYS_ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/v1/restaurant/*/delete").hasRole("SYS_ADMIN").anyRequest()
				.permitAll().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().sameOrigin();

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> jdbcAuthentication = 
				auth.jdbcAuthentication().dataSource(dataSource);

		if (!dataSource.getConnection().getMetaData().getTables(null, "", "USERS", null).first()) {
			jdbcAuthentication.withDefaultSchema().withUser(User.withUsername("sa").password(passwordEncoder().encode("password")).roles("SYS_ADMIN"))
			.withUser(
					User.withUsername("employee").password(passwordEncoder().encode("password")).roles("EMPLOYEE"));
		}
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
