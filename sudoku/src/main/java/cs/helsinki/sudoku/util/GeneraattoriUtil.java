package cs.helsinki.sudoku.util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static cs.helsinki.sudoku.util.Util.*;

/**
 * Sudokugeneraattorin tarvitsemia apumetodeja.
 */

public class GeneraattoriUtil {
    /**
    * Palauttaa satunnaisessa järjestyksessä olevat 81 lukuparia, jossa luvut 1-9 pareittain.
    * @return ArrayList jossa 81 lukuparia
    */
    
    public static ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> annaListaRuutuja() {
        ArrayList<Integer> rivit = yhdeksanLukua();
        ArrayList<Integer> sarakkeet = yhdeksanLukua();
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> tulos = new ArrayList<>();
        for (Integer rivi : rivit) {
            for (Integer sarake : sarakkeet) {
                AbstractMap.SimpleEntry<Integer, Integer> lukupari = new AbstractMap.SimpleEntry<>(rivi - 1, sarake - 1);
                tulos.add(lukupari);
            }
        }
        Collections.shuffle(tulos);
        return tulos;
    }
    
    /**
    * Kloonaa kokonaislukutaulukon.
    * @param alkup kloonattava taulukko
    * @return kloonattu kokonaislukutaulukko
    */

    public static int[][] kloonaa(int[][] alkup) {
        if (alkup == null) {
            return null;
        }

        final int[][] tulos = new int[alkup.length][];
        for (int i = 0; i < alkup.length; i++) {
            tulos[i] = Arrays.copyOf(alkup[i], alkup[i].length);
        }
        return tulos;
    }

    /**
    * Laskee osaruudun numeron annetulle riville ja sarakkeelle.
    * @param rivi rivi
    * @param sarake sarake
    * @return kokonaisluku 0-8, joka kertoo osaruudun numeron. Numerointi vasemmalta oikealle ja ylhäältä alas
    */
    
    public static int haeOsaruutu(int rivi, int sarake) {
        int osaruudunRivi = haeOsaruudunAlku(rivi);
        int osaruudunSarake = haeOsaruudunAlku(sarake);
        return osaruudunRivi + osaruudunSarake / 3;
    }
    
    /**
    * Laskee rivin tai sarakkeen ensimmäisen indeksin.
    * @param riviTaiSarake rivi tai sarake
    * @return rivin tai sarakkeen ensimmäinen indeksi
    */

    public static int haeOsaruudunAlku(int riviTaiSarake) {
        return (riviTaiSarake / 3) * 3;
    }
    
    /**
    * Tarkistaa, onko annettu pelilauta ratkaisu loppuun asti vai ei.
    * @param lauta pelilauta
    * @return boolean -arvo true jos ei yhtään tyhjää ruutua, false muuten.
    */

    public static boolean onRatkaistu(int[][] lauta) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (lauta[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
    * Kertoo onko annetun pelilaudan annettu ruutu tyhjä vai ei.
    * @param lauta pelilauta
    * @param rivi rivi
    * @param sarake sarake
    * @return boolean -arvo true jos on tyhjä, tai false jos ei.
    */

    public static boolean tyhjaRuutu(int[][] lauta, int rivi, int sarake) {
        return lauta[rivi][sarake] == 0;
    }
    
    /**
    * Laskee annetun pelilaudan tyhjien ruutujen lkm:n.
    * @param lauta pelilauta jota käytetään
    * @return tyhjienlkm
    */

    public static int tyhjienLkm(int[][] lauta) {
        int lkm = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tyhjaRuutu(lauta, i, j)) {
                    lkm++;
                }
            }
        }
        return lkm;
    }
}
