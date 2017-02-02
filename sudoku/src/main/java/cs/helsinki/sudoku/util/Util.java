package cs.helsinki.sudoku.util;

import java.util.ArrayList;

/**
 * Yleisiä apumetodeja.
 */

public class Util {
    
    /**
    * Palauttaa listan jossa luvut 1-9.
    * @return ArrayList jossa luvut 1-9
    */

    public static ArrayList<Integer> yhdeksanLukua() {
        ArrayList<Integer> luvut = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            luvut.add(i + 1);
        }
        return luvut;
    }
    
    /**
    * Tulostaa System.outiin annetut ruudut annetun rivi/sarakepituuden perusteella.
    * @param koko rivi/sarakepituus
    * @param tulostettavatRuudut pelilaudan ruudut, jotka tulostetaan
    */

    public static void tulosta(int koko, int[][] tulostettavatRuudut) {
        System.out.println("-----");
        for (int i = 0; i < koko; i++) {
            System.out.print("|");
            for (int j = 0; j < koko; j++) {
                int arvo = tulostettavatRuudut[i][j];
                if (arvo == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print(tulostettavatRuudut[i][j]);
                }
                System.out.print("|");
                if ((j + 1) % 3 == 0 && !((j + 1) == koko)) {
                    System.out.print(" |");
                }
            }
            System.out.println("");
            if ((i + 1) % 3 == 0 && !((i + 1) == koko)) {
                System.out.println("");
            }
        }
        System.out.println("-----");
    }

}
