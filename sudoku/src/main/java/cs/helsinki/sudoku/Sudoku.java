package cs.helsinki.sudoku;

import cs.helsinki.sudoku.app.*;

public class Sudoku {

    public static void main(String[] args) {

        Sudokugeneraattori generaattori = new Sudokugeneraattori();
        Pelimoottori moottori = new Pelimoottori(generaattori);
        Sudokupeli peli = moottori.uusiPeli(Vaikeusaste.HELPPO);
        peli.tulostaLauta();
    }
   
}
