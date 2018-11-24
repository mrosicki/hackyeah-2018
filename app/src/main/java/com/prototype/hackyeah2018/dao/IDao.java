package com.prototype.hackyeah2018.dao;

import java.util.List;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

@Dao
public interface IDao<T> {

    T findById(Long id);

    List<T> findAll();

    @Delete
    void delete(T object);

    @Delete
    Integer deleteAll();

    @Insert
    void insert(T object);

    @Insert
    void insertAll(List<T> objects);
}
