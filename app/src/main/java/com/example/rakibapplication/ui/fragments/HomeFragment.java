package com.example.rakibapplication.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rakibapplication.databinding.FragmentHomeBinding;
import com.example.rakibapplication.ui.activity.DataAddActivity;


public class HomeFragment extends Fragment {

   FragmentHomeBinding fragHome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragHome = FragmentHomeBinding.inflate(inflater);
        return fragHome.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragHome.marqueeText.setSelected(true);

        fragHome.dataAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(requireContext(), DataAddActivity.class));
            }
        });
    }
}