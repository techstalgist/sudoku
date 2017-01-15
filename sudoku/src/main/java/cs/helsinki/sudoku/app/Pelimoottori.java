package cs.helsinki.sudoku.app;

public class Pelimoottori {

    private Sudokugeneraattori generaattori;
    private Sudokupeli peli;
    

    public Pelimoottori(Sudokugeneraattori generaattori) {
        this.generaattori = generaattori;
    }

    public Sudokupeli uusiPeli(Vaikeusaste aste) {
        generaattori.tyhjennaRuudutJaRuutulista();
        generaattori.tayta(generaattori.annaRuudut(), 1, 0, true, false);
        generaattori.asetaRatkaisu();
        generaattori.asetaVaikeusaste(aste);
        generaattori.tyhjennaRuutujaTaydeltaLaudalta();
        Sudokupeli uusiPeli = generaattori.annaUusiSudokupeli();
        this.peli = uusiPeli;
        return peli;
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
