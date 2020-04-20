package com.example.pxstie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DatosActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressDialog dialog;
    private Button btnSig;
    private EditText edUser, edPass, edRPass, edNom;
    private String user, password, nombre, rpassword;
    TextView iniSes;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        iniSes = findViewById(R.id.iniSes);
        btnSig = findViewById(R.id.btnSig);

        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);
        edNom = findViewById(R.id.edNom);
        edRPass = findViewById(R.id.edRPass);

        iniSes.setOnClickListener(this);
        btnSig.setOnClickListener(this);

        String colorbarra = "#0B7EC5";

        this.window = getWindow();
        //barcolor
        window.setStatusBarColor(Color.parseColor(colorbarra));
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
                    Toast.makeText(this, "Seguro que desesa retroceder?", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                break;
            case R.id.btnSig:
                user=edUser.getText().toString();
                password=edPass.getText().toString();
                nombre=edNom.getText().toString();
                rpassword=edRPass.getText().toString();

                if (user.isEmpty()||password.isEmpty()||nombre.isEmpty()||rpassword.isEmpty())
                {
                    Toast.makeText(this, R.string.llenar_campos, Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(this, InformacionActivity.class));
                    finish();
                }
                break;
        }
    }
}
