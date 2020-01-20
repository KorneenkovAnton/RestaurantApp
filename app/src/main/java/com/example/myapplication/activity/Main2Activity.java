package com.example.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myapplication.R;
import com.example.myapplication.fragment.OrderFragment;
import com.example.myapplication.fragment.ProfileFragment;
import com.google.android.material.navigation.NavigationView;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences sharedPreferences;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String accessToken = getIntent().getStringExtra("accessToken");
        String refreshToken = sharedPreferences.getString("refreshToken",null);

        TextView accessView = findViewById(R.id.accessToken);
        TextView refreshView = findViewById(R.id.refreshToken);

        accessView.setText(accessToken);
        refreshView.setText(refreshToken);*/
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.profile:{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
                break;
            }
            case R.id.orders:{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new OrderFragment()).commit();
                break;
            }
            case R.id.logout:{
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("accessToken");
                editor.remove("refreshToken");
                editor.commit();
                startActivity(new Intent(this, MainActivity.class));
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
