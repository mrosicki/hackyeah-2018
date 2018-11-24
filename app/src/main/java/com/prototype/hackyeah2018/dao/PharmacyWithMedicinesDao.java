package com.prototype.hackyeah2018.dao;

import java.util.List;

import com.prototype.hackyeah2018.model.PharmacyWithMedicines;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

@Dao
public interface PharmacyWithMedicinesDao extends IDao<PharmacyWithMedicines> {

    @Override
    @Query("SELECT * FROM pharmacywithmedicines WHERE id = :id")
    PharmacyWithMedicines findById(Long id);

    @Override
    @Query("SELECT * FROM pharmacyWithMedicines")
    List<PharmacyWithMedicines> findAll();
}
