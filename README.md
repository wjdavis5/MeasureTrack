MeasureTrack
============

Simple Android App for jotting down measurements.

I've been doing some little projects around the house recently and I always find myself texting measurements to myself in order to keep track of them.

So I'm learning to use the Master/Detail template and running through tutorials on http://referrals.trhou.se/williamdavis3 and decided I would use my real world use case as a basis for a simple app. I followed through the "BlogReader" app on TeamTreehouse, but decided to start branching off on my own.

The app consists of two modules

**onetwentyonegwatt.com.measuretrack**
This is our android app - not much to explain here. A simple Master/Detail flow

and

**com.onetwentyonegwatt.MeasurementLib**
This is the various classes representing measurement types.
I kept arguing with myself about how this was going to be implemented. On one hand I wanted a simple type in a string as a value and be done. On the other hand I thought that I should set myself up for success if I ever wanted to expand upon this idea.

The latter one. So basically we have a base abstract class called Measurement

```
public abstract class Measurement {

    String Name;
    String Value;
    abstract void setName(String name);
    abstract Object getName();
    abstract void setValue(String value);
    abstract Object getValue();

    @Override
    abstract public String toString();
}
```

The first type that I am actually implemetning is the BasicMeasurement type

```
public class BasicMeasurement extends Measurement {

public BasicMeasurement(String name, String value){
    setName(name);
    setValue(value);
}
    @Override
    public void setName(String name) {
        this.Name = name;
    }

    @Override
    public String toString() {
        return getName() + ": " + getValue();
    }

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
```

The BasicMeasurement type fufills my desire for a quick 'note' style measurement with a name. While extending Measurement allows me to update all this jazz later.

Currently I also have a couple of other prototype types.
LinearMeasurement - which basically takes in a double Length
and
TwoDimensionMeasurement - which takes in two LinearMeasurements.
