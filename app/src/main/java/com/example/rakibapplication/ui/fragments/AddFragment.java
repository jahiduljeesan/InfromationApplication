package com.example.rakibapplication.ui.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rakibapplication.R;
import com.example.rakibapplication.databinding.FragmentAddBinding;

public class AddFragment extends Fragment {


    private FragmentAddBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new AlertDialog.Builder(requireContext())
                .setIcon(R.drawable.ic_launcher_background)
                .setTitle("নোটিশ!")
                .setMessage("নোট করার ফিচারটি," +
                        "\nখুব শিঘ্রই এপ এ যুক্ত করা হবে।")
                .create()
                .show();

    }
}