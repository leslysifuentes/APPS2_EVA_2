package com.example.eva2_14_document_provider;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText et_datos;
    Button b_guardar, b_abrir;
    final int ABRIR_ARCHIVO= 100;
    final int GUARDAR_ARCHIVO=200;

        Uri uri= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_datos = findViewById(R.id.et_datos);
        b_guardar = findViewById(R.id.b_guardar);
        b_abrir = findViewById(R.id.b_abrir);

        b_abrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in_abrir = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                in_abrir.addCategory(Intent.CATEGORY_OPENABLE);
                in_abrir.setType("text/plain");//mime type
                in_abrir.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri);
                startActivityForResult(in_abrir, ABRIR_ARCHIVO);
            }
        });

        b_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in_guardar = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                in_guardar.addCategory(Intent.CATEGORY_OPENABLE);
                in_guardar.putExtra(Intent.EXTRA_TITLE, "archivo.txt");
                in_guardar.setType("text/plain");//mime type
                in_guardar.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri);
                startActivityForResult(in_guardar, GUARDAR_ARCHIVO);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ABRIR_ARCHIVO:
                if(resultCode== Activity.RESULT_OK){
                    uri = data.getData();
                    Log.wtf("URI", uri.toString());
                    String texto;
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        InputStreamReader streamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(streamReader);
                        while ((texto = bufferedReader.readLine()) != null){
                            et_datos.append(texto);
                            et_datos.append("\n");

                        }
                        bufferedReader.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case GUARDAR_ARCHIVO:
                if(resultCode== Activity.RESULT_OK) {
                    uri = data.getData();
                    Log.wtf("URI", uri.toString());
                }
                try{
                    String[] cadena = et_datos.getText().toString().split("\n");
                    OutputStream outputStream = getContentResolver().openOutputStream(uri);
                    OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
                    BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);
                    for(int i = 0 ; i<cadena.length; i++){
                        bufferedWriter.append(cadena[i]);
                        bufferedWriter.append("\n");

                    }
                    bufferedWriter.close();
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }


    }
}