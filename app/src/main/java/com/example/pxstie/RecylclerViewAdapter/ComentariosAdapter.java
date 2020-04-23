package com.example.pxstie.RecylclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pxstie.ChatActivity;
import com.example.pxstie.R;

import java.util.ArrayList;

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ViewHolder> {

    ArrayList<Comentarios> comentarios;
    Context context;

    public ComentariosAdapter(ArrayList<Comentarios> comentarios, Context context) {
        this.comentarios = comentarios;
        this.context = context;
    }

    @NonNull
    @Override
    public ComentariosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_comentarios, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComentariosAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(comentarios.get(position).getImage());
        holder.nombre.setText(comentarios.get(position).getNombre());
        holder.comentario.setText(comentarios.get(position).getComentario());
    }


    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView nombre, comentario;
        LinearLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ChatActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("id", getAdapterPosition());
                    i.putExtras(b);
                    context.startActivity(i);
                }
            });*/
            image = itemView.findViewById(R.id.img);
            nombre = itemView.findViewById(R.id.txtNombre);
            comentario = itemView.findViewById(R.id.txtComentario);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
