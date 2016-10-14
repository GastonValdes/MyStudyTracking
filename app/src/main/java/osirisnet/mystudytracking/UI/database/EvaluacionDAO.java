package osirisnet.mystudytracking.UI.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gasto_000 on 19/9/2016.
 */
public class EvaluacionDAO extends DBDAO {
    public EvaluacionDAO(Context context) {
        super(context);
    }

    public List<String> getTiposEvaluacion (){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("Select * from TipoEval",null);
        List<String> labels = new ArrayList<String>();
        //Looping throwgh all rows and adding to list
        if (res.moveToFirst());{
            do {
                labels.add(res.getString(1));
            } while (res.moveToNext());
        }
        //Closing connection
        res.close();
        //Returning labels
        return labels;

    }


}
