package com.mobiquityinc.packer.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class SolutionTest {
    
    @Test
    void solutionToString() {
        Solution solution = new Solution(Arrays.asList(2, 7));
        assertEquals(solution.toString(), "2,7");
    }
}