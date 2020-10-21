package com.example.eva2_9_transiciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    ImageView iv_peq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this,MainActivity2.class);
        iv_peq = findViewById(R.id.iv_peq);

    }
    public void onClick(View v) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, iv_peq, "mi_transicion");
        startActivity(intent,options.toBundle());
    }

}