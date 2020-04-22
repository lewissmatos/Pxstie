package com.example.pxstie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edUser, edPass;
    private Button btnIniSes;
    private TextView btnReg;
    private String user, password;
    private ProgressDialog dialog;
    private Usuario userr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userr = Preferences.getUserData(this);
        if (!userr.getNombre().equalsIgnoreCase(" ")){
            startActivity(new Intent(this, ContenedorActivity.class));
            finish();
        }

        btnReg = findViewById(R.id.btnReg);
        btnIniSes = findViewById(R.id.btnIniSes);

        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);

        btnReg.setOnClickListener(this);
        btnIniSes.setOnClickListener(this);

        dialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReg :
                startActivity(new Intent(this, DatosActivity.class));
                finish();
                break;
            case R.id.btnIniSes:
                dialog = new ProgressDialog(this, R.style.DialogBasicCustomRose);
                dialog.setMessage("Iniciando Sesión...");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                user=edUser.getText().toString();
                password=edPass.getText().toString();

                if (user.isEmpty()||password.isEmpty())
                {
                    Toast.makeText(this, R.string.llenar_campos, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else {
                    Usuario.IniciarSesion(this, dialog, user, password);
                }
                break;
        }
    }
}
