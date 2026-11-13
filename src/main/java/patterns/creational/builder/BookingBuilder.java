package main.java.patterns.creational.builder;

import main.java.model.Booking;
import main.java.model.Vehicle;

public class BookingBuilder {
    private String bookingId;
    private String customerName;
    private Vehicle vehicle;
    private int rentalDays;
    private int rentalHours;

    public BookingBuilder setBookingId(String bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public BookingBuilder setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public BookingBuilder setVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        return this;
    }

    public BookingBuilder setRentalDays(int rentalDays){
        this.rentalDays = rentalDays;
        return this;
    }

    public BookingBuilder setRentalHours(int rentalHours) {
        this.rentalHours = rentalHours;
        return this;
    }
    public Booking build(){
        return new Booking(bookingId, customerName,vehicle,rentalDays,rentalHours);
    }
}
