package com.example.voxtech;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText campoEmail = findViewById(R.id.editTextTextEmailAddress);
        EditText campoSenha = findViewById(R.id.editTextTextPassword);
        Button botaoEntrar = findViewById(R.id.button);
        TextView txtEntrarSemUsuario = findViewById(R.id.textView2);



        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // .trim() remove espaços acidentais antes ou depois do texto
                String email = campoEmail.getText().toString().trim();
                String senha = campoSenha.getText().toString().trim();


                if (email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Digite seu e-mail e senha!", Toast.LENGTH_SHORT).show();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(MainActivity.this, "Digite um e-mail válido (ex: usuario@email.com)", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent irParaLoja = new Intent(MainActivity.this, TelaProdutos.class);
                    startActivity(irParaLoja);
                }
            }
        });



        txtEntrarSemUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irParaLoja = new Intent(MainActivity.this, TelaProdutos.class);
                startActivity(irParaLoja);
            }
        });
    }
}