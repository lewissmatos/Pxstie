package com.example.pxstie;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class EditarPostActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView btnVolver, btnGuardar;
    AlertDialog.Builder alertdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_post);

        btnVolver = findViewById(R.id.btnVolver);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnVolver.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);

        alertdialog = new AlertDialog.Builder(this, R.style.DialogBasicCustomRose);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVolver:
                alertdialog.setTitle("¿Descartar cambios?")
                .setMessage("¿Seguro que desea descartar los cambios?")
                .setIcon(R.drawable.advertencia)
                .setPositiveButton("Seguir editando", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("Descartar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                alertdialog.create();
                alertdialog.show();
                break;
            case R.id.btnGuardar:

                break;
        }
    }
}
