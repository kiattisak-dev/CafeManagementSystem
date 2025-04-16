package main.java.com.cafe.model;

// The Customer class serves as a base class for different types of customers.
// Using Inheritance here allows code reusability and extensibility, enabling subclasses
// like MemberCustomer to inherit common attributes (e.g., name) and behaviors (e.g., getName)
// while adding specific features like discounts for members.
public class Customer {
    protected String name;

    public Customer(String name) {
        this.name = name;
    }

    // Provides a default discount rate, which can be overridden by subclasses.
    // This method supports polymorphism, allowing different customer types to define their own discount logic.
    public double getDiscountRate() {
        return 0.0; // Regular customers receive no discount
    }

    public String getName() {
        return name;
    }
}