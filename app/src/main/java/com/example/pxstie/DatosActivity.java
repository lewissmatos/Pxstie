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

public class DatosActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressDialog dialog;
    private Button btnSig;
    private EditText edUser, edPass, edRPass, edNom;
    private String us, pass, nom, rpass;
    TextView iniSes;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        iniSes = findViewById(R.id.iniSes);
        btnSig = findViewById(R.id.btnSig);

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
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.btnSig:
                startActivity(new Intent(this, InformacionActivity.class));
                finish();
                break;
        }
    }
}
