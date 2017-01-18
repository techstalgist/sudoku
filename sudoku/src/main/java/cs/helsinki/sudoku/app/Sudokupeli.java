package cs.helsinki.sudoku.app;

import static cs.helsinki.sudoku.util.SopivatLuvut.*;
import static cs.helsinki.sudoku.util.Util.tulosta;
import java.util.Arrays;

public class Sudokupeli {

    private int koko;
    private int[][] lauta;
    private int[][] ruutujenStatukset;
    private final int[][] ratkaisu;
    private Vaikeusaste vaikeusaste;

    public Sudokupeli(int[][] lauta, int[][] ratkaisu, Vaikeusaste vaikeusaste) {
        this.koko = 9;
        this.lauta = lauta;
        this.ruutujenStatukset = alustaRuutujenStatukset();
        this.ratkaisu = ratkaisu;
        this.vaikeusaste = vaikeusaste;
    }

    public int[][] annaPelilauta() {
        return lauta;
    }

    public int[][] paivitaArvo(int arvo, int i, int j) {
        // tarkistus tehtävä ennen kuin arvo muuttuu laudalla
        boolean onSopivaLuku = annettuLukuOnSopivaLuku(arvo, lauta, i, j);
        lauta[i][j] = arvo;
        // miten tämä suhtautuu edelliseen kommenttiin???
        return paivitaRuutujenStatukset();
    }

    public boolean valmis() {
        return Arrays.deepEquals(lauta, ratkaisu);
    }

    public void tulostaLauta() {
        tulosta(koko, lauta);
    }

    public void tulostaRatkaisu() {
        tulosta(koko, ratkaisu);
    }
    
    public Vaikeusaste annaVaikeusaste() {
        return vaikeusaste;
    }

    private int[][] alustaRuutujenStatukset() {
        int[][] statukset = new int[koko][koko];
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                if (lauta[i][j] > 0) {
                    statukset[i][j] = 1;
                }
            }
        }
        return statukset;
    }

    private int[][] paivitaRuutujenStatukset() {
        
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                int arvo = lauta[i][j];
                if (arvo > 0) {
                  if (annettuLukuOnSopivaLuku(arvo, lauta, i, j)) {
                      ruutujenStatukset[i][j] = 2;
                  } else {
                      ruutujenStatukset[i][j] = 3;
                  }
                }                
            }
        }
        
        return ruutujenStatukset;
    }

}
