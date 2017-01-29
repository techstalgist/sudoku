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
    
    public int annaTyhjennettavienLkm() {
        return tyhjennettavienLkm;
    }
    
    public int annaMinuutit() {
        return minuutit;
    }
    
    public int annaSekunnit() {
        return sekunnit;
    }
}
