package main.java.patterns.behavioral.observer;

import main.java.model.Vehicle;

public class CustomerObserver implements Observer {

    private String customerName;

    public CustomerObserver(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void update(Vehicle vehicle, String message) {

        System.out.println("\n[CUSTOMER NOTIFICATION]");
        System.out.println("Customer: " + customerName);
        System.out.println("Vehicle: " +
                vehicle.getBrand() + " " + vehicle.getModel());
        System.out.println("Message: " + message);
    }
}