package osirisnet.mystudytracking.UI.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gasto_000 on 15/9/2016.
 */
public class UniversityDAO extends DBDAO {
    public UniversityDAO(Context context) {
        super(context);
    }

    public static final String TABLE_Universities_NAME = "Universidad";
    public static final String TABLE_Universidad_Carrera ="Universidad_carrera";

    public Cursor getUniversities(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_Universities_NAME, null);
        return res;
    }


    public Cursor getMiUniversidad (){
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select* from carrera as C Inner Join Alumno_Carrera as AC on C.ID = AC.CarreraID", null);
        Cursor res = db.rawQuery("select U.nombre from carrera as C Inner Join Alumno_Carrera as AC on C.ID = AC.CarreraID inner join Universidad_Carrera as UC on UC.CarreraID = AC.CarreraID inner join Universidad as U on UC.CarreraID = U.ID",null);

        return res;
    }

/**
    public void setMiUniversidad (String Universidad){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID from Universidad where Nombre = '"+Universidad+"'",null);
        res.moveToFirst();
        String strNum = res.getString(0);
        ContentValues valores = new ContentValues();
        valores.put("CarreraID",strNum);
        db.update(TABLE_Universidad_Carrera,valores,"AlumnoID = '1'",null);
    }

**/

    public List<String> getAllUniversities(){
        List<String> labels = new ArrayList<String>();

        Cursor cursor = getUniversities();

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
}
