
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
                    "Pricing strategy cannot be null"
            );
        }

        this.pricingStrategy = pricingStrategy;
    }

    public double calculatePrice(Vehicle vehicle, int duration) {

        if (pricingStrategy == null) {
            throw new IllegalStateException(
                    "Pricing strategy has not been selected"
            );
        }

        return pricingStrategy.calculatePrice(vehicle, duration);
    }

    // Explicit hourly calculation
    public double calculateHourlyPrice(Vehicle vehicle, int hours) {

        setPricingStrategy(new HourlyPricingStrategy());

        return calculatePrice(vehicle, hours);
    }

    // Explicit daily calculation
    public double calculateDailyPrice(Vehicle vehicle, int days) {

        setPricingStrategy(new DailyPricingStrategy());

        return calculatePrice(vehicle, days);
    }
}
