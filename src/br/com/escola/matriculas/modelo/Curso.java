package br.com.escola.matriculas.modelo;

public class Curso {

    private final int id;
    private final String nome;
    private final int vagas;

    public Curso(int id, String nome, int vagas) {
        this.id = id;
        this.nome = nome;
        this.vagas = vagas;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getVagas() {
        return vagas;
    }

    @Override
    public String toString() {
        return "Curso{id=" + id + ", nome='" + nome + "', vagas=" + vagas + "}";
    }
}
