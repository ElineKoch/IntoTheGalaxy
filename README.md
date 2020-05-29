# OOPD Processing Game Engine (OOPG) [![Build Status](https://travis-ci.org/HANICA/oopg.svg?branch=master)](https://travis-ci.org/HANICA/oopg) [![Coverage Status](https://coveralls.io/repos/github/HANICA/oopg/badge.svg?branch=master)](https://coveralls.io/github/HANICA/oopg?branch=master)
In deze repository staat de game-engine die studenten moeten gebruiken voor het beroepsproduct van OOPD (de game). De engine werd in 2014-2015 ontwikkeld door een aantal OOSE-studenten en wordt momenteel doorontwikkeld door docenten en studenten van ICA.

## Hoe ga je lokaal aan de slag met de game engine in Eclipse?
Kijk [hier](up-and-running.md).

## Tutorials & API
In de [wiki](https://github.com/HANICA/oopg/wiki) staan tutorials over alle belangrijke functionaliteiten uit de game engine.
De programmeurs API van deze game engine is hier beschikbaar: [API OOPG](http://hanica.github.io/oopg/).

## Oorspronkelijke ontwikkelaars
De engine werd gebaseerd op de oude [Android-engine](https://github.com/ddoa/game-api-android) die ontwikkeld werd onder begeleiding van (en door) Paul Bergervoet.

De eerste versie van huidige engine werd gebouwd door de volgende OOSE-studenten (opdrachtgever was Ralph Niels en de groep werd begeleid door Rody Middelkoop):

* Bram Heijmink
* Jeffrey Haen
* Joost Elshof
* Kenny Ligthart
* Mark Vaesen
* Nico Smolders

## Tip: How to cite + Processing 2 Reference
Goed om deze game engine in je FO of TO alvast te refereren! Goede oefening met APA, dat moet dan als volgt:
- Heijmink, B. at al. 2014. *OOPD Processing Game Engine (OOPG)* Geraadpleegd dd-mm-jjjj op https://github.com/HANICA/oopg

## Processing 2 Reference
Verder is deze game engine gebaseerd op Processing 2, dus NIET versie 3 zoals in de course gebruikt. Van deze dependency merk je weinig, laat Eclipse of IntelliJ dit voor je oplossen via importeren op basis van Maven (de `pom.xml`). En gebruik dan vooral de OOPG functionaliteit voor je eigen game, niet direct processing.

Ga dus vooral NIET zelf Processing ook importeren zoals je tijdens de Course deed. Mocht je echter wel direct Processing functies willen gebruiken, om simpele zaken te tekenen (bv. `g.ellipse(...)` dan hier een link naar een (mirror van de oude) Processing reference:
https://cs.brynmawr.edu/Courses/cs110/fall2015dc/processing2.2.1Reference/

Voor grootste deel gelijk aan [Processing 3 reference](https://processing.org/reference/), die je kent maar er zijn enkele kleine verschillen in de API (verder verschil vooral performance).
