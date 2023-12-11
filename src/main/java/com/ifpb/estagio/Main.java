package com.ifpb.estagio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ifpb.estagio.model.Aluno;
import com.ifpb.estagio.model.Empresa;
import com.ifpb.estagio.model.Estagio;
import com.ifpb.estagio.model.Orientador;

public class Main {
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BancoPU");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {
        entityManager.getTransaction().begin();

        try {
            // Adicionando aluno
            Aluno aluno = new Aluno();
            aluno.setNome("luiza");
            aluno.setCurso("ADS");

            // Adicionando orientador
            Orientador orientador = new Orientador();
            orientador.setNome("Daladie");
            orientador.setDepartamento("ADS");
            entityManager.persist(orientador);

            // Adicionando empresa
            Empresa empresa = new Empresa();
            empresa.setNome("Ads LTDA");
            empresa.setEndereco("Rua Tal, Número Tal");
            entityManager.persist(empresa);
            


            // Buscando um orientador existente pelo ID (suponha que o ID do orientador desejado seja 1)
            Orientador orientadorExistente = entityManager.find(Orientador.class, 1);

            // Verificando se o orientador existe antes de associá-lo ao aluno
            if (orientadorExistente != null) {
                aluno.setOrientador(orientadorExistente);

                // Buscando uma empresa existente pelo ID (suponha que o ID da empresa desejada seja 1)
                Empresa empresaExistente = entityManager.find(Empresa.class, 1);

                // Verificando se a empresa existe antes de associá-la ao aluno
                if (empresaExistente != null) {
                    aluno.setEmpresa(empresaExistente);
                    entityManager.persist(aluno);

                    // Persistindo a empresa (caso não tenha sido persistida antes)
                    if (!entityManager.contains(empresaExistente)) {
                        entityManager.persist(empresaExistente);
                    }

                    // Buscando um estágio existente pelo ID (suponha que o ID do estágio desejado seja 1)
                    Estagio estagioExistente = entityManager.find(Estagio.class, 1);

                    // Verificando se o estágio existe antes de associá-lo ao aluno
                    if (estagioExistente != null) {
                        aluno.setEstagio(estagioExistente);
                        entityManager.merge(aluno);
                    } else {
                        System.out.println("Estágio não encontrado.");
                    }

                } else {
                    System.out.println("Empresa não encontrada.");
                }
            } else {
                System.out.println("Orientador não encontrado.");
            }

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
}
