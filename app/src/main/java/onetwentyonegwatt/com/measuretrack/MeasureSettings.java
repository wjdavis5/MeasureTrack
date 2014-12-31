package onetwentyonegwatt.com.measuretrack;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.onetwentyonegwatt.MeasurementLib.Measurement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by William.Davis on 12/31/2014.
 */
public class MeasureSettings {

    private String json;
    private static final Gson gson = new Gson();
    public static final String SETTINGS_FILE_NAME = "MeasureTrack.config";
    public Config Config;
    private Activity mActivity;

    private MeasureSettings(Activity activity) {
        mActivity = activity;
        Config = new Config();
    }

   public static MeasureSettings LoadSettings(Activity activity) throws Exception {
       MeasureSettings measureSettings = new MeasureSettings(activity);
       measureSettings.json = getStringFromFile(SETTINGS_FILE_NAME);
       if(measureSettings.json.length() != 0) {
           measureSettings.Config = gson.fromJson(measureSettings.json, Config.class);
       }

       return measureSettings;
   }

    public Boolean SaveSettings() {
        try {
            FileOutputStream fileOutputStream = mActivity.openFileOutput(SETTINGS_FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(gson.toJson(this.Config).getBytes());
            fileOutputStream.close();
            return Boolean.TRUE;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;
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

    private static String getStringFromFile (String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(fl);
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

        public Config(){
            Measurements = new ArrayList<Measurement>();
            Items = new HashMap<>();
        }


    }

}
