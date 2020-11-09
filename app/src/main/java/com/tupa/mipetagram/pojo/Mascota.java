package com.tupa.mipetagram.pojo;

public class Mascota implements Comparable<Mascota>{

    private Integer id;
    private String nombre;
    private int foto;
    private Integer likes;

    public Mascota(String nombre, int foto, Integer likes) {
        this.nombre = nombre;
        this.foto = foto;
        this.likes = likes;
    }

    public Mascota() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(Integer foto) {
        this.foto = foto;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public int compareTo(Mascota m) {
        if (likes < m.likes) {
            return 1;
        }
        if (likes > m.likes) {
            return -1;
        }
        return 0;
    }
}
