## Testaus

### Testauskattavuus

Ohjelma eri algoritmien toimintaa erilaisilla syötteillä. Testejä on olemassa myös itseluoduille tietorakenteille. Kaiken kaikkiaan testien rivikattavuus on melko hyvä ja noin 80% rajoilla. Ongelmaksi muodostui hajautustaulujen yhteentörmäyksien testaaminen.

![testcoverage](https://github.com/jupste/2sat-algorithms/blob/master/documentation/testcoverage.jpg)

### Testisyötteet

Jokaista algoritmia on testattu sekä pienillä, että suurilla syötteillä. Pienet syötteet koostuvat noin kolmesta muuttujasta sekä muutamasta konjuktioparista kun taas suuret syötteet koostuvat sadasta muuttujasta ja puolesta miljoonasta konjuktioparista. Poikkeus on BruteForce-luokka, johon testejä on tehty vain pienille syötteille, sillä eksponentiaalinen aikavaativuus tekee suurista testeistä hyvin aikaavieviä.

### Manuaalinen testaus

Ohjelma mahdollistaa algoritmien vertailun tekstikäyttöliittymän kautta. Kokeissa Tarjanin ja Kosarajun algoritmit osoittautuivat melko lailla yhtä tehokkaiksi. Esimerkiksi 50 muutujan omaavan konjuktiivisen normaalimuodon lauseen ratkaiseminen vie molemmilla algoritmeilla noin 1 millisekunttia kun taas 300 muuttujan omaavan CNF:n ratkaiseminen vie 4 millisekunttia. Alun perin käytin testeissä huomattavasti isompia taulukoita, mutta tällöin huomasin että huolimatta muuttujien määrästä tulos oli noin 30 millisekunttia. Eli tällöin jotkin kiinteät kustannukset peittivät varsinaiset testit alleen. Brute force algoritmin rajat tulevat vastaan noin 25 muuttujan kohdalla. 25 muuttujan testaus vie  8480 millisekunttia ja esim. 30 muuttujan testaaminen ei enää onnistu missään järkevässä ajassa (180346 millisekuntti). 

Eli johtopäätös testaamisesta on se, että Tarjanin ja Kosarajun algoritmit ovat yhtä tehokkaita tämän ongelman ratkaisemiseen ja pelkkään raakaan voimaan perustuva ratkaisumenetelmä on hyvin epätehokas. Sekä Kosarajun että Tarjanin algoritmi toimivat polynomisessa ajassa O(|E|+|V|). Toki on syytä huomioida, että Brute Force menetelmä saattaa ratkaista hyvin nopeasti triviaalin suuren tapauksen, josta se löytää samantien sen toteuttavan totuusjakauman. Kuitenkin on hyvä muistaa että Brute Force- menetelmän todellinen aikavaatimus on O(2<sup>n</sup>).

### Testien ajaminen

Testit voi ajaa komennolla:
> mvn test

Testikattavuuden voi generoida komennolla:
> mvn test jacoco:report

Testikattavuusraportit löytyvät osoitteesta 
> 2SAT-comparison/target/site/jacoco/index.html


