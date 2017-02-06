
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
        p.setRuudut(u.valmisPohja1());
        p.tayta(p.getRuudut(),1,0, false);
        assertArrayEquals(u.oikeaTulos1(), p.getRuudut());
    }
    
    @Test
    public void tayttaaToisenPohjanOikein() {
        p.setRuudut(u.valmisPohja2());
        p.tayta(p.getRuudut(),1,0, false);
        assertArrayEquals(u.oikeaTulos2(), p.getRuudut());
    }
    
    @Test
    public void tayttaaKolmannenPohjanOikein() {
        p.setRuudut(u.valmisPohja3());
        p.tayta(p.getRuudut(),1,0, false);
        assertArrayEquals(u.oikeaTulos3(), p.getRuudut());
    }
    
    @Test
    public void tayttaaVaikeanPohjan() {
        p.setRuudut(u.vaikeaPohja());
        p.tayta(p.getRuudut(),1,0, false);
        assertArrayEquals(u.vaikeanPohjanRatkaisu(), p.getRuudut());
    }
            
    @Test
    public void taytettyPohjaSailyySamanaTyhjennyksenJalkeen() {
        p.setRuudut(u.valmisRatkaisu());
        p.tayta(p.getRuudut(),1,0, false);
        int[][] alkupRatkaisu = p.getRuudut();
        p.setRuutulista(u.ruutulista());
        p.setVaikeusaste(Vaikeusaste.KESKITASO);
        p.tyhjennaRuutujaTaydeltaLaudalta();
        p.tayta(p.getRuudut(),1,0, false);
        assertArrayEquals(alkupRatkaisu, p.getRuudut());
    }
      
    @Test 
    public void laskeeOikeinRuudunJollaVahitenSopivia() {
        p.setRuudut(u.valmisPohja4());
        ArrayList<Integer> ruutu = p.ruutuJollaVahitenSopivia(p.getRuudut());
        ArrayList<Integer> oikeaRuutu = new ArrayList<>(Arrays.asList(0, 7, 1));
        assertTrue(oikeaRuutu.equals(ruutu));
    }
    
    @Test
    public void antaaPoikkeavatTiedotJosEiYhtaanSopivaaRuutua() {
        p.setRuudut(u.lautaJollaEiYhtaanSopivaa());
        ArrayList<Integer> ruutu = p.ruutuJollaVahitenSopivia(p.getRuudut());
        ArrayList<Integer> oikeatTiedot = new ArrayList<>(Arrays.asList(0, 0, Integer.MAX_VALUE));
        assertTrue(oikeatTiedot.equals(ruutu));
    }
    
    @Test
    public void eiTeeVirheitaIsommallaMassallaPelinLuonnissa() {
        int virheellistenLkm = 0;
        int pelienLkm = 0;
        while(pelienLkm < 30) {
            pelienLkm++;
            Sudokugeneraattori p1 = new Sudokugeneraattori();
            p1.tayta(p1.getRuudut(), 1,0, false);
            p1.asetaPelilautaRatkaisuksi();
            int[][] alkupRatkaisu = p1.getRatkaisu();
            p1.setVaikeusaste(Vaikeusaste.KESKITASO);
            p1.tyhjennaRuutujaTaydeltaLaudalta();
            p1.tayta(p1.getRuudut(), 1,0, false);
            if (!u.vertaaRuudukkoja(alkupRatkaisu, p1.getRuudut())) {
                virheellistenLkm++;
            }   
        }
        assertEquals(0, virheellistenLkm);
    }
    
    @Test
    public void tyhjentaaLaudan() {
        p.setRuudut(u.valmisPohja4());
        p.tyhjennaRuudutJaRuutulista();
        int[][] tyhjaLauta = new int[9][9];
        assertArrayEquals(tyhjaLauta, p.getRuudut());
    }
  
}

