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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import osirisnet.mystudytracking.R;
import osirisnet.mystudytracking.UI.Components.Adaptador_Mat;
import osirisnet.mystudytracking.UI.Components.TitularItems;
import osirisnet.mystudytracking.UI.database.CareerDAO;
import osirisnet.mystudytracking.UI.database.MateriaDAO;

import static osirisnet.mystudytracking.UI.activities.MainActivity.strIniciarMateriaGlobal;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_StCourse.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_StCourse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_StCourse extends Main_Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<TitularItems> Items;
    private Adaptador_Mat Adaptador_Mat;
    private ListView listaAllMaterias;
    private ListView listaMateriasActivas;
    private OnFragmentInteractionListener mListener;
    private String strIniciarMateria, strFinalizarMateria;

    private TextView strMateriaElegida, cuentaRegistros;

    public Fragment_StCourse() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_stcourse.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_StCourse newInstance(String param1, String param2) {
        Fragment_StCourse fragment = new Fragment_StCourse();
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
        View view= inflater.inflate(R.layout.fragment_stcourse, container, false);
        CareerDAO MyDB = new CareerDAO(getMyActivity());
        Cursor res = MyDB.getMiCarrera();
        //TextView strCarrera = (TextView)view.findViewById(R.id.textView_MuestraCarrera); //capturo el texto del Nombre de la Universidad
        strMateriaElegida = (TextView) view.findViewById(R.id.textView_IniciarMateria);
        strMateriaElegida.setText(strIniciarMateriaGlobal);

        Button btnGrabar = (Button) view.findViewById(R.id.btn_Start_Course);

   //     if (res.getCount() != 1) {

   //         strCarrera.setText(" Usuario no registrado");
   //     }
   //    else {
   //         res.moveToFirst();
   //         strCarrera.setText(res.getString(1));

            // Vinculamos el objeto ListView con el objeto del archivo XML
//            listaAllMaterias = (ListView) view.findViewById(R.id.AllMaterias_listView);
            // Llamamos al método loadItems()
//            loadItems_AllMaterias();

//       }
  /**      listaAllMaterias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TitularItems itemActualAdd = (TitularItems) Adaptador_Mat.getItem(i);
                strIniciarMateria=itemActualAdd.getTitle();
                strMateriaElegida.setText(strIniciarMateria);

            }
        });
   */
btnGrabar.setOnClickListener(new View.OnClickListener(){

    @Override
    public void onClick(View view) {
        MateriaDAO DB_Mat = new MateriaDAO(getMyActivity());
        DB_Mat.setMateriaActiva(strIniciarMateriaGlobal,true);
        Toast.makeText(getActivity(),
                R.string.Data_Updated,
                Toast.LENGTH_LONG).show();
        Fragment fragment = null;
        fragment = new Fragment_ManageCareer();
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


/**    private void loadItems_AllMaterias(){
        Items = new ArrayList<TitularItems>(); // Creamos un objeto ArrayList de tipo TitularItems
// Agregamos elementos al ArrayList
        //creo una conexion
        MateriaDAO MyDB_Materia = new MateriaDAO(getMyActivity());
        Cursor res = MyDB_Materia.getMisMateriasPcursar();
       // cuentaRegistros.setText(""+res.getCount());
        if (res.getCount() != 0){
            res.moveToFirst();

            do {
            String Materia = res.getString(0);
                Items.add(new TitularItems(Materia, null, 0));

            } while (res.moveToNext());
        }
// Creamos un nuevo Adaptador_Mat y le pasamos el ArrayList
        Adaptador_Mat = new Adaptador_Mat(getMyActivity(), Items);
// Desplegamos los elementos en el ListView
        listaAllMaterias.setAdapter(Adaptador_Mat);
    }
*/


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
