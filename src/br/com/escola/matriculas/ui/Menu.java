package br.com.escola.matriculas.ui;

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
            imprimirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");
            System.out.println();
            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    cadastrarCurso();
                    break;
                case 3:
                    matricular();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
            System.out.println();
        } while (opcao != 0);
    }

    private void imprimirMenu() {
        System.out.println("==========================================");
        System.out.println("  SISTEMA DE MATRICULAS - ESCOLA DE IDIOMAS");
        System.out.println("==========================================");
        System.out.println("  1) Cadastrar aluno");
        System.out.println("  2) Cadastrar curso");
        System.out.println("  3) Matricular aluno em curso");
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
