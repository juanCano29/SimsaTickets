package com.example.juankno4.simsaticket.cEmp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VerDatosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VerDatosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerDatosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    Button submit_edit;
    TextView text_empNombre,text_empAp,text_empAm,text_empTR,text_empCel,text_empMail;

    private OnFragmentInteractionListener mListener;

    public VerDatosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerDatosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VerDatosFragment newInstance(String param1, String param2) {
        VerDatosFragment fragment = new VerDatosFragment();
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
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_ver_datos, container, false);
        submit_edit = vista.findViewById(R.id.submit_edit);
        text_empNombre = vista.findViewById(R.id.text_empNombre);
        text_empAp = vista.findViewById(R.id.text_empAp);
        text_empAm = vista.findViewById(R.id.text_empAm);
        text_empTR = vista.findViewById(R.id.text_empTR);
        text_empCel = vista.findViewById(R.id.text_empCel);
        text_empMail = vista.findViewById(R.id.text_empMail);

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
                             text_empNombre.setText(per.getNomEmp());
                             text_empAp.setText(per.getApPat());
                             text_empAm.setText(per.getApMat());
                             text_empTR.setText(per.getTelRed());
                             text_empCel.setText(per.getCelEmp());
                             text_empMail.setText(per.getEmailEmp());
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Problemas al obtener la informacion...", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), "Problemas al obtener la informacion...", Toast.LENGTH_SHORT).show();
                    }
                });

        VolleyS.getInstance(getContext()).getRq().add(jor);



        submit_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActualizarDatosFragment adf = ActualizarDatosFragment.newInstance("xx","ss");
                 getFragmentManager()
                        .beginTransaction()
                         .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                        .replace(R.id.pantalla,adf)
                        .commit();
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
