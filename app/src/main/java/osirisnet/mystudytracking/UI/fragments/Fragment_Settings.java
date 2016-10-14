package osirisnet.mystudytracking.UI.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

import osirisnet.mystudytracking.R;
import osirisnet.mystudytracking.UI.database.CareerDAO;
import osirisnet.mystudytracking.UI.database.ConfigDAO;
import osirisnet.mystudytracking.UI.database.UniversityDAO;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Settings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Settings extends Main_Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button buttonGrabar;
    ToggleButton toggle;
    ConfigDAO MyDB;
    CareerDAO CarreraDB;
    EditText strDias,strEventos;
    String strPreHabilitado, strSpinner_Carrera, strSpinner_Universidad;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_Settings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Settings.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Settings newInstance(String param1, String param2) {
        Fragment_Settings fragment = new Fragment_Settings();
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
        View view= inflater.inflate(R.layout.fragment_settings, container, false);


        //Declaraciones
        strDias = (EditText)view.findViewById(R.id.editText_Config_dias); //capturo el cuadro de los días de preaviso
        strEventos = (EditText)view.findViewById(R.id.editText_Config_Eventos); //capturo el cuadro del limite de Eventos
        buttonGrabar = (Button)view.findViewById(R.id.btn_Config_Save); //Capturo el boton
        final ConfigDAO MyDB = new ConfigDAO(getMyActivity()); //Declaro un objeto de bd de configuracion
        final CareerDAO CarreraDB = new CareerDAO(getMyActivity()); // Declaro un objeto de bd de Carrera
        final UniversityDAO UniversidadDB = new UniversityDAO(getMyActivity()); // Declaro un objeto de bd de Universidad

        //Carga de informacion en pantallas desde la base
        Cursor res = MyDB.getAllConfig(); //genero un recordset y lo populo con el metodo getAllConfig que me lista la bd de configuracion
        res.moveToFirst(); //voy al primer registro de mi base de configuracion
        strDias.setText(res.getString(2)); //me traigo la cantidad de dias de preaviso
        res.moveToNext(); //voy al siguiente registro
        strEventos.setText(res.getString(2)); //me traigo la cantidad de eventos de preaviso
        res.moveToNext();//voy al siguiente registro
        strPreHabilitado=(res.getString(2)); //Me traigo si el preaviso esta configurado o no

        Cursor res3 = UniversidadDB.getMiUniversidad(); //genero un cursor y lo populo con mi universidad.
        res3.moveToFirst(); //voy al primer registro
        strSpinner_Universidad=(res3.getString(0)); // leo mi universidad
        //showMessage("Lectura",strSpinner_Universidad);

        Cursor res2 = CarreraDB.getMiCarrera(); //genero un cursor y lo populo con mi carrera
        res2.moveToFirst();//voy al primer registro
        strSpinner_Carrera=(res2.getString(1)); //leo mi carrera
        //showMessage("Lectura",strSpinner_Carrera);


        ToggleButton toggle = (ToggleButton) view.findViewById(R.id.toggleButton);
        if (strPreHabilitado.equals("1")) {
            toggle.setChecked(true);
        }
        else{
            toggle.setChecked(false);
            strEventos.setEnabled(false);
            //strEventos.setFocusable(false);
            strDias.setEnabled(false);
            strDias.setEnabled(false);
        }
        // Le pongo un listener al boton grabar y ejecuto los metodos de grabacion en la base
        buttonGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = MyDB.UpdatePreaviso("1","Preaviso",strDias.getText().toString()) && MyDB.UpdatePreaviso("2","Eventos",strEventos.getText().toString()) && MyDB.UpdatePreaviso("3","PreHabilitado",strPreHabilitado.toString());
                CarreraDB.setMiCarrera(strSpinner_Carrera);

                if (isUpdated == true) {
                    Toast.makeText(getMyActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
                    Fragment fragment = null;
                    fragment = new Fragment_Start();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_main, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                } else
                    Toast.makeText(getMyActivity(), "Data NOT Inserted", Toast.LENGTH_LONG).show();




            }
        });



        /** Configuracion para Accion en boton de preaviso **/
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                 //   showMessage("Cartel", "Enabled = True");
                    strEventos.setEnabled(true);
                    //strEventos.setFocusable(true);
                    strDias.setEnabled(true);
                    //strDias.setFocusable(true);
                    strPreHabilitado = "1";
                } else {
                    // The toggle is disabled
                 //   showMessage("Cartel", "Enabled=False");
                    strEventos.setEnabled(false);
                 //   strEventos.setText("0");
                    strDias.setEnabled(false);
                 //   strDias.setText("0");
                    strPreHabilitado = "0";
                }
            }
        });
        /** Fin de configuracion para Accion en boton de preaviso**/


        final Spinner spinner_universidad = (Spinner) view.findViewById(R.id.spinner_Config_Universidad); // casteo el spinner_universidad del xml
        loadSpinner_Universidad(spinner_universidad); //populo el spinner_Universidad

        final Spinner spinner_carrera = (Spinner)view.findViewById(R.id.spinner_Config_Carrera); //casteo el espinner_carrera del xml
        load_Spinner_Carrera(spinner_carrera); //populo el spinner_carrera

        //pongo un listener en el spinner_carrera
        spinner_carrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                strSpinner_Carrera = spinner_carrera.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Pongo un listener en el spinner_Universidad
        spinner_universidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                strSpinner_Universidad = spinner_universidad.getSelectedItem().toString();
                load_Spinner_Carrera_Filtrada(spinner_carrera); //vuelvo a popular el spinner_carrera
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //Con el siguiente codigo me traigo la lista de carreras y populo un List con la lista de la base.
        List<String> labels = CarreraDB.getAllCareers(); // creo un objeto de tipo list y lo cargo con la lista de carreras de la base
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getMyActivity(),
                android.R.layout.simple_spinner_item, labels);  //creo una lista con los nombres de las carreras que me traje
       spinner_carrera.setSelection(labels.indexOf(strSpinner_Carrera)); //Seteo el spinner_carrera con el valor grabado en la base.

        List<String> labels_Universidad = UniversidadDB.getAllUniversities();
        ArrayAdapter<String> dataAdapter_U = new ArrayAdapter<String>(getMyActivity(),
                android.R.layout.simple_spinner_item, labels_Universidad);
        spinner_universidad.setSelection(labels_Universidad.indexOf(strSpinner_Universidad));

        return view;

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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    private void loadSpinner_Universidad(Spinner spinner) {
        // database handler
        //DatabaseHelper db = new DatabaseHelper(getMyActivity());
        UniversityDAO db = new UniversityDAO(getMyActivity());


        // Spinner Drop down elements
        List<String> lables = db.getAllUniversities();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getMyActivity(),
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    private void load_Spinner_Carrera(Spinner spinner) {
        // database handler
        //DatabaseHelper db = new DatabaseHelper(getMyActivity());
        CareerDAO db = new CareerDAO(getMyActivity());


        // Spinner Drop down elements
        List<String> lables = db.getAllCareers();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getMyActivity(),
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    private void load_Spinner_Carrera_Filtrada (Spinner spinner) {
        // database handler
        CareerDAO db = new CareerDAO(getMyActivity());

        // Spinner Drop down elements
        List<String> labels = db.getCarreras_x_Universidad(strSpinner_Universidad);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getMyActivity(),
                android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

}
