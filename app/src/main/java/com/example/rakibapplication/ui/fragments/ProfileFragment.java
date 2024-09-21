package com.example.rakibapplication.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rakibapplication.databinding.FragmentProfileBinding;
import com.example.rakibapplication.ui.activity.LoginActivity;
import com.example.rakibapplication.ui.activity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment{

    private FragmentProfileBinding binding;
    SharedPreferences sharedPrefs;
    String dbEmail,dbUserName;
    private Uri profileImageUri;
//    private Uri dbProfileImageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        sharedPrefs = getActivity().getSharedPreferences("com.info.application", Context.MODE_PRIVATE);
        dbUserName = sharedPrefs.getString("User_Name","");
        dbEmail = sharedPrefs.getString("User_Email","");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!isLoggedIn()) {
            binding.loginOrSupButton.setVisibility(View.VISIBLE);
            binding.tvUserName.setVisibility(View.INVISIBLE);
            binding.tvUserEmail.setVisibility(View.INVISIBLE);
            //binding.profilePhoto.setImageDrawable(getResources().getDrawable(R.drawable.profile_icon));
        }
        else {
            binding.loginOrSupButton.setVisibility(View.INVISIBLE);
            binding.tvUserName.setVisibility(View.VISIBLE);
            binding.tvUserEmail.setVisibility(View.VISIBLE);

//            String dbProfileImageRes = sharedPrefs.getString("profile_image_uri",null);

//            boolean check = dbProfileImageRes != null;
//            Log.d("Profile Image Check",check+"");
//            if (dbProfileImageRes != null) {
//                Log.d("Profile Image Check",dbProfileImageRes);
//                dbProfileImageUri = Uri.parse(dbProfileImageRes);
//                binding.profilePhoto.setImageURI(dbProfileImageUri);
//            }
        }

        if (dbUserName != null && dbEmail != null) {
            binding.tvUserName.setText(dbUserName);
            binding.tvUserEmail.setText(dbEmail);
        }


        binding.buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Selected the share button!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Selected the privacy button!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Selected the info button!", Toast.LENGTH_SHORT).show();
            }
        });
        binding.loginOrSupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), LoginActivity.class));
            }
        });

//        binding.btnSelectPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isLoggedIn()) {
//                   openImagePicker();
//                }
//                else {
//                    Toast.makeText(requireContext(), "Please log in first!", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

        binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLoggedIn()) {
                    Toast.makeText(requireContext(), "Log in or sign up your account first!", Toast.LENGTH_SHORT).show();
                }

                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();

                binding.tvUserName.setText("");
                binding.tvUserEmail.setText("");

                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.clear();
                Toast.makeText(requireContext(), "Logged out successfully!", Toast.LENGTH_SHORT).show();

//                OnLogout activity = (OnLogout) requireActivity();
//                activity.setFragmentOnLogout();

                startActivity(new Intent(requireContext(),MainActivity.class));
            }
        });
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 100 && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
//             profileImageUri = data.getData();
//
//            if (isLoggedIn()) {
//                binding.profilePhoto.setImageURI(profileImageUri);
//                requireActivity().getContentResolver().takePersistableUriPermission(profileImageUri, data.getFlags());
//
//                sharedPrefs.edit().putString("profile_image_uri",profileImageUri.toString()).apply();
//            }
//        }
//    }

    private boolean isLoggedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

//    private void checkPermissions() {
//        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION}, 1);
//        } else {
//            // Permissions already granted, you can proceed with image selection
//            openImagePicker();
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, you can proceed with image selection
//                openImagePicker();
//            } else {
//                Toast.makeText(requireContext(), "Permission denied to read storage", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

//    private void openImagePicker() {
//        Intent imageSelectIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//        imageSelectIntent.addCategory(Intent.CATEGORY_OPENABLE);
//        imageSelectIntent.setType("image/*");
//        startActivityForResult(imageSelectIntent, 100);
//    }


}