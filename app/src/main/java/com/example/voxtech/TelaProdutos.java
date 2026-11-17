package com.example.voxtech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TelaProdutos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Carrega o layout da tela de produtos
        setContentView(R.layout.activity_tela_produtos);

        // Liga os textos do cabeçalho onde aparecem o nome e o e-mail
        TextView txtNomeUsuario = findViewById(R.id.txtNomeUsuarioHeader);
        TextView txtEmailUsuario = findViewById(R.id.txtEmailUsuarioHeader);

        // Preenche o cabeçalho com os dados salvos no login
        if (txtNomeUsuario != null && txtEmailUsuario != null) {
            if (DadosCarrinho.nomeUsuario.equals("Sem usuário")) {
                txtNomeUsuario.setText("Sem usuário");
            } else {
                txtNomeUsuario.setText("Olá, " + DadosCarrinho.nomeUsuario);
            }
            txtEmailUsuario.setText(DadosCarrinho.emailUsuario);
        }

        // Botão "Sair": fecha a tela atual e volta para o login
        View btnSair = findViewById(R.id.btnSairLoja);
        if (btnSair != null) {
            btnSair.setOnClickListener(v -> finish());
        }

        // Ícone do carrinho no topo: abre diretamente a tela do carrinho
        View btnCarrinhoTopo = findViewById(R.id.btnIrCarrinhoTopo);
        if (btnCarrinhoTopo != null) {
            btnCarrinhoTopo.setOnClickListener(v -> {
                startActivity(new Intent(TelaProdutos.this, TelaCarrinho.class));
            });
        }

        // Configura o clique e os dados de cada um dos 10 produtos da loja
        configurarBotao(R.id.btnProd1, "Smartphone Vox 1", "R$ 1.500,00", 1500.00, "Tela AMOLED de 120Hz, 128GB de armazenamento e câmeras de alta definição.");
        configurarBotao(R.id.btnProd2, "Fone Bluetooth Vox", "R$ 250,00", 250.00, "Cancelamento ativo de ruído e bateria com duração de até 24 horas.");
        configurarBotao(R.id.btnProd3, "Smartwatch Vox", "R$ 450,00", 450.00, "Monitoramento cardíaco, GPS integrado e resistência à água.");
        configurarBotao(R.id.btnProd4, "SSD NVMe 1TB Vox", "R$ 429,00", 429.00, "Velocidade de leitura de até 3500MB/s para carregar jogos em segundos.");
        configurarBotao(R.id.btnProd5, "Placa-mãe B650 Pro", "R$ 1.199,00", 1199.00, "Suporte para processadores de última geração, Wi-Fi 6 e dissipadores reforçados.");
        configurarBotao(R.id.btnProd6, "Memória 32GB DDR5", "R$ 699,00", 699.00, "Alta frequência de 5600MHz com iluminação RGB sincronizável.");
        configurarBotao(R.id.btnProd7, "Mouse Pro RGB", "R$ 229,00", 229.00, "Sensor óptico de 16.000 DPI e design ultraleve de apenas 65g.");
        configurarBotao(R.id.btnProd8, "Teclado Mecânico Vox", "R$ 399,00", 399.00, "Switches mecânicos táteis, estrutura em alumínio e cabo removível.");
        configurarBotao(R.id.btnProd9, "Cadeira Gamer Vox", "R$ 1.299,00", 1299.00, "Ergonomia avançada com almofadas lombares e inclinação de até 180 graus.");
        configurarBotao(R.id.btnProd10, "Monitor 144Hz Ultra", "R$ 1.350,00", 1350.00, "Painel IPS de 27 polegadas com tempo de resposta de 1ms.");
    }

    // Método auxiliar que envia os dados do produto escolhido para a tela de detalhes
    private void configurarBotao(int idBotao, String nome, String precoTexto, double precoNumero, String descricao) {
        Button btn = findViewById(idBotao);
        if (btn != null) {
            btn.setOnClickListener(v -> {
                Intent mochila = new Intent(TelaProdutos.this, TelaDetalheProdutos.class);
                mochila.putExtra("NOME", nome);
                mochila.putExtra("PRECO_TEXTO", precoTexto);
                mochila.putExtra("PRECO_NUMERO", precoNumero);
                mochila.putExtra("DESCRICAO", descricao);
                startActivity(mochila);
            });
        }
    }
}