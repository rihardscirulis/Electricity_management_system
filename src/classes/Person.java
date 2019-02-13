package classes;

public class Person {
	private static int ID;
	private String Name;
	private String Surname;
	private String personCode;
	private String Email;

	//Noklusçtais kontruktors ar noklusçtâm vçrtîbâm
	public Person() {
		String symbol = "-";
		setName(symbol);
		setSurname(symbol);
		setPersonCode(symbol);
		setEmail(symbol);
		Person.ID = 0;
	}
	
	//Konstruktors ar vçrtîbâm un funkcijam
	public Person(String name, String surname, String person_Code) {
		setName(name);
		setSurname(surname);
		setPersonCode(person_Code);
		ID++;
	}

	//Funkcija, kas iegûst vârdu
	public String getName() {
		return Name;
	}

	//Funkcija, kas ievieto vârdu ar pârbaudçm
	public void setName(String name) {
		String temp = "";
		for(int i = 0; i < name.length(); i++) {
			if(Character.isLetter(name.charAt(i))) {
				temp += name.charAt(i);
			}
		}
		this.Name = temp;
	}
	
	//Funkcija, kas iegûst uzvârdu
	public String getSurname() {
		return Surname;
	}
	
	//Funkcija, kas ievieto uzvârdu ar pârbaudçm
	public void setSurname(String surname) {
		String temp = "";
		for(int i = 0; i < surname.length(); i++) {
			if(Character.isLetter(surname.charAt(i))) {
				temp += surname.charAt(i);
			}
		}
		this.Surname = temp;
	}

	//Funkcija, kas iegûst personas kodu
	public String getPersonCode() {
		return personCode;
	}

	//Funkcija, kas ievieto personas kodu ar pârbaudçm
	public void setPersonCode(String personCode) {
		String temp = "";
		String symbol = "-";
		for(int i = 0; i < personCode.length(); i++) {
			if(Character.isDigit(personCode.charAt(i))) {
				temp += personCode.charAt(i);
				if(i == 5) {
					temp += symbol;
				}
			}
		}
		this.personCode = temp;
	}

	//Funkcija, kas iegûst personas ID ar parbaudçm
	public int getID() {
		if(ID >= 0) {
			return ID;
		}
		else {
			return 0;
		}
	}
	
	// == Pabeigt ==
	//Funkcija, kas iegûst epastu
	public String getEmail() {
		return Email;
	}
	
	// == Pabeigt ==
	//Funkcija, kas ievieto epastu
	public void setEmail(String email) {
		Email = email;
	}
}
