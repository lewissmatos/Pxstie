package com.example.pxstie;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MensajesFragment extends Fragment implements View.OnClickListener {

    ImageView perfil;
    CardView msj1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mensajes, container, false);

        msj1 = view.findViewById(R.id.msj1);
        msj1.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.msj1:
                startActivity(new Intent(getContext(), ChatActivity.class));
        }
    }
}
