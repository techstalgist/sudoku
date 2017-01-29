package cs.helsinki.sudoku.ui;

import cs.helsinki.sudoku.ui.nakyma.Nakymanhallinta;
import cs.helsinki.sudoku.app.Pelimoottori;
import cs.helsinki.sudoku.app.RuudunStatus;
import cs.helsinki.sudoku.app.Vaikeusaste;

/**
 * Käyttöliittymää hallinnoiva luokka. Tarjoaa tapahtumakäsittelijöille rajapinnan pelimoottorin kutsuille.
 */

public class Kayttoliittyma implements Runnable {

    private Pelimoottori moottori;
    private Nakymanhallinta hallinta;
    private Vaikeusaste aste;
    private boolean kaytaAikarajaa;

    public Kayttoliittyma(Pelimoottori moottori) {
        this.moottori = moottori;
        this.hallinta = new Nakymanhallinta(this);
        this.kaytaAikarajaa = false;
    }

    @Override
    public void run() {
        hallinta.init();
    }
    
    public void asetaVaikeusaste(Vaikeusaste aste) {
        this.aste = aste;
    }
    
    public Vaikeusaste annaVaikeusaste() {
        return aste;
    }
    
    public boolean kaytaAikarajaa() {
        return kaytaAikarajaa;
    }

    public void pyydaUusiPeli() {
        if (aste == null) {
            aste = Vaikeusaste.HELPPO;
        }
        moottori.uusiPeli(aste);
    }

    public int[][] pyydaPelilauta() {
        return moottori.annaPelilauta();
    }
    
    public void paivitaArvo(int luku, int rivi, int sarake) {
        
        RuudunStatus[][] uudetStatukset = moottori.paivitaArvoPelilaudalla(luku, rivi, sarake);
        hallinta.paivitaPelinaytonVarit(uudetStatukset);
        boolean peliValmis = moottori.peliValmis();
        if (peliValmis) {
            hallinta.naytaValmisIlmoitus();
        }
    }

    public void tarkistaPelitilanneAjanLoppuessa() {
        boolean peliValmis = moottori.peliValmis();
        if (!peliValmis) {
            hallinta.naytaAikaLoppuiIlmoitus();
        }
    }

    public void asetaKaytaAikarajaa(boolean uusiArvo) {
        this.kaytaAikarajaa = uusiArvo;
    }
}
