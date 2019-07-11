package com.mobiquityinc.packer.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mobiquityinc.packer.pack.Pack;
import org.junit.jupiter.api.Test;

class KnapsackResolverCompleteSolutionTest {
    
    
    @Test
    void solve() {
        String stringPack = "56:(1,90.72,€13)(2,33.80,€40)(3,43.15,€10)(4,37.97,€16)(5,46.81,€36)(6,48.77,€79)"
            + "(7,81.80,€45)(8,19.36,€79)(9,6.76,€64)";
        KnapsackResolver resolver = new KnapsackResolver(new Pack(stringPack));
        
        Solution solution = resolver.solve();
        
        assertEquals(2, solution.getItemsIndexes().size());
        assertEquals("8,9", solution.toString());
    }
}