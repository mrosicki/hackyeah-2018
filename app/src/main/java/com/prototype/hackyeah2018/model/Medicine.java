package com.prototype.hackyeah2018.model;

import static android.arch.persistence.room.ForeignKey.CASCADE;

import java.util.Objects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Pharmacy.class, parentColumns = "id", childColumns = "pharmacyId", onDelete = CASCADE))
public class Medicine {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String name;

    private Boolean available;

    private Long pharmacyId;

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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Medicine medicine = (Medicine) o;
        return Objects.equals(id, medicine.id) &&
                Objects.equals(name, medicine.name) &&
                Objects.equals(available, medicine.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, available);
    }

    @Override
    public String toString() {
        return name;
    }
}
