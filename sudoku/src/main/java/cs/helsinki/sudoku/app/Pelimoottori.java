package cs.helsinki.sudoku.app;

/**
 * Luokka käyttää Sudokugeneraattoria uuden Sudokupelin luontiin ja palauttaa uuden pelin käyttöliittymälle.
 * Luokka kutsuu myös Sudokupelin metodia pelilaudan arvon päivittämiseksi.
 */

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
    
    public RuudunStatus[][] paivitaArvoPelilaudalla(int luku, int rivi, int sarake) {
        return peli.paivitaArvo(luku, rivi, sarake);
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
