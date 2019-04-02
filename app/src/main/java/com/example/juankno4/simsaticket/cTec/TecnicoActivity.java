package com.example.juankno4.simsaticket.cTec;

import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.example.juankno4.simsaticket.R;

public class TecnicoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setBackgroundColor(getResources().getColor(R.color.colorNegro));

        MenuItem init = navigationView.getMenu().findItem(R.id.nav_inicio);
        SpannableString span=new SpannableString(init.getTitle());
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,span.length(),0);
        init.setTitle(span);

        MenuItem datos = navigationView.getMenu().findItem(R.id.nav_datos);
        SpannableString spandatos=new SpannableString(datos.getTitle());
        spandatos.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,spandatos.length(),0);
        datos.setTitle(spandatos);

        MenuItem seguimiento = navigationView.getMenu().findItem(R.id.nav_seguimiento);
        SpannableString spanseguimiento=new SpannableString(seguimiento.getTitle());
        spanseguimiento.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,spanseguimiento.length(),0);
        seguimiento.setTitle(spanseguimiento);
        //elementos
        MenuItem administrar = navigationView.getMenu().findItem(R.id.nav_admproblema);
        SpannableString spanadm = new SpannableString(administrar.getTitle());
        spanadm.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,spanadm.length(),0);
        administrar.setTitle(spanadm);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tecnico, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            // Handle the camera action
        } else if (id == R.id.nav_seguimiento) {

        } else if (id == R.id.nav_datos) {

        } else if (id == R.id.nav_admproblema) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
