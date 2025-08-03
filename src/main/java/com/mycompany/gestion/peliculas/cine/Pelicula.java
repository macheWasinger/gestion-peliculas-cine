package com.mycompany.gestion.peliculas.cine;

public class Pelicula {
    private String titulo;
    private String director;
    private String genero;
    private int anioEstreno;
    private boolean disponible;
    
    public Pelicula(){};
    
    public Pelicula (String titulo, String director, String genero, int anioEstreno, boolean disponible) {
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.anioEstreno = anioEstreno;
        this.disponible = disponible;
    }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDirector() { return director; }
    public void setDirector(String director) {
        this.director = director;
    }
    
    public String getGenero() { return genero; }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public int getAnioEstreno() { return anioEstreno; }
    public void setAnioEstreno(int anioEstreno) {
        this.anioEstreno = anioEstreno;
    }
    
    public boolean getDisponible() { return disponible; }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    @Override
    public String toString() {
        return "pelicula{" + 
                "titulo=" + titulo +
                ", director=" + director + 
                ", genero=" + genero + 
                ", anioEstreno=" + anioEstreno + 
                ", disponible=" + disponible + 
                "}";
    }
}