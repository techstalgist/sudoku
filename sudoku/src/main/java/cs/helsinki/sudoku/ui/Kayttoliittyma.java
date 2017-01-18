package cs.helsinki.sudoku.ui;

import cs.helsinki.sudoku.app.Pelimoottori;
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

    public void pyydaUusiPeli() {
        moottori.uusiPeli(aste);
    }

    public int[][] pyydaPelilauta() {
        return moottori.annaPelilauta();
    }
    
    public void paivitaArvo(int luku, int rivi, int sarake) {
        
        moottori.paivitaArvoPelilaudalla(luku, rivi, sarake);        
        boolean peliValmis = moottori.peliValmis();
        if (peliValmis) {
            nakyma.naytaValmisIlmoitus();
        };
    }
}
