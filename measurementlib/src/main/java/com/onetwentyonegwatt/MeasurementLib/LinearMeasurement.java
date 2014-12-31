package com.onetwentyonegwatt.MeasurementLib;

/**
 * Created by William.Davis on 12/31/2014.
 */
public class LinearMeasurement extends Measurement {

    public double LinearLength;

    @Override
    void setName(String name) {
        this.Name = name;
    }

    @Override
    Object getName() {
        return this.Name;
    }

    /*
    @deprecated use setLength instead
     */
    @Override
    @Deprecated
    void setValue(String value) {

    }

    public void setLinearLength(double value)
    {
        LinearLength = value;
    }

    @Override
    Object getValue() {
        return LinearLength;
    }

    @Override
    public String toString() {
        return getName() + ": " + getValue().toString() + " long";
    }
}
