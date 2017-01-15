package cs.helsinki.sudoku.app;

public enum Vaikeusaste {
    HELPPO(25),
    KESKITASO(35),
    VAIKEA(50);

    private final int tyhjennettavienLkm;

    Vaikeusaste(int tyhjennettavienLkm) {
        this.tyhjennettavienLkm = tyhjennettavienLkm;
    }
    
    public int annaTyhjennettavienLkm() {
        return tyhjennettavienLkm;
    }
}
