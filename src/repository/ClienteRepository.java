package repository;

import model.Cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteRepository {


    private final ArrayList<Cliente> clientes;

    //Construtor estancia a lista de clientes
    public ClienteRepository() {
        clientes = new ArrayList<>();
    }

    //Passa o cliente pelo parâmetro da função e adiciona ele na lista se não existir
    public void adicionar(Cliente c) {
       if (buscarPorId(c.getId()) == null) {
           clientes.add(c);
       }
    }

    //Retorna todos os clientes da lista
    public ArrayList<Cliente> listar(){
        return clientes;
    }

    //Busca um cliente em específico
    public Cliente buscarPorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    //Remove um cliente da lista
    public boolean remover(int id) {
        Cliente cliente = buscarPorId(id);

        if (cliente != null) {
            clientes.remove(cliente);
            return true;
        }

        return false;
    }

    //Carrega dados de um arquivo
    public void carregarDados(String caminho) {
        try {
            File arquivo = new File(caminho);
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();

                //Verifica se a linha é vazia e pula
                if (linha.isBlank()) {
                    continue;
                }

                //O −1 coloca uma string vazia caso o dado esteja vazio
                String[] dados = linha.split(";", -1);

                //Para caso ocorra erro na conversão
                try {
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    String telefone = dados[2];

                    Cliente cliente = new Cliente(id, nome, telefone);
                    adicionar(cliente);

                } catch (NumberFormatException e) {
                    //Ignora a linha se o ID for inválido
                }
            }
            leitor.close();

        }catch (FileNotFoundException e){
            throw new RuntimeException("Arquivo não encontrado!", e);
        }
    }

}
