package cs.helsinki.sudoku.ui.nakyma;

import cs.helsinki.sudoku.ui.Kayttoliittyma;
import cs.helsinki.sudoku.ui.kasittelija.VaikeusasteKasittelija;
import cs.helsinki.sudoku.ui.kasittelija.UusiPeliKasittelija;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Aloitusnakyma extends Nakyma {

    public Aloitusnakyma(Kayttoliittyma kali, Nakymanhallinta nakyma) {
        super(kali, nakyma);
    }

    @Override
    public void tayta() {
        sisalto.setLayout(new BorderLayout());

        JPanel content = new JPanel();
        JLabel teksti = new JLabel("Tervetuloa pelaamaan Sudokua.");
        JLabel teksti2 = new JLabel("Valitse pelin vaikeusaste ja klikkaa Aloita peli aloittaaksesi uuden Sudoku-pelin.");
        JLabel teksti3 = new JLabel("Vaikeusasteen yhteydessä on tieto siitä, kuinka kauan Sudokun ratkaisemiseen on aikaa.");
        content.add(teksti);
        content.add(teksti2);
        content.add(teksti3);

        lisaaPainikkeet(content);
        
        JPanel alapalkki = new JPanel();
        JButton uusiPeli = new JButton("Aloita peli");
        UusiPeliKasittelija kasittelija = new UusiPeliKasittelija(hallinta, kali);
        uusiPeli.addActionListener(kasittelija);
        alapalkki.add(uusiPeli);

        JPanel tyhjaa = new JPanel();
        tyhjaa.setPreferredSize(new Dimension(600, 200));

        sisalto.add(tyhjaa, BorderLayout.NORTH);
        sisalto.add(content, BorderLayout.CENTER);
        sisalto.add(alapalkki, BorderLayout.SOUTH);
    }

    private void lisaaPainikkeet(Container content) {
        JLabel otsikko = new JLabel("Vaikeusaste:");
        ButtonGroup painikkeet = new ButtonGroup();

        JRadioButton helppo = new JRadioButton("Helppo (3 min)");
        helppo.setActionCommand("1");

        JRadioButton keskitaso = new JRadioButton("Keskitaso (10 min)");
        keskitaso.setActionCommand("2");

        JRadioButton vaikea = new JRadioButton("Vaikea (25 min)");
        vaikea.setActionCommand("3");

        VaikeusasteKasittelija v = new VaikeusasteKasittelija(kali);
        helppo.addActionListener(v);
        keskitaso.addActionListener(v);
        vaikea.addActionListener(v);

        painikkeet.add(helppo);
        painikkeet.add(keskitaso);
        painikkeet.add(vaikea);

        JPanel painikkeetContainer = new JPanel();
        painikkeetContainer.add(otsikko);
        painikkeetContainer.add(helppo);
        painikkeetContainer.add(keskitaso);
        painikkeetContainer.add(vaikea);
        content.add(painikkeetContainer);
    }

}
