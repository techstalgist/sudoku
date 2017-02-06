package cs.helsinki.sudoku.util;

import static cs.helsinki.sudoku.util.GeneraattoriUtil.*;
import static cs.helsinki.sudoku.util.Util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

/**
 * Luokka laskee Sudoku-pelin sääntöjen mukaisia sopivia lukuja / pelilaudan ruutuja annetulle pelilaudalle.
 */

public class SopivatLuvut {
    
    /**
    * Laskee pelilaudan ruudulle siihen sopivat luvut.
    * @param lauta pelilauta
    * @param rivi rivi
    * @param sarake sarake
    * @return ArrayList jossa sopivat luvut
    */

    public static ArrayList<Integer> laskeSopivatLuvut(int[][] lauta, int rivi, int sarake) {

        ArrayList<Integer> rivinLuvut = rivinSallitutLuvut(lauta, rivi);
        ArrayList<Integer> sarakkeenLuvut = sarakkeenSallitutLuvut(lauta, sarake);
        ArrayList<Integer> osaruudunLuvut = osaruudunSallitutLuvut(lauta, rivi, sarake);

        ArrayList<Integer> sopivatLuvut = new ArrayList<>();
        for (Integer i : rivinLuvut) {
            if (sarakkeenLuvut.contains(i) && osaruudunLuvut.contains(i)) {
                sopivatLuvut.add(i);
            }
        }

        return sopivatLuvut;
    }
    
    /**
    * Kertoo onko annettu luku pelilaudan ruutuun sopiva luku.
    * @param luku annettu luku
    * @param lauta pelilauta
    * @param rivi rivi
    * @param sarake sarake
    * @return true jos on sopiva, false muuten
    */

    public static boolean annettuLukuOnSopivaLuku(int luku, int[][] lauta, int rivi, int sarake) {
        ArrayList<Integer> sopivatLuvut = laskeSopivatLuvut(lauta, rivi, sarake);

        return sopivatLuvut.contains((Integer) luku);
    }
    
    /**
    * Kertoo, että jos pelilaudan ruudussa nyt oleva luku tyhjennettäisiin, niin olisiko alkuperäinen luku yksi ruutuun sopivista luvuista.
    * @param lauta pelilauta
    * @param rivi rivi
    * @param sarake sarake
    * @return true jos on sopiva, false muuten
    */
    
    public static boolean ruudunLukuOnSopivaLuku(int[][] lauta, int rivi, int sarake) {
        Integer alkupLuku = lauta[rivi][sarake];
        int[][] kloonattuLauta = kloonaa(lauta);
        kloonattuLauta[rivi][sarake] = 0;
        return laskeSopivatLuvut(kloonattuLauta, rivi, sarake).contains(alkupLuku);
    }
    
    /**
    * Laskee pelilaudan riville sopivat luvut.
    * @param lauta pelilauta
    * @param rivi rivi
    * @return ArrayList jossa sopivat luvut
    */

    public static ArrayList<Integer> rivinSallitutLuvut(int[][] lauta, int rivi) {
        ArrayList<Integer> luvut = yhdeksanLukua();
        for (int i = 0; i < 9; i++) {
            Integer luku = lauta[rivi][i];
            if (luku >= 1) {
                luvut.remove(luku);
            }
        }

        return luvut;
    }
    
    /**
    * Laskee pelilaudan sarakkeeseen sopivat luvut.
    * @param lauta pelilauta
    * @param sarake sarake
    * @return ArrayList jossa sopivat luvut
    */

    public static ArrayList<Integer> sarakkeenSallitutLuvut(int[][] lauta, int sarake) {
        ArrayList<Integer> luvut = yhdeksanLukua();
        for (int i = 0; i < 9; i++) {
            Integer luku = lauta[i][sarake];
            if (luku >= 1) {
                luvut.remove(luku);
            }
        }

        return luvut;
    }
    
    /**
    * Laskee pelilaudan osaruutuun sopivat luvut.
    * @param lauta pelilauta
    * @param rivi rivi
    * @param sarake sarake
    * @return ArrayList jossa sopivat luvut
    */

    public static ArrayList<Integer> osaruudunSallitutLuvut(int[][] lauta, int rivi, int sarake) {
        ArrayList<Integer> luvut = yhdeksanLukua();
        int osaruudunRivi = haeOsaruudunAlku(rivi);
        int osaruudunSarake = haeOsaruudunAlku(sarake);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Integer luku = lauta[osaruudunRivi + i][osaruudunSarake + j];
                if (luku >= 1) {
                    luvut.remove(luku);
                }
            }
        }

        return luvut;
    }
    
    /**
    * Palauttaa listan pelilaudan ruuduista, joille ei ole yhtään sopivaa lukua.
    * @param lauta pelilauta
    * @return ArrayList jossa lista ruuduista (lukupareina)
    */

    public static ArrayList<SimpleEntry<Integer, Integer>> annaTyhjatRuudutJoilleEiSopiviaLukuja(int[][] lauta) {
        ArrayList<SimpleEntry<Integer, Integer>> tyhjatRuudutJoilleEiSopiviaLukuja = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tyhjaRuutu(lauta, i, j)) {
                    ArrayList<Integer> sopivatLuvut = laskeSopivatLuvut(lauta, i, j);
                    if (sopivatLuvut.isEmpty()) {
                        tyhjatRuudutJoilleEiSopiviaLukuja.add(new SimpleEntry<>(i, j));
                    }
                }
            }
        }

        return tyhjatRuudutJoilleEiSopiviaLukuja;
    }
    
     /**
    * Metodi kutsuu laskeSopivatLuvut metodia pelilaudan tietylle ruudulle ja palauttaa listan sopivia lukuja, ilman ruudun alkuperäistä arvoa.
    * @param lauta jota käytetään
    * @param rivi rivi jolle sopivat luvut halutaan laskea
    * @param sarake sarake jolle sopivat luvut halutaan laskea
    * @param tyhjennettavanRivi jos ollaan tyhjentämässä pelilaudalta ruutuja, niin sen ruudun rivi, jolta arvo tyhjennettiin. muuten -1.
    * @param tyhjennettavanSarake jos ollaan tyhjentämässä pelilaudalta ruutuja, niin sen ruudun sarake, jolta arvo tyhjennettiin. muuten -1.
    * @param tyhjennettavanAlkupArvo jos ollaan tyhjentämässä pelilaudalta ruutuja, niin tyhjennettävän ruudun alkuperäinen arvo. muuten 0.
    * @return lista jossa on ruutuun sopivat luvut (ilman ruudun alkuperäistä arvoa)
    */

    public static ArrayList<Integer> laskeSopivatLuvutIlmanAlkuperaista(int[][] lauta, int rivi, int sarake, int tyhjennettavanRivi, int tyhjennettavanSarake, int tyhjennettavanAlkupArvo) {

        ArrayList<Integer> sopivatLuvut = laskeSopivatLuvut(lauta, rivi, sarake);
        if (rivi == tyhjennettavanRivi && sarake == tyhjennettavanSarake) {
            sopivatLuvut.remove((Integer) tyhjennettavanAlkupArvo);
        }
        return sopivatLuvut;
    }
}
