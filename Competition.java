import java.util.*;

/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public class Competition implements Subject{

	public String type; //tipul competitiei
	public String gender; //genul competitiei
	public ArrayList<Team> teams; //lista echipelor care sunt in competitie
	public Map<String, Integer> clasament; //clasamentul echipelor (cheia e numele echipei asociata valorii adica scorului)
	public ArrayList<Observer> observerList; //lista observatorilor (echipele in acest caz)
	/**
	 * Constructor prin care se pot creea instante de tip Competition 
	 * @param type tipul competitiei
	 * @param gender genul competitiei
	 * @param teams lista echipelor care sunt in competitie
	 * @param clasament clasamentul echipelor
	 */
	public Competition(String type, String gender, ArrayList<Team> teams, Map<String, Integer> clasament) {
		
		this.type = type;
		this.gender = gender;
		this.teams = teams;
		this.clasament = clasament;
		observerList = new ArrayList<Observer>(); //initializez lista observatorilor la apelul constructorului Competition
	}
	/**
	 * Metoda suprascrisa din Subject care adauga la lista de observatori un observator nou
	 */
	 @Override
	 public void registerObserver(Observer o) { 
		 
	       observerList.add(o); 
	 } 
	  /**
	   * Metoda suprascrisa din Subject care parcurge fiecare observator si il notifica de actualizarea clasamentului
	   */
	 @Override
	 public void notifyObservers()  { 
		 
	    for (Iterator<Observer> it = observerList.iterator(); it.hasNext();) { 
	    	
	          Observer o = it.next(); //parcurg fiecare observator din lista cu un iterator
	          o.update(clasament); 
	    } 
	 } 
	 /**
	  * Metoda care reprezinta competitia dintre echipe in sine - actualizeaza clasamentul la fiecare meci 
	  * si notifica echipele de acest lucru
	  * @return returneaza in urma tuturor meciurilor clasamentul final al competitiei
	  */
	 public Map<String, Integer> compete() {
		 
		 Visitor teamVisitor = new VisitorImplementation(); //am nevoie de un visitor care sa acceseze scorul echipelor
		 													//scor calculat cu Strategy, accesul fiind implementat in VisitorImplementation
		
		 for(int i = 0; i < this.teams.size() - 1; i++) //parcurg echipele ca in cazul unei sortari
			 for(int j = i + 1; j < this.teams.size(); j++) //asa fiecare echipa va avea un meci cu celelalte echipe
				 if(this.teams.get(i).accept(teamVisitor) > this.teams.get(j).accept(teamVisitor)) { //compara scorurile echipelor accesate cu visitor
		
				     int newScore = this.clasament.get(this.teams.get(i).teamName) + 3; //retin noul scor al echipei castigatoare din meci
					 this.clasament.remove(this.teams.get(i).teamName); //sterg din clasament echipa al carui scor s-a actualizat
					 this.clasament.put(this.teams.get(i).teamName, newScore); //adaug din nou in clasament echipa cu scorul actualizat acum
					 this.clasament = sortDescendingByValue(this.clasament); //scorul se actualizeaza, se face sortare descrescatoare dupa valori adica scoruri
					 notifyObservers(); //notific toate echipele din competitie de actualizarea clasamentului
				 }
				 else
					 if(this.teams.get(i).accept(teamVisitor) < this.teams.get(j).accept(teamVisitor)) {
						
						int newScore = this.clasament.get(this.teams.get(j).teamName) + 3; //analog mai sus
						this.clasament.remove(this.teams.get(j).teamName);
						this.clasament.put(this.teams.get(j).teamName, newScore);
						this.clasament = sortDescendingByValue(this.clasament);
						notifyObservers();
					 }
			     else if(this.teams.get(i).accept(teamVisitor) == this.teams.get(j).accept(teamVisitor)) {
						 
			    	 	 //in caz de egalitate se retin scorurile ambelor echipe la care se adauga 1 punct
			    	     int newScore1 = this.clasament.get(this.teams.get(i).teamName) + 1; 
			    	     int newScore2 = this.clasament.get(this.teams.get(j).teamName) + 1;
						 this.clasament.remove(this.teams.get(i).teamName); //analog mai sus
						 this.clasament.remove(this.teams.get(j).teamName);
						 this.clasament.put(this.teams.get(i).teamName, newScore1);
						 this.clasament.put(this.teams.get(j).teamName, newScore2);
						 this.clasament = sortDescendingByValue(this.clasament);
						 notifyObservers();
					 }
	
		 return this.clasament; //intorc clasamentul final actualizat
	 }
	 /**
	  * Metoda care sorteaza descrescator dupa valoare un map (cheia e un string si valoarea asociata ei un int),
	  * in cazul meu sorteaza clasamentul descrescator dupa scorurile echipelor
	  * @param unsortMap clasamentul nesortat 
	  * @return returneaza clasamentul sortat 
	  */
	 public Map<String, Integer> sortDescendingByValue(Map<String, Integer> unsortMap) {

		 	//folosesc o lista pt a retine toate intrarile in map
	        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
	        
	        //sortez lista cu ajutorul unui comparator implementat cu metoda compare
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	        	//metoda primeste ca parametri 2 intrari din map
	            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) { 
	            	//cand compareTo intoarce 1 eu intorc -1 pt a implementa sortarea descrescatoare (si nu crescatoare)
	            	//analog cand intoarce -1
	            	if((o1.getValue()).compareTo(o2.getValue()) == 1)
	            		return -1; 
	            	else if((o1.getValue()).compareTo(o2.getValue()) == -1)
	            		return 1;
	            	else 
	            		return 0;
	            }
	        });
	        //o sa retin la final intr-un nou map vechiul map sortat dupa valoare
	        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	        for (Map.Entry<String, Integer> entry : list) { //parcurg intrarile sortate ale map-ului din lista si le pun in noul map
	        	sortedMap.put(entry.getKey(), entry.getValue());
	        }

	        return sortedMap; //intorc noul map sortat
	    }
}