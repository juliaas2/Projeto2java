package br.insper.ecommerce.produto;

import br.insper.ecommerce.cliente.Cliente;

import java.util.ArrayList;

public class ProdutoService {

    private ArrayList<Produto> produto = new ArrayList<>();

    private ArrayList<Produto> produtos = new ArrayList<>();

    public void cadastrarProduto(String nome, Double preco) {

        if (nome.equals("") || preco.equals("")) {
            System.out.println("Dados do produto inválidos.");
        } else {
            Produto produto = new Produto(nome, preco);
            produtos.add(produto);
            System.out.println("Produto cadastrado com sucesso.");
        }

    }

    public void excluirProdutos(String nome) {
        Produto produtoRemover = null;
        for (Produto produto : produtos) {
            if (nome.equalsIgnoreCase(produto.getNome())) {
                produtoRemover = produto;
            }
        }
        if (produtoRemover != null) {
            System.out.println("Produto removido com sucesso");
            produtos.remove(produtoRemover);
        } else {
            System.out.println("Produto não encontrado");
        }
    }
    public Produto buscaProduto(String nome) {
        Produto produtoBusca = null;
        for (Produto produto : produtos) {
            if (nome.equalsIgnoreCase(produto.getNome())) {
                return produto;
            }
        }
        return null;

    }

    public void listarProdutos() {
        System.out.println("Lista de produtos:");
        for (Produto produto : produtos) {
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preco: " + produto.getPreco());
        }
    }
}
