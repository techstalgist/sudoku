
package cs.helsinki.sudoku.app.util;
import static cs.helsinki.sudoku.util.GeneraattoriUtil.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GeneraattoriUtilTest {
    
    @Test
    public void laskeeOsaruudunOikein() {
        assertEquals(0, haeOsaruutu(0,0));
        assertEquals(2, haeOsaruutu(0,7));
        assertEquals(4, haeOsaruutu(3,3));
        assertEquals(6, haeOsaruutu(6,2));
        assertEquals(8, haeOsaruutu(8,8));
    }
}
