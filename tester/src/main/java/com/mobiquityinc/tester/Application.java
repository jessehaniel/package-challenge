package com.mobiquityinc.tester;

import com.mobiquityinc.packer.Packer;
import com.mobiquityinc.packer.exception.APIException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    
    private static Logger logger = LogManager.getLogger(Application.class);
    
    public static void main(String[] args) throws APIException {
        String testFile = "";
        String answer = Optional.ofNullable(Packer.pack(testFile)).orElse("No response");
        String outputFile = "";
        try (FileWriter writer = new FileWriter(outputFile)){
            writer.write(answer);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
