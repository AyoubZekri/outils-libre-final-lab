package com.pricing;

import com.pricing.model.OrderItem;
import com.pricing.model.PricingResult;
import java.util.List;

public class PricingEngine {
    private static final double TAX_RATE = 0.20;
    private static final double VIP_DISCOUNT_RATE = 0.15;
    private static final double REGULAR_DISCOUNT_RATE = 0.05;
    private static final double REGULAR_DISCOUNT_THRESHOLD = 100.0;

    public PricingResult calculate(List<OrderItem> items, String customerType, String promoCode) {
        double subtotal = calculateSubtotal(items);
        double discount = calculateBaseDiscount(subtotal, customerType);
        discount += applyPromoCode(promoCode);

        double taxableAmount = subtotal - discount;
        double tax = taxableAmount * TAX_RATE;
        double finalPrice = taxableAmount + tax;

        return new PricingResult(subtotal, discount, tax, finalPrice);
    }

    private double calculateSubtotal(List<OrderItem> items) {
        return items.stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();
    }

    private double calculateBaseDiscount(double subtotal, String customerType) {
        if ("VIP".equalsIgnoreCase(customerType)) {
            return subtotal * VIP_DISCOUNT_RATE;
        } else if ("REGULAR".equalsIgnoreCase(customerType) && subtotal > REGULAR_DISCOUNT_THRESHOLD) {
            return subtotal * REGULAR_DISCOUNT_RATE;
        }
        return 0;
    }

    private double applyPromoCode(String promoCode) {
        if (promoCode == null)
            return 0;

        return switch (promoCode.toUpperCase()) {
            case "SAVE10" -> 10.0;
            case "SAVE20" -> 20.0;
            default -> 0;
        };
    }
}
