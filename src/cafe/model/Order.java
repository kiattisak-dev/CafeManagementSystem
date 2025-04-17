package cafe.model;

import java.util.HashMap;
import java.util.Map;

// Class for managing customer orders
public class Order {
    private Map<MenuItem, Integer> items = new HashMap<>(); // Map of items and quantities
    private Customer customer; // Customer placing the order
    private double totalAmount; // Total order amount

    // Set the customer for this order
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Get the customer
    public Customer getCustomer() {
        return customer;
    }

    // Get the total order amount
    public double getTotalAmount() {
        return totalAmount;
    }

    // Add an item with quantity to the order
    public void addItem(MenuItem item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity); // Update quantity if item exists
    }

    // Calculate total order amount with discount
    public void calculateTotal() {
        totalAmount = 0.0;
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            totalAmount += entry.getKey().getPrice() * entry.getValue(); // Sum price * quantity
        }

        if (customer != null) {
            double discount = totalAmount * customer.getDiscountRate(); // Apply discount
            totalAmount -= discount;
        }
    }

    // Print the order receipt
    public void printReceipt() {
        System.out.println("👤 Customer: " + (customer != null ? customer.getName() : "N/A"));
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            MenuItem item = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("- " + quantity + "x " + item.getName() + " @ " + item.getPrice() + " = "
                    + (item.getPrice() * quantity));
        }

        if (customer != null && customer.getDiscountRate() > 0 && customer.getDiscountRate() < 1) {
            double originalAmount = totalAmount / (1 - customer.getDiscountRate()); // Calculate original amount
            double discount = originalAmount * customer.getDiscountRate(); // Calculate discount
            System.out.printf(" Member Discount: -%.2f\n", discount);
        }

        System.out.printf(" Total: %.2f\n", totalAmount);
    }

    // Update cafe stock after order (if committed)
    public void updateStock(Cafe cafe, boolean commit) {
        if (commit) {
            for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
                cafe.updateStock(entry.getKey().getName(), entry.getValue()); // Reduce stock for each item
            }
        }
    }

    // Convert order to string for display
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order for ").append(customer != null ? customer.getName() : "N/A").append(":\n");
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            MenuItem item = entry.getKey();
            int quantity = entry.getValue();
            sb.append("  - ").append(quantity).append("x ").append(item.getName())
                    .append(" @ ").append(item.getPrice()).append(" = ")
                    .append(item.getPrice() * quantity).append("\n");
        }
        if (customer != null && customer.getDiscountRate() > 0 && customer.getDiscountRate() < 1) {
            double originalAmount = totalAmount / (1 - customer.getDiscountRate());
            double discount = originalAmount * customer.getDiscountRate();
            sb.append(String.format("  Member Discount: -%.2f\n", discount));
        }
        sb.append(String.format("  Total: %.2f", totalAmount));
        return sb.toString();
    }
}