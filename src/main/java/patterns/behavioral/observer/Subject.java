package main.java.patterns.behavioral.observer;

import main.java.model.Vehicle;

public interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers(Vehicle vehicle, String message);
}