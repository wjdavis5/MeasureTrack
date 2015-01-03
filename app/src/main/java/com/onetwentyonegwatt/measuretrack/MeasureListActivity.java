package com.onetwentyonegwatt.measuretrack;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.onetwentyonegwatt.MeasurementLib.Measurement;

import onetwentyonegwatt.com.measuretrack.R;


public class MeasureListActivity extends ListActivity {

    private String[] mMenuList;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    public MeasureSettings measureSettings;
    public ArrayAdapter<Measurement> MainArrayAdapter;
    static final int DRAWER_DELAY = 200;

    protected void onCreateInitializeDrawer(Bundle savedInstanceState)
    {
        mMenuList = getResources().getStringArray(R.array.menuList);
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the MainArrayAdapter for the list view
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,R.layout.drawer_list_item,R.id.txtItemEntry,mMenuList);
        mDrawerList.setAdapter(arrayAdapter);
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }
    protected void loadSettings(){
        try {
            measureSettings = MeasureSettings.LoadSettings(this);
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("e","e",e);
        }
    }
    protected void setupList()
    {  setContentView(R.layout.activity_measure_list);
        MainArrayAdapter =
                new ArrayAdapter<Measurement>(this, android.R.layout.simple_list_item_2, measureSettings.Config.Measurements);

        try {
            setListAdapter(MainArrayAdapter);

        } catch (Exception e) {
            Log.e("test", "test", e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadSettings();
        setupList();
        onCreateInitializeDrawer(savedInstanceState);

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

        if(id == R.id.action_AddEntry)
        {
            new Handler().postDelayed(openDrawerRunnable(), DRAWER_DELAY);
        }

        return super.onOptionsItemSelected(item);
    }

    private Runnable openDrawerRunnable() {
        return new Runnable() {

            @Override
            public void run() {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        };
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        FragmentManager fragmentManager = getFragmentManager();
        Measurement item = measureSettings.Config.Measurements.get(position);
        ViewBasicFragment viewBasicFragment = ViewBasicFragment.newInstance(item);
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, viewBasicFragment)
                .addToBackStack("a")
                .commit();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */

    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        FragmentManager fragmentManager = getFragmentManager();
        if(position == 0) {
            Fragment fragment = new CreateNewFragment();
            Bundle args = new Bundle();
            //args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
            fragment.setArguments(args);
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack("a")
                    .commit();
        }
        else{
            Toast.makeText(this,R.string.NotSupported,Toast.LENGTH_SHORT).show();
        }
        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        //setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }


}
