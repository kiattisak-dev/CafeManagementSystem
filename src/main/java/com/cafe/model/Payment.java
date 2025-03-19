package main.java.com.cafe.model;

public class Payment {
    private double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public void processPayment() {
        System.out.println("Processing payment of " + amount + "฿...");
        System.out.println("Payment successful !!!");
    }
}