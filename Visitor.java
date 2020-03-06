
/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public interface Visitor {
	/**
	 * Metoda care implementeaza Visitor, cu ajutorul careia visitor-ul poate accesa scorul unei echipe
	 * @param team echipa pe care o viziteaza 
	 * @return returneaza scorul accesat de visitor
	 */
	public Double visit(Team team);
}
