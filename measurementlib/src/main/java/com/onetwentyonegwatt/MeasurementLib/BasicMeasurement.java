package com.onetwentyonegwatt.MeasurementLib;

import java.io.Serializable;

/**
 * Created by William.Davis on 12/31/2014.
 * The basic measurement is, as implied, just a very basic measurement
 * Essentially this is nothing more than storing of a simple string with a name currently
 * Thats all I really need it to be for my upcoming project
 */
public class BasicMeasurement extends Measurement  {

public BasicMeasurement(String name, String value){
    setName(name);
    setValue(value);
    MyType = this.getClass().getCanonicalName();
}
    @Override
    public void setName(String name) {
        this.Name = name;
    }

    @Override
    public String toString() {
        return getName() + " - " + this.getClass().getSimpleName();    }

    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public void setValue(String value) {
        this.Value = value;
    }

    @Override
    public String getValue() {
        return Value;
    }
}
