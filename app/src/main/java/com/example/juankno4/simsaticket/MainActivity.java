package com.example.juankno4.simsaticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.juankno4.simsaticket.cEmp.Empleado;
import com.example.juankno4.simsaticket.cTec.TecnicoActivity;
import com.example.juankno4.simsaticket.cRoot.Root;


public class MainActivity extends AppCompatActivity {
 Button btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                  Intent in = new Intent(MainActivity.this, Root.class);
                    startActivity(in);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent li = new Intent(MainActivity.this, TecnicoActivity.class);
                startActivity(li);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent lii = new Intent(MainActivity.this, Empleado.class);
                startActivity(lii);
            }
        });


    }
}
