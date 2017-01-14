
package cs.helsinki.sudoku.app;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SudokugeneraattoriTest {
    Sudokugeneraattori p;
    SudokugeneraattoriTestUtil u;
    
    @Before
    public void setUp() {
        p = new Sudokugeneraattori();
        u = new SudokugeneraattoriTestUtil();
    }
    
    @Test
    public void tayttaaPohjanOikein() {
        p.asetaRuudut(u.valmisPohja1());
        p.tayta(p.annaRuudut(),1,0, false, false);
        assertArrayEquals(u.oikeaTulos1(), p.annaRuudut());
    }
    
    @Test
    public void tayttaaToisenPohjanOikein() {
        p.asetaRuudut(u.valmisPohja2());
        p.tayta(p.annaRuudut(),1,0, false, false);
        assertArrayEquals(u.oikeaTulos2(), p.annaRuudut());
    }
    
    @Test
    public void tayttaaKolmannenPohjanOikein() {
        p.asetaRuudut(u.valmisPohja3());
        p.tayta(p.annaRuudut(),1,0, false, false);
        assertArrayEquals(u.oikeaTulos3(), p.annaRuudut());
    }
    
    @Test
    public void tayttaaVaikeanPohjan() {
        p.asetaRuudut(u.vaikeaPohja());
        p.tayta(p.annaRuudut(),1,0, false, false);
        assertArrayEquals(u.vaikeanPohjanRatkaisu(), p.annaRuudut());
    }
            
    @Test
    public void taytettyPohjaSailyySamanaTyhjennyksenJalkeen() {
        p.asetaRuudut(u.valmisRatkaisu());
        p.tayta(p.annaRuudut(),1,0, false, false);
        int[][] alkupRatkaisu = p.annaRuudut();
        p.asetaRuutulista(u.ruutulista());
        p.tyhjennaRuutujaTaydeltaLaudalta(35);
        p.tayta(p.annaRuudut(),1,0, false, false);
        assertArrayEquals(alkupRatkaisu, p.annaRuudut());
    }
    
    @Test
    public void laskeeOikeinRuudunSopivatLuvut() {
        p.asetaRuudut(u.valmisPohja4());
        ArrayList<Integer> sopivat = p.laskeSopivatLuvutIlmanAlkuperaista(p.annaRuudut(), 6, 6);
        ArrayList<Integer> oikeatSopivat = new ArrayList<>(Arrays.asList(1, 6));
        assertTrue(oikeatSopivat.equals(sopivat));
    }
    
    @Test 
    public void laskeeOikeinRuudunJollaVahitenSopivia() {
        p.asetaRuudut(u.valmisPohja4());
        ArrayList<Integer> ruutu = p.ruutuJollaVahitenSopivia(p.annaRuudut());
        ArrayList<Integer> oikeaRuutu = new ArrayList<>(Arrays.asList(0, 7, 1));
        assertTrue(oikeaRuutu.equals(ruutu));
    }
    
    @Test
    public void eiTeeVirheitaIsommallaMassallaPelinLuonnissa() {
        int virheellistenLkm = 0;
        int pelienLkm = 0;
        while(pelienLkm < 30) {
            pelienLkm++;
            Sudokugeneraattori p1 = new Sudokugeneraattori();
            p1.tayta(p1.annaRuudut(), 1,0, true, false);
            p1.asetaRatkaisu();
            int[][] alkupRatkaisu = p1.annaRatkaisu();
            p1.tyhjennaRuutujaTaydeltaLaudalta(35);
            p1.tayta(p1.annaRuudut(), 1,0, false, false);
            if (!u.vertaaRuudukkoja(alkupRatkaisu, p1.annaRuudut())) {
                virheellistenLkm++;
            }   
        }
        assertEquals(0, virheellistenLkm);
    }
  
}

