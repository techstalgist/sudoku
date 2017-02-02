package cs.helsinki.sudoku.app;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SudokupeliTest {

    Sudokupeli p;
    SudokugeneraattoriTestUtil u;
    int[][] lauta;
    int[][] ratkaisu;
    Vaikeusaste aste;

    @Before
    public void setUp() {
        u = new SudokugeneraattoriTestUtil();
        lauta = u.valmisPohja1();
        ratkaisu = u.oikeaTulos1();
        aste = Vaikeusaste.KESKITASO;
        p = new Sudokupeli(lauta, ratkaisu, aste);
    }

    @Test
    public void antaaLaudan() {
        Assert.assertArrayEquals(lauta, p.getPelilauta());
    }

    @Test
    public void alustaaStatukset() {
        RuudunStatus[][] statukset = p.getStatukset();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (p.getPelilauta()[i][j] > 0) {
                    assertEquals(RuudunStatus.VALMIIKSI_TAYTETTY, statukset[i][j]);
                } else {
                    assertEquals(RuudunStatus.TAYTETTAVA, statukset[i][j]);
                }
            }
        }
    }

    @Test
    public void sopivanArvonPaivitys() {
        int oikeaArvo = 4;
        int rivi = 0;
        int sarake = 0;
        RuudunStatus[][] statukset = p.paivitaArvo(oikeaArvo, rivi, sarake);
        assertEquals(RuudunStatus.OIKEIN_TAYTETTY, statukset[rivi][sarake]);
        assertEquals(oikeaArvo, p.getPelilauta()[rivi][sarake]);
    }

    @Test
    public void epaKelvonArvonPaivitys() {
        int oikeaArvo = 9;
        int rivi = 0;
        int sarake = 0;
        RuudunStatus[][] statukset = p.paivitaArvo(oikeaArvo, rivi, sarake);
        assertEquals(RuudunStatus.VAARIN_TAYTETTY, statukset[rivi][sarake]);
        assertEquals(oikeaArvo, p.getPelilauta()[rivi][sarake]);
    }

    @Test
    public void vaarinTaytettyVoiMuuttuaOikeinTaytetyksiJosRuudunTyhjentaa() {
        int rivi = 1;
        int sarake = 4;
        int arvo = 4;
        int rivi2 = 0;
        int sarake2 = 3;
        p.paivitaArvo(arvo, rivi, sarake);
        RuudunStatus[][] statukset = p.paivitaArvo(arvo, rivi2, sarake2);
        assertEquals(RuudunStatus.VAARIN_TAYTETTY, statukset[rivi2][sarake2]);
        RuudunStatus[][] statukset2 = p.paivitaArvo(0, rivi, sarake);
        assertEquals(RuudunStatus.OIKEIN_TAYTETTY, statukset2[rivi2][sarake2]);
    }

    @Test
    public void peliEiValmis() {
        assertFalse(p.valmis());
    }

    @Test
    public void peliValmis() {
        // täyttämisen testi on SudokuGeneraattoriTestissä.
        Sudokupeli p2 = new Sudokupeli(ratkaisu, ratkaisu, aste);
        assertTrue(p2.valmis());
    }

    @Test
    public void antaaVaikeusasteen() {
        Sudokupeli p2 = new Sudokupeli(ratkaisu, ratkaisu, aste);
        assertEquals(aste, p2.getVaikeusaste());
    }
}
