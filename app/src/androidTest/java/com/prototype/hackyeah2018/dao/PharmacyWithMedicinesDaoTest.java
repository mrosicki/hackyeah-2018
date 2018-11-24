package com.prototype.hackyeah2018.dao;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.prototype.hackyeah2018.db.AppDatabase;
import com.prototype.hackyeah2018.model.Coordinate;
import com.prototype.hackyeah2018.model.Medicine;
import com.prototype.hackyeah2018.model.Pharmacy;
import com.prototype.hackyeah2018.model.PharmacyWithMedicines;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class PharmacyWithMedicinesDaoTest {

    private AppDatabase database;

    private PharmacyWithMedicinesDao pharmacyWithMedicinesDao;

    private PharmacyDao pharmacyDao;

    private MedicineDao medicineDao;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        pharmacyWithMedicinesDao = database.getPharmacyWithMedicinesDao();
        medicineDao = database.getMedicineDao();
        pharmacyDao = database.getPharmacyDao();
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void findById() {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName("pharmacy");
        pharmacy.setCoordinate(new Coordinate(1.0, 2.0));
        pharmacy.setId(1L);

        pharmacyDao.insert(pharmacy);

        Medicine m1 = new Medicine();
        m1.setId(1L);
        m1.setPharmacyId(1L);
        m1.setAvailable(true);
        m1.setName("m1");

        Medicine m2 = new Medicine();
        m2.setId(2L);
        m2.setPharmacyId(1L);
        m2.setAvailable(true);
        m2.setName("m2");

        Medicine m3 = new Medicine();
        m3.setId(3L);
        m3.setPharmacyId(1L);
        m3.setAvailable(true);
        m3.setName("m3");

        medicineDao.insertAll(Arrays.asList(m1, m2, m3));

        PharmacyWithMedicines pharmacyWithMedicines = new PharmacyWithMedicines();
        pharmacyWithMedicines.setPharmacy(pharmacy);
        pharmacyWithMedicines.setMedicines(Arrays.asList(m1, m2, m3));

        Assert.assertEquals(pharmacyWithMedicines, pharmacyWithMedicinesDao.findById(1L));
    }

    @Test
    public void findAll() {

    }
}