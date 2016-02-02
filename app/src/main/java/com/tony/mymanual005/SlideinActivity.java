package com.tony.mymanual005;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tony.mymanual005.fragments.fragmentDicp;
import com.tony.mymanual005.fragments.fragmentbm;

public class SlideinActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener {

    private fragmentDicp fragmentdicp = new fragmentDicp();
    private fragmentbm fragmentbm = new fragmentbm();

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidein);


         toolbar = (Toolbar) findViewById(R.id.toolbar);
       /* setSupportActionBar(toolbar); Вызывает пустой метод*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


 /*   private void setSupportActionBar(Toolbar toolbar) {
    } я*/


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Решить проблему с созданим меню ...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_about_us:
                Intent intent = new Intent(SlideinActivity.this,AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings: break;
                   }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        if (id == R.id.nav_discipline) {
            ftrans.replace(R.id.container,fragmentdicp);
            toolbar.setTitle(R.string.nav_discipline);
        } else if (id == R.id.nav_bookmark) {
            ftrans.replace(R.id.container, fragmentbm);
            toolbar.setTitle(R.string.nav_bookmark);
        }
        ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    
}
