package com.example.api_android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_android.model.Editorial;
import com.example.api_android.model.Libro;
import com.example.api_android.services.Apis;
import com.example.api_android.services.ApisEditorial;
import com.example.api_android.services.EditorialService;
import com.example.api_android.services.LibroService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibroActivity extends AppCompatActivity {

    LibroService service;
    EditorialService editorialService;

    ArrayAdapter<String> adaptador;
    Spinner spEditorial;

    List<Editorial> listEditorial =new ArrayList<>();
    int key = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro);

        TextView txtTitle = (TextView)findViewById(R.id.txtTitleRegistrar);
        TextView idesc=(TextView)findViewById(R.id.Id);
        EditText txtId=(EditText)findViewById(R.id.txtId);

        TextView libro=(TextView)findViewById(R.id.libro);
        final EditText txtLibro=(EditText)findViewById(R.id.txtLibro);
        TextView autor=(TextView)findViewById(R.id.autor);
        final EditText txtAutor=(EditText)findViewById(R.id.txtAutor);
        TextView paginas=(TextView)findViewById(R.id.txtPaginas);
        final EditText txtPaginas=(EditText)findViewById(R.id.txtPaginas);
        TextView editorial=(TextView)findViewById(R.id.editorial);
        spEditorial=(Spinner) findViewById(R.id.spEditorial);

        Button btnSave=(Button)findViewById(R.id.btnSave);
        Button btnVolver=(Button)findViewById(R.id.btnVolver);
        Button btnEliminar=(Button)findViewById(R.id.btnEliminar);

        listEditoriales();
        spEditorial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String idEditorial = adapterView.getSelectedItem().toString();
                key = Integer.parseInt(idEditorial.split(" ")[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Bundle bundle=getIntent().getExtras();
        final String id = bundle.getString("ID");
        String title=bundle.getString("title");
        String tit=bundle.getString("TITULO");
        String aut=bundle.getString("AUTOR");
        String pag=bundle.getString("PAGINAS");
        String edito=bundle.getString("EDITORIAL");

        txtTitle.setText(title);
        txtId.setText(id);
        txtLibro.setText(tit);
        txtAutor.setText(aut);
        txtPaginas.setText(pag);

        idesc.setVisibility(View.INVISIBLE);
        txtId.setVisibility(View.INVISIBLE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Libro l = new Libro();
                l.setTitulo(txtLibro.getText().toString());
                l.setAutor(txtAutor.getText().toString());
                l.setPaginas(Integer.valueOf(txtPaginas.getText().toString()));
                l.setIdeditorial(Integer.valueOf(""+key));

                if (id.trim().length()==0 || id.equals("")) {
                    addLibro(l);
                    Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    updateLibro(l,Integer.valueOf(id));
                    Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLibro(Integer.valueOf(id));
                Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void addLibro(Libro l){
        service= Apis.addLibro();
        Call<Libro> call=service.addLibro(l);
        call.enqueue(new Callback<Libro>() {
            @Override
            public void onResponse(Call<Libro> call, Response<Libro> response) {
                if(response.isSuccessful()){
                    Libro libro = response.body();
                    Toast.makeText(LibroActivity.this,"Se agrego exitosamente",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Libro> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(LibroActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void updateLibro(Libro l, int id){
        service= Apis.updateLibro();
        Call<Libro>call=service.updateLibro(l,id);
        call.enqueue(new Callback<Libro>() {
            @Override
            public void onResponse(Call<Libro> call, Response<Libro> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LibroActivity.this,"Se Actualizó exitosamente",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Libro> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(LibroActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void deleteLibro(int id){
        service=Apis.deleteLibro();
        Call<Libro>call=service.deleteLibro(id);
        call.enqueue(new Callback<Libro>() {
            @Override
            public void onResponse(Call<Libro> call, Response<Libro> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LibroActivity.this,"Se Elimino el registro",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Libro> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(LibroActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void listEditoriales(){
        editorialService = ApisEditorial.getEditoriales();
        Call<List<Editorial>> call= editorialService.getEditoriales();
        call.enqueue(new Callback<List<Editorial>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Editorial>> call, Response<List<Editorial>> response) {
                if(response.isSuccessful()) {
                    listEditorial = response.body();
                    String[] st = new String[listEditorial.size()];
                    for (int i = 0; i < listEditorial.size(); i++) {
                        st[i]= String.valueOf(listEditorial.get(i).getIdeditorial())+" "+ listEditorial.get(i).getNombre();
                        final ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, st);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spEditorial.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Editorial>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
}