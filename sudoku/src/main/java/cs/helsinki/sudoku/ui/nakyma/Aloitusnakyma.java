
package cs.helsinki.sudoku.ui.nakyma;

import cs.helsinki.sudoku.ui.Kayttoliittyma;
import cs.helsinki.sudoku.ui.kasittelija.VaikeusasteKasittelija;
import cs.helsinki.sudoku.ui.kasittelija.UusiPeliKasittelija;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class Aloitusnakyma extends Nakyma {
    
    private Nakymanhallinta nakyma;
    
    public Aloitusnakyma(Kayttoliittyma kali, Nakymanhallinta nakyma) {
        super(kali);
        this.nakyma = nakyma;
    }
    
    @Override
    public void tayta() {
        sisalto.setLayout(new BorderLayout());

        JPanel content = new JPanel();
        JLabel teksti = new JLabel("Tervetuloa pelaamaan Sudokua.");
        JLabel teksti2 = new JLabel("Valitse pelin vaikeusaste ja klikkaa Aloita peli aloittaaksesi uuden Sudoku-pelin.");
        content.add(teksti);
        content.add(teksti2);

        lisaaPainikkeet(content);

        JButton uusiPeli = new JButton("Aloita peli");
        UusiPeliKasittelija kasittelija = new UusiPeliKasittelija(nakyma, kali);
        uusiPeli.addActionListener(kasittelija);

        JPanel tyhjaa = new JPanel();
        tyhjaa.setPreferredSize(new Dimension(600, 200));

        sisalto.add(tyhjaa, BorderLayout.NORTH);
        sisalto.add(content, BorderLayout.CENTER);
        sisalto.add(uusiPeli, BorderLayout.SOUTH);
    }
    
    private void lisaaPainikkeet(Container content) {
        JLabel otsikko = new JLabel("Vaikeusaste:");
        ButtonGroup painikkeet = new ButtonGroup();

        JRadioButton helppo = new JRadioButton("Helppo");
        helppo.setActionCommand("1");

        JRadioButton keskitaso = new JRadioButton("Keskitaso");
        keskitaso.setActionCommand("2");

        JRadioButton vaikea = new JRadioButton("Vaikea");
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
