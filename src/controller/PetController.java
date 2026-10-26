package controller;

import model.Cliente;
import model.Pet;
import repository.ClienteRepository;
import repository.PetRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class PetController {

    private final PetRepository repository;
    private final ClienteRepository clienteRepository;

    public PetController(PetRepository repository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    public ArrayList<Pet>  listar(){
        return repository.listar();
    }

    public boolean remover(int id){
        return repository.remover(id);
    }

    public void adicionar(int id, String nome, String raca, String especie, LocalDate nascimento, int id_cliente){
        Cliente cliente = clienteRepository.buscarPorId(id_cliente);

        if (cliente != null) {
            Pet pet = new Pet(id, nome, raca, especie, cliente, nascimento);
            repository.adicionar(pet);
        }

    }

    public Pet buscar(int id){
        return repository.buscarPorId(id);
    }

}
