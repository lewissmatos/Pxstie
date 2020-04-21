package com.example.pxstie;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class OpcionesFragment extends Fragment implements View.OnClickListener{

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_opciones, container, false);

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
