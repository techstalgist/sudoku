
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
        Assert.assertArrayEquals(lauta, p.annaPelilauta());
    }
    
    @Test
    public void sopivanArvonPaivitys() {
        int oikeaArvo = 4;
        int rivi = 0;
        int sarake = 0;
        RuudunStatus[][] statukset = p.paivitaArvo(oikeaArvo, rivi, sarake);
        assertEquals(RuudunStatus.OIKEIN_TAYTETTY, statukset[rivi][sarake]);
        assertEquals(oikeaArvo,p.annaPelilauta()[rivi][sarake]);
    }
    
    @Test
    public void epaKelvonArvonPaivitys() {
        int oikeaArvo = 9;
        int rivi = 0;
        int sarake = 0;
        RuudunStatus[][] statukset = p.paivitaArvo(oikeaArvo, rivi, sarake);
        assertEquals(RuudunStatus.VAARIN_TAYTETTY, statukset[rivi][sarake]);
        assertEquals(oikeaArvo,p.annaPelilauta()[rivi][sarake]);
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
        assertEquals(aste, p2.annaVaikeusaste());
    }
}

