package restaurants.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateConstraint {
    String message() default "Invalid date.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String value();
}
