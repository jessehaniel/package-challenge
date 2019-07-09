package com.mobiquityinc.packer.order;

import com.mobiquityinc.packer.item.Item;
import com.mobiquityinc.packer.validation.ConstraintsValidation;
import java.util.List;

public class IntendedPack {
    
    private double packageWeightLimit;
    private List<Item> orderedItems;
    
    public IntendedPack() {
        super();
    }
    
    public IntendedPack(double packageWeightLimit, List<Item> orderedItems) {
        this.packageWeightLimit = packageWeightLimit;
        this.orderedItems = orderedItems;
    }
    
    public IntendedPack(String orderStringLine) {
        this();
        convertValidatingStringToOrder(orderStringLine);
    }
    
    private void convertValidatingStringToOrder(String orderStringLine) {
        String[] split = orderStringLine.split(":");
        this.packageWeightLimit = Double.parseDouble(split[0]);
        this.orderedItems = Item.convertItemsStringToItemsList(split[1]);
        ConstraintsValidation
            .validateFieldLimitBetweenZeroAndOneHundred(this.packageWeightLimit, "Package weight limit");
    }
    
    public double getPackageWeightLimit() {
        return packageWeightLimit;
    }
    
    public void setPackageWeightLimit(double packageWeightLimit) {
        this.packageWeightLimit = packageWeightLimit;
    }
    
    public List<Item> getOrderedItems() {
        return orderedItems;
    }
    
    public void setOrderedItems(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }
}
