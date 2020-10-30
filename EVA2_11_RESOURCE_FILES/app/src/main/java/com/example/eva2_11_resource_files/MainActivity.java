package com.example.eva2_11_resource_files;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView tv_archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_archivo= findViewById(R.id.tv_archivo);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //abrir archivo

        //leerlo en forma de bytes

        //interpretarlo (convertirlo a caracteres)

        InputStream inputStream = getResources().openRawResource(R.raw.archivo);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader( streamReader);
        String cadena;
        try{
            while((cadena = bufferedReader.readLine()) != null){
                tv_archivo.append(cadena);
                tv_archivo.append("\n");

            }
            bufferedReader.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}