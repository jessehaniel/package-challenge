package com.mobiquityinc.packer;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.order.IntendedPack;
import com.mobiquityinc.packer.order.OrderScreening;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Packer {
    
    private static final Logger logger = LogManager.getLogger(Packer.class);
    
    private Packer() {
        super();
    }
    
    public static String pack(String filePath) throws APIException {
        try {
            List<IntendedPack> intendedPacks = new OrderScreening().convertValidating(filePath);
            return intendedPacks.parallelStream()
                .map(Packer::solveKnapsack)
                .map(Solution::toString)
                .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new APIException(e);
        }
    }
    
    private static Solution solveKnapsack(IntendedPack intendedPack) {
        return new Solution(Collections.emptyList());
    }
}
