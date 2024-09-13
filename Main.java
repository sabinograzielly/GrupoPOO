package programs;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private static Cliente[] clientes = new Cliente[10];
    private static ItemMenu[] menu = new ItemMenu[10];
    private static Pedido[] pedidos = new Pedido[10];
    private static int clienteCount = 0;
    private static int menuCount = 0;
    private static int pedidoCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        inicializarMenu();

        while (true) {
            System.out.println("\n--- Sabor Gourmet ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Visualizar Menu");
            System.out.println("3. Fazer Pedido");
            System.out.println("4. Acompanhar Pedido");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente(sc);
                    break;
                case 2:
                    visualizarMenu();
                    break;
                case 3:
                    fazerPedido(sc);
                    break;
                case 4:
                    acompanharPedido(sc);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarCliente(Scanner sc) {
        if (clienteCount < clientes.length) {
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Endereço: ");
            String endereco = sc.nextLine();
            System.out.print("Telefone: ");
            String telefone = sc.nextLine();

            Cliente novoCliente = new Cliente(nome, endereco, telefone);
            clientes[clienteCount] = novoCliente;
            clienteCount++;
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Capacidade máxima de clientes atingida.");
        }
    }

    private static void visualizarMenu() {
        System.out.println("\n--- Menu ---");
        for (int i = 0; i < menuCount; i++) {
            menu[i].getDetalhesItem();
        }
    }

    private static void fazerPedido(Scanner sc) {
        if (clienteCount == 0) {
            System.out.println("Nenhum cliente cadastrado. Cadastre-se primeiro.");
            return;
        }

        System.out.print("Nome do cliente: ");
        String nomeCliente = sc.nextLine();
        Cliente cliente = encontrarCliente(nomeCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        if (pedidoCount < pedidos.length) {
            Pedido pedido = new Pedido(cliente, 5);

            while (true) {
                visualizarMenu();
                System.out.print("Digite o nome do item para adicionar ao pedido (ou 'finalizar' para concluir): ");
                String nomeItem = sc.nextLine();

                if (nomeItem.equalsIgnoreCase("finalizar")) {
                    break;
                }

                ItemMenu item = encontrarItem(nomeItem);
                if (item != null) {
                    pedido.adicionarItem(item);
                } else {
                    System.out.println("Item não encontrado.");
                }
            }

            pedido.calcularTotal();
            pedidos[pedidoCount] = pedido;
            pedidoCount++;

            System.out.println("Pedido realizado com sucesso!");
            pedido.exibirPedido();
        } else {
            System.out.println("Capacidade máxima de pedidos atingida.");
        }
    }

    private static void acompanharPedido(Scanner sc) {
        if (pedidoCount == 0) {
            System.out.println("Nenhum pedido foi realizado ainda.");
            return;
        }

        System.out.print("Nome do cliente: ");
        String nomeCliente = sc.nextLine();
        Cliente cliente = encontrarCliente(nomeCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Pedido pedido = encontrarPedidoPorCliente(cliente);
        if (pedido != null) {
            try {
                pedido.atualizarStatus("Em preparação");
                pedido.exibirPedido();
                TimeUnit.SECONDS.sleep(5);

                pedido.atualizarStatus("Pronto para entrega");
                pedido.exibirPedido();
                TimeUnit.SECONDS.sleep(5);

                pedido.atualizarStatus("Entregue");
                pedido.exibirPedido();
            } catch (InterruptedException e) {
                System.out.println("Erro no temporizador.");
            }
        } else {
            System.out.println("Nenhum pedido encontrado para este cliente.");
        }
    }

    private static void inicializarMenu() {
        menu[menuCount++] = new ItemMenu("Misto", "Lanche", 15.50);
        menu[menuCount++] = new ItemMenu("Pizza", "Pizza", 30.00);
        menu[menuCount++] = new ItemMenu("Coca-Cola", "Bebida", 5.00);
        menu[menuCount++] = new ItemMenu("Suco natural", "Bebida", 7.00);
    }

    private static Cliente encontrarCliente(String nome) {
        for (int i = 0; i < clienteCount; i++) {
            if (clientes[i].getNome().equalsIgnoreCase(nome)) {
                return clientes[i];
            }
        }
        return null;
    }

    private static ItemMenu encontrarItem(String nome) {
        for (int i = 0; i < menuCount; i++) {
            if (menu[i].getNome().equalsIgnoreCase(nome)) {
                return menu[i];
            }
        }
        return null;
    }

    private static Pedido encontrarPedidoPorCliente(Cliente cliente) {
        for (int i = 0; i < pedidoCount; i++) {
            if (pedidos[i].getCliente().equals(cliente)) {
                return pedidos[i];
            }
        }
        return null;
    }
}
