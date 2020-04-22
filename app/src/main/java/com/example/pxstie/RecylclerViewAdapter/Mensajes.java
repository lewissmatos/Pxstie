package com.example.pxstie.RecylclerViewAdapter;


public class Mensajes {

    private int image;
    private String nombre;
    private String mensaje;

    public Mensajes(int image, String nombre, String mensaje) {
        this.image = image;
        this.nombre = nombre;
        this.mensaje = mensaje;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
