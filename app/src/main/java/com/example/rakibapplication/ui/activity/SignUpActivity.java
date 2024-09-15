package com.example.rakibapplication.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.rakibapplication.R;
import com.example.rakibapplication.databinding.ActivitySignUpBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private String fullName = ""
            ,firstName = "", lastName = "",password = "", firstPassword = "",confirmPassword = "",email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass = s.toString();
                validPassword(pass);

                if (pass.isEmpty()) binding.etPasswordLayout.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!initUserData()) {
                    Toast.makeText(SignUpActivity.this, "Enter all data properly", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }

    private boolean isContainNumber(String pass) {
        for (Character ch: pass.toCharArray()) {
            if (Character.isDigit(ch)){
                return true;
            }
        }
        return false;
    }

    private boolean haveUpperCase(String password) {
        for (Character ch: password.toCharArray()) {
            if (Character.isUpperCase(ch)){
                return true;
            }
        }
        return false;
    }


    private boolean initUserData() {
        firstName = binding.etFirstName.getText().toString();
        if (firstName.isEmpty()) {
            binding.etFirstName.setError("First name is required!");
            return false;
        }
        lastName = binding.etLastName.getText().toString();

        fullName = firstName + lastName;

        email = binding.etEmail.getText().toString();

        if (!validEmail(email)) {
            binding.etEmail.setError("Email is required!");
            return false;
        }


        firstPassword = binding.etPassword.toString();
        if (firstPassword.isEmpty()) {
            binding.etPasswordLayout.setError("*required");
            return false;
        }

        if (!validPassword(password)) {
            binding.etPasswordLayout.setError("Enter password properly!");
            return false;
        }


        confirmPassword = binding.etConfirmPassword.toString();
        if (confirmPassword.isEmpty()) {
            binding.etPasswordLayout.setError("*required");
            return false;
        }

        if (!firstPassword.equals(confirmPassword)) return false;

        password = firstPassword;

        return true;
    }

    private boolean validPassword(String pass) {

        boolean isValid = false;

        if (pass.length() >= 8) {
            binding.hintMinLetter.setTextColor(ContextCompat.getColor(this,R.color.primary_color));
            isValid = true;
        }
        else {
            binding.hintMinLetter.setTextColor(ContextCompat.getColor(this,R.color.red));
            isValid = false;
        }

        if (haveUpperCase(pass)){
            binding.hintCap.setTextColor(ContextCompat.getColor(this,R.color.primary_color));
            isValid = true;
        }
        else {
            binding.hintCap.setTextColor(ContextCompat.getColor(this,R.color.red));
            isValid = false;

        }

        if (isContainNumber(pass)) {
            binding.hintDigit.setTextColor(ContextCompat.getColor(this,R.color.primary_color));
            isValid = true;
        }
        else {

            binding.hintDigit.setTextColor(ContextCompat.getColor(this,R.color.red));
            isValid = false;
        }

        if (isContainSymble(pass)) {
            binding.hintSymbol.setTextColor(ContextCompat.getColor(this,R.color.primary_color));
            isValid = true;
        }
        else {
            binding.hintSymbol.setTextColor(ContextCompat.getColor(this,R.color.red));
            isValid = false;
        }
        if (pass.isEmpty()) return false;
        return isValid;
    }

    private boolean isContainSymble(String pass) {
        Matcher matcher = Pattern.compile("[^a-zA-Z0-9]").matcher(pass);
        return matcher.find();
    }

    private boolean validEmail(String email) {

        if (email.isEmpty()) return false;

        if (!email.contains(".") || !email.contains("@")) {
            binding.etEmailLayout.setError("Enter email properly!");
        }
        if (email.contains(".") && email.contains("@")) {
            binding.etEmailLayout.setError(null);
            return true;
        }
        return false;
    }
}