package com.example.juankno4.simsaticket.cEmp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
 * {@link ActualizarDatosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActualizarDatosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActualizarDatosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    EditText edit_empNombre,edit_empAp,edit_empAm,edit_empTR,edit_empCel,edit_empMail;
    Button submit_reload;
    private OnFragmentInteractionListener mListener;

    public ActualizarDatosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActualizarDatosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActualizarDatosFragment newInstance(String param1, String param2) {
        ActualizarDatosFragment fragment = new ActualizarDatosFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_actualizar_datos, container, false);


        edit_empNombre = vista.findViewById(R.id.edit_empNombre);
        edit_empAp = vista.findViewById(R.id.edit_empAp);
        edit_empAm = vista.findViewById(R.id.edit_empAm);
        edit_empTR = vista.findViewById(R.id.edit_empTR);
        edit_empCel = vista.findViewById(R.id.edit_empCel);
        edit_empMail = vista.findViewById(R.id.edit_empMail);
        submit_reload = vista.findViewById(R.id.submit_reload);

        JsonObjectRequest jor = new JsonObjectRequest(
                Request.Method.GET,
                Datos.URL + "/mostrar",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Gson gson = new Gson();
                            Personas per = gson.fromJson(response.getJSONObject("info").getJSONObject("person").toString(),Personas.class);
                            edit_empNombre.setText(per.getNomEmp());
                            edit_empAp.setText(per.getApPat());
                            edit_empAm.setText(per.getApMat());
                            edit_empTR.setText(per.getTelRed());
                            edit_empCel.setText(per.getCelEmp());
                            edit_empMail.setText(per.getEmailEmp());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        VolleyS.getInstance(getContext()).getRq().add(jor);



        submit_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject dd = new JSONObject();

                try {
                    dd.put("NomEmp",edit_empNombre.getText().toString());
                    dd.put("ApPat",edit_empAp.getText().toString());
                    dd.put("ApMat",edit_empAm.getText().toString());
                    dd.put("TelRed",edit_empTR.getText().toString());
                    dd.put("CelEmp",edit_empCel.getText().toString());
                    dd.put("EmailEmp",edit_empMail.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jor = new JsonObjectRequest(
                        Request.Method.POST,
                        Datos.URL + "/actualizar",
                        dd,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {


                                try {
                                    Gson gso = new Gson();
                                    Personas p = gso.fromJson(response.getJSONObject("Act").getJSONObject("Persona").toString(), Personas.class);
                                    edit_empNombre.setText(p.getNomEmp());
                                    edit_empAp.setText(p.getApPat());
                                    edit_empAm.setText(p.getApMat());
                                    edit_empTR.setText(p.getTelRed());
                                    edit_empCel.setText(p.getCelEmp());
                                    edit_empMail.setText(p.getEmailEmp());
                                    Toast.makeText(getContext(), "Actualizado", Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(),error.toString(), Toast.LENGTH_SHORT).show();

                            }
                        });

                                        VolleyS.getInstance(getContext()).getRq().add(jor);
            }});




        // Inflate the layout for this fragment
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
