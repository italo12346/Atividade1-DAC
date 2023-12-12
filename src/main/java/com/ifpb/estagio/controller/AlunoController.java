package com.ifpb.estagio.controller;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.ifpb.estagio.model.Aluno;

import java.util.List;

public class AlunoController {
	private EntityManager entityManager;

	public AlunoController(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void deletarAluno(int idAluno) {
		entityManager.getTransaction().begin();
		entityManager.createQuery("delete from Aluno a where id = :idAluno").setParameter("idAluno", idAluno)
				.executeUpdate();
		entityManager.getTransaction().commit();
	}

	public void adicionarAluno(String nome, String curso) {
		entityManager.getTransaction().begin();

		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setCurso(curso);

		entityManager.persist(aluno);

		entityManager.getTransaction().commit();
	}

	public void atualizarAluno(Long idAluno, String novoNome) {
		entityManager.getTransaction().begin();
		entityManager.createQuery("update Aluno a set nome = :novoNome where a.id = :idAluno")
				.setParameter("novoNome", novoNome).setParameter("idAluno", idAluno).executeUpdate();
		entityManager.getTransaction().commit();
	}

	public Aluno buscarAluno(int idAluno) {
		return entityManager.find(Aluno.class, idAluno);
	}

	public void inserirAluno(Aluno aluno) {
		entityManager.getTransaction().begin();
		entityManager.persist(aluno);
		entityManager.getTransaction().commit();
	}

	public void deletarAluno(Aluno aluno) {
		entityManager.getTransaction().begin();
		entityManager.remove(aluno);
		entityManager.getTransaction().commit();
	}

	public void modificarAluno(Aluno aluno) {
		entityManager.getTransaction().begin();
		entityManager.merge(aluno);
		entityManager.getTransaction().commit();
	}

	public List<Aluno> listarAlunos() {
		String jpql = "select a from Aluno a";
		TypedQuery<Aluno> typedQuery = entityManager.createQuery(jpql, Aluno.class);
		List<Aluno> listaAluno = typedQuery.getResultList();

		for (Aluno aluno : listaAluno) {
			System.out.println(aluno.getNome());
		}
		return listaAluno;
	}
}
