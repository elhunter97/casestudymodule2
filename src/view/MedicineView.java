package view;

import model.Medicine;
import services.impl.MedicineService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MedicineView {
    private MedicineService medicineService;
    private DateTimeFormatter dateFormatter;
    private static final int MAX_FAIL = 5;

    public MedicineView() {
        this.medicineService = new MedicineService();
        this.dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }


    public void displayAllMedicines(List<Medicine> medicines) {
        System.out.println("List of all medicines:");
        for (Medicine medicine : medicines) {
            System.out.println(medicine);
        }
    }

    public int view(){
        System.out.println("-----------------------");
        System.out.println("1. Add Medicine");
        System.out.println("2. Edit Medicine");
        System.out.println("3. Delete Medicine");
        System.out.println("4. Sort Medicines");
        System.out.println("5. Search Medicines");
        System.out.println("6. Display All Medicines");
        System.out.println("7. Create Bill");
        System.out.println("0. End Program");
        System.out.println("Your choice: ");
        Scanner sc = new Scanner(System.in);
        int choice = Integer.parseInt(sc.nextLine());
        return choice;
    }

    public void handleAddMedicine() {
        Medicine newMedicine = viewAddMedicine();
        if (newMedicine != null) {
            boolean result = medicineService.add(newMedicine);
            viewMessage(result);
        } else {
            System.out.println("Failed to add medicine. Please try again.");
        }
    }

    public Medicine viewAddMedicine() {
        Scanner sc = new Scanner(System.in);
        int cnt = 0;
        System.out.print("Enter medicine ID: ");
        String medicineID = sc.nextLine();
        String name=null;
        while (cnt < MAX_FAIL) {
            System.out.print("Enter medicine name: ");
            name = sc.nextLine();
            if (!name.trim().isEmpty()) break;
            System.out.println("Medicine name cannot be empty. Please enter a valid name.");
            cnt++;
        }
        endProgramCauseMaxFail(cnt,MAX_FAIL);
        cnt=0;
        String type=null;
        while(cnt < MAX_FAIL) {
            System.out.print("Enter medicine type: ");
            type = sc.nextLine();
            if (!type.trim().isEmpty()) break;
            System.out.println("Medicine type cannot be empty. Please enter a valid medicine type.");
            ++cnt;
        }
        endProgramCauseMaxFail(cnt,MAX_FAIL);
        cnt=0;
        String inputDate = null;
        while (cnt < MAX_FAIL) {
            System.out.print("Enter input date(dd/MM/yyyy): ");
            inputDate = sc.nextLine();
            if (isValidDate(inputDate)) break;
            System.out.println("Invalid date format. Please enter a valid date.");
            ++cnt;
        }
        endProgramCauseMaxFail(cnt,MAX_FAIL);
        cnt=0;
        String expDate = null;
        while (cnt < MAX_FAIL) {
            System.out.print("Enter expiry date(dd/MM/yyyy): ");
            expDate = sc.nextLine();
            if (isValidDate(expDate)) break;
            System.out.println("Invalid date format. Please enter a valid date.");
            ++cnt;
        }
        endProgramCauseMaxFail(cnt,MAX_FAIL);
        cnt=0;
        int price=-1;
        while(cnt < MAX_FAIL){
            try{
                System.out.print("Enter medicine price: ");
                price = Integer.parseInt(sc.nextLine());
                if(price>0)break;
                System.out.println("Price cannot be negative. Please enter a valid price.");
            }catch (NumberFormatException e){
                System.out.println("Invalid input. Please enter number.");
            }
            ++cnt;
        }
        endProgramCauseMaxFail(cnt,MAX_FAIL);
        cnt = 0;
        int quantity=-1;
        while (cnt < MAX_FAIL) {
            try{
                System.out.print("Enter quantity: ");
                quantity = Integer.parseInt(sc.nextLine());
                if(quantity>0)break;
                System.out.println("Quantity cannot be negative. Please enter a valid quantity.");
            }catch (NumberFormatException e){
                System.out.println("Invalid input. Please enter number.");
            }
            ++cnt;
        }
        endProgramCauseMaxFail(cnt,MAX_FAIL);
        return new Medicine(medicineID,name, type, inputDate, expDate, price, quantity);
    }


    public void endProgramCauseMaxFail(int a, int b){
        if(a>=b){
            System.out.println("Too many invalid attempts. Program will terminate.");
            System.exit(1);
        }
    }

    public void handleEditMedicine() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the medicine to edit: ");
        String name = sc.nextLine();

        Medicine medicine = medicineService.findByName(name);
        if (medicine == null) {
            System.out.println("Medicine does not exist");
            return;
        }

        int attempts = 0;

        while (attempts < MAX_FAIL) {
            System.out.print("Enter new medicine type (current: " + medicine.getMedType() + "): ");
            String type = sc.nextLine();
            if (!type.trim().isEmpty()) {
                medicine.setMedType(type);
                break;
            }
            System.out.println("Medicine type cannot be empty. Please enter a valid type.");
            attempts++;
        }
        if (attempts >= MAX_FAIL) {
            System.out.println("Too many invalid attempts. Program will terminate.");
            System.exit(1);
        }


        attempts = 0;
        while (attempts < MAX_FAIL) {
            try {
                System.out.print("Enter new price (current: " + medicine.getPrice() + "): ");
                int price = Integer.parseInt(sc.nextLine());
                if (price >= 0) {
                    medicine.setPrice(price);
                    break;
                }
                System.out.println("Price cannot be negative. Please enter a valid price.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            attempts++;
        }
        if (attempts >= MAX_FAIL) {
            System.out.println("Too many invalid attempts. Program will terminate.");
            System.exit(1);
        }

        attempts = 0;
        while (attempts < MAX_FAIL) {
            System.out.print("Enter new input date (current: " + medicine.getInputDate() + ", format: dd/MM/yyyy): ");
            String inputDate = sc.nextLine();
            if (isValidDate(inputDate)) {
                medicine.setInputDate(inputDate);
                break;
            }
            System.out.println("Invalid date format. Please enter a valid date in the format dd/MM/yyyy.");
            attempts++;
        }
        if (attempts >= MAX_FAIL) {
            System.out.println("Too many invalid attempts. Program will terminate.");
            System.exit(1);
        }

        attempts = 0;
        while (attempts < MAX_FAIL) {
            System.out.print("Enter new expiry date (current: " + medicine.getExpDate() + ", format: dd/MM/yyyy): ");
            String expDate = sc.nextLine();
            if (isValidDate(expDate)) {
                medicine.setExpDate(expDate);
                break;
            }
            System.out.println("Invalid date format. Please enter a valid date in the format dd/MM/yyyy.");
            attempts++;
        }
        if (attempts >= MAX_FAIL) {
            System.out.println("Too many invalid attempts. Program will terminate.");
            System.exit(1);
        }

        attempts = 0;
        while (attempts < MAX_FAIL) {
            try {
                System.out.print("Enter new quantity (current: " + medicine.getQuantity() + "): ");
                int quantity = Integer.parseInt(sc.nextLine());
                if (quantity >= 0) {
                    medicine.setQuantity(quantity);
                    break;
                }
                System.out.println("Quantity cannot be negative. Please enter a valid quantity.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            attempts++;
        }
        if (attempts >= MAX_FAIL) {
            System.out.println("Too many invalid attempts. Program will terminate.");
            System.exit(1);
        }

        boolean result = medicineService.edit(medicine);
        viewMessage(result);
    }
    public void handleDeleteMedicine() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the medicine to delete: ");
        String medName = sc.nextLine();

        boolean result = medicineService.deleteByName(medName);
        viewMessage(result);
    }


    public void viewMessage(boolean res){
        if(res){
            System.out.println("Operate successful");
        }else{
            System.out.println("Operate unsuccessful");
        }
    }

    public void sortMedicines(){
        List<Medicine> sortedMedicines = medicineService.sortByPriceAndName();
        displayAllMedicines(sortedMedicines);
    }

    public void searchMedicine() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the medicine to find: ");
        String medName = sc.nextLine();

        List<Medicine> medicines = medicineService.searchByName(medName);
        if (medicines != null) {
            System.out.println("Medicine found:");
            displayAllMedicines(medicines);
        } else {
            System.out.println("Medicine not found.");
        }
    }

    public void handleDisplayAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        displayAllMedicines(medicines);
    }


    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}


