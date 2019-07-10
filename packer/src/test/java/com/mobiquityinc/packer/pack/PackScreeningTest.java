package com.mobiquityinc.packer.pack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.mobiquityinc.packer.exception.OrderScreeningException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackScreeningTest {
    
    private IPackScreening packScreening;
    
    @BeforeEach
    void setUp() {
        this.packScreening = new PackScreening();
    }
    
    @Test
    void whenInvalidFilePathThrowOrderScreeningException() {
        assertThrows(OrderScreeningException.class, () -> this.packScreening.convertValidating(""));
    }
    
    @Test
    void convertValidating() throws IOException, OrderScreeningException {
        String fileContent = "81:(1,53.38,€45)(2,88.62,€98)(3,78.48,€3)";
        String filePath = System.getProperty("java.io.tmpdir") + "testFile.txt";
        Path newFilePath = Files.write(Paths.get(filePath), fileContent.getBytes());
        
        List<Pack> packList = this.packScreening.convertValidating(newFilePath.toString());
        String packListString = packList.parallelStream().map(Pack::toString).collect(Collectors.joining());
        
        assertEquals(packListString, fileContent);
    }
    
    @Test
    void readAllLines() throws IOException {
        String fileContent = "Hello\nWorld\n!";
        String filePath = System.getProperty("java.io.tmpdir") + "testFile.txt";
        Path newFilePath = Files.write(Paths.get(filePath), fileContent.getBytes());
        
        List<String> allLines = this.packScreening.readAllLines(newFilePath.toString());
        
        List<String> originalContentList = Arrays.asList(fileContent.split("\n"));
        assertThat(allLines, is(originalContentList));
    }
    
    @Test
    void convertStringLineToIntendedPack() {
        //without trailing zeros
        List<String> packStringLineList = Arrays.asList(
            "81:(1,53.38,€45)(2,88.62,€98)(3,78.48,€3)(4,72.3,€76)(5,30.18,€9)(6,46.34,€48)",
            "8:(1,15.3,€34)",
            "75:(1,85.31,€29)(2,14.55,€74)(3,3.98,€16)(4,26.24,€55)(5,63.69,€52)(6,76.25,€75)(7,60.02,€74)(8,93.18,€35)(9,89.95,€78)",
            "56:(1,90.72,€13)(2,33.8,€40)(3,43.15,€10)(4,37.97,€16)(5,46.81,€36)(6,48.77,€79)(7,81.8,€45)(8,19.36,€79)(9,6.76,€64)"
        );
        List<Pack> packList = this.packScreening.convertValidatingStringLineToIntendedPack(packStringLineList);
        List<String> stringListConverted = packList.parallelStream().map(Pack::toString).collect(Collectors.toList());
        
        assertThat(stringListConverted, is(packStringLineList));
    }
}