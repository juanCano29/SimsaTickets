package com.example.juankno4.simsaticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.juankno4.simsaticket.cEmp.activity_empleado_inicio;
import com.example.juankno4.simsaticket.cTec.TecnicoInicioActivity;

public class MainActivity extends AppCompatActivity {
 Button btn2,btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn2 = findViewById(R.id.btn2);
        btn1=findViewById(R.id.btn1);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent li = new Intent(MainActivity.this,TecnicoInicioActivity.class);
                startActivity(li);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x=new Intent(MainActivity.this, activity_empleado_inicio.class);
                startActivity(x);
            }
        });

    }
}
