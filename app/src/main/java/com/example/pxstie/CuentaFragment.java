package com.example.pxstie;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class CuentaFragment extends Fragment implements View.OnClickListener {
    TextView txtMasInfo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cuenta, container, false);

        txtMasInfo = view.findViewById(R.id.txtMasInfo);

        txtMasInfo.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.txtMasInfo:
                startActivity(new Intent(getContext(), MasInfoActivity.class));
                break;
        }
    }
}
