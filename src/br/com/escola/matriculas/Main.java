package br.com.escola.matriculas;

import br.com.escola.matriculas.servico.Sistema;
import br.com.escola.matriculas.ui.Menu;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Menu menu = new Menu(sistema);
        menu.iniciar();
    }
}
