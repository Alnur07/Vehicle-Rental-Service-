package main.java.patterns.behavioral.observer;

import main.java.model.Vehicle;

public interface Observer {

    void update(Vehicle vehicle, String message);
}