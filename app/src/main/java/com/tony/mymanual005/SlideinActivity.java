package com.tony.mymanual005;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tony.mymanual005.fragments.fragmentContent;
import com.tony.mymanual005.fragments.fragmentDicp;
import com.tony.mymanual005.fragments.fragmentbm;

public class SlideinActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidein);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new fragmentDicp()).commit();
        toolbar.setTitle(R.string.nav_main);
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
        Intent intent;
        switch (id){
            case R.id.action_about_us:
                intent = new Intent(SlideinActivity.this,AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings:
                intent = new Intent(this, Prefs.class);
                startActivity(intent);
                break;
            case R.id.action_bookmark:

                if(item.isChecked())
                {
                    item.setIcon(R.drawable.ic_star_outline_24dp);
                    item.setChecked(false);
                }
                else {
                    item.setChecked(true);
                    item.setIcon(R.drawable.ic_grade_24dp);
                    Toast.makeText(SlideinActivity.this, "Сохранено", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getFragmentManager().getBackStackEntryCount() > 0 ){
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        if (id == R.id.nav_main) {
            transaction.replace(R.id.content_frame, new fragmentDicp());
            toolbar.setTitle(R.string.nav_main);
        } else if (id == R.id.nav_bookmark) {
            transaction.replace(R.id.content_frame, new fragmentbm());
            toolbar.setTitle(R.string.nav_bookmark);
        }else if (id == R.id.nav_asm) {
            transaction.replace(R.id.content_frame, new fragmentContent());
            toolbar.setTitle(R.string.asm);
        }
        transaction.addToBackStack(null);
        transaction.commit();

        if (id == R.id.nav_settings)
        {
            Intent intent = new Intent(this, Prefs.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
