package programs;

import java.util.Scanner;

class Cliente {
    private String nome;
    private String endereco;
    private String telefone;

    public Cliente(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    
    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }


    public void visualizarCliente() {
        System.out.println("Nome: " + nome + " | Endereço: " 
        					+ endereco + " | Telefone: " + telefone);
    }


    public static Cliente registrarCliente(Scanner sc) {
        System.out.print("Informe o nome do cliente: ");
        String nome = sc.nextLine();

        System.out.print("Informe o endereço do cliente: ");
        String endereco = sc.nextLine();

        System.out.print("Informe o telefone do cliente: ");
        String telefone = sc.nextLine();

        Cliente cliente = new Cliente(nome, endereco, telefone);
        System.out.println("Cliente registrado com sucesso!");

        return cliente;
    }
}
