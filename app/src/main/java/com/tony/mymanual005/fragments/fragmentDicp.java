package com.tony.mymanual005.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tony.mymanual005.AboutUsActivity;
import com.tony.mymanual005.ContentActivity;
import com.tony.mymanual005.MainActivity;
import com.tony.mymanual005.R;


public class fragmentDicp extends Fragment {

    private String mParam1;
    private String mParam2;
    Button button1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_dicp, container, false);

        button1 = (Button) rootView.findViewById(R.id.button);

        button1.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fragmentDicp.this.getActivity(),MainActivity.class);
                startActivity(intent);
            }});

        return rootView;
    }


}
