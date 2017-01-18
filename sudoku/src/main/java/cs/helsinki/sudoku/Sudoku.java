package cs.helsinki.sudoku;

import cs.helsinki.sudoku.app.*;
import cs.helsinki.sudoku.ui.Kayttoliittyma;
import javax.swing.SwingUtilities;

public class Sudoku {

    public static void main(String[] args) {

        Sudokugeneraattori generaattori = new Sudokugeneraattori();
        Pelimoottori moottori = new Pelimoottori(generaattori);
        SwingUtilities.invokeLater(new Kayttoliittyma(moottori));
    }
   
}
