
package main.java.patterns.structural.facade;

import main.java.model.Booking;
import main.java.model.Vehicle;

import main.java.patterns.structural.adapter.PaymentMethod;
import main.java.patterns.behavioral.observer.RentalAvailability;
import main.java.patterns.behavioral.strategy.PricingCalculator;

import java.util.Objects;

public class RentalFacade {

    private final PricingCalculator pricingCalculator;
    private final PaymentMethod paymentMethod;
    private final RentalAvailability rentalAvailability;

    public RentalFacade(PricingCalculator pricingCalculator,PaymentMethod paymentMethod,RentalAvailability rentalAvailability) {

        this.pricingCalculator = Objects.requireNonNull(pricingCalculator,"PricingCalculator cannot be null");

        this.paymentMethod = Objects.requireNonNull(paymentMethod,"PaymentMethod cannot be null");

        this.rentalAvailability = Objects.requireNonNull(rentalAvailability,"RentalAvailability cannot be null");
    }

    public boolean rentVehicle(Booking booking) {

        Objects.requireNonNull(booking,"Booking cannot be null");

        Vehicle vehicle = booking.getVehicle();

        if (!vehicle.isAvailable()) {
            System.out.println("Vehicle is not available.");
            return false;
        }

        double totalPrice;

        if (booking.getRentalHours() > 0 && booking.getRentalDays() == 0) {

            totalPrice = pricingCalculator.calculateHourlyPrice(vehicle,booking.getRentalHours());

        } else if (booking.getRentalDays() > 0 && booking.getRentalHours() == 0) {
            totalPrice = pricingCalculator.calculateDailyPrice(vehicle,booking.getRentalDays());
        }
        else {
            System.out.println("Invalid rental duration.");
            return false;
        }

        if (!Double.isFinite(totalPrice) || totalPrice <= 0) {
            System.out.println("Invalid rental price.");
            return false;
        }

        System.out.println("Total rental price: " + totalPrice);

        boolean paymentSuccessful = paymentMethod.pay(totalPrice);

        if (!paymentSuccessful) {
            System.out.println("Payment failed. Rental cancelled.");
            return false;
        }

        rentalAvailability.updateAvailability(vehicle,false);

        System.out.println("Vehicle rented successfully!");

        return true;
    }

    public void returnVehicle(Vehicle vehicle) {

        Objects.requireNonNull(vehicle,"Vehicle cannot be null");

        if (vehicle.isAvailable()) {
            System.out.println("Vehicle is already available.");
            return;
        }

        rentalAvailability.updateAvailability(vehicle, true);

        System.out.println("Vehicle returned successfully!");
    }
}
