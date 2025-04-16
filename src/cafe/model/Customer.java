package cafe.model;

public class Customer {
    protected String name; // Customer's name

    // Constructor: Initialize customer with a name
    public Customer(String name) {
        this.name = name;
    }

    // Get discount rate (default is 0 for regular customers)
    public double getDiscountRate() {
        return 0.0;
    }

    // Get the customer's name
    public String getName() {
        return name;
    }
}