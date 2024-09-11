package com.example.rakibapplication.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rakibapplication.databinding.ActivityDataBinding;

public class DataActivity extends AppCompatActivity {

    ActivityDataBinding actData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actData = ActivityDataBinding.inflate(getLayoutInflater());
        setContentView(actData.getRoot());

    }
}