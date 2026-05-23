package br.com.escola.matriculas.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.escola.matriculas.modelo.Aluno;
import br.com.escola.matriculas.modelo.Curso;
import br.com.escola.matriculas.modelo.Matricula;

public class Sistema {

    private final List<Aluno> alunos = new ArrayList<>();
    private final List<Curso> cursos = new ArrayList<>();
    private final List<Matricula> matriculas = new ArrayList<>();

    private int proximoIdAluno = 1;
    private int proximoIdCurso = 1;
    private int proximoIdMatricula = 1;

    public Aluno cadastrarAluno(String nome, String email) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do aluno nao pode ser vazio.");
        }
        Aluno aluno = new Aluno(proximoIdAluno++, nome.trim(), email);
        alunos.add(aluno);
        return aluno;
    }

    public Curso cadastrarCurso(String nome, int vagas) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do curso nao pode ser vazio.");
        }
        if (vagas <= 0) {
            throw new IllegalArgumentException("Vagas deve ser maior que zero.");
        }
        Curso curso = new Curso(proximoIdCurso++, nome.trim(), vagas);
        cursos.add(curso);
        return curso;
    }

    public Matricula matricular(int alunoId, int cursoId) {
        Aluno aluno = buscarAluno(alunoId);
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno nao encontrado: id=" + alunoId);
        }
        Curso curso = buscarCurso(cursoId);
        if (curso == null) {
            throw new IllegalArgumentException("Curso nao encontrado: id=" + cursoId);
        }
        validarMatriculaUnica(aluno, curso);
        validarVagasDisponiveis(curso);
        Matricula matricula = new Matricula(proximoIdMatricula++, aluno, curso);
        matriculas.add(matricula);
        return matricula;
    }

    public void cancelarMatricula(int alunoId, int cursoId) {
        boolean removida = matriculas.removeIf(m ->
                m.getAluno().getId() == alunoId && m.getCurso().getId() == cursoId);
        if (!removida) {
            throw new IllegalArgumentException(
                    "Matricula nao encontrada para aluno id=" + alunoId
                            + " no curso id=" + cursoId + ".");
        }
    }

    private void validarMatriculaUnica(Aluno aluno, Curso curso) {
        for (Matricula m : matriculas) {
            if (m.getAluno().getId() == aluno.getId()
                    && m.getCurso().getId() == curso.getId()) {
                throw new IllegalArgumentException(
                        "Aluno '" + aluno.getNome() + "' ja esta matriculado"
                                + " no curso '" + curso.getNome() + "'.");
            }
        }
    }

    private void validarVagasDisponiveis(Curso curso) {
        int matriculados = contarMatriculasDoCurso(curso.getId());
        if (matriculados >= curso.getVagas()) {
            throw new IllegalArgumentException(
                    "Curso '" + curso.getNome() + "' sem vagas disponiveis"
                            + " (vagas=" + curso.getVagas()
                            + ", matriculados=" + matriculados + ").");
        }
    }

    private int contarMatriculasDoCurso(int cursoId) {
        int total = 0;
        for (Matricula m : matriculas) {
            if (m.getCurso().getId() == cursoId) {
                total++;
            }
        }
        return total;
    }

    public Aluno buscarAluno(int id) {
        for (Aluno a : alunos) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public Curso buscarCurso(int id) {
        for (Curso c : cursos) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Aluno> listarAlunos() {
        return Collections.unmodifiableList(alunos);
    }

    public List<Curso> listarCursos() {
        return Collections.unmodifiableList(cursos);
    }

    public List<Aluno> listarAlunosDoCurso(int cursoId) {
        Curso curso = buscarCurso(cursoId);
        if (curso == null) {
            throw new IllegalArgumentException("Curso nao encontrado: id=" + cursoId);
        }
        List<Aluno> resultado = new ArrayList<>();
        for (Matricula m : matriculas) {
            if (m.getCurso().getId() == cursoId) {
                resultado.add(m.getAluno());
            }
        }
        return resultado;
    }
}
