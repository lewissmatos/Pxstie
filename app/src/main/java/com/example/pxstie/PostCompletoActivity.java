package com.example.pxstie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pxstie.RecylclerViewAdapter.Comentarios;
import com.example.pxstie.RecylclerViewAdapter.ComentariosAdapter;
import com.example.pxstie.RecylclerViewAdapter.Posts;
import com.example.pxstie.RecylclerViewAdapter.PostsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PostCompletoActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtNom, txtCaption, txtFecha, idUsuario;
    private EditText edComentario;
    ImageView btnVolver, btnEliminar, btnEditar, like, btnComentar;
    boolean activo = false;
    private String idPost;
    private ProgressDialog dialog;
    private Usuario user;
    private RecyclerView recyclerView;
    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_completo);

        try{
            Bundle b = getIntent().getExtras();
            idPost = b.getString("id");
        }catch (Exception e){}

        dialog = new ProgressDialog(this, R.style.DialogBasicCustomRose);

        user = Preferences.getUserData(this);

        btnVolver = findViewById(R.id.btnVolver);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnEditar = findViewById(R.id.btnEditar);

        idUsuario = findViewById(R.id.idUsuario);

        btnComentar = findViewById(R.id.btnComentar);
        edComentario = findViewById(R.id.edComentario);
        like = findViewById(R.id.like);

        txtCaption = findViewById(R.id.txtCaption);
        txtNom = findViewById(R.id.txtNom);
        txtFecha = findViewById(R.id.txtFecha);

        btnVolver.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnEditar.setOnClickListener(this);
        btnComentar.setOnClickListener(this);

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

        recyclerView = findViewById(R.id.recyclerView);

        getPostById();
        getComentarios();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVolver:
                onBackPressed();
                break;
            case R.id.btnEditar:

                break;
            case R.id.btnEliminar:
                alertDialog  =new AlertDialog.Builder(this, R.style.DialogBasicCustomRose);
                alertDialog.setMessage("Seguro que desea eliminar?")
                        .setTitle(R.string.advertencia)
                        .setIcon(R.drawable.eliminar_grande)
                    .setPositiveButton(R.string.aceptar_sesion, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deletePost();
                        }
                    }).setNegativeButton(R.string.cancelar_sesion, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                alertDialog.create();
                alertDialog.show();

                break;
            case R.id.btnComentar:
                Comentar();
                break;
        }
    }

    private void deletePost(){
        dialog.setMessage("Eliminando post...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        String url = "https://thejopipedia.000webhostapp.com/wsJSONDeletePost.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                startActivity(new Intent(PostCompletoActivity.this, ContenedorActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PostCompletoActivity.this, "Ha ocurrido un error al eliminar el post", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", idPost);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getComentarios(){
        dialog.setMessage("Cargando...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        String url = "https://thejopipedia.000webhostapp.com/wsJSONGetComentarioById.php?id=" + idPost;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray json = response.optJSONArray("comentario");
                ArrayList<Comentarios> comList = new ArrayList<Comentarios>();

                try {
                    for (int i = 0; i < json.length(); i++){
                        Comentarios com = new Comentarios();
                        JSONObject jsonObject = null;
                        jsonObject = json.getJSONObject(i);

                        com.setImage(R.drawable.el_lidel);
                        com.setComentario(jsonObject.getString("contenido"));
                        com.setNombre(jsonObject.getString("nombre"));

                        comList.add(com);
                    }
                    dialog.dismiss();
                    recyclerView.setLayoutManager (new LinearLayoutManager(PostCompletoActivity.this));
                    Collections.reverse(comList);
                    recyclerView.setAdapter(new ComentariosAdapter(comList, PostCompletoActivity.this));

                } catch (JSONException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                    Toast.makeText(PostCompletoActivity.this, R.string.no_estab_conex, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PostCompletoActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    private void Comentar() {
        dialog.setMessage("Comentando...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        final String comentario = edComentario.getText().toString();
        final String fecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        if (comentario.isEmpty()){
            Toast.makeText(this, "llena el campo para comentar", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else{

            String url = "https://thejopipedia.000webhostapp.com/wsJSONComentario.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    edComentario.setText("");
                    getComentarios();
                    dialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(PostCompletoActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("contenido", comentario);
                    map.put("fecha", fecha);
                    map.put("idpost", idPost);
                    map.put("idusuario", user.getIdUsuario());
                    return map;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

    private void getPostById(){
        dialog.setMessage("Cargando...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        String url = "https://thejopipedia.000webhostapp.com/wsJSONGetPostById.php?id=" + idPost;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray json = response.optJSONArray("post");

                try {
                    for (int i = 0; i < json.length(); i++){
                        JSONObject jsonObject = null;
                        jsonObject = json.getJSONObject(i);

                        txtCaption.setText(jsonObject.getString("caption"));
                        txtFecha.setText(jsonObject.getString("fecha"));
                        txtNom.setText(jsonObject.getString("nombre"));
                        idUsuario.setText(jsonObject.getString("idUsuario"));
                    }
                    dialog.dismiss();

                    if (user.getIdUsuario().equals(idUsuario.getText().toString())){
                        btnEliminar.setVisibility(View.VISIBLE);
                        btnEditar.setVisibility(View.VISIBLE);
                    }
                    else{
                        btnEliminar.setVisibility(View.INVISIBLE);
                        btnEditar.setVisibility(View.INVISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                    Toast.makeText(PostCompletoActivity.this, R.string.no_estab_conex, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PostCompletoActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}
