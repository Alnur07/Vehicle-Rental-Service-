package main.java.patterns.behavioral.strategy;

import main.java.model.Vehicle;

public class HourlyPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(Vehicle vehicle, int hours) {

        if (vehicle == null) {
            throw new IllegalArgumentException(
                    "Vehicle cannot be null");
        }

        if (hours <= 0) {
            throw new IllegalArgumentException(
                    "Rental hours must be positive");
        }

        return vehicle.getPricePerHour() * hours;
    }
}