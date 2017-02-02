
package cs.helsinki.sudoku.app;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class VaikeusasteTest {
    
    Vaikeusaste v;
    
    @Test
    public void antaaMinuutitJaSekunnit() {
        v = Vaikeusaste.HELPPO;
        assertEquals(3, v.getMinuutit());
        assertEquals(0, v.getSekunnit());
    }
}
