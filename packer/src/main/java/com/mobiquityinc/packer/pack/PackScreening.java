package com.mobiquityinc.packer.pack;

import com.mobiquityinc.packer.exception.OrderScreeningException;
import com.mobiquityinc.packer.validation.ConstraintsValidation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PackScreening implements IPackScreening {
    
    @Override
    public List<Pack> convertValidating(String filePath) throws OrderScreeningException {
        try {
            List<String> allLines = readAllLines(filePath);
            allLines.parallelStream().forEach(ConstraintsValidation::validateInputFormat);
            return convertValidatingStringLineToIntendedPack(allLines);
        } catch (Exception e) {
            throw new OrderScreeningException(e.getMessage(), e);
        }
    }
    
    @Override
    public List<String> readAllLines(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }
    
    @Override
    public List<Pack> convertValidatingStringLineToIntendedPack(List<String> packStringLineList) {
        return packStringLineList.parallelStream()
            .map(Pack::new)
            .collect(Collectors.toList());
    }
}
