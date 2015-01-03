package com.onetwentyonegwatt.measuretrack;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
//import com.onetwentyonegwatt.measuretrack.AClassAdapter;
import com.onetwentyonegwatt.MeasurementLib.Measurement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import onetwentyonegwatt.com.measuretrack.R;


/**
 * Created by William.Davis on 12/31/2014.
 */
public class MeasureSettings {

    private String json;
    private static final Gson gson = new Gson();
    public static final String SETTINGS_FILE_NAME = "MeasureTrack.config";
    public Config Config;
    private Activity mActivity;
    protected GsonBuilder gsonBuilder;
    public AClassAdapter<Measurement> measurementAClassAdapter;

    private MeasureSettings(Activity activity) {
        mActivity = activity;
        Config = new Config();
        gsonBuilder = new GsonBuilder();
        measurementAClassAdapter = new AClassAdapter<>();
        gsonBuilder.registerTypeAdapter(Config.class,new AClassAdapter<>());
    }


   public static MeasureSettings LoadSettings(Activity activity) throws Exception {
       MeasureSettings measureSettings = new MeasureSettings(activity);
       measureSettings.json = measureSettings.getStringFromFile(SETTINGS_FILE_NAME);
       if(measureSettings.json.length() != 0) {
          JsonParser jsonParser = new JsonParser();
          JsonObject o = (JsonObject)jsonParser.parse(measureSettings.json);
          JsonElement items = o.get("Items");
          measureSettings.Config.Items = gson.fromJson(items,measureSettings.Config.Items.getClass());
          JsonElement measurements = o.get("Measurements");
          for(JsonElement item : measurements.getAsJsonArray())
           {
               String className = item.getAsJsonObject().get("MyType").getAsString();
               Measurement l = (Measurement)measureSettings.gsonBuilder.create().fromJson(item, Class.forName(className));
               measureSettings.Config.Measurements.add(l);
           }

       }
       measureSettings.SaveSettings();
       return measureSettings;
   }

    public boolean SaveSettings() {
        try {

            FileOutputStream fileOutputStream = mActivity.openFileOutput(SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
            this.json = gson.toJson(this.Config);
           // this.json = gsonBuilder.create().toJson(this.Config);
            fileOutputStream.write(this.json.getBytes());
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    private String getStringFromFile (String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = null;
        try {
            fin = mActivity.openFileInput(SETTINGS_FILE_NAME);
            String ret = convertStreamToString(fin);
            fin.close();
            return ret;
        }
       catch(FileNotFoundException e){
           Log.e("e", "e", e);
           return "";
       }

        //Make sure you close all streams.


    }

    public class Config {
        public List<Measurement> Measurements;
        public HashMap<String,String> Items;
        public String MyType;
        public Config(){
            Measurements = new ArrayList<Measurement>();
            Items = new HashMap<>();
            MyType = this.getClass().getCanonicalName();
        }

    }

    public class AClassAdapter<A>  implements JsonSerializer<A>, JsonDeserializer<A> {
        @Override
        public JsonElement serialize(A src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject result = new JsonObject();

            result.add("MyType", new JsonPrimitive(src.getClass().getCanonicalName()));
            result.add("properties", context.serialize(src, src.getClass()));
            return result;
        }


        @Override
        public A deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String type = jsonObject.get("MyType").getAsString();
            try {
                // String thePackage = "com.onetwentyonegwatt.MeasurementLib";
                return context.deserialize(json, Class.forName(type));
            } catch (ClassNotFoundException e) {
                throw new JsonParseException("Unknown element type: " + type, e);
            }
        }
    }

}
