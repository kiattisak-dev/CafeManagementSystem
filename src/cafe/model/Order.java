package cafe.model;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<MenuItem, Integer> items = new HashMap<>();
    private Customer customer;
    private double totalAmount;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void addItem(MenuItem item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
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
            System.out.println("- " + quantity + "x " + item.getName() + " @ " + item.getPrice() + " = "
                    + (item.getPrice() * quantity));
        }

        if (customer != null && customer.getDiscountRate() > 0 && customer.getDiscountRate() < 1) {
            double originalAmount = totalAmount / (1 - customer.getDiscountRate());
            double discount = originalAmount * customer.getDiscountRate();
            System.out.printf("💸 Member Discount: -%.2f\n", discount);
        }

        System.out.printf("💰 Total: %.2f\n", totalAmount);
    }

    public void updateStock(Cafe cafe, boolean commit) {
        if (commit) {
            for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
                cafe.updateStock(entry.getKey().getName(), entry.getValue());
            }
        }
    }
}