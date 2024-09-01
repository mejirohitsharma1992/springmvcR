package com.jiro.Spring_Web_Application.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = { EmployeeRoleValidator.class })
public @interface EmployeeRoleValidation {
//copy and paste 3 line
    String message() default "Role of Employee can either be  USER or ADMIN";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
