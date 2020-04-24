package com.example.pxstie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PostCompletoActivity extends AppCompatActivity implements View.OnClickListener{


    ImageView btnVolver, like;
    boolean activo = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_completo);

        btnVolver = findViewById(R.id.btnVolver);
        like = findViewById(R.id.like);
        btnVolver.setOnClickListener(this);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!activo){
                    like.setImageResource(R.drawable.estrella_llena);
                    activo = true;
                }
                else {
                    like.setImageResource(R.drawable.estrella_normal);
                    activo = false;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVolver:
                startActivity(new Intent(this, ContenedorActivity.class));
                finish();
        }
    }
}
