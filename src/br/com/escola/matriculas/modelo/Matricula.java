package br.com.escola.matriculas.modelo;

public class Matricula {

    private final int id;
    private final Aluno aluno;
    private final Curso curso;

    public Matricula(int id, Aluno aluno, Curso curso) {
        this.id = id;
        this.aluno = aluno;
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    @Override
    public String toString() {
        return "Matricula{id=" + id
                + ", aluno=" + aluno.getNome()
                + ", curso=" + curso.getNome() + "}";
    }
}
