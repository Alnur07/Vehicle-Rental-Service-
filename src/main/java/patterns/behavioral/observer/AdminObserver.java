package main.java.patterns.behavioral.observer;

import main.java.model.Vehicle;

public class AdminObserver implements Observer {

    private String adminName;

    public AdminObserver(String adminName) {
        this.adminName = adminName;
    }

    @Override
    public void update(Vehicle vehicle, String message) {

        System.out.println("\n[ADMIN NOTIFICATION]");
        System.out.println("Administrator: " + adminName);
        System.out.println("Vehicle: " +
                vehicle.getBrand() + " " + vehicle.getModel());
        System.out.println("Message: " + message);
    }
}