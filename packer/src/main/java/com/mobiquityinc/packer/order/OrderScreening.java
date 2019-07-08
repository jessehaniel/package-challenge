package com.mobiquityinc.packer.order;

import com.mobiquityinc.packer.exception.OrderScreeningException;
import com.mobiquityinc.packer.validation.ConstraintsValidation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class OrderScreening implements IOrderScreening {
    
    @Override
    public List<Order> convertValidating(String filePath) throws OrderScreeningException {
        try {
            List<String> allLines = readAllLines(filePath);
            allLines.parallelStream().forEach(ConstraintsValidation::validateInputFormat);
            List<Order> orderList = convertStringLineToOrderedPack(allLines);
            orderList.parallelStream().forEach(ConstraintsValidation::validateOrderConstraints);
            return orderList;
        } catch (Exception e) {
            throw new OrderScreeningException(e.getMessage(), e);
        }
    }
    
    @Override
    public List<String> readAllLines(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }
    
    @Override
    public List<Order> convertStringLineToOrderedPack(List<String> orderStringLineList) {
        return orderStringLineList.parallelStream()
            .map(this::convertStringLineToOrderedPack)
            .collect(Collectors.toList());
    }
    
    @Override
    public Order convertStringLineToOrderedPack(String orderStringLine) {
        return new Order(orderStringLine);
    }
}
