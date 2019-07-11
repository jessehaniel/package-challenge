package com.mobiquityinc.packer.solution;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class Solution {
    
    private List<Integer> itemsIndexes;
    
    Solution(List<Integer> itemsIndexes) {
        this.itemsIndexes = itemsIndexes;
    }
    
    public List<Integer> getItemsIndexes() {
        return itemsIndexes;
    }
    
    public void setItemsIndexes(List<Integer> itemsIndexes) {
        this.itemsIndexes = itemsIndexes;
    }
    
    @Override
    public String toString() {
        List<Integer> sortedItems = itemsIndexes.stream().sorted().collect(Collectors.toList());
        return itemsIndexes.isEmpty() ? "-" : StringUtils.join(sortedItems, ",");
    }
}
