package main.java.patterns.creational.factory;
import main.java.model.Vehicle;

public abstract class VehicleFactory {

    public abstract Vehicle createVehicle(
            String id,
            String brand,
            String model,
            double pricePerHour
    );
}
