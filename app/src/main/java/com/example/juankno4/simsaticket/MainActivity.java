package com.example.juankno4.simsaticket;

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
    Button btn1, btn2, btn3, btnl;
    SharedPreferences shp;
    EditText ed, pas;
    RequestQueue request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        shp = getSharedPreferences("archivo", MODE_PRIVATE);
        request = Volley.newRequestQueue(this);

        ed = findViewById(R.id.user);
        pas = findViewById(R.id.pas);
        btnl = findViewById(R.id.btn_login);

        /*String enUs = shp.getString("user", null);
        String enPassm = shp.getString("password", null);*/
        /*if (enUs != null) {
            ed.setText(enUs);
            pas.setText(enPassm);
        }*/


        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject dd = new JSONObject();
                try {
                    dd.put("NomUsuario", ed.getText().toString());
                    dd.put("PassUsuario", pas.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
// Toast.makeText(MainActivity.this,dd.toString(),Toast.LENGTH_LONG).show();

                String url = Datos.URL + "/loginAnd";
//                Toast.makeText(MainActivity.this,url.toString(),Toast.LENGTH_LONG).show();

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


                                    String nomPer = us.NomUsuario;
                                    //String nomPer = us.NomPer;
                                    Integer idPer = us.CodEmp;
                                    Integer tipoPer = us.TipoP;




                                    if (per.getCodTipoPersona()==3)
                                    {

                                        Bundle bu = new Bundle();
                                        bu.putString("nomPer",nomPer);
                                        bu.putInt("idPer",idPer);


                                        Intent emp=new Intent(MainActivity.this, Empleado.class);
                                        emp.putExtras(bu);
                                        Toast.makeText(MainActivity.this,bu.toString(), Toast.LENGTH_LONG).show();
                                        startActivity(emp);

                                    }if (per.getCodTipoPersona()==2){
                                        Bundle bu = new Bundle();
                                        bu.putString("nomPer",nomPer);
                                        bu.putInt("idPer",idPer);

                                        Intent emp=new Intent(MainActivity.this, TecnicoActivity.class);
                                        emp.putExtras(bu);
                                        Toast.makeText(MainActivity.this,bu.toString(), Toast.LENGTH_LONG).show();

                                        startActivity(emp);
                                    }if (per.getCodTipoPersona()==1){

                                        Bundle bu = new Bundle();
                                        bu.putString("nomPer",nomPer);
                                        bu.putInt("idPer",idPer);

                                        Intent emp=new Intent(MainActivity.this, Root.class);
                                        emp.putExtras(bu);
                                        Toast.makeText(MainActivity.this,bu.toString(), Toast.LENGTH_LONG).show();

                                        startActivity(emp);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

//                                Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("mensajee", error.toString());
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


                VolleyS.getInstance(getApplicationContext()).getRq().add(jor);



            }
        });


    }

}
