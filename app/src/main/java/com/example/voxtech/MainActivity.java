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
        // Carrega o layout da tela de login
        setContentView(R.layout.activity_main);

        // Liga os elementos visuais do XML às variáveis Java
        EditText campoEmail = findViewById(R.id.editTextTextEmailAddress);
        EditText campoSenha = findViewById(R.id.editTextTextPassword);
        Button botaoEntrar = findViewById(R.id.button);
        TextView txtEntrarSemUsuario = findViewById(R.id.textView2);

        // Ação ao clicar no botão principal "Entrar"
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pega o texto digitado e remove espaços extras nas pontas
                String email = campoEmail.getText().toString().trim();
                String senha = campoSenha.getText().toString().trim();

                // Verifica se algum campo está vazio
                if (email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Digite seu e-mail e senha!", Toast.LENGTH_SHORT).show();
                    // Verifica se o texto digitado tem formato válido de e-mail
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(MainActivity.this, "Digite um e-mail válido (ex: usuario@email.com)", Toast.LENGTH_SHORT).show();
                } else {
                    // Salva o nome e o e-mail do usuário na memória do app
                    DadosCarrinho.definirUsuarioPorEmail(email);

                    // Abre a tela do catálogo de produtos
                    Intent irParaLoja = new Intent(MainActivity.this, TelaProdutos.class);
                    startActivity(irParaLoja);
                }
            }
        });

        // Ação ao clicar em "Entrar sem usuário"
        txtEntrarSemUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define o acesso como visitante sem cadastro
                DadosCarrinho.definirSemUsuario();

                // Abre a tela do catálogo de produtos
                Intent irParaLoja = new Intent(MainActivity.this, TelaProdutos.class);
                startActivity(irParaLoja);
            }
        });
    }
}