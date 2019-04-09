package com.example.juankno4.simsaticket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.juankno4.simsaticket.Modelos.Datos;
import com.example.juankno4.simsaticket.Modelos.Usuario;
import com.example.juankno4.simsaticket.cEmp.Empleado;
import com.example.juankno4.simsaticket.cRoot.Root;
import com.example.juankno4.simsaticket.cTec.TecnicoActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
 Button btn1, btn2, btn3, btnl;
 EditText ed,pas;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed=findViewById(R.id.user);
        pas=findViewById(R.id.pas);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btnl=findViewById(R.id.btn_login);

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
        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject dd = new JSONObject();
                try {
                    dd.put("user",ed.getText().toString());
                    dd.put("pass",pas.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jor=new JsonObjectRequest(
                        Request.Method.POST,
                        Datos.URL+"/loginAnd",
                        dd,
                        new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                // kiko este apartado es lo modificado del shared preference
                        // para que no le muevas weeeeey

                        try {
                            Gson gson = new Gson();
                            Usuario us= gson.fromJson(response.getJSONObject("datos").getJSONObject("user").toString(), Usuario.class);
                            Datos.usuario=us;
                            SharedPreferences shp = getSharedPreferences("user", Context.MODE_PRIVATE);

                            SharedPreferences.Editor edi=shp.edit();
                            edi.putString("email",ed.getText().toString());
                            edi.putString("pass",pas.getText().toString());
                            edi.commit();


                             if (us.CodEmp == 1){
                                 Intent root = new Intent(MainActivity.this, Root.class);
                                 startActivity(root);
                             } else if (us.CodEmp == 2){
                                 Intent tec = new Intent(MainActivity.this, TecnicoActivity.class);
                                 startActivity(tec);
                             } else if (us.CodEmp == 3){
                                 Intent emp = new Intent(MainActivity.this, Empleado.class);
                                 startActivity(emp);
                             }
                            finish();

                        } catch (JSONException e) {

                            Toast.makeText(MainActivity.this, "verifica tus datos", Toast.LENGTH_SHORT).show();
                            /* e.printStackTrace();*/
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
