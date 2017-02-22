package cs.helsinki.sudoku.ui.nakyma;

import cs.helsinki.sudoku.ui.Kayttoliittyma;
import cs.helsinki.sudoku.ui.kasittelija.Aikarajakasittelija;
import cs.helsinki.sudoku.ui.kasittelija.VaikeusasteKasittelija;
import cs.helsinki.sudoku.ui.kasittelija.UusiPeliKasittelija;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Luokka piirtää aloitusnäkymän käyttöliittymäkomponentit.
 */
public class Aloitusnakyma extends Nakyma {

    public Aloitusnakyma(Kayttoliittyma kali, Nakymanhallinta nakyma) {
        super(kali, nakyma);
    }

    @Override
    public void tayta() {
        sisalto.setLayout(new BorderLayout());

        JPanel content = new JPanel();

        JLabel teksti = new JLabel();
        teksti.setText("<html><body align=\"center\"><strong>Tervetuloa pelaamaan Sudokua!</strong><br><br>"
                + "Valitse pelin vaikeusaste ja klikkaa Aloita peli aloittaaksesi uuden Sudoku-pelin.<br>"
                + "Jos haluat lisää haastetta, niin valitse Käytä aikarajaa,<br>"
                + "jolloin sinun täytyy ratkaista Sudoku annetussa määräajassa.<br>"
                + "Pelilaudan ruutujen värikoodaus on kuvattu alla näkyvässä taulukossa.</body></html>");
        content.add(teksti);

        lisaaPainikkeet(content);
        JCheckBox valinta = new JCheckBox("Käytä aikarajaa");
        valinta.addItemListener(new Aikarajakasittelija(kali));
        content.add(valinta);
        
        JLabel varit = new JLabel();
        varit.setText("<html>\n"
                + "<head>\n"
                + "<style>\n"
                + "table, th, td {\n"
                + "    border: 1px solid black;\n"
                + "    border-collapse: collapse; \n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<table style=\"border:solid;text-align:left\">\n"
                + "  <tr>\n"
                + "    <th>Ruudun väri</th>\n"
                + "    <th>Selite</th>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td style=\"background-color:white\"></td>\n"
                + "    <td>Valmiiksi täytetty ruutu</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td style=\"background-color:yellow\"></td>\n"
                + "    <td>Täytettävä ruutu</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td style=\"background-color:#32CD32\"></td>\n"
                + "    <td>Syöttämäsi luku on yksi sopivista luvuista (muttei välttämättä oikea luku)</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td style=\"background-color:red\"></td>\n"
                + "    <td>Syöttämäsi luku ei ole yksi sopivista luvuista</td>\n"
                + "  </tr>\n"
                + "</table>\n"
                + "\n"
                + "</body>\n"
                + "</html>");
        content.add(varit);

        JPanel alapalkki = new JPanel();
        JButton uusiPeli = new JButton("Aloita peli");
        UusiPeliKasittelija kasittelija = new UusiPeliKasittelija(hallinta, kali);
        uusiPeli.addActionListener(kasittelija);
        alapalkki.add(uusiPeli);

        JPanel tyhjaa = new JPanel();
        tyhjaa.setPreferredSize(new Dimension(600, 100));

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
