
package cs.helsinki.sudoku.app;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class VaikeusasteTest {
    
    Vaikeusaste v;
    
    @Test
    public void antaaMinuutitJaSekunnit() {
        v = Vaikeusaste.HELPPO;
        assertEquals(3, v.annaMinuutit());
        assertEquals(0, v.annaSekunnit());
    }
}
