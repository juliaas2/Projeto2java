package br.insper.ecommerce;

import br.insper.ecommerce.cliente.Cliente;
import br.insper.ecommerce.produto.Produto;
import br.insper.ecommerce.compra.CompraService;
import br.insper.ecommerce.cliente.ClienteService;
import br.insper.ecommerce.compra.Item;
import br.insper.ecommerce.pagamento.Boleto;
import br.insper.ecommerce.pagamento.CartaoCredito;
import br.insper.ecommerce.pagamento.MeioPagamento;
import br.insper.ecommerce.pagamento.Pix;
import br.insper.ecommerce.produto.ProdutoService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ClienteService clienteService = new ClienteService();
        ProdutoService produtoService = new ProdutoService();
        String opcao = "0";
        CompraService compraService = new CompraService();
        Item item = new Item();
        while(!opcao.equalsIgnoreCase("9")) {

            System.out.println("""
                    1 - Cadastrar Cliente
                    2 - Listar Clientes
                    3 - Excluir Cliente
                    4 - Cadastrar Produtos
                    5 - Listar Produtos
                    6 - Excluir Produtos
                    7 - Cadastrar Compra
                    8 - Listar Compras
                    9 - Sair
                    """);
            opcao = scanner.nextLine();
            if (opcao.equalsIgnoreCase("1")) {

                System.out.println("Digite o nome do cliente:");
                String nome = scanner.nextLine();
                System.out.println("Digite o CPF do cliente;");
                String cpf = scanner.nextLine();

                if (nome.equals("") || cpf.equals("")) {
                    System.out.println("Dados do cliente invalido.");
                } else {
                    Cliente cliente = new Cliente(nome, cpf, null);
                    clienteService.cadastrarCliente(nome, cpf);
                    System.out.println("Cliente cadastrado com sucesso.");
                }

            }

            if (opcao.equalsIgnoreCase("2")) {

                System.out.println("Lista de clientes:");
                clienteService.listarClientes();
            }

            if (opcao.equalsIgnoreCase("3")) {

                System.out.println("Digite o cpf do cliente para deletar:");
                String cpf = scanner.nextLine();
                clienteService.excluirClientes(cpf);
            }

            if (opcao.equalsIgnoreCase("4")) {

                System.out.println("Digite o nome do produto:");
                String nome = scanner.nextLine();
                System.out.println("Digite o preço do produto:");
                Double preco  = scanner.nextDouble();

                produtoService.cadastrarProduto(nome,preco);

            }

            if (opcao.equalsIgnoreCase("5")) {

                produtoService.listarProdutos();


            }

            if (opcao.equalsIgnoreCase("6")) {

                System.out.println("Digite o nome do produto para deletar:");
                String nome = scanner.nextLine();
                produtoService.excluirProdutos(nome);

            }

            if (opcao.equalsIgnoreCase("7")) {
                System.out.println("Digite o cpf do cliente:");
                String cpf = scanner.nextLine();

                Cliente cliente = clienteService.buscaCliente(cpf);

                System.out.println("Digite o nome do produto:");
                String nome = scanner.nextLine();
                System.out.println("Digite a quantidade do produto:");
                int quantidade = scanner.nextInt();
                scanner.nextLine();

                item.setProduto(produtoService.buscaProduto(nome));
                item.setQuantidade(quantidade);

                System.out.println("""
                    Escolha o Meio de Pagamento:
                    1 - Cartão de Crédito
                    2 - Pix
                    3 - Boleto
                    """);
                String meioPagamento = scanner.nextLine();

                MeioPagamento meioPagamento1 = null;


                if (meioPagamento.equalsIgnoreCase("1")) {
                    System.out.println("Digite o número do cartão:");
                    String numeroCartao = scanner.nextLine();

                    System.out.println("Digite a bandeira do cartão:");
                    String bandeiraCartao = scanner.nextLine();

                    meioPagamento1 = new CartaoCredito(true, LocalDateTime.now(), numeroCartao, bandeiraCartao);
                }

                if (meioPagamento.equalsIgnoreCase("2")) {
                    System.out.println("Digite a chave pix:");
                    String chavePix = scanner.nextLine();

                    System.out.println("Digite o QRCode:");
                    String qrCodePix = scanner.nextLine();

                    meioPagamento1 = new Pix(true, LocalDateTime.now(), chavePix, qrCodePix);
                }

                if (meioPagamento.equalsIgnoreCase("3")) {
                    System.out.println("Digite o código de barras do boleto:");
                    String codigoBoleto = scanner.nextLine();

                    meioPagamento1 = new Boleto(true, LocalDateTime.now(), codigoBoleto);
                }

                compraService.cadastrarCompra(item, cliente, meioPagamento1);

            }
            if (opcao.equalsIgnoreCase("8")) {
                compraService.listarCompras();
            }

        }


    }

}