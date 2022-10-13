package com.example.api_android.services;

public class Apis {
    public static final String URL_001="http://172.17.7.11:3000/libro/";

    public static LibroService getLibros(){
        return  Cliente.getClient(URL_001).create(LibroService.class);
    }

    public static LibroService getOne(){
        return  Cliente.getClient(URL_001).create(LibroService.class);
    }

    public static LibroService addLibro(){
        return  Cliente.getClient(URL_001).create(LibroService.class);
    }

    public static LibroService updateLibro(){
        return  Cliente.getClient(URL_001).create(LibroService.class);
    }

    public static LibroService deleteLibro(){
        return  Cliente.getClient(URL_001).create(LibroService.class);
    }
}
