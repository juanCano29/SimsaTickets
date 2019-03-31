package com.example.juankno4.simsaticket.cEmp;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.juankno4.simsaticket.R;

public class Empleado extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,fragmentHistorial.OnFragmentInteractionListener,
        FragmentInicioEmpleado.OnFragmentInteractionListener
      {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setBackgroundColor(getResources().getColor(R.color.colorNegro));
//        cambiar el color del titulo inicio
        MenuItem init = navigationView.getMenu().findItem(R.id.inicio);
        SpannableString span=new SpannableString(init.getTitle());
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,span.length(),0);
        init.setTitle(span);
//        cambiar el color del titulo historial
        MenuItem hist = navigationView.getMenu().findItem(R.id.historial);
        SpannableString spa=new SpannableString(hist.getTitle());
        spa.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,spa.length(),0);
        hist.setTitle(spa);
//        cambiar el color del titulo registro
        MenuItem reg = navigationView.getMenu().findItem(R.id.registrar);
        SpannableString x=new SpannableString(reg.getTitle());
        x.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,x.length(),0);
        reg.setTitle(x);
//        cambiar el color del titulo ver datos
        MenuItem datos = navigationView.getMenu().findItem(R.id.datos);
        SpannableString ss=new SpannableString(datos.getTitle());
        ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,ss.length(),0);
        datos.setTitle(ss);
//        experimentando

        MenuItem comm = navigationView.getMenu().findItem(R.id.cominuca);
        SpannableString xxx=new SpannableString(comm.getTitle());
        xxx.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorPrimary)),0,xxx.length(),0);
        comm.setTitle(xxx);

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
        getMenuInflater().inflate(R.menu.empleado, menu);
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

        if (id == R.id.inicio) {
            FragmentInicioEmpleado i= FragmentInicioEmpleado.newInstance("xx","ss");
            getSupportFragmentManager().beginTransaction().replace(R.id.pantalla,i).commit();
        } else if (id == R.id.historial) {
            fragmentHistorial x=fragmentHistorial.newInstance("xx","ss");
            getSupportFragmentManager().beginTransaction().replace(R.id.pantalla,x).commit();
        } else if (id == R.id.registrar) {
            RegistrarProblemaFragment rpf = RegistrarProblemaFragment.newInstance("xx","ss");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.pantalla,rpf)
                    .commit();

        } else if (id == R.id.datos) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
