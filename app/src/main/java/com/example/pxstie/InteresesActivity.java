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

public class InteresesActivity extends AppCompatActivity implements View.OnClickListener{


    private ProgressDialog dialog;
    private Button btnSig,btnAnte;
    private EditText edBusca, edMusica, edHobbies, edCreencias;
    private String busca, musica, hobbies, creencias;
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
                busca=edBusca.getText().toString();
                musica=edMusica.getText().toString();
                hobbies=edHobbies.getText().toString();
                creencias=edCreencias.getText().toString();

                if (!busca.isEmpty()||!musica.isEmpty()||!hobbies.isEmpty()||!creencias.isEmpty())
                {
                    Toast.makeText(this, "Seguro que desesa retroceder?", Toast.LENGTH_SHORT).show();
                }
                else {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                }
                break;
            case R.id.btnAnte:

                musica=edMusica.getText().toString();
                hobbies=edHobbies.getText().toString();
                creencias=edCreencias.getText().toString();

                if (!musica.isEmpty()||!hobbies.isEmpty()||!creencias.isEmpty())
                {
                    Toast.makeText(this, "Seguro que desesa retroceder?", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(this, InformacionActivity.class));
                    finish();
                }

                break;
            case R.id.btnSig:

                musica=edMusica.getText().toString();
                hobbies=edHobbies.getText().toString();
                creencias=edCreencias.getText().toString();
                if (musica.isEmpty()||hobbies.isEmpty()||creencias.isEmpty())
                {
                    Toast.makeText(this, R.string.llenar_campos, Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(this, SocialActivity.class));
                    finish();
                }
                break;
        }
    }
}
