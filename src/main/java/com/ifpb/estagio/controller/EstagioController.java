package com.ifpb.estagio.controller;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.ifpb.estagio.model.Estagio;

public class EstagioController {
	private static Scanner scanner = new Scanner(System.in);

	public static void menuEstagio(EntityManagerFactory entityManagerFactory) {
		int opcao;

		do {
			System.out.println("Menu Estágio:");
			System.out.println("1. Criar Estágio");
			System.out.println("2. Listar Estágios");
			System.out.println("3. Modificar Estágio");
			System.out.println("4. Deletar Estágio");
			System.out.println("0. Voltar");

			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				criarEstagio(entityManagerFactory);
				break;

			case 2:
				listarEstagios(entityManagerFactory);
				break;

			case 3:
				modificarEstagio(entityManagerFactory);
				break;

			case 4:
				deletarEstagio(entityManagerFactory);
				break;

			case 0:
				System.out.println("Voltando para o menu principal...");
				break;

			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (opcao != 0);
	}

	private static void criarEstagio(EntityManagerFactory entityManagerFactory) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Estagio estagio = new Estagio();
		System.out.println("Digite o nome do estágio:");
		estagio.setNome(scanner.next());

		System.out.println("Digite o Status:");
		estagio.setStatus(scanner.next());

		System.out.println("Digite a carga horária total do estágio:");
		scanner.nextLine(); // Limpar o buffer antes de ler o próximo inteiro
		estagio.setCargaHorariaTotal(scanner.nextInt());

		System.out.println("Digite a data de início do estágio (AAAA-MM-DD):");
		String dataInicioStr = scanner.next();
		Date dataInicio = Date.valueOf(dataInicioStr);
		estagio.setDataInicio(dataInicio);

		System.out.println("Digite a data de término do estágio (AAAA-MM-DD):");
		String dataFimStr = scanner.next();
		Date dataFim = Date.valueOf(dataFimStr);
		estagio.setDataFim(dataFim);

		entityManager.persist(estagio);

		entityManager.getTransaction().commit();
		entityManager.close();

		System.out.println("Estágio criado com sucesso!");
	}

	private static void listarEstagios(EntityManagerFactory entityManagerFactory) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		String jpql = "select e from Estagio e";
		TypedQuery<Estagio> typedQuery = entityManager.createQuery(jpql, Estagio.class);
		List<Estagio> listaEstagios = typedQuery.getResultList();

		System.out.println("Lista de Estágios:");
		for (Estagio estagio : listaEstagios) {
			System.out.println("ID: " + estagio.getId() + ", Nome: " + estagio.getNome() + ", Status: "
					+ estagio.getStatus() + ", Inicio: " + estagio.getDataInicio() + ", Fim: " + estagio.getDataFim());
		}

		entityManager.close();
	}

	private static void modificarEstagio(EntityManagerFactory entityManagerFactory) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		System.out.println("Digite o ID do estágio que deseja modificar:");
		long idEstagio = scanner.nextLong();
		Estagio estagio = entityManager.find(Estagio.class, idEstagio);

		if (estagio != null) {
			System.out.println("Digite o novo nome para o estágio:");
			estagio.setNome(scanner.next());

			entityManager.merge(estagio);

			entityManager.getTransaction().commit();
			System.out.println("Estágio modificado com sucesso!");
		} else {
			System.out.println("Estágio não encontrado.");
			entityManager.getTransaction().rollback();
		}

		entityManager.close();
	}

	private static void deletarEstagio(EntityManagerFactory entityManagerFactory) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		System.out.println("Digite o ID do estágio que deseja deletar:");
		long idEstagio = scanner.nextLong();
		Estagio estagio = entityManager.find(Estagio.class, idEstagio);

		if (estagio != null) {
			entityManager.remove(estagio);

			entityManager.getTransaction().commit();
			System.out.println("Estágio deletado com sucesso!");
		} else {
			System.out.println("Estágio não encontrado.");
			entityManager.getTransaction().rollback();
		}

		entityManager.close();
	}
}
