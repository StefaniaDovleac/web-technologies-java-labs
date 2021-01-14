package com.unibuc.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PasswordMatchesValidator.class})
public @interface PasswordMatches {
    String message() default "Password don't match";

    Class<?> [] groups() default {};

    Class<? extends Payload> [] payload() default {};
}
