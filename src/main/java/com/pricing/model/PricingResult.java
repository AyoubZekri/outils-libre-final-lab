package com.pricing.model;

public class PricingResult {
    private final double subtotal;
    private final double discountAmount;
    private final double taxAmount;
    private final double finalPrice;

    public PricingResult(double subtotal, double discountAmount, double taxAmount, double finalPrice) {
        this.subtotal = subtotal;
        this.discountAmount = discountAmount;
        this.taxAmount = taxAmount;
        this.finalPrice = finalPrice;
    }

    public double getSubtotal() { return subtotal; }
    public double getDiscountAmount() { return discountAmount; }
    public double getTaxAmount() { return taxAmount; }
    public double getFinalPrice() { return finalPrice; }

    @Override
    public String toString() {
        return String.format("Subtotal: %.2f\nDiscount: %.2f\nTax: %.2f\nFinal: %.2f", 
            subtotal, discountAmount, taxAmount, finalPrice);
    }
}
