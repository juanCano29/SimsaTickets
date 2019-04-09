package com.example.juankno4.simsaticket.cTec;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
        implements NavigationView.OnNavigationItemSelectedListener, fragment_inicio_tecnico.OnFragmentInteractionListener,fragment_verdatos_tecnico.OnFragmentInteractionListener,
        fragment_editardatos_tecnico.OnFragmentInteractionListener, fragment_problemas_tecnico.OnFragmentInteractionListener,fragment_seguimiento_tecnico.OnFragmentInteractionListener, fragment_actualizar_tecnico.OnFragmentInteractionListener{

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

        MenuItem dato = navigationView.getMenu().findItem(R.id.nav_datos);
        SpannableString spandato=new SpannableString(dato.getTitle());
        spandato.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,spandato.length(),0);
        dato.setTitle(spandato);

        MenuItem seg = navigationView.getMenu().findItem(R.id.nav_seguimiento);
        SpannableString spanseg=new SpannableString(seg.getTitle());
        spanseg.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,spanseg.length(),0);
        seg.setTitle(spanseg);

        MenuItem adm = navigationView.getMenu().findItem(R.id.nav_admproblema);
        SpannableString spanadm=new SpannableString(adm.getTitle());
        spanadm.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,spanadm.length(),0);
        adm.setTitle(spanadm);
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
            Fragment inicio = new fragment_inicio_tecnico();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.contenido,inicio);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_seguimiento) {

            Fragment seguimiento = new fragment_seguimiento_tecnico();
            FragmentTransaction transactions = getSupportFragmentManager().beginTransaction();
            transactions.replace(R.id.contenido,seguimiento);
            transactions.commit();


        } else if (id == R.id.nav_datos) {
            Fragment datos = new fragment_verdatos_tecnico();
            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.contenido,datos);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_admproblema) {

            Fragment problema = new fragment_problemas_tecnico();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contenido,problema);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public TecnicoActivity() {
        super();
    }
}
