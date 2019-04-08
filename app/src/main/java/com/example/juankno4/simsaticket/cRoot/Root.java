package com.example.juankno4.simsaticket.cRoot;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.juankno4.simsaticket.R;

public class Root extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FragmentInicioRoot.OnFragmentInteractionListener,
        fragmenthistorialR.OnFragmentInteractionListener,AgregarEmpleadoFragment.OnFragmentInteractionListener,AgregarEquipoFragment.OnFragmentInteractionListener,
        AgregarUsuarioFragment.OnFragmentInteractionListener
{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
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




        MenuItem inir = navigationView.getMenu().findItem(R.id.inicioroot);
        SpannableString span=new SpannableString(inir.getTitle());
        span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,span.length(),0);
        inir.setTitle(span);
        MenuItem hist = navigationView.getMenu().findItem(R.id.historialroot);
        SpannableString ss=new SpannableString(hist.getTitle());
        ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,ss.length(),0);
        hist.setTitle(ss);
        MenuItem comm = navigationView.getMenu().findItem(R.id.rootpanel);
        SpannableString xxx=new SpannableString(comm.getTitle());
        xxx.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorPrimary)),0,xxx.length(),0);
        comm.setTitle(xxx);
        MenuItem commm = navigationView.getMenu().findItem(R.id.id_agreg);
        SpannableString xxxx=new SpannableString(commm.getTitle());
        xxxx.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,xxxx.length(),0);
        commm.setTitle(xxxx);
        MenuItem co = navigationView.getMenu().findItem(R.id.add_emp);
        SpannableString xx=new SpannableString(co.getTitle());
        xx.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,xx.length(),0);
        co.setTitle(xx);
        MenuItem coo = navigationView.getMenu().findItem(R.id.add_user);
        SpannableString x=new SpannableString(coo.getTitle());
        x.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,x.length(),0);
        coo.setTitle(x);
        MenuItem coa = navigationView.getMenu().findItem(R.id.add_equipo);
        SpannableString xvx=new SpannableString(coa.getTitle());
        xvx.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorBlanco)),0,xvx.length(),0);
        coa.setTitle(xvx);


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
        getMenuInflater().inflate(R.menu.root, menu);
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

        if (id == R.id.inicioroot) {
            FragmentInicioRoot fri = FragmentInicioRoot.newInstance("xx","ss");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.pantallaR,fri)
                    .commit();
            // Handle the camera action
        } else if (id == R.id.historialroot) {
            fragmenthistorialR fhr = fragmenthistorialR.newInstance("xx","ss");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.pantallaR,fhr)
                    .commit();

        } else if (id == R.id.add_emp){

            AgregarEmpleadoFragment aef = AgregarEmpleadoFragment.newInstance("xx","ss");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.pantallaR,aef)
                    .commit();
        } else if (id == R.id.add_equipo){
            AgregarEquipoFragment af = AgregarEquipoFragment.newInstance("xx","ss");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.pantallaR, af)
                    .commit();
        }else if (id == R.id.add_user){
            AgregarUsuarioFragment auf = AgregarUsuarioFragment.newInstance("xx","ss");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.pantallaR, auf)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
