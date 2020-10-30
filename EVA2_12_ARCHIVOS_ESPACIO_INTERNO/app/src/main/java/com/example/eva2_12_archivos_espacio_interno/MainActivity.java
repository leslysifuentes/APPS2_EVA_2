package com.example.eva2_12_archivos_espacio_interno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    Button b_guardar, b_leer;
    final String archivo = "archivo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_datos = findViewById(R.id.et_datos);
        b_guardar = findViewById(R.id.b_guardar);
        b_leer = findViewById(R.id.b_leer);

        b_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] cadena2 = et_datos.getText().toString().split("\n");
                try {
                    OutputStream outputStream = openFileOutput(archivo, 0);
                    OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
                    BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);
                    for(int i= 0; i< cadena2.length; i++){
                        bufferedWriter.append(cadena2[i]);
                        bufferedWriter.append("\n");
                    }
                    bufferedWriter.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        b_leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream inputStream = openFileInput(archivo);
                    InputStreamReader streamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(streamReader);
                    String cadena;
                    while((cadena = bufferedReader.readLine()) != null){
                        et_datos.append(cadena);
                        et_datos.append("\n");
                    }
                    bufferedReader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}