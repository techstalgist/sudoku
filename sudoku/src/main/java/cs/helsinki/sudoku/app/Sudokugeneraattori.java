package cs.helsinki.sudoku.app;
import static cs.helsinki.sudoku.util.GeneraattoriUtil.*;
import static cs.helsinki.sudoku.util.SopivatLuvut.*;
import static cs.helsinki.sudoku.util.Util.tulosta;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;

public class Sudokugeneraattori {
    
    private int koko;
    private int [][] ruudut;
    private int [][] ratkaisu;
    private int kutsujenRaja;
    private int kutsut;
    private ArrayList<SimpleEntry<Integer,Integer>> ruutulista;
    private int tyhjennettavanRivi;
    private int tyhjennettavanSarake;
    private int tyhjennettavanAlkupArvo;
    
    public Sudokugeneraattori (int kutsujenRaja) {
        this.koko = 9;
        this.ruudut = new int [koko] [koko];
        this.ratkaisu = new int [koko] [koko];
        this.kutsut = 0;
        this.kutsujenRaja = kutsujenRaja;
        this.ruutulista = new ArrayList<>();
        this.tyhjennettavanRivi = -1;
        this.tyhjennettavanSarake = -1;
        this.tyhjennettavanAlkupArvo = 0;
    }
    
    public Sudokugeneraattori() {
        this(Integer.MAX_VALUE);
    }

    public void asetaRuutulista(ArrayList<SimpleEntry<Integer,Integer>> lista) {
        this.ruutulista = lista;
    }
    
     
    public void asetaRatkaisu() {
        this.ratkaisu = this.ruudut;
    }
    
    public int[][] annaRatkaisu() {
        return ratkaisu;
    }
    
    public void asetaRuudut(int[][] pohja) {      
        ruudut = pohja;
    }
    
    public void tyhjennaKutsut()  {
        kutsut = 0;
    } 
        
    public int[][] annaRuudut() {
        return ruudut;
    }
          
    private void tyhjennaLauta(int[][] lauta) {
        for (int i = 0; i < koko; i++) {
            for(int j = 0; j < koko; j++) {
               lauta[i][j] = 0;  
            }
        }
    }
    
    public void tyhjennaRuudutJaRuutulista() {
        tyhjennaLauta(ruudut);
        ruutulista.clear();
    }
    
    public void tyhjennaRuutujaTaydeltaLaudalta(int tyhjienLkm) {
        if (ruutulista.isEmpty()) {
           ruutulista = annaListaRuutuja(); 
        }
        
        int[][] kloonattuLauta = kloonaa(ruudut);
        for (SimpleEntry<Integer, Integer> ruutu : ruutulista) {
            int rivi = ruutu.getKey();
            int sarake = ruutu.getValue();
            this.tyhjennettavanRivi = rivi;
            this.tyhjennettavanSarake = sarake;
            this.tyhjennettavanAlkupArvo = kloonattuLauta[rivi][sarake];
            kloonattuLauta[rivi][sarake] = 0;
            
            if (tayta(kloonattuLauta, rivi, sarake, false, true)) {
               kloonattuLauta[rivi][sarake] = tyhjennettavanAlkupArvo;
            }
            if (tyhjienLkm(kloonattuLauta)>=tyhjienLkm) {
                break;
            }
        }
        // palautetaan arvot, jotta voidaan testata myöhemmin, oliko pohja oikein tyhjennetty
        this.tyhjennettavanRivi = -1;
        this.tyhjennettavanSarake = -1;
        this.tyhjennettavanAlkupArvo = 0;
        ruudut = kloonattuLauta;
    }
    
    /*
    
    PRINCIPLE:
    0. some seed data. Notice that not all random data leads to a valid Sudoku board.
    1. check if we found a solution or a contradiction
    2. compute A = # of possible numbers for each cell
    3. choose the cell C that has lowest A
    4. assign a possible value to C
    5. do search recursively from step 1 (search should return false if not successful)
    http://norvig.com/sudoku.html 
    */ 
    
    public boolean tayta(int[][] lauta, int rivi, int sarake, boolean saaTyhjentaa, boolean vertaaRatkaisuun) {
        this.kutsut++;
        // when creating Sudokus, use a threshold of e.g. 1000
        // when solving Sudokus, use a threshold of e.g. 300000
        if (this.kutsut > this.kutsujenRaja) {
            tulosta(koko, lauta);
            if (saaTyhjentaa) {
                tyhjennaKutsut();
                tyhjennaLauta(lauta);
            } else {
                return false;
            }
        }
       
       if (onRatkaistu(lauta)) {
            if (!vertaaRatkaisuun) {
                ruudut = lauta;
                System.out.println(kutsut);
                tyhjennaKutsut();
            }
            return true;
        };
        
        ArrayList<SimpleEntry<Integer,Integer>> tyhjatRuudutJoilleEiSopiviaLukuja = annaTyhjatRuudutJoilleEiSopiviaLukuja(lauta);
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
       
    public ArrayList<Integer> ruutuJollaVahitenSopivia(int[][] lauta) {
        ArrayList<Integer> ruutuJaLkm = new ArrayList<>();
        int pieninLkm = Integer.MAX_VALUE;
        int pienimmanRivi = 0;
        int pienimmanSarake = 0;
        for(int i = 0; i < koko; i++) {
            for(int j = 0; j < koko; j++) {
                if (!tyhjaRuutu(lauta, i,j)) { continue; };
                int lkm = laskeSopivatLuvutIlmanAlkuperaista(lauta, i, j).size();
                if((lkm>0) && (lkm < pieninLkm)) {
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
    
    public ArrayList<Integer> laskeSopivatLuvutIlmanAlkuperaista(int[][] lauta, int rivi, int sarake) {
        
        ArrayList<Integer> sopivatLuvut = laskeSopivatLuvut(lauta, rivi, sarake);
      
        if (rivi == tyhjennettavanRivi && sarake == tyhjennettavanSarake) {
            sopivatLuvut.remove((Integer) tyhjennettavanAlkupArvo);
        }
        
        return sopivatLuvut;
    }
     
}
