package osirisnet.mystudytracking.UI.activities;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import osirisnet.mystudytracking.UI.Components.TitularItems;
import osirisnet.mystudytracking.UI.fragments.Fragment_Calendar;
import osirisnet.mystudytracking.UI.fragments.Fragment_DatePick;
import osirisnet.mystudytracking.UI.fragments.Fragment_EditNote;
import osirisnet.mystudytracking.UI.fragments.Fragment_EndCourse;
import osirisnet.mystudytracking.UI.fragments.Fragment_ManageCareer;
import osirisnet.mystudytracking.UI.fragments.Fragment_My_Career;
import osirisnet.mystudytracking.UI.fragments.Fragment_NewEvent;
import osirisnet.mystudytracking.UI.fragments.Fragment_Settings;
import osirisnet.mystudytracking.UI.fragments.Fragment_StCourse;
import osirisnet.mystudytracking.UI.fragments.Fragment_Start;
import osirisnet.mystudytracking.UI.fragments.Fragment_Student;
import osirisnet.mystudytracking.R;
import osirisnet.mystudytracking.UI.fragments.fragment_about;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        fragment_about.OnFragmentInteractionListener,
        Fragment_Student.OnFragmentInteractionListener,
        Fragment_Start.OnFragmentInteractionListener,
        Fragment_NewEvent.OnFragmentInteractionListener,
        Fragment_Calendar.OnFragmentInteractionListener,
        Fragment_My_Career.OnFragmentInteractionListener,
        Fragment_ManageCareer.OnFragmentInteractionListener,
        Fragment_Settings.OnFragmentInteractionListener,
        Fragment_StCourse.OnFragmentInteractionListener,
        Fragment_EndCourse.OnFragmentInteractionListener,
        Fragment_DatePick.OnFragmentInteractionListener,
        Fragment_EditNote.OnFragmentInteractionListener

{
public String strFragname;
public static String strIniciarMateriaGlobal="", strFinalizarMateriaGlobal="", strDatePickCall="", strDatePickFechaInicio="", strDatePickFechaFin="", strDateFechaNewEvet ="",strUpcommingMateria="";
public static int intUpcommingMateria= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // Helper helper = new Helper (getApplicationContext());
        //ArrayList<Object[]> obj = helper.getAllStudents();

        /**
         * Esta parte carga un fragment al abrir la aplicacion.
         */
        boolean FragmentTransaction = false;
        Fragment fragment = null;
        fragment = new Fragment_Start();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, fragment)
                .commit();
        strFragname = getSupportActionBar().getTitle().toString(); //aca tomo el nombre de la barra de titulo para poder usarla en el back

        /**
         * Aca se cargan los floating buttons.
         */
        FloatingActionButton fabNewEvent = (FloatingActionButton) findViewById(R.id.fabNewEvent);
        fabNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                fragment = new Fragment_NewEvent();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, fragment)
                        .addToBackStack(null)
                        .commit();
                //strFragname = getSupportActionBar().getTitle().toString();
                getSupportActionBar().setTitle(R.string.Flt_NewEvent); //en esta linea seteo el nombre en la barra

            }

        });

        FloatingActionButton fabCarrer = (FloatingActionButton) findViewById(R.id.fabCarrer);
        fabCarrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = null;
                fragment = new Fragment_My_Career();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, fragment)
                        .addToBackStack(null)
                        .commit();
               // strFragname = getSupportActionBar().getTitle().toString();
                getSupportActionBar().setTitle(R.string.Flt_CareerOverview); //en esta linea seteo el nombre en la barra


              //  Snackbar.make(view, "Aca lanza la carrera", Snackbar.LENGTH_LONG)
              //          .setAction("Action", null).show();
            }

        });

        FloatingActionButton fabTeacher = (FloatingActionButton) findViewById(R.id.fabTeacher);
        fabTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                fragment = new Fragment_Calendar();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, fragment)
                        .addToBackStack(null)
                        .commit();

                getSupportActionBar().setTitle(R.string.Flt_Calendar); //en esta linea seteo el nombre en la barra
                                //      Snackbar.make(view, "Aca lanza el Calendario", Snackbar.LENGTH_LONG)
            //             .setAction("Action", null).show();
           }

        });

        /**
         * Aca inicia el navigation Drawer
         */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
