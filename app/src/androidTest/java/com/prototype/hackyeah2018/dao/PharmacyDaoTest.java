package com.prototype.hackyeah2018.dao;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.prototype.hackyeah2018.db.AppDatabase;
import com.prototype.hackyeah2018.model.Coordinate;
import com.prototype.hackyeah2018.model.Pharmacy;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

public class PharmacyDaoTest {

    private AppDatabase database;

    private PharmacyDao pharmacyDao;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        pharmacyDao = database.getPharmacyDao();
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void delete() {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(1L);
        pharmacy.setName("pharmacy");
        pharmacy.setCoordinate(new Coordinate(1., 2.));

        pharmacyDao.insert(pharmacy);
        pharmacyDao.delete(pharmacy);

        Assert.assertEquals(0, pharmacyDao.findAll().size());
    }

    @Test
    public void findById() {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(1L);
        pharmacy.setName("pharmacy");
        pharmacy.setCoordinate(new Coordinate(1., 2.));

        pharmacyDao.insert(pharmacy);

        Assert.assertEquals(pharmacy, pharmacyDao.findById(1L));
    }

    @Test
    public void findAll() {

        Pharmacy f1 = new Pharmacy();
        f1.setId(1L);
        f1.setName("f1");
        f1.setCoordinate(new Coordinate(1., 2.));

        Pharmacy f2 = new Pharmacy();
        f2.setId(2L);
        f2.setName("f2");
        f2.setCoordinate(new Coordinate(1., 2.));

        pharmacyDao.insert(f1);
        pharmacyDao.insert(f2);

        Assert.assertEquals(Arrays.asList(f1, f2), pharmacyDao.findAll());
    }
}