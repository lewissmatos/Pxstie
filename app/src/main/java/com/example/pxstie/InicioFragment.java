package com.example.pxstie;

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

import com.example.pxstie.RecylclerViewAdapter.Mensajes;
import com.example.pxstie.RecylclerViewAdapter.MensajesAdapter;
import com.example.pxstie.RecylclerViewAdapter.Posts;
import com.example.pxstie.RecylclerViewAdapter.PostsAdapter;

import java.util.ArrayList;


public class InicioFragment extends Fragment implements View.OnClickListener {

    ImageView btnMensajes, btnAddPic, btnPostear;
    RecyclerView recyclerView;
    EditText edPost;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_inicio, container, false);

        btnMensajes = view.findViewById(R.id.btnMensajes);
        btnPostear = view.findViewById(R.id.btnPostear);
        btnAddPic = view.findViewById(R.id.btnAddPic);
        edPost = view.findViewById(R.id.edPost);

        btnMensajes.setOnClickListener(this);
        btnPostear.setOnClickListener(this);
        btnAddPic.setOnClickListener(this);

        edPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPost.setFocusable(true);
            }
        });


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager (new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new PostsAdapter(getPostslList(), getContext()));
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMensajes:
                startActivity(new Intent(getContext(), MensajesActivity.class));
        }
    }
    private ArrayList<Posts> getPostslList() {
        ArrayList<Posts> posts = new ArrayList<>();
        posts.add(new Posts(R.drawable.yo, "Lewis Matos", "gracia papá dio otro día sin ser neybero"));
        posts.add(new Posts(R.drawable.alofoke, "Santiago Matías", "donde ta topo??"));
        posts.add(new Posts(R.drawable.alondra, "Alondra Gómez", "Lewis me tiene asfixiaaaa"));
        posts.add(new Posts(R.drawable.pan, "Pan Matos", "con la prima"));
        posts.add(new Posts(R.drawable.gangstars, "Lo moreno bailarines", "A trabajar con el equipo!"));
        return posts;
    }
}
