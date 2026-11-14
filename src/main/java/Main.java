package main.java;

import main.java.model.Vehicle;
import main.java.model.Booking;

import main.java.patterns.creational.factory.VehicleFactory;
import main.java.patterns.creational.factory.CarFactory;
import main.java.patterns.creational.factory.MotorcycleFactory;
import main.java.patterns.creational.factory.VanFactory;

import main.java.patterns.creational.builder.BookingBuilder;

import main.java.patterns.behavioral.strategy.PricingCalculator;
import main.java.patterns.behavioral.strategy.HourlyPricingStrategy;

import main.java.patterns.behavioral.observer.RentalAvailability;
import main.java.patterns.behavioral.observer.CustomerObserver;
import main.java.patterns.behavioral.observer.AdminObserver;

import main.java.patterns.structural.adapter.BankApi;
import main.java.patterns.structural.adapter.BankPaymentAdapter;
import main.java.patterns.structural.adapter.PaymentMethod;

import main.java.patterns.structural.facade.RentalFacade;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Vehicle> vehicles = new ArrayList<>();

    private static final Map<String, Booking> activeBookings = new LinkedHashMap<>();

    private static final Map<String, CustomerObserver> customerObservers = new LinkedHashMap<>();

    private static RentalAvailability availability;
    private static RentalFacade rentalFacade;

    private static int bookingCounter = 1;

    public static void main(String[] args) {

        initializeSystem();

        System.out.println("\n========================================");
        System.out.println("       VEHICLE RENTAL SERVICE");
        System.out.println("========================================");

        boolean running = true;

        while (running) {
            showMenu();

            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    showAllVehicles();
                    break;

                case 2:
                    rentVehicle();
                    break;

                case 3:
                    returnVehicle();
                    break;

                case 4:
                    showActiveBookings();
                    break;

                case 0:
                    running = false;
                    System.out.println("\nThank you for using Vehicle Rental Service!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void initializeSystem() {

        // FACTORY METHOD
        VehicleFactory carFactory = new CarFactory();
        VehicleFactory motorcycleFactory = new MotorcycleFactory();
        VehicleFactory vanFactory = new VanFactory();

        vehicles.add(carFactory.createVehicle("V001", "Toyota", "Camry", 1000));

        vehicles.add(carFactory.createVehicle("V002", "BMW", "X5", 2000));

        vehicles.add(motorcycleFactory.createVehicle("V003", "Yamaha", "MT-07", 800));

        vehicles.add(vanFactory.createVehicle("V004", "Mercedes", "Sprinter", 1500));

        // OBSERVER
        availability = new RentalAvailability();

        availability.attach(new AdminObserver("Administrator"));

        // STRATEGY
        PricingCalculator calculator = new PricingCalculator(new HourlyPricingStrategy());

        // ADAPTER
        BankApi bankApi = new BankApi();

        PaymentMethod paymentMethod = new BankPaymentAdapter(bankApi);

        // FACADE
        rentalFacade = new RentalFacade(calculator,paymentMethod,availability);
    }

    // Display menu
    private static void showMenu() {

        System.out.println("\n========================================");
        System.out.println("                 MENU");
        System.out.println("========================================");

        System.out.println("1. Show all vehicles");
        System.out.println("2. Rent a vehicle");
        System.out.println("3. Return a vehicle");
        System.out.println("4. View active bookings");
        System.out.println("0. Exit");

        System.out.println("========================================");
    }

    // Display all vehicles
    private static void showAllVehicles() {

        System.out.println("\n========== AVAILABLE VEHICLES ==========");

        for (Vehicle vehicle : vehicles) {

            System.out.println(
                    vehicle.getId() + " | " +
                            vehicle.getBrand() + " " +
                            vehicle.getModel() +
                            " | Hourly price: " +
                            vehicle.getPricePerHour() +
                            " | Status: " +
                            (vehicle.isAvailable() ? "Available" : "Rented")
            );
        }
    }

    // Rent a vehicle
    private static void rentVehicle() {

        System.out.println("\n========== RENT A VEHICLE ==========");

        List<Vehicle> availableVehicles = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }

        if (availableVehicles.isEmpty()) {
            System.out.println("No vehicles are currently available.");
            return;
        }

        System.out.println("\nAvailable vehicles:");

        for (int i = 0; i < availableVehicles.size(); i++) {

            Vehicle vehicle = availableVehicles.get(i);

            System.out.println(
                    (i + 1) + ". " +
                            vehicle.getBrand() + " " +
                            vehicle.getModel() +
                            " | " + vehicle.getPricePerHour() + " per hour"
            );
        }

        int vehicleChoice = readInt("\nSelect vehicle: ");

        if (vehicleChoice < 1 || vehicleChoice > availableVehicles.size()) {

            System.out.println("Invalid vehicle selection.");
            return;
        }

        Vehicle selectedVehicle = availableVehicles.get(vehicleChoice - 1);

        String customerName = readNonEmptyString(
                "Enter customer name: "
        );

        System.out.println("\nSelect rental type:");
        System.out.println("1. Hourly rental");
        System.out.println("2. Daily rental");

        int rentalType = readInt("Choose rental type: ");

        if (rentalType != 1 && rentalType != 2) {
            System.out.println("Invalid rental type.");
            return;
        }

        int duration = readInt(rentalType == 1 ? "Enter rental hours: " : "Enter rental days: "
        );

        if (duration <= 0) {
            System.out.println("Rental duration must be positive.");
            return;
        }

        // BUILDER
        String bookingId = String.format("B%03d", bookingCounter++);

        BookingBuilder builder = new BookingBuilder()
                .setBookingId(bookingId)
                .setCustomerName(customerName)
                .setVehicle(selectedVehicle);

        if (rentalType == 1) {
            builder.setRentalHours(duration);
        } else {
            builder.setRentalDays(duration);
        }

        Booking booking = builder.build();

        CustomerObserver customerObserver =
                new CustomerObserver(customerName);

        availability.attach(customerObserver);

        // FACADE: Process complete rental
        System.out.println("\nProcessing rental...");

        boolean success = rentalFacade.rentVehicle(booking);

        if (success) {
            activeBookings.put(bookingId, booking);
            customerObservers.put(bookingId, customerObserver);

            System.out.println("\nBooking completed successfully!");
            System.out.println("Booking ID: " + bookingId);

        } else{

            availability.detach(customerObserver);
            System.out.println("\nBooking was not completed.");
        }
    }

    // Return a rented vehicle
    private static void returnVehicle() {

        System.out.println("\n========== RETURN VEHICLE ==========");

        if (activeBookings.isEmpty()) {
            System.out.println("There are no active bookings.");
            return;
        }

        List<Booking> bookings =
                new ArrayList<>(activeBookings.values());

        for (int i = 0; i < bookings.size(); i++) {

            Booking booking = bookings.get(i);

            System.out.println(
                    (i + 1) + ". " +
                            booking.getBookingId() +
                            " | " + booking.getCustomerName() +
                            " | " + booking.getVehicle().getBrand() +
                            " " + booking.getVehicle().getModel()
            );
        }

        int choice = readInt("\nSelect booking to return: ");

        if (choice < 1 || choice > bookings.size()) {
            System.out.println("Invalid booking selection.");
            return;
        }

        Booking selectedBooking = bookings.get(choice - 1);

        // FACADE: Return vehicle
        rentalFacade.returnVehicle(selectedBooking.getVehicle());

        activeBookings.remove(selectedBooking.getBookingId());

        CustomerObserver observer =
                customerObservers.remove(selectedBooking.getBookingId());

        if (observer != null) {
            availability.detach(observer);
        }
    }

    // Show active bookings
    private static void showActiveBookings() {

        System.out.println("\n========== ACTIVE BOOKINGS ==========");

        if (activeBookings.isEmpty()) {
            System.out.println("No active bookings.");
            return;
        }

        for (Booking booking : activeBookings.values()) {

            System.out.println("\n" + booking);
            System.out.println("----------------------------------------");
        }
    }

    // Read integer safely
    private static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid whole number."
                );
            }
        }
    }

    // Read non-empty text
    private static String readNonEmptyString(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("This field cannot be empty.");
        }
    }
}
