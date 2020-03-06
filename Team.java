import java.util.*;

/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public class Team implements ScoreVisitor, Observer, Strategy{
	
	public String teamName; //numele echipei
	public String gender; //genul echipei
	public Integer numberOfPlayers; //numarul de jucatori din echipa
	public ArrayList<Player> players; //lista jucatorilor din echipa
	public Map<String, Integer> clasament; //clasamentul echipelor din competitie
	/**
	 * Constructor care initializeaza lista de jucatori si prin care se pot creea instante de tip Team
	 */
	public Team() {
		
		players = new ArrayList<Player>();
	}
	/**
	 * Metoda suprascrisa pentru implementarea Visitor
	 */
	@Override
	public Double accept(Visitor visitor) {
		
		return visitor.visit(this); 
	}
	/**
	 * Metoda care intoarce scorul celui mai bun jucator din echipa (cel mai mare scor)
	 * @return returneaza o valoare de tip Double ce reprezinta scorul celui mai bun jucator din echipa
	 */
	public Double scoreOfBestPlayer() {
		
		Double maximumScore = 0.00; 
		maximumScore = maximumScore + players.get(0).score; //am scris asa pentru ca nu pot face cast de la int la double
		for(int i = 0; i < numberOfPlayers; i++) 
			if(players.get(i).score > maximumScore)
				maximumScore = 0.00 + players.get(i).score; //analog mai sus, adun cu valoarea nula de tip double pt a obtine double
		return maximumScore;
	}
	/**
	 * Metoda care intoarce scorul celui mai slab jucator din echipa (cel mai mic scor)
	 * @return returneaza o valoare de tip Double ce reprezinta scorul celui mai slab jucator din echipa
	 */
	public Double scoreOfWorstPlayer() {
		
		Double minimumScore = 0.00;
		minimumScore = minimumScore + players.get(0).score;
		for(int i = 0; i < numberOfPlayers; i++)
			if(players.get(i).score < minimumScore)
				minimumScore = 0.00 + players.get(i).score;
		return minimumScore;
	}
	/**
	 * Metoda suprascrisa pentru implementarea Observer
	 */
	@Override
	public void update(Map<String, Integer> clasament) {
		
		this.clasament = clasament;
	}
	
	/**
	 * Metoda suprascrisa pentru implementarea Strategy
	 */
	@Override
	public Double calculateScore() {
		
		return calculateScore();
	}
}
