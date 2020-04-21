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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class DatosActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressDialog dialog;
    private Button btnReg,btnEdad, btnGenero;
    private EditText edUser, edPass, edRPass, edNom;
    private String user, password, nombre, rpassword;
    TextView iniSes;
    private Window window;
    AlertDialog.Builder opdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        iniSes = findViewById(R.id.iniSes);
        btnReg = findViewById(R.id.btnReg);

        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);
        edNom = findViewById(R.id.edNom);
        edRPass = findViewById(R.id.edRPass);
        btnEdad = findViewById(R.id.btnEdad);
        btnGenero = findViewById(R.id.btnGenero);

        iniSes.setOnClickListener(this);
        btnReg.setOnClickListener(this);
        btnGenero.setOnClickListener(this);
        btnEdad.setOnClickListener(this);

        String colorbarra = "#0B7EC5";

        this.window = getWindow();
        //barcolor
        window.setStatusBarColor(Color.parseColor(colorbarra));

        dialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iniSes:
                user=edUser.getText().toString();
                password=edPass.getText().toString();
                nombre=edNom.getText().toString();
                rpassword=edRPass.getText().toString();

                if (!user.isEmpty()||!password.isEmpty()||!nombre.isEmpty()||!rpassword.isEmpty())
                {
                    opdialog = new AlertDialog.Builder(this, R.style.DialogBasicCustomAzul);
                    opdialog.setMessage("Seguro que desea retroceder?")
                            .setIcon(R.drawable.advertencia)
                            .setTitle(R.string.advertencia)
                            .setPositiveButton(R.string.aceptar_sesion, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                   // Preferences.SaveUserData(CuentaActivity.this, " ", " ", " ", " ");
                                    startActivity(new Intent(DatosActivity.this, MainActivity.class));
                                    finish();
                                }
                            }).setNegativeButton(R.string.cancelar_sesion, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    opdialog.create();
                    opdialog.show();
                }
                else {
                    startActivity(new Intent(DatosActivity.this, MainActivity.class));
                    finish();
                }
                break;
            case R.id.btnReg:
                dialog = new ProgressDialog(this, R.style.DialogBasicCustomAzul);
                dialog.setMessage("Registrando...");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                user=edUser.getText().toString();
                password=edPass.getText().toString();
                nombre=edNom.getText().toString();
                rpassword=edRPass.getText().toString();

                if (user.isEmpty()||password.isEmpty()||nombre.isEmpty()||rpassword.isEmpty())
                {
                    Toast.makeText(this, R.string.llenar_campos, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else {
                    if (!password.equals(rpassword)) {
                        Toast.makeText(this, R.string.contr_no_coin, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else {
                        if (password.length() <= 6){
                            Toast.makeText(this, R.string.contr_6_car, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else
                            Usuario.Registrar(this, dialog, nombre, user, password, "M", "2020-04-10");
                    }
                }

                break;
        }
    }
}
