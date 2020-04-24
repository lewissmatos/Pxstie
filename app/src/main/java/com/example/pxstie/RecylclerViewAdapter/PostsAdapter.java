package com.example.pxstie.RecylclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pxstie.ChatActivity;
import com.example.pxstie.CuentaActivity;
import com.example.pxstie.PostCompletoActivity;
import com.example.pxstie.R;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    ArrayList<Posts> posts;
    Context context;
    boolean activo=false;
    public PostsAdapter(ArrayList<Posts> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_posts, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PostsAdapter.ViewHolder holder, int position) {
        if (posts.get(position).getImage() != 0){
            holder.image.setVisibility(View.VISIBLE);
            holder.image.setImageResource(posts.get(position).getImage());
        }

        if (posts.get(position).getCaption().length() > 70){
            holder.caption.setText(posts.get(position).getCaption().substring(0, 70) + "...");
        }
        else {
            holder.caption.setText(posts.get(position).getCaption());
        }

        holder.nombre.setText(posts.get(position).getNombre());
        holder.idPost.setText(posts.get(position).getIdPost());
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!activo){
                    holder.like.setImageResource(R.drawable.estrella_llena);
                    activo = true;
                }
                else {
                    holder.like.setImageResource(R.drawable.estrella_normal);
                    activo = false;
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, like;
        TextView nombre, caption;
        LinearLayout parent;
        TextView idPost;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, PostCompletoActivity.class);
                    Bundle b = new Bundle();
                    b.putString("id", idPost.getText().toString());
                    i.putExtras(b);
                    context.startActivity(i);
                    itemView.setActivated(true);
                }
            });
            image = itemView.findViewById(R.id.img);
            nombre = itemView.findViewById(R.id.txtNom);
            caption = itemView.findViewById(R.id.txtCaption);
            like = itemView.findViewById(R.id.like);
            idPost = itemView.findViewById(R.id.idPost);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
