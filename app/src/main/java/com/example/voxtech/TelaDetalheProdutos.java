package com.example.voxtech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TelaDetalheProdutos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe_produtos);

        // Conecta os textos visuais
        TextView textoNome = findViewById(R.id.txtNomeDetalhe);
        TextView textoPreco = findViewById(R.id.txtPrecoDetalhe);
        TextView textoDescricao = findViewById(R.id.txtDescricaoDetalhe);

        // Conecta o botão de adicionar ao carrinho
        Button btnAdicionar = findViewById(R.id.btnAdicionarCarrinho);

        // Pega as informações da mochila
        Intent mochila = getIntent();
        String nomeRecebido = mochila.getStringExtra("NOME");
        String precoRecebido = mochila.getStringExtra("PRECO");
        String descricaoRecebida = mochila.getStringExtra("DESCRICAO");

        // Atualiza a tela
        if (nomeRecebido != null) {
            textoNome.setText(nomeRecebido);
            textoPreco.setText(precoRecebido);
            textoDescricao.setText(descricaoRecebida);
        }

        // ====== NAVEGAÇÃO PARA O CARRINHO ======
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irParaCarrinho = new Intent(TelaDetalheProdutos.this, TelaCarrinho.class);
                startActivity(irParaCarrinho);
            }
        });
    }
}