package com.example.juankno4.simsaticket.cEmp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
import android.view.View;
import android.widget.TextView;

import com.example.juankno4.simsaticket.MainActivity;
import com.example.juankno4.simsaticket.Modelos.Datos;
import com.example.juankno4.simsaticket.R;

public class Empleado extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, fragmentHistorial.OnFragmentInteractionListener,
        FragmentInicioEmpleado.OnFragmentInteractionListener, RegistrarProblemaFragment.OnFragmentInteractionListener,
        VerDatosFragment.OnFragmentInteractionListener, ActualizarDatosFragment.OnFragmentInteractionListener {

    //          TextView principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setBackgroundColor(getResources().getColor(R.color.colorNegro));

        MenuItem comm = navigationView.getMenu().findItem(R.id.cominuca);
        SpannableString xx = new SpannableString(comm.getTitle());
        xx.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimary)), 0, xx.length(), 0);
        comm.setTitle(xx);


        View encabezado = navigationView.getHeaderView(0);
        TextView nombre = (TextView) encabezado.findViewById(R.id.NomEmp);
        nombre.setText(Datos.getPer().getNomEmp());


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
        // Handle action bar item clicks in kiko. The action bar will
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
            FragmentInicioEmpleado i = FragmentInicioEmpleado.newInstance("xx", "ss");
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.pantalla, i)
                    .commit();
        } else if (id == R.id.historial) {
            fragmentHistorial x = fragmentHistorial.newInstance("xx", "ss");
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                    .replace(R.id.pantalla, x)
                    .commit();
        } else if (id == R.id.registrar) {
            RegistrarProblemaFragment rpf = RegistrarProblemaFragment.newInstance("xx", "ss");
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                    .replace(R.id.pantalla, rpf)
                    .commit();

        } else if (id == R.id.datos) {
            VerDatosFragment vdf = VerDatosFragment.newInstance("xx", "ss");
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                    .replace(R.id.pantalla, vdf)
                    .commit();

        } else if (id == R.id.Cerrar) {
            Intent ss = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(ss);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
