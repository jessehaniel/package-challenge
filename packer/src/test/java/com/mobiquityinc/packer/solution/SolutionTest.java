package com.mobiquityinc.packer.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class SolutionTest {
    
    @Test
    void solutionToString() {
        Solution solution = new Solution(Arrays.asList(7, 2, 5, 3));
        assertEquals(solution.toString(), "2,3,5,7");
    }
}