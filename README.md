# JAMB

### Zadatak 1.
Napišite program koji omogućuje igranje igre Jamb (engl. Yahtzee). Poštujte SOLID načela. Poštujte pravila OOP dizajna. Razdvojite funkcionalnost u klase. Treba postojati:
 - bacanje kockice, provjera stanja
 - igra se sa 6 kockica, omogućiti bacanje svih kockica, zaključavanje 0-6 kockica, bacanje samo otključanih kockica
 - provjeru rezultata bacanja - podržati barem 3 provjere za jamb (jamb, poker, skala)
 - pogonski program za provjeru napisane funkcionalnosti


Napisani program omogućava igranje Jamba jednom ili više igrača. Od svih vrijednosti(kategorije) koje se inače bilježe u Jambu, omogućene su JAMB, FULL, POKER, TRILING i SKALA.
S obzirom da postoje brojni načini kako se pojedine vrijednosti računaju, određeno je da se vrijednosti računaju kao:
1. JAMB = 5*(vrijednost koja se pojavljuje 5x) + 50 
2. FULL = 3*(vrijednost koja se pojavljuje 3x) + 2*(vrijednost koja se pojavljuje 2x) + 30; s obzirom da se može dogoditi situacija da postoje 2x po tri iste vrijednosti (pr. 3,3,3,2,2,2), vrijednost FULL-a računa se na način da se kao vrijednost koja se množi s 3 uzima najveća ponavljajuća vrijednost (u slučaju navedenog primjera je to 3), a ona vrijednost koja je manja množi se s 2 ( u slučaju navedenog primjera je to 2)
3. POKER = 4*(vrijednost koja se ponavlja 4x) + 40
4. TRILING = 3*(vrijednost koja se ponavlha 3x) + 20; isto vrijedi i ovdje, ukoliko imamo npr. 3,3,3,2,2,2, za vrijednost koja će se množiti s 3, uzima se najveća vrijednost (u slučaju navedenog primjera to je 3)
5. SKALA = 30 (mala skala) ili 40 (velika skala)

Igračima je omogućeno:
1. bacanje kockica (maksimalno 3 puta),
2. zaključavanje kockica (zaključava kockice te se one prilikom bacanja ne bacaju),
3. otključavanje kockica (otključava kockice kako bi se prilikom bacanja bacale),
4. provjera rezultata bacanja -> s obzirom da igračima nije važno samo radi li se o JAMB-u, FULL-u i sl., već im je zapravo važno koliko bodova mogu ostvariti, pa tako ova funkcionalnost, igraču, za odabranu kategoriju (JAMB, SKALA,...), ispisuje broj bodova koji se može ostvariti ukoliko se ta kategorija odluči zapisati u tablicu,
5. prikaz tablice (prikaz bodova i ukupnog  rezultata igrača koji traži izvođenje navedenu funkcionalnost),
6. unos bodova u tablicu (unos bodova za JAMB, FULL,...).

<p align="justify">
U pravilu Jamb igra ima brojne stupce koji se mogu popunjavati u tablici, no ovdje se radi o unosu u samo jedan stupac bez nekog obaveznog redosljeda unosa.
Što se tiče redosljeda igranja i većine pravila(3 maksimalna bacanja, obavezan unos bodova, nemogućnost prepisivanja jednom zapisanih bodova u tablici, bacanje svih kockica prilikom prvog bacanja, ...) napisani program se ne razlikuju od originalnog načina igranja. 

Nakon pokretanja programa, od korisnika se zahtjeva informacija o broju igrača koji će igrati igru, a zatim i dodjeljivanje imena pojedinom igraču kako bi se znalo kada je čiji red, ali i tko je pobijedio. Nakon izvršavanja navedenog, igra započinje.
  </p>
<p align="justify">
Kada igrač dođe na red, prvo mu se nudi samo funkcionalnost bacanje kockica (ostale funkcionalnosti nisu omogućene zato što je u prvom bacanju zabranjeno zaključavanje kockica, nema se što otključati, nema se što ni provjeriti kada kockice još nisu bačene, prikaz tablice trenutno nije važan, nema se što unijeti u tablicu). Poslije prvog bacanja, igraču se nude funkcionalnosti: bacanje kockica, zaključavanje kockica, otključavanje kockica, provjera rezultata bacanja, prikaz tablice i unos bodova u tablicu, a one su mu dostupne sve dok ne odabere funkcionalnost unesi bodove u tablicu ili ne baci kockice treći put. Ukoliko igrač baci kockice i treći put, jedine funkcionalnosti koje mu se tada nude su: provjera rezultat bacanja, prikaz tablice i unos bodova u tablicu (zato što više nema pravo bacanja kockica i zato što mora unijeti bodove u tablicu), a sve dokle igrač ne odabere funkcionalnost unos bodova u tablicu, dostupne su mu navedene funkcionalnosti. Kada igrač unese bodove u tablicu, na red dolazi slijedeći igrač ili ponovno isti igrač (ovisno igra li jedan igrač ili više) i sve se ponavlja, što znači da jedan igrač ne završava sa svojim redom dokle god ne unese bodove u tablicu (dopušteno mu je unijeti bodove u bilo kojem trenutku nakon prvog bacanja, ali je primoran nakon trećeg bacanja).
  
Igra završava onda kada svi igrači popune svoju tablicu, a pobjednik je igrač s najvećim ukupnim rezultatom.
  
Važno je napomenuti da jednom započeta funckionalnost mora biti i izvršena (npr. ukoliko se odabere funkcionalnost unos bodova u tablicu, nema dalje dokle god se ne odabere kategorija (JAMB, SKALA, ...) u koju će se bodovi zapisati).
  </p>
