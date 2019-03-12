package classes;

import java.util.Date;

public class Measurement {
	private int ID;
	private String measurement;
	private String date;
	
	public Measurement() {
		
	}
	
	public Measurement(int id, String measurement) {
		setID(id);
		setMeasurement(measurement);
	}
	
	public Measurement(int id, String date, String measurement) {
		setID(id);
		setDate(date);
		setMeasurement(measurement);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
}
