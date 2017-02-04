
package cs.helsinki.sudoku.ui.nakyma;

import javax.swing.JOptionPane;


public class Ilmoitus {
    private String[] painikkeet;
    private String teksti;
    private String otsikko;
    private Nakymanhallinta hallinta;

    public Ilmoitus(String[] painikkeet, String teksti, String otsikko, Nakymanhallinta hallinta) {
        this.painikkeet = painikkeet;
        this.teksti = teksti;
        this.otsikko = otsikko;
        this.hallinta = hallinta;
    }
    
    public void nayta() {
        int vastaus = JOptionPane.showOptionDialog(hallinta.getFrame(), teksti, otsikko, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, painikkeet,
                painikkeet[0]);
        kasitteleVastaus(vastaus);
    }
    
    private void kasitteleVastaus(int vastaus) {
        if (vastaus == 1) {
            hallinta.naytaAloitusnaytto();
        } else {
            System.exit(0);
        }
    }
}
