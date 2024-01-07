package com.vedruna.perezvazquez01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = getIntent().getStringExtra("usuario");


        TextView tvUsername = findViewById(R.id.texto_LoginCorrecto);
        tvUsername.setText("by " + username);
    }
}