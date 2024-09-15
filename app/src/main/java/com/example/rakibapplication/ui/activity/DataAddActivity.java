package com.example.rakibapplication.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rakibapplication.data_model.ItemsModel;
import com.example.rakibapplication.R;
import com.example.rakibapplication.database.room.ContentDatabaseRoom;
import com.example.rakibapplication.databinding.ActivityDataAddBinding;

public class DataAddActivity extends AppCompatActivity {

    String[] categorys;
    ActivityDataAddBinding actDataAdd;
    private String title = "",writer = "",content = "",category = "";
    private int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actDataAdd = ActivityDataAddBinding.inflate(getLayoutInflater());
        setContentView(actDataAdd.getRoot());

        categorys= getResources().getStringArray(R.array.category);
        // adding category to spinner.
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,categorys);
        actDataAdd.spnCategory.setAdapter(spinnerAdapter);

        actDataAdd.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        actDataAdd.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getData()) return;

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        ContentDatabaseRoom.getContentRoom(DataAddActivity.this)
                                .dataDao()
                                .insertData(new ItemsModel(id,title,writer,category,content));
                    }
                });

                Toast.makeText(DataAddActivity.this, "ধন্যবাদ! তথ্য সাবমিট হয়েছে।", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean getData() {
        title = actDataAdd.etTitle.getText().toString();
        writer = actDataAdd.etWriterName.getText().toString();
        content = actDataAdd.etContent.getText().toString();
        category = actDataAdd.spnCategory.getText().toString().toLowerCase();

        if (title.isEmpty()) {
            actDataAdd.etTitle.setError("অনুগ্রহ নাম প্রবেশ করুন!");
            return false;
        }
        if (writer.isEmpty()) {
            actDataAdd.etWriterName.setError("অনুগ্রহ করে লেখকের নাম প্রবেশ করুন!");
            return false;
        }
        if (category.isEmpty()) {
            return false;
        }
        if (content.isEmpty()) {
            actDataAdd.etContent.setError("অনুগ্রহ করে তথ্য প্রবেশ করুন!");
            return false;
        }

        return true;

    }
}