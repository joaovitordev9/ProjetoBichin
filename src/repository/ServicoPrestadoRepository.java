package repository;

import model.Cliente;
import model.Pet;
import model.Servico;
import model.ServicoPrestado;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ServicoPrestadoRepository {

    private final ArrayList<ServicoPrestado> ServicosPrestado;

    public ServicoPrestadoRepository() {
        ServicosPrestado = new ArrayList<>();
    }

    public ArrayList<ServicoPrestado> listar() {
        return ServicosPrestado;
    }

    public void adicionar(ServicoPrestado servicoPrestado) {
        if (buscarPorId(servicoPrestado.getId()) == null) {
            ServicosPrestado.add(servicoPrestado);
        }
    }

    //Busca serviços realizados em determinado período
    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {

        ArrayList<ServicoPrestado> servicosNoPeriodo = new ArrayList<>();

        for (ServicoPrestado servicoPrestado : ServicosPrestado) {
            //Busca serviços dentro do período informado, incluindo as datas inicial e final
            if (!servicoPrestado.getData().isBefore(dataInicial) && !servicoPrestado.getData().isAfter(dataFinal)) {
                servicosNoPeriodo.add(servicoPrestado);
            }
        }

        return servicosNoPeriodo;
    }

    public boolean remover(int id) {

        ServicoPrestado servicoPrestado = buscarPorId(id);

        if (servicoPrestado != null) {
            ServicosPrestado.remove(servicoPrestado);
            return true;
        }

        return false;
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nome) {

        //Cria uma lista para armazenar os pets encontrados
        ArrayList<Pet> petsDoCliente = new ArrayList<>();

        for (ServicoPrestado servicoPrestado : ServicosPrestado) {
            Pet pet = servicoPrestado.getPet();
            //Verifica o nome do dono e evita pets repetidos
            if (pet.getCliente().getNome().equals(nome) && !petsDoCliente.contains(pet)) {
                petsDoCliente.add(pet);
            }
        }

        return petsDoCliente;
    }

    //Busca um serviço prestado pelo ID
    public ServicoPrestado buscarPorId(int id) {
        for (ServicoPrestado servicoPrestado : ServicosPrestado) {
            if (servicoPrestado.getId() == id) {
                return servicoPrestado;
            }
        }
        //Retorna null caso não encontre o serviço
        return null;
    }

    //Busca os serviços prestados a um cliente
    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente) {

        ArrayList<ServicoPrestado> servicosDoCliente = new ArrayList<>();

        for (ServicoPrestado servicoPrestado : ServicosPrestado) {
            Pet pet = servicoPrestado.getPet();
            if (pet.getCliente().equals(cliente)) {
                servicosDoCliente.add(servicoPrestado);
            }
        }

        return servicosDoCliente;
    }

    //Retorna o maior valor cobrado em serviços de um cliente
    public double maiorValorPorCliente(Cliente cliente) {

        double maior = 0;

        for (ServicoPrestado servicoPrestado : ServicosPrestado) {
            Pet pet = servicoPrestado.getPet();
            if (pet.getCliente().equals(cliente)) {
                if (servicoPrestado.getValorCobrado() > maior) {
                    maior = servicoPrestado.getValorCobrado();
                }
            }
        }

        return maior;
    }

    //Carrega dados dos serviços prestados de um arquivo
    public void carregarDados(String caminho, PetRepository petRepository, ServicoRepository servicoRepository) {

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

                //Para caso ocorra erro na conversão dos dados
                try {
                    int id = Integer.parseInt(dados[0]);
                    int idServico = Integer.parseInt(dados[1]);

                    //Converte a data para LocalDate
                    LocalDate data = LocalDate.parse(dados[2]);

                    double valorCobrado = Double.parseDouble(dados[3]);
                    int idPet = Integer.parseInt(dados[4]);

                    //Busca o serviço associado pelo ID
                    Servico servico = servicoRepository.buscarPorId(idServico);

                    //Busca o pet associado pelo ID
                    Pet pet = petRepository.buscarPorId(idPet);

                    //Verifica se o serviço e o pet existem
                    if (servico == null || pet == null) {
                        continue;
                    }

                    //Cria um objeto ServicoPrestado com os dados do arquivo
                    ServicoPrestado servicoPrestado = new ServicoPrestado(
                            id, servico, data, valorCobrado, pet
                    );

                    //Adiciona o serviço prestado à lista
                    adicionar(servicoPrestado);

                } catch (NumberFormatException e) {
                    //Ignora a linha caso algum número seja inválido
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

