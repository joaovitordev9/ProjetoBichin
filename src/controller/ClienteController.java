package controller;

import model.Cliente;
import repository.ClienteRepository;

import java.util.ArrayList;

public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente buscar(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<Cliente> listar() {
        return repository.listar();
    }

    public void adicionar(int id, String nome, String telefone) {

        Cliente cliente = new Cliente(id, nome, telefone);

        repository.adicionar(cliente);
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }

}
