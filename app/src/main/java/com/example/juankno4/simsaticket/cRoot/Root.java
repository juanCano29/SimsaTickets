package com.example.juankno4.simsaticket.cRoot;

import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.juankno4.simsaticket.R;

public class Root extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FragmentInicioRoot.OnFragmentInteractionListener,
        fragmenthistorialR.OnFragmentInteractionListener,AgregarEmpleadoFragment.OnFragmentInteractionListener,AgregarEquipoFragment.OnFragmentInteractionListener,
        AgregarUsuarioFragment.OnFragmentInteractionListener
{

    TextView nomprincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        nomprincipal =(TextView) findViewById(R.id.nomprincipal);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setBackgroundColor(getResources().getColor(R.color.colorNegro));

        Bundle buu = new Bundle();
        buu.getString("nomPer");
        buu.getInt("idPer");
        //nomprincipal.setText(buu.getBundle("nomPer"));
        nomprincipal.setText("Bienvenido " + buu.getString("nomPer"));

        Toast.makeText(Root.this,buu.getString("nomPer"), Toast.LENGTH_LONG).show();


        MenuItem comm = navigationView.getMenu().findItem(R.id.rootpanel);
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
