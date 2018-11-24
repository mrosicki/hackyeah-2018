package com.prototype.hackyeah2018.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prototype.hackyeah2018.db.AppDatabase;
import com.prototype.hackyeah2018.model.Medicine;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class MedicineDaoTest {

    private MedicineDao medicineDao;

    private AppDatabase database;

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        medicineDao = database.getMedicineDao();
    }

    @Test
    public void delete() {
        Medicine medicine = new Medicine();
        medicine.setId(1L);
        medicine.setName("medicine");
        medicine.setAvailable(true);
        medicine.setPharmacyId(null);

        medicineDao.insert(medicine);
        medicineDao.delete(medicine);
        Assert.assertEquals(0, medicineDao.findAll().size());
    }

    @Test
    public void findById() {
        Medicine medicine = new Medicine();
        medicine.setId(1L);
        medicine.setName("medicine");
        medicine.setAvailable(true);
        medicine.setPharmacyId(null);

        medicineDao.insert(medicine);

        Assert.assertEquals(medicine,  medicineDao.findById(1L));
    }

    @Test
    public void findAll() {
        Medicine m1 = new Medicine();
        m1.setId(1L);
        m1.setName("m1");
        m1.setAvailable(true);
        m1.setPharmacyId(null);

        Medicine m2 = new Medicine();
        m2.setId(2L);
        m2.setName("m2");
        m2.setAvailable(true);
        m2.setPharmacyId(null);

        List<Medicine> medicines = new ArrayList<>(Arrays.asList(m1, m2));

        medicineDao.insertAll(medicines);

        Assert.assertEquals(medicines, medicineDao.findAll());
    }

    @After
    public void tearDown() {
        database.close();
    }
}