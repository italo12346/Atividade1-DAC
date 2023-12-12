package com.ifpb.estagio.controller;

import com.ifpb.estagio.model.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class EmpresaController {
    private static Scanner scanner = new Scanner(System.in);

    public static void menuEmpresa(EntityManagerFactory entityManagerFactory) {
        int opcao;

        do {
            System.out.println("Menu Empresa:");
            System.out.println("1. Criar Empresa");
            System.out.println("2. Listar Empresas");
            System.out.println("3. Modificar Empresa");
            System.out.println("4. Deletar Empresa");
            System.out.println("0. Voltar");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    criarEmpresa(entityManagerFactory);
                    break;
                case 2:
                    listarEmpresas(entityManagerFactory);
                    break;
                case 3:
                    modificarEmpresa(entityManagerFactory);
                    break;
                case 4:
                    deletarEmpresa(entityManagerFactory);
                    break;
                case 0:
                    System.out.println("Voltando para o menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void criarEmpresa(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Empresa empresa = new Empresa();
        System.out.println("Digite o nome da empresa:");
        empresa.setNome(scanner.next());

        System.out.println("Digite o endereço da empresa:");
        empresa.setEndereco(scanner.next());

        System.out.println("Digite o setor da empresa:");
        empresa.setSetor(scanner.next());

        entityManager.persist(empresa);

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println("Empresa criada com sucesso!");
    }

    private static void listarEmpresas(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String jpql = "select e from Empresa e";
        TypedQuery<Empresa> typedQuery = entityManager.createQuery(jpql, Empresa.class);
        List<Empresa> listaEmpresas = typedQuery.getResultList();

        System.out.println("Lista de Empresas:");
        for (Empresa empresa : listaEmpresas) {
            System.out.println("ID: " + empresa.getId() + ", Nome: " + empresa.getNome()+ ", Endereço: " + empresa.getEndereco());
        }

        entityManager.close();
    }

    private static void modificarEmpresa(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Digite o ID da empresa que deseja modificar:");
        long idEmpresa = scanner.nextLong();
        Empresa empresa = entityManager.find(Empresa.class, idEmpresa);

        if (empresa != null) {
            System.out.println("Digite o novo nome para a empresa:");
            empresa.setNome(scanner.next());

            System.out.println("Digite o novo endereço para a empresa:");
            empresa.setEndereco(scanner.next());

            System.out.println("Digite o novo setor para a empresa:");
            empresa.setSetor(scanner.next());

            entityManager.merge(empresa);

            entityManager.getTransaction().commit();
            System.out.println("Empresa modificada com sucesso!");
        } else {
            System.out.println("Empresa não encontrada.");
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }

    private static void deletarEmpresa(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Digite o ID da empresa que deseja deletar:");
        long idEmpresa = scanner.nextLong();
        Empresa empresa = entityManager.find(Empresa.class, idEmpresa);

        if (empresa != null) {
            entityManager.remove(empresa);

            entityManager.getTransaction().commit();
            System.out.println("Empresa deletada com sucesso!");
        } else {
            System.out.println("Empresa não encontrada.");
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }
}
