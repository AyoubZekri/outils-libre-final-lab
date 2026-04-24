package com.pricing;

import java.util.List;

/**
 * BAD DESIGN STARTER CODE
 * This class has multiple issues: 
 * - Poor naming
 * - Single massive method (God Method)
 * - Hardcoded magic numbers (tax rates, discount percentages)
 * - Public fields for results (violates encapsulation)
 * - Mixed concerns (calculation + console output)
 * - No error handling
 */
public class PricingEngine {
    public double s; // subtotal
    public double d; // discount
    public double t; // tax
    public double f; // final price

    public void calc(List<Double> p, List<Integer> q, String typ, String cod) {
        s = 0;
        for (int i = 0; i < p.size(); i++) {
            s += p.get(i) * q.get(i);
        }
        
        // VIP discount
        if (typ.equals("VIP")) {
            d = s * 0.15;
        } else if (typ.equals("REGULAR")) {
            if (s > 100) {
                d = s * 0.05;
            } else {
                d = 0;
            }
        }
        
        // Promo codes
        if (cod != null) {
            if (cod.equals("SAVE10")) {
                d += 10;
            } else if (cod.equals("SAVE20")) {
                d += 20;
            }
        }
        
        t = (s - d) * 0.2;
        f = s - d + t;
        
        System.out.println("Subtotal: " + s);
        System.out.println("Discount: " + d);
        System.out.println("Tax: " + t);
        System.out.println("Final: " + f);
    }
}
