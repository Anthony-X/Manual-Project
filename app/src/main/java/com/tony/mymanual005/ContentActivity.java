package com.tony.mymanual005;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ContentActivity extends ActionBarActivity {
    WebView webView;
    String resName;
    int res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content);
        webView = (WebView) findViewById(R.id.webView);

        Intent intent = getIntent();
        //получаем строку и формируем имя ресурса
        res = intent.getIntExtra("head", 0);
        resName = "n" + intent.getIntExtra("head", 0);
        Log.i("name", resName);
        Context context = getBaseContext(); //получаем контекст

        //читаем текстовый файл из ресурсов по имени
        String text = readRawTextFile(context, getResources().getIdentifier(resName, "raw", "com.tony.mymanual005"));

        webView.loadDataWithBaseURL(null, text, "text/html", "en_US", null);
    }
    public void previousClick (View v){
        if(res-1>=0){
            res--;
        resName = "n" + res;
        String text = readRawTextFile(getBaseContext(), getResources().getIdentifier(resName, "raw", "com.tony.mymanual005"));

        webView.loadDataWithBaseURL(null, text, "text/html", "en_US", null);}
    }
    public void nextClick (View v){
        if(res+1<=1){
            res++;
            resName = "n" + res;
        String text = readRawTextFile(getBaseContext(), getResources().getIdentifier(resName, "raw", "com.tony.mymanual005"));

        webView.loadDataWithBaseURL(null, text, "text/html", "en_US", null);}
    }

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
}