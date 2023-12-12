package com.ifpb.estagio.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.ifpb.estagio.model.Aluno;
import com.ifpb.estagio.model.Orientador;

public class OrientadorController {
	private EntityManager entityManager;

	public OrientadorController(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Orientador buscarOrientador(long l) {
		return entityManager.find(Orientador.class, l);
	}

	public Orientador criarOrientador(String nome, String departamento) {
		entityManager.getTransaction().begin();
		Orientador orientador = new Orientador();
		orientador.setNome(nome);
		orientador.setDepartamento(departamento);
		entityManager.persist(orientador);
		entityManager.getTransaction().commit();
		return orientador;
	}
	public void atualizarOrientador(Long idOrientador, String novoNome , String novoDepartamento) {
		entityManager.getTransaction().begin();
		entityManager.createQuery("update Orientador a set nome = :novoNome where a.id = :idOrientador")
				.setParameter("novoNome", novoNome).setParameter("idOrientador", idOrientador).executeUpdate();
		entityManager.getTransaction().commit();
	}

	public List<Orientador> listarOrientadores() {
			String jpql = "select o from Orientador o";
			TypedQuery<Orientador> typedQuery = entityManager.createQuery(jpql, Orientador.class);
			List<Orientador> listaOrientadores = typedQuery.getResultList();

			for (Orientador orientador : listaOrientadores) {
				System.out.println(orientador.getNome());
			}
			return listaOrientadores;
	}
}
