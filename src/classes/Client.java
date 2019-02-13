package classes;

public class Client extends Person {
	private String Measurement;
	private String Password;
	
	//Noklus�tais konstruktors ar noklus�t�m v�rt�b�m
	Client() {
		super("-", "-", "-");
		setMeasurement("0");
		setPassword("-");
	}
	
	//Konstruktors ar v�rt�b�m
	Client(String name, String surname, String person_Code, String measurement, String password) {
		super(name, surname, person_Code);
		setMeasurement(measurement);
		setPassword(password);
	}

	//Funkcija, kas ieg�st elektriba skaitli
	public String getMeasurement() {
		return Measurement;
	}
	
	//Funkcija, kas ievieto v�rt�bu un p�rbauda
	public void setMeasurement(String measurement) {
		String temp = "";
		for(int i = 0; i < measurement.length(); i++) {
			if(Character.isDigit(measurement.charAt(i))) {
				temp += measurement.charAt(i);
			}
		}
		this.Measurement = temp;
	}
	
	//Funkcija, kas ieg�st paroli
	public String getPassword() {
		return Password;
	}
	
	// == Pabeigt ==
	//Funkcija, kas ievieto v�rt�bu ar p�rbaud�m
	public void setPassword(String password) {
		Password = password;
	}
}
