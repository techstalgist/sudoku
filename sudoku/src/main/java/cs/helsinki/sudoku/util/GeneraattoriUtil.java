
package cs.helsinki.sudoku.util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static cs.helsinki.sudoku.util.Util.*;

public class GeneraattoriUtil {
    
    public static ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> annaListaRuutuja() {
       ArrayList<Integer> rivit = yhdeksanLukua();
       ArrayList<Integer> sarakkeet = yhdeksanLukua();
       ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> tulos = new ArrayList<>();
       for (Integer rivi : rivit) {
           for (Integer sarake : sarakkeet) {
               AbstractMap.SimpleEntry<Integer, Integer> lukupari = new AbstractMap.SimpleEntry<>(rivi -1 , sarake-1);
               tulos.add(lukupari);
           }
       }
       Collections.shuffle(tulos);
       return tulos;
    }
    
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
    
    public static int haeOsaruutu(int rivi, int sarake) {
        int osaruudunRivi = haeOsaruudunAlku(rivi);
        int osaruudunSarake = haeOsaruudunAlku(sarake);
        return osaruudunRivi + osaruudunSarake / 3;
    }
    
    public static int haeOsaruudunAlku(int riviTaiSarake) {
        return (riviTaiSarake / 3) * 3;
    }
    
    public static boolean onRatkaistu(int[][] lauta) {
        for (int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
               if (lauta[i][j] == 0) {
                   return false;
               }  
            }
        }
        return true;
    }
    
    public static boolean tyhjaRuutu(int[][] lauta, int rivi, int sarake) {
        return lauta[rivi][sarake] == 0;
    }
    
    public static int tyhjienLkm(int[][] lauta) {
        int lkm = 0;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(tyhjaRuutu(lauta,i,j)){
                    lkm++;
                };
            }
        }
        return lkm;
    }
}
