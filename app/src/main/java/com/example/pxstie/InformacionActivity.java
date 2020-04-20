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
import android.widget.Toast;

public class InformacionActivity extends AppCompatActivity implements View.OnClickListener{


    private ProgressDialog dialog;
    private Button btnSig,btnAnte;
    private EditText edGenero, edOcupacion, edPais, edCiudad,edMusica, edHobbies;
    private RadioButton rbMas, rbFem;
    private String edad, ciudad, ocup, pais, hobbies, musica;
    TextView iniSes;
    private Window window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        iniSes = findViewById(R.id.iniSes);
        rbMas = findViewById(R.id.rbMas);
        rbFem = findViewById(R.id.rbFem);

        edGenero = findViewById(R.id.edGenero);
        edOcupacion = findViewById(R.id.edOcupacion);
        edPais = findViewById(R.id.edPais);
        edCiudad = findViewById(R.id.edCiudad);
        edMusica = findViewById(R.id.edMusica);
        edHobbies = findViewById(R.id.edHobbies);

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

                hobbies=edHobbies.getText().toString();
                musica=edMusica.getText().toString();
                ocup=edOcupacion.getText().toString();
                pais=edPais.getText().toString();
                ciudad=edCiudad.getText().toString();

                if (!musica.isEmpty()||!hobbies.isEmpty()||!ocup.isEmpty()||!pais.isEmpty()||!ciudad.isEmpty())
                {
                    Toast.makeText(this, "Seguro que desesa retroceder?", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                break;
            case R.id.btnAnte:
                hobbies=edHobbies.getText().toString();
                musica=edMusica.getText().toString();
                ocup=edOcupacion.getText().toString();
                pais=edPais.getText().toString();
                ciudad=edCiudad.getText().toString();

                if (!musica.isEmpty()||!hobbies.isEmpty()||!ocup.isEmpty()||!pais.isEmpty()||!ciudad.isEmpty())
                {
                    Toast.makeText(this, "Seguro que desesa retroceder?", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(this, DatosActivity.class));
                    finish();
                }
                break;
        }
    }
}
