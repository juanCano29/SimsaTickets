package com.example.juankno4.simsaticket.cRoot;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.juankno4.simsaticket.R;
import com.example.juankno4.simsaticket.cTec.fragment_sidebar;

public class cRoot extends AppCompatActivity implements cfragment_sidebar_root.OnFragmentInteractionListener
{
    LinearLayout ly;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_inicio);

        despSidebarRoot();
    }

    public void despSidebarRoot()
    {
        ly = findViewById(R.id.rootcontsidebar);
        findViewById(R.id.rootbotonfrag).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ly.setEnabled(true);
                ly.setVisibility(View.VISIBLE);
                FragmentTransaction ff= getSupportFragmentManager().beginTransaction();
                fragment_sidebar f1 = fragment_sidebar.newInstance("id","ADOLFO");
                ff.replace(R.id.rootcontsidebar,f1).commit();

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }
}
