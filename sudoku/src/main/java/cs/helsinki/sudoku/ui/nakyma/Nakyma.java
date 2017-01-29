
package cs.helsinki.sudoku.ui.nakyma;

import cs.helsinki.sudoku.ui.Kayttoliittyma;
import javax.swing.JPanel;

/**
 * Yleiset näkymän tarvitsemät attribuutit ja metodit.
 */

public abstract class Nakyma {
    
    protected JPanel sisalto;
    protected Kayttoliittyma kali;
    protected Nakymanhallinta hallinta;
    
    public Nakyma(Kayttoliittyma kali, Nakymanhallinta hallinta) {
        this.sisalto = new JPanel();
        this.kali = kali;
        this.hallinta = hallinta;
    }
    
    public JPanel annaSisalto() {
        return sisalto;
    }
    
    abstract void tayta();
    
}
