package cs.helsinki.sudoku.ui;

import cs.helsinki.sudoku.ui.nakyma.Nakymanhallinta;
import cs.helsinki.sudoku.app.Pelimoottori;
import cs.helsinki.sudoku.app.RuudunStatus;
import cs.helsinki.sudoku.app.Vaikeusaste;

public class Kayttoliittyma implements Runnable {

    private Pelimoottori moottori;
    private Nakymanhallinta nakyma;
    private Vaikeusaste aste;

    public Kayttoliittyma(Pelimoottori moottori) {
        this.moottori = moottori;
        this.nakyma = new Nakymanhallinta(this);
    }

    @Override
    public void run() {
        nakyma.init();
    }
    
    public void asetaVaikeusaste(Vaikeusaste aste) {
        this.aste = aste;
    }
    
    public Vaikeusaste annaVaikeusaste() {
        return aste;
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
        nakyma.paivitaPelinaytonVarit(uudetStatukset);
        boolean peliValmis = moottori.peliValmis();
        if (peliValmis) {
            nakyma.naytaValmisIlmoitus();
        }
    }

    public void tarkistaPelitilanneAjanLoppuessa() {
        boolean peliValmis = moottori.peliValmis();
        if (!peliValmis) {
            nakyma.naytaAikaLoppuiIlmoitus();
        }
    }
}
