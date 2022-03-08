package deepak.mahawild;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;
    public static int posList;
    public static String sanctuaryName;
    public static int posView = 1;
    private ListView lv;
    ArrayAdapter<String> adapter;
    SearchView searchView;


    String[] names = {"Amba Barwa", "Aner Dam", "Bhamragarh", "Bhimashankar", "Bor", "Chaprala", "Dhyanganga",
            "Gautala-Autramghat", "Kalsubai Harishchandragad", "Katepurna", "Koyana", "Malvan Marine",
            "Mansingdeo", "Mayureswar Supe", "Melghat", "Nagzira", "Naigaon Mayur", "New Bor", "Painganga",
            "Radhanagari", "Rehekuri Blackbuck", "Sagareshwar", "Tansa", "Tipeshwar", "Tungareshwar",
            "Umred-Kharandla", "Wan", "Yawal", "Yedshi Ramling"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstances();


        // Listview Data
        final String sanctuaries[] = getResources().getStringArray(R.array.wls_name);
        lv = (ListView) findViewById(R.id.list);

        //Hide keypad when loaded app first time
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        searchView = (SearchView) findViewById(R.id.idSearch);
        searchView.setIconifiedByDefault(false);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.sanc_name, sanctuaries);
        lv.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        final String[] n = new String[1];
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                sanctuaryName = lv.getItemAtPosition(position).toString();
                for(int i=0;i<names.length;i++) {
                    if (sanctuaryName.equals(names[i])) {
                        posList = i;
                        break;
                    }
                }
                Intent myIntent = new Intent(view.getContext(), Amba_Barwa.class);
                startActivityForResult(myIntent, 0);

            }
        });
    }


    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.navigation_view_item_1, R.string.navigation_view_item_1) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);

        navigation = (NavigationView) findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_1:
                        posView = 1;
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_2:
                        posView = 2;
                        startActivity(new Intent(MainActivity.this, NationalParks.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_3:
                        posView = 3;
                        startActivity(new Intent(MainActivity.this, Bird.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_6:
                        startActivity(new Intent(MainActivity.this, MahaEco.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_7:
                        startActivity(new Intent(MainActivity.this, Safari.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_8:
                        startActivity(new Intent(MainActivity.this, Accomodation.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_9:
                        startActivity(new Intent(MainActivity.this, Nearby.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_10:
                        startActivity(new Intent(MainActivity.this, AboutUs.class));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout layout = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (layout.isDrawerOpen(GravityCompat.START)) {
            layout.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
    }
}