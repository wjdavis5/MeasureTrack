package com.onetwentyonegwatt.MeasurementLib;

/**
 * Created by William.Davis on 12/31/2014.
 */
public abstract class BaseMeasurement {

    String Name;
    String Value;
    abstract void setName(String name);
    abstract Object getName();
    abstract void setValue(String value);
    abstract Object getValue();

    @Override
    abstract public String toString();
}
