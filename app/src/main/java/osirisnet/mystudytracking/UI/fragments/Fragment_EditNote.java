package osirisnet.mystudytracking.UI.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import osirisnet.mystudytracking.R;
import osirisnet.mystudytracking.UI.database.NotaDAO;

import static osirisnet.mystudytracking.UI.activities.MainActivity.intUpcommingMateria;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_EditNote.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_EditNote#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_EditNote extends Main_Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //private TextView tvEditNoteTitle, tvEditNonte, tvEditNoteFech, tvEditNoteHora, tvEditNoteTipoEvento;
    private EditText edtFecha, edtHora;
    private OnFragmentInteractionListener mListener;

    public Fragment_EditNote() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_EditNote.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_EditNote newInstance(String param1, String param2) {
        Fragment_EditNote fragment = new Fragment_EditNote();
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
        View view = inflater.inflate(R.layout.fragment_editnote, container, false);


        NotaDAO MydbNota = new NotaDAO(getMyActivity());
        Cursor resNota = MydbNota.getNotaxID(intUpcommingMateria);
        if (resNota.getCount()!= 0){
            resNota.moveToFirst();
            showMessage("Lectura", "ID: "+resNota.getString(0)+" Nota: "+resNota.getString(1)+" Fecha: "+resNota.getString(2)+" Hora: "+resNota.getString(3)+" Tipo Evauacion: "+resNota.getString(4) );

        }
        //edtFecha.setText(resNota.getString(2));


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
