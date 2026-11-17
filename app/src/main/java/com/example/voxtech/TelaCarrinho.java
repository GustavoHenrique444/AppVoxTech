package com.example.voxtech;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Locale;

public class TelaCarrinho extends AppCompatActivity {

    private LinearLayout container;
    private TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Carrega o layout da tela do carrinho
        setContentView(R.layout.activity_tela_carrinho);

        // Botão de voltar no topo: fecha o carrinho e volta para a tela anterior
        View btnVoltar = findViewById(R.id.btnVoltarCarrinho);
        if (btnVoltar != null) {
            btnVoltar.setOnClickListener(v -> finish());
        }

        // Liga o container da lista, o texto do total e o botão de finalizar
        container = findViewById(R.id.containerCarrinho);
        txtTotal = findViewById(R.id.txtTotalCarrinho);
        Button btnFinalizar = findViewById(R.id.btnFinalizarCompra);

        // Monta os itens na tela assim que ela abre
        atualizarInterfaceCarrinho();

        // Ação ao clicar em "Finalizar Compra"
        btnFinalizar.setOnClickListener(v -> {
            if (DadosCarrinho.nomes.isEmpty()) {
                Toast.makeText(this, "Adicione um produto antes de finalizar!", Toast.LENGTH_SHORT).show();
            } else {
                // Abre o link externo no navegador do celular
                String linkDoSite = "https://www.google.com";
                Intent abrirNavegador = new Intent(Intent.ACTION_VIEW, Uri.parse(linkDoSite));
                startActivity(abrirNavegador);
            }
        });
    }

    // Desenha os cartões de produtos do carrinho e recalcula o valor total
    private void atualizarInterfaceCarrinho() {
        // Limpa a lista visual antes de redesenhar
        container.removeAllViews();

        // Se o carrinho estiver vazio, mostra apenas uma mensagem centralizada
        if (DadosCarrinho.nomes.isEmpty()) {
            TextView avisoVazio = new TextView(this);
            avisoVazio.setText("Seu carrinho está vazio no momento.");
            avisoVazio.setTextColor(Color.parseColor("#9D95AA"));
            avisoVazio.setTextSize(16);
            avisoVazio.setGravity(Gravity.CENTER);
            avisoVazio.setPadding(0, 80, 0, 0);
            container.addView(avisoVazio);
        } else {
            // Percorre cada produto salvo na lista e cria seu cartão na tela
            for (int i = 0; i < DadosCarrinho.nomes.size(); i++) {
                final int index = i;
                String nomeProduto = DadosCarrinho.nomes.get(i);
                double precoUnitario = DadosCarrinho.precos.get(i);
                int quantidade = DadosCarrinho.quantidades.get(i);
                double subtotal = precoUnitario * quantidade;

                // Cria o cartão escuro arredondado do item
                CardView card = new CardView(this);
                LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                cardParams.setMargins(0, 0, 0, 24);
                card.setLayoutParams(cardParams);
                card.setCardBackgroundColor(Color.parseColor("#16121F"));
                card.setRadius(36);
                card.setCardElevation(0);

                // Linha interna que organiza: [Nome/Preço] + [Quantidade] + [Lixeira]
                LinearLayout linhaPrincipal = new LinearLayout(this);
                linhaPrincipal.setOrientation(LinearLayout.HORIZONTAL);
                linhaPrincipal.setGravity(Gravity.CENTER_VERTICAL);
                linhaPrincipal.setPadding(36, 36, 32, 36);

                // Coluna com o nome e o subtotal do produto
                LinearLayout colunaInfo = new LinearLayout(this);
                colunaInfo.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams infoParams = new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                );
                infoParams.setMargins(0, 0, 16, 0);
                colunaInfo.setLayoutParams(infoParams);

                TextView txtNome = new TextView(this);
                txtNome.setText(nomeProduto);
                txtNome.setTextColor(Color.WHITE);
                txtNome.setTextSize(16);
                txtNome.setTypeface(null, Typeface.BOLD);

                TextView txtPreco = new TextView(this);
                txtPreco.setText(String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", subtotal));
                txtPreco.setTextColor(Color.parseColor("#A970FF"));
                txtPreco.setTextSize(15);
                txtPreco.setTypeface(null, Typeface.BOLD);
                txtPreco.setPadding(0, 8, 0, 0);

                colunaInfo.addView(txtNome);
                colunaInfo.addView(txtPreco);

                // Caixa que agrupa os botões de diminuir e aumentar quantidade
                LinearLayout boxQuantidade = new LinearLayout(this);
                boxQuantidade.setOrientation(LinearLayout.HORIZONTAL);
                boxQuantidade.setGravity(Gravity.CENTER_VERTICAL);
                boxQuantidade.setPadding(12, 8, 12, 8);

                GradientDrawable fundoBox = new GradientDrawable();
                fundoBox.setColor(Color.parseColor("#0B0912"));
                fundoBox.setCornerRadius(24);
                fundoBox.setStroke(2, Color.parseColor("#282133"));
                boxQuantidade.setBackground(fundoBox);

                // Botão "-" para diminuir a quantidade (mínimo 1)
                TextView btnMenos = new TextView(this);
                btnMenos.setText("−");
                btnMenos.setTextSize(18);
                btnMenos.setTypeface(null, Typeface.BOLD);
                btnMenos.setGravity(Gravity.CENTER);
                btnMenos.setWidth(64);
                btnMenos.setHeight(64);
                btnMenos.setTextColor(quantidade > 1 ? Color.parseColor("#A970FF") : Color.parseColor("#4C3575"));
                btnMenos.setOnClickListener(v -> {
                    if (DadosCarrinho.quantidades.get(index) > 1) {
                        DadosCarrinho.diminuirQuantidade(index);
                        atualizarInterfaceCarrinho();
                    }
                });

                // Texto que mostra a quantidade atual do item
                TextView txtQtd = new TextView(this);
                txtQtd.setText(String.valueOf(quantidade));
                txtQtd.setTextColor(Color.WHITE);
                txtQtd.setTextSize(15);
                txtQtd.setTypeface(null, Typeface.BOLD);
                txtQtd.setGravity(Gravity.CENTER);
                txtQtd.setPadding(16, 0, 16, 0);

                // Botão "+" para aumentar a quantidade
                TextView btnMais = new TextView(this);
                btnMais.setText("+");
                btnMais.setTextColor(Color.parseColor("#A970FF"));
                btnMais.setTextSize(18);
                btnMais.setTypeface(null, Typeface.BOLD);
                btnMais.setGravity(Gravity.CENTER);
                btnMais.setWidth(64);
                btnMais.setHeight(64);
                btnMais.setOnClickListener(v -> {
                    DadosCarrinho.aumentarQuantidade(index);
                    atualizarInterfaceCarrinho();
                });

                boxQuantidade.addView(btnMenos);
                boxQuantidade.addView(txtQtd);
                boxQuantidade.addView(btnMais);

                // Botão de lixeira para excluir o produto do carrinho
                ImageButton btnLixeira = new ImageButton(this);
                btnLixeira.setImageResource(android.R.drawable.ic_menu_delete);
                btnLixeira.setColorFilter(Color.parseColor("#A970FF"), PorterDuff.Mode.SRC_IN);
                btnLixeira.setBackgroundColor(Color.TRANSPARENT);
                LinearLayout.LayoutParams lixeiraParams = new LinearLayout.LayoutParams(84, 84);
                lixeiraParams.setMargins(16, 0, 0, 0);
                btnLixeira.setLayoutParams(lixeiraParams);

                btnLixeira.setOnClickListener(v -> {
                    DadosCarrinho.removerItem(index);
                    Toast.makeText(this, "Item removido do carrinho", Toast.LENGTH_SHORT).show();
                    atualizarInterfaceCarrinho();
                });

                // Adiciona todos os elementos dentro do cartão e coloca na tela
                linhaPrincipal.addView(colunaInfo);
                linhaPrincipal.addView(boxQuantidade);
                linhaPrincipal.addView(btnLixeira);
                card.addView(linhaPrincipal);
                container.addView(card);
            }
        }

        // Atualiza o valor total a pagar no rodapé
        double total = DadosCarrinho.calcularTotal();
        txtTotal.setText(String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", total));
    }
}