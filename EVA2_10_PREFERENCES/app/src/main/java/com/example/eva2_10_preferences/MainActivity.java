package com.example.eva2_10_preferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.settingsLayout, new SettingsFragment());
        ft.commit();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Toast.makeText(this, preferences.getString("dias","nade"), Toast.LENGTH_SHORT).show();
      //  Toast.makeText(this,""+ preferences.getBoolean("laboral", false), Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, preferences.getString("nombre", "nombre default"), Toast.LENGTH_SHORT).show();
    }
}