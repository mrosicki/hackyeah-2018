package com.prototype.hackyeah2018.dao;

import java.util.List;

import com.prototype.hackyeah2018.model.Pharmacy;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

@Dao
public interface PharmacyDao extends IDao<Pharmacy> {

    @Override
    @Query("SELECT * FROM pharmacy WHERE id = :id")
    Pharmacy findById(Long id);

    @Override
    @Query("SELECT * FROM pharmacy")
    List<Pharmacy> findAll();
}
