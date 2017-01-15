package cs.helsinki.sudoku.app;

public class Pelimoottori {

    private Sudokugeneraattori generaattori;
    private Sudokupeli peli;
    private int tyhjennettavienLkm;

    public Pelimoottori(Sudokugeneraattori generaattori) {
        this.generaattori = generaattori;
    }

    public void asetaTyhjennettavienLkm(int lkm) {
        this.tyhjennettavienLkm = lkm;
    }

    public void luoPeli() {
        generaattori.tyhjennaRuudutJaRuutulista();
        generaattori.tayta(generaattori.annaRuudut(), 1, 0, true, false);
        generaattori.asetaRatkaisu();
        generaattori.tyhjennaRuutujaTaydeltaLaudalta(tyhjennettavienLkm);
        Sudokupeli uusiPeli = generaattori.annaUusiSudokupeli();
        this.peli = uusiPeli;
    }

    public int[][] annaPelilauta() {
        return peli.annaPelilauta();
    }

    public Sudokupeli annaPeli() {
        return peli;
    }

    public void tulostaRatkaisu() {
        peli.tulostaRatkaisu();
    }
}
