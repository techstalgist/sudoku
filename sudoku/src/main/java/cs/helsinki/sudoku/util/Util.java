
package cs.helsinki.sudoku.util;


public class Util {
    
    public static void tulosta(int koko, int[][] tulostettavatRuudut) {
        System.out.println("-----");
        for(int i = 0; i < koko; i++) {
            System.out.print("|");
            for(int j = 0; j < koko; j++) {
                int arvo = tulostettavatRuudut[i][j];
                if (arvo == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print(tulostettavatRuudut[i][j]);
                }
                System.out.print("|");
                if ((j+1) % 3 == 0 && !((j+1)==koko)) {
                    System.out.print(" |");
                }
            }
            System.out.println("");
            if ((i+1) % 3 == 0 && !((i+1)==koko)) {
                    System.out.println("");
            }
        }
        System.out.println("-----");
    }
    
}
