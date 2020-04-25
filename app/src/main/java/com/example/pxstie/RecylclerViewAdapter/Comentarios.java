package com.example.pxstie.RecylclerViewAdapter;


public class Comentarios {

    private int image;
    private String nombre;
    private String comentario;

    public Comentarios(int image, String nombre, String mensaje) {
        this.image = image;
        this.nombre = nombre;
        this.comentario = comentario;
    }

    public Comentarios() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
