package com.example.rakibapplication.database;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.rakibapplication.ui.activity.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserData {

    //entities
    private String fullName = "",password = "",email = "";
    FirebaseAuth auth;
    DatabaseReference userRef;
    FirebaseUser user;
    Context context;
    ProgressDialog progressDialog;
    public static volatile UserData userData;

    public UserData(Context context, String fullName, String password,String email) {
        this.context = context;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        setDatafirebase();
    }

//    public UserData() {
//    }
//
//    public static UserData getUserData() {
//        if (userData == null) {
//            userData = new UserData();
//        }
//        return userData;
//    }

    private void setDatafirebase() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Signing Up...");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false); // Prevent manual dismissal
        progressDialog.show();

        auth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference("Users");

        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        user = auth.getCurrentUser();
                        if (user != null) {
                            String uid = user.getUid();
                            Log.d("Firebase", "User UID: " + uid);

                            // Check if fullName is not null or empty
                            if (fullName == null || fullName.isEmpty()) {
                                Log.e("Firebase", "Full name is null or empty.");
                                if (progressDialog.isShowing()) progressDialog.dismiss();
                                Toast.makeText(context, "Full name is missing.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            // Save user's full name in the database
                            userRef.child(uid).child("name").setValue(fullName)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d("Firebase", "User data saved successfully.");
                                            if (progressDialog.isShowing()) progressDialog.dismiss();
                                            Toast.makeText(context, "Signed up and user data saved successfully!", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e("Firebase", "Failed to save user data: " + e.getMessage(), e);
                                            if (progressDialog.isShowing()) progressDialog.dismiss();
                                            Toast.makeText(context, "Failed to save user data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            Log.e("Firebase", "User is null after sign-up.");
                            if (progressDialog.isShowing()) progressDialog.dismiss();
                            Toast.makeText(context, "Sign-up failed, user is null.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Firebase", "Sign-up failed: " + e.getMessage(), e);
                        if (progressDialog.isShowing()) progressDialog.dismiss();
                        Toast.makeText(context, "Sign-up failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
