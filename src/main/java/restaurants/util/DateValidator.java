package restaurants.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidDateConstraint, String> {

	private String format;

	@Override
	public void initialize(ValidDateConstraint constraintAnnotation) {
		format = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext context) {

		if (date == null) {
			return true;
		}

		SimpleDateFormat df = new SimpleDateFormat(format);
		df.setLenient(false);
		try {
			df.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

}
