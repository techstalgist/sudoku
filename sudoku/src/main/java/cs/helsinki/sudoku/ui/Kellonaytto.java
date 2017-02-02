
package cs.helsinki.sudoku.ui;

import static cs.helsinki.sudoku.ui.nakyma.Pelinakyma.FONTTI;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Käyttöliittymäkomponentti, joka näyttää annetut sekunnit minuutteina ja sekunteina kellonäyttönä.
 */


public class Kellonaytto {
    JLabel label;
    private final Kayttoliittyma kali;
    
    public Kellonaytto(Kayttoliittyma kali) {
        this.label = new JLabel();
        paivitaUlkoasu();
        this.kali = kali;
    }

    private void paivitaUlkoasu() {
        label.setPreferredSize(new Dimension(80, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setFont(FONTTI);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    public void asetaAika(int aika) {
        String aikaTeksti = muotoileAika(aika);
        label.setText(aikaTeksti);
        if(aika == 0) {
            kali.tarkistaPelitilanneAjanLoppuessa();
        }
    }
    
    private String muotoileAika(int aika) {
        int minuutit = aika / 60;
        String minuutitString = minuutit<10 ? "0" + String.valueOf(minuutit) : String.valueOf(minuutit);
        int sekunnit = aika % 60;
        String sekunnitString = sekunnit<10 ? "0" + String.valueOf(sekunnit) : String.valueOf(sekunnit);
        return minuutitString + ":" + sekunnitString;
    }
    
    public JLabel getSisalto() {
        return label;
    }
    
}
