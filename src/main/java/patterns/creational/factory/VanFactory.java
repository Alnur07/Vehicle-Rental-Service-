package main.java.patterns.creational.factory;

import main.java.model.Van;
import main.java.model.Vehicle;

public class VanFactory extends VehicleFactory {

    @Override
    public Vehicle createVehicle(String id, String brand, String model, double pricePerHour) {
        return new Van(id,brand,model,pricePerHour);
    }
}
