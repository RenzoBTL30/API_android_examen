package com.example.api_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_android.model.Libro;
import com.example.api_android.services.Apis;
import com.example.api_android.services.LibroService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarActivity extends AppCompatActivity {

    LibroService service;
    List<Libro> listLibro =new ArrayList<>();

    TextView txtLibItem, txtAutItem, txtPagItem, txtEditoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        EditText edtId=(EditText)findViewById(R.id.editTxtId);
        ImageButton btnBuscar=(ImageButton)findViewById(R.id.btnBuscar);
        txtLibItem = findViewById(R.id.libro_item);
        txtAutItem = findViewById(R.id.autor_item);
        txtPagItem = findViewById(R.id.paginas_item);
        txtEditoItem = findViewById(R.id.editorial_item);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar(edtId.getText().toString());
            }
        });
    }

    private void buscar(String titulo){
        service= Apis.getOne();
        Call<List<Libro>> call=service.getOne(titulo);
        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                try {

                    if (response.isSuccessful()){
                        listLibro = response.body();
                        txtLibItem.setText("Libro: " + listLibro.get(0).getTitulo().toString());
                        txtAutItem.setText("Autor: " + listLibro.get(0).getAutor());
                        txtPagItem.setText("Paginas: " + listLibro.get(0).getPaginas());
                        txtEditoItem.setText("Editorial: " + listLibro.get(0).getEditorial().toString());
                    }
                } catch (Exception exception){
                    Toast.makeText(BuscarActivity.this, "Fallo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buscar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.exit) {
            startActivity(new Intent(this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}