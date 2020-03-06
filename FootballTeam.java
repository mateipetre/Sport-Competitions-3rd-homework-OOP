import java.util.*;

/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public class FootballTeam extends Team implements ScoreVisitor, Observer, Strategy{
	
	/**
	 * Constructor folosit pentru a crea instante de tip FootballTeam
	 * @param teamName numele echipei
	 * @param gender genul echipei
	 * @param numberOfPlayers numarul de jucatori din echipa
	 * @param players lista jucatorilor din echipa
	 */
	public FootballTeam(String teamName, String gender, Integer numberOfPlayers, ArrayList<Player> players) {
		
		super(); //apelez constructorul clasei parinte
		this.teamName = teamName;
		this.gender = gender;
		this.numberOfPlayers = numberOfPlayers;
		this.players = players;
	}
	/**
	 * Metoda suprascrisa prin care intorc un string ce semnifica afisarea corespunzatoare
	 * a echipei de fotbal in fisierul de iesire
	 */
	public String toString() {
		
		return "{teamName: " + this.teamName + ", gender: " + this.gender + ", numberOfPlayers: " +
				this.numberOfPlayers + ", players: " + this.players + "}";
	}
	/**
	 * Metoda suprascrisa pentru implementarea Visitor, prin intermediul careia
	 * practic accesez scorul echipei de fotbal la fiecare meci
	 */
	@Override
	public Double accept(Visitor visitor) {
		
		return visitor.visit(this); 
	}
	/**
	 * Metoda suprascrisa pentru implementarea Observer, prin intermediul careia
	 * practic notific echipa de fotbal de actualizarea clasamentului
	 */
	@Override
	public void update(Map<String, Integer> clasament) {
		
		this.clasament = clasament;
	}
	/**
	 * Metoda suprascrisa pentru implementarea Strategy, prin intermediul careia
	 * calculez scorul echipei de fotbal
	 */
	@Override
	public Double calculateScore() {
		
		Double teamScore = 0.00;
		
			if(gender.equals("masculin")) { //daca echipa e de gen masculin, calculez conform cerintei
			
				Double scoreOfBestPlayer = scoreOfBestPlayer(); //retin scorul celui mai bun jucator
				teamScore = 2 * scoreOfBestPlayer;
				for(int i = 0; i < numberOfPlayers; i++)
					if((0.00 + players.get(i).score) != scoreOfBestPlayer) //am scris asa pentru a putea compara obiectele de tip double
						teamScore = teamScore + players.get(i).score; //cazul cand nu gasesc scorul celui mai bun jucator
					else 
						scoreOfBestPlayer = -1.00; //cazul cand am gasit scorul celui mai bun jucator
				return teamScore;				   //am scris asa pentru cazul cand exista mai multi jucatori cu cel mai bun scor
			}
			else { //daca echipa e de gen feminin
			
				Double scoreOfWorstPlayer = scoreOfWorstPlayer(); //retin scorul celui mai slab jucator
				teamScore = 2 * scoreOfWorstPlayer; 
				for(int i = 0; i < numberOfPlayers; i++)
					if((0.00 + players.get(i).score) != scoreOfWorstPlayer)
						teamScore = teamScore + players.get(i).score;	
					else 
						scoreOfWorstPlayer = -1.00;
				return teamScore;
			}
	}
}
