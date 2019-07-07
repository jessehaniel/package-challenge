package com.mobiquityinc.packer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Packer {
    
    private static final Logger logger = LogManager.getLogger(Packer.class);
    
    public static String pack(String filePath) throws APIException {
        List<String> allLines = readInputFile(filePath);
        validateInputFile(allLines);
    
        List<OrderedPack> orderedPacks = allLines.parallelStream()
            .flatMap(Packer::convertToItemsOrder)
            .collect(Collectors.toList());
    
        return orderedPacks.parallelStream()
            .map(Packer::solveKnapsack)
            .map(Solution::toString)
            .collect(Collectors.joining("\n"));
    }
    
    private static Solution solveKnapsack(OrderedPack orderedPack) {
        return new Solution(Collections.emptyList());
    }
    
    private static Stream<OrderedPack> convertToItemsOrder(String line) {
        String[] split = line.split(":");
        int packWeightLimit = Integer.parseInt(split[0]);
        List<Item> orderedItems = convertItemsStringToItemsList(split[1]);
        return Stream.of(new OrderedPack(packWeightLimit, orderedItems));
    }
    
    private static List<Item> convertItemsStringToItemsList(String orderedItems) {
        List<String> listOfStringItem = Arrays.asList(
            splitStringIntoListStringItem(orderedItems));
        return listOfStringItem.parallelStream()
            .map(Item::new)
            .collect(Collectors.toList());
    }
    
    private static String[] splitStringIntoListStringItem(String orderedItems) {
        return orderedItems.replaceAll("(", "").split(")");
    }
    
    private static void validateInputFile(List<String> allLines) throws APIException {
        boolean allMatch = allLines.stream()
            .allMatch(Packer::lineMatch);
        if (negate(allMatch)) {
            throw new APIException("Not all lines match the specified input pattern");
        }
    }
    
    private static boolean negate(boolean booleanValue) {
        return !booleanValue;
    }
    
    private static boolean lineMatch(String line) {
        String regex = "^1?\\d\\d?:(\\(1?\\d,\\d\\d?(\\.\\d+)?,€?\\d+\\))+$";
        return line.replaceAll("\\s+", "").matches(regex);
    }
    
    private static List<String> readInputFile(String filePath) throws APIException {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new APIException(e);
        }
    }
}
