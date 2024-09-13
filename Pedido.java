package programs;

class Pedido {
    private ItemMenu[] itens;     
    private Cliente cliente;   
    private double total;         
    private String status;       
    private int itemCount;       
    
    public Pedido(Cliente cliente, int tamanhoMaximoItens) {
        this.cliente = cliente;
        this.itens = new ItemMenu[tamanhoMaximoItens];
        this.total = 0.0;
        this.status = "Em preparação";  
        this.itemCount = 0;             
    }

   
    public void adicionarItem(ItemMenu item) {
        if (itemCount < itens.length) {
            itens[itemCount] = item;  
            itemCount++;
            System.out.println("Item " + item.getNome() + " adicionado ao pedido.");
        } else {
            System.out.println("Não é possível adicionar mais itens ao pedido.");
        }
    }

   
    public void calcularTotal() {
        double soma = 0;
        for (int i = 0; i < itemCount; i++) {
            soma += itens[i].calcularPreco(); 
        }
        this.total = soma;
    }

    
    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
    }


    public void exibirPedido() {
        System.out.println("\n--- Pedido ---");
        System.out.println("Cliente: " + cliente.getNome()); 
        System.out.println("Itens do Pedido:");
        for (int i = 0; i < itemCount; i++) {
            System.out.println("- " + itens[i].getNome() + " | Preço: R$ " 
            					+ itens[i].calcularPreco());
        }
        System.out.println("Total: R$ " + total);  
        System.out.println("Status: " + status);  
        }

    
    public Cliente getCliente() {
        return cliente;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }
}

