package com.example.juankno4.simsaticket;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.juankno4.simsaticket.Modelos.VolleyS;

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

                String url = Datos.URL + "/loginAnd";

                JsonObjectRequest jor = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        dd,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


                VolleyS.getInstance(getApplicationContext()).getRq().add(jor);


                /*jor=new JsonObjectRequest(
                  //    Request.Method.POST,
                    //    Datos.URL+"/loginAn",
                      //  dd,
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
                            edi.putString("usr",us.NomUsario);
                            edi.putString("pass",pas.getText().toString());
                            edi.commit();


                             if (us.CodEmp == 1){
                                 Toast.makeText(MainActivity.this, "puto el fofo", Toast.LENGTH_SHORT).show();
                                 Intent root = new Intent(MainActivity.this, Root.class);
                                 startActivity(root);
                             } else if (us.CodEmp == 2){
                                 Toast.makeText(MainActivity.this, "puto el fofo", Toast.LENGTH_SHORT).show();
                                 Intent tec = new Intent(MainActivity.this, TecnicoActivity.class);
                                 startActivity(tec);
                             } else if (us.CodEmp == 3){
                                 Toast.makeText(MainActivity.this, "puto el fofo", Toast.LENGTH_SHORT).show();
                                 Intent emp = new Intent(MainActivity.this, Empleado.class);
                                 startActivity(emp);
                             }
                            finish();
                        } catch (JSONException e) {

                            Toast.makeText(MainActivity.this, "verifica tus datos", Toast.LENGTH_SHORT).show();
                             e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                VolleyS.getInstance(getApplicationContext()).getRq().add(jor);*/

            }
        });


    }

    public void iniciarsesion(View v) {
//        SharedPreferences shp.getSharedPreferences("user", );
        ed = findViewById(R.id.user);
        pas = findViewById(R.id.pas);
    }
}
