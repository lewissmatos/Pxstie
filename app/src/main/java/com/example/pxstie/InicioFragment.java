package com.example.pxstie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.pxstie.RecylclerViewAdapter.Mensajes;
import com.example.pxstie.RecylclerViewAdapter.MensajesAdapter;
import com.example.pxstie.RecylclerViewAdapter.Posts;
import com.example.pxstie.RecylclerViewAdapter.PostsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class InicioFragment extends Fragment implements View.OnClickListener {

    ImageView btnMensajes, btnCuenta, btnAddPic, btnPostear, btnAddPost;
    RecyclerView recyclerView;

    EditText edPost;
    private Usuario user;
    boolean activo=false;
    private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_inicio, container, false);

        btnMensajes = view.findViewById(R.id.btnMensajes);
        btnCuenta = view.findViewById(R.id.btnCuenta);
        btnPostear = view.findViewById(R.id.btnPostear);
        btnAddPic = view.findViewById(R.id.btnAddPic);
        btnAddPost = view.findViewById(R.id.btnAddPost);
        edPost = view.findViewById(R.id.edPost);

        recyclerView = view.findViewById(R.id.recyclerView);

        user = Preferences.getUserData(getContext());

        btnMensajes.setOnClickListener(this);
        btnCuenta.setOnClickListener(this);
        btnPostear.setOnClickListener(this);
        btnAddPic.setOnClickListener(this);
        btnAddPost.setOnClickListener(this);

        edPost.setVisibility(View.INVISIBLE);
        btnPostear.setVisibility(View.INVISIBLE);
        edPost.setEnabled(false);
        btnPostear.setEnabled(false);

        dialog = new ProgressDialog(getContext(), R.style.DialogBasicCustomRose);

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
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMensajes:
                startActivity(new Intent(getContext(), MensajesActivity.class));
                break;
            case R.id.btnCuenta:
                startActivity(new Intent(getContext(), CuentaActivity.class));
                break;
            case R.id.btnPostear:
                dialog.setMessage("Posteando...");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                final String caption = edPost.getText().toString();
                final String fecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                if (caption.isEmpty()){
                    Toast.makeText(getContext(), "Debes agregar un caption", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else{

                    String url = "https://thejopipedia.000webhostapp.com/wsJSONPostearPxx.php?caption=" + caption + "&fecha=" + fecha + "&idusuario=" + user.getIdUsuario();

                    url = url.replace(" ", "%20");

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getContext(), "Publicado correctamente", Toast.LENGTH_SHORT).show();
                            edPost.setText("");
                            getPosts();
                            dialog.dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    requestQueue.add(jsonObjectRequest);
                    /*StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getContext(), "se hizo el maldito post", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("caption", caption);
                            map.put("fecha", fecha);
                            map.put("idusuario", "1");
                            return map;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    requestQueue.add(stringRequest);*/
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
                    recyclerView.setLayoutManager (new LinearLayoutManager(getContext()));
                    Collections.reverse(postsList);
                    recyclerView.setAdapter(new PostsAdapter(postsList, getContext()));

                } catch (JSONException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                    Toast.makeText(getContext(), R.string.no_estab_conex, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);


    }

    private ArrayList<Posts> getPostslList() {
        ArrayList<Posts> posts = new ArrayList<>();

        return posts;
    }
}
