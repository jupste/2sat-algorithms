## Testaus

### Testauskattavuus

Ohjelma eri algoritmien toimintaa erilaisilla syötteillä. Testejä on olemassa myös itseluoduille tietorakenteille. Kaiken kaikkiaan testien rivikattavuus on melko hyvä ja noin 80% rajoilla. Ongelmaksi muodostui hajautustaulujen yhteentörmäyksien testaaminen.

[!testcoverage](https://github.com/jupste/2sat-algorithms/blob/master/documents/testcoverage.jpg)

### Testisyötteet

Jokaista algoritmia on testattu sekä pienillä, että suurilla syötteillä. Pienet syötteet koostuvat noin kolmesta muuttujasta sekä muutamasta konjuktioparista kun taas suuret syötteet koostuvat sadasta muuttujasta ja puolesta miljoonasta konjuktioparista. Poikkeus on BruteForce-luokka, johon testejä on tehty vain pienille syötteille, sillä eksponentiaalinen aikavaativuus tekee suurista testeistä hyvin aikaavieviä.


