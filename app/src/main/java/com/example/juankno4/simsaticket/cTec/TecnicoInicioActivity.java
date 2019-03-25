package com.example.juankno4.simsaticket.cTec;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.juankno4.simsaticket.R;

public class TecnicoInicioActivity extends AppCompatActivity implements fragment_sidebar.OnFragmentInteractionListener
{
    LinearLayout ly;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnico_inicio);

        ly = findViewById(R.id.contsidebar);
        fragment_sidebar sideBar = new fragment_sidebar();
        findViewById(R.id.botonfrag).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ly.setEnabled(true);
                ly.setVisibility(View.VISIBLE);
                FragmentTransaction ff= getSupportFragmentManager().beginTransaction();
                fragment_sidebar f1 = fragment_sidebar.newInstance("id","ADOLFO");
                ff.replace(R.id.contsidebar,f1).commit();

            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }
}
