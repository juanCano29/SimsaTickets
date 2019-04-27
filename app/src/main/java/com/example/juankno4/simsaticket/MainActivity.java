package com.example.juankno4.simsaticket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.juankno4.simsaticket.Modelos.Datos;
import com.example.juankno4.simsaticket.Modelos.Personas;
import com.example.juankno4.simsaticket.Modelos.Usuario;
import com.example.juankno4.simsaticket.Modelos.VolleyS;
import com.example.juankno4.simsaticket.cEmp.Empleado;
import com.example.juankno4.simsaticket.cRoot.Root;
import com.example.juankno4.simsaticket.cTec.TecnicoActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    Button btnl;
    EditText ed, pas;
    RequestQueue request;
    Context conx=MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        request = Volley.newRequestQueue(this);

        ed = findViewById(R.id.user);
        pas = findViewById(R.id.pas);
        btnl = findViewById(R.id.btn_login);


        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject dd = new JSONObject();
                try {
                    dd.put("NomUsuario", ed.getText().toString());
                    dd.put("PassUsuario", pas.getText().toString());
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this,"Tenemos problemas técnicos... Lo sentimos", Toast.LENGTH_LONG).show();
                }

                String url = Datos.URL + "/loginAnd";

                JsonObjectRequest jor = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        dd,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("response", response.toString());
                                try {
                                    Gson gson = new Gson();
                                    Personas per = gson.fromJson(response.getJSONObject("datos").getJSONObject("persona").toString(), Personas.class);
                                    Usuario us = gson.fromJson(response.getJSONObject("datos").getJSONObject("user").toString(), Usuario.class);

                                    Datos.setUsm(us);
                                    Datos.setPer(per);
                                    String nomPer = per.NomEmp;
                                    //String nomPer = us.NomPer;
                                    Integer idPer = per.id;
                                    Bundle bu = new Bundle();
                                    Intent intent=new Intent();


                                    switch (per.getCodTipoPersona()){
                                        case 1:
                                            bu.putString("nomPer",nomPer);
                                            bu.putInt("idPer",idPer);

                                            intent.setClass(conx,Root.class);
                                            intent.putExtras(bu);
                                            startActivity(intent);
                                            break;
                                        case 2:
                                            bu.putString("nomPer",nomPer);
                                            bu.putInt("idPer",idPer);

                                            intent.setClass(conx,TecnicoActivity.class);
                                            intent.putExtras(bu);
                                            startActivity(intent);
                                            break;
                                        case 3:
                                            bu.putString("nomPer",nomPer);
                                            bu.putInt("idPer",idPer);


                                            intent.setClass(conx,Empleado.class);
                                            intent.putExtras(bu);
                                            startActivity(intent);
                                            break;
                                    }

                                    finish();

                                } catch (JSONException e)
                                {
                                    Toast.makeText(MainActivity.this,"USUARIO O CONTRASEÑA INCORRECTOS", Toast.LENGTH_LONG).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("mensajee", error.toString());
                        Toast.makeText(MainActivity.this,"PROBLEMAS DE CONEXION...", Toast.LENGTH_LONG).show();
                    }
                });


                VolleyS.getInstance(getApplicationContext()).getRq().add(jor);



            }
        });


    }

}
