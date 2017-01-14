
package cs.helsinki.sudoku.app;

import static cs.helsinki.sudoku.util.GeneraattoriUtil.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;


public class PelimoottoriTest {
    
    Pelimoottori m;
    int tyhjat;
    
    @Before
    public void setUp() {
        Sudokugeneraattori s = new Sudokugeneraattori();
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
