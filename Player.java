
/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public class Player{

	public String name; //numele jucatorului
	public Integer score; //scorul jucatorului
	/**
	 * Constructor folosit pentru a crea instante de tip Player
	 * @param name numele jucatorului
	 * @param score scorul jucatorului
	 */
	public Player(String name, Integer score) {
		
		this.name = name;
		this.score = score;
	}
	/**
	 * Metoda suprascrisa prin care intorc un string ce semnifica afisarea corespunzatoare
	 * a jucatorului in cadrul echipei
	 */
	public String toString() {
		
		return "{name: " + this.name + ", score: " + this.score + "}";
	}
}
