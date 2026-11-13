package main.java.patterns.creational.factory;

import main.java.model.Motorcycle;
import main.java.model.Vehicle;

public class MotorcycleFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle(String id, String brand, String model, double pricePerHour) {
        return new Motorcycle(id,brand,model,pricePerHour);
    }
}
