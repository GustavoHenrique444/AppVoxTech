package com.example.voxtech;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TelaDetalheProdutos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_produtos);
        findViewById(R.id.btnVoltarDetalhe).setOnClickListener(v -> finish());

        TextView textoNome = findViewById(R.id.txtNomeDetalhe);
        TextView textoPreco = findViewById(R.id.txtPrecoDetalhe);
        TextView textoDescricao = findViewById(R.id.txtDescricaoDetalhe);
        Button btnAdicionar = findViewById(R.id.btnAdicionarCarrinho);

        Intent mochila = getIntent();
        String nomeRecebido = mochila.getStringExtra("NOME");
        String precoTextoRecebido = mochila.getStringExtra("PRECO_TEXTO");
        double precoNumeroRecebido = mochila.getDoubleExtra("PRECO_NUMERO", 0.0);
        String descricaoRecebida = mochila.getStringExtra("DESCRICAO");

        if (nomeRecebido != null) {
            textoNome.setText(nomeRecebido);
            textoPreco.setText(precoTextoRecebido);
            textoDescricao.setText(descricaoRecebida);
        }

        btnAdicionar.setOnClickListener(v -> {
            // Salva o produto atual na memória do carrinho!
            if (nomeRecebido != null) {
                DadosCarrinho.adicionar(nomeRecebido, precoNumeroRecebido);
                Toast.makeText(this, "Adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
            }

            Intent irParaCarrinho = new Intent(TelaDetalheProdutos.this, TelaCarrinho.class);
            startActivity(irParaCarrinho);
        });
    }
}