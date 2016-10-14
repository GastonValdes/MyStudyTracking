package osirisnet.mystudytracking.UI.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;

import osirisnet.mystudytracking.R;

import static osirisnet.mystudytracking.UI.activities.MainActivity.strDateFechaNewEvet;
import static osirisnet.mystudytracking.UI.activities.MainActivity.strDatePickCall;
import static osirisnet.mystudytracking.UI.activities.MainActivity.strDatePickFechaFin;
import static osirisnet.mystudytracking.UI.activities.MainActivity.strDatePickFechaInicio;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_DatePick.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_DatePick#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_DatePick extends Main_Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatePicker dpResult;
    private int mes, dia, año, hora, minutos;
    private Button btn_event_ok;
    private OnFragmentInteractionListener mListener;

    public Fragment_DatePick() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_DatePick.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_DatePick newInstance(String param1, String param2) {
        Fragment_DatePick fragment = new Fragment_DatePick();
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
        View view = inflater.inflate(R.layout.fragment_datepick, container, false);

        /**
         * Las proximas dos lineas ocultan el teclado
         */
        InputMethodManager inputManager = (InputMethodManager) getMyActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        dpResult = (DatePicker) view.findViewById(R.id.datePicker); //casteo el DatePicker
        btn_event_ok =(Button) view.findViewById(R.id.btn_event_ok);
        btn_event_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mes = 1 + dpResult.getMonth();
                dia = dpResult.getDayOfMonth();
                año = dpResult.getYear();
                if (strDatePickCall.matches("FechaInicio")){
                    strDatePickFechaInicio = dia +"/"+mes+"/"+año;
                    strDatePickCall ="";
                    Fragment fragment = null;
                    fragment = new Fragment_EndCourse();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_main, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                else if (strDatePickCall.matches("FechaFin")){
                    strDatePickFechaFin = dia +"/"+mes+"/"+año;
                    strDatePickCall ="";
                    Fragment fragment = null;
                    fragment = new Fragment_EndCourse();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_main, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                else if (strDatePickCall.matches("NewEvent")){
                    strDateFechaNewEvet = dia +"/"+mes+"/"+año;
                    strDatePickCall ="";
                    Fragment fragment = null;
                    fragment = new Fragment_NewEvent();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_main, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

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
