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
import com.example.pxstie.R;

import java.util.ArrayList;

public class MensajesAdapter extends RecyclerView.Adapter<MensajesAdapter.ViewHolder> {

    ArrayList<Mensajes> mensajes;
    Context context;

    public MensajesAdapter(ArrayList<Mensajes> mensajes, Context context) {
        this.mensajes = mensajes;
        this.context = context;
    }

    @NonNull
    @Override
    public MensajesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_mensajes, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MensajesAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(mensajes.get(position).getImage());
        holder.nombre.setText(mensajes.get(position).getNombre());
        holder.mensaje.setText(mensajes.get(position).getMensaje());
    }


    @Override
    public int getItemCount() {
        return mensajes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView nombre, mensaje;
        LinearLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ChatActivity.class);
                    context.startActivity(i);
                }
            });
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
            mensaje = itemView.findViewById(R.id.txtMensaje);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
