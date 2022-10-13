package com.example.api_android.services;

import com.example.api_android.model.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LibroService {
    @GET("listar")
    Call<List<Libro>> getLibros();

    @GET("buscartitulo/{titulo}")
    Call<List<Libro>> getOne(@Path("titulo") String titulo);

    @POST("agregar")
    Call<Libro>addLibro(@Body Libro libro);

    @PUT("actualizar/{id}")
    Call<Libro>updateLibro(@Body Libro libro, @Path("id") int id);

    @DELETE("eliminar/{id}")
    Call<Libro>deleteLibro(@Path("id")int id);
}
