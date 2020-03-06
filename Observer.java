import java.util.*;

/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public interface Observer {

	/**
	 * Metoda prin care se actualizeaza clasamentul pe care il retine fiecare echipa la fiecare schimbare
	 * @param clasament clasamentul echipelor aflate in competitie
	 */
	public void update(Map<String, Integer> clasament);
}
