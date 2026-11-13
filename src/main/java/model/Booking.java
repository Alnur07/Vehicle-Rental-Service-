package main.java.model;

public class Booking {

    private String bookingId;
    private String customerName;
    private Vehicle vehicle;
    private int rentalDays;
    private int rentalHours;

    public Booking(String bookingId, String customerName,
                   Vehicle vehicle, int rentalDays,int rentalHours) {

        if (bookingId == null || bookingId.isBlank()) {
            throw new IllegalArgumentException("Booking ID cannot be empty");
        }

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }

        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }

        if (rentalDays < 0 || rentalHours < 0) {
            throw new IllegalArgumentException("Rental duration cannot be negative");
        }

        if (rentalDays > 0 && rentalHours > 0) {
            throw new IllegalArgumentException("Choose either days or hours");
        }

        if (rentalDays == 0 && rentalHours == 0) {
            throw new IllegalArgumentException("Rental duration must be positive");
        }

        this.bookingId = bookingId;
        this.customerName = customerName;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.rentalHours = rentalHours;
    }

    public Booking(String bookingId, String customerName,
                   Vehicle vehicle, int rentalDays) {
        this(bookingId, customerName, vehicle, rentalDays, 0);
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

    public int getRentalHours() {
        return rentalHours;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                "\nCustomer: " + customerName +
                "\nVehicle: " + vehicle.getBrand() + " " + vehicle.getModel() +
                (rentalDays > 0
                        ? "\nRental days: " + rentalDays
                        : "\nRental hours: " + rentalHours);
    }
}
