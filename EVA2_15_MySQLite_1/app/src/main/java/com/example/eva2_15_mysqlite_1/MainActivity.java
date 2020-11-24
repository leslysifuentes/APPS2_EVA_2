package com.example.eva2_15_mysqlite_1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //crea o abre la bd en el espacio interno
        database = openOrCreateDatabase("mi_bd", MODE_PRIVATE, null);

        try {
            database.execSQL("create table prueba(id integer primary key autoincrement, " +
                    "columna1 text," +
                    "columna2 integer);");
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        try {
            database.execSQL("insert into prueba(columna1, columna2) values('lesly', 20);");
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
}