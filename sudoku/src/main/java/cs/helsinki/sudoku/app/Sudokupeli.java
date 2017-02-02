package cs.helsinki.sudoku.app;

import static cs.helsinki.sudoku.util.SopivatLuvut.*;
import java.util.Arrays;

/**
 * Luokkaa käyttäen luodaan yksi Sudokupeli annetulla valmiilla pelilaudalla ja sen ratkaisulla.
 * Luokkaa käytetään pelilaudan arvon päivittämiseen. Luokka pitää myös kirjaa jokaisen pelilaudan ruudun tilasta.
 */

public class Sudokupeli {

    private int koko;
    private int[][] lauta;
    private RuudunStatus[][] ruutujenStatukset;
    private final int[][] ratkaisu;
    private Vaikeusaste vaikeusaste;
    
    /**
    * Luo uuden Sudokupeli-olion annetun pelilaudalla, ratkaisulla ja vaikeusasteella.
    * @param lauta pelilauta, joka on valmis pelattavaksi
    * @param ratkaisu pelin ratkaisu
    * @param vaikeusaste pelin vaikeusaste
    * Konstruktorissa kutsutaan myös metodia, joka alustaa ruutujen statukset.
    */
    
    public Sudokupeli(int[][] lauta, int[][] ratkaisu, Vaikeusaste vaikeusaste) {
        this.koko = 9;
        this.lauta = lauta;
        this.ruutujenStatukset = alustaRuutujenStatukset();
        this.ratkaisu = ratkaisu;
        this.vaikeusaste = vaikeusaste;
    }

    public int[][] getPelilauta() {
        return lauta;
    }
    
    /**
    * Metodi päivittää annettuun ruutuun annetun arvon ja palauttaa ruutujen statukset päivityksen jälkeen.
    * @param arvo uusi arvo
    * @param i rivi
    * @param j sarake
    * @return RuudunStatus-taulukko päivityksen jälkeen
    */

    public RuudunStatus[][] paivitaArvo(int arvo, int i, int j) {

        boolean onSopivaLuku = annettuLukuOnSopivaLuku(arvo, lauta, i, j);
        lauta[i][j] = arvo;

        return paivitaRuutujenStatukset(i, j, onSopivaLuku);
    }
    
    /**
    * Metodi palauttaa tiedon siitä onko Sudouupeli valmis vai ei.
    * @return boolean arvo, joka kertoo että onko lauta -muuttujassa samat arvot kuin ratkaisussa
    */

    public boolean valmis() {
        return Arrays.deepEquals(lauta, ratkaisu);
    }

    public Vaikeusaste getVaikeusaste() {
        return vaikeusaste;
    }
    
    public RuudunStatus[][] getStatukset() {
        return ruutujenStatukset;
    }

    private RuudunStatus[][] alustaRuutujenStatukset() {
        RuudunStatus[][] statukset = new RuudunStatus[koko][koko];
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                if (lauta[i][j] > 0) {
                    statukset[i][j] = RuudunStatus.VALMIIKSI_TAYTETTY;
                } else {
                    statukset[i][j] = RuudunStatus.TAYTETTAVA;
                }
            }
        }
        return statukset;
    }

    private RuudunStatus[][] paivitaRuutujenStatukset(int taytetynRivi, int taytetynSarake, boolean onSopivaLuku) {

        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                RuudunStatus status = ruutujenStatukset[i][j];

                if (status != RuudunStatus.VALMIIKSI_TAYTETTY) {
                    int arvo = lauta[i][j];
                    if (arvo == 0) {
                        ruutujenStatukset[i][j] = RuudunStatus.TAYTETTAVA;
                        continue;
                    }
                    if (i == taytetynRivi && j == taytetynSarake) {
                        ruutujenStatukset[i][j] = onSopivaLuku ? RuudunStatus.OIKEIN_TAYTETTY : RuudunStatus.VAARIN_TAYTETTY;
                        continue;
                    }
                    // ilmeisesti oikein täytetty ruutu ei voi muuttua väärin täytetyksi, koska tässä tilanteessa syötetty luku on väärin täytetty.
                    // sen sijaan väärin täytetty voi muuttua oikein täytetyksi, jos jonkun ruudun tyhjentää
                    if (ruutujenStatukset[i][j] == RuudunStatus.VAARIN_TAYTETTY && ruudunLukuOnSopivaLuku(lauta, i, j)) {
                        ruutujenStatukset[i][j] = RuudunStatus.OIKEIN_TAYTETTY;
                    }
                }
            }
        }

        return ruutujenStatukset;
    }

}
