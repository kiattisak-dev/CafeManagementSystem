package cafe.model;

public class MenuItem {
    private String name;
    private double price;
    private int stock;

    public MenuItem(String name, double price,int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public int getStock(){
        return stock;
    }
    public void reduceStock(int quantity) {
        this.stock -= quantity;
    }
    public void setStock(int stock) { 
        this.stock = stock;
    }
}
