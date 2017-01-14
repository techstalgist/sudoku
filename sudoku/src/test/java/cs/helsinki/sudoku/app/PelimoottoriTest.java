
package cs.helsinki.sudoku.app;

import static cs.helsinki.sudoku.util.GeneraattoriUtil.*;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class PelimoottoriTest {
    Sudokugeneraattori s;
    Pelimoottori m;
    int tyhjat;
    
    @Before
    public void setUp() {
        s = new Sudokugeneraattori();
        m = new Pelimoottori(s);
        tyhjat = 40;
        m.asetaTyhjennettavienLkm(tyhjat);
    }
    
    @Test
    public void luoPelinJossaOikeaMaaraTyhjia() {
       m.luoPeli();
       int[][] lauta = m.annaPelilauta();
       assertEquals(tyhjat, tyhjienLkm(lauta));
    }
    
    @Test
    public void asettaaRatkaisunPeliaLuotaessa() {
       m.luoPeli();
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
       m.luoPeli(); 
       assertFalse(Arrays.deepEquals(s.annaRuudut(), u.valmisPohja1()));
       // voi arpoa saman ruutulistankin teoriassa
       assertFalse(s.annaRuutulista().equals(u.ruutulista()));
    }
    
    @Test
    public void antaaLaudanJossaOikeaRiviJaSarakemaara() {
       m.luoPeli();
       int[][] lauta = m.annaPelilauta();
       assertEquals(9, lauta.length);
       assertEquals(9, lauta[0].length);
    }
    
    @Test
    public void antaaPelin() {
       m.luoPeli();
       Sudokupeli p = m.annaPeli();
       assertThat(p, instanceOf(Sudokupeli.class));
    }
}
