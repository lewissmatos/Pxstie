package com.example.pxstie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContenedorActivity extends AppCompatActivity {
    AlertDialog.Builder opdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navLiistener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new InicioFragment()).commit();
        opdialog = new AlertDialog.Builder(this, R.style.DialogBasicCustomBlack);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navLiistener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    if (menuItem.getItemId() == R.id.logOut)
                    {
                        opdialog.setMessage("Seguro que desea cerrar sesión?")
                                .setTitle(R.string.advertencia)
                                .setIcon(R.drawable.advertencia)
                                .setPositiveButton(R.string.aceptar_sesion, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Preferences.SaveUserData(ContenedorActivity.this, "", "", "","", "", "");
                                        startActivity(new Intent(ContenedorActivity.this, MainActivity.class));
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
                    else
                    switch (menuItem.getItemId()) {
                        case R.id.inicio:
                            selectedFragment = new InicioFragment();
                            break;
                        /*case R.id.notificaciones:
                            selectedFragment = new NotificacionesFragment();
                            break;*/

                    }if (menuItem.getItemId() != R.id.logOut) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                    }
                    return true;
                }
            };
}
