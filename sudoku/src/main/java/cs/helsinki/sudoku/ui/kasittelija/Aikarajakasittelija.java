
package cs.helsinki.sudoku.ui.kasittelija;

import cs.helsinki.sudoku.ui.Kayttoliittyma;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Käsittelee Käytä aikarajaa -checkboxin täppäämisen päälle tai pois päältä.
 */

public class Aikarajakasittelija implements ItemListener {

    private final Kayttoliittyma kali;
    
    public Aikarajakasittelija(Kayttoliittyma kali) {
        this.kali = kali;
    }
    

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            kali.asetaKaytaAikarajaa(false);
        } else {
            kali.asetaKaytaAikarajaa(true);
        }
    }
    
}
