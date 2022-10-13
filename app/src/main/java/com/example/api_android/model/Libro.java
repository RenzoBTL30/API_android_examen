package com.example.api_android.model;

public class Libro {
    private int idlibro;
    private String titulo;
    private String autor;
    private int paginas;
    private String edicion;
    private int ideditorial;
    private String editorial;

    public Libro() {
    }

    public Libro(int idlibro, String titulo, String autor, int paginas, String edicion, int ideditorial, String editorial) {
        this.idlibro = idlibro;
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.edicion = edicion;
        this.ideditorial = ideditorial;
        this.editorial = editorial;
    }

    public Libro(String titulo, String autor, int paginas, String edicion, int ideditorial, String editorial) {
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.edicion = edicion;
        this.ideditorial = ideditorial;
        this.editorial = editorial;
    }

    public int getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public int getIdeditorial() {
        return ideditorial;
    }

    public void setIdeditorial(int ideditorial) {
        this.ideditorial = ideditorial;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}