package restaurants.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RootController {
	@RequestMapping(value = "/restaurants/")
	public void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("/restaurants/swagger-ui/index.html");
	}
}
