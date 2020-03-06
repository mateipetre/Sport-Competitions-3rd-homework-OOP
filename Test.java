import java.io.*;
import java.util.*;

/**
 * 
 * @author Matei Alexandru-Petrut, 325CB
 *
 */
public class Test {

	/**
	 * Constructor fara parametri al clasei Test
	 */
	public Test() {

	}

	/**
	 * Metoda main care reprezinta implementarea generala a cerintelor - inscrierea echipelor,
	 * competitia, in acelasi timp citirea din fisierele de intrare, afisarea in fisierele de
	 * iesire
	 * @param args parametri dati in linia de comanda
	 * @throws Exception exceptia care este aruncata in cazul in care fisierele nu exista
	 * sau nu pot fi accesate (nu se poate citi din ele, nu se poate afisa in ele), apar
	 * erori la parsarea acestora, la apelul unor metode
	 */
	public static void main(String[] args) throws Exception{

		File inputFile = new File(args[1]); //fisierul de intrare cu echipe
		File input1File = new File(args[2]); //fisierul de intrare cu competitia
		File outputFile = new File(args[3]); //fisierul de iesire cu inscrierea echipelor sau cu rezultatele competitiei
		//declarare entitati necesare pentru stocarea datelor si afisarea lor
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		String current_line, current_token;
		StringTokenizer tokenizer; //folosesc StringTokenizer pentru impartirea in tokens a liniilor fisierelor
		Vector<Team> teamCollection = new Vector<>(); //lista echipelor inscrise
		TeamFactory teamFactory = TeamFactory.getInstance(); //factory-ul de echipe inscrise, unic instantiat
		Vector<String> nameOfTeamsInCompetition = new Vector<>(); //vectorul numelor echipelor inscrise in competitie
		ArrayList<Team> teamsInCompetition = new ArrayList<>();  // lista echipelor din competitie
		Map<String, Integer> clasamentForCompetition = new TreeMap<>(); //clasamentul echipelor din competitie
		try {
			
			if(args[0].equals("inscriere")) { //daca comanda e inscriere
				
				current_line = br.readLine(); //citesc prima linie din fisierul de echipe
				while(current_line != null) { //cat timp mai exista linii de citit in fisier
				
					tokenizer = new StringTokenizer(current_line); //parsare in tokens a liniei citite
					if(tokenizer.countTokens() > 2) { //daca linia contine mai mult de 2 informatii
		
						current_token = tokenizer.nextToken(); //retin prima informatie
						String teamType = current_token.substring(0, current_token.length() - 1); //tipul echipei din care scot virgula
						current_token = tokenizer.nextToken(); //urmatorul token
						String teamName = new String(); //numele echipei
						//verific daca este format dintr-un token (ex: theTeam1) sau mai multi (ex: the F Leakers)
						if(current_token.contains(","))  //daca tokenul contine virgula este format doar din unul
							teamName = current_token.substring(0, current_token.length() - 1);
						else //altfel este format din mai multi tokens
							{
								while(!current_token.contains(",")) { //pana cand ajung la tokenul cu virgula
									teamName = teamName + current_token + " "; //ii concatenez pe tokens intre ei cu spatiu
									current_token = tokenizer.nextToken(); //urmatorul token
								}
								teamName = teamName + current_token.substring(0, current_token.length() - 1); //ultimul token din nume are virgula 
							}
						current_token = tokenizer.nextToken(); //urmatorul token
						String gender = current_token.substring(0, current_token.length() - 1); //genul echipei fara virgula
						Integer numberOfPlayers = Integer.parseInt(tokenizer.nextToken()); //numarul jucatorilor din echipa
						ArrayList<Player> listOfPlayers = new ArrayList<>(); //lista de jucatori ai echipei
						for(int i = 0; i < numberOfPlayers; i++) {
						
							//citesc acum atatea linii din fisier cati jucatori sunt
							current_line = br.readLine(); //fiecare linie acum e un jucator
							tokenizer = new StringTokenizer(current_line); //o impart in tokens
							current_token = tokenizer.nextToken(); //numele jucatorului
							String next_token = tokenizer.nextToken(); //scorul jucatorului
							//jucatorul curent creat
							Player currentPlayer = new Player(current_token.substring(0, current_token.length() - 1), Integer.parseInt(next_token));
							listOfPlayers.add(currentPlayer); //adaug jucatorul curent la lista de jucatori ai echipei
						}
						//creez o echipa cu datele colectate mai sus prin factory
						Team team = teamFactory.createTeam(teamType, teamName, gender, numberOfPlayers, listOfPlayers);
						teamCollection.add(team); //adaug echipa la vectorul de echipe
					}
					current_line = br.readLine(); //citesc in continuare o linie din fisier
				}
				//parcurg vectorul de echipe si afisez in fisierul de iesire de inscriere echipele 
				for(int i = 0; i < teamCollection.size(); i++) {
				
					bw.write(teamCollection.get(i).toString());
					bw.newLine();
				}
				br.close();
				bw.close();
			}
			else if(args[0].equals("competitie")) { //daca comanda e competitie
				
				BufferedReader br1 = new BufferedReader(new FileReader(input1File));
				ArrayList<String> listOfTeamsInCompInOrder = new ArrayList<>(); //lista numelor echipelor din competitie in ordine
																				//folosita pt afisarea locurilor la final
				current_line = br1.readLine(); //prima linie din fisierul de intrare cu competitia
				tokenizer = new StringTokenizer(current_line); //o impart in tokens
				current_token = tokenizer.nextToken(); //primul token
				String typeOfCompetition = current_token.substring(0, current_token.length() - 1); //tipul competitiei fara virgula
				String genderOfCompetition = tokenizer.nextToken(); //al doilea token, genul competitiei
				current_line = br1.readLine(); //linia urmatoare care contine numele unei echipe 
				while(current_line != null) { //cat timp mai sunt linii de citit in fisier
					
					String nameOfTeamInComp = new String(); //numele unei echipe din competitie
					tokenizer = new StringTokenizer(current_line); //imparte linia in tokens
					if(tokenizer.countTokens() > 1) { //numele echipei este format din mai multi tokens
						
						current_token = tokenizer.nextToken();
						while(current_token != null) {
							nameOfTeamInComp =  nameOfTeamInComp + current_token + " "; //formarea numelui
							current_token = tokenizer.nextToken();
						}
					}
					else
						nameOfTeamInComp = nameOfTeamInComp + tokenizer.nextToken(); //cand numele este doar un cuvant
					nameOfTeamsInCompetition.add(nameOfTeamInComp); //adaug numele echipei la lista de nume ale echipelor din competitie
					current_line = br1.readLine(); //citesc mai departe alta linie 
					listOfTeamsInCompInOrder.add(nameOfTeamInComp); //adaug numele echipei la lista de nume ale echipelor, folosita la afisare 
				}
				br1.close();
				//deschid fisierul cu echipe si citesc din el pt creearea echipelor din competitie
				current_line = br.readLine(); 
				while(current_line != null) {
				
					//analog pt inscriere
					tokenizer = new StringTokenizer(current_line);
					if(tokenizer.countTokens() > 2) {
		
						current_token = tokenizer.nextToken();
						String teamType = current_token.substring(0, current_token.length() - 1); //tipul echipei
						current_token = tokenizer.nextToken();
						String teamName = new String(); //numele echipei
						if(current_token.contains(",")) 
							teamName = current_token.substring(0, current_token.length() - 1);
						else
							{
								while(!current_token.contains(",")) {
									teamName = teamName + current_token + " ";
									current_token = tokenizer.nextToken();
								}
								teamName = teamName + current_token.substring(0, current_token.length() - 1);
							}
						current_token = tokenizer.nextToken();
						String gender = current_token.substring(0, current_token.length() - 1); //genul echipei
						Integer numberOfPlayers = Integer.parseInt(tokenizer.nextToken()); //numarul de jucatori ai echipei
						ArrayList<Player> listOfPlayers = new ArrayList<>(); //lista de jucatori
						//adaug jucatorii la lista de jucatori
						for(int i = 0; i < numberOfPlayers; i++) {
						
							current_line = br.readLine();
							tokenizer = new StringTokenizer(current_line);
							current_token = tokenizer.nextToken();
							String next_token = tokenizer.nextToken();
							Player currentPlayer = new Player(current_token.substring(0, current_token.length() - 1), Integer.parseInt(next_token));
							listOfPlayers.add(currentPlayer);
						}
						//verific daca numele echipei se afla printre cele din competitie si daca tipul si genul ei din fisierul competitie sunt conforme cu cele din fisierul echipe
						if((typeOfCompetition.equals(teamType) == true) && (genderOfCompetition.equals(gender) == true) && (nameOfTeamsInCompetition.contains(teamName) == true)) {
							
							//daca da, creez echipa din competitie cu factory
							Team teamInCompetition = teamFactory.createTeam(teamType, teamName, gender, numberOfPlayers, listOfPlayers);
							teamsInCompetition.add(teamInCompetition); //o adaug in lista echipelor din competitie
							clasamentForCompetition.put(teamName, 0); //o adaug si in clasamentul meciurilor initial cu scor 0
						}
					}
					current_line = br.readLine(); //urmatoarea linie din fisierul de echipe
				}
				
				br.close(); 
				//creez competitia cu datele necesare colectate mai sus
				Competition competition = new Competition(typeOfCompetition, genderOfCompetition, teamsInCompetition, clasamentForCompetition);
				
				//inregistrez observatorii si anume echipele din competitie
				for(int i = 0; i < teamsInCompetition.size(); i++)
					competition.registerObserver(competition.teams.get(i));
				
				//retin clasamentul final in urma tututor meciurilor dintre echipe
				Map<String, Integer> finalClasament = competition.compete();
				int podium = 3; //pentru a afisa doar echipele din top 3 din clasament
				for(Map.Entry<String,Integer> entry : finalClasament.entrySet()) 
					if(podium >= 1 ) { //daca se afla pe podium (in top 3)
						String key = entry.getKey(); //retin pentru fiecare intrare din clasamentul final doar cheia, adica numele echipei
						bw.write(key); //afisez in fisierul rezultate
						bw.newLine();
						podium --;
					}
				//retin cheile din clasamentul final intr-o lista de nume ale echipelor in ordinea clasarii
				List<String> clasamentOfTeams = new ArrayList<String>(finalClasament.keySet());
				
				/*parcurg lista numelor echipelor din competitie in ordinea din fisierul competitie 
				 si lista numele echipelor in ordinea clasarii si verific daca cele 2 nume coincid
				 iar cand coincid afisez locul pe care l-a ocupat echipa respectiva in clasament , 
				 prin indicele numelui din lista cu ordinea clasarii
				 */
				for(int i = 0; i < listOfTeamsInCompInOrder.size(); i++)
					for (int j = 0; j < clasamentOfTeams.size(); j++) 
						if(listOfTeamsInCompInOrder.get(i).equals(clasamentOfTeams.get(j))) {
							
							bw.write("Echipa " + listOfTeamsInCompInOrder.get(i) + " a ocupat locul " + (j+1));
							bw.newLine();
						}
				bw.close();
			}
		}
		//prind exceptia cu un bloc de catch
		catch(Exception e) {
			
			e.printStackTrace();
			System.out.println("Error parsing file");
		}
	}

}
