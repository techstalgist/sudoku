
package cs.helsinki.sudoku.app;


import java.awt.Color;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class RuudunStatusTest {
    
    
    @Test
    public void antaaVarin() {
       assertEquals(Color.YELLOW, RuudunStatus.TAYTETTAVA.getVari()); 
    }
}
