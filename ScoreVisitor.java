
/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public interface ScoreVisitor {

	/**
	 * Metoda prin care este accesat scorul unei echipe de catre Visitor
	 * @param visitor visitor-ul care acceseaza scorul echipei prin metoda visit 
	 * @return returneaza scorul echipei
	 */
	public Double accept(Visitor visitor);
}
