package com.example.api_android;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.api_android.adapter.LibroAdapter;
import com.example.api_android.model.Libro;
import com.example.api_android.services.Apis;
import com.example.api_android.services.LibroService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    LibroService libroService;
    List<Libro> listLibro =new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.listView);

        listLibros();



        FloatingActionButton fab = findViewById(R.id.fabe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, LibroActivity.class);
                intent.putExtra("title","Registrar Libro");
                intent.putExtra("ID","");
                intent.putExtra("TITULO","");
                intent.putExtra("AUTOR","");
                intent.putExtra("PAGINAS","");
                intent.putExtra("EDITORIAL","");
                startActivity(intent);
            }
        });

    }

        public void listLibros(){
        libroService = Apis.getLibros();
        Call<List<Libro>> call= libroService.getLibros();
        call.enqueue(new Callback<List<Libro>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if(response.isSuccessful()) {
                    listLibro = response.body();
                    listView.setAdapter(new LibroAdapter(MainActivity.this,R.layout.content_main, listLibro));
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.buscar) {
            startActivity(new Intent(this, BuscarActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}