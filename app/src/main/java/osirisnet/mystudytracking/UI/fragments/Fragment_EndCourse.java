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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import osirisnet.mystudytracking.R;
import osirisnet.mystudytracking.UI.Components.Adaptador_Mat;
import osirisnet.mystudytracking.UI.Components.TitularItems;
import osirisnet.mystudytracking.UI.database.CareerDAO;
import osirisnet.mystudytracking.UI.database.MateriaDAO;

import static osirisnet.mystudytracking.UI.activities.MainActivity.strDatePickCall;
import static osirisnet.mystudytracking.UI.activities.MainActivity.strDatePickFechaFin;
import static osirisnet.mystudytracking.UI.activities.MainActivity.strDatePickFechaInicio;
import static osirisnet.mystudytracking.UI.activities.MainActivity.strFinalizarMateriaGlobal;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_EndCourse.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_EndCourse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_EndCourse extends Main_Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView strUniversidad;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<TitularItems> Items;
    private Adaptador_Mat Adaptador_Mat;
    private ListView listaAllMaterias;
    private ListView listaMateriasActivas;
    private OnFragmentInteractionListener mListener;
    private String strIniciarMateria, strFinalizarMateria;
    private boolean boolRegularizada, boolAprobada, boolActiva;
    private Integer intTemphsTotales;
    private CheckBox chkRegularizada, chkAprobada, chkActiva ;
    private EditText strHsTotales, strStartDate, strEndDate, strFinalScore  ;
    private Button btnUpdate;
    private Double dblTempNotaFinal;
    public Fragment_EndCourse() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_endcourse.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_EndCourse newInstance(String param1, String param2) {
        Fragment_EndCourse fragment = new Fragment_EndCourse();
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
        View view= inflater.inflate(R.layout.fragment_endcourse, container, false);
         strHsTotales = (EditText)view.findViewById(R.id.editText_Hs); //capturo el cuadro de las HS
        strStartDate= (EditText)view.findViewById(R.id.editText_fechaInicio); //capturo el cuadro de la Fecha Inicio
        strEndDate = (EditText)view.findViewById(R.id.editText_fechaFin); //capturo el cuadro de la Fecha Fin
        strFinalScore = (EditText)view.findViewById(R.id.editText_NotaFinal); //capturo el cuadro de NotaFinal
         chkRegularizada = (CheckBox) view.findViewById(R.id.checkBox_Regularizada); //capturo el check Regularizada
         chkAprobada = (CheckBox) view.findViewById(R.id.checkBox_aprobada); //capturo el check Aprobada
         chkActiva = (CheckBox) view.findViewById(R.id.checkBox_activa); //capturo el check Activa
        btnUpdate = (Button) view.findViewById(R.id.btn_EndCourseUpdate); // Capturo el boton Update

        TextView strMataCerrar = (TextView)view.findViewById(R.id.textView_CierreMat); //Capturo el cuadro con el Nombre de la materia
        strMataCerrar.setText(strFinalizarMateriaGlobal); //Seteo el nombre de la materia con el valor que le puse a la variable global en la pantalla anterior
        final MateriaDAO MyDB = new MateriaDAO(getMyActivity()); //Inicio un nuevo conector ADO
        Cursor res = MyDB.getMateriaxNombre(strFinalizarMateriaGlobal); // Generon un cursor

            res.moveToFirst();
            strHsTotales.setText(res.getString(2)); //traigo de la base las HS de cursada
            strStartDate.setText(res.getString(6)); //traigo de la base la FechaInicio
            strEndDate.setText(res.getString(7)); //traigo de la base la FechaFin
            strFinalScore.setText(res.getString(9)); //traigo de la base la nota Final

        if (strEndDate.getText().toString().matches("")){
            strEndDate.setText(strDatePickFechaFin);
        }
        if (strStartDate.getText().toString().matches("")){
            strStartDate.setText(strDatePickFechaInicio);
        }

        boolRegularizada = stringToBool(res.getString(3));
        if (boolRegularizada == true) {
            chkRegularizada.setChecked(true);
        }
        boolAprobada = stringToBool(res.getString(4));
         if (boolAprobada == true) {
             chkAprobada.setChecked(true);
         }
        boolActiva = stringToBool(res.getString(8));
         if (boolActiva= true){
             chkActiva.setChecked(true);
         }

        /*
        Este Metodo llama al Fragmen DatePick para elejir la fecha cuando hago foco en fecha fin
         */
        strEndDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                strDatePickCall ="FechaFin";
                Fragment fragment = null;
                fragment = new Fragment_DatePick();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        /*
        Este Metodo llama al Fragmen DatePick para elejir la fecha cuando hago foco en fecha Inicio
         */
        strStartDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                strDatePickCall ="FechaInicio";
                Fragment fragment = null;
                fragment = new Fragment_DatePick();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
         });


        chkActiva.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            chkAprobada.setChecked(false);
            boolAprobada = false;
            boolActiva = true;

            if (strFinalScore.getText().toString().matches("Debe Ingresar un valor")){
                strFinalScore.setText ("");
            }
            if (strEndDate.getText().toString().matches("Debe Ingresar un valor")){
                strEndDate.setText ("");
            }
        }
    });
