package com.example.voxtech;

import java.util.ArrayList;

public class DadosCarrinho {
    // Listas que guardam os nomes, preços e quantidades dos itens no carrinho
    public static ArrayList<String> nomes = new ArrayList<>();
    public static ArrayList<Double> precos = new ArrayList<>();
    public static ArrayList<Integer> quantidades = new ArrayList<>();

    // Variáveis que guardam o nome e e-mail do usuário durante o uso do app
    public static String nomeUsuario = "Sem usuário";
    public static String emailUsuario = "Modo visitante";

    // Adiciona um produto novo ou soma +1 caso ele já esteja no carrinho
    public static void adicionar(String nome, double preco) {
        int index = nomes.indexOf(nome);
        if (index != -1) {
            quantidades.set(index, quantidades.get(index) + 1);
        } else {
            nomes.add(nome);
            precos.add(preco);
            quantidades.add(1);
        }
    }

    // Soma +1 na quantidade do produto na posição informada
    public static void aumentarQuantidade(int index) {
        if (index >= 0 && index < quantidades.size()) {
            quantidades.set(index, quantidades.get(index) + 1);
        }
    }

    // Subtrai -1 na quantidade do produto, mantendo o mínimo de 1 unidade
    public static void diminuirQuantidade(int index) {
        if (index >= 0 && index < quantidades.size()) {
            int qtdAtual = quantidades.get(index);
            if (qtdAtual > 1) {
                quantidades.set(index, qtdAtual - 1);
            }
        }
    }

    // Remove o produto das três listas do carrinho
    public static void removerItem(int index) {
        if (index >= 0 && index < nomes.size()) {
            nomes.remove(index);
            precos.remove(index);
            quantidades.remove(index);
        }
    }

    // Soma o preço multiplicado pela quantidade de todos os itens
    public static double calcularTotal() {
        double total = 0;
        for (int i = 0; i < precos.size(); i++) {
            total += precos.get(i) * quantidades.get(i);
        }
        return total;
    }

    // Pega o texto antes do "@" do e-mail para exibir como primeiro nome
    public static void definirUsuarioPorEmail(String email) {
        emailUsuario = email;
        String antesDoArroba = email.split("@")[0];
        String[] partes = antesDoArroba.split("[0-9._-]+");
        String primeiroNome = partes.length > 0 && !partes[0].isEmpty() ? partes[0] : antesDoArroba;

        if (primeiroNome.length() > 0) {
            nomeUsuario = primeiroNome.substring(0, 1).toUpperCase() + primeiroNome.substring(1).toLowerCase();
        } else {
            nomeUsuario = "Usuário";
        }
    }

    // Define os textos padrão quando o usuário entra sem cadastro
    public static void definirSemUsuario() {
        nomeUsuario = "Sem usuário";
        emailUsuario = "Acesso sem cadastro";
    }
}