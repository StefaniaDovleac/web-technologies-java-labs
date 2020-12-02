package com.unibuc.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {MaxNumberOfValidator.class})
public @interface MaxNumberOf {
    String message() default "Invalid request";

    Class<?> [] groups() default {};

    Class<? extends Payload> [] payload() default {};
}
