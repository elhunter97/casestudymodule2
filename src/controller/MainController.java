package controller;

import view.BillView;
import view.MedicineView;

import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {
        MedicineView medicineView = new MedicineView();
        BillView billView = new BillView();
        Scanner sc = new Scanner(System.in);
        int choice;
        String name;
        while(true){
            choice = medicineView.view();
            switch(choice){
                case 1:
                    medicineView.handleAddMedicine();
                    break;
                case 2:
                    medicineView.handleEditMedicine();
                    break;
                case 3:
                    medicineView.handleDeleteMedicine();
                    break;
                case 4:
                    medicineView.sortMedicines();
                    break;
                case 5:
                    medicineView.searchMedicine();
                    break;
                case 6:
                    medicineView.handleDisplayAllMedicines();
                    break;
                case 7:
                    billView.handleCreateBill();
                    break;
                case 0:
                    System.out.println("Ending Program");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.Please try again");
            }
        }
    }
}
