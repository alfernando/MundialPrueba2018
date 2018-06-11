package quiniela.tesis.com.mundialprueba2018;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;


public class MainActivity extends AppCompatActivity
        implements LocationListener, NavigationView.OnNavigationItemSelectedListener {

    private LocationManager locationManager;
    private String TAG= "LocalizacionApp";
    private TextView txt1,txt2;
    private final int MY_PERMISSIONS_REQUEST = 1;

    ArrayList<PartidoVo> listDatos;
    RecyclerView recycler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Almacenar Resultados", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //inicia el codigo de GPS

        txt1 = (TextView) findViewById(R.id.txtview1);
        txt2 = (TextView) findViewById(R.id.txtview3);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           Log.d(TAG,"Faltan Permisos");
           ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},MY_PERMISSIONS_REQUEST);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);


        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));



        listDatos = new ArrayList<>();
        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        llenarPartidos();

        AdapterDatos adapter = new AdapterDatos(listDatos);
        recycler.setAdapter(adapter);

       // for (int i=0;i<=50;i++)
        //{
         //   listDatos.add("Dato: "+i+" ");
        //}
        //AdapterDatos adapter = new AdapterDatos(listDatos);
        //recycler.setAdapter(adapter);



    }









    private void llenarPartidos(){
        String nombre ="rusia";
        int image1 = 1;


        switch (nombre){
                        case "rusia":image1= R.drawable.lobo_rusia; break;
                        default: image1=R.drawable.fifa_cup; break;
                    }

        listDatos.add(new PartidoVo(nombre,"Grupo A",image1) );
        listDatos.add(new PartidoVo("Arabia Saudi","Grupo A",image1));
        listDatos.add(new PartidoVo(nombre,"Grupo A",image1) );
        listDatos.add(new PartidoVo("Arabia Saudi","Grupo A",image1));
        listDatos.add(new PartidoVo(nombre,"Grupo A",image1) );
        listDatos.add(new PartidoVo("Arabia Saudi","Grupo A",image1));
        listDatos.add(new PartidoVo(nombre,"Grupo A",image1) );
        listDatos.add(new PartidoVo("Arabia Saudi","Grupo A",image1));
        listDatos.add(new PartidoVo(nombre,"Grupo A",image1) );
        listDatos.add(new PartidoVo("Arabia Saudi","Grupo A",image1));


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_exit) {
            setLogOff();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setLogOff() {

        finish();
    }

    @Override
    public void onLocationChanged(Location location) {
        txt1.setText("Latitud: "+location.getLatitude()+"");
        txt2.setText("Longitud: "+location.getLongitude()+"");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onRequestPermissionsResult(int recuestCode,String permissions[],int[] grantResult){
        switch (recuestCode){
            case MY_PERMISSIONS_REQUEST:{
                if (grantResult.length > 0 && grantResult[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Perfecto", LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"???",LENGTH_LONG).show();
                }
            }
        }
    }
}
