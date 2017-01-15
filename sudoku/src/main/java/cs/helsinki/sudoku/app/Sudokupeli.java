package cs.helsinki.sudoku.app;

import static cs.helsinki.sudoku.util.SopivatLuvut.*;
import static cs.helsinki.sudoku.util.Util.tulosta;
import java.util.Arrays;

public class Sudokupeli {

    private int koko;
    private int[][] lauta;
    private final int[][] ratkaisu;
    private Vaikeusaste vaikeusaste;

    public Sudokupeli(int[][] lauta, int[][] ratkaisu, Vaikeusaste vaikeusaste) {
        this.koko = 9;
        this.lauta = lauta;
        this.ratkaisu = ratkaisu;
        this.vaikeusaste = vaikeusaste;
    }

    public int[][] annaPelilauta() {
        return lauta;
    }

    public boolean paivitaArvo(int arvo, int i, int j) {
        // tarkistus tehtävä ennen kuin arvo muuttuu laudalla
        boolean onSopivaLuku = annettuLukuOnSopivaLuku(arvo, lauta, i, j);
        lauta[i][j] = arvo;
        return onSopivaLuku;
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

}
