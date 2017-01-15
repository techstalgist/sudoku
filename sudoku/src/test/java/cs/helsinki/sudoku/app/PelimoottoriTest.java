
package cs.helsinki.sudoku.app;

import static cs.helsinki.sudoku.util.GeneraattoriUtil.*;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;


public class PelimoottoriTest {
    Sudokugeneraattori s;
    Pelimoottori m;
    Vaikeusaste aste;
    
    @Before
    public void setUp() {
        s = new Sudokugeneraattori();
        m = new Pelimoottori(s);
        aste = Vaikeusaste.KESKITASO;
    }
    
    @Test
    public void luoPelinJossaOikeaMaaraTyhjia() {
       m.uusiPeli(aste);
       int[][] lauta = m.annaPelilauta();
       assertEquals(aste.annaTyhjennettavienLkm(), tyhjienLkm(lauta));
    }
    
    @Test
    public void asettaaRatkaisunPeliaLuotaessa() {
       m.uusiPeli(aste);
       int[][] ratkaisu = s.annaRatkaisu();
       assertEquals(9, ratkaisu.length);
       assertEquals(9, ratkaisu[0].length);
       assertEquals(0, tyhjienLkm(ratkaisu));
    }
    
    @Test
    public void tyhjentaaRuudutJaRuutulistanJosAloitetaanUusiPeli() {
       SudokugeneraattoriTestUtil u = new SudokugeneraattoriTestUtil(); 
       s.asetaRuudut(u.valmisPohja1());
       s.asetaRuutulista(u.ruutulista());
       // teoriassa on mahdollista, että generaattori loisi täsmälleen saman pelin, mutta tämä on niin epätodennäköistä, että mahdollisuus sivuutetaan.
       m.uusiPeli(aste);
       assertFalse(Arrays.deepEquals(s.annaRuudut(), u.valmisPohja1()));
       // voi arpoa saman ruutulistankin teoriassa
       assertFalse(s.annaRuutulista().equals(u.ruutulista()));
    }
    
    @Test
    public void antaaLaudanJossaOikeaRiviJaSarakemaara() {
       m.uusiPeli(aste);
       int[][] lauta = m.annaPelilauta();
       assertEquals(9, lauta.length);
       assertEquals(9, lauta[0].length);
    }
    
    @Test
    public void antaaPelin() {
       Sudokupeli p = m.uusiPeli(aste);
       assertThat(p, instanceOf(Sudokupeli.class));
    }
}
