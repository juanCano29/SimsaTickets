package com.example.juankno4.simsaticket.cTec;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.juankno4.simsaticket.Modelos.Datos;
import com.example.juankno4.simsaticket.Modelos.Personas;
import com.example.juankno4.simsaticket.Modelos.VolleyS;
import com.example.juankno4.simsaticket.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_editardatos_tecnico.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_editardatos_tecnico#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_editardatos_tecnico extends Fragment {

    private int id;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public fragment_editardatos_tecnico() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_editardatos_tecnico.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_editardatos_tecnico newInstance(String param1, String param2) {
        fragment_editardatos_tecnico fragment = new fragment_editardatos_tecnico();
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
            id = getArguments().getInt("idPer",0);
        }
    }


    Button guardar;
    View v;
    Integer i;
    EditText editnombre,editmaterno,editpaterno,edittel,editcel,editemail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_fragment_editardatos_tecnico, container, false);


        editnombre = v.findViewById(R.id.editnombre);
        editpaterno= v.findViewById(R.id.editpaterno);
        editmaterno=v.findViewById(R.id.editmaterno);
        editcel=v.findViewById(R.id.editcelular);
        edittel=v.findViewById(R.id.edittelefono);
        editemail=v.findViewById(R.id.editcorreo);

        i=Datos.getPer().getId();

        JSONObject objeto= new JSONObject();
        try {
            objeto.put("idTec",i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                Datos.URL + "/datostec",
                objeto,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Gson g = new Gson();
                            Personas p = g.fromJson(response.getJSONObject("tec").toString(),Personas.class);
                            editnombre.setText(p.getNomEmp());
                            editmaterno.setText(p.getApMat());
                            editpaterno.setText(p.getApPat());
                            edittel.setText(p.getTelRed());
                            editcel.setText(p.getCelEmp());
                            editemail.setText(p.getEmailEmp());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleyS.getInstance(getContext()).getRq().add(jsonObjectRequest);

        guardar= v.findViewById(R.id.guar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject objetoid = new JSONObject();
                try {
                    objetoid.put("id",i);
                    objetoid.put("NomEmp",editnombre.getText().toString());
                    objetoid.put("ApPat",editpaterno.getText().toString());
                    objetoid.put("ApMat",editmaterno.getText().toString());
                    objetoid.put("TelRed",edittel.getText());
                    objetoid.put("CelEmp",editcel.getText());
                    objetoid.put("EmailEmp",editemail.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest json = new JsonObjectRequest(
                        Request.Method.POST,
                        Datos.URL + "/edittec",
                        objetoid,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    Gson gso = new Gson();
                                    Personas p = gso.fromJson(response.getJSONObject("edittec").getJSONObject("Persona").toString(), Personas.class);
                                    Datos.setPer(p);
                                    Toast.makeText(getContext(), "Actualizado", Toast.LENGTH_SHORT).show();

                                    fragment_verdatos_tecnico datos = fragment_verdatos_tecnico.newInstance("za","za");
                                    getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).replace(R.id.contenido,datos).commit();
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
                VolleyS.getInstance(getContext()).getRq().add(json);

            }

        });

        return v;
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