chkAprobada.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        chkActiva.setChecked(false);
        boolActiva = false;
        boolAprobada = true;
        if (strFinalScore.getText().toString().matches("")){
            strFinalScore.setText ("Debe Ingresar un valor");
        }
        if (strEndDate.getText().toString().matches("")){
            strEndDate.setText ("Debe Ingresar un valor");
        }
    }
});
btnUpdate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
if (chkAprobada.isChecked()){
    boolAprobada = true;
}
else {
    boolAprobada = false;
}
        if (chkActiva.isChecked()){
            boolActiva = true;
        }
        else {
            boolActiva = false;
        }
        if (chkRegularizada.isChecked()){
            boolRegularizada = true;
        }
        else{
            boolRegularizada = false;
        }
        if (boolAprobada == true ) {
            //showMessage("Aprobado","");
            if (strFinalScore.getText().toString().matches("Debe Ingresar un valor") && strEndDate.getText().toString().matches("Debe Ingresar un valor")){
                showMessage("Error","Debe Ingresar puntaje final y fecha de finalizacion para cerrar una materia como aprobada");
            }
            else if (strFinalScore.getText().toString().matches("Debe Ingresar un valor")|(strFinalScore.getText().toString().matches(""))){
                showMessage("Error","Debe Ingresar puntaje final para cerrar una materia como aprobada");
            }
            else if (strEndDate.getText().toString().matches("Debe Ingresar un valor")|(strEndDate.getText().toString().matches(""))){
                showMessage("Error","Debe Ingresar una fecha de finalizacion para cerrar una materia como aprobada");
            }
        }
        else {
            //showMessage("No Aprobado?","");
        }

if (strFinalScore.getText().toString().matches("")){
    dblTempNotaFinal = null;
}
        else{
    dblTempNotaFinal= Double.parseDouble(strFinalScore.getText().toString());
        }


if (strHsTotales.getText().toString().matches("")){
    intTemphsTotales = null;
}
else{
    intTemphsTotales = Integer.parseInt(strHsTotales.getText().toString());
}
if (strStartDate.getText().toString().matches("")){
    strIniciarMateria =null;
}
        else{
    strIniciarMateria = strStartDate.getText().toString();
        }
if (strEndDate.getText().toString().matches("")){
            strFinalizarMateria =null;
}
        else {
            strFinalizarMateria = strEndDate.getText().toString();
        }
 //       showMessage("Fecha Inicio: ",strStartDate.getText().toString());
 //       showMessage("Fecha Fin: ",strEndDate.getText().toString());
