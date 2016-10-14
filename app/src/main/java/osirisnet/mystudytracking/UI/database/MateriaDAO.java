package osirisnet.mystudytracking.UI.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gasto_000 on 17/9/2016.
 */
public class MateriaDAO extends DBDAO{
    public MateriaDAO(Context context) {
        super(context);
    }

    public static final String TABLE_Materia_NAME = "Materia";
    public static final String COL1_Materia = "ID";
    public static final String COL2_Materia = "Nombre";
    public static final String COL3_Materia = "HSTotales";
    public static final String COL4_Materia = "Regularizada";
    public static final String COL5_Materia = "Aprobada";
    public static final String COL6_Materia = "Correlativa";
    public static final String COL7_Materia = "FechaInicio";
    public static final String COL8_Materia = "FechaFin";
    public static final String COL9_Materia = "Activ";
    public static final String COL10_Materia = "NotaFinal";

//*****************************************************************************************
//          Genero un cursor y lo lleno con una query que selecciona mis materias que no estan en curso

    public Cursor getMisMateriasPcursar (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select M.Nombre, M.Activ from Alumno_Carrera as AC Inner Join Carrera as C on C.ID = AC.CarreraID inner join Carrera_Materia as CM on C.ID = CM.CarreraID inner join Materia as M on CM.MateriaID = M.ID where M.Activ !='1' order by M.Nombre", null);
        return res;
    }
//*****************************************************************************************
    //***************************************************************************************************
    //  Genero una lista, utilizo el cursor del metodo getMisMateriasPcursar, lo recorro y populo mi lista.

    public List<String> getAllMisMateriasPcursar(){
        List<String> labels = new ArrayList<String>();

        Cursor cursor = getMisMateriasPcursar();

        //Looping throwgh all rows and adding to list
        if (cursor.moveToFirst());{
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        //Closing connection
        cursor.close();


        //Returning labels
        return labels;
    }

    //****************************************************************************************************






//*****************************************************************************************
//          Genero un cursor y lo lleno con una query que selecciona mis materias

    public Cursor getMisMaterias (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select M.Nombre, M.Activ from Alumno_Carrera as AC Inner Join Carrera as C on C.ID = AC.CarreraID inner join Carrera_Materia as CM on C.ID = CM.CarreraID inner join Materia as M on CM.MateriaID = M.ID order by M.Nombre", null);
        return res;
    }
//*****************************************************************************************

    //***************************************************************************************************
    //  Genero una lista, utilizo el cursor del metodo getMisMaterias, lo recorro y populo mi lista.

    public List<String> getAllMisMaterias(){
        List<String> labels = new ArrayList<String>();

        Cursor cursor = getMisMaterias();

        //Looping throwgh all rows and adding to list
        if (cursor.moveToFirst());{
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        //Closing connection
        cursor.close();


        //Returning labels
        return labels;
    }

    //****************************************************************************************************



//*****************************************************************************************
//          Genero un cursor y lo lleno con una query que selecciona mis materias activas

    public Cursor getMateriasActivas (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select M.Nombre, M.Activ from Alumno_Carrera as AC Inner Join Carrera as C on C.ID = AC.CarreraID inner join Carrera_Materia as CM on C.ID = CM.CarreraID inner join Materia as M on CM.MateriaID = M.ID WHERE M.Activ='1'", null);
        return res;
    }
//*****************************************************************************************

    public Cursor getMateriasAprobadas (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Materia as M inner join Carrera_Materia as CM on M.ID = CM.MateriaID  inner join Carrera as C on C.ID = CM.CarreraID where M.Aprobada = '1'", null);
        return res;
    }

    //*****************************************************************************************

    public Cursor getMateriasRegularizadas (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Materia as M inner join Carrera_Materia as CM on M.ID = CM.MateriaID  inner join Carrera as C on C.ID = CM.CarreraID inner join Alumno_Carrera as AC on AC.CarreraID = C.ID where M.Aprobada != '1' AND  M.Regularizada = '1'", null);
        return res;
    }

    //***************************************************************************************************
    //  Genero una lista, utilizo el cursor del metodo getMateriasActivas, lo recorro y populo mi lista.

    public List<String> getAllMateriasActivas(){
        List<String> labels = new ArrayList<String>();

        Cursor cursor = getMateriasActivas();

        if (cursor.getCount() != 0){
            if (cursor.moveToFirst());{
                do {
                    labels.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }

        }
        //Looping throwgh all rows and adding to list

        //Closing connection
        cursor.close();


        //Returning labels
        return labels;
    }

    //****************************************************************************************************
    public void UpdateMateriaxNombre (String Materia, Integer HsTotales, boolean Regularizada, boolean Aprobada, Integer Correlativa, String FechaInicio, String FechaFin, boolean Activa, Double Notafinal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from Materia where Nombre ='" + Materia + "'", null);
        res.moveToFirst();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_Materia, Materia);
        if (HsTotales != null){
            contentValues.put(COL3_Materia, HsTotales);
        }
            contentValues.put(COL4_Materia, Regularizada);
            contentValues.put(COL5_Materia, Aprobada);
        if (Correlativa != null){
            contentValues.put(COL6_Materia, Correlativa);
        }
        if (FechaInicio != null){
            contentValues.put(COL7_Materia, FechaInicio);
        }
        if (FechaFin != null){
               contentValues.put(COL8_Materia, FechaFin);
        }
            contentValues.put(COL9_Materia, Activa);
        if (Notafinal !=null){
            contentValues.put (COL10_Materia, Notafinal);
        }
        db.update(TABLE_Materia_NAME,contentValues,"Nombre = ?",new String[]{Materia});
        }

    public void setMateriaActiva (String Materia, boolean Activa){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from Materia where Nombre ='" + Materia + "'", null);
        res.moveToFirst();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_Materia, Materia);
        contentValues.put (COL9_Materia, Activa);
        db.update(TABLE_Materia_NAME,contentValues,"Nombre = ?",new String[]{Materia});

    }
    public Cursor getMateriaxNombre (String Materia){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from Materia where Nombre ='" + Materia + "'", null);
        return res;
            }

    }

