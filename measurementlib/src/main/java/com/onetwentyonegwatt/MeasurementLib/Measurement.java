package com.onetwentyonegwatt.MeasurementLib;


import java.io.Serializable;

/**
 * Created by William.Davis on 12/31/2014.
 */
public abstract class Measurement implements Serializable {

    String Name;
    String Value;
    String MyType;
    abstract void setName(String name);
    abstract Object getName();
    abstract void setValue(String value);
    abstract Object getValue();

    @Override
    abstract public String toString();
}
