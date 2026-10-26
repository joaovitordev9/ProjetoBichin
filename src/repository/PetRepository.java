package repository;

import model.Cliente;
import model.Pet;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class PetRepository {

    private final ArrayList<Pet> pets;

    //Construtor instancia a lista de pets
    public PetRepository() {
        pets = new ArrayList<>();
    }

    //Passa o pet pelo parâmetro da função e adiciona ele na lista se não existir
    public void adicionar(Pet pet) {
        if (buscarPorId(pet.getId()) == null) {
            pets.add(pet);
        }
    }

    //Retorna todos os pets da lista
    public ArrayList<Pet> listar() {
        return pets;
    }

    //Remove um pet da lista pelo ID
    public boolean remover(int id) {
        Pet pet = buscarPorId(id);

        if (pet != null) {
            pets.remove(pet);
            return true;
        }
        return false;
    }

    //Busca um pet em específico pelo ID
    public Pet buscarPorId(int id) {
        for (Pet pet : pets) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }

    //Carrega dados dos pets de um arquivo
    public void carregarDados(String caminho, ClienteRepository clienteRepository) {
        try {
            File arquivo = new File(caminho);
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();

                //Verifica se a linha é vazia e pula
                if (linha.isBlank()) {
                    continue;
                }

                //O -1 coloca uma string vazia caso o dado esteja vazio
                String[] dados = linha.split(";", -1);

                //Para caso ocorra erro na conversão dos IDs
                try {
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    String raca = dados[2];
                    String especie = dados[3];

                    int idCliente = Integer.parseInt(dados[4]);

                    //Busca o cliente associado ao pet pelo ID
                    Cliente cliente = clienteRepository.buscarPorId(idCliente);

                    //Converte a data de nascimento para LocalDate
                    LocalDate nascimento = LocalDate.parse(dados[5]);

                    //Cria um objeto Pet com os dados do arquivo
                    Pet pet = new Pet(
                            id, nome, raca, especie, cliente, nascimento
                    );

                    //Adiciona o pet à lista
                    //Somente se não existir outro pet com o mesmo ID
                    adicionar(pet);

                } catch (NumberFormatException e) {
                    //Ignora a linha caso algum ID seja inválido
                } catch (java.time.format.DateTimeParseException e) {
                    //Ignora a linha caso a data seja inválida
                }
            }

            //Fecha o arquivo após terminar a leitura
            leitor.close();

        } catch (FileNotFoundException e) {
            //Lança uma exceção caso o arquivo não seja encontrado
            throw new RuntimeException("Arquivo não encontrado!", e);
        }
    }
}
