package com.unibuc.demo.validators;

import com.unibuc.demo.dto.DirectoryDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MaxNumberOfValidator implements ConstraintValidator<MaxNumberOf, DirectoryDTO> {
    @Override
    public boolean isValid(DirectoryDTO directoryDTO, ConstraintValidatorContext constraintValidatorContext) {
        return directoryDTO.getFiles().stream().count() <= directoryDTO.getMaxNumberOfFiles();
    }
}
