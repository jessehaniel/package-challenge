package com.mobiquityinc.packer.pack;

import com.mobiquityinc.packer.exception.OrderScreeningException;
import java.io.IOException;
import java.util.List;

public interface IPackScreening {
    
    List<Pack> convertValidating(String filePath) throws OrderScreeningException;
    
    List<String> readAllLines(String filePath) throws IOException;
    
    List<Pack> convertValidatingStringLineToIntendedPack(List<String> orderStringLineList);
    
    Pack convertValidatingStringLineToIntendedPack(String orderStringLine);
    
}
