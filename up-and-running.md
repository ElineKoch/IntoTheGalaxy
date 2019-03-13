# Up and running
> Hoe kun je lokaal aan de slag in Eclipse met je eigen game?
Met deze simpele 1-2-3... ;)

### 0. Download git
Download en installeer **git** als je deze nog niet hebt. Stap 1 gaat uit van gebruik van git voor command line (cli). Deze cli is ook onderdeel van [Github Desktop](https://desktop.github.com/) desktop applicatie (voor Windows, linux of macOS). Of installeer enkel de cli via [git-scm.com](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).

### 1. Clone deze repo naar een (logische) lokale folder.
Handigst is om hier direct de naam te kiezen i.v.m. toekomstige upload in iSAS (kies dus een naam voor je spel; e.g. NIET `nieuw-spel`!)

```
git clone https://github.com/HANICA/oopg.git oopg-nieuw-spel
```

NB Hiervoor moet je [git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) gedowload en -installeerd hebben. Je kunt ook soortgelijk doen via een grafische git UI als GitHub for Windows of TreeSource. Of je kunt de zip zelf downloaden uit Github en uitpakken. Graag wel de root foldernaam aanpassen.
<img width="500" alt="Clone repo of download zip" src="https://user-images.githubusercontent.com/3029472/40170033-c4ce6218-59c7-11e8-95a9-1d125786ea92.png">


### 2. Importeer folder in (nieuw) Eclipse project
Als je de code lokaal hebt staan moet je deze nog importeren in Eclipse zodat je er hier mee kunt werken.
Je kunt dit ook in IntelliJ doen als je dit een fijnere IDE vindt. Dit gaat soortgelijk, we beschrijven hier alleen Eclipse.

Kies menu `File` -> `Import...` en dan `Projects from folder or archive...`
<img width="700" alt="Importeer game in Eclipse" src="https://user-images.githubusercontent.com/3029472/40170032-c4b53f40-59c7-11e8-853e-382ea1d9549d.png">

In de `Import project...` dialoog blader naar de folder aangemaakt bij stap 1
<img width="700" alt="Project import dialoog" src="https://user-images.githubusercontent.com/3029472/40170031-c49d996c-59c7-11e8-922c-4e4c0ccf9815.png">

### 3. Maak een nieuwe game
In de wiki van deze repository staat een tutorial. Begin daar voor je eerste stappen in deze game engine. [Link naar tutorial](https://github.com/HANICA/oopg/wiki/01-Hello-Game-World)

### 4. Run / bekijk de voorbeeld game
De voorbeeld game van OOPG heet: WaterWorld. Deze kan je hier vinden: [WaterWorld repository](https://github.com/HANICA/waterworld). Deze folder moet je op dezelfde manier clonen/downloaden en importeren in Eclipse zoals de OOPG. Eclipse gaat eerst zelf op zoek naar alle dependencies (andere software of libraries die nodig is om het spel te draaien). Dus het kan even duren, maar daarna kan je [WaterWorld.java](https://github.com/HANICA/waterworld/blob/master/src/main/java/nl/han/ica/oopd/waterworld/WaterWorld.java) draaien. Verdere uitleg staat ook op de waterworld repository pagina of in de tutorial. 

<img width="700" alt="Voorbeeld game, Run as Java Application" src="https://user-images.githubusercontent.com/3029472/40170444-c5e53f7c-59c8-11e8-82cc-cdc5c4495608.png">

### Werkt het niet?
Check of je al bovenstaande stappen goed hebt doorlopen. Pas aan of begin evt. opnieuw. RTM! Vraag anders een collega student of de docent.
