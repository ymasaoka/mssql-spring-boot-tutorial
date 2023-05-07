package jp.mappiekochi.sample.firstmssqlproject.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jp.mappiekochi.sample.firstmssqlproject.annotation.Phone;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private boolean onlyNumber = false;

    @Override
    public void initialize(Phone phone) {
        onlyNumber = phone.onlyNumber();
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext cxt) {
        if (input == null) {
            return true;
        }
        if (onlyNumber) {
            return input.matches("[0-9]*");
        } else {
            return input.matches("^0([0-9]-[0-9]{4}|[0-9]{2}-[0-9]{3}|[0-9]{3}-[0-9]{2}|[0-9]{4}-[0-9])-[0-9]{4}$");
        }
    }
}
