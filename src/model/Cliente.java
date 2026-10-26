package model;

public class Cliente {

    //Atributos do cliente
    private int id;
    private String nome;
    private String telefone;


    //Construtor Vazio que chama o construtor com uso do this
    public Cliente() {
        this(0, null, null);
    }

    //Construtor com todos os atributos do cliente
    public Cliente(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }



    //Substitui o toString padrão
    public String toString() {
        return "Id: " + id + " - Nome: " + nome + " - Telefone: " + telefone;
    }

    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
