package br.com.escola.matriculas.servico;

import br.com.escola.matriculas.modelo.Aluno;
import br.com.escola.matriculas.modelo.Curso;
import br.com.escola.matriculas.modelo.Matricula;

public class SistemaTest {

    private static int passados = 0;
    private static int falhados = 0;

    public static void main(String[] args) {
        cadastrarAlunoValido();
        cadastrarAlunoComNomeVazioFalha();
        cadastrarAlunoComNomeSoEspacosFalha();
        cadastrarCursoValido();
        cadastrarCursoComVagasZeroFalha();
        cadastrarCursoComVagasNegativasFalha();
        matricularComIdsValidos();
        matricularAlunoInexistenteFalha();
        matricularCursoInexistenteFalha();
        idsSaoIncrementais();

        System.out.println();
        System.out.println("============================================");
        System.out.println("RESULTADO: " + passados + " ok, " + falhados + " falha(s).");
        System.out.println("============================================");
        if (falhados > 0) {
            System.exit(1);
        }
    }

    static void cadastrarAlunoValido() {
        Sistema s = new Sistema();
        Aluno a = s.cadastrarAluno("Maria", "maria@x.com");
        assertEquals("Maria", a.getNome(), "US01 - cadastrar aluno valido (nome)");
        assertEquals(1, a.getId(), "US01 - cadastrar aluno valido (id inicial)");
    }

    static void cadastrarAlunoComNomeVazioFalha() {
        Sistema s = new Sistema();
        assertThrows(() -> s.cadastrarAluno("", "x@x.com"),
                "US01 - nome vazio deve falhar");
    }

    static void cadastrarAlunoComNomeSoEspacosFalha() {
        Sistema s = new Sistema();
        assertThrows(() -> s.cadastrarAluno("   ", "x@x.com"),
                "US01 - nome so com espacos deve falhar");
    }

    static void cadastrarCursoValido() {
        Sistema s = new Sistema();
        Curso c = s.cadastrarCurso("Ingles A1", 20);
        assertEquals("Ingles A1", c.getNome(), "US02 - cadastrar curso valido (nome)");
        assertEquals(20, c.getVagas(), "US02 - cadastrar curso valido (vagas)");
    }

    static void cadastrarCursoComVagasZeroFalha() {
        Sistema s = new Sistema();
        assertThrows(() -> s.cadastrarCurso("Ingles", 0),
                "US02 - vagas zero deve falhar");
    }

    static void cadastrarCursoComVagasNegativasFalha() {
        Sistema s = new Sistema();
        assertThrows(() -> s.cadastrarCurso("Ingles", -3),
                "US02 - vagas negativas deve falhar");
    }

    static void matricularComIdsValidos() {
        Sistema s = new Sistema();
        Aluno a = s.cadastrarAluno("Joao", "j@x.com");
        Curso c = s.cadastrarCurso("Espanhol", 10);
        Matricula m = s.matricular(a.getId(), c.getId());
        assertEquals(a.getId(), m.getAluno().getId(), "US03 - matricula liga aluno correto");
        assertEquals(c.getId(), m.getCurso().getId(), "US03 - matricula liga curso correto");
    }

    static void matricularAlunoInexistenteFalha() {
        Sistema s = new Sistema();
        Curso c = s.cadastrarCurso("Frances", 5);
        assertThrows(() -> s.matricular(99, c.getId()),
                "US03 - aluno inexistente deve falhar");
    }

    static void matricularCursoInexistenteFalha() {
        Sistema s = new Sistema();
        Aluno a = s.cadastrarAluno("Ana", "a@x.com");
        assertThrows(() -> s.matricular(a.getId(), 99),
                "US03 - curso inexistente deve falhar");
    }

    static void idsSaoIncrementais() {
        Sistema s = new Sistema();
        Aluno a1 = s.cadastrarAluno("A", "a@x.com");
        Aluno a2 = s.cadastrarAluno("B", "b@x.com");
        assertEquals(1, a1.getId(), "ids incrementais - aluno 1");
        assertEquals(2, a2.getId(), "ids incrementais - aluno 2");
    }

    static void assertEquals(Object esperado, Object obtido, String nome) {
        boolean ok = (esperado == null && obtido == null)
                || (esperado != null && esperado.equals(obtido));
        if (ok) {
            passados++;
            System.out.println("  [OK]    " + nome);
        } else {
            falhados++;
            System.out.println("  [FALHA] " + nome
                    + " -> esperado=" + esperado + ", obtido=" + obtido);
        }
    }

    static void assertThrows(Runnable r, String nome) {
        try {
            r.run();
            falhados++;
            System.out.println("  [FALHA] " + nome + " -> nao lancou excecao");
        } catch (IllegalArgumentException e) {
            passados++;
            System.out.println("  [OK]    " + nome);
        }
    }
}
