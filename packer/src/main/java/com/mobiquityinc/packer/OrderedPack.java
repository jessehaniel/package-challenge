package com.mobiquityinc.packer;

import java.util.List;

public class OrderedPack {
    
    private int packageWeightLimit;
    private List<Item> orderedItems;
    
    public OrderedPack() {
        super();
    }
    
    public OrderedPack(int packageWeightLimit, List<Item> orderedItems) {
        this.packageWeightLimit = packageWeightLimit;
        this.orderedItems = orderedItems;
    }
    
    public int getPackageWeightLimit() {
        return packageWeightLimit;
    }
    
    public void setPackageWeightLimit(int packageWeightLimit) {
        this.packageWeightLimit = packageWeightLimit;
    }
    
    public List<Item> getOrderedItems() {
        return orderedItems;
    }
    
    public void setOrderedItems(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }
}
