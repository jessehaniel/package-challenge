package com.mobiquityinc.packer.exception;

public class InvalidFileFormatException extends RuntimeException {
    
    public InvalidFileFormatException() {
        super();
    }
    
    public InvalidFileFormatException(String message) {
        super(message);
    }
}
