package com.prototype.hackyeah2018.model;

import java.util.Objects;

public class Coordinate {

    private final Double longtitude;

    private final Double lattitude;

    public Coordinate(Double lattitude, Double longtitude) {
        this.lattitude = lattitude;
        this.longtitude = longtitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public Double getLattitude() {
        return lattitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return Objects.equals(longtitude, that.longtitude) &&
                Objects.equals(lattitude, that.lattitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longtitude, lattitude);
    }
}
