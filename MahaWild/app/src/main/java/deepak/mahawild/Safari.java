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
import android.webkit.WebView;

public class Safari extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mahaeco);
        initInstances();
        webview = (WebView)findViewById(R.id.webView1);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.setWebViewClient(new WebViewClient());

        webview.loadUrl("http://mahaecotourism.gov.in/Site/Common/OnlineBooking1.aspx");
    }

    private class WebViewClient extends android.webkit.WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(Safari.this, drawerLayout, R.string.navigation_view_item_7, R.string.navigation_view_item_7);
        drawerLayout.setDrawerListener(drawerToggle);

        navigation = (NavigationView) findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_1:
                        MainActivity.posView=1;
                        startActivity(new Intent(Safari.this, MainActivity.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_2:
                        MainActivity.posView=2;
                        startActivity(new Intent(Safari.this,NationalParks.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_3:
                        MainActivity.posView=3;
                        startActivity(new Intent(Safari.this,Bird.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_6:
                        startActivity(new Intent(Safari.this,MahaEco.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_7:
                        startActivity(new Intent(Safari.this,Safari.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_8:
                        startActivity(new Intent(Safari.this,Accomodation.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_9:
                        startActivity(new Intent(Safari.this, Nearby.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.navigation_item_10:
                        startActivity(new Intent(Safari.this,AboutUs.class));
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
            Intent intent = new Intent(Safari.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}