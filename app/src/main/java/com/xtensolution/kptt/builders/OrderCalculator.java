package com.xtensolution.kptt.builders;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Test on 11/17/2017.
 */

public class OrderCalculator {
    private static final String KEY_QTY = "quantity";
    private static final String KEY_RATE = "rate";
    private int discount;
    private int gst;
    private double grossTotal;
    private double subTotal;

    private ArrayList<HashMap<String, Object>> itemMap;

    public OrderCalculator() {
        itemMap = new ArrayList<>();
    }

    public static class Builder {
        private int quantity;
        private double rate;
        private OrderCalculator orderCalculator;

        public Builder() {
            orderCalculator = new OrderCalculator();
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder rate(double rate) {
            this.rate = rate;
            return this;
        }

        public OrderCalculator build() {
            if (quantity > 0 && rate > 0) {
                HashMap<String, Object> map = new HashMap<>();
                map.put(KEY_QTY, quantity);
                map.put(KEY_RATE, rate);
                orderCalculator.setItem(map);
            }
            return orderCalculator;
        }
    }

    public void setItem(HashMap<String, Object> map) {
        itemMap.add(map);
    }

    public double getGrossTotal() {
        grossTotal = 0;
        for (HashMap<String, Object> map : itemMap) {
            int quantity = (int) map.get(KEY_QTY);
            double rate = (Double) map.get(KEY_RATE);
            grossTotal += quantity * rate;
        }
        return grossTotal;
    }

    public double getDiscount(int discount) {
        this.discount = discount;
        return (grossTotal * discount) / 100;
    }

    public double getSubTotal() {
        subTotal = grossTotal - getDiscount(discount);
        return subTotal;
    }

    public double getGst(int gst) {
        this.gst = gst;
        if (subTotal > 0)
            return (subTotal * gst) / 100;
        else
            return (grossTotal * gst) / 100;
    }

    public double getNetTotal() {
        if (gst > 0)
            return subTotal + ((subTotal * gst) / 100);
        else
            return subTotal;
    }
}
