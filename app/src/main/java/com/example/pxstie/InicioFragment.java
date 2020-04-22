package com.example.pxstie;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class InicioFragment extends Fragment implements View.OnClickListener {

    ImageView btnMensajes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_inicio, container, false);

        btnMensajes = view.findViewById(R.id.btnMensajes);
        btnMensajes.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMensajes:
                startActivity(new Intent(getContext(), MensajesActivity.class));
        }
    }
}
