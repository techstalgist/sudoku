# Käyttöohjeet

## Sovelluksen käynnistäminen

Tuplaklikkaa .jar tiedostoa tai aja komentoriviltä:
java -jar sudoku-1.0.jar

## Uuden pelin luonti

Valitse ensin haluttu vaikeusaste: Helppo, Keskitaso tai Vaikea.
Vaikeusaste vaikuttaa siihen, kuinka monta ruutua sinun täytyy täyttää Sudoku-pelilaudalla.
Valitse "Käytä aikarajaa", jos haluat lisähaastetta, koska tällöin sinun täytyy saada Sudoku-peli valmiiksi annetussa aikarajassa.

Valinnat tehtyäsi klikkaa Aloita peli.

## Pelin pelaaminen

Sudoku-pelin ideana on, että jokaiselle riville, sarakkeelle ja 9 ruudun osaruudulle pitää täyttää luvut 1-9.
Täytettävät ruudut on merkattu keltaisella. Valmiiksi täytetyt ruudut on merkattu valkoisella.

Syötä siis lukuja keltaisiin ruutuihin. Luvun syötettyäsi ruutu merkataan joko punaisella tai vihreällä.
Punainen väri tarkoittaa, että syöttämäsi luku ei sovi ko. ruutuun, eli ko. rivillä / sarakkeella / osaruudussa on jo syöttämäsi luku.
Vihreä väri tarkoittaa, että syöttämäsi luku sopii ko. ruutuun senhetkisen pelitilanteen mukaan.
Huomaa, että vihreä väri ei välttämättä tarkoita sitä, että syöttämäsi luku on oikea luku, sillä pelitilanteesta riippuen voit syöttää ruutuihin lukuja, jotka eivät ole samoja kuin oikeassa ratkaisussa, mutta täyttävät em. luvuille pätevät ehdot ko. pelitilanteessa.

Voit siis täyttää pelilaudan melkein loppuun asti vihreiksi merkatuilla luvuilla ja havaita, että et saa ratkaistua peliä. Tällöin sinun tulee tietysti tyhjentää ruutuja. Pelin ratkaisu on aina uniikki.

Pelin voi keskeyttää Keskeytä peli -painikkeesta. Tällöin pääset takaisin aloitusnäkymälle.
Jos pelissä on aikaraja, niin aikarajan umpeuduttua peli päättyy ilmoitukseen siitä, että aika loppui.
Jos saat pelin ratkaistua, niin tällöinkin näät ilmoituksen asiasta.

Kummankin ilmoituksen tapauksessa voit joko aloittaa uuden pelin tai lopettaa sovelluksen. 
