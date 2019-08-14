package com.geektrust.cricket.random;


import java.util.Random;

/**
 * implementation for Randomizer that uses java.util.Random
 */
public class RandomizerImpl implements Randomizer {

    private Random random;

    //bound can not be changed once this randomizer is initialized so that values can be uniformly distributed.
    private int bound;

    public RandomizerImpl(int bound) {
        random = new Random();
        this.bound = bound;
    }

    public int next() {
        return random.nextInt(bound);
    }
}
