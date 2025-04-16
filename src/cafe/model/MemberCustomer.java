package cafe.model;

// MemberCustomer extends Customer to represent a member with additional benefits.
// Inheritance enables MemberCustomer to reuse the name attribute and getName method
// from Customer while introducing member-specific features like a member ID and discount rate.
// This design promotes scalability, as new customer types can be added with minimal code changes.
public class MemberCustomer extends Customer {
    private String memberId;

    public MemberCustomer(String name, String memberId) {
        super(name);
        this.memberId = memberId;
    }

    // Overrides the base class method to provide a member-specific discount.
    // Polymorphism allows the system to call this method dynamically based on the actual customer type,
    // ensuring flexibility in handling different discount policies.
    @Override
    public double getDiscountRate() {
        return 0.1; // 10% discount for members
    }

    public String getMemberId() {
        return memberId;
    }
}