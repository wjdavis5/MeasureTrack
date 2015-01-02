package com.onetwentyonegwatt.measuretrack;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.onetwentyonegwatt.MeasurementLib.BasicMeasurement;
import com.onetwentyonegwatt.MeasurementLib.Measurement;

import java.util.Locale;

import onetwentyonegwatt.com.measuretrack.R;

/**
 * Created by William.Davis on 1/1/2015.
 */
public class CreateNewFragment extends Fragment implements View.OnClickListener {

    Activity mActivity;
    MeasureSettings measureSettings;

    public CreateNewFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mActivity = getActivity();
        measureSettings = ((MeasureListActivity)mActivity).measureSettings;
        View rootView = inflater.inflate(R.layout.fragment_new_measurement, container, false);
        Button btnSave = (Button) rootView.findViewById(R.id.btnSaveMeasurement);
        btnSave.setOnClickListener(this);
        return rootView;
    }
    public void saveMeasurementClickHandler(View target) {
        EditText txtName = (EditText) mActivity.findViewById(R.id.txtName);
        EditText txtValue = (EditText) mActivity.findViewById(R.id.txtValue);
        if(txtName.getText().toString().isEmpty() || txtValue.getText().toString().isEmpty()) {
            Toast.makeText(mActivity, "Please provide values for all fields", Toast.LENGTH_LONG).show();
            return;
        }
        //TODO Update to use generics
        // btnSave = (Button) findViewById(R.id.btnSaveMeasurement);
        Measurement nM = new BasicMeasurement(txtName.getText().toString(),
                txtValue.getText().toString());

        measureSettings.Config.Measurements.add(nM);
        measureSettings.SaveSettings();

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        saveMeasurementClickHandler(v);
    }
}
