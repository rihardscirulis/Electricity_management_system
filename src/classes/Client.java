package classes;

public class Client extends Person {
	private String Measurement;
	private String Password;
	
	//Noklusçtais konstruktors ar noklusçtâm vçrtîbâm
	Client() {
		super("-", "-", "-");
		setMeasurement("0");
		setPassword("-");
	}
	
	//Konstruktors ar vçrtîbâm
	Client(String name, String surname, String person_Code, String measurement, String password) {
		super(name, surname, person_Code);
		setMeasurement(measurement);
		setPassword(password);
	}

	//Funkcija, kas iegûst elektriba skaitli
	public String getMeasurement() {
		return Measurement;
	}
	
	//Funkcija, kas ievieto vçrtîbu un pârbauda
	public void setMeasurement(String measurement) {
		String temp = "";
		for(int i = 0; i < measurement.length(); i++) {
			if(Character.isDigit(measurement.charAt(i))) {
				temp += measurement.charAt(i);
			}
		}
		this.Measurement = temp;
	}
	
	//Funkcija, kas iegûst paroli
	public String getPassword() {
		return Password;
	}
	
	// == Pabeigt ==
	//Funkcija, kas ievieto vçrtîbu ar pârbaudçm
	public void setPassword(String password) {
		Password = password;
	}
}
