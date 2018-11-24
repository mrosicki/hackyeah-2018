package com.prototype.hackyeah2018.dao;

import java.util.List;

import com.prototype.hackyeah2018.model.Medicine;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

@Dao
public interface MedicineDao extends IDao<Medicine> {

    @Override
    @Query("SELECT * FROM medicine WHERE id = :id")
    Medicine findById(Long id);

    @Override
    @Query("SELECT * FROM medicine")
    List<Medicine> findAll();
}
