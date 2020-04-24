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

public class CuentaActivity extends AppCompatActivity implements View.OnClickListener{
    Window window;
    Button btnCerrarSesion;
    TextView btnVolver,txtMasInfo;
    AlertDialog.Builder opdialog;
    private Usuario user;
    private TextView txtNom, txtUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        btnVolver = findViewById(R.id.btnVolver);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        txtMasInfo = findViewById(R.id.txtMasInfo);
        txtNom = findViewById(R.id.txtNom);
        txtUser = findViewById(R.id.txtUser);

        btnCerrarSesion.setOnClickListener(this);
        btnVolver.setOnClickListener(this);
        txtMasInfo.setOnClickListener(this);

        String colorbarra = "#0B7EC5";
        this.window = getWindow();
        window.setStatusBarColor(Color.parseColor(colorbarra));

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
                finish();
                break;
            case R.id.btnCerrarSesion:
                opdialog = new AlertDialog.Builder(this, R.style.DialogBasicCustomAzul);
                opdialog.setMessage("Seguro que desea cerrar sesión?")
                        .setIcon(R.drawable.advertencia)
                        .setTitle(R.string.advertencia)
                        .setPositiveButton(R.string.aceptar_sesion, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Preferences.SaveUserData(CuentaActivity.this, "", "", "","", "", "");
                                startActivity(new Intent(CuentaActivity.this, MainActivity.class));
                                finish();
                            }
                        }).setNegativeButton(R.string.cancelar_sesion, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                opdialog.create();
                opdialog.show();
            break;
        }
    }
}
