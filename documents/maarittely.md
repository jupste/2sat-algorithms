## Määrittelydokumentti

### Aiheen kuvaus

Harjoitustyön tarkoituksena on tutkia erilaisia algoritmeja, joilla pystyy ratkaisemaan 2-SAT ongelman. 2-SAT ongelmassa yritetään selvittää, onko jollakin propositiolauseella, jossa on kahden symbolin disjunktioita yhdistetty konjuktiolla, olemassa ratkaisua. 2-SAT ongelmalle on olemassa polynomisessa ajassa toimivia ratkaisuita, mutta tästä laajemmat SAT-ongelmat on todistettu NP-täydellisiksi.

### Miksi aihe on tärkeä
Jokainen propositiologiikan lause on ekvivalentti jonkin konjuktiivisessa normaalimuodossa olevan propositiolauseen kanssa. SAT-ongelman ydin on se, että löytyykö annetulle CNF-muotoiselle propositiolauseelle ratkaisua. Näin ollen SAT-ongelman ratkaisijalla voidaan samalla selvittää jokaisen prositiolauseen kohdalla onko sillä ratkaisua. 2-SAT-ongelma on SAT-ongelman alijoukko, jolle on keksitty polynomisessa ajassa toimivia ratkaisuita. Näin ollen kaikki propositiolauseet, jossa on vain kaksipaikkaisia klausuuleita ratkeavat polynomisessa ajassa. Jos klausuuleissa on kuitenkin enemmän kuin kaksi paikkaa, ratkaisu siirtyy eksponenttiaaliseen aikavaatimukseen. SAT-ongelma onkin sen laajan käyttökelpoisuutensa vuoksi luultavasti eniten tutkittu NP-täydellinen ongelma. Mikäli siihen löytyisi polynomisessa ajassa toimiva ratkaisu, ratkaisisi se samalla P=NP ongelman.  

### Käytetyt algoritmit

Harjoitustyössä on tarkoitus käyttää Tarjanin algoritmia sekä Kosarajun algoritmia ja vertailla näiden tehokkuutta. Nämä algoritmit varsinaisesti tutkivat verkon yhtenäisiä komponentteja, mutta 2-SAT ongelma voidaan muuttaa verkkomuotoon, jolloin ratkaisu löytyy näillä algoritmeilla.

### Käytetyt tietorakenteet

Työssä käytetään ainakin ArrayList tietorakenteita, jonka lisäksi Tarjanin algoritmin voi toteuttaa myös pinoa hyväksikäyttäen.

### Aikakompleksisuus

Molemmilla algoritmeilla on teoriassa aikavaativuus O(V+E). Harjoitustyön tarkoituksena on selvittää kumpi näistä on käytännössä tehokkaampi.

### Ohjelman saamat syötteet

Ohjelma saa syötteeksi propositiologiikan lauseita, joista ohjelma päättelee onko ne toteutuvia. Lyhyemmät lauseet on luotu käsin kun taas pidemmät lauseet generoidaan omalla metodilla. 

### Lähteet
[2SAT-ongelma](https://en.wikipedia.org/wiki/2-satisfiability)

[Kosarajun algoritmi](https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm)

[Tarjan algoritmi](https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm)