package com.mobiquityinc.packer.item;

import com.mobiquityinc.packer.validation.ConstraintsValidation;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

public class Item {
    
    private static final int MAX_SIZE = 15;
    private static final String EURO_SYMBOL = "€";
    
    private static final DecimalFormat decimalFormatter = new DecimalFormat("0.##",
        new DecimalFormatSymbols(Locale.US));
    
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
            .map(Item::new)
            .limit(MAX_SIZE)
            .collect(Collectors.toList());
    }
    
    static String[] splitStringIntoListStringItem(String orderedItems) {
        //expected pattern -> (\(\d+,\d+(\.\d+)?,€\d+(\.\d+)?\))
        return orderedItems.substring(1, orderedItems.length() - 1).split("\\)\\(");
    }
    
    private void convertValidatingStringToItem(String stringItem) {
        String[] split = stringItem.split(",");
        this.index = Integer.valueOf(split[0]);
        this.weight = parseStringToDoubleRoudingUp(split[1]);
        this.value = parseStringToDoubleRoudingUp(split[2].replace("€", ""));
        
        ConstraintsValidation.validateFieldLimitBetweenZeroAndOneHundred(this.weight, "Item WEIGHT limit");
        ConstraintsValidation.validateFieldLimitBetweenZeroAndOneHundred(this.weight, "Item VALUE limit");
    }
    
    private Double parseStringToDoubleRoudingUp(String doubleString) {
        String formattedInput = decimalFormatter.format(Double.valueOf(doubleString));
        return Double.valueOf(formattedInput);
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
        return index == item.index &&
            Double.compare(item.weight, weight) == 0 &&
            Double.compare(item.value, value) == 0;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(index, weight, value);
    }
    
    @Override
    public String toString() {
        return "("
            + index + ","
            + decimalFormatter.format(weight) + ","
            + EURO_SYMBOL + decimalFormatter.format(value)
            + ")";
    }
}
