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

    ImageView btnMensajes, btnCuenta, btnAddPic, btnPostear, btnAddPost;
    RecyclerView recyclerView;
    EditText edPost;
    boolean activo=false;
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

        btnMensajes.setOnClickListener(this);
        btnCuenta.setOnClickListener(this);
        btnPostear.setOnClickListener(this);
        btnAddPic.setOnClickListener(this);
        btnAddPost.setOnClickListener(this);

        edPost.setVisibility(View.INVISIBLE);
        btnPostear.setVisibility(View.INVISIBLE);
        edPost.setEnabled(false);
        btnPostear.setEnabled(false);

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
                break;
            case R.id.btnCuenta:
                startActivity(new Intent(getContext(), CuentaActivity.class));
                break;
        }
    }
    private ArrayList<Posts> getPostslList() {
        ArrayList<Posts> posts = new ArrayList<>();
        posts.add(new Posts("Eiron Diaz", "mi primer post sfhdfjfdhffjdfhjdfjhdfdhfdhfjdjfhdjhfdfhdfhdfjhdfhdfhdjfjdfjdhin foto"));
        posts.add(new Posts(R.drawable.yo, "Lewis Matos", "gracia papá dio otro día sin ser neybero"));
        posts.add(new Posts("Lewis Matos", "gracia papá dio otro día sin ser neybero te amo jesucristo mua bebe hermoso chulo papasote"));
        posts.add(new Posts(R.drawable.alofoke, "Santiago Matías", "donde ta topo??"));
        return posts;
    }
}
