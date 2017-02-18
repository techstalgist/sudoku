# Rakennekuvaus

## Pääohjelma

Sovelluksen pääohjelma on Sudoku.java.

## app
Sovelluslogiikan oleellisimmat luokat ovat Pelimoottori, Sudokugeneraattori, Sudokupeli.
Sudokugeneraattori osaa luoda uuden Sudokupeli-pohjan annetun vaikeustason perusteella, käyttäen muutamaa apuluokkaa (esim. SopivatLuvut).
Sudokupeli -luokan instanssi taas vastaa yhtä Sudokupeliä, jolla on pelilauta, jota voi päivittää. Jokaisella pelilaudan ruudulla on myös RuudunStatus, jotta tiedetään, mitkä ruudut ovat täyttämättä jne.

Uutta peliä aloitettaessa Pelimoottori pyytää Sudokugeneraattorilta uuden pelin ja tallentaa uuden Sudokupelin oliomuuttujaansa.
Peliä pelattaessa käyttöliittymä kutsuu Pelimoottoria pelilaudan arvon päivittämiseksi. UI:n ja sovelluslogiikan yhteistyö siis toimii Pelimoottorin kautta.

## ui
Kayttoliittyma -luokka hallinnoi koko käyttöliittymää, ja kutsuu pelimoottorin metodeja. Lisäksi Kayttoliittyma kutsuu Nakymanhallinta-luokan metodeja näkymien tietojen päivittämistä varten.
Nakymanhallinta sisältää käyttöliittymän ylätason UI-komponentit, ja se kutsuu tiettyjen näkymien metodeja esim. pelinäytön täyttämistä ja näyttämistä varten.
Eri näkymille on omat luokkansa. Lisäksi on vielä omat luokkansa ajastimelle, kellonäytölle ja ilmoitukselle, koska nämä ovat tarvittavia UI-komponentteja.

Jokaiselle käyttöliittymän toiminnolle on luonnollisesti myös oma käsittelijänsä. Pelilaudan ruutujen täyttämisessä käytetään DocumentFilteriä, jotta pystytään samalla validoimaan annettu syöte. Muut käsittelijät ovat tyypiltään ActionListener tai ItemListener.
