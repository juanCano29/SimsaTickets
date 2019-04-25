package com.example.juankno4.simsaticket.cTec;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.juankno4.simsaticket.Modelos.AdministrarTec;
import com.example.juankno4.simsaticket.Modelos.Datos;
import com.example.juankno4.simsaticket.Modelos.Personas;
import com.example.juankno4.simsaticket.Modelos.Problemas;
import com.example.juankno4.simsaticket.Modelos.VolleyS;
import com.example.juankno4.simsaticket.R;
import com.example.juankno4.simsaticket.adaptadores.adaptaProblemaTec;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_problemas_tecnico.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_problemas_tecnico#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_problemas_tecnico extends Fragment implements fragment_actualizar_tecnico.OnFragmentInteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public fragment_problemas_tecnico() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_problemas_tecnico.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_problemas_tecnico newInstance(String param1, String param2) {
        fragment_problemas_tecnico fragment = new fragment_problemas_tecnico();
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
    Button ver;
    View vista;
    RecyclerView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista= inflater.inflate(R.layout.fragment_fragment_problemas_tecnico, container, false);
        ver= vista.findViewById(R.id.ver);

        lista = vista.findViewById(R.id.lista);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",Datos.getPer().getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                Datos.URL + "/histec",
                jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Type ProblemaList = new TypeToken<List<AdministrarTec>>(){}.getType();

                        List<AdministrarTec> problemas = new Gson().fromJson(response.toString(),ProblemaList);

                        lista.setLayoutManager(new LinearLayoutManager(getContext()));
                        lista.setAdapter(new adaptaProblemaTec(problemas));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleyS.getInstance(getContext()).getRq().add(jsonArrayRequest);




        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_actualizar_tecnico actualizar_tecnico= fragment_actualizar_tecnico.newInstance("ss","aa");
                getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).replace(R.id.contenido,actualizar_tecnico).commit();
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
