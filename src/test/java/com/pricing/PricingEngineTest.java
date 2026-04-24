package com.pricing;

import com.pricing.model.OrderItem;
import com.pricing.model.PricingResult;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PricingEngineTest {

    @Test
    public void testRegularCustomerNoDiscount() {
        PricingEngine engine = new PricingEngine();
        List<OrderItem> items = Arrays.asList(
                new OrderItem(10.0, 1),
                new OrderItem(20.0, 1));

        PricingResult result = engine.calculate(items, "REGULAR", null);

        assertEquals(30.0, result.getSubtotal(), "Subtotal should be 30");
        assertEquals(0.0, result.getDiscountAmount(), "Discount should be 0");
        assertEquals(6.0, result.getTaxAmount(), "Tax should be 6 (20% of 30)");
        assertEquals(36.0, result.getFinalPrice(), "Final price should be 36");
    }

    @Test
    public void testVIPCustomerWithPromo() {
        PricingEngine engine = new PricingEngine();
        List<OrderItem> items = Arrays.asList(new OrderItem(100.0, 1));

        PricingResult result = engine.calculate(items, "VIP", "SAVE10");

        assertEquals(100.0, result.getSubtotal());
        assertEquals(25.0, result.getDiscountAmount());
        assertEquals(15.0, result.getTaxAmount());
        assertEquals(90.0, result.getFinalPrice());
    }
}