//  MyDB.UpdateMateriaxNombre(strFinalizarMateriaGlobal,intTemphsTotales,boolRegularizada,boolAprobada,null,null,null,boolActiva,null);
MyDB.UpdateMateriaxNombre(strFinalizarMateriaGlobal,intTemphsTotales,boolRegularizada,boolAprobada,null,strIniciarMateria,strFinalizarMateria,boolActiva,dblTempNotaFinal);
strFinalizarMateriaGlobal ="";
        strDatePickFechaInicio ="";
        strDatePickFechaFin ="";
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



  //      if (res.getCount() != 1) {
  //        strCarrera.setText(" Usuario no registrado");
  //      }
  //     else {
  //          res.moveToFirst();
  //          strCarrera.setText(res.getString(1));


   //         strTituloInt.setText(res.getString(2));
   //         strTituloGrado.setText(res.getString(3));


            // Vinculamos el objeto ListView con el objeto del archivo XML
            //listaAllMaterias = (ListView) view.findViewById(R.id.AllMaterias_listView);
            // Llamamos al método loadItems()
            //loadItems_AllMaterias();
            // Vinculamos el objeto ListView con el objeto del archivo XML
           //listaMateriasActivas = (ListView) view.findViewById(R.id.ActiveMaterias_listView);
            // Llamamos al método loadItems()
           //loadItems_MateriasActivas();

  //      }



  /*
        listaMateriasActivas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //strFinalizarMateria = listaMateriasActivas.getSelectedItem().toString();
        TitularItems itemActualElim = (TitularItems) Adaptador_Mat.getItem(i);
        strFinalizarMateria=itemActualElim.getTitle();
        Toast.makeText(getActivity(),
                "Iniciar screen de detalle para: \n" + strFinalizarMateria,
                Toast.LENGTH_SHORT).show();
        //showMessage("Lei", strFinalizarMateria);
    }
});
*/





/**
 *

        listaAllMaterias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //strFinalizarMateria = listaMateriasActivas.getSelectedItem().toString();
                TitularItems itemActualAdd = (TitularItems) Adaptador_Mat.getItem(i);
                strIniciarMateria=itemActualAdd.getTitle();
                Toast.makeText(getActivity(),
                        "Iniciar screen de detalle para: \n" + strIniciarMateria,
                        Toast.LENGTH_SHORT).show();

            }
        });
 */


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


  /*
    private void loadItems_AllMaterias(){
        Items = new ArrayList<TitularItems>(); // Creamos un objeto ArrayList de tipo TitularItems
// Agregamos elementos al ArrayList
        //creo una conexion
        MateriaDAO MyDB_Materia = new MateriaDAO(getMyActivity());
        Cursor res = MyDB_Materia.getMisMaterias();
        if (res.getCount() != 0){
            res.moveToFirst();

            do {
            String Materia = res.getString(0);
                Items.add(new TitularItems(Materia, null, 0));

            } while (res.moveToNext());
        }



// Creamos un nuevo Adaptador_upcoming y le pasamos el ArrayList
//        Adaptador_Mat = new Adaptador_Mat(getMyActivity(), Items);
// Desplegamos los elementos en el ListView
//        listaAllMaterias.setAdapter(Adaptador_Mat);
    }





    private void loadItems_MateriasActivas(){
        Items = new ArrayList<TitularItems>(); // Creamos un objeto ArrayList de tipo TitularItems
// Agregamos elementos al ArrayList
        //creo una conexion
        MateriaDAO MyDB_MateriaAct = new MateriaDAO(getMyActivity());
        Cursor res = MyDB_MateriaAct.getMateriasActivas();
        if (res.getCount() != 0){
            res.moveToFirst();
            do {
                String Materia = res.getString(0);
                Items.add(new TitularItems(Materia, null, 0));
                } while (res.moveToNext());
        }
// Creamos un nuevo Adaptador_upcoming y le pasamos el ArrayList
        Adaptador_Mat = new Adaptador_Mat(getMyActivity(), Items);
// Desplegamos los elementos en el ListView
        listaMateriasActivas.setAdapter(Adaptador_Mat);
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
