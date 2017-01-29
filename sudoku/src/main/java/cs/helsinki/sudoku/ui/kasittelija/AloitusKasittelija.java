package cs.helsinki.sudoku.ui.kasittelija;

import cs.helsinki.sudoku.ui.nakyma.Nakymanhallinta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AloitusKasittelija implements ActionListener {

    Nakymanhallinta hallinta;

    public AloitusKasittelija(Nakymanhallinta hallinta) {
        this.hallinta = hallinta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        hallinta.naytaAloitusnaytto();
        hallinta.keskeytaAjastinPelinaytolla();
    }

}
