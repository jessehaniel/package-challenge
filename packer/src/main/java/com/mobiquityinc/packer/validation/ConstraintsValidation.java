package com.mobiquityinc.packer.validation;

import com.mobiquityinc.packer.exception.InvalidFieldException;
import com.mobiquityinc.packer.exception.InvalidFileFormatException;

public class ConstraintsValidation {
    
    private static final double UPPER_WEIGHT_LIMIT = 100;
    private static final double LOWER_WEIGHT_LIMIT = 0;
    private static final String LINE_PATTERN = "^\\d+(\\.\\d+)?:(\\(\\d+,\\d+(\\.\\d+)?,€\\d+(\\.\\d+)?\\))+$";
    
    private ConstraintsValidation() {
        super();
    }
    
    public static void validateFieldLimitBetweenZeroAndOneHundred(double weight, String fieldName) {
        if (weight < LOWER_WEIGHT_LIMIT || weight > UPPER_WEIGHT_LIMIT) {
            String message = String.format("Invalid field {%s}. It was expected {%s}",
                fieldName, "Between 0 and 100");
            throw new InvalidFieldException(message);
        }
    }
    
    public static void validateInputFormat(String line) throws InvalidFileFormatException {
        if (negate(lineMatch(line))) {
            String message = String.format("Line {%s} doesn't match the expected input format.", line);
            throw new InvalidFileFormatException(message);
        }
    }
    
    private static boolean negate(boolean booleanValue) {
        return !booleanValue;
    }
    
    private static boolean lineMatch(String line) {
        return line.replaceAll("\\s+", "").matches(LINE_PATTERN);
    }
}
