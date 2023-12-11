package com.ifpb.estagio;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.ifpb.estagio.model.Aluno;
import com.ifpb.estagio.model.Empresa;
import com.ifpb.estagio.model.Estagio;
import com.ifpb.estagio.model.Orientador;

public class PovoarBanco {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BancoPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            // Povoamento de Orientadores
            Orientador orientador1 = criarOrientador("João", "TI");
            Orientador orientador2 = criarOrientador("Maria", "Engenharia");
            entityManager.persist(orientador1);
            entityManager.persist(orientador2);

            // Povoamento de Empresas
            Empresa empresa1 = criarEmpresa("Loopis", "Rua 1, Número 1", "Tecnologia");
            Empresa empresa2 = criarEmpresa("TechCorp", "Rua 2, Número 2", "Software");
            entityManager.persist(empresa1);
            entityManager.persist(empresa2);

            // Povoamento de Alunos
            Aluno aluno1 = criarAluno("Lucas","ADS", orientador1, empresa1);
            Aluno aluno2 = criarAluno("Maria","Automação", orientador2, empresa2);
            entityManager.persist(aluno1);
            entityManager.persist(aluno2);

            // Povoamento de Estágios
            LocalDate dataInicioEstagio1 = LocalDate.of(2023, 1, 1); // Data de início desejada
            LocalDate dataFimEstagio1 = LocalDate.of(2023, 6, 30); // Data de término desejada
            Estagio estagio1 = criarEstagio("Assistente", orientador1, empresa1, aluno1, dataInicioEstagio1, dataFimEstagio1);

            LocalDate dataInicioEstagio2 = LocalDate.of(2023, 7, 1); // Data de início desejada
            LocalDate dataFimEstagio2 = LocalDate.of(2023, 12, 31); // Data de término desejada
            Estagio estagio2 = criarEstagio("Desenvolvedor", orientador2, empresa2, aluno2, dataInicioEstagio2, dataFimEstagio2);

            entityManager.persist(estagio1);
            entityManager.persist(estagio2);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    private static Orientador criarOrientador(String nome, String departamento) {
        Orientador orientador = new Orientador();
        orientador.setNome(nome);
        orientador.setDepartamento(departamento);
        return orientador;
    }

    private static Empresa criarEmpresa(String nome, String endereco, String setor) {
        Empresa empresa = new Empresa();
        empresa.setNome(nome);
        empresa.setEndereco(endereco);
        empresa.setSetor(setor);
        return empresa;
    }

    private static Aluno criarAluno(String nome, String curso, Orientador orientador, Empresa empresa) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setCurso(curso);
        aluno.setOrientador(orientador);
        aluno.setEmpresa(empresa);
        return aluno;
    }

    private static Estagio criarEstagio(String nome, Orientador orientador, Empresa empresa, Aluno aluno, LocalDate dataInicio, LocalDate dataFim) {
        Estagio estagio = new Estagio();
        estagio.setNome(nome);
        estagio.setCargaHorariaTotal(50);
        estagio.setDataInicio(Date.valueOf(dataInicio));
        estagio.setDataFim(Date.valueOf(dataFim));
        estagio.setEmpresa(empresa);
        estagio.setAluno(aluno);
        estagio.setOrientador(orientador);
        return estagio;
    }
}
