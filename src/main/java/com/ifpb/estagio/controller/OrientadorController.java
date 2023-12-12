package com.ifpb.estagio.controller;

import com.ifpb.estagio.model.Orientador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class OrientadorController {
    private static Scanner scanner = new Scanner(System.in);

    public static void menuOrientador(EntityManagerFactory entityManagerFactory) {
        int opcao;

        do {
            System.out.println("Menu Orientador:");
            System.out.println("1. Criar Orientador");
            System.out.println("2. Listar Orientadores");
            System.out.println("3. Modificar Orientador");
            System.out.println("4. Deletar Orientador");
            System.out.println("0. Voltar");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    criarOrientador(entityManagerFactory);
                    break;
                case 2:
                    listarOrientadores(entityManagerFactory);
                    break;
                case 3:
                    modificarOrientador(entityManagerFactory);
                    break;
                case 4:
                    deletarOrientador(entityManagerFactory);
                    break;
                case 0:
                    System.out.println("Voltando para o menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void criarOrientador(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Orientador orientador = new Orientador();
        System.out.println("Digite o nome do orientador:");
        orientador.setNome(scanner.next());

        System.out.println("Digite o departamento do orientador:");
        orientador.setDepartamento(scanner.next());

        entityManager.persist(orientador);

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println("Orientador criado com sucesso!");
    }

    private static void listarOrientadores(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String jpql = "select o from Orientador o";
        TypedQuery<Orientador> typedQuery = entityManager.createQuery(jpql, Orientador.class);
        List<Orientador> listaOrientadores = typedQuery.getResultList();

        System.out.println("Lista de Orientadores:");
        for (Orientador orientador : listaOrientadores) {
            System.out.println("ID: " + orientador.getId() + ", Nome: " + orientador.getNome());
        }

        entityManager.close();
    }

    private static void modificarOrientador(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Digite o ID do orientador que deseja modificar:");
        long idOrientador = scanner.nextLong();
        Orientador orientador = entityManager.find(Orientador.class, idOrientador);

        if (orientador != null) {
            System.out.println("Digite o novo nome para o orientador:");
            orientador.setNome(scanner.next());

            System.out.println("Digite o novo departamento para o orientador:");
            orientador.setDepartamento(scanner.next());

            entityManager.merge(orientador);

            entityManager.getTransaction().commit();
            System.out.println("Orientador modificado com sucesso!");
        } else {
            System.out.println("Orientador não encontrado.");
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }

    private static void deletarOrientador(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Digite o ID do orientador que deseja deletar:");
        long idOrientador = scanner.nextLong();
        Orientador orientador = entityManager.find(Orientador.class, idOrientador);

        if (orientador != null) {
            entityManager.remove(orientador);

            entityManager.getTransaction().commit();
            System.out.println("Orientador deletado com sucesso!");
        } else {
            System.out.println("Orientador não encontrado.");
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }
}
