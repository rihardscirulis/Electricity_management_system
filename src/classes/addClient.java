package classes;

public class addClient {
	private int ID;
	private String name;
	private String surname;
	private String personCode;
	private String email;
	private String measurement;
	private String username;
	private String password;
	private String userType;
	
	//Noklus�tais konstruktors ar noklus�t�m v�rt�b�m
	public addClient() {
		
	}
	
	public addClient(int id, String Name, String Surname, String person_Code, String Email, String Username, String Password, String UserType) {
		setID(id);
		setName(Name);
		setSurname(Surname);
		setPersonCode(person_Code);
		setEmail(Email);
		setUsername(Username);
		setPassword(Password);
		setUserType(UserType);
	}
	
	public addClient(String Name, String Surname, String person_Code, String Email, String Username, String Password, String UserType) {
		setID(ID++);
		setName(Name);
		setSurname(Surname);
		setPersonCode(person_Code);
		setEmail(Email);
		setUsername(Username);
		setPassword(Password);
		setUserType(UserType);
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	//Funkcija, kas ieg�st paroli
	public String getPassword() {
		return password;
	}
	
	// == Pabeigt ==
	//Funkcija, kas ievieto v�rt�bu ar p�rbaud�m
	public void setPassword(String password) {
		this.password = password;
	}
	
	//Funkcija, kas ieg�st v�rdu
		public String getName() {
			return name;
		}

	//Funkcija, kas ievieto v�rdu ar p�rbaud�m
	public void setName(String name) {
		String temp = "";
		for(int i = 0; i < name.length(); i++) {
			if(Character.isLetter(name.charAt(i))) {
				temp += name.charAt(i);
			}
		}
		this.name = temp;
	}
		
	//Funkcija, kas ieg�st uzv�rdu
	public String getSurname() {
		return surname;
	}
		
	//Funkcija, kas ievieto uzv�rdu ar p�rbaud�m
	public void setSurname(String surname) {
		String temp = "";
		for(int i = 0; i < surname.length(); i++) {
			if(Character.isLetter(surname.charAt(i))) {
				temp += surname.charAt(i);
			}
		}
		this.surname = temp;
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
		return email;
	}
		
	// == Pabeigt ==
	//Funkcija, kas ievieto epastu
	public void setEmail(String email) {
		this.email = email;
	}
}
