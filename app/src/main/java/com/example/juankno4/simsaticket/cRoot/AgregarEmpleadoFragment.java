package com.example.juankno4.simsaticket.cRoot;

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
 * {@link AgregarEmpleadoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgregarEmpleadoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarEmpleadoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vista;
    EditText edit_rootNombre,edit_RootaP,edit_RootaM,edit_RoottelR,edit_Rootcel,edit_Rootcorr,edit_rootTiPer,edit_rootDepa;
    Button btnroot_addPer;

    private OnFragmentInteractionListener mListener;

    public AgregarEmpleadoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarEmpleadoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarEmpleadoFragment newInstance(String param1, String param2) {
        AgregarEmpleadoFragment fragment = new AgregarEmpleadoFragment();
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
                             Bundle savedInstanceState)
    {

        vista = inflater.inflate(R.layout.fragment_agregar_empleado, container, false);

        edit_rootNombre = vista.findViewById(R.id.nom);
        edit_RootaP = vista.findViewById(R.id.apeP);
        edit_RootaM = vista.findViewById(R.id.apeM);
        edit_RoottelR = vista.findViewById(R.id.telR);
        edit_Rootcel = vista.findViewById(R.id.telP);
        edit_Rootcorr = vista.findViewById(R.id.mail);
        edit_rootTiPer = vista.findViewById(R.id.tper);
        edit_rootDepa = vista.findViewById(R.id.dep);




        btnroot_addPer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                JSONObject dd = new JSONObject();

                try
                {
                    dd.put("nom",edit_rootNombre.getText().toString());
                    dd.put("apeP",edit_RootaP.getText().toString());
                    dd.put("apeM",edit_RootaM.getText().toString());
                    dd.put("telR",edit_RoottelR.getText().toString());
                    dd.put("telP",edit_Rootcel.getText().toString());
                    dd.put("mail",edit_Rootcorr.getText().toString());
                    dd.put("tper",edit_rootTiPer.getText().toString());
                    dd.put("dep",edit_rootDepa.getText().toString());

                } catch (JSONException e)
                {
                    Toast.makeText(getContext(), "HOUSTON TENEMOS UN PROBLEMA", Toast.LENGTH_SHORT).show();
                }

                JsonObjectRequest objinsper = new JsonObjectRequest(

                        Request.Method.POST,
                        Datos.URL + "/insper",
                        dd,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {


                                try {
                                    Gson gso = new Gson();
                                    Personas p = gso.fromJson(response.getJSONObject("Act").getJSONObject("Persona").toString(), Personas.class);
                                    edit_rootNombre.setText(p.getNomEmp());
                                    edit_RootaP.setText(p.getApPat());
                                    edit_RootaM.setText(p.getApMat());
                                    edit_RoottelR.setText(p.getTelRed());
                                    edit_Rootcel.setText(p.getCelEmp());
                                    edit_Rootcorr.setText(p.getEmailEmp());
                                    edit_rootTiPer.setText(p.getEmailEmp());
                                    edit_rootDepa.setText(p.getEmailEmp());

                                    Toast.makeText(getContext(), edit_rootNombre.getText() + " se ha agregado Correctamente", Toast.LENGTH_SHORT).show();
                                }
                                catch (JSONException e)
                                {
                                    Toast.makeText(getContext(), "HOUSTON TENEMOS UN PROBLEMA", Toast.LENGTH_SHORT).show();
                                    //putos
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(getContext(), "HOUSTON TENEMOS UN PROBLEMA", Toast.LENGTH_SHORT).show();

                            }
                        }


                );
                    VolleyS.getInstance(getContext()).getRq().add(objinsper);
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
