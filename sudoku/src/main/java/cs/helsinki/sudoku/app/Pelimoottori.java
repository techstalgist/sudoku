package cs.helsinki.sudoku.app;

/**
 * Luokka käyttää Sudokugeneraattoria uuden Sudokupelin luontiin ja palauttaa uuden pelin käyttöliittymälle.
 * Luokka kutsuu myös Sudokupelin metodia pelilaudan arvon päivittämiseksi.
 */

public class Pelimoottori {

    private Sudokugeneraattori generaattori;
    private Sudokupeli peli;
    
    /**
    * Luo uuden Pelimoottori-olion ja asettaa sille oliomuuttujaksi annetun Sudokugeneraattorin.
    * @param generaattori Sudokugeneraattori, jonka toiminnot ovat moottorin käytössä.
    */
    
    public Pelimoottori(Sudokugeneraattori generaattori) {
        this.generaattori = generaattori;
    }
    
    /**
    * Metodi kutsuu Sudokugeneraattoria ja pyytää siltä uuden Sudokupelin annetulla vaikeusasteella.
    *
    * @param   aste   Haluttu vaikeusaste.
    * 
    */
    
    public void uusiPeli(Vaikeusaste aste) {
        generaattori.tyhjennaRuudutJaRuutulista();
        generaattori.tayta(generaattori.getRuudut(), 1, 0, false);
        generaattori.asetaPelilautaRatkaisuksi();
        generaattori.setVaikeusaste(aste);
        generaattori.tyhjennaRuutujaTaydeltaLaudalta();
        Sudokupeli uusiPeli = generaattori.annaUusiSudokupeli();
        this.peli = uusiPeli;
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
    
    public int[][] pyydaPelilauta() {
        return peli.getPelilauta();
    }

}
