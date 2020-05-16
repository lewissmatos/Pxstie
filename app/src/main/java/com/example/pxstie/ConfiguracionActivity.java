package com.example.pxstie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ConfiguracionActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView btnVolver;
    private TextView txtNom, txtUser;
    private Usuario user;
    RelativeLayout lyCuenta, lyLogOut;
    AlertDialog.Builder opdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        btnVolver  =findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(this);
        lyCuenta.setOnClickListener(this);
        lyLogOut.setOnClickListener(this);
        txtNom = findViewById(R.id.txtNom);
        txtUser = findViewById(R.id.txtUser);
        lyCuenta = findViewById(R.id.lyCuenta);
        lyLogOut = findViewById(R.id.lyLogOut);

        user = Preferences.getUserData(this);

        txtNom.setText(user.getNombre());
        txtUser.setText("@" + user.getCorreo());
        opdialog = new AlertDialog.Builder(this, R.style.DialogBasicCustomBlack);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVolver:
                startActivity(new Intent(this, HomeScreenActivity.class));
                break;
            case R.id.lyCuenta:
                startActivity(new Intent(this, CuentaActivity.class));
                break;
            case R.id.lyLogOut:
                opdialog.setMessage("Seguro que desea cerrar sesión?")
                        .setTitle(R.string.advertencia)
                        .setIcon(R.drawable.advertencia)
                        .setPositiveButton(R.string.aceptar_sesion, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Preferences.SaveUserData(ConfiguracionActivity.this, "", "", "","", "", "");
                                startActivity(new Intent(ConfiguracionActivity.this, MainActivity.class));
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
