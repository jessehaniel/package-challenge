package com.mobiquityinc.packer.validation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.mobiquityinc.packer.exception.InvalidFieldException;
import com.mobiquityinc.packer.exception.InvalidFileFormatException;
import org.junit.jupiter.api.Test;

class ConstraintsValidationTest {
    
    @Test
    void validInputFormat() {
        String validInputLine = "81:(1,53.38,€45)(2,88.62,€98)(3,78.48,€3)(4,72.30,€76)(5,30.18,€9)(6,46.34,€48)";
        ConstraintsValidation.validateInputFormat(validInputLine);
    }
    
    @Test
    void whenUnexpectedExtraDotThrowsInvalidInputFormat() {
        String unexpectedExtraDotInputLine =
            "81 : (1,5.3.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        assertThrows(InvalidFileFormatException.class,
            () -> ConstraintsValidation.validateInputFormat(unexpectedExtraDotInputLine));
    }
    
    @Test
    void validFieldLimitBetweenZeroAndOneHundred() {
        double weight = 81;
        String fieldName = "Package limit";
        ConstraintsValidation.validateFieldLimitBetweenZeroAndOneHundred(weight, fieldName);
    }
    
    @Test
    void whenOutOfLimitsInputThrowsInvalidFieldException() {
        double weight = 120;
        String fieldName = "Package limit";
        assertThrows(InvalidFieldException.class, () ->
            ConstraintsValidation.validateFieldLimitBetweenZeroAndOneHundred(weight, fieldName));
    }
}