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

    public Sudokugeneraattori() {
        this.koko = 9;
        this.ruudut = new int[koko][koko];
        this.ratkaisu = new int[koko][koko];
        this.ruutulista = new ArrayList<>();
        this.tyhjennettavanRivi = -1;
        this.tyhjennettavanSarake = -1;
        this.tyhjennettavanAlkupArvo = 0;
    }
    
    /**
    * Metodi asettaa annetun listan ruutulistaksi testausta varten.
    *
    * @param lista lista lukupareja muodossa rivi, sarake
    */

    public void asetaRuutulista(ArrayList<SimpleEntry<Integer, Integer>> lista) {
        this.ruutulista = lista;
    }
    
    /**
    * Metodi palauttaa ruutulistan.
    *
    * @return lista ruutuja muodossa rivi, sarake
    */

    public ArrayList<SimpleEntry<Integer, Integer>> annaRuutulista() {
        return ruutulista;
    }
    
    /**
    * Metodi asettaa ruudut -oliomuuttujan taulukon ratkaisu-oliomuuttujaan.
    *
    */

    public void asetaRatkaisu() {
        this.ratkaisu = this.ruudut;
    }
    
    /**
    * Metodi palauttaa ratkaisu-oliomuuttujan arvon.
    *  @return ratkaisu kokonaislukutaulukkona
    */

    public int[][] annaRatkaisu() {
        return ratkaisu;
    }
    
    /**
    * Metodi asettaa annetun taulukon ruudut-oliomuuttujaksi.
    * @param pohja kokonaislukutaulukko jossa valmis pelipohja
    */

    public void asetaRuudut(int[][] pohja) {
        ruudut = pohja;
    }
    
    /**
    * Metodi palauttaa senhetkisen pelilaudan.
    *  @return ruudut eli senhetkinen pelilauta kokonaislukutaulukkona
    */
    
    public int[][] annaRuudut() {
        return ruudut;
    }
    
    /**
    * Metodi asettaa vaikeusasteen Sudokugeneraattorille.
    * @param aste haluttu vaikeusaste
    *  
    */

    public void asetaVaikeusaste(Vaikeusaste aste) {
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
    * Metodi tyhjentää ruudut ja ruutulista- oliomuuttujat.
    * Tämä on tarpeen silloin jos generaattoria käyttää useaan kertaan peräkkäin pelin luontiin.
    */

    public void tyhjennaRuudutJaRuutulista() {
        tyhjennaLauta(ruudut);
        ruutulista.clear();
    }
    
    /**
    * Metodi tyhjentää vaikeusasteen mukaisen määrän ruutuja valmiiksi täytetyltä pelilaudalta.
    * Arpoo ensin satunnaisen listan ruutuja (yht 81 kpl)
    * Käy läpi listan siten, että tyhjentää ensin ko. ruudun. Sitten kutsuu tayta -metodia ja tarkistaa, onko peli mahdollista ratkaista
    * käyttämällä ko. ruudussa jotain muuta kuin alkuperäistä arvoa. Jos on, niin alkuperäinen arvo palautetaan, koska ratkaisu ei säily uniikkina.
    * Jos ei ole, niin ruutu pysyy tyhjänä.
    * Lopetetaan kun tyhjiä ruutuja on haluttu määrä.
    */

    public void tyhjennaRuutujaTaydeltaLaudalta() {
        int tyhjienLkm = vaikeusaste.annaTyhjennettavienLkm();
        if (ruutulista.isEmpty()) {
            ruutulista = annaListaRuutuja();
        }

        int[][] kloonattuLauta = kloonaa(ruudut);
        for (SimpleEntry<Integer, Integer> ruutu : ruutulista) {
            int rivi = ruutu.getKey();
            int sarake = ruutu.getValue();
            asetaTyhjennettavanTiedot(rivi, sarake, kloonattuLauta[rivi][sarake]);
            kloonattuLauta[rivi][sarake] = 0;

            if (tayta(kloonattuLauta, rivi, sarake, false, true)) {
                kloonattuLauta[rivi][sarake] = tyhjennettavanAlkupArvo;
            }
            if (tyhjienLkm(kloonattuLauta) >= tyhjienLkm) {
                break;
            }
        }
        // palautetaan arvot, jotta voidaan testata myöhemmin, oliko pohja oikein tyhjennetty
        asetaTyhjennettavanTiedot(-1, -1, 0);
        ruudut = kloonattuLauta;
    }

    private void asetaTyhjennettavanTiedot(int rivi, int sarake, int alkupArvo) {
        this.tyhjennettavanRivi = rivi;
        this.tyhjennettavanSarake = sarake;
        this.tyhjennettavanAlkupArvo = alkupArvo;
    }

    /**
    * Metodi täyttää parametrina annetun pelilaudan alkaen annetusta rivistä ja sarakkeesta.
    * @param lauta pelilauta joka halutaan täyttää
    * @param rivi rivi, jolta täyttäminen aloitetaan
    * @param sarake sarake, jolta täyttäminen aloitetaan
    * @param saaTyhjentaa ???
    * @param vertaaRatkaisuun jos ratkaisu löytyy, niin halutaanko vain saada tieto siitä että ratkaisu löytyi (true), vai halutaanko myös asettaa ratkaisu ruudut-oliomuuttujaan.
    * Toimintaperiaate:
    * PRINCIPLE:
    0. some seed data. Notice that not all random data leads to a valid Sudoku board.
    1. check if we found a solution or a contradiction
    2. compute A = # of possible numbers for each cell
    3. choose the cell C that has lowest A
    4. assign a possible value to C
    5. do search recursively from step 1 (search should return false if not successful)
    http://norvig.com/sudoku.html 
    * @return boolean arvo, joka kertoo onnistuiko laudan täyttäminen vai ei.
     */
    
    
    public boolean tayta(int[][] lauta, int rivi, int sarake, boolean saaTyhjentaa, boolean vertaaRatkaisuun) {

        if (tarkistaOnkoRatkaistu(lauta, vertaaRatkaisuun)) {
            return true;
        }

        ArrayList<SimpleEntry<Integer, Integer>> tyhjatRuudutJoilleEiSopiviaLukuja = annaTyhjatRuudutJoilleEiSopiviaLukuja(lauta);
        if (!tyhjatRuudutJoilleEiSopiviaLukuja.isEmpty()) {
            return false;
        }

        ArrayList<Integer> kokeiltavaRuutu = ruutuJollaVahitenSopivia(lauta);
        if (kokeiltavaRuutu.get(2) == Integer.MAX_VALUE) {
            // ei yhtään ruutua, jolle löytyy sopivia lukuja       
            return false;
        }
        int kokeiltavanRivi = kokeiltavaRuutu.get(0);
        int kokeiltavanSarake = kokeiltavaRuutu.get(1);

        ArrayList<Integer> sopivatLuvut = laskeSopivatLuvutIlmanAlkuperaista(lauta, kokeiltavanRivi, kokeiltavanSarake);
        Collections.shuffle(sopivatLuvut);

        for (Integer i : sopivatLuvut) {
            int[][] uusiLauta = kloonaa(lauta);
            uusiLauta[kokeiltavanRivi][kokeiltavanSarake] = i;

            if (tayta(uusiLauta, kokeiltavanRivi, kokeiltavanSarake, saaTyhjentaa, vertaaRatkaisuun)) {
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
    * 
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
                int lkm = laskeSopivatLuvutIlmanAlkuperaista(lauta, i, j).size();
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
    
    /**
    * Metodi kutsuu laskeSopivatLuvut metodia pelilaudan tietylle ruudulle ja palauttaa listan sopivia lukuja, ilman ruudun alkuperäistä arvoa.
    * @param lauta jota käytetään
    * @param rivi rivi jolle sopivat luvut halutaan laskea
    * @param sarake sarake jolle sopivat luvut halutaan laskea
    * @return lista jossa on ruutuun sopivat luvut (ilman ruudun alkuperäistä arvoa)
    */


    public ArrayList<Integer> laskeSopivatLuvutIlmanAlkuperaista(int[][] lauta, int rivi, int sarake) {

        ArrayList<Integer> sopivatLuvut = laskeSopivatLuvut(lauta, rivi, sarake);
        if (rivi == tyhjennettavanRivi && sarake == tyhjennettavanSarake) {
            sopivatLuvut.remove((Integer) tyhjennettavanAlkupArvo);
        }
        return sopivatLuvut;
    }

}
