package cafe.model;

// Class for a menu item
public class MenuItem {
    private String name; // Item name
    private double price; // Item price
    private int stock; // Available stock

    // Constructor: Initialize menu item
    public MenuItem(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Get item name
    public String getName() {
        return name;
    }

    // Get item price
    public double getPrice() {
        return price;
    }

    // Get current stock
    public int getStock() {
        return stock;
    }

    // Reduce stock by given quantity
    public void reduceStock(int quantity) {
        this.stock -= quantity;
    }

    // Set new stock value
    public void setStock(int stock) {
        this.stock = stock;
    }
}