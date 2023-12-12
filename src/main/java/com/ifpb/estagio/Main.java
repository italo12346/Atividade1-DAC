package com.ifpb.estagio;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ifpb.estagio.controller.AlunoController;
import com.ifpb.estagio.controller.EmpresaController;
import com.ifpb.estagio.controller.EstagioController;
import com.ifpb.estagio.controller.OrientadorController;

public class Main {
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BancoPU");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Aluno");
            System.out.println("2. Orientador");
            System.out.println("3. Empresa");
            System.out.println("4. Estágio");
            System.out.println("0. Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    AlunoController.menuAluno(entityManagerFactory);
                    break;

                case 2:
                    OrientadorController.menuOrientador(entityManagerFactory);
                    break;

                case 3:
                    EmpresaController.menuEmpresa(entityManagerFactory);
                    break;

                case 4:
                    EstagioController.menuEstagio(entityManagerFactory);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
        entityManagerFactory.close();
    }
}
