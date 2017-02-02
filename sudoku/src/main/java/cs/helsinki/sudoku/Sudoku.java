package cs.helsinki.sudoku;

import cs.helsinki.sudoku.app.*;
import cs.helsinki.sudoku.ui.Kayttoliittyma;
import javax.swing.SwingUtilities;

/**
 * Sudoku-sovelluksen Main-luokka. Luo Sudokugeneraattorin, pelimoottorin ja käynnistää käyttöliittymän
 */

public class Sudoku {
    
    /**
    * Sovelluksen käynnistävä main-metodi. Luo Sudokugeneraattorin, pelimoottorin ja käynnistää käyttöliittymän.
    * @param args argumentit
    */
    
    public static void main(String[] args) {

        Sudokugeneraattori generaattori = new Sudokugeneraattori();
        Pelimoottori moottori = new Pelimoottori(generaattori);
        SwingUtilities.invokeLater(new Kayttoliittyma(moottori));
    }
   
}
