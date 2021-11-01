package com.example.moviedb.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.moviedb.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavHostFragment navHostFragment;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        toolbar = findViewById(R.id.toolbar_main_menu);
        setSupportActionBar(toolbar);
        bottomNavigationView = findViewById(R.id.bottom_nav_main_menu);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_fragment_main_menu);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.nowPlayingFragment, R.id.upComingFragment).build();
        NavigationUI.setupActionBarWithNavController(MainMenuActivity.this, navHostFragment.getNavController(), appBarConfiguration);

        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());

    }

    @Override
    public boolean onSupportNavigateUp() {
        navHostFragment.getNavController().navigateUp();
        return super.onSupportNavigateUp();
    }
}