package osirisnet.mystudytracking.UI.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import osirisnet.mystudytracking.R;
import osirisnet.mystudytracking.UI.database.EvaluacionDAO;
import osirisnet.mystudytracking.UI.database.MateriaDAO;
import osirisnet.mystudytracking.UI.database.NotaDAO;

import static osirisnet.mystudytracking.UI.activities.MainActivity.strDateFechaNewEvet;
import static osirisnet.mystudytracking.UI.activities.MainActivity.strDatePickCall;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_NewEvent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_NewEvent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_NewEvent extends Main_Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DatePicker dpResult;
    private EditText editDate;
    private TimePicker tmpResult;
    private int mes, dia, año, hora, minutos;
    private Button btn_event_ok;
    private TextView textView_date_evt;
    NotaDAO MyNotaDb;
    String strSpinner_Materia, strSpinnerTipoEval ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_NewEvent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_NewEvent.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_NewEvent newInstance(String param1, String param2) {
        Fragment_NewEvent fragment = new Fragment_NewEvent();
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
        View view= inflater.inflate(R.layout.fragment_new_event, container, false);
        final Spinner spinner_Materias = (Spinner) view.findViewById(R.id.spinner_Materia); // casteo el spinner_Materia del xml
        loadSpinner_Materias(spinner_Materias);
        final Spinner spinner_TipoEval = (Spinner) view.findViewById(R.id.spinner_TipoEval); // casteo el spinner_TipoEval del xml
        loadSpinner_TipoEval(spinner_TipoEval);
//        dpResult = (DatePicker) view.findViewById(R.id.datePicker); //casteo el DatePicker
        editDate = (EditText) view.findViewById(R.id.editText_datepick)   ;
        tmpResult =(TimePicker) view.findViewById(R.id.timePicker); // casteo el Tmepicker
        btn_event_ok = (Button) view.findViewById(R.id.btn_evt_ok);
        //textView_date_evt = (TextView) view.findViewById(R.id.textView_date_evt);
        if (strDateFechaNewEvet.matches("")){
editDate.setText(R.string.EditText_Fecha);
        }
        else {
            editDate.setText(strDateFechaNewEvet);
        }

        final NotaDAO MyNotaDB = new NotaDAO(getMyActivity());
        btn_event_ok.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
/* Elimino el date picker
                mes = 1 + dpResult.getMonth();
                dia = dpResult.getDayOfMonth();
                año = dpResult.getYear();
*/
                if (Build.VERSION.SDK_INT >= 23) {
                    hora = tmpResult.getHour();
                    minutos = tmpResult.getMinute();
                } else {
                    hora = tmpResult.getCurrentHour();
                    minutos = tmpResult.getCurrentMinute();
                }


                strSpinner_Materia = spinner_Materias.getSelectedItem().toString();
                strSpinnerTipoEval = spinner_TipoEval.getSelectedItem().toString();
             //   textView_date_evt.setText("Dia: " + dia + "-" + mes + "-" + año + "Hora: " + hora + ":" + minutos);
                /* Aca va el moco para convertir String a formato fecha */

                String str_date = editDate.getText().toString();
                String strHora = hora + ":" + minutos;
                //showMessage("Fecha",""+textView_date_evt.getText());
                MyNotaDB.setNuevaNota(strSpinner_Materia, str_date, strHora, "", strSpinnerTipoEval);
                Toast.makeText(getMyActivity(), R.string.Data_Updated, Toast.LENGTH_LONG).show();
                Fragment fragment = null;
                fragment = new Fragment_Start();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

editDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){

    @Override
    public void onFocusChange(View view, boolean b) {
        strDatePickCall = "NewEvent";
        strDateFechaNewEvet = "";
        Fragment fragment = null;
        fragment = new Fragment_DatePick();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
});

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



    private void loadSpinner_Materias(Spinner spinner) {
        // database handler
        //DatabaseHelper db = new DatabaseHelper(getMyActivity());
        MateriaDAO db = new MateriaDAO(getMyActivity());


        // Spinner Drop down elements
        List<String> labels = db.getAllMateriasActivas();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getMyActivity(),
                android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    private void loadSpinner_TipoEval(Spinner spinner) {
        // database handler
        //DatabaseHelper db = new DatabaseHelper(getMyActivity());
        EvaluacionDAO db = new EvaluacionDAO(getMyActivity());


        // Spinner Drop down elements
        List<String> labels = db.getTiposEvaluacion();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getMyActivity(),
                android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
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
}
