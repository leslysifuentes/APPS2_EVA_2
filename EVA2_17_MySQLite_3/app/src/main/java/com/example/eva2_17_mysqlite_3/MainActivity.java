package com.example.eva2_17_mysqlite_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv_datos;
    SQLiteDatabase db;
    final String BASE_DATOS = "base_datos";
    List<String> nombres = new ArrayList<String>();

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

        //insert
        ContentValues values = new ContentValues();
        values.put("nombre", "Lesly");
        values.put("apellido", "Sifuentes");
        db.insert("tabla", null, values);
        values.clear();
        values.put("nombre", "Paola");
        values.put("apellido", "Ramírez");
        db.insert("tabla", null, values);
        values.clear();
        values.put("nombre", "Carlos");
        values.put("apellido", "Rivas");
        db.insert("tabla", null, values);
        values.clear();



       //regresa el id
        long clave;
        values.put("nombre", "Denisse");
        values.put("apellido", "Nevárez");
        clave= db.insert("tabla", null, values);
        Toast.makeText(this, ""+clave, Toast.LENGTH_SHORT).show();



        //update
        values.clear();
        values.put("nombre", "Sandra");
        String[] args={""+clave,"Denisse"};
        db.update("tabla", values, "id = ? and nombre = ?", args);


        //delete
        String[] args2 = {"Carlos"};
        db.delete("tabla", "nombre = ?",args2);


        //query
       String[] columns= {"id", "nombre", "apellido"};
        String[] args3 = {"Lesly"};
        Cursor cursor = db.query("tabla",
                columns,
                "nombre like ?",
                args3,
                null,
                null,
                "apellido"
        );

    //    Cursor cursor = db.rawQuery("select * from tabla;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            nombres.add(cursor.getString(cursor.getColumnIndex("id")) +
                    " " + cursor.getString(cursor.getColumnIndex("nombre")) +
                    " " + cursor.getString(cursor.getColumnIndex("apellido")));
            cursor.moveToNext();
        }
        lv_datos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres));



    }
}