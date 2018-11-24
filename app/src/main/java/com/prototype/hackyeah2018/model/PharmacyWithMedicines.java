package com.prototype.hackyeah2018.model;

import java.util.List;
import java.util.Objects;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

public class PharmacyWithMedicines {

    @Embedded
    private Pharmacy pharmacy;

    @Relation(entity = Medicine.class, parentColumn = "id", entityColumn = "pharmacyId")
    private List<Medicine> medicines;

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PharmacyWithMedicines that = (PharmacyWithMedicines) o;
        return Objects.equals(pharmacy, that.pharmacy) &&
                Objects.equals(medicines, that.medicines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pharmacy, medicines);
    }
}
