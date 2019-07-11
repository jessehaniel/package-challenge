package com.mobiquityinc.packer.solution;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mobiquityinc.packer.item.Item;
import com.mobiquityinc.packer.pack.Pack;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KnapsackResolverTest {
    
    private KnapsackResolver resolver;
    private List<Item> eligibleItemsSortedList;
    
    @BeforeEach
    void setUp() {
        String stringPack = "81:(1,53.38,€45)(2,88.62,€98)(3,78.48,€3)(4,72.30,€76)(5,30.18,€9)(6,46.34,€48)";
        this.resolver = new KnapsackResolver(new Pack(stringPack));
        this.eligibleItemsSortedList = Arrays.asList(
            new Item("4,72.3,€76"),
            new Item("6,46.34,€48"),
            new Item("1,53.38,€45"),
            new Item("5,30.18,€9"),
            new Item("3,78.48,€3")
        );
    }
    
    @Test
    void mapChosenItemsToSolution() {
        this.resolver.setChosenItems(Arrays.asList(
            new Item(1, 53.38, 45),
            new Item(2, 88.62, 98)
        ));
        Solution solution = this.resolver.mapChosenItemsToSolution();
        assertEquals(solution.toString(), "1,2");
    }
    
    @Test
    void filterEligibleItemsAndSortByValueDescAndWeight() {
        List<Item> itemList = this.resolver.filterEligibleItemsAndSortByValueDescAndWeight();
        assertThat(itemList, is(eligibleItemsSortedList));
    }
    
    @Test
    void doesItFitInKnapsack() {
        this.resolver.setChosenItems(Collections.singletonList(
            new Item(1, 53.38, 45)
        ));
        boolean fit = this.resolver.doesItFitInKnapsack(new Item("5,30.18,€9"));
        assertFalse(fit);
    }
    
    @Test
    void whenNoneChosenDoesItFitInKnapsack() {
        this.resolver.setChosenItems(Collections.emptyList());
        Item item = eligibleItemsSortedList.stream().findAny().get();
        boolean fit = this.resolver.doesItFitInKnapsack(item);
        assertTrue(fit);
    }
    
    @Test
    void chooseBestFitItems() {
        List<Item> chosenItems = this.resolver.chooseBestFitItems(eligibleItemsSortedList);
        assertEquals(1, chosenItems.size());
        assertEquals(eligibleItemsSortedList.stream().findFirst(), chosenItems.stream().findFirst());
    }
}