package com.prototype.hackyeah2018.db;

import com.prototype.hackyeah2018.dao.MedicineDao;
import com.prototype.hackyeah2018.dao.PharmacyDao;
import com.prototype.hackyeah2018.model.Medicine;
import com.prototype.hackyeah2018.model.Pharmacy;
import com.prototype.hackyeah2018.model.PharmacyWithMedicines;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {
//        Pharmacy.class,
        Medicine.class,
//        PharmacyWithMedicines.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

//    public abstract PharmacyDao getPharmacyDao();

    public abstract MedicineDao getMedicineDao();
}
