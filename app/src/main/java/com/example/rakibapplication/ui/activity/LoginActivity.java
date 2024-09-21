package com.example.rakibapplication.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rakibapplication.R;
import com.example.rakibapplication.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity{

    private ActivityLoginBinding binding;
    FirebaseAuth auth;
    DatabaseReference userRef;
    FirebaseUser user;
    ProgressDialog progressDialog;
    private String email = "",password = "";
    String user_fullname;
    SharedPreferences sharedPref;
    UserLogInData userLogInData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Log.d("Back button check", "onClick: btn Back ");
            }
        });
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!initData()) return;
                Toast.makeText(LoginActivity.this, "This is a test", Toast.LENGTH_SHORT).show();
                parseSignIn();
            }
        });
    }

    private boolean initData() {
        email = binding.etEmail.getText().toString();
        if (email.isEmpty()) {
            binding.etEmailLayout.setError("Email can't be empty");
            return false;
        }
        password = binding.etPassword.getText().toString();
        if (email.isEmpty()) {
            binding.etPasswordLayout.setError("Password can't be empty");
            return false;
        }
        return true;
    }

    private void parseSignIn() {
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setTitle("Logging in...");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();


            auth = FirebaseAuth.getInstance();
            userRef = FirebaseDatabase.getInstance().getReference("Users");

            auth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            user = authResult.getUser();
                            if (user != null) {
                                String uid = user.getUid();

                                //getting user name
                                userRef.child(uid).child("username").get()
                                        .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    if(progressDialog.isShowing()) progressDialog.dismiss();

                                                    user_fullname = task.getResult().getValue(String.class);

                                                    sharedPref = getSharedPreferences("com.info.application", Context.MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = sharedPref.edit();
                                                    editor.putString("User_Name",user_fullname);
                                                    editor.putString("User_Email",email);
                                                    editor.apply();


                                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                                    startActivity(intent);

                                                }
                                                else {
                                                    Toast.makeText(LoginActivity.this, "Log in failed!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (progressDialog.isShowing()) progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

    }
    public interface UserLogInData{
        void getUserLoginData(String name, String email);
    }
}