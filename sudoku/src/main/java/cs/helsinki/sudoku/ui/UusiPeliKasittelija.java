package cs.helsinki.sudoku.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UusiPeliKasittelija implements ActionListener {

    private Kayttoliittyma ui;
    private Nakymanhallinta nakyma;

    public UusiPeliKasittelija(Nakymanhallinta nakyma, Kayttoliittyma ui) {
        this.nakyma = nakyma;
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ui.pyydaUusiPeli();
        nakyma.taytaPelinaytto();
        nakyma.naytaPelinaytto();

    }

}
