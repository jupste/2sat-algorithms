### Ohjelman käynnistäminen

Lataa ohjelma omalle koneelle githubista joko ylhällä näkyvän "Clone repository"-näppäimen kautta tai kirjoittamalla konsoliin

- git clone git@github.com:jupste/2sat-algorithms.git

Tämän jälkeen siirrytään projektikansion juureen eli 2SAT-algorithms/2SAT-comparison

Ohjelman saa käynnistettyä komennolla
- mvn exec:java

Ohjelman testit saa ajettua komennolla 
- mvn test jacoco:report

Testin Jacoco raportit löytyvät osoitteesta 2SAT-comparison/target/site/jacoco/index.html