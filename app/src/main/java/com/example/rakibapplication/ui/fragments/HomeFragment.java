package com.example.rakibapplication.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rakibapplication.R;
import com.example.rakibapplication.databinding.FragmentHomeBinding;
import com.example.rakibapplication.ui.activity.DataAddActivity;
import com.example.rakibapplication.ui.activity.LoginActivity;


public class HomeFragment extends Fragment {

   FragmentHomeBinding fragHome;
   ActionBarDrawerToggle drawerToggle;
   DrawerLayout drawerLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragHome = FragmentHomeBinding.inflate(inflater,container,false);
        //drawerLayout = fragHome.drawerLayout;
        return fragHome.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        drawerToggle = new ActionBarDrawerToggle(requireActivity(), fragHome.drawerLayout ,R.string.nav_open,R.string.nav_close);
        fragHome.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

         fragHome.homeViewContent.marqueeText.setSelected(true);

        fragHome.homeViewContent.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fragHome.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    fragHome.drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    fragHome.drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });

        fragHome.homeViewContent.dataAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(requireContext(), DataAddActivity.class));
            }
        });
        fragHome.homeViewContent.btQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), LoginActivity.class));
            }
        });

    }
}