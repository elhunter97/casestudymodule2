package services;

import model.Medicine;

import java.util.List;

public interface IMedicine {
    boolean add(Medicine medicine);
    boolean edit(Medicine medicine);
    Medicine findByName(String medicineName);

    boolean deleteByName(String medName);

    List<Medicine> sortByPriceAndName();

    List<Medicine> searchByName(String medName);

    List<Medicine> getAllMedicines();
}
