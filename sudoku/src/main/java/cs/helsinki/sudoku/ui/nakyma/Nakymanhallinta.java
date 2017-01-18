package cs.helsinki.sudoku.ui.nakyma;

import cs.helsinki.sudoku.app.RuudunStatus;
import cs.helsinki.sudoku.ui.Kayttoliittyma;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Nakymanhallinta {
    private Kayttoliittyma kali;
    private JFrame frame;
    private JPanel panelContainer;
    private CardLayout cl;
    private Aloitusnakyma aloitus;
    private Pelinakyma pelinaytto;
    
    public Nakymanhallinta(Kayttoliittyma kali) {
        this.kali = kali;
    }
    
    public void init() {
        frame = new JFrame("Sudoku");
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit();
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit() {
        panelContainer = new JPanel();
        aloitus = new Aloitusnakyma(kali, this);
        pelinaytto = new Pelinakyma(kali, 9);
        cl = new CardLayout();
        panelContainer.setLayout(cl);

        aloitus.tayta();

        panelContainer.add(pelinaytto.annaSisalto(), "peli");
        panelContainer.add(aloitus.annaSisalto(), "aloitus");

        cl.show(panelContainer, "aloitus");
        frame.add(panelContainer);
    } 
    
    public void naytaValmisIlmoitus() {

        String[] painikkeet = {"Lopeta", "Uusi peli"};
        int vastaus = JOptionPane.showOptionDialog(frame, "Täytit Sudokun oikein. Onneksi olkoon!\nVoit joko aloittaa uuden pelin tai lopettaa sovelluksen.",
                "Sudoku täytetty oikein!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, painikkeet,
                painikkeet[0]);

        if (vastaus == 1) {
            naytaAloitusnaytto();
        } else {
            System.exit(0);
        }

    }

    public void naytaPelinaytto() {
        cl.show(panelContainer, "peli");
    }
    
    public void taytaPelinaytto() {
        pelinaytto.tayta();
    }
    
    public void naytaAloitusnaytto() {
        cl.show(panelContainer, "aloitus");
    }

    public void paivitaPelinaytonVarit(RuudunStatus[][] uudetStatukset) {
        pelinaytto.paivitaVarit(uudetStatukset);
    }
}
