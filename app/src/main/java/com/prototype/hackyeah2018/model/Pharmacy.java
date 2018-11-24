package com.prototype.hackyeah2018.model;

import java.util.Objects;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Pharmacy {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String name;

    @Embedded
    private Coordinate coordinate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pharmacy pharmacy = (Pharmacy) o;
        return Objects.equals(id, pharmacy.id) &&
                Objects.equals(name, pharmacy.name) &&
                Objects.equals(coordinate, pharmacy.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinate);
    }
}
