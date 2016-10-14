package osirisnet.mystudytracking.UI.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by gasto_000 on 31/8/2016.
 */

public class Main_Fragment extends Fragment {

    private Activity myActivity;

    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(myActivity);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            myActivity = (Activity) context;
        }
    }

    protected boolean stringToBool (String string){
        boolean output;
        String valor = "1";
        if (string.equals(valor)){
            output = true;
        }
        else {
            output = false;
        }

        return output;
    }

    protected static int safeLongToInt(long l) {
        int i = (int)l;
        if ((long)i != l) {
            throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
        }
        return i;
    }

    protected Activity getMyActivity() {
        return myActivity;
    }

    protected void setMyActivity(Activity myActivity) {
        this.myActivity = myActivity;
    }
}
