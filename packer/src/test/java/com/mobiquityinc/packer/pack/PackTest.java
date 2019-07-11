package com.mobiquityinc.packer.pack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mobiquityinc.packer.item.Item;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class PackTest {
    
    @Test
    void testNewPackFromString() {
        String stringPack = "81:(1,53.38,€45)(2,88.62,€98)(3,78.48,€3)(4,72.30,€76)(5,30.18,€9)(6,46.34,€48)";
        Pack pack = new Pack(stringPack);
        List<Item> expectedItemsList = Arrays.asList(
            new Item(1, 53.38, 45),
            new Item(2, 88.62, 98),
            new Item(3, 78.48, 3),
            new Item(4, 72.30, 76),
            new Item(5, 30.18, 9),
            new Item(6, 46.34, 48));
        assertEquals(Double.valueOf("81"), pack.getPackageWeightLimit());
        assertThat(pack.getItemsList(), is(expectedItemsList));
    }
}