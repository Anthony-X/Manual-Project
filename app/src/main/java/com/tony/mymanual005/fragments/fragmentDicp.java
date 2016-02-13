package com.tony.mymanual005.fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tony.mymanual005.R;

public class fragmentDicp extends Fragment {


    Button button1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_dicp, container, false);

        button1 = (Button) rootView.findViewById(R.id.button);

        button1.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame, new fragmentContent()).commit();

             if((((AppCompatActivity)getActivity()).getSupportActionBar())!=null)
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.nav_asm);

            }});

        return rootView;
    }


}
