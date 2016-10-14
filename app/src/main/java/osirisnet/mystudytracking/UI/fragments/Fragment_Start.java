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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import osirisnet.mystudytracking.R;
import osirisnet.mystudytracking.UI.Components.Adaptador_upcoming;
import osirisnet.mystudytracking.UI.Components.TitularItems;
import osirisnet.mystudytracking.UI.database.AlumnoDAO;
import osirisnet.mystudytracking.UI.database.ConfigDAO;
import osirisnet.mystudytracking.UI.database.DBDAO;
import osirisnet.mystudytracking.UI.database.NotaDAO;

import static osirisnet.mystudytracking.UI.activities.MainActivity.intUpcommingMateria;
import static osirisnet.mystudytracking.UI.activities.MainActivity.strUpcommingMateria;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Start.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Start#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Start extends Main_Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String strConfigDias, strConfigEventos;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    DBDAO MyDB;
    // Variables de la clase
    private ArrayList<TitularItems> Items;
    private Adaptador_upcoming Adaptador_Upcomming;
    private ListView listaItems;

    public Fragment_Start() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Start.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Start newInstance(String param1, String param2) {
        Fragment_Start fragment = new Fragment_Start();
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
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        AlumnoDAO MyDB = new AlumnoDAO(getMyActivity());
        Cursor res = MyDB.getAllAlumno();
        TextView t= (TextView)view.findViewById(R.id.textView_Wellcome);
        if (res.getCount() != 1) {

            t.append(" Usuario no registrado");
        }
        // MyDB = new DatabaseHelper(myActivity);
        //Cursor res = MyDB.getAllAlumno();
        else {
            res.moveToNext();
            t.append(res.getString(1));
        }

       /**Lista Vieja que falla en el celu
        final ListView HomeUpcoming_listView = (ListView) view.findViewById(R.id.HomeUpcoming_listView);
        load_ListView_HomeUpcoming(HomeUpcoming_listView);
       **/
        // Vinculamos el objeto ListView con el objeto del archivo XML
        listaItems = (ListView) view.findViewById(R.id.HomeUpcoming_listView);
        // Llamamos al método loadItems()
        ConfigDAO MyDBConfig = new ConfigDAO(getMyActivity());
        String strConfigPreHabilitado;
          //Carga de informacion en pantallas desde la base
         Cursor resConfig = MyDBConfig.getAllConfig(); //genero un recordset y lo populo con el metodo getAllConfig que me lista la bd de configuracion
         resConfig.moveToFirst(); //voy al primer registro de mi base de configuracion
         strConfigDias = (res.getString(2)); //me traigo la cantidad de dias de preaviso
         resConfig.moveToNext(); //voy al siguiente registro
         strConfigEventos =(resConfig.getString(2)); //me traigo la cantidad de eventos de preaviso
         resConfig.moveToNext();//voy al siguiente registro
         strConfigPreHabilitado=(resConfig.getString(2)); //Me traigo si el preaviso esta configurado o no
         if (strConfigPreHabilitado.equals("1")) {
             loadItems();
         }


        listaItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TitularItems itemActualMod = (TitularItems) Adaptador_Upcomming.getItem(i);
                intUpcommingMateria= itemActualMod.getID();
               String stria= itemActualMod.getTitle();
//showMessage("Leo", stria);
                Fragment fragment = null;
                fragment = new Fragment_EditNote();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }


    // Método cargar Items
    private void loadItems(){
        Items = new ArrayList<TitularItems>(); // Creamos un objeto ArrayList de tipo TitularItems



        //creo una conexion
        NotaDAO MyDB_Nota = new NotaDAO(getMyActivity());
        Cursor res = MyDB_Nota.getUpcoming();
        if (res.getCount() == 0){ //Si busco y hay 0 registros de novedades

            Items.add(new TitularItems("No hay Novedades", "Sin eventos registrados", this.getResources().getIdentifier("alert", "drawable", getMyActivity().getPackageName())));
        }
    else{ //Si encuentro registros con Novedades
            res.moveToFirst();
            Integer myNum, recordCount;
            myNum = Integer.parseInt(strConfigEventos);
            recordCount = res.getCount();
            if (recordCount < myNum){ //me aseguro de que el limite de entradas no supere mi cantidad de registros para que no explote al llegar al final del recordset
                myNum = recordCount;
            }
            for(int i = 0; i < myNum; i++){ //Inicio un loop para cargar tantos registros como defini en el config
                String Materia = res.getString(2);
                String strFecha = res.getString(0);
                strFecha = strFecha +" "+res.getString(1);
                String strTipoEval = res.getString(3);
                int intID = res.getInt(4);

                String strIcono = "";
                if (strTipoEval.equals("Trabajo Practico") ){
                    strIcono = "ic_tp";
                }

                if (strTipoEval.equals("Examen Parcial")){
                    strIcono = "ic_exam";
                }

                if (strTipoEval.equals("Examen Final") ){
                    strIcono ="ic_final_exam";
                }
                Items.add(new TitularItems(Materia, strFecha, this.getResources().getIdentifier(strIcono, "drawable", getMyActivity().getPackageName()),intID));
                res.moveToNext();
            }

            /**

            do {
                int Counter = 1;

                String Materia = res.getString(2);
                String strFecha = res.getString(0);
                strFecha = strFecha +" "+res.getString(1);
                String strTipoEval = res.getString(3);
                String strIcono = "";
                if (strTipoEval.equals("Trabajo Practico") ){
                    strIcono = "ic_tp";
                }

                if (strTipoEval.equals("Examen Parcial")){
                    strIcono = "ic_exam";
                }

                if (strTipoEval.equals("Examen Final") ){
                    strIcono ="ic_final_exam";
                }
                Items.add(new TitularItems(Materia, strFecha, this.getResources().getIdentifier(strIcono, "drawable", getMyActivity().getPackageName())));
            } while (res.moveToNext());

            **/

            /**
             if (res.moveToFirst());{
             do {
             labels.add(cursor.getString(0)+" - "+cursor.getString(1)+"-"+cursor.getString(2)+"-"+cursor.getString(3));
             } while (cursor.moveToNext());
             }
            **/

        }

// Creamos un nuevo Adaptador_upcoming y le pasamos el ArrayList
        Adaptador_Upcomming = new Adaptador_upcoming(getMyActivity(), Items);
// Desplegamos los elementos en el ListView
        listaItems.setAdapter(Adaptador_Upcomming);
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

    private void load_ListView_HomeUpcoming(ListView listview) {
        // database handler
        //DatabaseHelper db = new DatabaseHelper(getMyActivity());
        NotaDAO db = new NotaDAO(getMyActivity());


        // Spinner Drop down elements
        List<String> labels = db.getAllUpcoming();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getMyActivity(),
                android.R.layout.simple_list_item_multiple_choice, labels);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);

        // attaching data adapter to spinner
        listview.setAdapter(dataAdapter);
    }

}
