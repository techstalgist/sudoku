# Aiheen kuvaus

## Aihe: Sudoku
Toteutetaan sovellus, jolla käyttäjä voi pelata yksin Sudokua. Sovelluksessa voi aloitusruudulla valita halutun vaikeusasteen Sudoku-pelille ja käynnistää pelin. Sovellus osaa itse arpoa valmiin pelin annetun vaikeusasteen perusteella.

Peliä pelataan täyttämällä Sudoku-pelilaudan ruutuja numeroilla. Sovellus ohjaa pelaajaa siten, että jos pelaaja syöttää tyhjään ruutuun numeron, joka ei täytä Sudoku-pelin ehtoja, niin ruutu merkataan punaisella. Jos ruutuun syötetty numero on kelvollinen, ruutu merkataan vihreäksi.

Kun pelilauta on täytetty oikein, pelaajalle näytetään ilmoitus pelin läpäisemisestä. Pelaaja voi käynnistää uuden pelin tai lopettaa sovelluksen suoraan ilmoituksesta. Pelaajan on myös mahdollista keskeyttää käynnissä oleva peli, ja palata aloitusruudulle.

## Käyttäjät:
Pelaaja

## Toiminnot:

- uuden pelin aloittaminen aloitusruudulla (sisältäen vaikeustason valinnan, ja valmiin Sudoku-pohjan luonnin)
- pelin pelaaminen numeroja syöttämällä
  * numeroita voi syöttää vain tyhjiin ruutuihin
- pelin lopettaminen / uuden pelin aloittaminen onnistuneen pelin jälkeen
  * ilmoitus näytetään vasta kun peli on onnistuneesti läpäisty
- pelin keskeyttäminen
  * mahdollista kun peli on käynnissä
