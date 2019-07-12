package com.mobiquityinc.packer.pack;

import com.mobiquityinc.packer.item.Item;
import com.mobiquityinc.packer.validation.ConstraintsValidation;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Pack {
    
    private static final DecimalFormat decimalFormatter = new DecimalFormat("0.##");
    
    private double packageWeightLimit;
    private List<Item> itemsList;
    
    public Pack(String orderStringLine) {
        super();
        convertValidatingStringToOrder(orderStringLine);
    }
    
    private void convertValidatingStringToOrder(String orderStringLine) {
        String[] split = orderStringLine.split(":");
        this.packageWeightLimit = Double.parseDouble(split[0]);
        this.itemsList = Item.convertItemsStringToItemsList(split[1]);
        ConstraintsValidation
            .validateFieldLimitBetweenZeroAndOneHundred(this.packageWeightLimit, "Package weight limit");
    }
    
    public double getPackageWeightLimit() {
        return packageWeightLimit;
    }
    
    public List<Item> getItemsList() {
        return itemsList;
    }
    
    @Override
    public String toString() {
        return decimalFormatter.format(packageWeightLimit) + ":"
            + itemsList.stream()
            .map(Item::toString)
            .collect(Collectors.joining());
    }
}
