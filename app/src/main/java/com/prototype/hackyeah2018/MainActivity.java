package com.prototype.hackyeah2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.prototype.hackyeah2018.db.AppDatabase;
import com.prototype.hackyeah2018.inserter.MedicineGenerator;
import com.prototype.hackyeah2018.model.Coordinate;
import com.prototype.hackyeah2018.model.Medicine;
import com.prototype.hackyeah2018.model.Pharmacy;
import com.prototype.hackyeah2018.reader.ReaderCaptureActivity;
import com.prototype.hackyeah2018.search.ISearchEngine;
import com.prototype.hackyeah2018.search.SimpleSearchEngine;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;

    private IMedicineService medicineService;

    private IPharmacyService pharmacyService;

    private List<Medicine> medicineList = new ArrayList<>();

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

        final AutoCompleteTextView suggestions = findViewById(R.id.autoCompleteTextView);

        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReaderCaptureActivity.class);
                startActivity(intent);
            }
        });

        String [] queryWords = getIntent().getStringArrayExtra("Array");

        if (queryWords != null) {
            List<Medicine> foundedMedicines = findMedicines(medicineList, Arrays.asList(queryWords), queryWords.length);
            ArrayAdapter<Medicine> adapter = new ArrayAdapter<>(this,R.layout.one_suggest_item, foundedMedicines);
            suggestions.setAdapter(adapter);
        }
    }

    private List<Medicine> findMedicines(List<Medicine> medicines, List<String> words, int index) {
        if (index >= words.size()) {
            return medicines;
        }
        ISearchEngine searchEngine = new SimpleSearchEngine();
        return findMedicines(searchEngine.search(medicines, words.get(index)), words, index++);
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
            medicineList.clear();
            medicineList.addAll(medicineService.getMedicines());
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
