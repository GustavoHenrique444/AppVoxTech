package com.example.voxtech;

import java.util.ArrayList;

public class DadosCarrinho {
    // Listas que guardam os nomes e preços enquanto o app estiver aberto
    public static ArrayList<String> nomes = new ArrayList<>();
    public static ArrayList<Double> precos = new ArrayList<>();

    // Função para adicionar um item novo
    public static void adicionar(String nome, double preco) {
        nomes.add(nome);
        precos.add(preco);
    }

    // Função para calcular o valor total
    public static double calcularTotal() {
        double total = 0;
        for (double p : precos) {
            total += p;
        }
        return total;
    }
}