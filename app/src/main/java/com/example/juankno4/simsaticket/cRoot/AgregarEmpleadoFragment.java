package com.example.juankno4.simsaticket.cRoot;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    Spinner spitipo, spidep;

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
        //edit_rootTiPer = vista.findViewById(R.id.tper);
        //edit_rootDepa = vista.findViewById(R.id.dep);
        btnroot_addPer = vista.findViewById(R.id.btnroot_addPer);


        spitipo = vista.findViewById(R.id.tper);
        String[] arraytipop = {"Root","Tecnico","Empleado"};
        spitipo.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,arraytipop));


        spidep = vista.findViewById(R.id.dep);
        String[] arraydep = {"Contabilidad","Finanzas","Bancos", "Juridico",  "Recursos Humanos"};
        spidep.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,arraydep));


        btnroot_addPer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(getContext(),"ENTRO AL ONCLICK", Toast.LENGTH_LONG).show();
                JSONObject dd = new JSONObject();
                //Toast.makeText(getContext(),"ENTRO AL OBJECT", Toast.LENGTH_LONG).show();
                try {

                    dd.put("NomEmp",edit_rootNombre.getText().toString());
                    dd.put("ApPat",edit_RootaP.getText().toString());
                    dd.put("ApMat",edit_RootaM.getText().toString());
                    dd.put("TelRed",edit_RoottelR.getText().toString());
                    dd.put("CelEmp",edit_Rootcel.getText().toString());
                    dd.put("EmailEmp",edit_Rootcorr.getText().toString());

                    String valuespi = (String) spitipo.getSelectedItem();
                    if (valuespi == "Root")
                    {
                        dd.put("CodTipoPersona",1);
                    }
                    if (valuespi == "Tecnico")
                    {
                        dd.put("CodTipoPersona",2);
                    }
                    if (valuespi == "Empleado")
                    {
                        dd.put("CodTipoPersona",3);
                    }



                    String valuespidep = (String) spidep.getSelectedItem();
                    if (valuespidep == "Contabilidad")
                    {
                        dd.put("CodDepa", 1);
                    }
                    if (valuespidep == "Finanzas")
                    {
                        dd.put("CodDepa", 2);
                    }
                    if (valuespidep == "Bancos")
                    {
                        dd.put("CodDepa", 3);
                    }
                    if (valuespidep == "Juridico")
                    {
                        dd.put("CodDepa", 4);
                    }
                    if (valuespidep == "Recursos Humanos")
                    {
                        dd.put("CodDepa", 5);
                    }

                } catch (JSONException e)
                {
                    Toast.makeText(getContext(),"Por favor, llena los campos necesarios", Toast.LENGTH_LONG).show();

                }



                JsonObjectRequest jor= new JsonObjectRequest(
                        Request.Method.POST,
                        Datos.URL + "/insper",
                        dd,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                Toast.makeText(getContext(),"Se registró correctamente", Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getContext(),"Porfavor, llena los campos Necesarios", Toast.LENGTH_LONG).show();
                    }
                }

                );
                VolleyS.getInstance(getContext()).getRq().add(jor);
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
