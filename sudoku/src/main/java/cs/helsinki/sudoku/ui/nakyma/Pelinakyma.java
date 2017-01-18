
package cs.helsinki.sudoku.ui.nakyma;

import cs.helsinki.sudoku.app.RuudunStatus;
import cs.helsinki.sudoku.ui.Kayttoliittyma;
import cs.helsinki.sudoku.ui.kasittelija.LisaaPoistaSuodatin;
import cs.helsinki.sudoku.ui.nakyma.Nakyma;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;


public class Pelinakyma extends Nakyma {
    
    private JTextField[][] ruudut;
    private int koko;
    
    public static final Font FONTTI = new Font("Monospaced", Font.BOLD, 20);
    
    public Pelinakyma(Kayttoliittyma kali, int koko) {
        super(kali);
        this.koko = koko;
        this.ruudut = new JTextField[koko][koko];
    }
    
    @Override
    public void tayta() {
        sisalto.removeAll();
        sisalto.setLayout(new BorderLayout());
        GridLayout gl = new GridLayout(koko, koko);
        JPanel peli = new JPanel();
        peli.setLayout(gl);
        sisalto.add(peli, BorderLayout.CENTER);

        int[][] lauta = kali.pyydaPelilauta();
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                Integer luku = lauta[i][j];
                if (luku > 0) {
                    ruudut[i][j] = new JTextField(luku.toString());
                    ruudut[i][j].setEditable(false);
                    ruudut[i][j].setBackground(RuudunStatus.VALMIIKSI_TAYTETTY.annaVari());
                } else {
                    ruudut[i][j] = new JTextField("");
                    ruudut[i][j].setEditable(true);
                    ruudut[i][j].setBackground(RuudunStatus.TAYTETTAVA.annaVari());
                }
                ruudut[i][j].setHorizontalAlignment(JTextField.CENTER);
                ruudut[i][j].setFont(FONTTI);
                PlainDocument dokumentti = (PlainDocument) ruudut[i][j].getDocument();
                dokumentti.setDocumentFilter(new LisaaPoistaSuodatin(kali, ruudut[i][j], i, j));
                merkkaaReunat(i, j);

                peli.add(ruudut[i][j]);
            }
        }
    }
    private void merkkaaReunat(int i, int j) {
        int top, bottom, left, right;
        top = bottom = left = right = 1;
        if (i == 0) {
            top = 3;
        } else if (i == koko - 1) {
            bottom = 3;
        }
        if (j == koko - 1) {
            right = 3;
        }
        if (i % 3 == 0) {
            top = 3;
        }
        if (j % 3 == 0) {
            left = 3;
        }
        ruudut[i][j].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
    }
     

    public void paivitaVari(JTextField kentta, RuudunStatus status) {
        kentta.setBackground(status.annaVari());
    } 

    public void paivitaVarit(RuudunStatus[][] uudetStatukset) {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                paivitaVari(ruudut[i][j], uudetStatukset[i][j]);
            }
        }
    }

}
