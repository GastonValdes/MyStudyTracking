package osirisnet.mystudytracking.UI.Components;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import osirisnet.mystudytracking.R;

/**
 * Created by gasto_000 on 30/9/2016.
 */

public class Adaptador_MatActiv extends BaseAdapter {
    private Activity activity; //Activity desde el cual se hace referencia al llenado de la lista
    private ArrayList<TitularItems> arrayItems; // Lista de items
    // Constructor con parámetros que recibe la Acvity y los datos de los items.
    public Adaptador_MatActiv(Activity activity, ArrayList<TitularItems> listaItems){
        super();
        this.activity = activity;
        this.arrayItems = new ArrayList<TitularItems>(listaItems);
    }
    // Retorna el número de items de la lista
    @Override
    public int getCount() {
        return arrayItems.size();
    }
    // Retorna el objeto TitularItems de la lista
    @Override
    public Object getItem(int position) {
        return arrayItems.get(position);
    }
    // Retorna la posición del item en la lista
    @Override
    public long getItemId(int position) {
        return position;
    }
    /*
    Clase estática que contiene los elementos de la lista
    */
    public static class Fila
    {
        TextView txtTitle;
//        TextView txtDescription;
//        ImageView img;
    }
    // Método que retorna la vista formateada
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fila view = new Fila();
        LayoutInflater inflator = activity.getLayoutInflater();
        TitularItems itm = arrayItems.get(position);
/*
Condicional para recrear la vista y no distorcionar el número de elementos
*/
        if(convertView==null)
        {
//           convertView = inflator.inflate(R.layout.items, parent, false);
           convertView = inflator.inflate(R.layout.items_materias, parent, false);
            view.txtTitle = (TextView) convertView.findViewById(R.id.txt_listMateria);
//            view.txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);
//            view.img = (ImageView)convertView.findViewById(R.id.imgItem);
            convertView.setTag(view);
        }
        else
        {
            view = (Fila)convertView.getTag();
        }
// Se asigna el dato proveniente del objeto TitularItems
        view.txtTitle.setText(itm.getTitle());
//        view.txtDescription.setText(itm.getDescription());
//        view.img.setImageResource(itm.getImg());
// Retornamos la vista
        return convertView;
    }
}
