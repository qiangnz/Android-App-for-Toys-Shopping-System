package com.example.sd6501_final_project_2173138.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.example.sd6501_final_project_2173138.R;
import com.example.sd6501_final_project_2173138.ReadMeActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    
        drawer = findViewById(R.id.drawer_layout);
    
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.open_navigation_drawer, R.string.close_navigation_drawer);
    
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    
        //link the fragment to the menu item clicked
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ToyListFragment()).commit();
        
    }
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_toyList:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ToyListFragment()).commit();
                break;
            case R.id.nav_orderList:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OrderListFragment()).commit();
                break;
            case R.id.nav_readme:
                Intent intent= new Intent(this, ReadMeActivity.class);
                startActivity(intent);
            case R.id.nav_sign_out:
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
                edit.clear();
                edit.apply();
                finish();
                break;
        }
    
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
