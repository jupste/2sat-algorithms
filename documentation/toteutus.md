## Toteutusdokumentti

### Ohjelman rakenne

Ohjelma koostuu kolmesta eri algoritmista, joilla voi ratkaista 2SAT-ongelmaa. Näitä algoritmeja voi käyttää ohjelman tekstikäyttöliittymän kautta. Ohjelma sisältää myös erilaisia testejä, joilla näiden algoritmien tehokkuutta voi vertailla. Näiden lisäksi ohjelmassa on omia tietorakenteita, joilla matkitaan jo Javassa olevia tietorakenteita, kuten ArrayListia.

### Saavutetut aika- ja tilavaatimukset

#### BruteForce
BruteForce- luokka luo jokaista kokeiluaa varten uuden taulukon ja käy tämän läpi. BruteForce luo jokaisen mahdollisen boolean arvoja sisältävän taulun, ennen kuin se toteaa että CNF ei toteudu millään totuusjakaumalla. Täten tämän luokan aikavaativuus on O(2<sup>n</sup>), jossa n on muuttujien määrä. Samoin tilavaativuus on O(n*2<sup>n</sup>)=O(2<sup>n</sup>), sillä jokainen läpikäynti tarvitsee uuden taulukon.

#### TarjanAlgorithm
Tarjanin algoritmiin perustuva menetelmä on huomattavasti tehokkaampi ja se pystyy ratkaisemaan 2SAT-ongelman O(|V|+|E|) ajassa, jossa |V| on muuttujien lukumäärä ja |E| on muuttujien välisten yhteyksien määrä. Algoritmin vaatii neljä verkon kokonaiskoon kokoista taulukkoa sekä verkon transpoosin, joten kokonaistilavaativuudeksi tulee O(|V|*(2+5w)), jossa w on järjestelmän sanakoko. 