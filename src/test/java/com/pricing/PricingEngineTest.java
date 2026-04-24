package com.pricing;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PricingEngineTest {

    @Test
    public void testRegularCustomerNoDiscount() {
        PricingEngine engine = new PricingEngine();
        List<Double> prices = Arrays.asList(10.0, 20.0);
        List<Integer> quantities = Arrays.asList(1, 1);

        engine.calc(prices, quantities, "REGULAR", null);

        assertEquals(30.0, engine.s, "Subtotal should be 30");
        assertEquals(0.0, engine.d, "Discount should be 0");
        assertEquals(6.0, engine.t, "Tax should be 6 (20% of 30)");
        assertEquals(36.0, engine.f, "Final price should be 36");
    }

    @Test
    public void testVIPCustomerWithPromo() {
        PricingEngine engine = new PricingEngine();
        List<Double> prices = Arrays.asList(100.0);
        List<Integer> quantities = Arrays.asList(1);

        engine.calc(prices, quantities, "VIP", "SAVE10");

        // Subtotal: 100
        // VIP Discount: 15% of 100 = 15
        // Promo SAVE10: +10
        // Total Discount: 25
        // Tax: (100 - 25) * 0.2 = 15
        // Final: 100 - 25 + 15 = 90

        assertEquals(100.0, engine.s);
        assertEquals(25.0, engine.d);
        assertEquals(15.0, engine.t);
        assertEquals(90.0, engine.f);
    }
}
