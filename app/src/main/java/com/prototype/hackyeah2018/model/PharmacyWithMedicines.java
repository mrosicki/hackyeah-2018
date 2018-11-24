package com.prototype.hackyeah2018.model;

import java.util.List;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Relation;

@Entity
public class PharmacyWithMedicines {

    @Embedded
    private Pharmacy pharmacy;

    @Relation(parentColumn = "id", entityColumn = "pharmacyId")
    private List<Medicine> medicines;
}
