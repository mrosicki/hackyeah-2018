package com.prototype.hackyeah2018.service;

import java.util.List;

import com.prototype.hackyeah2018.model.Medicine;

public interface IMedicineService {

    Medicine getMedicine(Long medicineId);

    void insertMedicine(Medicine medicine);

    void deleteMedicine(Medicine medicine);

    List<Medicine> getMedicines();
}
