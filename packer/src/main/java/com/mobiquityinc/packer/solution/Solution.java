package com.mobiquityinc.packer.solution;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class Solution {
    
    private List<Integer> itemsIndexes;
    
    Solution(List<Integer> itemsIndexes) {
        this.itemsIndexes = itemsIndexes;
    }
    
    List<Integer> getItemsIndexes() {
        return itemsIndexes;
    }
    
    @Override
    public String toString() {
        List<Integer> sortedItems = itemsIndexes.stream().sorted().collect(Collectors.toList());
        return itemsIndexes.isEmpty() ? "-" : StringUtils.join(sortedItems, ",");
    }
}
