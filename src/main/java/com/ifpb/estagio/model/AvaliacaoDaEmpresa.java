package com.ifpb.estagio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "avaliacao_da_empresa")
public class AvaliacaoDaEmpresa implements Serializable, Base {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rendimentoDeTrabalho;

    @Column(nullable = false)
    private String conhecimentos;

    @Column(nullable = false)
    private String cumprimentoDasTarefas;

    @Column(nullable = false)
    private String aprendizagem;

    @Column(nullable = false)
    private String desempenho;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRendimentoDeTrabalho() {
		return rendimentoDeTrabalho;
	}

	public void setRendimentoDeTrabalho(String rendimentoDeTrabalho) {
		this.rendimentoDeTrabalho = rendimentoDeTrabalho;
	}

	public String getConhecimentos() {
		return conhecimentos;
	}

	public void setConhecimentos(String conhecimentos) {
		this.conhecimentos = conhecimentos;
	}

	public String getCumprimentoDasTarefas() {
		return cumprimentoDasTarefas;
	}

	public void setCumprimentoDasTarefas(String cumprimentoDasTarefas) {
		this.cumprimentoDasTarefas = cumprimentoDasTarefas;
	}

	public String getAprendizagem() {
		return aprendizagem;
	}

	public void setAprendizagem(String aprendizagem) {
		this.aprendizagem = aprendizagem;
	}

	public String getDesempenho() {
		return desempenho;
	}

	public void setDesempenho(String desempenho) {
		this.desempenho = desempenho;
	}

    // Getters and setters
    
    
}
