package main.java.model;

public class Booking {

    private String bookingId;
    private String customerName;
    private Vehicle vehicle;
    private int rentalDays;

    public Booking(String bookingId, String customerName,
                   Vehicle vehicle, int rentalDays) {

        if (bookingId == null || bookingId.isBlank()) {
            throw new IllegalArgumentException("Booking ID cannot be empty");
        }

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }

        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }

        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Rental days must be positive");
        }

        this.bookingId = bookingId;
        this.customerName = customerName;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                "\nCustomer: " + customerName +
                "\nVehicle: " + vehicle.getBrand() + " " + vehicle.getModel() +
                "\nRental days: " + rentalDays;
    }
}
