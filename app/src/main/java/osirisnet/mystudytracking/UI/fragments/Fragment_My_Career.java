package osirisnet.mystudytracking.UI.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import osirisnet.mystudytracking.R;
import osirisnet.mystudytracking.UI.database.CareerDAO;
import osirisnet.mystudytracking.UI.database.MateriaDAO;
import osirisnet.mystudytracking.UI.database.UniversityDAO;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_My_Career.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_My_Career#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_My_Career extends Main_Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView strUniversidad;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_My_Career() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_My_Career.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_My_Career newInstance(String param1, String param2) {
        Fragment_My_Career fragment = new Fragment_My_Career();
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
        View view= inflater.inflate(R.layout.fragment_my_career, container, false);


        //Primero capturo los textos del xml
        TextView strUniversidad = (TextView)view.findViewById(R.id.textView_Mng_Career_Universidad); //capturo el texto del Nombre de la Universidad
        TextView strCarrera = (TextView)view.findViewById(R.id.textView_Mng_Career_Carrera); //capturo el texto del Nombre de la Carrera
        TextView strTituloInt = (TextView)view.findViewById(R.id.textView_Mng_Career_TituloInt); //capturo el texto del Nombre del Titulo Intermedio
        TextView strTituloGrado = (TextView)view.findViewById(R.id.textView_Mng_Career_TituloGrado); //capturo el texto del Nombre del Titulo de Grado
        TextView strMateriasActivas = (TextView)view.findViewById(R.id.textView_Mng_Career_MatCursText);
        TextView strMateriasAprobadas = (TextView)view.findViewById(R.id.textView_Mng_Career_MatAproText);
        TextView strMateriasRegularizadas = (TextView)view.findViewById(R.id.textView_Mng_Career_MatRegText);
        TextView strMateriasTotales = (TextView)view.findViewById(R.id.textView_Mng_Career_MatTotal);
        //En esta parte me traigo el dato de mi universidad y se lo paso al texto correspondiente
        UniversityDAO MyDB = new UniversityDAO(getMyActivity());
        Cursor res = MyDB.getMiUniversidad();
        if (res.getCount() != 1) {

            strUniversidad.setText(" Usuario no registrado");
        }
        else {
            res.moveToFirst();
            strUniversidad.setText(res.getString(0));
            }
        //Ahora me traigo los datos de mi carrera
        CareerDAO MyCareerDB = new CareerDAO(getMyActivity());
        Cursor res2 = MyCareerDB.getMiCarrera();
        if (res2.getCount() != 1) {

            strCarrera.setText(" Usuario no registrado");
            strTituloGrado.setText(" Usuario no registrado");
            strTituloInt.setText(" Usuario no registrado");
        }
        else {
            res2.moveToFirst();
            strCarrera.setText(res2.getString(1));
            strTituloInt.setText(res2.getString(2));
            strTituloGrado.setText(res2.getString(3));
        }

        MateriaDAO MyMateriaDB = new MateriaDAO(getMyActivity());
        Cursor res3 = MyMateriaDB.getMateriasActivas();
        //strMateriasActivas
          Integer MatAct = res3.getCount();
        strMateriasActivas.setText("" + MatAct.toString());


        Cursor res4 = MyMateriaDB.getMateriasAprobadas();
        Integer MatApprov = res4.getCount();
        strMateriasAprobadas.setText("" +MatApprov.toString());

        Cursor res5 = MyMateriaDB.getMateriasRegularizadas();
        Integer MatRegular = res5.getCount();
        strMateriasRegularizadas.setText("" +MatRegular.toString());

        Cursor res6 = MyMateriaDB.getMisMaterias();
        Integer MatTotal = res6.getCount();
        strMateriasTotales.setText("" +MatTotal.toString());

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
}
