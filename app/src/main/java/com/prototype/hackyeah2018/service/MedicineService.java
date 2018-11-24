package com.prototype.hackyeah2018.service;

import java.util.List;

import com.prototype.hackyeah2018.dao.MedicineDao;
import com.prototype.hackyeah2018.model.Medicine;

public class MedicineService implements IMedicineService {

    private final MedicineDao medicineDao;

    public MedicineService(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }

    @Override
    public List<Medicine> getMedicines() {
        return medicineDao.findAll();
    }

    @Override
    public Medicine getMedicine(Long medicineId) {
        return medicineDao.findById(medicineId);
    }

    @Override
    public void insertMedicine(Medicine medicine) {
        this.medicineDao.insert(medicine);
    }

    @Override
    public void deleteMedicine(Medicine medicine) {
        this.medicineDao.delete(medicine);
    }
}
