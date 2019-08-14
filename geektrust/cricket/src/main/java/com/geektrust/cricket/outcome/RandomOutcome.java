package com.geektrust.cricket.outcome;


import com.geektrust.cricket.random.Randomizer;

import java.util.ArrayList;
import java.util.List;

public class RandomOutcome implements Outcome {

    private Randomizer randomizer;
    private List<Integer> probabilities;

    public RandomOutcome(Randomizer randomizer, List<Integer> probabilities) {
        this.randomizer = randomizer;
        this.probabilities = new ArrayList<>();
        initializeProbabilityList(probabilities);
    }

    private void initializeProbabilityList(List<Integer> probabilities) {
        for(int i = 0; i < probabilities.size(); i++) {
            int val = probabilities.get(i);
            for(int j = 0; j < val; j++) {
                this.probabilities.add(i);
            }
        }
    }

    public Integer getOutcome() {
        return probabilities.get(randomizer.next());
    }
}
