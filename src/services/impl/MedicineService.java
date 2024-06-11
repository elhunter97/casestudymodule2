package services.impl;

import model.Medicine;
import repositories.MedicineRepositories;
import services.IMedicine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MedicineService implements IMedicine {
    private MedicineRepositories medicineRepositories;
    public MedicineService() {
        this.medicineRepositories = new MedicineRepositories();
    }

    @Override
    public boolean add(Medicine medicine) {
        medicineRepositories.addMedicine(medicine);
        return true;
    }

    @Override
    public boolean edit(Medicine medicine) {
        return medicineRepositories.editMedicine(medicine);
    }

    @Override
    public Medicine findByName(String medicineName) {
        return medicineRepositories.findByName(medicineName);
    }

    @Override
    public boolean deleteByName(String medName) {
        return medicineRepositories.delete(medName);
    }

    @Override
    public List<Medicine> sortByPriceAndName() {
        List<Medicine> medicines = medicineRepositories.loadMedicines();
        Collections.sort(medicines,new Comparator<Medicine>() {
            @Override
            public int compare(Medicine o1, Medicine o2) {
                if(o1.getPrice() > o2.getPrice()) {
                    return 1;
                }
                else if(o1.getPrice() < o2.getPrice()) {
                    return -1;
                }
                else return o1.getMedName().compareTo(o2.getMedName());
            }
        });
        return medicines;
    }

    @Override
    public List<Medicine> searchByName(String medName) {
        List<Medicine> medicines = medicineRepositories.loadMedicines();
        Collections.sort(medicines,new Comparator<Medicine>() {

            @Override
            public int compare(Medicine o1, Medicine o2) {
                return o1.getMedName().compareTo(o2.getMedName());
            }
        });
        List<Medicine> res = new ArrayList<>();
        for(Medicine m : medicines) {
            if(m.getMedName().equals(medName)) {
                res.add(m);
            }
        }
        return res;
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepositories.loadMedicines();
    }
}
