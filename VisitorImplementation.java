
/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public class VisitorImplementation implements Visitor{

	/**
	 * Constructor prin care se pot creea instante de tip VisitorImplementation
	 */
	public VisitorImplementation() {
		
	}
	/**
	 * Metoda suprascrisa care acceseaza scorul echipei calculat prin Strategy
	 */
	public Double visit(Team team) {
		
		StrategyContext context = new StrategyContext(team); //e folosita o instanta a StrategyContext 
		return context.executeStrategy(); //returneaza efectiv scorul echipei calculat prin Strategy
	}
}
