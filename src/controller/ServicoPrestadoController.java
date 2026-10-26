package controller;

import model.Cliente;
import model.Pet;
import model.Servico;
import model.ServicoPrestado;
import repository.PetRepository;
import repository.ServicoPrestadoRepository;
import repository.ServicoRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class ServicoPrestadoController {

    private final PetRepository  petRepository;
    private final ServicoRepository servicoRepository;
    private final ServicoPrestadoRepository repository;

    public ServicoPrestadoController(PetRepository petRepository, ServicoRepository servicoRepository, ServicoPrestadoRepository repository) {
        this.petRepository = petRepository;
        this.servicoRepository = servicoRepository;
        this.repository = repository;
    }

    public double maiorValorPorCliente(Cliente cliente) {
        return repository.maiorValorPorCliente(cliente);
    }

    public boolean removerServicoPrestado(int id) {
        return repository.remover(id);
    }

    public ServicoPrestado buscar(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nome) {
        return repository.buscarBichosPorNomeDono(nome);
    }

    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return repository.buscarPorPeriodo(inicio, fim);
    }

    public void adicionar(int id, int id_servico, int id_pet, double valor, LocalDate data){
        Servico servico = servicoRepository.buscarPorId(id_servico);
        Pet pet = petRepository.buscarPorId(id_pet);

        if (servico != null && pet != null) {
            ServicoPrestado servicoPrestado = new ServicoPrestado(id,servico,data,valor,pet);
            repository.adicionar(servicoPrestado);
        }
    }

    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente){
        return repository.buscarPorCliente(cliente);
    }

    public ArrayList<ServicoPrestado> listar(){
        return repository.listar();
    }
}
