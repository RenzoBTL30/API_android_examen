package com.example.api_android.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.api_android.LibroActivity;
import com.example.api_android.R;
import com.example.api_android.model.Libro;

import java.util.List;


public class LibroAdapter extends ArrayAdapter<Libro>{
    private Context context;
    private List<Libro> libros;
    private TextView txtEditorial;
    private AlertDialog alertDialog, alertconfirm;


    public LibroAdapter(@NonNull Context context, int resource, @NonNull List<Libro> objects) {
        super(context, resource, objects);
        this.context=context;
        this.libros =objects;
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_main,parent,false);
        TextView txtTitulo=(TextView)rowView.findViewById(R.id.Libro);
        TextView txtAutor=(TextView)rowView.findViewById(R.id.Autor);
        TextView txtPaginas=(TextView)rowView.findViewById(R.id.Paginas);
        txtEditorial=(TextView)rowView.findViewById(R.id.Editorial);

        txtTitulo.setText(String.format("TITULO: %s ", libros.get(position).getTitulo()));
        txtAutor.setText(String.format("AUTOR: %s ", libros.get(position).getAutor()));
        txtPaginas.setText(String.format("PAGINAS: %d ", libros.get(position).getPaginas()));
        txtEditorial.setText(String.format("EDITORIAL: %s ", libros.get(position).getEditorial()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setTitle("Opciones");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Editar",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                Intent intent=new Intent(context, LibroActivity.class);
                                intent.putExtra("title","Modificar Libro");
                                intent.putExtra("ID",String.valueOf(libros.get(position).getIdlibro()));
                                intent.putExtra("TITULO", libros.get(position).getTitulo());
                                intent.putExtra("AUTOR", libros.get(position).getAutor());
                                intent.putExtra("PAGINAS", String.valueOf(libros.get(position).getPaginas()));
                                intent.putExtra("EDITORIAL", String.valueOf(libros.get(position).getIdeditorial()));
                                context.startActivity(intent);
                            }
                        })

                        .setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false)
                        .setNeutralButton("Eliminar",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                Intent intent=new Intent(context, LibroActivity.class);
                                intent.putExtra("title","Eliminar Libro");
                                intent.putExtra("ID",String.valueOf(libros.get(position).getIdlibro()));
                                intent.putExtra("TITULO", libros.get(position).getTitulo());
                                intent.putExtra("AUTOR", libros.get(position).getAutor());
                                intent.putExtra("PAGINAS", String.valueOf(libros.get(position).getPaginas()));
                                intent.putExtra("EDITORIAL", String.valueOf(libros.get(position).getIdeditorial()));
                                context.startActivity(intent);
                            }
                        }).create().show();

            }
        });
        return rowView;
    }


}
