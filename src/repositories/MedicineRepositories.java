package repositories;

import model.Medicine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineRepositories {
    private static final String fileMedicine = "src/data/medicine.csv";
    private static List<Medicine>  medicines = new ArrayList<>();

    public List<Medicine> loadMedicines(){
        List<Medicine> medicines = new ArrayList<>();
        File file = new File(fileMedicine);
        FileReader fr = null;
        BufferedReader br = null;
        try{
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                String[] temp = line.split(",");
//               //String medName, String medType, String inputDate, String expDate, int price, int quantity
                medicines.add(new Medicine(temp[0],temp[1],temp[2],temp[3],temp[4],Integer.parseInt(temp[5]),Integer.parseInt(temp[6])));
            }
        }catch(FileNotFoundException e) {
            System.out.println("Couldn't find file");
        } catch (IOException e) {
            System.out.println("Couldn't read file");
        }finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Cannot close file");
                }
            }
        }
        return medicines;
    }

    public void writeMedicines(List<Medicine> medicines, boolean append) {
        File file = new File(fileMedicine);
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file, append);
            bw = new BufferedWriter(fw);
            for(Medicine temp : medicines) {
                bw.write(toString(temp));
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

//    private String medicineToCSV(Medicine medicine) {
//        return String.join(",", medicine.getMedID(), medicine.getMedName(), medicine.getMedType(), medicine.getInputDate(), medicine.getExpDate(), String.valueOf(medicine.getPrice()), String.valueOf(medicine.getQuantity()));
//    }


    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
        writeMedicines(medicines, true);
    }

    public boolean editMedicine(Medicine updatedMedicine) {
        List<Medicine> medicines = loadMedicines();
        for (int i = 0; i < medicines.size(); i++) {
            if (medicines.get(i).getMedID().equals(updatedMedicine.getMedID())) {
                medicines.set(i, updatedMedicine);
                writeMedicines(medicines, false);
                return true;
            }
        }
        return false;
    }

    public Medicine findByName(String name) {
        List<Medicine> medicines = loadMedicines();
        for (Medicine medicine : medicines) {
            if (medicine.getMedName().equalsIgnoreCase(name)) {
                return medicine;
            }
        }
        return null;
    }

    public String toString(Medicine medicine) {
        return medicine.getMedID()+","+medicine.getMedName()+","+medicine.getMedType()+","+medicine.getInputDate()+","+medicine.getExpDate()+","+medicine.getPrice()+","+medicine.getQuantity();
    }

    public boolean delete(String medName) {
        List<Medicine> medicines = loadMedicines();
        for (Medicine medicine : medicines) {
            if (medicine.getMedName().equalsIgnoreCase(medName)) {
                medicines.remove(medicine);
                writeMedicines(medicines, false);
                return true;
            }
        }
        return false;
    }
}
