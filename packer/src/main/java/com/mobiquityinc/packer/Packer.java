package com.mobiquityinc.packer;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.pack.IPackScreening;
import com.mobiquityinc.packer.pack.Pack;
import com.mobiquityinc.packer.pack.PackScreening;
import com.mobiquityinc.packer.solution.KnapsackResolver;
import com.mobiquityinc.packer.solution.Solution;
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
            IPackScreening packScreening = new PackScreening();
            List<Pack> packs = packScreening.convertValidating(filePath);
    
            return packs.parallelStream()
                .map(pack -> new KnapsackResolver(pack).solve())
                .map(Solution::toString)
                .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new APIException(e);
        }
    }
}
