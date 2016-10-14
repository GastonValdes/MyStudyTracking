package osirisnet.mystudytracking.UI.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gasto_000 on 14/9/2016.
 */
public class CareerDAO extends DBDAO {
    public CareerDAO(Context context) {
        super(context);
    }

    public static final String COL_1_Carrera = "id";
    public static final String TABLE_Carrera_NAME = "Carrera";
    public static final String TABLE_Alumno_Carrera_NAME = "Alumno_Carrera";
    public static final String COL_2_Carrera = "nombre";
    public static final String COL_3_Carrera = "TituloIntermedio";
    public static final String COL_4_Carrera = "TituloGrado";

    public Cursor getMiCarrera (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select* from carrera as C Inner Join Alumno_Carrera as AC on C.ID = AC.CarreraID", null);
        return res;
    }

    public List<String> getCarreras_x_Universidad (String Universidad){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select C.Nombre from Universidad as U inner join Universidad_Carrera as UC on U.ID = UC.UniversidadID inner join Carrera as C on C.ID = UC.CarreraID where U.Nombre = '"+Universidad+"'",null);
        List<String> labels = new ArrayList<String>();
        //Looping throwgh all rows and adding to list
        if (res.moveToFirst());{
            do {
                labels.add(res.getString(0));
            } while (res.moveToNext());
        }
        //Closing connection
        res.close();
        //Returning labels
        return labels;

    }


    public List<String> getAllCareers(){
        List<String> labels = new ArrayList<String>();

        Cursor cursor = getCarreras();

        //Looping throwgh all rows and adding to list
        if (cursor.moveToFirst());{
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        //Closing connection
        cursor.close();


        //Returning labels
        return labels;
    }


    public void setMiCarrera (String carrera){
       SQLiteDatabase db = this.getWritableDatabase();
       Cursor res = db.rawQuery("select ID from carrera where Nombre = '"+carrera+"'",null);
       res.moveToFirst();
        String strNum = res.getString(0);
        ContentValues valores = new ContentValues();
        valores.put("CarreraID",strNum);
        db.update(TABLE_Alumno_Carrera_NAME,valores,"AlumnoID = '1'",null);
   }

    public Cursor getCarreras (){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_Carrera_NAME, null);
        return res;
    }




}
