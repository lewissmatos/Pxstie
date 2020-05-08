package com.example.pxstie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.prefs.Preferences;

public class DatosActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressDialog dialog;
    private Button btnReg,btnEdad;
    private Spinner spGenero;
    private EditText edUser, edPass, edRPass, edNom, edGenero;
    private String user, password, nombre, rpassword, edad, genero;
    TextView iniSes;
    private Window window;
    AlertDialog.Builder opdialog;
    private String[] generos = new String[]{"GÉNERO", "Masculino", "Femenino", "Especificar"};

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
        spGenero = findViewById(R.id.spGenero);
        edGenero = findViewById(R.id.edGenero);

        iniSes.setOnClickListener(this);
        btnReg.setOnClickListener(this);
        //btnGenero.setOnClickListener(this);
        btnEdad.setOnClickListener(this);

        spGenero.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, generos));

        spGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getSelectedItem().toString();
                if (item.equalsIgnoreCase("especificar")){
                    edGenero.setVisibility(View.VISIBLE);
                }
                else
                    edGenero.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String colorbarra = "#F8F8F8";

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
                    opdialog = new AlertDialog.Builder(this, R.style.DialogBasicCustomBlack);
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
                dialog = new ProgressDialog(this, R.style.DialogBasicCustomRose);
                dialog.setMessage("Registrando...");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                user=edUser.getText().toString();
                password=edPass.getText().toString();
                nombre=edNom.getText().toString();
                rpassword=edRPass.getText().toString();
                edad = btnEdad.getText().toString();
                if(spGenero.getSelectedItem().toString().equalsIgnoreCase("especificar"))
                {
                    genero = edGenero.getText().toString();
                }
                else {
                    genero = spGenero.getSelectedItem().toString();
                }

                if (user.isEmpty()||password.isEmpty()||nombre.isEmpty()||rpassword.isEmpty()||edad.equalsIgnoreCase("edad")||genero.equalsIgnoreCase("genero"))
                {
                    Toast.makeText(this, R.string.llenar_campos, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else {
                    if (!password.equals(rpassword)){
                        Toast.makeText(this, R.string.contr_no_coin, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else {
                        if (password.length() <= 6){
                            Toast.makeText(this, R.string.contr_6_car, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else
                            Usuario.Registrar(this, dialog, nombre, user, password, genero, edad);
                    }
                }
                break;
            case R.id.btnEdad:
                getDate();
                break;
        }
    }

    private void getDate(){
        final Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);

        DatePickerDialog datepicker = new DatePickerDialog(DatosActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                btnEdad.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        } , 2000, 12, 12);
        //datepicker.getDatePicker().setMinDate(System.currentTimeMillis());
        //c.set(1995, 01, 01);
        //datepicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        datepicker.show();
    }
}
