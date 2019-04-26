package com.example.juankno4.simsaticket.cTec;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.juankno4.simsaticket.MainActivity;
import com.example.juankno4.simsaticket.Modelos.Datos;
import com.example.juankno4.simsaticket.Modelos.Personas;
import com.example.juankno4.simsaticket.Modelos.VolleyS;
import com.example.juankno4.simsaticket.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_verdatos_tecnico.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_verdatos_tecnico#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_verdatos_tecnico extends Fragment implements fragment_editardatos_tecnico.OnFragmentInteractionListener{

    private int id;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public fragment_verdatos_tecnico() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_verdatos_tecnico.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_verdatos_tecnico newInstance(String param1, String param2) {
        fragment_verdatos_tecnico fragment = new fragment_verdatos_tecnico();
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

    Button editar;
    View vista;
    TextView nombreeditT,apellidomeditT,apellidopeditT,telefonoeditT,celulareditT,correoeditT;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_fragment_verdatos_tecnico, container, false);
        editar= vista.findViewById(R.id.editar);
        nombreeditT = vista.findViewById(R.id.nombreeditT);
        apellidomeditT = vista.findViewById(R.id.apellidomeditT);
        apellidopeditT = vista.findViewById(R.id.apellidopeditT);
        telefonoeditT = vista.findViewById(R.id.telefonoeditT);
        celulareditT = vista.findViewById(R.id.celulareditT);
        correoeditT = vista.findViewById(R.id.correoeditT);



        JSONObject objeto= new JSONObject();
        try {
            objeto.put("idTec",id);
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
                            nombreeditT.setText(p.getNomEmp());
                            apellidomeditT.setText(p.getApMat());
                            apellidopeditT.setText(p.getApPat());
                            telefonoeditT.setText(p.getTelRed());
                            celulareditT.setText(p.getCelEmp());
                            correoeditT.setText(p.getEmailEmp());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        VolleyS.getInstance(getContext()).getRq().add(jsonObjectRequest);



        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment_editardatos_tecnico tecnico = fragment_editardatos_tecnico.newInstance("xx","dd");
                getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).replace(R.id.contenido,tecnico).commit();

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

    @Override
    public void onFragmentInteraction(Uri uri) {

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
