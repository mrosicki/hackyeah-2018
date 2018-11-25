package com.prototype.hackyeah2018;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.prototype.hackyeah2018.db.AppDatabase;
import com.prototype.hackyeah2018.inserter.MedicineGenerator;
import com.prototype.hackyeah2018.model.Coordinate;
import com.prototype.hackyeah2018.model.Medicine;
import com.prototype.hackyeah2018.model.Pharmacy;
import com.prototype.hackyeah2018.model.PharmacyWithMedicines;
import com.prototype.hackyeah2018.reader.ReaderCaptureActivity;
import com.prototype.hackyeah2018.search.ISearchEngine;
import com.prototype.hackyeah2018.search.SimpleSearchEngine;
import com.prototype.hackyeah2018.service.IMedicineService;
import com.prototype.hackyeah2018.service.IPharmacyService;
import com.prototype.hackyeah2018.service.MedicineService;
import com.prototype.hackyeah2018.service.PharmacyService;

import android.Manifest.permission;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;

    private FusedLocationProviderClient mFusedLocationClient;

    private IMedicineService medicineService;

    private IPharmacyService pharmacyService;

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        initMapView(savedInstanceState);

        database = AppDatabase.getInstance(this);
        medicineService = new MedicineService(database.getMedicineDao());
        pharmacyService = new PharmacyService(database.getPharmacyDao(), database.getPharmacyWithMedicinesDao());

        new FillDatabaseTask().execute();
        initTakePictureButton();
        initSearchButton();
        initSuggestions();
    }

    private void initSuggestions() {
        getSuggestions().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = "";
                if (s != null) {
                    text = s.toString();
                }
                new MedicineSearchForSuggestionsTask().execute(text.split(" "));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setTextFromTextRecognition();
    }

    private void setTextFromTextRecognition() {
        String[] texts = getIntent().getStringArrayExtra("Array");
        if (texts != null) {
            StringBuilder builder = new StringBuilder();
            for (String t : texts) {
                builder.append(t).append(" ");
            }
            getSuggestions().setText(builder.toString());
        }
    }


    private void initSearchButton() {
        getSearchButton().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new PharmacySearchForGoogleMapsTask().execute(getSuggestions().getText().toString());
            }
        });
    }

    private class PharmacySearchForGoogleMapsTask extends AsyncTask<String, Void, List<Pharmacy>> {

        @Override
        protected List<Pharmacy> doInBackground(String... medicineNames) {
            List<PharmacyWithMedicines> pharmacyWithMedicinesList = loadPharmaciesWithMedicines();

            List<Pharmacy> matchedPharmacies = new ArrayList<>();

            if(medicineNames.length == 1) {
                ISearchEngine searchEngine = new SimpleSearchEngine();
                for (PharmacyWithMedicines pharmacyWithMedicines : pharmacyWithMedicinesList) {
                    if(!searchEngine.search(pharmacyWithMedicines.getMedicines(), medicineNames[0]).isEmpty()) {
                        matchedPharmacies.add(pharmacyWithMedicines.getPharmacy());
                    }
                }
            }

            return matchedPharmacies;
        }

        @Override
        protected void onPostExecute(List<Pharmacy> pharmacies) {
            for (Pharmacy pharmacy : pharmacies) {
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(pharmacy.getCoordinate().getLattitude(),
                                pharmacy.getCoordinate().getLongtitude()))
                        .title(pharmacy.getName()));
            }
        }
    }

    private  class MedicineSearchForSuggestionsTask extends AsyncTask<String, Void, List<Medicine>> {

        @Override
        protected List<Medicine> doInBackground(String... queries) {
            List<Medicine> medicines = loadMedicines();
            if (queries.length > 0) {
                ISearchEngine searchEngine  = new SimpleSearchEngine();

                for (String query : queries) {
                    medicines = searchEngine.search(medicines, query);
                }
            }
            return medicines;
        }

        @Override
        protected void onPostExecute(List<Medicine> medicines) {
            getSuggestions().setAdapter(new ArrayAdapter<>(MainActivity.this, R.layout.one_suggest_item, medicines));
        }
    }

    private List<Medicine> loadMedicines() {
        return medicineService.getMedicines();
    }


    private void initTakePictureButton() {
        getTakePictureButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReaderCaptureActivity.class);
                startActivity(intent);
            }
        });

    }

    private Button getTakePictureButton() {
        return findViewById(R.id.buttonPicture);
    }

    private Button getSearchButton() {
        return findViewById(R.id.buttonSearch);
    }

    private AutoCompleteTextView getSuggestions() {
        return findViewById(R.id.autoCompleteTextView);
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
                mFusedLocationClient.getLastLocation().addOnSuccessListener(MainActivity.this,
                        new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(12).build();
                                    MainActivity.this.googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                    MainActivity.this.googleMap.addMarker(new MarkerOptions()
                                            .position(new LatLng(location.getLatitude(), location.getLongitude()))
                                            .title("tutaj"));
                                }
                            }
                        });
            }
        });
    }

    private List<Pharmacy> loadPharmacies() {
        return pharmacyService.getPharmacies();
    }

    private List<PharmacyWithMedicines> loadPharmaciesWithMedicines() {
        return pharmacyService.getPharmacyWithMedicines();
    }

    private class FillDatabaseTask extends AsyncTask<Void, Void, Void> {
        private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        @Override
        protected Void doInBackground(Void... voids) {
            fillDatabase();
//            dialog.show();
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

            List<Medicine> m2 = MedicineGenerator.getMedicines(p2);

            pharmacyService.insertPharmacy(p1);
            medicineService.insertMedicines(m1.subList(0, m1.size() / 2));

            pharmacyService.insertPharmacy(p2);
            medicineService.insertMedicines(m2.subList(m2.size() / 2, m2.size() - 1));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
//            dialog.dismiss();
        }
    }
}
