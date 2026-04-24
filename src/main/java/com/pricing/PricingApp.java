package com.pricing;

import com.pricing.model.OrderItem;
import com.pricing.model.PricingResult;
import java.util.ArrayList;
import java.util.List;

public class PricingApp {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: java PricingApp <customerType> <promoCode> <price1:qty1> <price2:qty2> ...");
            System.exit(1);
        }

        String customerType = args[0];
        String promoCode = args[1].equalsIgnoreCase("none") ? null : args[1];
        List<OrderItem> items = new ArrayList<>();

        for (int i = 2; i < args.length; i++) {
            String[] parts = args[i].split(":");
            items.add(new OrderItem(Double.parseDouble(parts[0]), Integer.parseInt(parts[1])));
        }

        PricingEngine engine = new PricingEngine();
        PricingResult result = engine.calculate(items, customerType, promoCode);

        // Print results in a machine-readable format (JSON-like or key-value)
        System.out.println("subtotal=" + result.getSubtotal());
        System.out.println("discount=" + result.getDiscountAmount());
        System.out.println("tax=" + result.getTaxAmount());
        System.out.println("total=" + result.getFinalPrice());
    }
}
