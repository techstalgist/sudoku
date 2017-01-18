package cs.helsinki.sudoku.ui.kasittelija;

import cs.helsinki.sudoku.ui.nakyma.Nakymanhallinta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AloitusKasittelija implements ActionListener {

    Nakymanhallinta nakyma;

    public AloitusKasittelija(Nakymanhallinta nakyma) {
        this.nakyma = nakyma;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nakyma.naytaAloitusnaytto();
    }

}
