package com.vedruna.perezvazquez01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class activity_login extends AppCompatActivity {

    private TextInputEditText usuario;
    private TextInputEditText contraseña;

    private TextView respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = findViewById(R.id.texto_Nombre);
        contraseña = findViewById(R.id.texto_Password);
        respuesta = findViewById(R.id.texto_correcto);


    }
    public void login(View view){
        String user = usuario.getText().toString();
        String password = contraseña.getText().toString();
        if(user.equals("admin") && password.equals("admin")){

            respuesta.setText("Usuario y contraseña correcta");
            Intent intent=new Intent(this,MainActivity.class);
            intent.putExtra("usuario",user);
            startActivity(intent);
        }else {
            respuesta.setText("Usuario o contraseña incorrecta");
        }
    }


}