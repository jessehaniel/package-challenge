package com.mobiquityinc.packer.item;

import com.mobiquityinc.packer.validation.ConstraintsValidation;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Item {
    
    private int index;
    private double weight;
    private double value;
    
    public Item() {
        super();
    }
    
    public Item(int index, double weight, double value) {
        this.index = index;
        this.weight = weight;
        this.value = value;
    }
    
    public Item(String stringItem) {
        this();
        convertValidatingStringToItem(stringItem);
    }
    
    public static List<Item> convertItemsStringToItemsList(String orderedItems) {
        List<String> listOfStringItem = Arrays.asList(splitStringIntoListStringItem(orderedItems));
        return listOfStringItem.parallelStream()
            .map(s -> s.replaceAll("\\(", "").replaceAll("\\)", ""))
            .map(Item::new)
            .collect(Collectors.toList());
    }
    
    private static String[] splitStringIntoListStringItem(String orderedItems) {
        return orderedItems.split("(\\(\\d+,\\d+(\\.\\d+)?,€\\d+(\\.\\d+)?\\))");
    }
    
    private void convertValidatingStringToItem(String stringItem) {
        String[] split = stringItem.split(",");
        this.index = Integer.valueOf(split[0]);//XXX is this necessary?
        this.weight = Double.valueOf(split[1]);
        this.value = Double.valueOf(split[2].replace("€", ""));
        
        ConstraintsValidation.validateFieldLimitBetweenZeroAndOneHundred(this.weight, "Item WEIGHT limit");
        ConstraintsValidation.validateFieldLimitBetweenZeroAndOneHundred(this.weight, "Item VALUE limit");
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public double getValue() {
        return value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return value == item.value;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
