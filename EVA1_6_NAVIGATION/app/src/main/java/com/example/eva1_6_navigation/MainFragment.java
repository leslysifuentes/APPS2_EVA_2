package com.example.eva1_6_navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        Button b_blue, b_red;
        b_blue = view.findViewById(R.id.b_blue);
        b_red = view.findViewById(R.id.b_red);
        b_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("prueba", "mensaje por bundle");
                MainFragmentDirections.ActionMainFragmentToRedFragment action = MainFragmentDirections.actionMainFragmentToRedFragment();
                action.setAValor(5000);
                navController.navigate(action);
              //  navController.navigate(R.id.action_mainFragment_to_redFragment, bundle);
            }
        });
        b_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_mainFragment_to_blueFragment);
            }
        });

    }
}