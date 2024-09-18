package com.example.rakibapplication.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.rakibapplication.R;
import com.example.rakibapplication.database.UserData;
import com.example.rakibapplication.databinding.ActivitySignUpBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private String fullName = ""
            ,firstName = "", lastName = "",password = "", firstPassword = "",confirmPassword = "",email = "";
    private UserData userData;

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
                String mail = s.toString();
                if (mail.isEmpty()) {
                    binding.etEmailLayout.setError(null);
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String mail = s.toString();
                validEmail(mail);
            }
        });

        binding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass = s.toString();

                if (pass.isEmpty()){
                    binding.etPasswordLayout.setError(null);
                    return;
                }

                parseValidPassword(pass);

            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("init_user_data_check",initUserData()+"");
                if (!initUserData()) return;
                Toast.makeText(SignUpActivity.this, "data initialized", Toast.LENGTH_SHORT).show();


                Log.d("Emailtext",email);
                userData = new UserData(SignUpActivity.this,fullName,password,email);
            }
        });

    }

    private boolean isValidPassword(String password) {
//        if (!password.isEmpty()
//                && isContainSymble(password)
//                && isContainNumber(password)
//                &&haveUpperCase(password)
//                && password.length() <= 8) {
//            return true;
//        }
//        return false;
        return true;
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
        Log.d("first_name",firstName);
        if (firstName.isEmpty()) {
            binding.etFirstName.setError("First name is required!");
            return false;
        }
        lastName = binding.etLastName.getText().toString();

        fullName = firstName +" "+ lastName;
        Log.d( "fullname ",fullName);

        email = binding.etEmail.getText().toString();

        Log.d("EmailValidityCheck",validEmail(email)+"");
        if (!validEmail(email)) {
            binding.etEmail.setError("Email is required!");
            return false;
        }
        Log.d("Email: ",email);


        firstPassword = binding.etPassword.getText().toString();
        Log.d("first_pass",firstPassword);

        if (!isValidPassword(password)) {
            binding.etPasswordLayout.setError("Enter password properly!");
            return false;
        }


        confirmPassword = binding.etConfirmPassword.getText().toString();

        if (confirmPassword.isEmpty()) {
            binding.etPasswordLayout.setError("*required");
            return false;
        }

        Log.d("passCheck",firstPassword.equals(confirmPassword)+"");
        if (!firstPassword.equals(confirmPassword)){
            binding.etConfirmPassword.setError("Password should be same.");
            return false;
        }

        password = firstPassword;
        Log.d("final_password",password);

        return true;
    }

    private void parseValidPassword(String pass) {

        if (pass.length() >= 8) {
            binding.hintMinLetter.setTextColor(ContextCompat.getColor(this,R.color.primary_color));
        }
        else {
            binding.hintMinLetter.setTextColor(ContextCompat.getColor(this,R.color.red));
        }

        if (haveUpperCase(pass)){
            binding.hintCap.setTextColor(ContextCompat.getColor(this,R.color.primary_color));
        }
        else {
            binding.hintCap.setTextColor(ContextCompat.getColor(this,R.color.red));
        }

        if (isContainNumber(pass)) {
            binding.hintDigit.setTextColor(ContextCompat.getColor(this,R.color.primary_color));
        }
        else {
            binding.hintDigit.setTextColor(ContextCompat.getColor(this,R.color.red));
        }

        if (isContainSymble(pass)) {
            binding.hintSymbol.setTextColor(ContextCompat.getColor(this,R.color.primary_color));
        }
        else {
            binding.hintSymbol.setTextColor(ContextCompat.getColor(this,R.color.red));
        }

    }

    private boolean isContainSymble(String pass) {
        Matcher matcher = Pattern.compile("[^a-zA-Z0-9]").matcher(pass);
        return matcher.find();
    }

    private boolean validEmail(String email) {

        if (email.isEmpty()) return false;

//        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
//        Matcher m = p.matcher(email);
//
//        Log.d("email is valid",m.find()+"");
//        return m.find();
        return true;
    }
}