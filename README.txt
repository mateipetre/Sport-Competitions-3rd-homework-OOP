MATEI Alexandru-Petrut, 325CB

			=========== Competitii sportive (Design Patterns) ================
	
	
	Am folosit pentru implementarea temei 10 clase si 5 interfete. Am creeat, in primul rand, o
	ierarhie de clase pentru implementarea atat a primei cerinte - inscriere echipelor in cadrul
	competitiei sportive, cat si pentru implementarea celei de-a doua cerinte - competitia propriu
	zisa. Clasele copii FootballTeam, BasketballTeam si HandballTeam mostenesc clasa parinte 
	Team. Pentru inscrierea echipelor folosesc design pattern-urile Factory si Singleton, iar
	pentru gestionarea meciurilor si a rezultatelor folosesc atat Factory si Singleton, cat si 
	Visitor, Observer si Strategy. Folosesc Factory pentru a creea instante de tip copiii lui Team,
	Singleton pentru ca acest factory sa aiba o unica instanta, Visitor pentru a accesa scorurile
	echipelor din competitie, Observer pentru a notifica echipele de actualizarea clasamentului
	si Strategy pentru a calcula scorurile echipelor. Asadar, exista clasele/interfetele:
	
	- Team care implementeaza interfetele ScoreVisitor, Observer si Strategy si care contine:

		- campurile corespunzatoare numelui echipelor, genului echipei, numarului de jucatori
		din echipa, lista jucatorilor din echipa si clasamentul echipelor din competitie;
	
		- un constructor care creeaza instante de tip Team si initializeaza lista de jucatori

		- metode suprascrise care implementeaza design pattern-urile folosite, din interfetele
		pe care le implementeaza

		- metoda scoreOfBestPlayer care intoarce scorul celui mai bun jucator din echipa
 
		- metoda scoreOfWorstPlayer care intoarce scorul celui mai slab jucator din echipa

	- FootballTeam care mosteneste Team si implementeaza ScoreVisitor, Observer, Strategy si care
	contine: (reprezinte echipa de fotbal)

		- un constructor prin care se creeaza instante de tip FootballTeam

		- metode suprascrise care implementeaza design pattern-urile folosite, din interfetele
		pe care le implementeaza 

		- metoda suprascrisa toString pentru afisarea corespunzatoare a echipei in fisierul de
		iesire

	- BasketballTeam care mosteneste Team si implementeaza ScoreVisitor, Observer, Strategy si care
	contine: (reprezinta echipa de basket)

		- un constructor prin care se creeaza instante de tip BasketballTeam

		- metode suprascrise care implementeaza design pattern-urile folosite, din interfetele
		pe care le implementeaza

		- metoda suprascrisa toString pentru afisarea corespunzatoare a echipei in fisierul de
		iesire

	- HandballTeam care mosteneste Team si implementeaza ScoreVisitor, Observer, Strategy si care
	contine: (reprezinta echipa de handbal)

		-  un constructor  prin care se creeaza instante de tip HandballTeam

		- metode suprascrise care implementeaza design pattern-urile folosite, din interfetele
		pe care le implementeaza

		- metoda suprascrisa toString pentru afisarea corespunzatoare a echipei in fisierul de
		iesire

	- Player care reprezinta un jucator al echipelor si care contine:

		- 2 campuri pentru numele jucatorului si scorul acestuia

		- un constructor prin care se creeaza instante de tip Player

		- metoda suprascrisa toString pentru afisarea corespunzatoare a jucatorului in cadrul
		echipei in fisierul de iesire

	- TeamFactory care reprezinta factory-ul de echipe si contine:

		- un camp static ce reprezinta unica instanta de tip TeamFactory, folosita pentru Singleton

		- un constructor folosit pentru a crea instante de tip TeamFactory

		- metoda createTeam prin care se implementeaza factory-ul si, efectiv, ea intoarce
		o instanta a unuia dintre copiii clasei parinte Team

		- metoda statica getInstance care creeaza unica instanta a TeamFactory si o intoarce

	- VisitorImplementation care implementeaza Visitor; ea prezinta efectiv implementarea lui Visitor
	si anume suprascrie metoda visit pe care o contine interfata Visitor astfel incat foloseste 
	o instanta la StrategyContext pentru a returna scorul echipei calculat deci prin Strategy

		- contine un constructor folosit pentru crearea de instante

	- StrategyContext care contine un camp de tip Strategy, un constructor cu parametru de tip
	Strategy si o metoda care "executa" Strategy-ul si anume intoarce scorul calculat al echipei
	prin instanta la Strategy (campul clasei), apeland metoda din Strategy de calcul al scorului
	si anume calculateScore

	- interfata Visitor contine metoda visit cu ajutorul careia visitor-ul poate accesa scorul
	unei echipe

	- interfata ScoreVisitor contine metoda accept prin care este accesat scorul unei echipe
	de catre Visitor (ea va apela visit din Visitor la randul ei)

	- interfata Subject care contine o metoda registerObserver prin care sunt inregistrati observatorii
	, si anume echipele din competitie si o metoda notifyObservers prin care sunt notificati toti
	observatorii de actualizarea clasamentului (ea va apela la randul ei update din Observer)

	- interfata Observer care contine metoda update prin care se actualizeaza clasamentul, pe care
	fiecare echipa il va retine la fiecare schimbare

	- interfata Strategy care contine metoda calculateScore care calculeaza scorul unei echipe

	- Competition care reprezinta efectiv desfasuarea competitiei (meciuri, actualizare clasament,
	, indica rezultatul final al competitiei); ea contine:

		- campuri ca tipul competitiei, genul competitiei, lista echipelor din competitie,
		clasamentul echipelor, lista observatorilor(echipele)

		- un constructor prin care se pot creea instante de tip Competition

		- metodele registerObserver si notifyObservers din Subject suprascrise

		- metoda compete care implementeaza desfasurarea meciurilor intre echipe, actualizeaza
		clasamentul la fiecare meci disputat si notifica echipele participante de fiecare dintre
		aceste actualizari de clasament; ea va intoarce clasamentul final in urma competitiei

		- metoda sortDescendingByValue care sorteaza clasamentul (ales de tip Map) dupa scorurile
		echipelor, adica dupa valorile cheilor din map (cheile sunt numele echipelor din competitie)
		evident, sortarea se face descrescator

	- Test care contine implementarea generala a competitiilor sportive; ea contine metoda main
	in care exista un bloc de try in care se face citirea din fisiere de intrare, afisarea in fisierele
	de iesire, stocarea datelor necesare pentru inscrierea echipelor si pentru creearea competitiei,
	stocarea rezultatelor competitiei; exista si un bloc de catch pentru prinderea exceptiei aruncate
	in cazul in care exista erori la parsarea fisierelor, la citirea sau afisarea din ele, fisierele
	nu exista.
	Pentru mai multe detalii legate de implementarea fiecarei metode mai detaliat a se consulta 
	comentariile din codul temei.
	