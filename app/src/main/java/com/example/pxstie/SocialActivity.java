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

public class SocialActivity extends AppCompatActivity implements View.OnClickListener{


    private ProgressDialog dialog;
    private Button btnFin ,btnAnte;
    private EditText edInstragram, edFacebook, edTwitter, edTelefono;
    private String fb, ig, tt, tel;
    TextView iniSes;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        iniSes = findViewById(R.id.iniSes);
        btnFin = findViewById(R.id.btnFin);
        btnAnte = findViewById(R.id.btnAnte);

        edInstragram = findViewById(R.id.edInstragram);
        edFacebook = findViewById(R.id.edFacebook);
        edTwitter = findViewById(R.id.edTwitter);
        edTelefono = findViewById(R.id.edTelefono);

        iniSes.setOnClickListener(this);
        btnFin.setOnClickListener(this);
        btnAnte.setOnClickListener(this);

        String colorbarra = "#AD1AC6";

        this.window = getWindow();
        //barcolor
        window.setStatusBarColor(Color.parseColor(colorbarra));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iniSes:
                ig=edInstragram.getText().toString();
                fb=edFacebook.getText().toString();
                tt=edTwitter.getText().toString();
                tel=edTelefono.getText().toString();

                if (!ig.isEmpty()||!fb.isEmpty()||!tt.isEmpty()||!tel.isEmpty())
                {
                    Toast.makeText(this, "Seguro que desesa retroceder?", Toast.LENGTH_SHORT).show();
                }
                else {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                }
                break;
            case R.id.btnAnte:

                ig=edInstragram.getText().toString();
                fb=edFacebook.getText().toString();
                tt=edTwitter.getText().toString();
                tel=edTelefono.getText().toString();

                if (!ig.isEmpty()||!fb.isEmpty()||!tt.isEmpty()||!tel.isEmpty())
                {
                    Toast.makeText(this, "Seguro que desesa retroceder?", Toast.LENGTH_SHORT).show();
                }
                else {
                   // startActivity(new Intent(this, InteresesActivity.class));
                    finish();
                }
                break;
            case R.id.btnFin:

                ig=edInstragram.getText().toString();
                fb=edFacebook.getText().toString();
                tt=edTwitter.getText().toString();
                tel=edTelefono.getText().toString();

                if (ig.isEmpty()||fb.isEmpty()||tt.isEmpty()||tel.isEmpty())
                {
                    Toast.makeText(this, R.string.llenar_campos, Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                break;
        }
    }
}
