package com.prototype.hackyeah2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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

import android.Manifest.permission;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;

    private IMedicineService medicineService;

    private IPharmacyService pharmacyService;

    private List<Medicine> medicineList;

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMapView(savedInstanceState);

        database = AppDatabase.getInstance(this);
        medicineService = new MedicineService(database.getMedicineDao());
        pharmacyService = new PharmacyService(database.getPharmacyDao(), database.getPharmacyWithMedicinesDao());
        new FillDatabaseTask().execute();

        new GetAllMedicinesTask().execute();
        final List<Medicine> matchedMedicines = new ArrayList<>();


        final Button takePicture = findViewById(R.id.buttonPicture);

        final AutoCompleteTextView suggestions = findViewById(R.id.autoCompleteTextView);

        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReaderCaptureActivity.class);
                matchedMedicines.clear();
                startActivity(intent);
            }
        });

        if (getIntent().getStringArrayExtra("Array") != null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String[] textArray = getIntent().getStringArrayExtra("Array");
            for (int i = 0; i < textArray.length; i++) {
                for (int j = 0; j < medicineList.size(); j++) {
                    System.out.println("MainActivity: " + i + " " + textArray[i]);
                    if (medicineList.get(j).getName().toLowerCase().contains(textArray[i].toLowerCase())) {
                        System.out.println("Found: " + textArray[i] + " in " + medicineList.get(j).getName());
                        matchedMedicines.add(medicineList.get(j));
                    }
                }
            }
            ArrayAdapter<Medicine> adapter = new ArrayAdapter<>(this, R.layout.one_suggest_item, matchedMedicines);
            suggestions.setAdapter(adapter);
        }
    }

    private void initMapView(Bundle savedInstanceState) {
        MapView mapView = findViewById(R.id.pharmacyMapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MainActivity.this.googleMap = googleMap;
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat
                        .checkSelfPermission(MainActivity.this,
                                permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                for (LatLng latLng : getMapPinsLocations()) {
                    googleMap.addMarker(new MarkerOptions().position(latLng));
                }
            }
        });
    }

    private List<LatLng> getMapPinsLocations() {
        List<LatLng> latLngs = new ArrayList<>();
        for (Pharmacy pharmacy : loadPharmacies()) {
            latLngs.add(new LatLng(pharmacy.getCoordinate().getLattitude(), pharmacy.getCoordinate().getLongtitude()));
        }

        return latLngs;
    }

    private List<Pharmacy> loadPharmacies() {
        return Collections.emptyList();
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

            Pharmacy p1 = new Pharmacy();
            p1.setId(1L);
            p1.setName("DOZ1");
            p1.setCoordinate(new Coordinate(52.2901142, 20.9818041));

            List<Medicine> m1 = MedicineGenerator.getMedicines(p1);

            Pharmacy p2 = new Pharmacy();
            p2.setId(2L);
            p2.setName("DOZ2");
            p2.setCoordinate(new Coordinate(52.2901038, 20.9818041));

            List<Medicine> m2 = MedicineGenerator.getMedicines(p1);

            pharmacyService.insertPharmacy(p1);
            medicineService.insertMedicines(m1.subList(0, m1.size() / 2));

            pharmacyService.insertPharmacy(p2);
            medicineService.insertMedicines(m2.subList(m2.size() / 2, m2.size() - 1));
        }
    }
}
