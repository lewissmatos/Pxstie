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

public class InteresesActivity extends AppCompatActivity implements View.OnClickListener{


    private ProgressDialog dialog;
    private Button btnSig,btnAnte;
    private EditText edBusca, edMusica, edHobbies, edCreencias;
    private String us, pass, nom, rpass;
    TextView iniSes;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intereses);

        iniSes = findViewById(R.id.iniSes);
        btnSig = findViewById(R.id.btnSig);
        btnAnte = findViewById(R.id.btnAnte);

        edBusca = findViewById(R.id.edBusca);
        edMusica = findViewById(R.id.edMusica);
        edHobbies = findViewById(R.id.edHobbies);
        edCreencias = findViewById(R.id.edCreencias);

        iniSes.setOnClickListener(this);
        btnSig.setOnClickListener(this);
        btnAnte.setOnClickListener(this);

        String colorbarra = "#FF521C";

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
                startActivity(new Intent(this, InformacionActivity.class));
                finish();
                break;
            case R.id.btnSig:
                startActivity(new Intent(this, SocialActivity.class));
                finish();
        }
    }
}
