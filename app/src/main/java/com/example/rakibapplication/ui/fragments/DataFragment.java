package com.example.rakibapplication.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rakibapplication.adapters.DataAdapter;
import com.example.rakibapplication.data_model.ItemsModel;
import com.example.rakibapplication.databinding.FragmentDataBinding;

import java.util.List;


public class DataFragment extends Fragment {
    private FragmentDataBinding binding;

    private List<ItemsModel> dataList;
    private String act_tag;
    private DataAdapter dataAdapter;

    public DataFragment(List<ItemsModel> dataList, String act_tag) {
        this.dataList = dataList;
        this.act_tag = act_tag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDataBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.itemListView.setLayoutManager(new LinearLayoutManager(requireContext()));
        dataAdapter = new DataAdapter(requireContext(),dataList,act_tag);
        binding.itemListView.setAdapter(dataAdapter);




    }
}