package org.gusanta.toserba.core.util.validation;


import org.gusanta.toserba.core.util.CheckUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class DateFormatValidation implements ConstraintValidator<DateFormatVal, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return CheckUtil.isDateFormatValid(value);
    }
}
