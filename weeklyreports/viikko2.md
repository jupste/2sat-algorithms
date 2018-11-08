## Toinen viikko

### Koodi

Toisella viikolla olen keskittynyt varsinaisen ohjelman tekemisen aloitukseen. Olen ottanut selvää, mihin muotoon konjuktiivisen normaalimuodon (conjuctive normal form CNF) lauseet pitää muuttaa, jotta ne voidaan testata vahvoja yhdistyneitä komponentteja (strongly connected components SCC) tunnistavilla algoritmeilla. Tällä hetkellä olen koodannut Tarjanin algoritmin suorittavan luokan, jonka avulla voin tarkastaa onko jokin CNF toteutuva. 

Oma ongelmansa oli myös selvittää, miten CNF voi koodata jonkinlaiseen tietorakenteeseen. Päädyin esittämään CNF:t kokonaislukutaulukoina, jossa jokin numero edustaa tiettyä muuttujaa ja tämän luvun vastaluku vastaa tämän muuttujan negaatiota. Tästä taulukosta puolestaan muodostetaan verkko naapurilista (adjacency list) muodossa. Loin erillisen luokan hoitamaan verkkojen luonnit ja tulostukset. Tein myös BruteForce luokan, joka yrittää ratkaista SAT-ongelman raa'alla voimalla. Tämä luokka on luotu vain esiintuomaan sen, että kyseinen ongelma on erittäin vaativa ratkaistavaksi pelkällä kokeilulla. Tämän lähestymistavan aikavaatimus on eksponentiaalinen muuttujien lukumäärän suhteen, joten sitä ei voi käyttää kovin monimutkaisien CNF:ien ratkaisuun.

### Testaus ja dokumentointi

Kirjoitin Tarjanin algoritmista vastaavalle luokalle omat jUnit testit, joilla testataan osaako se ratkaista onko annetut CNF:ät ratkeavia. Testit testaavat sekä pienillä syötteillä, että valtavilla miljoonan kokoisilla syötteillä. Kirjoitin myös kattavat javadoc dokumentoinnit tähän luokkaan.

### Konfiguraatioiden teko

Tein ohjelmasta Maven-projektin ja mahdollistin jacoco-raporttien luomisen. Näin harjoitustyölle saa tehtyä rivikattavuuden näyttäviä testejä.

### Seuraavan viikon tavoitteet

Seuraavalla viikolla aion alkaa kirjoittaa Kosajarun algoritmia, jonka toimintaa vertailen Tarjanin algoritmiin. Aion myös alkaa kirjoittamaan omia versioita tietorakenteista.

