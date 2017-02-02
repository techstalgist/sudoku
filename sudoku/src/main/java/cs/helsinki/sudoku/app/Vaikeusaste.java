package cs.helsinki.sudoku.app;

/**
 * Mahdolliset Sudoku-pelin vaikeusasteet, tyhjennettävien ruutujen lkm:t sekä aikarajat.
 */

public enum Vaikeusaste {
    HELPPO(25, 3, 0),
    KESKITASO(40, 10, 0),
    VAIKEA(55, 25, 0);

    private final int tyhjennettavienLkm;
    private final int minuutit;
    private final int sekunnit;
    
    Vaikeusaste(int tyhjennettavienLkm, int minuutit, int sekunnit) {
        this.tyhjennettavienLkm = tyhjennettavienLkm;
        this.minuutit = minuutit;
        this.sekunnit = sekunnit;
    }
    
    /**
    * Metodi kertoo, kuinka monta ruutua pitää tyhjentää vaikeusasteelle.
    * @return tyhjennettävien ruutujen lukumäärä
    */
    
    public int annaTyhjennettavienLkm() {
        return tyhjennettavienLkm;
    }
    
    /**
    * Metodi kertoo, kuinka monta minuuttia pelin ratkaisemiseen on aikaa ko. vaikeusasteella.
    * @return minuutit kokonaislukuna
    */
    
    public int annaMinuutit() {
        return minuutit;
    }
    
     /**
    * Metodi kertoo, kuinka monta sekuntia pelin ratkaisemiseen on aikaa ko. vaikeusasteella.
    * @return sekunnit kokonaislukuna
    */
    
    public int annaSekunnit() {
        return sekunnit;
    }
}
