package cs.helsinki.sudoku;

import cs.helsinki.sudoku.app.*;

public class Sudoku {
    
    public static void main(String[] args) {
                 
        int lkm = 0;
        while(lkm < 1000) {
            lkm++;
             Sudokugeneraattori generaattori = new Sudokugeneraattori();
            Pelimoottori moottori = new Pelimoottori(generaattori);
            int tyhjennettavienLkm = 20;
            moottori.asetaTyhjennettavienLkm(tyhjennettavienLkm);
            generaattori.tayta(generaattori.annaRuudut(), 1, 0, true, false);
            
          //  #moottori.luoPeli();
          //  Sudokupeli peli = moottori.annaPeli();
          //  peli.tulostaLauta();
        }
        
    }
}
