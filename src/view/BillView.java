package view;

import model.Bill;
import services.impl.BillService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BillView {
    private BillService billService;

    public BillView() {
        this.billService = billService;
    }

    public void handleCreateBill() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer ID: ");
        String customerId = sc.nextLine();

        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();

        Map<String, Integer> products = new HashMap<>();
        String productName;
        int quantity;
        while (true) {
            System.out.print("Enter product name (or type 'done' to finish): ");
            productName = sc.nextLine();
            if (productName.equalsIgnoreCase("done")) break;
            System.out.print("Enter quantity: ");
            quantity = Integer.parseInt(sc.nextLine());
            products.put(productName, quantity);
        }

        int totalPrice = billService.calculateTotalPrice(products);
        Bill bill = new Bill(customerId, customerName, products, totalPrice);

        boolean result = billService.createBill(bill);
        if (result) {
            System.out.println("Bill created successfully.");
        } else {
            System.out.println("Failed to create bill.");
        }
    }


}
