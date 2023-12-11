package com.ifpb.estagio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avaliacao_de_orientador")
public class AvaliacaoDoOrientador implements Serializable, Base {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String assiduidade;

    @Column(nullable = false)
    private String disciplina;

    @Column(nullable = false)
    private String sociabilidade;

    @Column(nullable = false)
    private String responsabilidade;

    @Column(nullable = false)
    private String iniciativaSensoCritico;
    																				
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssiduidade() {
		return assiduidade;
	}

	public void setAssiduidade(String assiduidade) {
		this.assiduidade = assiduidade;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getSociabilidade() {
		return sociabilidade;
	}

	public void setSociabilidade(String sociabilidade) {
		this.sociabilidade = sociabilidade;
	}

	public String getResponsabilidade() {
		return responsabilidade;
	}

	public void setResponsabilidade(String responsabilidade) {
		this.responsabilidade = responsabilidade;
	}

	public String getIniciativaSensoCritico() {
		return iniciativaSensoCritico;
	}

	public void setIniciativaSensoCritico(String iniciativaSensoCritico) {
		this.iniciativaSensoCritico = iniciativaSensoCritico;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
