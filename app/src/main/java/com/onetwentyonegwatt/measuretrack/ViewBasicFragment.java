package com.onetwentyonegwatt.measuretrack;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.onetwentyonegwatt.MeasurementLib.BasicMeasurement;
import com.onetwentyonegwatt.MeasurementLib.Measurement;

import onetwentyonegwatt.com.measuretrack.R;



public class ViewBasicFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MEASUREMENT = "Measurement";
    BasicMeasurement basicMeasurement;
    Button btnBack;
    Button btnDelete;
    EditText txtName;
    EditText txtValue;

    public static ViewBasicFragment newInstance(Measurement measurement) {
        ViewBasicFragment fragment = new ViewBasicFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MEASUREMENT,(BasicMeasurement)measurement);
        fragment.setArguments(args);
        return fragment;
    }

    public ViewBasicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_basic, container, false);
        if (getArguments() != null) {
            basicMeasurement = (BasicMeasurement) getArguments().getSerializable(ARG_MEASUREMENT);
            btnBack = (Button) rootView.findViewById(R.id.btnBack);
            btnBack.setOnClickListener(this);
            txtName = (EditText) rootView.findViewById(R.id.txtName);
            txtValue = (EditText) rootView.findViewById(R.id.txtValue);
            txtName.setText(basicMeasurement.getName());
            txtValue.setText(basicMeasurement.getValue());
            btnDelete = (Button)rootView.findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Activity mActivity = getActivity();
                    if(((MeasureListActivity)mActivity).measureSettings.Config.Measurements.contains(basicMeasurement))
                    {
                        ((MeasureListActivity)mActivity).measureSettings.Config.Measurements.remove(basicMeasurement);
                        ((MeasureListActivity)mActivity).measureSettings.SaveSettings();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.popBackStackImmediate();
                    }
                    else{
                        Toast.makeText(mActivity,"ERROR",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return rootView;
    }



    @Override
    public void onClick(View v) {
        //TODO Save the measurement
        Activity mActivity = getActivity();
        if(((MeasureListActivity)mActivity).measureSettings.Config.Measurements.contains(basicMeasurement))
        {
            basicMeasurement.setName(txtName.getText().toString());
            basicMeasurement.setValue(txtValue.getText().toString());
            ((MeasureListActivity)mActivity).measureSettings.SaveSettings();
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStackImmediate();

    }




}
