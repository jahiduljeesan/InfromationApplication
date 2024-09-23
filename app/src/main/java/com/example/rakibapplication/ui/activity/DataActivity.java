package com.example.rakibapplication.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.rakibapplication.datahandeler.AppData;
import com.example.rakibapplication.databinding.ActivityDataBinding;
import com.example.rakibapplication.ui.fragments.DataFragment;

public class DataActivity extends AppCompatActivity {

    ActivityDataBinding actData;
    private Fragment activeFragment;
    private String  act_tag, act_name;
    AppData appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actData = ActivityDataBinding.inflate(getLayoutInflater());
        setContentView(actData.getRoot());

        appData = new AppData(this);


    }
    private void setButtonData() {
        act_tag = getIntent().getStringExtra("Act_name");

        switch (act_tag) {
            case "gaan" : activeFragment = new DataFragment(appData.gaan_list,"gaan");
            case "gazal" : activeFragment = new DataFragment(appData.gaan_list,"gazal");
        }
    }
}