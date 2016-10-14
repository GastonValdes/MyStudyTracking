package osirisnet.mystudytracking.UI.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by gasto_000 on 16/9/2016.
 */
public class ConfigDAO extends DBDAO {
    public ConfigDAO(Context context) {
        super(context);
    }
    public static final String TABLE_CONFIG_NAME = "Config";
    public static final String COL_1_CONFIG = "id";
    public static final String COL_2_CONFIG = "Parametro";
    public static final String COL_3_CONFIG = "Valor";


    public Cursor getAllConfig(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_CONFIG_NAME,null);
        return res;
    }

    public Boolean UpdatePreaviso(String id, String parametro, String valor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_CONFIG,id);
        contentValues.put(COL_2_CONFIG,parametro);
        contentValues.put(COL_3_CONFIG,valor);
        db.update(TABLE_CONFIG_NAME,contentValues,"ID = ?",new String[]{id});
        return true;
    }
}
