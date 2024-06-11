package services.impl;

import model.Bill;
import model.Medicine;
import repositories.BillRepositories;
import services.IBill;

import java.util.List;
import java.util.Map;

public class BillService implements IBill {
    private MedicineService medicineService;
    private BillRepositories billRepositories;

    public BillService(MedicineService medicineService, BillRepositories billRepositories) {
        this.medicineService = medicineService;
        this.billRepositories = billRepositories;
    }

    @Override
    public boolean createBill(Bill bill) {
        billRepositories.addBill(bill);
        return true;
    }

    @Override
    public int calculateTotalPrice(Map<String, Integer> products) {
        int totalPrice = 0;
        List<Medicine> medicines = medicineService.getAllMedicines();
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            for (Medicine medicine : medicines) {
                if (medicine.getMedName().equalsIgnoreCase(entry.getKey())) {
                    totalPrice += medicine.getPrice() * entry.getValue();
                }
            }
        }
        return totalPrice;
    }
}
