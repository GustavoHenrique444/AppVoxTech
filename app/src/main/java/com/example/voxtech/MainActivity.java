package com.example.voxtech;

import android.content.Intent;
import android.os.Bundle;
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

        // 1. Conecta os campos visuais ao Java através dos IDs
        EditText campoEmail = findViewById(R.id.editTextTextEmailAddress);
        EditText campoSenha = findViewById(R.id.editTextTextPassword);
        Button botaoEntrar = findViewById(R.id.button);
        TextView txtEntrarSemUsuario = findViewById(R.id.textView2);


        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pega o texto exato que o utilizador digitou
                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();

                // Validação 1: Campos Vazios
                if (email.isEmpty() || senha.isEmpty()) {
                    // O Toast é aquela pequena mensagem preta que aparece no fundo da tela e some rápido
                    Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
                // Validação 2: Simular um utilizador real para a apresentação
                else if (email.equals("admin@voxtech.com") && senha.equals("123456")) {
                    // Dados corretos! Libera o acesso para a tela de produtos
                    Intent irParaLoja = new Intent(MainActivity.this, TelaProdutos.class);
                    startActivity(irParaLoja);
                }
                // Validação 3: Digitou algo, mas está errado
                else {
                    Toast.makeText(MainActivity.this, "E-mail ou senha incorretos!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtEntrarSemUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vai direto para a loja sem exigir nada
                Intent irParaLoja = new Intent(MainActivity.this, TelaProdutos.class);
                startActivity(irParaLoja);
            }
        });
    }
}