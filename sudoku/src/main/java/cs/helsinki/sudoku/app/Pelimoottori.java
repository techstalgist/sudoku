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
    
    public void paivitaArvoPelilaudalla(int luku, int rivi, int sarake) {
        peli.paivitaArvo(luku, rivi, sarake);
        // tähän kaikkien ruutujen värien päivitys kutsumalla jotain Nakyman metodia...
    }
    
    public boolean peliValmis() {
        return peli.valmis();
    }
    
    public int[][] annaPelilauta() {
        return peli.annaPelilauta();
    }
    
    public void tulostaRatkaisu() {
        peli.tulostaRatkaisu();
    }
}
