package com.onetwentyonegwatt.MeasurementLib;

/**
 * Created by William.Davis on 12/31/2014.
 */
public class TwoDimensionMeasurement extends Measurement {

    public LinearMeasurement LinearX;
    public LinearMeasurement LinearY;

    public TwoDimensionMeasurement(LinearMeasurement x,LinearMeasurement y){
        LinearX = x;
        LinearY = y;
    }

    @Override
    void setName(String name) {
        this.Name = name;
    }

    @Override
    Object getName() {
        return this.Name;
    }

    /*
    @deprecated
     */
    @Override
    @Deprecated
    void setValue(String value) {

    }

    @Override
    Object getValue() {
        return this.Value;
    }

    @Override
    public String toString() {
        return this.Name;
    }
}
