package main.java.com.cafe.model;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int orderCounter = 1;
    private int orderId;
    private Map<MenuItem, Integer> items;
    private double totalAmount;

    public Order() {
        this.orderId = orderCounter++;
        this.items = new HashMap<>();
    }

    public void addItem(MenuItem item, int quantity) {
        items.put(item, quantity);
    }

    public void calculateTotal() {
        totalAmount = 0;
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            totalAmount += entry.getKey().getPrice() * entry.getValue();
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void updateStock(Cafe cafe, boolean paymentSuccess) {
        if (paymentSuccess) {
            for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
                cafe.updateStock(entry.getKey().getName(), entry.getValue());
            }
        }
    }

    public void printReceipt() {
        System.out.println("\n Receipt - Order #" + orderId);
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            System.out.println("✅ " + entry.getValue() + "x " + entry.getKey().getName() + " - "
                    + entry.getKey().getPrice() + "฿ each");
        }
        System.out.println("Total: " + totalAmount + "฿");
    }
}
