package com.mobiquityinc.packer;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Solution {
    
    private List<Integer> itemsIndexes;
    
    public Solution(List<Integer> itemsIndexes) {
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
        return itemsIndexes.isEmpty()? "-" : StringUtils.join(itemsIndexes, ",");
    }
}
