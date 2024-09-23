package com.example.rakibapplication.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.rakibapplication.R;
import com.example.rakibapplication.databinding.ActivityMainBinding;
import com.example.rakibapplication.datahandeler.RoomToApi;
import com.example.rakibapplication.ui.fragments.AddFragment;
import com.example.rakibapplication.ui.fragments.HomeFragment;
import com.example.rakibapplication.ui.fragments.ProfileFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding actMain;
    String fragmentName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actMain = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(actMain.getRoot());

        replaceFragment(new HomeFragment());


        if (haveNetworkConnectivity(MainActivity.this)) {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    new RoomToApi(MainActivity.this);
                }
            });
        }


        actMain.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.homeMenu) replaceFragment(new HomeFragment());
                else if (itemId == R.id.addMenu) replaceFragment(new AddFragment());
                else if (itemId == R.id.profileMenu) replaceFragment(new ProfileFragment());

                return true;
            }
        });
    }


    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFragmentsContainer,fragment);
        fragmentTransaction.commit();
    }

    private boolean haveNetworkConnectivity(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}