package com.ifpb.estagio.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.ifpb.estagio.model.Empresa;

public class EmpresaController {
    private EntityManager entityManager;

    public EmpresaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Empresa buscarEmpresa(int idEmpresa) {
        return entityManager.find(Empresa.class, idEmpresa);
    }

    public Empresa criarEmpresa(String nome, String endereco, String setor) {
        Empresa empresa = new Empresa();
        empresa.setNome(nome);
        empresa.setEndereco(endereco);
        empresa.setSetor(setor);
        return empresa;
    }

    public List<Empresa> listarEmpresas() {
        String jpql = "select e from Empresa e";
        TypedQuery<Empresa> typedQuery = entityManager.createQuery(jpql, Empresa.class);
        List<Empresa> listarEmpresas = typedQuery.getResultList();

        for (Empresa empresa : listarEmpresas) {
            System.out.println(empresa.getNome());
        }
        return listarEmpresas;
    }

    public Empresa atualizarEmpresa(long id, String novoNome, String novoEndereco, String novoSetor) {
        Empresa empresa = entityManager.find(Empresa.class, id);

        if (empresa != null) {
            entityManager.getTransaction().begin();

            // Atualize os valores
            empresa.setNome(novoNome);
            empresa.setEndereco(novoEndereco);
            empresa.setSetor(novoSetor);

            entityManager.getTransaction().commit();
        }

        return empresa;
    }
}
