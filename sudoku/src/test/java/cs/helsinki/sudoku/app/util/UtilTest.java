
package cs.helsinki.sudoku.app.util;

import cs.helsinki.sudoku.app.SudokugeneraattoriTestUtil;
import static cs.helsinki.sudoku.util.Util.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class UtilTest {
    private final ByteArrayOutputStream sOut = new ByteArrayOutputStream();
    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(sOut));
    }
    
    @Test
    public void tulostaaOikein() {
       
        SudokugeneraattoriTestUtil u = new SudokugeneraattoriTestUtil();
        tulosta(9, u.valmisPohja1());
        String tuloste =  "-----\n" +
        "| | |3| | |2| | |6| | |\n" +
        "|9| | | |3| |5| | | |1|\n" +
        "| | |1| |8| |6| |4| | |\n" +
        "\n" +
        "| | |8| |1| |2| |9| | |\n" +
        "|7| | | | | | | | | |8|\n" +
        "| | |6| |7| |8| |2| | |\n" +
        "\n" +
        "| | |2| |6| |9| |5| | |\n" +
        "|8| | | |2| |3| | | |9|\n" +
        "| | |5| | |1| | |3| | |\n" +
        "-----\n";
        assertEquals(tuloste, sOut.toString());
    }
}
