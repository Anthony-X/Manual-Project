package com.tony.mymanual005.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.tony.mymanual005.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class fragmentContentTwo extends Fragment{


    WebView webView;
    ImageButton iB_next,iB_previous;
    String resName;
    int res;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contenttwo,container,false);

        iB_next = (ImageButton) rootView.findViewById(R.id.iB_next);
        iB_previous = (ImageButton) rootView.findViewById(R.id.iB_previous);

        iB_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(res+1<=1){
                    res++;
                    resName = "n" + res;
                    String text = readRawTextFile(getActivity(), getResources().getIdentifier(resName, "raw", "com.tony.mymanual005"));

                    webView.loadDataWithBaseURL(null, text, "text/html", "en_US", null);}

            }
        });
        iB_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(res-1>=0){
                    res--;
                    resName = "n" + res;
                    String text = readRawTextFile(getActivity(), getResources().getIdentifier(resName, "raw", "com.tony.mymanual005"));
                    webView.loadDataWithBaseURL(null, text, "text/html", "en_US", null);
                }
            }
        });


        /*Intent intent = getIntent();*/
        Bundle bundle=getArguments();
        if(bundle!=null) {
            res = bundle.getInt("head");
            resName = "n" + bundle.getInt("head");
        }

       webView = (WebView)  rootView.findViewById(R.id.webView);
        //получаем строку и формируем имя ресурса


        Context context = getActivity(); //получаем контекст
        //читаем текстовый файл из ресурсов по имени
        String text = readRawTextFile(context, getResources().getIdentifier(resName, "raw", "com.tony.mymanual005"));

        webView.loadDataWithBaseURL(null, text, "text/html", "en_US", null);



        return rootView;

    }


    // Обрабатываем переход на след/пред тему
    //читаем текст из raw-ресурсов
    public static String readRawTextFile(Context context, int resId)
    {
        InputStream inputStream = context.getResources().openRawResource(resId);

        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buffReader = new BufferedReader(inputReader);
        String line;
        StringBuilder builder = new StringBuilder();

        try {
            while (( line = buffReader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        } catch (IOException e) {
            return null;
        }
        return builder.toString();
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        int textSize = Integer.parseInt(prefs.getString(
                getString(R.string.pref_text_size), "16"));
        if(textSize<=26 && textSize>=10)
            webView.getSettings().setDefaultFontSize(textSize);

    }

}
