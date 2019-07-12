package com.mobiquityinc.packer.solution;

import com.mobiquityinc.packer.item.Item;
import com.mobiquityinc.packer.pack.Pack;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class KnapsackResolver {
    
    private List<Item> chosenItems;
    private Pack pack;
    
    public KnapsackResolver(Pack pack) {
        super();
        this.pack = pack;
    }
    
    public Solution solve() {
        List<Item> eligibleItemsSortedList = filterEligibleItemsAndSortByValueDescAndWeight();
        this.chosenItems = chooseBestFitItems(eligibleItemsSortedList);
        return mapChosenItemsToSolution();
    }
    
    List<Item> filterEligibleItemsAndSortByValueDescAndWeight() {
        return this.pack.getItemsList().parallelStream()
            .filter(this::itemWeightBelowLimit)
            .sorted(Comparator.comparingDouble(Item::getValue).reversed()
                .thenComparingDouble(Item::getWeight))
            .collect(Collectors.toList());
    }
    
    private boolean itemWeightBelowLimit(Item item) {
        return item.getWeight() <= this.pack.getPackageWeightLimit();
    }
    
    List<Item> chooseBestFitItems(List<Item> eligibleItemsSortedList) {
        this.chosenItems = new ArrayList<>();
        eligibleItemsSortedList.forEach(item -> {
            if (doesItFitInKnapsack(item)) {
                chosenItems.add(item);
            }
        });
        return chosenItems;
    }
    
    Solution mapChosenItemsToSolution() {
        return new Solution(
            this.chosenItems.parallelStream()
                .map(Item::getIndex)
                .collect(Collectors.toList())
        );
    }
    
    boolean doesItFitInKnapsack(Item eligibleItem) {
        return computeSumOfAllChosenItemsWeights() + eligibleItem.getWeight() < this.pack.getPackageWeightLimit();
    }
    
    private double computeSumOfAllChosenItemsWeights() {
        return this.chosenItems.parallelStream()
            .map(Item::getWeight)
            .reduce(Double::sum)
            .orElse(0d);
    }
    
    void setChosenItems(List<Item> chosenItems) {
        this.chosenItems = chosenItems;
    }
}
