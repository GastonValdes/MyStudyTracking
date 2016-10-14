package osirisnet.mystudytracking.UI.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gasto_000 on 14/9/2016.
 */
public class DBDAO extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "MyStudyTracking.db";
   // public static final String TABLE_Alumno_NAME = "Alumno";
   // public static final String TABLE_Universities_NAME = "Universidad";
   // public static final String COL_1_Alumno = "id";
   // public static final String COL_2_Alumno = "nombre";
   // public static final String COL_3_Alumno = "apellido";
   // public static final String COL_4_Alumno = "email";
    public static final String TABLE_Alumno_Carrera_NAME = "Alumno_Carrera";
    public static final String COL_1_Alumno_Carrera = "AlumnoID";
    public static final String COL_2_Alumno_Carrera = "CarreraID";
    private static final int DATABASE_VERSION = 1;

    public DBDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
/**
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
    }**/

private Activity myActivity;

public void showMessage (String title, String message){
    AlertDialog.Builder builder = new AlertDialog.Builder(myActivity);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(message);
    builder.show();
}

}