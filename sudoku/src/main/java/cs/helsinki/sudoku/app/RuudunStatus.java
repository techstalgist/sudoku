package cs.helsinki.sudoku.app;

import java.awt.Color;

/**
 * Mahdolliset Sudoku-pelin ruudun tilat, sekä väri joka tilalle.
 */

public enum RuudunStatus {
    TAYTETTAVA(Color.YELLOW),
    VALMIIKSI_TAYTETTY(Color.WHITE),
    OIKEIN_TAYTETTY(Color.GREEN),
    VAARIN_TAYTETTY(Color.RED);

    private final Color vari;

    RuudunStatus(Color vari) {
        this.vari = vari;
    }
    
    public Color annaVari() {
        return vari;
    }
    
    
}
