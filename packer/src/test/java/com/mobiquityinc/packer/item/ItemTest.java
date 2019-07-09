package com.mobiquityinc.packer.item;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {
    
    @BeforeEach
    void setUp() {
    }
    
    @Test
    void convertItemsStringToItemsList() {
        String itemsString = "(1,53.38,€45)(2,88.62,€98)(3,78.48,€3)(4,72.30,€76)(5,30.18,€9)(6,46.34,€48)";
        List<Item> expectedItemsList = Arrays.asList(
            new Item(1, 53.38, 45),
            new Item(2, 88.62, 98),
            new Item(3, 78.48, 3),
            new Item(4, 72.30, 76),
            new Item(5, 30.18, 9),
            new Item(6, 46.34, 48));
        List<Item> actualItemList = Item.convertItemsStringToItemsList(itemsString);
        
        assertEquals(expectedItemsList.size(), actualItemList.size());
        assertThat(actualItemList, is(expectedItemsList));
    }
    
    @Test
    void createItemFromString() {
        String itemString = "6,46.34,€48";
        Item item = new Item(itemString);
        
        assertEquals(6, item.getIndex());
        assertEquals(46.34, item.getWeight());
        assertEquals(48, item.getValue());
    }
    
    @Test
    void splitStringIntoListStringItem() {
        String itemsString = "(1,53.38,€45)(2,88.62,€98)(3,78.48,€3)";
        String[] expectedItemArray = new String[]{"1,53.38,€45", "2,88.62,€98", "3,78.48,€3"};
        String[] splitItemsArray = Item.splitStringIntoListStringItem(itemsString);
        
        assertEquals(expectedItemArray.length, splitItemsArray.length);
        assertThat(splitItemsArray, is(expectedItemArray));
    }
}