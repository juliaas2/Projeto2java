
package br.insper.ecommerce.compra;

import br.insper.ecommerce.cliente.Cliente;
import br.insper.ecommerce.pagamento.MeioPagamento;
import java.util.ArrayList;

public class CompraService {
    private ArrayList<Compra> compras = new ArrayList<>();
    public void cadastrarCompra(Item item, Cliente cliente, MeioPagamento meioPagamento) {
        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setMeioPagamento(meioPagamento);
        compra.adicionarItem(item);
        compra.calculaPrecoTotal();
        compras.add(compra);
    }
    public void listarCompras() {
        System.out.println("Lista de Compras:");
        for (Compra compra : compras) {
            System.out.println("Nome do Cliente: " + compra.getCliente().getNome());
            System.out.println("CPF do Cliente: " + compra.getCliente().getCpf());
            System.out.println("Data da Compra: " + compra.getDataCompra());
            System.out.println("Preço Total: " + compra.getPrecoTotal());
            System.out.println("Meio de Pagamento: " + compra.getMeioPagamento());
        }
    }
}