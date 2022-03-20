package com.example.statapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements LoginFragment.FragmentLoginListener, RegisterFragment.FragmentRegisterListener {
    private LoginFragment fragmentLogin;
    private RegisterFragment fragmentRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentLogin = new LoginFragment();
        fragmentRegister = new RegisterFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_register, fragmentRegister)
                .replace(R.id.container_login, fragmentLogin)
                .commit();


        // Code below is for navigating between fragments
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch(item.getItemId()){
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.nav_game:
                        selectedFragment = new GameFragment();
                        break;
                    case R.id.nav_stats:
                        selectedFragment = new StatsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                return true;
            }

        });

    }

    @Override
    public void onInputLoginSent(CharSequence input) {
        fragmentRegister.updateEditText(input);
    }

    @Override
    public void onInputRegisterSent(CharSequence input) {
        fragmentLogin.updateEditText(input);
    }
}