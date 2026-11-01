package com.example.voxtech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TelaProdutos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_produtos);

        // BOTÃO DO PRODUTO 1
        Button btnProduto1 = findViewById(R.id.btnProd1);
        btnProduto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui está a mudança: chamando TelaDetalheProdutos (com S)
                Intent mochila = new Intent(TelaProdutos.this, TelaDetalheProdutos.class);
                mochila.putExtra("NOME", "Smartphone Vox 1");
                mochila.putExtra("PRECO", "R$ 1.500,00");
                mochila.putExtra("DESCRICAO", "O Smartphone Vox 1 tem tela AMOLED, 128GB de memória e uma câmera incrível para o seu dia a dia.");
                startActivity(mochila);
            }
        });

        // BOTÃO DO PRODUTO 2
        Button btnProduto2 = findViewById(R.id.btnProd2);
        btnProduto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mochila = new Intent(TelaProdutos.this, TelaDetalheProdutos.class);
                mochila.putExtra("NOME", "Fone Bluetooth Vox");
                mochila.putExtra("PRECO", "R$ 250,00");
                mochila.putExtra("DESCRICAO", "Fone com cancelamento de ruído e bateria que dura 24 horas ininterruptas.");
                startActivity(mochila);
            }
        });

        // BOTÃO DO PRODUTO 3
        Button btnProduto3 = findViewById(R.id.btnProd3);
        btnProduto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mochila = new Intent(TelaProdutos.this, TelaDetalheProdutos.class);
                mochila.putExtra("NOME", "Smartwatch Vox");
                mochila.putExtra("PRECO", "R$ 450,00");
                mochila.putExtra("DESCRICAO", "Relógio inteligente que monitoriza a sua saúde e recebe notificações do seu telemóvel.");
                startActivity(mochila);
            }
        });
    }
}