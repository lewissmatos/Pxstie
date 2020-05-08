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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edUser, edPass;
    private Button btnIniSes;
    private TextView btnReg;
    private String user, password;
    private ProgressDialog dialog;
    private Usuario userr;
    private Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userr = Preferences.getUserData(this);
        if (!userr.getNombre().equalsIgnoreCase("")){
            startActivity(new Intent(this, HomeScreenActivity.class));
            finish();
        }

        btnReg = findViewById(R.id.btnReg);
        btnIniSes = findViewById(R.id.btnIniSes);

        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);

        btnReg.setOnClickListener(this);
        btnIniSes.setOnClickListener(this);

        dialog = new ProgressDialog(this);

        String colorbarra = "#F8F8F8";

        this.window = getWindow();
        //barcolor
        window.setStatusBarColor(Color.parseColor(colorbarra));

        dialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReg :
                startActivity(new Intent(this, DatosActivity.class));
                finish();
                break;
            case R.id.btnIniSes:
                dialog = new ProgressDialog(this, R.style.DialogBasicCustomRose);
                dialog.setMessage("Iniciando Sesión...");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                user=edUser.getText().toString();
                password=edPass.getText().toString();

                if (user.isEmpty()||password.isEmpty())
                {
                    Toast.makeText(this, R.string.llenar_campos, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else {
                    String url = "https://thejopipedia.000webhostapp.com/wsJSONLoginPx.php?correo=" + user + "&pass=" + password;

                    url = url.replace(" ", "%20");

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONArray json = response.optJSONArray("usuario");

                            try {
                                for (int i = 0; i < json.length(); i++){
                                    Usuario user = new Usuario();
                                    JSONObject jsonObject = null;
                                    jsonObject = json.getJSONObject(i);

                                    user.setIdusuario(jsonObject.getString("id"));
                                    user.setNombre(jsonObject.getString("nombre"));
                                    user.setCorreo(jsonObject.getString("usuario"));
                                    user.setContraseña(jsonObject.getString("contraseña"));
                                    user.setGenero(jsonObject.getString("genero"));
                                    user.setFecha(jsonObject.getString("fecha"));

                                    Preferences.SaveUserData(MainActivity.this, user.getIdUsuario(), user.getNombre(), user.getCorreo(), user.getContraseña(), user.getGenero(), user.getFecha());
                                }
                                dialog.dismiss();
                                startActivity(new Intent(MainActivity.this, HomeScreenActivity.class));
                                finish();

                            } catch (JSONException e) {
                                e.printStackTrace();
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, R.string.no_estab_conex, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, R.string.no_inic_sesion, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(jsonObjectRequest);
                }
                break;
        }
    }
}
