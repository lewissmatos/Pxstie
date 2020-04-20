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
import android.widget.RadioButton;
import android.widget.TextView;

public class InformacionActivity extends AppCompatActivity implements View.OnClickListener{


    private ProgressDialog dialog;
    private Button btnSig,btnAnte;
    private EditText edGenero, edEdad, edOcupacion, edPais, edCiudad;
    private RadioButton rbMas, rbFem;
    private String us, pass, nom, rpass;
    TextView iniSes;
    private Window window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        iniSes = findViewById(R.id.iniSes);
        btnSig = findViewById(R.id.btnSig);
        btnAnte = findViewById(R.id.btnAnte);
        rbMas = findViewById(R.id.rbMas);
        rbFem = findViewById(R.id.rbFem);

        edGenero = findViewById(R.id.edGenero);
        edEdad = findViewById(R.id.edEdad);
        edOcupacion = findViewById(R.id.edOcupacion);
        edPais = findViewById(R.id.edPais);
        edCiudad = findViewById(R.id.edCiudad);

        iniSes.setOnClickListener(this);
        btnSig.setOnClickListener(this);
        btnAnte.setOnClickListener(this);
        rbMas.setOnClickListener(this);
        rbFem.setOnClickListener(this);

        String colorbarra = "#18B530";

        this.window = getWindow();
        //barcolor
        window.setStatusBarColor(Color.parseColor(colorbarra));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iniSes:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.btnAnte:
                startActivity(new Intent(this, DatosActivity.class));
                finish();
                break;
            case R.id.btnSig:
                startActivity(new Intent(this, InteresesActivity.class));
                finish();
                break;
        }
    }
}
