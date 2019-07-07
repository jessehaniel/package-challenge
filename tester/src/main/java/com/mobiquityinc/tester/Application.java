package com.mobiquityinc.tester;

import com.mobiquityinc.packer.APIException;
import com.mobiquityinc.packer.Packer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class Application {
    
    public static void main(String[] args) throws APIException {
        String testFile = "";
        String answer = Optional.ofNullable(Packer.pack(testFile)).orElse("No response");
        String outputFile = "";
        try (FileWriter writer = new FileWriter(outputFile)){
            writer.write(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
