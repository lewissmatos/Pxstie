package com.example.pxstie;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Usuario{

    private String idUsuario;
    private String nombre;
    private String correo;
    private String contraseña;
    private String genero;
    private String fecha;
    private Context context;

    public Usuario(Context context, String idUsuario, String nombre, String correo, String contraseña) {
        this.context = context;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public Usuario() {
    }

    public static void IniciarSesion(final Context context, final ProgressDialog dialog, String email, String pass){

        String url = "https://thejopipedia.000webhostapp.com/wsJSONLoginPx.php?correo=" + email + "&pass=" + pass;

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

                        Preferences.SaveUserData(context, user.getIdUsuario(), user.getNombre(), user.getCorreo(), user.getContraseña(), user.getGenero(), user.getFecha());
                    }
                    dialog.dismiss();
                    context.startActivity(new Intent(context, ContenedorActivity.class));

                } catch (JSONException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                    Toast.makeText(context, R.string.no_estab_conex, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, R.string.no_inic_sesion, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }

    public static void Registrar(final Context context, final ProgressDialog dialog, final String nombre, final String email, final String pass, final String genero, final String fecha){

        String url = "https://thejopipedia.000webhostapp.com//wsJSONRegistroPx.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.isEmpty()) {
                    Toast.makeText(context, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    context.startActivity(new Intent(context, MainActivity.class));
                }
                else{
                    Toast.makeText(context, response,  Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("nombre", nombre);
                map.put("correo", email);
                map.put("contraseña", pass);
                map.put("genero", genero);
                map.put("fecha", fecha);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdusuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
