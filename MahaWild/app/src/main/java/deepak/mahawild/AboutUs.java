package deepak.mahawild;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class AboutUs extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;
    ImageView img, img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        initInstances();
        img = (ImageView) findViewById(R.id.email_icon);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Queries");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                intent.setData(Uri.parse("grouproject4@gmail.com"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


    }


    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(AboutUs.this, drawerLayout, R.string.navigation_view_item_10, R.string.navigation_view_item_10);
        drawerLayout.setDrawerListener(drawerToggle);

        navigation = (NavigationView) findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_1:
                        MainActivity.posView=1;
                        startActivity(new Intent(AboutUs.this, MainActivity.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_2:
                        MainActivity.posView=2;
                        startActivity(new Intent(AboutUs.this,NationalParks.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_3:
                        MainActivity.posView=3;
                        startActivity(new Intent(AboutUs.this,Bird.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_6:
                        startActivity(new Intent(AboutUs.this,MahaEco.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_7:
                        startActivity(new Intent(AboutUs.this,Safari.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_8:
                        startActivity(new Intent(AboutUs.this,Accomodation.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_9:
                        startActivity(new Intent(AboutUs.this,Nearby.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_10:
                        startActivity(new Intent(AboutUs.this,AboutUs.class));
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout layout = (DrawerLayout)findViewById(R.id.drawerLayout);
        if (layout.isDrawerOpen(GravityCompat.START)) {
            layout.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(AboutUs.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
