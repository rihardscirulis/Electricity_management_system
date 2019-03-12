package classes;

public class appClient {
	private int ID;
	private String name;
	private String surname;
	private String personCode;
	private String email;
	private String measurement;
	private String username;
	private String password;
	private String userType;
	
	//Noklusçtais konstruktors ar noklusçtâm vçrtîbâm
	public appClient() {
		
	}
	
	public appClient(String Name, String Surname, String person_Code, String Email, String Measurement, String Username, String Password, String UserType) {
		setID(ID++);
		setName(Name);
		setSurname(Surname);
		setPersonCode(person_Code);
		setEmail(Email);
		setMeasurement(Measurement);
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

	//Funkcija, kas iegûst elektriba skaitli
	public String getMeasurement() {
		return measurement;
	}
	
	//Funkcija, kas ievieto vçrtîbu un pârbauda
	public void setMeasurement(String measurement) {
		String temp = "";
		for(int i = 0; i < measurement.length(); i++) {
			if(Character.isDigit(measurement.charAt(i))) {
				temp += measurement.charAt(i);
			}
		}
		this.measurement = temp;
	}
	
	//Funkcija, kas iegûst paroli
	public String getPassword() {
		return password;
	}
	
	// == Pabeigt ==
	//Funkcija, kas ievieto vçrtîbu ar pârbaudçm
	public void setPassword(String password) {
		this.password = password;
	}
	
	//Funkcija, kas iegûst vârdu
		public String getName() {
			return name;
		}

	//Funkcija, kas ievieto vârdu ar pârbaudçm
	public void setName(String name) {
		String temp = "";
		for(int i = 0; i < name.length(); i++) {
			if(Character.isLetter(name.charAt(i))) {
				temp += name.charAt(i);
			}
		}
		this.name = temp;
	}
		
	//Funkcija, kas iegûst uzvârdu
	public String getSurname() {
		return surname;
	}
		
	//Funkcija, kas ievieto uzvârdu ar pârbaudçm
	public void setSurname(String surname) {
		String temp = "";
		for(int i = 0; i < surname.length(); i++) {
			if(Character.isLetter(surname.charAt(i))) {
				temp += surname.charAt(i);
			}
		}
		this.surname = temp;
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
		return email;
	}
		
	// == Pabeigt ==
	//Funkcija, kas ievieto epastu
	public void setEmail(String email) {
		this.email = email;
	}
}
