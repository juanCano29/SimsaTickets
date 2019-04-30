package com.example.juankno4.simsaticket.cRoot;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AgregarEquipoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgregarEquipoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarEquipoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View v;
    EditText descr,nums;
    AutoCompleteTextView autoCompleteTextView;
    Spinner tequ,per;
    Button submit;
    Personas equipoTrabajo, idper, variable, mm;
    JSONObject dd = new JSONObject();



    private OnFragmentInteractionListener mListener;

    public AgregarEquipoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarEquipoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarEquipoFragment newInstance(String param1, String param2) {
        AgregarEquipoFragment fragment = new AgregarEquipoFragment();
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
        v=inflater.inflate(R.layout.fragment_agregar_equipo, container, false);
        descr=v.findViewById(R.id.edit_descequipo);
        nums=v.findViewById(R.id.textv_nums);
        tequ=v.findViewById(R.id.spin_tipoequipo);
        autoCompleteTextView = v.findViewById(R.id.autoCompleteTextView);
        submit = v.findViewById(R.id.submit_addequipo);

        String[] array_tequip={"LAPTOP","ESCRITORIO","TELEFONO"};
        tequ.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,array_tequip));

        JsonObjectRequest json = new JsonObjectRequest
                (
                        Request.Method.GET,
                        Datos.URL + "/allper",
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                try {
                                    JSONObject Perss = response.getJSONObject("info");
                                    JSONArray pp = response.getJSONObject("info").getJSONArray("Persona");
                                    String[] Dessc = new String[pp.length()];
                                    Gson gson = new Gson();
                                    for (int i = 0; i < pp.length(); i++)
                                    {
                                        Personas equipoTrabajo = gson.fromJson(pp.get(i).toString(),Personas.class);
                                        Datos.per = equipoTrabajo;
                                        response.getJSONObject("info").getJSONArray("Persona");
                                        Datos.per.getId();
                                        Dessc[i] = equipoTrabajo.getNomEmp()+" "+equipoTrabajo.getApPat()+" "+equipoTrabajo.getApMat();

                                    }
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,Dessc);
                                    autoCompleteTextView.setAdapter(adapter);
                                    autoCompleteTextView.setThreshold(1);

                                } catch (JSONException e)
                                {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(),"No se ha podido llenar el Spinner con la tabla Personas", Toast.LENGTH_LONG).show();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(getContext(), "HOUSTON, TENEMOS UN PROBLEMA", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getContext(),"Ocurrio algun error", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });//putos si se armooooooooooo

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                try
                {
                    dd.put("Descripcion",descr.getText().toString());
                    dd.put("NoSerie",nums.getText().toString());

                    String valuespi = (String) tequ.getSelectedItem();
                    if (valuespi == "LAPTOP")
                    {
                        dd.put("TipoEquipo","LAPTOP");
                    }
                    if (valuespi == "ESCRITORIO")
                    {
                        dd.put("TipoEquipo","ESCRITORIO");
                    }
                    if (valuespi == "TELEFONO")
                    {
                        dd.put("TipoEquipo","TELEFONO");
                    }
                }
                catch (JSONException e)
                {
                    Toast.makeText(getContext(),"Por favor, llenar los campos necesarios", Toast.LENGTH_LONG).show();
                }

                JsonObjectRequest jor = new JsonObjectRequest
                        (
                                Request.Method.POST,
                                Datos.URL + "/insequ",
                                dd,
                                new Response.Listener<JSONObject>()
                                {
                                    @Override
                                    public void onResponse(JSONObject response)
                                    {
                                        Toast.makeText(getContext(),
                                                "Se registró correctamente",
                                                Toast.LENGTH_LONG).show();
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
