package main.java.patterns.structural.adapter;
import java.util.Objects;

public class BankPaymentAdapter implements PaymentMethod {
    private final BankApi bankApi;

    public BankPaymentAdapter(BankApi bankApi) {
        this.bankApi = Objects.requireNonNull(bankApi,"BankApi cannot be null");
    }
    @Override
    public boolean pay(double amount) {
        return bankApi.processPayment(amount);
    }
}
