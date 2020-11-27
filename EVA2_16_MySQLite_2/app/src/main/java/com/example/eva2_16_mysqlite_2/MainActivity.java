package com.example.eva2_16_mysqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv_datos;
    SQLiteDatabase db;
    final String BASE_DATOS = "base_datos";
    List <String> nombres = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_datos = findViewById(R.id.lv_datos);

        db = openOrCreateDatabase(BASE_DATOS, MODE_PRIVATE, null);

        try {
            db.execSQL("create table tabla(id integer primary key autoincrement," +
                    "nombre text," +
                    "apellido text)");
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        db.beginTransaction();
        try {
            db.execSQL("insert into tabla(nombre, apellido) values('Lesly', 'Sifuentes')");
            db.execSQL("insert into tabla(nombre, apellido) values('Karla', 'Esparza')");
            db.execSQL("insert into tabla(nombre, apellido) values('Paola', 'Ramirez')");
            //int i= 1/0;
            db.execSQL("insert into tabla(nombre, apellido) values('Denisse', 'Nevarez')");
            db.execSQL("insert into tabla(nombre, apellido) values('Carlos', 'Rivas')");
            db.execSQL("insert into tabla(nombre, apellido) values('Sara', 'Montes')");

            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }

        Cursor cursor = db.rawQuery("select * from tabla;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            nombres.add(cursor.getString(cursor.getColumnIndex("nombre")) +
                  " " + cursor.getString(cursor.getColumnIndex("apellido")));
            cursor.moveToNext();
        }
        lv_datos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres));

    }
}