package main.java.patterns.behavioral.strategy;

import main.java.model.Vehicle;

public interface PricingStrategy {

    double calculatePrice(Vehicle vehicle, int duration);
}