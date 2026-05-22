package br.com.escola.matriculas.modelo;

public class Aluno {

    private final int id;
    private final String nome;
    private final String email;

    public Aluno(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Aluno{id=" + id + ", nome='" + nome + "', email='" + email + "'}";
    }
}
