package main.java.model;

public abstract class Vehicle {

    private String id;
    private String brand;
    private String model;
    private double pricePerHour;
    private boolean available;

    public Vehicle(String id, String brand, String model, double pricePerHour) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.pricePerHour = pricePerHour;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return brand + " " + model +
                " | Price per hour: " + pricePerHour +
                " | Available: " + available;
    }
}
