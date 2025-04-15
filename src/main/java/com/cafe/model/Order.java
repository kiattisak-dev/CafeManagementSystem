package com.cafe.model;
import java.util.HashMap;

private Customer customer;

public void setCustomer(Customer customer) {
    this.customer = customer;
}

public Customer getCustomer() {
    return customer;
}

public void calculateTotal() {
    totalAmount = 0.0;
    for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
        totalAmount += entry.getKey().getPrice() * entry.getValue();
    }

    if (customer != null) {
        double discount = totalAmount * customer.getDiscountRate();
        totalAmount -= discount;
    }
}

public void printReceipt() {
    System.out.println("👤 Customer: " + (customer != null ? customer.getName() : "N/A"));
    for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
        MenuItem item = entry.getKey();
        int quantity = entry.getValue();
        System.out.println("- " + quantity + "x " + item.getName() + " @ " + item.getPrice() + " = " + (item.getPrice() * quantity));
    }

    if (customer != null && customer.getDiscountRate() > 0) {
        double discount = totalAmount / (1 - customer.getDiscountRate()) * customer.getDiscountRate();
        System.out.printf("💸 Member Discount: -%.2f\n", discount);
    }

    System.out.printf("💰 Total: %.2f\n", totalAmount);
}
