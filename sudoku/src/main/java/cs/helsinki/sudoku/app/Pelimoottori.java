package cs.helsinki.sudoku.app;

/**
 * Luokka käyttää Sudokugeneraattoria uuden Sudokupelin luontiin ja palauttaa uuden pelin käyttöliittymälle.
 * Luokka kutsuu myös Sudokupelin metodia pelilaudan arvon päivittämiseksi.
 */

public class Pelimoottori {

    private Sudokugeneraattori generaattori;
    private Sudokupeli peli;
    
    public Pelimoottori(Sudokugeneraattori generaattori) {
        this.generaattori = generaattori;
    }
    
    /**
    * Metodi kutsuu Sudokugeneraattoria ja pyytää siltä uuden Sudokupelin annetulla vaikeusasteella.
    *
    * @param   aste   Haluttu vaikeusaste.
    * 
    * @return valmis Sudokupeli, jossa osa ruuduista täytetty ja osa tyhjiä. 
    */
    
    public Sudokupeli uusiPeli(Vaikeusaste aste) {
        generaattori.tyhjennaRuudutJaRuutulista();
        generaattori.tayta(generaattori.annaRuudut(), 1, 0, true, false);
        generaattori.asetaRatkaisu();
        generaattori.asetaVaikeusaste(aste);
        generaattori.tyhjennaRuutujaTaydeltaLaudalta();
        Sudokupeli uusiPeli = generaattori.annaUusiSudokupeli();
        this.peli = uusiPeli;
        return peli;
    }
    
    /**
    * Kutsuu Sudokupelin paivitaArvo metodia arvon päivittämiseksi pelilaudalla.
    *
    * @param   luku   Käyttäjän antama numero
    * @param   rivi   Rivi jolle numero syötettiin
    * @param   sarake   Sarake jolle numero syötettiin
    * @return RuudunStatus -taulukko käyttöliittymälle päivityksen jälkeen
    */
    
    public RuudunStatus[][] paivitaArvoPelilaudalla(int luku, int rivi, int sarake) {
        return peli.paivitaArvo(luku, rivi, sarake);
    }
    
    /**
    * Kutsuu Sudokupelin valmis -metodia.
    *
    * @return boolean -arvo, joka kertoo onko peli valmis vai ei.
    */
    
    public boolean peliValmis() {
        return peli.valmis();
    }
    
    /**
    * Kutsuu Sudokupelin annaPelilauta-metodia.
    *
    * @return int-taulukko jossa senhetkinen pelilauta esitetty numeroina.
    */
    
    public int[][] annaPelilauta() {
        return peli.annaPelilauta();
    }

}
