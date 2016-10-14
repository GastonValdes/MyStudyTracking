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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import osirisnet.mystudytracking.R;
import osirisnet.mystudytracking.UI.database.AlumnoDAO;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Student.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Student#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Student extends Main_Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button buttonGrabar;
    EditText strNombre;
    EditText strApellido;
    EditText streMail;
    AlumnoDAO MyDB;



    private OnFragmentInteractionListener mListener;

    public Fragment_Student() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Student.
     */

    public static Fragment_Student newInstance(String param1, String param2) {
        Fragment_Student fragment = new Fragment_Student();
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
        View view = inflater.inflate(R.layout.fragment_student, container, false);

        buttonGrabar = (Button)view.findViewById(R.id.buttonGrabar); //Capturo el boton
        strNombre = (EditText)view.findViewById(R.id.editTextNombreEst); //capturo el cuadro del Nombre
        strApellido = (EditText)view.findViewById(R.id.editTextApellidoEst); //capturo el cuadro del Apellido
        streMail = (EditText)view.findViewById(R.id.editTextEmailEst); //capturo el cuadro del email
        final AlumnoDAO MyDB = new AlumnoDAO(getMyActivity());
        Cursor res = MyDB.getAllAlumno();
        if (res.getCount() != 1) {
            // show message

            showMessage("Error", "No Valid Data Found");
           // return;
        }

        else {
            res.moveToNext();
            //showMessage("Data","usuario "+res.getString(1));
            strNombre.setText(res.getString(1));
            strApellido.setText(res.getString(2));
            streMail.setText(res.getString(3));

        }


        //Helper helper = new Helper (view.getContext());
        //ArrayList<Object[]> obj = helper.getAllStudents();

        //strNombre.setText(obj.get(0)[1].toString());
        buttonGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //Toast.makeText (getMyActivity(),"Hice Clic y capture: Nombre: "+strNombre.getText() +", Apellido: " +strApellido.getText()+ ", email: "+streMail.getText(),Toast.LENGTH_LONG).show();
                Cursor res = MyDB.getAllAlumno();
                if (res.getCount() == 1) {

                    boolean isUpdated = MyDB.updateAlumno("1",
                            strNombre.getText().toString(),
                            strApellido.getText().toString(),
                            streMail.getText().toString());
                    if (isUpdated == true) {
                        Toast.makeText(getMyActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
                        //Intent intent = new Intent(getMyActivity(), Fragment_Start.class);
                        //startActivity(intent);
                        Fragment fragment = null;
                        fragment = new Fragment_Start();

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.content_main, fragment);
                       // transaction.addToBackStack(null);
                        transaction.commit();

                    } else
                        Toast.makeText(getMyActivity(), "Data NOT Inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    boolean isUpdated = MyDB.insertAlumno(
                            strNombre.getText().toString(),
                            strApellido.getText().toString(),
                            streMail.getText().toString());
                    if (isUpdated == true) {
                        Toast.makeText(getMyActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
                        //Intent intent = new Intent(getMyActivity(), Fragment_Start.class);
                        //startActivity(intent);
                    } else
                        Toast.makeText(getMyActivity(), "Data NOT Inserted", Toast.LENGTH_LONG).show();
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

        void onFragmentInteraction(Uri uri);
    }




}
