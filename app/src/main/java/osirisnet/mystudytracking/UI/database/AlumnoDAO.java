package osirisnet.mystudytracking.UI.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by gasto_000 on 15/9/2016.
 */
public class AlumnoDAO extends DBDAO {
    public AlumnoDAO(Context context) {
        super(context);
    }
    public static final String TABLE_Alumno_NAME = "Alumno";
    public static final String COL_1_Alumno = "id";
    public static final String COL_2_Alumno = "nombre";
    public static final String COL_3_Alumno = "apellido";
    public static final String COL_4_Alumno = "email";

    public boolean insertAlumno(String Nombre, String Apellido, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_Alumno,Nombre);
        contentValues.put(COL_3_Alumno,Apellido);
        contentValues.put(COL_4_Alumno,email);
        long result = db.insert (TABLE_Alumno_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllAlumno(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_Alumno_NAME,null);
        return res;
    }

    public Cursor getThisAlumno(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_Alumno_NAME + "WHERE ID = '1'",null);
        return res;
    }


    public boolean updateAlumno(String id, String Nombre, String Apellido, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_Alumno,id);
        contentValues.put(COL_2_Alumno,Nombre);
        contentValues.put(COL_3_Alumno,Apellido);
        contentValues.put(COL_4_Alumno,email);
        db.update(TABLE_Alumno_NAME,contentValues,"ID = ?",new String[]{id});
        return true;

    }
    public Cursor getAlumno_Carrera (){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_Alumno_Carrera_NAME ,null);
        return res;
    }


}
