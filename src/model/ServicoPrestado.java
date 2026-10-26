package model;

import java.time.LocalDate;

public class ServicoPrestado {

    //Atributos do serviço Prestado
    private int id;
    private Servico servico;
    private LocalDate data;
    private double valorCobrado;
    private Pet pet;

    //Construtor Vazio que chama o construtor com uso do this
    public ServicoPrestado() {
        this(0,null,null,0f,null);
    }

    //Construtor com os atributos do Serviço Prestado
    public ServicoPrestado(int id, Servico servico, LocalDate data, double valorCobrado, Pet pet) {
        this.id = id;
        this.servico = servico;
        this.data = data;
        this.valorCobrado = valorCobrado;
        this.pet = pet;
    }

    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
