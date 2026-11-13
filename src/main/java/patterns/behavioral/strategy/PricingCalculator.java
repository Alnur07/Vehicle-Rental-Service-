package main.java.patterns.behavioral.strategy;

import main.java.model.Vehicle;

public class PricingCalculator {

    private PricingStrategy pricingStrategy;

    public PricingCalculator(PricingStrategy pricingStrategy) {

        setPricingStrategy(pricingStrategy);
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {

        if (pricingStrategy == null) {
            throw new IllegalArgumentException(
                    "Pricing strategy cannot be null");
        }

        this.pricingStrategy = pricingStrategy;
    }

    public double calculatePrice(Vehicle vehicle, int duration) {

        return pricingStrategy.calculatePrice(vehicle, duration);
    }
}