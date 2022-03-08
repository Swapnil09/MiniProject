package deepak.mahawild;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static deepak.mahawild.Weather.setpos;

public class Jaikwadi extends AppCompatActivity implements LocationListener {

    public  static final int RequestPermissionCode  = 1 ;
    Button buttonEnable, buttonEnable1;
    TextView textView1, textView2, textView3, textView4, textView5, textView6 ;
    ImageView imageView;
    Context context;
    Location location;
    LocationManager locationManager ;
    boolean GpsStatus = false ;
    Criteria criteria ;
    String Holder;
    String coordinatesHolder ;
    float tempLong , tempLati ;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;
    public static int pos1 ;
    String[] name;
    String[] estb;
    String[] area;
    String[] loc;
    String[] fauna;
    String[] visit;
    String[] b_sanc_long;
    String[] b_sanc_lat;
    int[] imagBird = {R.drawable.bustard, R.drawable.jaikwadi, R.drawable.karnala, R.drawable.lonar, R.drawable.nandur_madhmeshwar,
            R.drawable.phansad, R.drawable.flamingo};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jaikwadi);
        pos1 = Bird.posList;
        name = getResources().getStringArray(R.array.bird_name);
        loc = getResources().getStringArray(R.array.bird_location);
        estb= getResources().getStringArray(R.array.bird_estb);
        area = getResources().getStringArray(R.array.bird_area);
        visit = getResources().getStringArray(R.array.bird_visit);
        fauna = getResources().getStringArray(R.array.bird_fauna);

        setTitle(name[pos1]);
        displayOnTextViews();

        EnableRuntimePermission();

        buttonEnable = (Button)findViewById(R.id.button);
        buttonEnable1 = (Button)findViewById(R.id.button1);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        criteria = new Criteria();

        Holder = locationManager.getBestProvider(criteria, false);

        context = getApplicationContext();

        CheckGpsStatus();

        buttonEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckGpsStatus();
                b_sanc_long = getResources().getStringArray(R.array.longitude_b);
                b_sanc_lat = getResources().getStringArray(R.array.latitude_b);


                if(GpsStatus == true) {
                    if (Holder != null) {
                        if (ActivityCompat.checkSelfPermission(
                                Jaikwadi.this,
                                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                &&
                                ActivityCompat.checkSelfPermission( Jaikwadi.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                        != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        location = locationManager.getLastKnownLocation(Holder);
                        locationManager.requestLocationUpdates(Holder, 12000, 7,  Jaikwadi.this);
                    }
                }else {

                    Toast.makeText( Jaikwadi.this, "Please Enable GPS First", Toast.LENGTH_LONG).show();

                }
                coordinatesHolder = String.format(Locale.ENGLISH, "geo:%f,%f", Float.parseFloat(b_sanc_long[pos1]), Float.parseFloat(b_sanc_lat[pos1]));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(coordinatesHolder));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        buttonEnable1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setpos(pos1);

                Intent j=new Intent(Jaikwadi.this,Weather.class);
                startActivity(j);
                stopService(getIntent());
            }
        });


    }

    private void displayOnTextViews()
    {
        imageView = (ImageView)findViewById(R.id.imageView2);
        textView1 = (TextView)findViewById(R.id.name);
        textView2 = (TextView)findViewById(R.id.district);
        textView3 = (TextView)findViewById(R.id.year);
        textView4 = (TextView)findViewById(R.id.area);
        textView5 = (TextView)findViewById(R.id.visit);
        textView6 = (TextView)findViewById(R.id.fauna);

        imageView.setImageResource(imagBird[pos1]);
        textView1.setText(name[pos1]);
        textView2.setText(loc[pos1]);
        textView3.setText(estb[pos1]);
        textView4.setText(area[pos1]);
        textView5.setText(visit[pos1]);
        textView6.setText(fauna[pos1]);
    }
    @Override
    public void onLocationChanged(Location location) {


        tempLong = (float)location.getLongitude() ;
        tempLati = (float)location.getLatitude() ;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public void CheckGpsStatus(){

        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale( Jaikwadi.this,
                Manifest.permission.ACCESS_FINE_LOCATION))
        {

            Toast.makeText( Jaikwadi.this,"ACCESS_FINE_LOCATION permission allows us to Access GPS in app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions( Jaikwadi.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, RequestPermissionCode);

        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

    //                Toast.makeText( Jaikwadi.this,"Permission Granted, Now your application can access GPS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(Jaikwadi.this,"Permission Canceled, Sorry your application cannot access GPS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

}

