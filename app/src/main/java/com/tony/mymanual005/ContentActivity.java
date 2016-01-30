package com.tony.mymanual005;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_about_us:
                Intent intent = new Intent(ContentActivity.this,AboutUsActivity.class);
                startActivity(intent);
                break;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // Обрабатываем переход на след/пред тему
    public void previousClick (View v){
        if(res-1>=0){
            res--;
        resName = "n" + res;
        String text = readRawTextFile(getBaseContext(), getResources().getIdentifier(resName, "raw", "com.tony.mymanual005"));
        webView.loadDataWithBaseURL(null, text, "text/html", "en_US", null);
        }else if (res-1==-1){
            onBackPressed();
            /*Intent intent = new Intent(ContentActivity.this,MainActivity.class);
            startActivity(intent);*/
        }
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