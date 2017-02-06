package cs.helsinki.sudoku.app;
import static cs.helsinki.sudoku.util.GeneraattoriUtil.*;
import static cs.helsinki.sudoku.util.SopivatLuvut.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Luokkaa käyttäen generoidaan uusi Sudoku-peli täyttämällä tyhjä lauta satunnaisilla Sudoku-pelin säännöt täyttävillä luvuilla, 
 * ja tyhjentämällä haluttu määrä ruutuja valmiiksi täytetyltä laudalta.
 */

public class Sudokugeneraattori {
    private int koko;
    private int[][] ruudut;
    private int[][] ratkaisu;
    private ArrayList<SimpleEntry<Integer, Integer>> ruutulista;
    private int tyhjennettavanRivi;
    private int tyhjennettavanSarake;
    private int tyhjennettavanAlkupArvo;
    private Vaikeusaste vaikeusaste;

    /**
    * Luo uuden Sudokugeneraattori -olion, ja sille tyhjän pelilaudan, ratkaisun ja ruutulistan.
    * Pelilaudan koko on vakio 9x9. Asettaa myös alkuarvoja ruutujen tyhjentämisessä tarvittaville muuttujille.
    */    
    public Sudokugeneraattori() {
        this.koko = 9;
        this.ruudut = new int[koko][koko];
        this.ratkaisu = new int[koko][koko];
        this.ruutulista = new ArrayList<>();
        this.tyhjennettavanRivi = -1;
        this.tyhjennettavanSarake = -1;
        this.tyhjennettavanAlkupArvo = 0;
    }

    public void setRuutulista(ArrayList<SimpleEntry<Integer, Integer>> lista) {
        this.ruutulista = lista;
    }

    public ArrayList<SimpleEntry<Integer, Integer>> getRuutulista() {
        return ruutulista;
    }
    
    /**
    * Asettaa valmiiksi täytetyn pelilaudan ratkaisuksi.
    */
    public void asetaPelilautaRatkaisuksi() {
        this.ratkaisu = this.ruudut;
    }

    public int[][] getRatkaisu() {
        return ratkaisu;
    }

    public void setRuudut(int[][] pohja) {
        ruudut = pohja;
    }

    public int[][] getRuudut() {
        return ruudut;
    }

    public void setVaikeusaste(Vaikeusaste aste) {
        this.vaikeusaste = aste;
    }
    
    /**
    * Metodi palauttaa uuden Sudokupelin.
    *  @return Sudokupeli, jossa pelilauta, ratkaisu ja vaikeusaste ovat samat kuin ko. oliomuuttujien senhetkiset arvot
    */
    public Sudokupeli annaUusiSudokupeli() {
        return new Sudokupeli(ruudut, ratkaisu, vaikeusaste);
    }

