package osirisnet.mystudytracking.UI.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gasto_000 on 20/9/2016.
 */

public class NotaDAO extends DBDAO {
    public NotaDAO(Context context) {super(context);}

    public static final String TABLE_Notas_NAME = "Notas";
    public static final String COL_1_Notas = "ID";
    public static final String COL_2_Notas = "Nota";
    public static final String COL_3_Notas = "Fecha";
    public static final String COL_4_Notas = "Hora";
    public static final String COL_5_Notas = "TipoEval";
    public static final String TABLE_Notas_Mat_NAME = "Notas_Materias";


    public Cursor getAllNotas (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select* from Notas, null",null);
        return res;
    }

    public Cursor getNotaxID (int _id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select* from Notas where ID="+_id,null);
        return res;
    }
    public Cursor getNotaFullxID (int _id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select N.ID, N.Nota, N.Fecha, N.Hora, M.Nombre from Notas as N inner join Notas_Materia as NM on N.ID = NM.NotaID inner join Materia as M on NM.MateriaID = M.ID where N.ID ="+_id, null);
        return res;
    }

    public void setNuevaNota (String Materia, String Fecha, String Hora,String Nota, String TipoEval) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select ID from TipoEval where Eval ='" + TipoEval + "'", null);
        res.moveToFirst();
        Integer ID_Eval = res.getInt(0);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_Notas, Nota);
        contentValues.put(COL_3_Notas, Fecha);
        contentValues.put(COL_4_Notas, Hora);
        contentValues.put(COL_5_Notas, ID_Eval);
        db.insert(TABLE_Notas_NAME, null, contentValues);
        //Cursor Res2 = db.rawQuery("Insert into Notas (Nota, Fecha, Hora, TipoEval) values ("+Nota+","+Fecha+","+Hora+","+ID_Eval+")", null);
        Cursor Res2 = db.rawQuery("Select * from "+TABLE_Notas_NAME,null);
        Res2.moveToLast();
        Integer ID_Nota = Res2.getInt(0);
        Cursor Res3 =db.rawQuery("Select * from Materia where Nombre ='"+Materia+"'",null);
        Res3.moveToFirst();
        Integer ID_Materia = Res3.getInt(0);
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("MateriaID", ID_Materia);
        contentValues2.put("NotaID", ID_Nota);
        db.insert("Notas_Materia", null, contentValues2);
        //Cursor Res4 = db.rawQuery("Insert into Notas_Materia (MateriaID, NotaID) values ("+ID_Materia+","+ID_Nota+")",null);
    }

    public Cursor getUpcoming (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select N.Fecha, N.Hora, M.Nombre, TE.Eval , N.ID from Notas_Materia as NM inner Join Notas as N on NM.NotaID = N.ID inner join Materia as M on M.ID = NM.MateriaID Inner Join TipoEval as TE on TE.ID = N.TipoEval", null);
        return res;
    }

    //***************************************************************************************************
    //  Genero una lista, utilizo el cursor del metodo getUpcoming, lo recorro y populo mi lista.

    public List<String> getAllUpcoming(){
        List<String> labels = new ArrayList<String>();

        Cursor cursor = getUpcoming();

        //Looping throwgh all rows and adding to list
        if (cursor.moveToFirst());{
            do {
                labels.add(cursor.getString(0)+" - "+cursor.getString(1)+"-"+cursor.getString(2)+"-"+cursor.getString(3));
            } while (cursor.moveToNext());
        }

        //Closing connection
        cursor.close();


        //Returning labels
        return labels;
    }

    //****************************************************************************************************


}
