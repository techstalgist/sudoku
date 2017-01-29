package cs.helsinki.sudoku.util;

import static cs.helsinki.sudoku.util.GeneraattoriUtil.*;
import static cs.helsinki.sudoku.util.Util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

/**
 * Luokka laskee Sudoku-pelin sääntöjen mukaisia sopivia lukuja / pelilaudan ruutuja annetulle pelilaudalle.
 */

public class SopivatLuvut {

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

    public static boolean annettuLukuOnSopivaLuku(int luku, int[][] lauta, int rivi, int sarake) {
        ArrayList<Integer> sopivatLuvut = laskeSopivatLuvut(lauta, rivi, sarake);

        return sopivatLuvut.contains((Integer) luku);
    }
    
    public static boolean ruudunLukuOnSopivaLuku(int[][] lauta, int rivi, int sarake) {
        Integer alkupLuku = lauta[rivi][sarake];
        int[][] kloonattuLauta = kloonaa(lauta);
        kloonattuLauta[rivi][sarake] = 0;
        return laskeSopivatLuvut(kloonattuLauta, rivi, sarake).contains(alkupLuku);
    }

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
}
