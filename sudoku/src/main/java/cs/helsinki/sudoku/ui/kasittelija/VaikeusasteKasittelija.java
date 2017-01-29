package cs.helsinki.sudoku.ui.kasittelija;

import cs.helsinki.sudoku.app.Vaikeusaste;
import cs.helsinki.sudoku.ui.Kayttoliittyma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Käsittelee vaikeusasteen muuttamisen aloitusnäytöllä.
 */

public class VaikeusasteKasittelija implements ActionListener {

    private Kayttoliittyma ui;

    public VaikeusasteKasittelija(Kayttoliittyma ui) {
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("1")) {
            ui.asetaVaikeusaste(Vaikeusaste.HELPPO);
        } else if (e.getActionCommand().equals("2")) {
            ui.asetaVaikeusaste(Vaikeusaste.KESKITASO);
        } else {
            ui.asetaVaikeusaste(Vaikeusaste.VAIKEA);
        }

    }

}
