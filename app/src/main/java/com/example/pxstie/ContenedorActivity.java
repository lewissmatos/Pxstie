package com.example.pxstie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContenedorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navLiistener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new InicioFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navLiistener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    if (menuItem.getItemId() == R.id.cuenta)
                    {
                        startActivity(new Intent(ContenedorActivity.this, CuentaActivity.class));
                    }
                    else
                    switch (menuItem.getItemId()) {
                        case R.id.inicio:
                            selectedFragment = new InicioFragment();
                            break;
                        case R.id.mensajes:
                            selectedFragment = new MensajesFragment();
                            break;
                        case R.id.notificaciones:
                            selectedFragment = new NotificacionesFragment();
                            break;
                        case R.id.buscar:
                                selectedFragment = new BuscarFragment();
                                break;
                    }
                    if (menuItem.getItemId() != R.id.cuenta){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                     }
                    return true;
                }
            };
}
