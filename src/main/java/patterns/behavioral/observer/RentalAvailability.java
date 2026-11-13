package main.java.patterns.behavioral.observer;

import main.java.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class RentalAvailability implements Subject {

    private List<Observer> observers;

    public RentalAvailability() {
        observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer observer) {

        if (observer == null) {
            throw new IllegalArgumentException(
                    "Observer cannot be null");
        }

        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Vehicle vehicle, String message) {

        for (Observer observer : observers) {
            observer.update(vehicle, message);
        }
    }

    public void updateAvailability(Vehicle vehicle, boolean available) {

        if (vehicle == null) {
            throw new IllegalArgumentException(
                    "Vehicle cannot be null");
        }

        if (vehicle.isAvailable() == available) {
            return;
        }

        vehicle.setAvailable(available);

        String message;

        if (available) {
            message = "Vehicle is now AVAILABLE for rental.";
        } else {
            message = "Vehicle is now NOT AVAILABLE for rental.";
        }

        notifyObservers(vehicle, message);
    }
}