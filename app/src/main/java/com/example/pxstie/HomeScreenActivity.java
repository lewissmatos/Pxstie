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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pxstie.RecylclerViewAdapter.Posts;
import com.example.pxstie.RecylclerViewAdapter.PostsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView btnMensajes, btnCuenta, btnAddPic, btnPostear, btnAddPost, btnLogOut;
    RecyclerView recyclerView;
    EditText edPost;
    private Usuario user;
    boolean activo=false;
    private ProgressDialog dialog;
    AlertDialog.Builder opdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        btnMensajes = findViewById(R.id.btnMensajes);
        btnCuenta = findViewById(R.id.btnCuenta);
        btnPostear = findViewById(R.id.btnPostear);
        btnAddPic = findViewById(R.id.btnAddPic);
        btnAddPost = findViewById(R.id.btnAddPost);
        btnLogOut = findViewById(R.id.btnLogOut);
        edPost = findViewById(R.id.edPost);

        recyclerView = findViewById(R.id.recyclerView);

        user = Preferences.getUserData(this);

        btnMensajes.setOnClickListener(this);
        btnCuenta.setOnClickListener(this);
        btnPostear.setOnClickListener(this);
        btnAddPic.setOnClickListener(this);
        btnAddPost.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);

        edPost.setVisibility(View.INVISIBLE);
        btnPostear.setVisibility(View.INVISIBLE);
        edPost.setEnabled(false);
        btnPostear.setEnabled(false);

        dialog = new ProgressDialog(this, R.style.DialogBasicCustomRose);
        opdialog = new AlertDialog.Builder(this, R.style.DialogBasicCustomBlack);

        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!activo){
                    btnAddPost.setImageResource(R.drawable.close);
                    activo = true;
                    edPost.setVisibility(View.VISIBLE);
                    btnPostear.setVisibility(View.VISIBLE);
                    edPost.setEnabled(true);
                    btnPostear.setEnabled(true);
                }
                else {
                    btnAddPost.setImageResource(R.drawable.addpost);
                    activo = false;
                    edPost.setVisibility(View.INVISIBLE);
                    btnPostear.setVisibility(View.INVISIBLE);
                    edPost.setEnabled(false);
                    btnPostear.setEnabled(false);
                }

            }
        });

        getPosts();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMensajes:
                startActivity(new Intent(this, MensajesActivity.class));
                break;
            case R.id.btnCuenta:
                startActivity(new Intent(this, CuentaActivity.class));
                break;
            case R.id.btnLogOut:
                opdialog.setMessage("Seguro que desea cerrar sesión?")
                        .setTitle(R.string.advertencia)
                        .setIcon(R.drawable.advertencia)
                        .setPositiveButton(R.string.aceptar_sesion, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Preferences.SaveUserData(HomeScreenActivity.this, "", "", "","", "", "");
                                startActivity(new Intent(HomeScreenActivity.this, MainActivity.class));
                                finish();
                            }
                        }).setNegativeButton(R.string.cancelar_sesion, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                opdialog.create();
                opdialog.show();
                break;
            case R.id.btnPostear:
                dialog.setMessage("Posteando...");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                final String caption = edPost.getText().toString();
                final String fecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                if (caption.isEmpty()){
                    Toast.makeText(this, "Debes agregar un caption", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else{

                    String url = "https://thejopipedia.000webhostapp.com/wsJSONPostearPxx.php?caption=" + caption + "&fecha=" + fecha + "&idusuario=" + user.getIdUsuario();

                    url = url.replace(" ", "%20");

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(HomeScreenActivity.this, "Publicado correctamente", Toast.LENGTH_SHORT).show();
                            edPost.setText("");
                            getPosts();
                            dialog.dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(HomeScreenActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(jsonObjectRequest);
                }
                break;
        }
    }
    private void getPosts(){
        dialog.setMessage("Cargando...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        String url = "https://thejopipedia.000webhostapp.com/wsJSONGetPosts.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray json = response.optJSONArray("post");
                ArrayList<Posts> postsList = new ArrayList<>();

                try {
                    for (int i = 0; i < json.length(); i++){
                        Posts post = new Posts();
                        JSONObject jsonObject = null;
                        jsonObject = json.getJSONObject(i);

                        post.setCaption(jsonObject.getString("caption"));
                        post.setFecha(jsonObject.getString("fecha"));
                        post.setNombre(jsonObject.getString("nombre"));
                        post.setIdPost(jsonObject.getString("id"));

                        postsList.add(post);
                    }
                    dialog.dismiss();
                    recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager (new LinearLayoutManager(HomeScreenActivity.this));
                    Collections.reverse(postsList);
                    recyclerView.setAdapter(new PostsAdapter(postsList, HomeScreenActivity.this));

                } catch (JSONException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                    Toast.makeText(HomeScreenActivity.this, R.string.no_estab_conex, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeScreenActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);


    }

    private ArrayList<Posts> getPostslList() {
        ArrayList<Posts> posts = new ArrayList<>();

        return posts;
    }
}
