package classes;

public class Person {
	private static int ID;
	private String Name;
	private String Surname;
	private String personCode;
	private String Email;

	//Noklus�tais kontruktors ar noklus�t�m v�rt�b�m
	public Person() {
		String symbol = "-";
		setName(symbol);
		setSurname(symbol);
		setPersonCode(symbol);
		setEmail(symbol);
		Person.ID = 0;
	}
	
	//Konstruktors ar v�rt�b�m un funkcijam
	public Person(String name, String surname, String person_Code) {
		setName(name);
		setSurname(surname);
		setPersonCode(person_Code);
		ID++;
	}

	//Funkcija, kas ieg�st v�rdu
	public String getName() {
		return Name;
	}

	//Funkcija, kas ievieto v�rdu ar p�rbaud�m
	public void setName(String name) {
		String temp = "";
		for(int i = 0; i < name.length(); i++) {
			if(Character.isLetter(name.charAt(i))) {
				temp += name.charAt(i);
			}
		}
		this.Name = temp;
	}
	
	//Funkcija, kas ieg�st uzv�rdu
	public String getSurname() {
		return Surname;
	}
	
	//Funkcija, kas ievieto uzv�rdu ar p�rbaud�m
	public void setSurname(String surname) {
		String temp = "";
		for(int i = 0; i < surname.length(); i++) {
			if(Character.isLetter(surname.charAt(i))) {
				temp += surname.charAt(i);
			}
		}
		this.Surname = temp;
	}

	//Funkcija, kas ieg�st personas kodu
	public String getPersonCode() {
		return personCode;
	}

	//Funkcija, kas ievieto personas kodu ar p�rbaud�m
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

	//Funkcija, kas ieg�st personas ID ar parbaud�m
	public int getID() {
		if(ID >= 0) {
			return ID;
		}
		else {
			return 0;
		}
	}
	
	// == Pabeigt ==
	//Funkcija, kas ieg�st epastu
	public String getEmail() {
		return Email;
	}
	
	// == Pabeigt ==
	//Funkcija, kas ievieto epastu
	public void setEmail(String email) {
		Email = email;
	}
}
