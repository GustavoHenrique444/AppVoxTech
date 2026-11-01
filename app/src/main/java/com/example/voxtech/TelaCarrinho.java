package com.example.voxtech;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TelaCarrinho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_carrinho);

        // Conecta o botão de Finalizar Compra
        Button btnFinalizar = findViewById(R.id.btnFinalizarCompra);

        // ====== TRUQUE PARA ABRIR O NAVEGADOR ======
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Coloque o link do site do seu projeto aqui dentro das aspas:
                String linkDoSite = "https://www.google.com";

                // Cria a ordem para abrir o navegador com esse link
                Intent abrirNavegador = new Intent(Intent.ACTION_VIEW, Uri.parse(linkDoSite));
                startActivity(abrirNavegador);
            }
        });
    }
}