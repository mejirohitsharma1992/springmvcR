package com.jiro.Spring_Web_Application.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String> {
    //alt+shift+enter
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext context) {
        if(inputRole==null) return false;
        List<String>roles=List.of("USER","ADMIN");
        return roles.contains(inputRole);
    }
}
