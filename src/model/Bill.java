package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bill {
    private String customerId;
    private String customerName;
    private Map<String, Integer> products; // Medicine name and quantity
    private int totalPrice;

    public Bill(String customerId, String customerName, Map<String, Integer> products, int totalPrice) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(customerId).append(",").append(customerName).append(",").append(totalPrice).append(",");
        products.forEach((name, quantity) -> sb.append(name).append(":").append(quantity).append(";"));
        return sb.toString();
    }
}
