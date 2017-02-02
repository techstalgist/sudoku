
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
       int[][] lauta = m.pyydaPelilauta();
       assertEquals(aste.getTyhjennettavienLkm(), tyhjienLkm(lauta));
    }
    
    @Test
    public void peliKesken() {
       m.uusiPeli(aste);
       assertFalse(m.peliValmis());
    }
    
    @Test
    public void asettaaRatkaisunPeliaLuotaessa() {
       m.uusiPeli(aste);
       int[][] ratkaisu = s.getRatkaisu();
       assertEquals(9, ratkaisu.length);
       assertEquals(9, ratkaisu[0].length);
       assertEquals(0, tyhjienLkm(ratkaisu));
    }
    
    @Test
    public void tyhjentaaRuudutJaRuutulistanJosAloitetaanUusiPeli() {
       SudokugeneraattoriTestUtil u = new SudokugeneraattoriTestUtil(); 
       s.setRuudut(u.valmisPohja1());
       s.setRuutulista(u.ruutulista());
       // teoriassa on mahdollista, että generaattori loisi täsmälleen saman pelin, mutta tämä on niin epätodennäköistä, että mahdollisuus sivuutetaan.
       m.uusiPeli(aste);
       assertFalse(Arrays.deepEquals(s.getRuudut(), u.valmisPohja1()));
       // voi arpoa saman ruutulistankin teoriassa
       assertFalse(s.getRuutulista().equals(u.ruutulista()));
    }
    
    @Test
    public void antaaLaudanJossaOikeaRiviJaSarakemaara() {
       m.uusiPeli(aste);
       int[][] lauta = m.pyydaPelilauta();
       assertEquals(9, lauta.length);
       assertEquals(9, lauta[0].length);
    }
    
    @Test
    public void antaaPelin() {
       Sudokupeli p = m.uusiPeli(aste);
       assertThat(p, instanceOf(Sudokupeli.class));
    }
    
    @Test
    public void paivittaaArvonLaudalla() {
       Sudokupeli p = m.uusiPeli(aste);
       int[][] lauta = m.pyydaPelilauta();
       int rivi = 0;
       int sarake = 0;
       RuudunStatus[][] statukset = new RuudunStatus[9][9];
       for (int i = 0; i < 9; i++) {
           for(int j = 0; j < 9; j++) {
               if (lauta[i][j]==0) {
                   statukset = m.paivitaArvoPelilaudalla(3, i, j);
                   rivi = i;
                   sarake = j;
                   break;
               }
           }
       }
       assertEquals(3, m.pyydaPelilauta()[rivi][sarake]);
       assertTrue(statukset[0][0] != null);
    }
}
