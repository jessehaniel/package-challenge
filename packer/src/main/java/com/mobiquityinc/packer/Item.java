package com.mobiquityinc.packer;

import java.util.Objects;

public class Item {
    
    private int index;
    private double weight;
    private int value;
    
    public Item() {
        super();
    }
    
    public Item(int index, double weight, int value) {
        this.index = index;
        this.weight = weight;
        this.value = value;
    }
    
    public Item(String stringItem) {
        this();
        String[] split = stringItem.split(",");
        this.index = Integer.valueOf(split[0]);
        this.weight = Double.valueOf(split[1]);
        this.value = Integer.valueOf(split[2].replace("€",""));
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
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
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
