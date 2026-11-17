package com.example.voxtech;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Locale;

public class TelaCarrinho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_carrinho);

        LinearLayout container = findViewById(R.id.containerCarrinho);
        TextView txtTotal = findViewById(R.id.txtTotalCarrinho);
        Button btnFinalizar = findViewById(R.id.btnFinalizarCompra);

        // Verifica se o carrinho está vazio
        if (DadosCarrinho.nomes.isEmpty()) {
            TextView avisoVazio = new TextView(this);
            avisoVazio.setText("Seu carrinho está vazio no momento.");
            avisoVazio.setTextColor(Color.parseColor("#9D95AA"));
            avisoVazio.setTextSize(16);
            container.addView(avisoVazio);
        } else {
            // Cria um cartão escuro para cada produto adicionado!
            for (int i = 0; i < DadosCarrinho.nomes.size(); i++) {
                String nomeProduto = DadosCarrinho.nomes.get(i);
                double precoProduto = DadosCarrinho.precos.get(i);

                CardView card = new CardView(this);
                LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                cardParams.setMargins(0, 0, 0, 24);
                card.setLayoutParams(cardParams);
                card.setCardBackgroundColor(Color.parseColor("#16121F"));
                card.setRadius(24);
                card.setCardElevation(0);

                LinearLayout linha = new LinearLayout(this);
                linha.setOrientation(LinearLayout.VERTICAL);
                linha.setPadding(36, 36, 36, 36);

                TextView txtNome = new TextView(this);
                txtNome.setText(nomeProduto);
                txtNome.setTextColor(Color.WHITE);
                txtNome.setTextSize(18);

                TextView txtPreco = new TextView(this);
                txtPreco.setText(String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", precoProduto));
                txtPreco.setTextColor(Color.parseColor("#A970FF"));
                txtPreco.setTextSize(16);

                linha.addView(txtNome);
                linha.addView(txtPreco);
                card.addView(linha);
                container.addView(card);
            }
        }

        // Atualiza o valor total lá embaixo
        double total = DadosCarrinho.calcularTotal();
        txtTotal.setText(String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", total));

        // Redireciona para o site
        btnFinalizar.setOnClickListener(v -> {
            if (DadosCarrinho.nomes.isEmpty()) {
                Toast.makeText(this, "Adicione um produto antes de finalizar!", Toast.LENGTH_SHORT).show();
            } else {
                String linkDoSite = "https://www.google.com";
                Intent abrirNavegador = new Intent(Intent.ACTION_VIEW, Uri.parse(linkDoSite));
                startActivity(abrirNavegador);
            }
        });
    }
}