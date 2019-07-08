package com.mobiquityinc.packer.order;

import com.mobiquityinc.packer.exception.OrderScreeningException;
import java.io.IOException;
import java.util.List;

public interface IOrderScreening {
    
    List<Order> convertValidating(String filePath) throws OrderScreeningException;
    
    List<String> readAllLines(String filePath) throws IOException;
    
    List<Order> convertStringLineToOrderedPack(List<String> orderStringLineList);
    
    Order convertStringLineToOrderedPack(String orderStringLine);
    
}
