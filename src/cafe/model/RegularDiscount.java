package cafe.model;

// Class for calculating regular customer discounts
public class RegularDiscount implements Discountable {
    private double discountRate = 0.05; // Discount rate set to 5%

    // Calculate discount based on price
    @Override
    public double calculateDiscount(double price) {
        return price * discountRate; // Apply 5% discount
    }
}
