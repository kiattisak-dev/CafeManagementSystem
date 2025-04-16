package cafe.model;

// Interface for calculating discounts
public interface Discountable {
    // Calculate discount based on the price
    double calculateDiscount(double price);
}