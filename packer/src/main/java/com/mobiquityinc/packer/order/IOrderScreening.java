package com.mobiquityinc.packer.order;

import com.mobiquityinc.packer.exception.OrderScreeningException;
import java.io.IOException;
import java.util.List;

public interface IOrderScreening {
    
    List<IntendedPack> convertValidating(String filePath) throws OrderScreeningException;
    
    List<String> readAllLines(String filePath) throws IOException;
    
    List<IntendedPack> convertValidatingStringLineToIntendedPack(List<String> orderStringLineList);
    
    IntendedPack convertValidatingStringLineToIntendedPack(String orderStringLine);
    
}
