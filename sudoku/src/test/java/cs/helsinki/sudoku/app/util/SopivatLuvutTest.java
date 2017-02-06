
package cs.helsinki.sudoku.app.util;

import cs.helsinki.sudoku.app.SudokugeneraattoriTestUtil;
import static cs.helsinki.sudoku.util.SopivatLuvut.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class SopivatLuvutTest {
    SudokugeneraattoriTestUtil u;
    int[][] pohja;
    
    @Before
    public void setUp() {
        u = new SudokugeneraattoriTestUtil();
        pohja = u.valmisPohja4();
    }
    
    @Test
    public void palauttaaTyhjatRuudutJoilleEiSopiviaLukuja() {
        int[][] lauta = u.lautaJotaEiVoiTayttaaOikein();
        ArrayList<SimpleEntry<Integer,Integer>> tyhjatRuudutJoilleEiSopiviaLukuja = annaTyhjatRuudutJoilleEiSopiviaLukuja(lauta);
        
        SimpleEntry<Integer, Integer> eka = new SimpleEntry<>(4,8);
        SimpleEntry<Integer, Integer> toka = new SimpleEntry<>(5,1);
        SimpleEntry<Integer, Integer> kolmas = new SimpleEntry<>(8,2);
        
        assertEquals(eka, tyhjatRuudutJoilleEiSopiviaLukuja.get(0));
        assertEquals(toka, tyhjatRuudutJoilleEiSopiviaLukuja.get(1));
        assertEquals(kolmas, tyhjatRuudutJoilleEiSopiviaLukuja.get(2));
    }
    
    @Test
    public void laskeeRivinSallitutLuvut() {
        ArrayList<Integer> sopivat = rivinSallitutLuvut(pohja, 0);
        ArrayList<Integer> oikeatSopivat = new ArrayList<>(Arrays.asList(1));
        assertTrue(oikeatSopivat.equals(sopivat));
    }
    
    @Test
    public void laskeeSarakkeenSallitutLuvut() {
        ArrayList<Integer> sopivat = sarakkeenSallitutLuvut(pohja, 6);
        ArrayList<Integer> oikeatSopivat = new ArrayList<>(Arrays.asList(1, 6));
        assertTrue(oikeatSopivat.equals(sopivat));
    }
    
    @Test
    public void laskeeOsaruudunSallitutLuvut() {
        ArrayList<Integer> sopivat = osaruudunSallitutLuvut(pohja, 0, 6);
        ArrayList<Integer> oikeatSopivat = new ArrayList<>(Arrays.asList(1, 6));
        assertTrue(oikeatSopivat.equals(sopivat));
    }
    
    @Test
    public void laskeeOikeinRuudunSopivatLuvut() {
        ArrayList<Integer> sopivat = laskeSopivatLuvutIlmanAlkuperaista(u.valmisPohja4(), 6, 6, -1, -1, 0);
        ArrayList<Integer> oikeatSopivat = new ArrayList<>(Arrays.asList(1, 6));
        assertTrue(oikeatSopivat.equals(sopivat));
    }
}
