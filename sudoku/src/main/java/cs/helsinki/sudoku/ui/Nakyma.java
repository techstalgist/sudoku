
package cs.helsinki.sudoku.ui;

import javax.swing.JPanel;


public abstract class Nakyma {
    
    protected JPanel sisalto;
    protected Kayttoliittyma kali;
    
    public Nakyma(Kayttoliittyma kali) {
        this.sisalto = new JPanel();
        this.kali = kali;
    }
    
    public JPanel annaSisalto() {
        return sisalto;
    }
    
    abstract void tayta();
    
}
