package cs.helsinki.sudoku.ui.nakyma;

import cs.helsinki.sudoku.app.RuudunStatus;
import cs.helsinki.sudoku.app.Vaikeusaste;
import cs.helsinki.sudoku.ui.Ajastin;
import cs.helsinki.sudoku.ui.Kayttoliittyma;
import cs.helsinki.sudoku.ui.Kellonaytto;
import cs.helsinki.sudoku.ui.kasittelija.AloitusKasittelija;
import cs.helsinki.sudoku.ui.kasittelija.LisaaPoistaSuodatin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

/**
 * Luokka piirtää Sudoku-pelilaudan ja hallinnoi ajastinta jos se on käytössä.
 */

public class Pelinakyma extends Nakyma {

    private JTextField[][] ruudut;
    private int koko;
    private Ajastin ajastin;

    public static final Font FONTTI = new Font("Monospaced", Font.BOLD, 20);
    private final Kellonaytto kello;

    public Pelinakyma(Kayttoliittyma kali, Nakymanhallinta hallinta, int koko) {
        super(kali, hallinta);
        this.koko = koko;
        this.ruudut = new JTextField[koko][koko];
        this.kello = new Kellonaytto(kali);
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
                    ruudut[i][j].setBackground(RuudunStatus.VALMIIKSI_TAYTETTY.getVari());
                } else {
                    ruudut[i][j] = new JTextField("");
                    ruudut[i][j].setEditable(true);
                    ruudut[i][j].setBackground(RuudunStatus.TAYTETTAVA.getVari());
                }
                ruudut[i][j].setHorizontalAlignment(JTextField.CENTER);
                ruudut[i][j].setFont(FONTTI);
                PlainDocument dokumentti = (PlainDocument) ruudut[i][j].getDocument();
                dokumentti.setDocumentFilter(new LisaaPoistaSuodatin(kali, ruudut[i][j], i, j));
                merkkaaReunat(i, j);

                peli.add(ruudut[i][j]);
            }
        }
        lisaaAlapalkki();
    }

    private void lisaaAlapalkki() {
        JPanel alapalkki = new JPanel();
        alapalkki.setLayout(new FlowLayout(FlowLayout.TRAILING));
        JButton aloitus = new JButton("Keskeytä peli");
        aloitus.addActionListener(new AloitusKasittelija(hallinta));
        alapalkki.add(aloitus);
        
        if (kali.kaytaAikarajaa()) {
            JLabel teksti = new JLabel("Aikaa jäljellä: ");
            alapalkki.add(teksti);
            alapalkki.add(kello.getSisalto());
        }

        sisalto.add(alapalkki, BorderLayout.SOUTH);
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
        kentta.setBackground(status.getVari());
    }

    public void paivitaVarit(RuudunStatus[][] uudetStatukset) {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                paivitaVari(ruudut[i][j], uudetStatukset[i][j]);
            }
        }
    }

    public void kaynnistaAjastin(Vaikeusaste aste) {
        int halututMinuutit = aste.getMinuutit();
        int halututSekunnit = aste.getSekunnit();
        // pitää lisätä yksi, koska muuten laskuri alkaisi kohdasta halututSekunnit - 1.
        long loppumisaika = System.currentTimeMillis() + 1000 * (halututMinuutit * 60 + halututSekunnit + 1);
        final ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        this.ajastin = new Ajastin(loppumisaika, ses, kello);

        ajastin.ajaKunnes(ses, 1, TimeUnit.SECONDS);
    }

    public void keskeytaAjastin() {
        ajastin.keskeyta();
    }

}
