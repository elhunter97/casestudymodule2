package repositories;

import model.Bill;
import model.Medicine;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillRepositories {
    private static final String fileBill = "src/data/sell.csv";
    private MedicineRepositories medicineRepositories = new MedicineRepositories();
    public void addBill(Bill bill) {
        List<Bill> bills = loadBills();
        List<Medicine> medicines = medicineRepositories.loadMedicines();
        for (Map.Entry<String, Integer> entry : bill.getProducts().entrySet()) {
            for (Medicine medicine : medicines) {
                if (medicine.getMedName().equalsIgnoreCase(entry.getKey())) {
                    if (medicine.getQuantity() >= entry.getValue()) {
                        medicine.setQuantity(medicine.getQuantity() - entry.getValue());
                    } else {
                        System.out.println("Not enough stock for medicine: " + entry.getKey());
                        return;
                    }
                }
            }
        }
        bills.add(bill);
        writeBills(bills, true);
    }

    private void writeBills(List<Bill> bills, boolean append) {
        File file = new File(fileBill);
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file, append);
            bw = new BufferedWriter(fw);
            for (Bill bill : bills) {
                bw.write(bill.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    System.out.println("Cannot close file");
                }
            }
        }
    }

    public List<Bill> loadBills() {
        List<Bill> bills = new ArrayList<>();
        File file = new File(fileBill);
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(",");
                String customerId = temp[0];
                String customerName = temp[1];
                int totalPrice = Integer.parseInt(temp[2]);
                Map<String, Integer> products = new HashMap<>();
                String[] productsArray = temp[3].split(";");
                for (String product : productsArray) {
                    String[] productDetails = product.split(":");
                    products.put(productDetails[0], Integer.parseInt(productDetails[1]));
                }
                bills.add(new Bill(customerId, customerName, products, totalPrice));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        } catch (IOException e) {
            System.out.println("Couldn't read file");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Cannot close file");
                }
            }
        }
        return bills;
    }
}
