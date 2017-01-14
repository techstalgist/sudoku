package cs.helsinki.sudoku;

import cs.helsinki.sudoku.app.*;

public class Sudoku {
    
    public static void main(String[] args) {      
       
        Sudokugeneraattori generaattori = new Sudokugeneraattori();
        Pelimoottori moottori = new Pelimoottori(generaattori);
        int tyhjennettavienLkm = 20;
        moottori.asetaTyhjennettavienLkm(tyhjennettavienLkm);
        moottori.luoPeli();
        Sudokupeli peli = moottori.annaPeli();
        peli.tulostaLauta();
    
    }
}
