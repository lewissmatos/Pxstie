package com.example.pxstie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CuentaActivity extends AppCompatActivity implements View.OnClickListener {

    TextView btnVolver,txtMasInfo;
    AlertDialog.Builder opdialog;
    private Usuario user;
    private TextView txtNom, txtUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        btnVolver = findViewById(R.id.btnVolver);
        txtMasInfo = findViewById(R.id.txtMasInfo);
        txtNom = findViewById(R.id.txtNom);
        txtUser = findViewById(R.id.txtUser);

        btnVolver.setOnClickListener(this);
        txtMasInfo.setOnClickListener(this);

        user = Preferences.getUserData(this);

        txtNom.setText(user.getNombre());
        txtUser.setText("@" + user.getCorreo());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVolver:
                onBackPressed();
                break;
            case R.id.txtMasInfo:
                startActivity(new Intent(this, MasInfoActivity.class));
                break;

        }
    }
}
