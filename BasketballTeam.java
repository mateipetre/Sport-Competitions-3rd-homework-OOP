import java.util.*;

/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public class BasketballTeam extends Team implements ScoreVisitor, Observer, Strategy{

	/**
	 * Constructor folosit pentru a crea instante de tip BasketballTeam
	 * @param teamName numele echipei
	 * @param gender genul echipei
	 * @param numberOfPlayers numarul de jucatori din echipa
	 * @param players lista jucatorilor din echipa
	 */
	public BasketballTeam(String teamName, String gender, Integer numberOfPlayers, ArrayList<Player> players) {
		
		super(); //apelez constructorul clasei parinte
		this.teamName = teamName;
		this.gender = gender;
		this.numberOfPlayers = numberOfPlayers;
		this.players = players;
	}
	/**
	 * Metoda suprascrisa prin care intorc un string ce semnifica afisarea corespunzatoare
	 * a echipei de basket in fisierul de iesire
	 */
	public String toString() {
		
		return "{teamName: " + this.teamName + ", gender: " + this.gender + ", numberOfPlayers: " +
				this.numberOfPlayers + ", players: " + this.players + "}";
	}
	/**
	 * Metoda suprascrisa pentru implementarea Visitor, prin intermediul careia
	 * practic accesez scorul echipei de basket la fiecare meci
	 */
	@Override
	public Double accept(Visitor visitor) {
		
		return visitor.visit(this);
	}
	/**
	 * Metoda suprascrisa pentru implementarea Observer, prin intermediul careia
	 * practic notific echipa de basket de actualizarea clasamentului
	 */
	@Override
	public void update(Map<String, Integer> clasament) {
		
		this.clasament = clasament;
	}
	/**
	 * Metoda suprascrisa pentru implementarea Strategy, prin intermediul careia
	 * calculez scorul echipei de basket
	 */
	@Override
	public Double calculateScore() {
		
		Double sumOfScores = 0.00; 
		Double averageScore;
		for(int i = 0; i < numberOfPlayers; i++)
			sumOfScores = sumOfScores + players.get(i).score;
		averageScore = (Double) (sumOfScores / numberOfPlayers); //scorul echipei e media scorurilor jucatorilor
		return averageScore;
	}
}
