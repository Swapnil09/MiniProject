package deepak.mahawild;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Nearby extends AppCompatActivity {
    ListView listView;
    ArrayAdapter adapter ;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;

    String[] nearbyLocations = {"Hospitals", "ATM's", "Petrol Pumps", "Hotels", "Wildlife Sanctuaries", "Restuarants"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby);

        initInstances();

        listView = (ListView)findViewById(R.id.nearyList);

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.sanc_name, nearbyLocations);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == 0) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.in/maps/search/hospitals")));
                }
                if (position == 1) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.in/maps/search/atms")));
                }
                if (position == 2) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.in/maps/search/petrol+pumps")));
                }
                if (position == 3) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.in/maps/search/hotels")));
                }
                if (position == 4) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.in/maps/search/wildlife+sanctuary")));
                }
                if (position == 5) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.in/maps/search/restaurants")));
                }
            }
        });
    }


    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(Nearby.this, drawerLayout, R.string.navigation_view_item_2, R.string.navigation_view_item_2);
        drawerLayout.setDrawerListener(drawerToggle);

        navigation = (NavigationView) findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_1:
                        MainActivity.posView = 1;
                        startActivity(new Intent(Nearby.this, MainActivity.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_2:
                        MainActivity.posView = 2;
                        startActivity(new Intent(Nearby.this,NationalParks.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_3:
                        MainActivity.posView = 3;
                        startActivity(new Intent(Nearby.this,Bird.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_6:
                        startActivity(new Intent(Nearby.this,MahaEco.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_7:
                        startActivity(new Intent(Nearby.this,Safari.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_8:
                        startActivity(new Intent(Nearby.this,Accomodation.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_9:
                        startActivity(new Intent(Nearby.this, Nearby.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_10:
                        startActivity(new Intent(Nearby.this,AboutUs.class));
                        drawerLayout.closeDrawers();
                        break;

                }
                return false;
            }
        });

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout layout = (DrawerLayout)findViewById(R.id.drawerLayout);
        if (layout.isDrawerOpen(GravityCompat.START)) {
            layout.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Nearby.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}

