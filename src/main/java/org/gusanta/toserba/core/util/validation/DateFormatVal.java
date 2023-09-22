package org.gusanta.toserba.core.util.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateFormatValidation.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormatVal {
    String message() default "Wrong Date format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
