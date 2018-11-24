package com.prototype.hackyeah2018.model;

import java.util.ArrayList;
import java.util.List;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

@Entity
public class Pharmacy {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String name;

    @Embedded
    private Coordinate coordinate;

    @Relation(parentColumn = "pharmacy.id", entityColumn = "pharmacyId")
    private List<Medicine> medicines = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
