import java.util.*;

/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public class HandballTeam extends Team implements ScoreVisitor, Observer, Strategy{

	/**
	 * Constructor folosit pentru a crea instante de tip HandballTeam
	 * @param teamName numele echipei
	 * @param gender genul echipei
	 * @param numberOfPlayers numarul de jucatori din echipa
	 * @param players lista jucatorilor din echipa
	 */
	public HandballTeam(String teamName, String gender, Integer numberOfPlayers, ArrayList<Player> players) {
		
		super(); //apelez constructorul clasei parinte
		this.teamName = teamName;
		this.gender = gender;
		this.numberOfPlayers = numberOfPlayers;
		this.players = players;
	}
	/**
	 * Metoda suprascrisa prin care intorc un string ce semnifica afisarea corespunzatoare
	 * a echipei de handbal in fisierul de iesire
	 */
	public String toString() {
		
		return "{teamName: " + this.teamName + ", gender: " + this.gender + ", numberOfPlayers: " +
				this.numberOfPlayers + ", players: " + this.players + "}";
	}
	/**
	 * Metoda suprascrisa pentru implementarea Visitor, prin intermediul careia
	 * practic accesez scorul echipei de handbal la fiecare meci
	 */
	@Override
	public Double accept(Visitor visitor) {
		
		return visitor.visit(this);
	}
	/**
	 * Metoda suprascrisa pentru implementarea Observer, prin intermediul careia
	 * practic notific echipa de handbal de actualizarea clasamentului
	 */
	@Override
	public void update(Map<String, Integer> clasament) {
		
		this.clasament = clasament;
	}
	/**
	 * Metoda suprascrisa pentru implementarea Strategy, prin intermediul careia
	 * calculez scorul echipei de handbal
	 */
	@Override
	public Double calculateScore() {
		
		Double teamScore;
		if(gender.equals("masculin")) { //daca e de gen masculin
			
			teamScore = 0.00; 
			for(int i = 0; i < numberOfPlayers; i++)
				teamScore = teamScore + players.get(i).score; //scorul e suma scorurilor jucatorilor
			return teamScore;
		}
		else { //daca e de gen feminin
			teamScore = 1.00;
			for(int i = 0; i < numberOfPlayers; i++)
				teamScore = teamScore * players.get(i).score; //scorul e suma scorurilor jucatorilor
			return teamScore;
		}
	}
}
