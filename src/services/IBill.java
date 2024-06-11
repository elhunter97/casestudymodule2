package services;

import model.Bill;

import java.util.Map;

public interface IBill {
    boolean createBill(Bill bill);

    int calculateTotalPrice(Map<String, Integer> products);
}
