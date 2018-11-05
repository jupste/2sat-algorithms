## Määrittelydokumentti

### Aiheen kuvaus

Harjoitustyön tarkoituksena on tutkia erilaisia algoritmeja, joilla pystyy ratkaisemaan 2-SAT ongelman. 2-SAT ongelmassa yritetään selvittää, onko jollakin propositiolauseella, jossa on kahden symbolin disjunktioita yhdistetty konjuktiolla, olemassa ratkaisua. 2-SAT ongelmalle on olemassa polynomisessa ajassa toimivia ratkaisuita, mutta tästä laajemmat SAT-ongelmat on todistettu NP-täydellisiksi.

### Käytetyt algoritmit

Harjoitustyössä on tarkoitus käyttää Tarjanin algoritmia sekä Kosarajun algoritmia ja vertailla näiden tehokkuutta. Nämä algoritmit varsinaisesti tutkivat verkon yhtenäisiä komponentteja, mutta 2-SAT ongelma voidaan muuttaa verkkomuotoon, jolloin ratkaisu löytyy näillä algoritmeilla.

### Käytetyt tietorakenteet

Työssä käytetään ainakin ArrayList tietorakenteita, jonka lisäksi Tarjanin algoritmin voi toteuttaa myös pinoa hyväksikäyttäen.

### Aikakompleksisuus

Molemmilla algoritmeilla on teoriassa aikavaativuus O(V+E). Harjoitustyön tarkoituksena on selvittää kumpi näistä on käytännössä tehokkaampi.

### Ohjelman saamat syötteet

Ohjelma saa syötteeksi propositiologiikan lauseita, joista ohjelma päättelee onko ne toteutuvia. Lyhyemmät lauseet on luotu käsin kun taas pidemmät lauseet generoidaan omalla metodilla. 
