package com.ifpb.estagio.controller;

import com.ifpb.estagio.model.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class AlunoController {
    private static Scanner scanner = new Scanner(System.in);

    public static void menuAluno(EntityManagerFactory entityManagerFactory) {
        int opcao;

        do {
            System.out.println("Menu Aluno:");
            System.out.println("1. Criar Aluno");
            System.out.println("2. Listar Alunos");
            System.out.println("3. Modificar Aluno");
            System.out.println("4. Deletar Aluno");
            System.out.println("0. Voltar");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    criarAluno(entityManagerFactory);
                    break;

                case 2:
                    listarAlunos(entityManagerFactory);
                    break;

                case 3:
                    modificarAluno(entityManagerFactory);
                    break;

                case 4:
                    deletarAluno(entityManagerFactory);
                    break;

                case 0:
                    System.out.println("Voltando para o menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void criarAluno(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Aluno aluno = new Aluno();
        System.out.println("Digite o nome do aluno:");
        aluno.setNome(scanner.next());

        entityManager.persist(aluno);

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println("Aluno criado com sucesso!");
    }

    private static void listarAlunos(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String jpql = "select a from Aluno a";
        TypedQuery<Aluno> typedQuery = entityManager.createQuery(jpql, Aluno.class);
        List<Aluno> listaAluno = typedQuery.getResultList();

        System.out.println("Lista de Alunos:");
        for (Aluno aluno : listaAluno) {
            System.out.println("ID: " + aluno.getId() + ", Nome: " + aluno.getNome());
        }

        entityManager.close();
    }

    private static void modificarAluno(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Digite o ID do aluno que deseja modificar:");
        long idAluno = scanner.nextLong();
        Aluno aluno = entityManager.find(Aluno.class, idAluno);

        if (aluno != null) {
            System.out.println("Digite o novo nome para o aluno:");
            aluno.setNome(scanner.next());

            entityManager.merge(aluno);

            entityManager.getTransaction().commit();
            System.out.println("Aluno modificado com sucesso!");
        } else {
            System.out.println("Aluno não encontrado.");
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }

    private static void deletarAluno(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Digite o ID do aluno que deseja deletar:");
        long idAluno = scanner.nextLong();
        Aluno aluno = entityManager.find(Aluno.class, idAluno);

        if (aluno != null) {
            entityManager.remove(aluno);

            entityManager.getTransaction().commit();
            System.out.println("Aluno deletado com sucesso!");
        } else {
            System.out.println("Aluno não encontrado.");
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }
}
