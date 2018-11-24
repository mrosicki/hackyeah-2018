package com.prototype.hackyeah2018.service;

import java.util.List;

import com.prototype.hackyeah2018.model.Pharmacy;
import com.prototype.hackyeah2018.model.PharmacyWithMedicines;

public interface IPharmacyService {

    PharmacyWithMedicines getPharmacyWithMedicines(Long pharmacyId);

    Pharmacy getPharmacy(Long pharmacyId);

    void insertPharmacy(Pharmacy pharmacy);

    void deletePharmacy(Pharmacy pharmacy);

    List<Pharmacy> getPharmacies();

    List<PharmacyWithMedicines> getPharmacyWithMedicines();
}