/**
 * Le saco el codigo para el backpress
*/

/**
    @Override
    public void onBackPressed() {
       // Toast.makeText(this, "back", Toast.LENGTH_LONG).show();
        getSupportActionBar().setTitle(strFragname);
  //      getSupportFragmentManager().beginTransaction()  //inicio transaccion con fragment
  //              .replace(R.id.content_main, new Fragment_Start())  //reemplazo el content main con el fragment que traigo desde arriba
  //              .commit();                             // commiteo la transaccion
        super.onBackPressed();
        return;
    }

*/



    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            //super.onBackPressed();
            //return;
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

        this.doubleBackToExitPressedOnce = true;
       // Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, R.string.Tap2_Exit, Toast.LENGTH_SHORT).show();

        getSupportActionBar().setTitle(strFragname);
        getSupportFragmentManager().beginTransaction()  //inicio transaccion con fragment
                .replace(R.id.content_main, new Fragment_Start())  //reemplazo el content main con el fragment que traigo desde arriba
                .commit();                             // commiteo la transaccion
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


/**
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();

            getSupportActionBar().setTitle(strFragname);
            getSupportFragmentManager().beginTransaction()  //inicio transaccion con fragment
            .replace(R.id.content_main, new Fragment_Start())  //reemplazo el content main con el fragment que traigo desde arriba
            .commit();                             // commiteo la transaccion

        }

 */





    /**
     * A parir de Aca se define que hace cada boton del Navigation Drawer.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean FragmentTransaction = false;
        Fragment fragment = null;


        if (id == R.id.nav_home) {
            fragment = new Fragment_Start();
            FragmentTransaction = true;
        } else if (id == R.id.nav_MyProfile) {
            fragment = new Fragment_Student(); //en esta liea defino como se llamara el fragment
            FragmentTransaction = true;        //en esta linea seteo FragmentTransaction en true para que el if que esta mas abajo capture el evento
        } else if (id == R.id.nav_MngCareer) {
            fragment = new Fragment_ManageCareer(); //en esta liea defino como se llamara el fragment
            FragmentTransaction = true;        //en esta linea seteo FragmentTransaction en true para que el if que esta mas abajo capture el evento
        } else if (id == R.id.nav_settings) {
            fragment = new Fragment_Settings(); //en esta liea defino como se llamara el fragment
            FragmentTransaction = true;      //en esta linea seteo FragmentTransaction en true para que el if que esta mas abajo capt
        } else if (id == R.id.nav_share) {
           // fragment = new Fragment_Student(); //en esta liea defino como se llamara el fragment
           // FragmentTransaction = true;        //en esta linea seteo FragmentTransaction en true para que el if que esta mas abajo capture el evento
        } else if (id == R.id.nav_about) {
            fragment = new fragment_about(); //en esta liea defino como se llamara el fragment
            FragmentTransaction = true;      //en esta linea seteo FragmentTransaction en true para que el if que esta mas abajo capture el evento
        }

        if (FragmentTransaction) {

            getSupportFragmentManager().beginTransaction()  //inicio transaccion con fragment
                    .replace(R.id.content_main, fragment)  //reemplazo el content main con el fragment que traigo desde arriba
                    .addToBackStack(null)                   // En esta linea habilito la vuelta atras con el back del celu
                    .commit();                             // commiteo la transaccion

            item.setChecked(true);
           // strFragname = getSupportActionBar().getTitle().toString();
            getSupportActionBar().setTitle(item.getTitle()); //en esta linea seteo el titulo en la barra

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



}
