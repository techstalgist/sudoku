package cs.helsinki.sudoku.ui.kasittelija;
import cs.helsinki.sudoku.ui.Kayttoliittyma;
import cs.helsinki.sudoku.ui.nakyma.Nakymanhallinta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UusiPeliKasittelija implements ActionListener {

    private Kayttoliittyma ui;
    private Nakymanhallinta hallinta;

    public UusiPeliKasittelija(Nakymanhallinta hallinta, Kayttoliittyma ui) {
        this.hallinta = hallinta;
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ui.pyydaUusiPeli();
        hallinta.taytaPelinaytto();
        hallinta.naytaPelinaytto();
        hallinta.kaynnistaAjastinPelinaytolla();
        
    }

}
