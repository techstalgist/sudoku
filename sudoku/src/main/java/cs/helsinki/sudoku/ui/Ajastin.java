package cs.helsinki.sudoku.ui;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import static java.lang.Math.toIntExact;

/**
 * Ajastin joka suorittaa halutun käskyn sekunnin välein tiettyyn päättymisaikaan asti.
 */

public class Ajastin implements Runnable {

    private volatile ScheduledFuture<?> self;
    private long paattymisaika;
    private ScheduledExecutorService ses;
    private Kellonaytto kello;

    public Ajastin(long paattymisaika, ScheduledExecutorService ses, Kellonaytto kello) {
        this.paattymisaika = paattymisaika;
        this.ses = ses;
        this.kello = kello;
    }

    @Override
    public void run() {
        long aikaNyt = System.currentTimeMillis();
        long diff = (paattymisaika - aikaNyt);
        
        // häkkäys jolla varmistutaan siitä, että alussa ajastin alkaa aina nollannesta sekunnista
        // jos nimittäin ensimmäisen ja toisen sekunnin välissä on >1000 millisekuntia, niin käy niin että yksi sekunti hypätään yli
        if (diff % 1000 < 50) {
            diff -= 51;
        }
        if (diff > 1000) {
            int erotus = toIntExact(diff/1000);
            kello.asetaAika(erotus);
        } else {
            kello.asetaAika(0);
            keskeyta();
        }
    }
    
    public void keskeyta() {
        self.cancel(false);
        ses.shutdown();
    }

    public void ajaKunnes(ScheduledExecutorService executor, long period, TimeUnit unit) {
        self = executor.scheduleAtFixedRate(this, 0, period, unit);
    }
}
