package com.example.juankno4.simsaticket.cRoot;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.juankno4.simsaticket.Modelos.Datos;
import com.example.juankno4.simsaticket.Modelos.Personas;
import com.example.juankno4.simsaticket.Modelos.VolleyS;
import com.example.juankno4.simsaticket.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AgregarUsuarioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgregarUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarUsuarioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    JSONObject dd = new JSONObject();




    View vista;
    AutoCompleteTextView autoCompleteTextView;
    String[] Dessc;
    Integer[] arrayid;
    Personas equipoTrabajo, idper, variable, mm;
    Button btnr_saveusr;
    EditText editr_usr, editr_pass;
    ArrayList<String> listaPersonas;
    ArrayList<Personas> PersonasList;
    Integer auto;

    private OnFragmentInteractionListener mListener;

    public AgregarUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarUsuarioFragment newInstance(String param1, String param2) {
        AgregarUsuarioFragment fragment = new AgregarUsuarioFragment();
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
        vista  = inflater.inflate(R.layout.fragment_agregar_usuario, container, false);
        autoCompleteTextView = vista.findViewById(R.id.autoCompleteTextView);
        btnr_saveusr = vista.findViewById(R.id.btnr_saveusr);
        editr_usr = vista.findViewById(R.id.editr_usr);
        editr_pass = vista.findViewById(R.id.editr_pass);


        //recibo yodo el modelo y lo guardo, lo igualo al spinner, ya sea el array o lo que apetezca
        //String[] arraypers = {"Android ","java","IOS","SQL","JDBC","Web services"};
        //ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,arraypers);
        //autoCompleteTextView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,arraypers));
        //autoCompleteTextView.setAdapter(adapter);
        //autoCompleteTextView.setThreshold(1);

        JsonObjectRequest json = new JsonObjectRequest
                (
                        Request.Method.GET,
                        Datos.URL + "/allper",
                        null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                try
                                {
                                    final JSONObject Perss = response.getJSONObject("info");
                                    JSONArray pp = response.getJSONObject("info").getJSONArray("Persona");
                                    Datos.per.getId();
                                    //JSONArray pp = response.getJSONArray("Persona");
                                    Dessc = new String[pp.length()];
                                    arrayid = new Integer[pp.length()];

                                    Gson gson = new Gson();
                                    for (int i = 0; i < pp.length(); i++)
                                    {
                                        equipoTrabajo = gson.fromJson(pp.get(i).toString(),Personas.class);
                                        idper = gson.fromJson(pp.get(i).toString(),Personas.class);
                                        Datos.per = equipoTrabajo;

                                        response.getJSONObject("info").getJSONArray("Persona");
                                        //response.getJSONArray("Persona");
                                        //Datos.per.getId();
                                        Dessc[i] = equipoTrabajo.getId()+".- "+equipoTrabajo.getNomEmp()+" "+equipoTrabajo.getApPat()+" "+equipoTrabajo.getApMat();
                                        arrayid[i] = equipoTrabajo.getId();


                                        //arrayid[i] = equipoTrabajo.getId(); - http://simsatableau.dyndns.org:8080/
                                        //Personas pxxp = gson.fromJson(pp.get(i).toString(),Personas.class);
                                        //Datos.per = equipoTrabajo;
                                    }
                                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,Dessc);
                                    autoCompleteTextView.setAdapter(adapter);
                                    autoCompleteTextView.setThreshold(1);

                                } catch (JSONException e)
                                {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(),"No se ha podido llenar el Spinner con la tabla Personas", Toast.LENGTH_LONG).show();
                                }

                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(getContext(), "Problemas con tu conexión al servidor...", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        });
        VolleyS.getInstance(getContext()).getRq().add(json);



       autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener()
       {

           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id)
           {
               try
               {
                   dd.put("CodEmp",Datos.per.getId());

               }
               catch (JSONException e)
               {
                   Toast.makeText(getContext(),"error", Toast.LENGTH_LONG).show();
                   e.printStackTrace();
               }

           }
       });


        btnr_saveusr.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                try
                {
                    dd.put("NomUsuario",editr_usr.getText().toString());
                    dd.put("PassUsuario",editr_pass.getText().toString());
                }
                catch (JSONException e)
                {
                    Toast.makeText(getContext(),"Por favor, llenar los campos necesarios", Toast.LENGTH_LONG).show();
                }

                JsonObjectRequest jor = new JsonObjectRequest
                (
                        Request.Method.POST,
                        Datos.URL + "/insuss",
                        dd,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                Toast.makeText(getContext(),
                                        "Se registró correctamente", Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getContext(),
                                "Ocurrió algún error en el proceso...Lo sentimos",
                                Toast.LENGTH_LONG).show();
                    }
                }

                );
                VolleyS.getInstance(getContext()).getRq().add(jor);
            }
        });
        /*multiAutoCompleteTextView.setAdapter(adapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());*/
        return  vista;
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
