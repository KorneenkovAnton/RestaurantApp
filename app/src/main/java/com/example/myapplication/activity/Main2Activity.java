package com.example.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myapplication.DTO.LoginResponseDto;
import com.example.myapplication.DTO.UserDto;
import com.example.myapplication.R;
import com.example.myapplication.async.AsyncTaskResult;
import com.example.myapplication.async.UserAsyncTask;
import com.example.myapplication.fragment.CartFragment;
import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.fragment.OrderFragment;
import com.example.myapplication.fragment.ProfileFragment;
import com.example.myapplication.service.TokenService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.ExecutionException;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;
    private String accessToken;
    private TokenService tokenService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment(accessToken)).commit();
        navigationView.setCheckedItem(R.id.main);
        floatingActionButton.show();
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
                UserAsyncTask userAsyncTask = new UserAsyncTask();
                try {
                    AsyncTaskResult<UserDto> result = userAsyncTask.execute(accessToken).get();
                    if(result.getException() == null){
                        if(result.getStatus() == 200 && result.getResult() != null){
                            floatingActionButton.hide();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new ProfileFragment(result.getResult(), accessToken)).commit();
                        }else {
                            if(result.getStatus() == 403){
                                /*String refreshToken = sharedPreferences.getString("refreshToken", null);
                                //LoginResponseDto loginResponseDto = tokenService.refreshToken(refreshToken);
                                tokenService.saveTokens(sharedPreferences,refreshToken);*/
                            }
                        }
                    }
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

                break;
            }
            case R.id.orders:{
                floatingActionButton.hide();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new OrderFragment()).commit();
                break;
            }
            case R.id.main:{
                floatingActionButton.show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment(accessToken)).commit();
                break;
            }
            case R.id.logout:{
                tokenService.deleteTokens(sharedPreferences);
                floatingActionButton.hide();
                startActivity(new Intent(this, MainActivity.class));
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void init(){
        accessToken = getIntent().getStringExtra("accessToken");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);

        tokenService = new TokenService();
    }

    @Override
    public void onClick(View v) {
        floatingActionButton.hide();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new CartFragment()).commit();
    }
}
