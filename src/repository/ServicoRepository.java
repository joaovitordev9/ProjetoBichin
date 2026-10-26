package repository;

import model.Servico;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServicoRepository {

    private final ArrayList<Servico> servicos;

    public ServicoRepository() {
        servicos = new ArrayList<>();
    }

    public void adicionar(Servico servico) {
        if (buscarPorId(servico.getId()) == null) {
            servicos.add(servico);
        }
    }

    public ArrayList<Servico> listar() {
        return servicos;
    }

    public Servico buscarPorId(int id) {
        for (Servico servico : servicos) {
            if (servico.getId() == id) {
                return servico;
            }
        }
        return null;
    }

    public boolean remover(int id) {
        Servico servico = buscarPorId(id);
        if (servico != null) {
            servicos.remove(servico);
            return true;
        }
        return false;
    }


    //Carrega dados dos serviços de um arquivo
    public void carregarDados(String caminho) {
        try {
            File arquivo = new File(caminho);
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();

                //Verifica se a linha é vazia e pula
                if (linha.isBlank()) {
                    continue;
                }

                //O −1 coloca uma string vazia caso o dado esteja vazio
                String[] dados = linha.split(";", -1);

                //Para caso ocorra erro na conversão dos dados
                try {
                    int id = Integer.parseInt(dados[0]);
                    String descricao = dados[1];
                    double valor = Double.parseDouble(dados[2]);

                    //Cria um objeto Serviço com os dados do arquivo
                    Servico servico = new Servico(id, descricao, valor);

                    //Adiciona o serviço à lista se o ID não existir
                    adicionar(servico);

                } catch (NumberFormatException e) {
                    //Ignora a linha caso o ID ou valor seja inválido
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
