package com.example.api_android.services;

import com.example.api_android.model.Editorial;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EditorialService {
    @GET("listar")
    Call<List<Editorial>> getEditoriales();

    @GET("buscar/{id}")
    Call<List<Editorial>> getOne(@Path("id") int id);
}
