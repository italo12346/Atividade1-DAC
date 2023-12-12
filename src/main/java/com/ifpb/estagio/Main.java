package com.ifpb.estagio;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.ifpb.estagio.model.Aluno;

public class Main {
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BancoPU");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Criar Aluno");
            System.out.println("2. Listar Alunos");
            System.out.println("3. Modificar Aluno");
            System.out.println("4. Deletar Aluno");
            System.out.println("0. Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    criarAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    modificarAluno();
                    break;
                case 4:
                    deletarAluno();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
        entityManager.close();
        entityManagerFactory.close();
    }

    private static void criarAluno() {
        entityManager.getTransaction().begin();

        Aluno aluno = new Aluno();
        System.out.println("Digite o nome do aluno:");
        aluno.setNome(scanner.next());

        entityManager.persist(aluno);

        entityManager.getTransaction().commit();
        System.out.println("Aluno criado com sucesso!");
    }

    private static void listarAlunos() {
        String jpql = "select a from Aluno a";
        TypedQuery<Aluno> typedQuery = entityManager.createQuery(jpql, Aluno.class);
        List<Aluno> listaAluno = typedQuery.getResultList();

        System.out.println("Lista de Alunos:");
        for (Aluno aluno : listaAluno) {
            System.out.println("ID: " + aluno.getId() + ", Nome: " + aluno.getNome());
        }
    }

    private static void modificarAluno() {
        entityManager.getTransaction().begin();

        System.out.println("Digite o ID do aluno que deseja modificar:");
        long idAluno = scanner.nextLong(); // Alteração aqui para nextLong
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
    }

    private static void deletarAluno() {
        entityManager.getTransaction().begin();

        System.out.println("Digite o ID do aluno que deseja deletar:");
        long idAluno = scanner.nextLong(); // Alteração aqui para nextLong
        Aluno aluno = entityManager.find(Aluno.class, idAluno);

        if (aluno != null) {
            entityManager.remove(aluno);

            entityManager.getTransaction().commit();
            System.out.println("Aluno deletado com sucesso!");
        } else {
            System.out.println("Aluno não encontrado.");
            entityManager.getTransaction().rollback();
        }
    }

}
