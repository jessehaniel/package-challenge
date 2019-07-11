package com.mobiquityinc.packer;

import com.mobiquityinc.packer.exception.APIException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PackerTest {
    
    @Test
    void whenNotExistentFileThrowsApiException() {
        Assertions.assertThrows(APIException.class, () -> Packer.pack(""));
    }
    
    @Test
    void whenInvalidFileThrowsApiException() {
        Assertions.assertThrows(APIException.class, () -> {
            String filePath = prepareInputFile(getInvalidInput());
            Packer.pack(filePath);
        });
    }
    
    @Test
    void whenPackControlInputThenSuccess() throws APIException, IOException {
        String filePath = prepareInputFile(getControlInput());
        String solution = Packer.pack(filePath);
        String expectedSolution = "4\n-\n2,7\n8,9";
        Assertions.assertEquals(expectedSolution, solution);
    }
    
    @Test
    void whenPackChallengeInputThenSuccess() throws APIException, IOException {
        String filePath = prepareInputFile(getChallengeInput());
        String solution = Packer.pack(filePath);
        String expectedSolution = "8,9,11,12\n2,3,4\n1,2,3,4,5,6,7";
        Assertions.assertEquals(expectedSolution, solution);
    }
    
    private String prepareInputFile(String fileContent) throws IOException {
        String filePath = System.getProperty("java.io.tmpdir") + "testFile.txt";
        Path newPath = Files.write(Paths.get(filePath), fileContent.getBytes());
        return newPath.toString();
    }
    
    private String getControlInput() {
        return "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)\n"
            + "8 : (1,15.3,€34)\n"
            + "75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)\n"
            + "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)\n";
    }
    
    private String getInvalidInput() {
        return "120 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)\n"
            + "87 : (a,53,€10)\n"
            + "18: (1,10,20)\n"
            + "20 : (1,53.38,€45) 2,88.62,€98 (3,78.48,€3)";
    }
    
    private String getChallengeInput() {
        return "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) "
            + "(8,19.36,€79) (9,6.76,€64) (10,85.31,€29) (11,14.55,€74) (12,3.98,€16) (13,26.24,€55) (14,63.69,€52) "
            + "(15,76.25,€75) (16,60.02,€74) (17,93.18,€35) (18,89.95,€78)\n"
            + "75 : (1,85.31,€31) (2,14.55,€31) (3,3.98,€31) (4,26.24,€31) (5,63.69,€31) (6,76.25,€31) (7,60.02,€31) (8,93.18,€31) (9,89.95,€31)\n"
            + "75 : (1,5.31,€29) (2,4.55,€74) (3,3.98,€16) (4,26.24,€55) (5,3.69,€52) (6,6.25,€75) (7,6.02,€74)";
    }
}