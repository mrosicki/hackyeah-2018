package com.prototype.hackyeah2018;

import java.sql.Time;
import java.util.List;

import com.prototype.hackyeah2018.db.AppDatabase;
import com.prototype.hackyeah2018.inserter.MedicineGenerator;
import com.prototype.hackyeah2018.model.Coordinate;
import com.prototype.hackyeah2018.model.Medicine;
import com.prototype.hackyeah2018.model.Pharmacy;
import com.prototype.hackyeah2018.reader.ReaderCaptureActivity;
import com.prototype.hackyeah2018.service.IMedicineService;
import com.prototype.hackyeah2018.service.IPharmacyService;
import com.prototype.hackyeah2018.service.MedicineService;
import com.prototype.hackyeah2018.service.PharmacyService;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;

    private IMedicineService medicineService;

    private IPharmacyService pharmacyService;

    private List<Medicine> medicineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = AppDatabase.getInstance(this);
        medicineService = new MedicineService(database.getMedicineDao());
        pharmacyService = new PharmacyService(database.getPharmacyDao(), database.getPharmacyWithMedicinesDao());
        new FillDatabaseTask().execute();

        new GetAllMedicinesTask().execute();



        final Button takePicture= findViewById(R.id.buttonPicture);

        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReaderCaptureActivity.class);
                startActivity(intent);
            }
        });

        if (getIntent().getStringArrayExtra("Array")!=null){
            String[] textArray = getIntent().getStringArrayExtra("Array");
            for (int i = 0;i<textArray.length;i++){
                for (int j = 0 ;j<medicineList.size();j++){
                    System.out.println("MainActivity: " + i + " " + textArray[i]);
                    if (medicineList.get(j).getName().toLowerCase().contains(textArray[i].toLowerCase())){
                        System.out.println("Found: " + textArray[i] + " in " + medicineList.get(j).getName());
                        break;
                    }
                }
            }
        }
    }

    private class GetAllMedicinesTask extends AsyncTask<Void, Void, Void>{
        private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Matching...");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            this.dialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids){
               medicineList = medicineService.getMedicines();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }



    private class FillDatabaseTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            fillDatabase();
            return null;
        }

        private void fillDatabase() {
            Pharmacy pharmacy = new Pharmacy();
            pharmacy.setId(1L);
            pharmacy.setName("DOZ");
            pharmacy.setCoordinate(new Coordinate(1.0, 1.0));

            List<Medicine> medicines = MedicineGenerator.getMedicines(pharmacy);

            pharmacyService.insertPharmacy(pharmacy);
            medicineService.insertMedicines(medicines);
        }
    }
}
