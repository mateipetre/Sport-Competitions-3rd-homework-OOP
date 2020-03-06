
/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public class StrategyContext {

	public Strategy strategy; 
	
	/**
	 * Constructor prin care se pot creea instante de tip StrategyContext, care primeste ca parametru 
	 * un obiect de tip Strategy
	 * @param strategy obiect de tip Strategy, in cazul meu una din echipele din competitie care implementeaza Strategy
	 */
	public StrategyContext(Strategy strategy) {
		
		this.strategy = strategy;
	}
	/**
	 * Metoda prin care este intors scorul echipei calculat prin Strategy
	 * @return returneaza scorul echipei 
	 */
	public Double executeStrategy(){
		
	      return strategy.calculateScore();
	}
}
