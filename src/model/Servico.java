package model;

public class Servico {

    //Atributos do serviço
    private int id;
    private String descricao;
    private double valor;


    //Construtor Vazio que chama o construtor com uso do this
    public Servico() {
        this(0,null,0f);
    }

    //Construtor com os atributos do serviço
    public Servico(int id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String toString() {
        return "Id: " + id + " - Descricao: " + descricao + " - Valor: " + valor;
    }

    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
