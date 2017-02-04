package cs.helsinki.sudoku.ui.nakyma;

import cs.helsinki.sudoku.app.RuudunStatus;
import cs.helsinki.sudoku.ui.Kayttoliittyma;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Luokka vastaa näkymien luonnista, näytettävän näkymän vaihtamisesta, sekä Valmis- ja Aika loppui- ilmoitusten näyttämisestä.
 */

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
    
    public JFrame getFrame() {
        return frame;
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
        pelinaytto = new Pelinakyma(kali, this, 9);
        cl = new CardLayout();
        panelContainer.setLayout(cl);

        aloitus.tayta();

        panelContainer.add(pelinaytto.getSisalto(), "peli");
        panelContainer.add(aloitus.getSisalto(), "aloitus");

        cl.show(panelContainer, "aloitus");
        frame.add(panelContainer);
    }

    public void naytaValmisIlmoitus() {
        keskeytaAjastinPelinaytolla();
        String[] painikkeet = {"Lopeta", "Uusi peli"};
        String teksti = "Täytit Sudokun oikein. Onneksi olkoon!\nVoit joko aloittaa uuden pelin tai lopettaa sovelluksen.";
        String otsikko = "Sudoku täytetty oikein!";
        Ilmoitus valmisIlmoitus = new Ilmoitus(painikkeet, teksti, otsikko, this);
        valmisIlmoitus.nayta();
    }
    
    public void naytaAikaLoppuiIlmoitus() {
        String[] painikkeet = {"Lopeta", "Uusi peli"};
        String teksti = "Et saanut täytettyä Sudokua määräajassa. Voit joko aloittaa uuden pelin tai lopettaa sovelluksen.";
        String otsikko = "Aika loppui kesken.";
        Ilmoitus aikaLoppuiIlmoitus = new Ilmoitus(painikkeet, teksti, otsikko, this);
        aikaLoppuiIlmoitus.nayta();
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

    public void kaynnistaAjastinPelinaytolla() {
        if (kali.kaytaAikarajaa()) {
            pelinaytto.kaynnistaAjastin(kali.getVaikeusaste());
        }
    }

    public void keskeytaAjastinPelinaytolla() {
        if (kali.kaytaAikarajaa()) {
            pelinaytto.keskeytaAjastin();
        }
    }    
}
