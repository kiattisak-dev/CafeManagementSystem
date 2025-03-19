package main.java.com.cafe.model;

import java.util.ArrayList;
import java.util.List;

public class Cafe {
    private List<MenuItem> menu;

    public Cafe() {
        this.menu = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public MenuItem getMenuItem(String name, int quantity) throws Exception {
        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(name)) {
                if (item.getStock() < quantity) {
                    throw new Exception("❌ Not enough stock for " + name + ". Available: " + item.getStock());
                }
                return item;
            }
        }
        throw new Exception("❌ Menu item not found: " + name);
    }

    public void updateStock(String name, int quantity) {
        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(name)) {
                int newStock = Math.max(0, item.getStock() - quantity);
                item.setStock(newStock);
                return;
            }
        }
    }

    public void displayStock() {
        System.out.println("\n📦 Coffee Stock:");
        for (MenuItem item : menu) {
            System.out.println("✅ " + item.getName() + " - " + item.getStock() + " left");
        }
    }

    public void displayMenu() {
        System.out.println("\n🍵 Menu:");
        for (MenuItem item : menu) {
            System.out.println("✅ " + item.getName() + " - " + item.getPrice() + "฿ (" + item.getStock() + " left)");
        }
    }
}
