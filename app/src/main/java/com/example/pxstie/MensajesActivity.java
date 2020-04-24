package com.example.pxstie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.pxstie.RecylclerViewAdapter.Mensajes;
import com.example.pxstie.RecylclerViewAdapter.MensajesAdapter;

import java.util.ArrayList;

public class MensajesActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView btnVolver;
    CardView msj1;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);

        btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager (new LinearLayoutManager(this));

        recyclerView.setAdapter(new MensajesAdapter(getMensasjeslList(), this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVolver:
               onBackPressed();
        }
    }
    private ArrayList<Mensajes> getMensasjeslList() {
        ArrayList<Mensajes> mensajes = new ArrayList<>();
        mensajes.add(new Mensajes(R.drawable.yo, "Lewis Matos", "klk vamo a bebe romo?"));
        return mensajes;
    }
}
