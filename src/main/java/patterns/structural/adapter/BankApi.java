package main.java.patterns.structural.adapter;

public class BankApi {
    public boolean processPayment(double amount) {

        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Bank API: Invalid payment amount.");
            return false;
        }

        System.out.println("Bank API: Connecting to bank...");
        System.out.println("Bank API: Processing payment of " + amount);

        System.out.println("Bank API: Payment successful!");

        return true;
    }
}
