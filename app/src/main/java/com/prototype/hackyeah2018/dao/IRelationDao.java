package com.prototype.hackyeah2018.dao;

import java.util.List;

import com.prototype.hackyeah2018.model.PharmacyWithMedicines;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

@Dao
public interface IRelationDao<T> {
    T findById(Long id);

    List<T> findAll();
}
