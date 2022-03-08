package deepak.mahawild;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
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

public class Bird extends AppCompatActivity {
    ListView listView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;
    public static int posList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.national_parks);
        initInstances();

        listView = (ListView) findViewById(R.id.list);
        String[] lstSource = {
                "Great Indian Bustard ", "Jaikwadi ", "Karnala ", "Lonar", "Nandur Madhameshwar", "Phansad",
                "Thane Creek Flamingo "
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,lstSource);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                    posList = position;
                    Intent myIntent = new Intent(view.getContext(), Jaikwadi.class);
                    startActivityForResult(myIntent, 0);


            }
        });
    }


    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(Bird.this, drawerLayout, R.string.navigation_view_item_3, R.string.navigation_view_item_3);
        drawerLayout.setDrawerListener(drawerToggle);

        navigation = (NavigationView) findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_1:
                        MainActivity.posView = 1;
                        startActivity(new Intent(Bird.this, MainActivity.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_2:
                        MainActivity.posView = 2;
                        startActivity(new Intent(Bird.this,NationalParks.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_3:
                        MainActivity.posView =3;
                        startActivity(new Intent(Bird.this,Bird.class));
                        drawerLayout.closeDrawers();
                        break;
                   case R.id.navigation_item_6:
                        startActivity(new Intent(Bird.this,MahaEco.class));
                       drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_7:
                        startActivity(new Intent(Bird.this,Safari.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_8:
                        startActivity(new Intent(Bird.this,Accomodation.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_9:
                        startActivity(new Intent(Bird.this, Nearby.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_10:
                        startActivity(new Intent(Bird.this,AboutUs.class));
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
            Intent intent = new Intent(Bird.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
