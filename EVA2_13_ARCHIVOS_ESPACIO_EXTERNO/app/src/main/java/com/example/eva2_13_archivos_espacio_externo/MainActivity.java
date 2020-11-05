package com.example.eva2_13_archivos_espacio_externo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText et_datos;
    Button b_guardar, b_leer;
    String rutaSDApp, rutaSD;
    boolean bo_escribir = false;
    final static int PERMISO_WRITE= 20;
    final static String ARCHIVO = "archivo.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_datos = findViewById(R.id.et_datos);
        b_guardar = findViewById(R.id.b_guardar);
        b_leer = findViewById(R.id.b_leer);
        rutaSD = Environment.getExternalStorageDirectory().getAbsolutePath();
        rutaSDApp = getExternalFilesDir(null).getAbsolutePath();
        //es directo
        Log.wtf("rutaSd",rutaSD);
        //se almacena en data
        Log.wtf("rutaSd",rutaSDApp);
        //if. que version tiene el disp
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED ){
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISO_WRITE);
            }
            else{
                bo_escribir = true;
            }
        }
        else{
            bo_escribir = true;
        }

        b_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bo_escribir){
                    String[] cadenas = et_datos.getText().toString().split("\n");
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(rutaSD +"/" +ARCHIVO);
                        OutputStreamWriter streamWriter = new OutputStreamWriter(fileOutputStream);
                        BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);

                        for(int i= 0; i < cadenas.length;i++){
                            bufferedWriter.append(cadenas[i]);
                            bufferedWriter.append("\n");
                        }
                        bufferedWriter.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        b_leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bo_escribir){
                    String cade;
                    try {
                        FileInputStream fileInputStream = new FileInputStream(rutaSD+ "/"+ARCHIVO);
                        InputStreamReader streamReader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(streamReader);
                        while((cade = bufferedReader.readLine()) != null){
                            et_datos.append(cade);
                            et_datos.append("\n");
                        }
                        bufferedReader.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISO_WRITE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                bo_escribir = true;
            }
        }
    }
}