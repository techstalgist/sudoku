
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
        generaattori.tyhjennaKutsut();
        generaattori.tyhjennaRuudutJaRuutulista();
        generaattori.tayta(generaattori.annaRuudut(), 1, 0, true, false);
        generaattori.asetaRatkaisu();
        generaattori.tyhjennaKutsut();
        generaattori.tyhjennaRuutujaTaydeltaLaudalta(tyhjennettavienLkm);
        Sudokupeli uusiPeli = new Sudokupeli(generaattori.annaRuudut(), generaattori.annaRatkaisu());
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
