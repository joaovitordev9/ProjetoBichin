package controller;

import model.Servico;
import repository.ServicoRepository;

import java.util.ArrayList;

public class ServicoController {

    private final ServicoRepository repository;

    public ServicoController(ServicoRepository repository) {
        this.repository = repository;
    }

    public ArrayList<Servico> listar() {
        return repository.listar();
    }

    public Servico buscar(int id){
        return repository.buscarPorId(id);
    }

    public void adicionar(int id, String nome, double valor){
        Servico servico = new Servico(id, nome, valor);
        repository.adicionar(servico);
    }

    public boolean remover(int id){
        return repository.remover(id);
    }
}
