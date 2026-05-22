# Sistema de Matriculas - Escola de Idiomas

Projeto da disciplina **10275 - Engenharia de Software** (Extreme Programming).

Sistema de console em **Java puro** que aplica XP em iteracoes curtas.
Iteracao 1 entrega US01 (Cadastrar Aluno), US02 (Cadastrar Curso) e
US03 (Matricular Aluno em Curso). Dados em memoria.

## Requisitos

- JDK 17 ou superior (testado com Java 21).

## Estrutura

```
src/
  br/com/escola/matriculas/
     Main.java
     modelo/   -> Aluno, Curso, Matricula
     servico/  -> Sistema (regras das US)
     ui/       -> Menu (console)
```

## Como rodar

Compilar:

```bash
javac -d bin -sourcepath src src/br/com/escola/matriculas/Main.java
```

Executar:

```bash
java -cp bin br.com.escola.matriculas.Main
```

## Documentacao do processo (XP)

- [Etapa1_Planejamento.txt](Etapa1_Planejamento.txt) - estorias, estimativas, priorizacao.
- [Etapa2_DesenhoSolucao.txt](Etapa2_DesenhoSolucao.txt) - classes e fluxo.
- [Etapa3_PlanoImplantacao.txt](Etapa3_PlanoImplantacao.txt) - estrutura de arquivos e plano de implementacao.
