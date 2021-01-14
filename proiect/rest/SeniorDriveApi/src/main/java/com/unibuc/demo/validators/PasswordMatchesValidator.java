package com.unibuc.demo.validators;

import com.unibuc.demo.dto.DirectoryDTO;
import com.unibuc.demo.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserDTO> {

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext constraintValidatorContext) {
        return  userDTO.getPassword().equals(userDTO.getMatchingPassword());
    }
}
