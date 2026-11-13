package main.java.patterns.behavioral.strategy;

import main.java.model.Vehicle;

public class DailyPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(Vehicle vehicle, int days) {

        if (vehicle == null) {
            throw new IllegalArgumentException(
                    "Vehicle cannot be null");
        }

        if (days <= 0) {
            throw new IllegalArgumentException(
                    "Rental days must be positive");
        }

        double dailyPrice = vehicle.getPricePerHour() * 24;

        return dailyPrice * days;
    }
}