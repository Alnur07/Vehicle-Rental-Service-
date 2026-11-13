package main.java.patterns.creational.factory;
import main.java.model.Car;
import main.java.model.Vehicle;

public class CarFactory extends VehicleFactory {

    @Override
    public Vehicle createVehicle(String id, String brand, String model, double pricePerHour) {
        return new Car(id,brand,model,pricePerHour);
    }
}
