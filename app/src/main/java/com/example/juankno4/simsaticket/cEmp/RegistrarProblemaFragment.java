package com.example.juankno4.simsaticket.cEmp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.juankno4.simsaticket.Modelos.Datos;
import com.example.juankno4.simsaticket.Modelos.EquipoTrabajo;
import com.example.juankno4.simsaticket.Modelos.VolleyS;
import com.example.juankno4.simsaticket.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrarProblemaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrarProblemaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarProblemaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText edit_desc;
    View vista;

    private OnFragmentInteractionListener mListener;

    public RegistrarProblemaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarProblemaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarProblemaFragment newInstance(String param1, String param2) {
        RegistrarProblemaFragment fragment = new RegistrarProblemaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    Button submit_ip;
    Spinner spin_pdp, spin_tipo, spin_eqt;
    EditText edit_fecha;
    DatePickerDialog.OnDateSetListener fechaescucha;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_registrar_problema, container, false);
        spin_pdp = (Spinner) vista.findViewById(R.id.spin_pdp);
        spin_tipo = (Spinner) vista.findViewById(R.id.spin_tipo);
        spin_eqt = (Spinner) vista.findViewById(R.id.spin_eqt);
        edit_desc = vista.findViewById(R.id.edit_desc);
        edit_fecha = vista.findViewById(R.id.edit_fecha);
        submit_ip = vista.findViewById(R.id.submit_ip);
        String prioridad[] = {"Baja", "Media", "Alta"};
        String tipoprob[] = {"Hardware", "Software", "Desconocido"};
        spin_pdp.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, prioridad));
        spin_tipo.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipoprob));

        edit_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int año = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogo = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, fechaescucha, año, mes, dia);

                dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogo.show();
            }
        });

        fechaescucha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String fecha = dayOfMonth + "-" + month + "-" + year;
                edit_fecha.setText(fecha);
            }
        };

        JSONObject eq = new JSONObject();
        try {
            eq.put("id", Datos.getPer().getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jor = new JsonObjectRequest(
                Request.Method.POST,
                Datos.URL + "/EquipoEmp",
                eq,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray Equipos = response.getJSONArray("Equip");
                            String[] Dessc = new String[Equipos.length()];
                            Gson gson = new Gson();
                            for (int i = 0; i < Equipos.length(); i++) {
                                EquipoTrabajo equipoTrabajo = gson.fromJson(Equipos.get(i).toString(), EquipoTrabajo.class);
                                Datos.equipoTrabajo = equipoTrabajo;
                                Dessc[i] = equipoTrabajo.getDescripcion();
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, Dessc);
                            spin_eqt.setAdapter(adapter);


                        } catch (JSONException e) {

                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        VolleyS.getInstance(getContext()).getRq().add(jor);


        submit_ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject dd = new JSONObject();
                try {
                    dd.put("CodEqTrab", Datos.equipoTrabajo.getId());
                    dd.put("NotaProblema", edit_desc.getText().toString());
                    dd.put("prioridad", spin_pdp.getSelectedItem());
                    String valorcod = (String) spin_tipo.getSelectedItem();
                    if (valorcod == "Hardware") {
                        dd.put("CodTipoProblema", 500);
                    }
                    if (valorcod == "Software") {
                        dd.put("CodTipoProblema", 501);
                    }
                    if (valorcod == "Desconocido") {
                        dd.put("CodTipoProblema", 502);
                    }

                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Por favor, llena los campos necesarios", Toast.LENGTH_LONG).show();
                }

                JsonObjectRequest jor2 = new JsonObjectRequest(
                        Request.Method.POST,
                        Datos.URL + "/regprob",
                        dd,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("pifi", response.toString());
                                try {
                                    Toast.makeText(getContext(), response.getJSONObject("prob").get("Mensaje").toString(), Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                VolleyS.getInstance(getContext()).getRq().add(jor2);
                edit_desc.getText().clear();
            }

        });
        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
