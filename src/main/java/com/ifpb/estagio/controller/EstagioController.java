package com.ifpb.estagio.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.ifpb.estagio.model.Aluno;
import com.ifpb.estagio.model.Empresa;
import com.ifpb.estagio.model.Estagio;
import com.ifpb.estagio.model.Orientador;

public class EstagioController {
	private EntityManager entityManager;

	public EstagioController(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void adicionarEstagio(Estagio estagio) {
		entityManager.getTransaction().begin();
		entityManager.persist(estagio);
		entityManager.getTransaction().commit();
	}

	public Estagio criarEstagio(String nome, LocalDate dataInicio,
			LocalDate dataFim) {
		Estagio estagio = new Estagio();
		estagio.setNome(nome);
		estagio.setCargaHorariaTotal(50);
		estagio.setDataInicio(Date.valueOf(dataInicio));
		estagio.setDataFim(Date.valueOf(dataFim));
		return estagio;
	}

	public List<Estagio> listarEstagios() {
		String jpql = "SELECT e FROM Estagio e";
		TypedQuery<Estagio> query = entityManager.createQuery(jpql, Estagio.class);
		return query.getResultList();
	}
}
