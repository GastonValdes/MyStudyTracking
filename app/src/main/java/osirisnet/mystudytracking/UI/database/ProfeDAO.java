package osirisnet.mystudytracking.UI.database;

import android.content.Context;

/**
 * Created by gasto_000 on 8/10/2016.
 */

public class ProfeDAO extends DBDAO {

    public ProfeDAO(Context context) {
        super(context);
    }

    public static final String TABLE_Docente_NAME = "Docente";
    public static final String COL1_Docente = "ID";
    public static final String COL2_Docente = "Nombre";
    public static final String COL3_Docente = "Apellido";
    public static final String COL4_Docente = "Tel";
    public static final String COL5_Docente = "Celular";
    public static final String COL6_Docente = "email";
    public static final String COL7_Docente = "Other";

public void setDocente (String Nombre, String Apellido, int Tel, int cel, String email, String Other){


}

}
