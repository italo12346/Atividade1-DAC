package com.ifpb.estagio;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.ifpb.estagio.controller.AlunoController;
import com.ifpb.estagio.controller.EmpresaController;
import com.ifpb.estagio.controller.EstagioController;
import com.ifpb.estagio.controller.OrientadorController;
import com.ifpb.estagio.model.Empresa;
import com.ifpb.estagio.model.Estagio;
import com.ifpb.estagio.model.Orientador;

public class Main {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BancoPU");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void exibirLista(String titulo, List<?> lista) {
        StringBuilder mensagem = new StringBuilder(titulo + ":\n");
        for (Object item : lista) {
            mensagem.append(item.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Menu de Atualização");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        // Botoes de Listagem
        JButton listarAlunosButton = new JButton("Listar Alunos");
        JButton listarOrientadoresButton = new JButton("Listar Orientadores");
        JButton listarEmpresasButton = new JButton("Listar Empresas");
        JButton listarEstagiosButton = new JButton("Listar Estágios");

        // Botoes de Adição e Atualização
        JButton alunoButtonAdd = new JButton("Adicionar Aluno");
        JButton alunoButtonAt = new JButton("Atualizar Aluno");
        JButton orientadorButtonAdd = new JButton("Adicionar Orientador");
        JButton orientadorButtonAt = new JButton("Atualizar Orientador");
        JButton empresaButtonAdd = new JButton("Adicionar Empresa");

        // Botoes de Atualização
        JButton empresaButton = new JButton("Atualizar Empresa");
        JButton estagioButton = new JButton("Atualizar Estágio");
        
        // Botão para criar Estágio
        JButton criarEstagioButton = new JButton("Criar Estágio");

        // Botão de Voltar
        JButton voltarButton = new JButton("Voltar");

        panel.add(listarAlunosButton);
        panel.add(listarOrientadoresButton);
        panel.add(listarEmpresasButton);
        panel.add(listarEstagiosButton);
        panel.add(empresaButtonAdd);
        panel.add(alunoButtonAdd);
        panel.add(alunoButtonAt);
        panel.add(orientadorButtonAdd);
        panel.add(orientadorButtonAt);
        panel.add(empresaButton);
        panel.add(estagioButton);
        panel.add(criarEstagioButton);

        AlunoController alunoController = new AlunoController(entityManager);
        OrientadorController orientadorController = new OrientadorController(entityManager);
        EmpresaController empresaController = new EmpresaController(entityManager);
        EstagioController estagioController = new EstagioController(entityManager);

        alunoButtonAdd.addActionListener(createAlunoAddActionListener(alunoController));
        alunoButtonAt.addActionListener(createAlunoAtActionListener(alunoController));
        criarEstagioButton.addActionListener(createCriarEstagioActionListener(estagioController));
        orientadorButtonAdd.addActionListener(createOrientadorAddActionListener(orientadorController));
        orientadorButtonAt.addActionListener(createOrientadorAtActionListener(orientadorController));
        empresaButtonAdd.addActionListener(createEmpresaAddActionListener(empresaController));
        empresaButton.addActionListener(createEmpresaActionListener(empresaController));
        criarEstagioButton.addActionListener(createCriarEstagioActionListener(estagioController));
        listarAlunosButton.addActionListener(createListarAlunosActionListener(alunoController));
        listarOrientadoresButton.addActionListener(createListarOrientadoresActionListener(orientadorController));
        listarEmpresasButton.addActionListener(createListarEmpresasActionListener(empresaController));
        listarEstagiosButton.addActionListener(createListarEstagiosActionListener(estagioController));

        voltarButton.addActionListener(createVoltarActionListener(frame));

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private static ActionListener createAlunoAddActionListener(AlunoController alunoController) {
        return e -> alunoController.adicionarAluno("italo", "ads");
    }

    private static ActionListener createAlunoAtActionListener(AlunoController alunoController) {
        return e -> alunoController.atualizarAluno(1L, "Risalva");
    }

    private static ActionListener createOrientadorAddActionListener(OrientadorController orientadorController) {
        return e -> orientadorController.criarOrientador("Cristiano", "ADS");
    }

    private static ActionListener createOrientadorAtActionListener(OrientadorController orientadorController) {
        return e -> orientadorController.atualizarOrientador(1L, "paulo", "Engenharia");
    }

    private static ActionListener createEmpresaAddActionListener(EmpresaController empresaController) {
        return e -> {
            Empresa empresa = empresaController.criarEmpresa("Am3", "Cajazeiras", "Tecnologia");
            if (empresa != null) {
                JOptionPane.showMessageDialog(null, "Empresa criada: " + empresa.getNome());
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao criar a empresa.");
            }
        };
    }

    private static ActionListener createEmpresaActionListener(EmpresaController empresaController) {
        return e -> empresaController.atualizarEmpresa(1L, "Novo Nome", "Nova Localizacao", "Nova Area");
    }


    private static ActionListener createCriarEstagioActionListener(EstagioController estagioController) {
        return e -> {
            Estagio estagio = estagioController.criarEstagio("Nome do Novo Estágio", LocalDate.now(), LocalDate.now().plusDays(30));
            JOptionPane.showMessageDialog(null, "Estágio criado: " + estagio.getNome());
        };
    }

    private static ActionListener createListarAlunosActionListener(AlunoController alunoController) {
        return e -> alunoController.listarAlunos();
    }

    private static ActionListener createListarOrientadoresActionListener(OrientadorController orientadorController) {
        return e -> {
            List<Orientador> orientadores = orientadorController.listarOrientadores();
            exibirLista("Orientadores", orientadores);
        };
    }

    private static ActionListener createListarEmpresasActionListener(EmpresaController empresaController) {
        return e -> empresaController.listarEmpresas();
    }

    private static ActionListener createListarEstagiosActionListener(EstagioController estagioController) {
        return e -> estagioController.listarEstagios();
    }

    private static ActionListener createVoltarActionListener(JFrame frame) {
        return e -> {
            entityManager.close();
            entityManagerFactory.close();
            frame.dispose();
        };
    }
}
