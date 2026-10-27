package visao.cmd;

import controller.ClienteController;
import controller.PetController;
import model.Cliente;
import model.Pet;

import java.util.Scanner;

public class UiCmdPet {

    private Scanner teclado;

    private PetController controller;
    private ClienteController clienteController;

    public UiCmdPet(PetController controller, ClienteController clienteController) {

        teclado = new Scanner(System.in);

        this.controller = controller;
        this.clienteController = clienteController;
    }

    public void apagar(){

        int id;
        Pet pet;

        while (true) {
            System.out.println("Digite o identificador do pet que deseja apagar:");
            id = teclado.nextInt();

            pet = controller.buscar(id);

            if (pet != null) {
                break;
            }

            System.out.println("Pet não encontrado! Digite outro ID.");
        }

        System.out.println("Pet encontrado: " + pet.getNome());
        System.out.println("Deseja realmente apagar? (S/N)");

        char resposta = teclado.next().toUpperCase().charAt(0);

        if (resposta == 'S') {
            controller.remover(id);
            System.out.println("Pet apagado com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
