package onetwentyonegwatt.com.measuretrack;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import com.onetwentyonegwatt.MeasurementLib.BasicMeasurement;
import com.onetwentyonegwatt.MeasurementLib.Measurement;

import java.util.ArrayList;
import java.util.List;


public class MeasureListActivity extends ListActivity {

    //protected String[] mMeasurements =  {"one","two"};
    protected List<Measurement> mMeasurements = new ArrayList<Measurement>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeasurements.add(new BasicMeasurement("test", "1\" x 2\""));
        setContentView(R.layout.activity_measure_list);
        ArrayAdapter<Measurement> adapter = new ArrayAdapter<Measurement>(this,android.R.layout.simple_list_item_1, mMeasurements);
        try {
            setListAdapter(adapter);
        }
        catch(Exception e) {
            Log.e("test", "test", e);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_measure_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