    private void tyhjennaLauta(int[][] lauta) {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                lauta[i][j] = 0;
            }
        }
    }
    
    /**
    * Metodi tyhjentää ruudut ja ruutulista- oliomuuttujat. Tämä on tarpeen silloin jos generaattoria käyttää useaan kertaan peräkkäin pelin luontiin.
    */
    public void tyhjennaRuudutJaRuutulista() {
        tyhjennaLauta(ruudut);
        ruutulista.clear();
    }
    
    /**
    * Metodi tyhjentää vaikeusasteen mukaisen määrän ruutuja valmiiksi täytetyltä pelilaudalta.
    * Arpoo ensin satunnaisen listan ruutuja (yht 81 kpl). Käy läpi listan siten, että tyhjentää ensin ko. ruudun. Sitten kutsuu tayta -metodia ja tarkistaa, onko peli mahdollista ratkaista.
    * käyttämällä ko. ruudussa jotain muuta kuin alkuperäistä arvoa. Jos on, niin alkuperäinen arvo palautetaan, koska ratkaisu ei säily uniikkina. Jos ei ole, niin ruutu pysyy tyhjänä.
    * Lopetetaan kun tyhjiä ruutuja on haluttu määrä.
    */
    public void tyhjennaRuutujaTaydeltaLaudalta() {
        int tyhjienLkm = vaikeusaste.getTyhjennettavienLkm();
        if (ruutulista.isEmpty()) {
            ruutulista = annaListaRuutuja();
        }
        int[][] kloonattuLauta = kloonaa(ruudut);
        for (SimpleEntry<Integer, Integer> ruutu : ruutulista) {
            int rivi = ruutu.getKey();
            int sarake = ruutu.getValue();
            asetaTyhjennettavanTiedot(rivi, sarake, kloonattuLauta[rivi][sarake]);
            kloonattuLauta[rivi][sarake] = 0;
            if (tayta(kloonattuLauta, rivi, sarake, true)) {
                kloonattuLauta[rivi][sarake] = tyhjennettavanAlkupArvo;
            }
            if (tyhjienLkm(kloonattuLauta) >= tyhjienLkm) {
                break;
            }
        }
        asetaTyhjennettavanTiedot(-1, -1, 0); // palautetaan arvot, jotta voidaan testata myöhemmin, oliko pohja oikein tyhjennetty
        ruudut = kloonattuLauta;
    }

    private void asetaTyhjennettavanTiedot(int rivi, int sarake, int alkupArvo) {
        this.tyhjennettavanRivi = rivi;
        this.tyhjennettavanSarake = sarake;
        this.tyhjennettavanAlkupArvo = alkupArvo;
    }

    /**
    * Metodi täyttää parametrina annetun pelilaudan alkaen annetusta rivistä ja sarakkeesta. Toimintaperiaate: http://norvig.com/sudoku.html 
    * @param lauta pelilauta joka halutaan täyttää
    * @param rivi rivi, jolta täyttäminen aloitetaan
    * @param sarake sarake, jolta täyttäminen aloitetaan
    * @param vertaaRatkaisuun jos ratkaisu löytyy, niin halutaanko vain saada tieto siitä että ratkaisu löytyi (true), vai halutaanko myös asettaa ratkaisu ruudut-oliomuuttujaan.
    * @return boolean arvo, joka kertoo onnistuiko laudan täyttäminen vai ei.
    */    
    public boolean tayta(int[][] lauta, int rivi, int sarake, boolean vertaaRatkaisuun) {

        if (tarkistaOnkoRatkaistu(lauta, vertaaRatkaisuun)) {
            return true;
        }
        ArrayList<SimpleEntry<Integer, Integer>> tyhjatRuudutJoilleEiSopiviaLukuja = annaTyhjatRuudutJoilleEiSopiviaLukuja(lauta);
        if (!tyhjatRuudutJoilleEiSopiviaLukuja.isEmpty()) {
            return false;
        }
        ArrayList<Integer> kokeiltavaRuutu = ruutuJollaVahitenSopivia(lauta);
        if (kokeiltavaRuutu.get(2) == Integer.MAX_VALUE) {
            return false; // ei yhtään ruutua, jolle löytyy sopivia lukuja 
        }
        int kokeiltavanRivi = kokeiltavaRuutu.get(0);
        int kokeiltavanSarake = kokeiltavaRuutu.get(1);
        ArrayList<Integer> sopivatLuvut = laskeSopivatLuvutIlmanAlkuperaista(lauta, kokeiltavanRivi, kokeiltavanSarake, tyhjennettavanRivi, tyhjennettavanSarake, tyhjennettavanAlkupArvo);
        Collections.shuffle(sopivatLuvut);
        for (Integer i : sopivatLuvut) {
            int[][] uusiLauta = kloonaa(lauta);
            uusiLauta[kokeiltavanRivi][kokeiltavanSarake] = i;
            if (tayta(uusiLauta, kokeiltavanRivi, kokeiltavanSarake, vertaaRatkaisuun)) {
                return true;
            }
        }
        return false;
    }

    private boolean tarkistaOnkoRatkaistu(int[][] lauta, boolean vertaaRatkaisuun) {
        if (onRatkaistu(lauta)) {
            if (!vertaaRatkaisuun) {
                ruudut = lauta;
            }
            return true;
        }
        return false;
    }
    
    /**
    * Metodi palauttaa annetulle pelilaudalle ruudun, jolla on vähiten sopivia lukuja.
    * @param lauta pelilauta kokonaislukutaulukkona
    * @return ArrayList, jossa ekana ruudun rivi, tokana sarake, ja kolmantena sopivien lukujen lkm.
    */

    public ArrayList<Integer> ruutuJollaVahitenSopivia(int[][] lauta) {
        ArrayList<Integer> ruutuJaLkm = new ArrayList<>();
        int pieninLkm = Integer.MAX_VALUE;
        int pienimmanRivi = 0;
        int pienimmanSarake = 0;
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                if (!tyhjaRuutu(lauta, i, j)) {
                    continue;
                }
                int lkm = laskeSopivatLuvutIlmanAlkuperaista(lauta, i, j, tyhjennettavanRivi, tyhjennettavanSarake, tyhjennettavanAlkupArvo).size();
                if ((lkm > 0) && (lkm < pieninLkm)) {
                    pieninLkm = lkm;
                    pienimmanRivi = i;
                    pienimmanSarake = j;
                }
            }
        }
        ruutuJaLkm.add(pienimmanRivi);
        ruutuJaLkm.add(pienimmanSarake);
        ruutuJaLkm.add(pieninLkm);
        return ruutuJaLkm;
    }
}