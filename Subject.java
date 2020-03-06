
/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public interface Subject {

	/**
	 * Metoda prin care sunt inregistrati observatorii, in cazul de fata echipele din competitie
	 * @param o observatorul, respectiv o echipa in acest caz
	 */
	public void registerObserver(Observer o); 
	/**
	 * Metoda prin care sunt notificati toti observatorii de update-ul produs asupra clasamentului
	 */
    public void notifyObservers(); 
}
