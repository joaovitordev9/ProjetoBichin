package model;

import java.time.LocalDate;
import java.util.Locale;

public class Pet {

    //Atributos do pet
    private int id;
    private String nome;
    private String raca;
    private String especie;
    private Cliente cliente;
    private LocalDate nascimento;

    //Construtor Vazio que chama o construtor com uso do this
    public Pet() {
        this(0, null, null, null, null, null);
    }

    //Construtor com os atributos do pet
    public Pet(int id, String nome, String raca, String especie, Cliente cliente, LocalDate nascimento) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.especie = especie;
        this.cliente = cliente;
        this.nascimento = nascimento;
    }


    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
}
