package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDatabase {
	static Connection connection = null;
	
	public void createConnection() throws ClassNotFoundException, SQLException {
		try {
			Class.forName("org.sqlite.JDBC"); //atver sqlite draiveri
	    	connection = DriverManager.getConnection("jdbc:sqlite:UsersDB.db"); // izveido savienojumu
	    	//UsersDB.db b�s nosaukums datub�zei (tuk�a datub�ze tiks izveidota, ja t� neeksist�)
	    	System.out.println("Opened database successfully");
    	}
		catch(Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
		System.out.println("Database created succesfully!");
	}
	
	public void endConnection() throws SQLException {
		connection.close();
	}
	
	public void createUsersDBTable() throws SQLException {
		try {
			Statement statement = connection.createStatement();
			String sqlStatement = "CREATE TABLE IF NOT EXISTS Users ("+
					"User_ID INTEGER PRIMARY KEY,"+
					"Name VARCHAR(50) NOT NULL,"+
					"Surname VARCHAR(50) NOT NULL,"+
					"PersonCode VARCHAR(12) NOT NULL,"+
					"Email VARCHAR(50) NOT NULL,"+
					"Username VARCHAR(50) NOT NULL,"+
					"Password VARCHAR(50) NOT NULL,"+
					"UserType VARCHAR(5) NOT NULL)";
			statement.executeUpdate(sqlStatement);
			statement.close();
			System.out.println("Table created succesfully!");
		}
		catch(Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
	}
	
	public void insertNewRecord(int userID, String name, String surname, String personCode, String email, String username, String password, String userType) throws SQLException {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Users (User_ID, Name, Surname, PersonCode, Email, Username, Password, UserType)"+
				"VALUES ("+userID+", '"+name+"', '"+surname+"', '"+personCode+"', '"+email+"', '"+username+"', '"+password+"', '"+userType+"');");
			statement.close();
		}
		catch(Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
	}
	
	//Funkcija, kas p�rbauda lietotaja ierakst�t� ielogo�an�s datus datub�ze un ja ir pareizi, atrie� true, ja ne - false
	public boolean checkLoginDataValidation(String typedUsername, String typedPassword) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Users");
		String username, password;
		//Lasa tik ilgi k�m�r ir ko las�t
		while(result.next()) {
			username = result.getString("Username");
			password = result.getString("Password");
			if((typedUsername.equals(username)) && (typedPassword.equals(password))) {
				return true;
			}
		}
		result.close();
		return false;
	}
	
	public boolean checkAdminOrUser(String typedUsername, String typedPassword, String userType) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Users");
		String username, password, type;
		while(result.next()) {
			username = result.getString("Username");
			password = result.getString("Password");
			type = result.getString("UserType");
			if((typedUsername.equals(username) && (typedPassword.equals(password) && type.equals(userType)))) {
				return true;
			}	
		}
		result.close();
		return false;
	}
}
