package cs.helsinki.sudoku.app;

public enum Vaikeusaste {
    HELPPO(25, 3, 0),
    KESKITASO(35, 10, 0),
    VAIKEA(50, 25, 0);

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
