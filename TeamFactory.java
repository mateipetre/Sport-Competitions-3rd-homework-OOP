import java.util.*;

/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public class TeamFactory {

	private static TeamFactory uniqueInstance; //unica instanta de tip TeamFactory, folosita pentru implementarea Singleton
	
	/**
	 * Constructor folosit pentru a crea instante de tip TeamFactory
	 */
	public TeamFactory() {
		
	}
	/**
	 * Metoda prin care se implementeaza Factory si, efectiv, ea creeaza un factory de echipe
	 * @param type tipul echipei - fotbal, basket sau handbal
	 * @param teamName numele echipei
	 * @param gender genul echipei
	 * @param numberOfPlayers numarul de jucatori ai echipei
	 * @param players lista de jucatori ai echipei
	 * @return returneaza o instanta a unuia dintre copiii clasei parinte Team - FootballTeam, BasketballTeam, HandballTeam
	 */
	public Team createTeam(String type, String teamName, String gender, Integer numberOfPlayers, ArrayList<Player> players) {
		
		Team team = null;
		if(type.equals("football")) 
			team = new FootballTeam(teamName, gender, numberOfPlayers, players); 
		if(type.equals("basketball")) 
			team = new BasketballTeam(teamName, gender, numberOfPlayers, players);
		if(type.equals("handball")) 
			team = new HandballTeam(teamName, gender, numberOfPlayers, players);
		return team;
	}
	/**
	 * Metoda care creeaza unica instanta a TeamFactory
	 * @return returneaza unica instanta a TeamFactory 
	 */
	public static TeamFactory getInstance() {
		
		if(uniqueInstance == null)
			uniqueInstance = new TeamFactory();
		return uniqueInstance;
	}
}
