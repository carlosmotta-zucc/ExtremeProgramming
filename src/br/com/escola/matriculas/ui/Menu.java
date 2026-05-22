package br.com.escola.matriculas.ui;

import java.util.List;
import java.util.Scanner;

import br.com.escola.matriculas.modelo.Aluno;
import br.com.escola.matriculas.modelo.Curso;
import br.com.escola.matriculas.modelo.Matricula;
import br.com.escola.matriculas.servico.Sistema;

public class Menu {

    private final Sistema sistema;
    private final Scanner scanner;

    public Menu(Sistema sistema) {
        this.sistema = sistema;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            limparTela();
            imprimirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");
            limparTela();
            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    pausar();
                    break;
                case 2:
                    cadastrarCurso();
                    pausar();
                    break;
                case 3:
                    matricular();
                    pausar();
                    break;
                case 4:
                    listar();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    pausar();
            }
        } while (opcao != 0);
    }

    private void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pausar() {
        System.out.println();
        System.out.print("Pressione Enter para continuar...");
        scanner.nextLine();
    }

    private void imprimirMenu() {
        System.out.println("==========================================");
        System.out.println("  SISTEMA DE MATRICULAS - ESCOLA DE IDIOMAS");
        System.out.println("==========================================");
        System.out.println("  1) Cadastrar aluno");
        System.out.println("  2) Cadastrar curso");
        System.out.println("  3) Matricular aluno em curso");
        System.out.println("  4) Listar");
        System.out.println("  0) Sair");
        System.out.println("------------------------------------------");
    }

    private void cadastrarAluno() {
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("E-mail do aluno: ");
        String email = scanner.nextLine();
        try {
            Aluno aluno = sistema.cadastrarAluno(nome, email);
            System.out.println("Aluno cadastrado com sucesso. ID = " + aluno.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void cadastrarCurso() {
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();
        int vagas = lerInteiro("Quantidade de vagas: ");
        try {
            Curso curso = sistema.cadastrarCurso(nome, vagas);
            System.out.println("Curso cadastrado com sucesso. ID = " + curso.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void matricular() {
        int alunoId = lerInteiro("ID do aluno: ");
        int cursoId = lerInteiro("ID do curso: ");
        try {
            Matricula matricula = sistema.matricular(alunoId, cursoId);
            System.out.println("Matricula registrada. ID = " + matricula.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("--- LISTAR ---");
        System.out.println("  1) Todos os alunos");
        System.out.println("  2) Todos os cursos");
        System.out.println("  3) Alunos matriculados em um curso");
        System.out.println("  0) Voltar");
        int op = lerInteiro("Escolha: ");
        limparTela();
        switch (op) {
            case 1:
                listarTodosAlunos();
                pausar();
                break;
            case 2:
                listarTodosCursos();
                pausar();
                break;
            case 3:
                listarAlunosDoCurso();
                pausar();
                break;
            case 0:
                break;
            default:
                System.out.println("Opcao invalida.");
                pausar();
        }
    }

    private void listarTodosAlunos() {
        List<Aluno> alunos = sistema.listarAlunos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        System.out.println("Alunos cadastrados:");
        for (Aluno a : alunos) {
            System.out.println("  - " + a);
        }
    }

    private void listarTodosCursos() {
        List<Curso> cursos = sistema.listarCursos();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
            return;
        }
        System.out.println("Cursos cadastrados:");
        for (Curso c : cursos) {
            System.out.println("  - " + c);
        }
    }

    private void listarAlunosDoCurso() {
        int cursoId = lerInteiro("ID do curso: ");
        try {
            List<Aluno> alunos = sistema.listarAlunosDoCurso(cursoId);
            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno matriculado neste curso.");
                return;
            }
            System.out.println("Alunos matriculados:");
            for (Aluno a : alunos) {
                System.out.println("  - " + a);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = scanner.nextLine();
            try {
                return Integer.parseInt(linha.trim());
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido. Tente novamente.");
            }
        }
    }
}
