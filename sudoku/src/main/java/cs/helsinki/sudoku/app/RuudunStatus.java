package cs.helsinki.sudoku.app;

import java.awt.Color;

/**
 *
 * @author mikkoruuskanen
 */
public enum RuudunStatus {
    TAYTETTAVA(Color.YELLOW, 0),
    VALMIIKSI_TAYTETTY(Color.WHITE, 1),
    OIKEIN_TAYTETTY(Color.GREEN, 2),
    VAARIN_TAYTETTY(Color.RED, 3);

    private final Color vari;
    private final int numero;

    RuudunStatus(Color vari, int nro) {
        this.vari = vari;
        this.numero = nro;
    }
    
    public Color annaVari() {
        return vari;
    }
    
    public int numero() {
        return numero;
    }

}
