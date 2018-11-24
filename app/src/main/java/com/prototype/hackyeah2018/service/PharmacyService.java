package com.prototype.hackyeah2018.service;

import java.util.List;

import com.prototype.hackyeah2018.dao.PharmacyDao;
import com.prototype.hackyeah2018.dao.PharmacyWithMedicinesDao;
import com.prototype.hackyeah2018.model.Pharmacy;
import com.prototype.hackyeah2018.model.PharmacyWithMedicines;

public class PharmacyService implements IPharmacyService {

    private final PharmacyDao pharmacyDao;

    private final PharmacyWithMedicinesDao pharmacyWithMedicinesDao;

    public PharmacyService(PharmacyDao pharmacyDao, PharmacyWithMedicinesDao pharmacyWithMedicinesDao) {
        this.pharmacyDao = pharmacyDao;
        this.pharmacyWithMedicinesDao = pharmacyWithMedicinesDao;
    }

    @Override
    public List<Pharmacy> getPharmacies() {
        return pharmacyDao.findAll();
    }

    @Override
    public List<PharmacyWithMedicines> getPharmacyWithMedicines() {
        return pharmacyWithMedicinesDao.findAll();
    }

    @Override
    public PharmacyWithMedicines getPharmacyWithMedicines(Long pharmacyId) {
        return pharmacyWithMedicinesDao.findById(pharmacyId);
    }

    @Override
    public Pharmacy getPharmacy(Long pharmacyId) {
        return pharmacyDao.findById(pharmacyId);
    }

    @Override
    public void insertPharmacy(Pharmacy pharmacy) {
        pharmacyDao.insert(pharmacy);
    }

    @Override
    public void deletePharmacy(Pharmacy pharmacy) {
        pharmacyDao.delete(pharmacy);
    }
}
