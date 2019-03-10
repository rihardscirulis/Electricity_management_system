package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.appClient;
import classes.appClient;

public class UserDatabase {
	static Connection connection = null;
	
	public void createConnection() throws ClassNotFoundException, SQLException {
		try {
			Class.forName("org.sqlite.JDBC"); //atver sqlite draiveri
	    	connection = DriverManager.getConnection("jdbc:sqlite:UsersDB.db"); // izveido savienojumu
	    	//UsersDB.db bûs nosaukums datubâzei (tukða datubâze tiks izveidota, ja tâ neeksistç)
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
				"VALUES ((SELECT MAX(User_ID) FROM Users) + 1,'"+name+"', '"+surname+"', '"+personCode+"', '"+email+"', '"+username+"', '"+password+"', '"+userType+"');");
			statement.close();
		}
		catch(Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
	}
	
	//Funkcija, kas pârbauda lietotaja ierakstîtâ ielogoðanâs datus datubâze un ja ir pareizi, atrieþ true, ja ne - false
	public boolean checkLoginDataValidation(String typedUsername, String typedPassword) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Users");
		String username, password;
		//Lasa tik ilgi kâmçr ir ko lasît
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
	
	public appClient getClientByUsername(String username) throws SQLException {
		appClient getClient = new appClient();
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Users WHERE Username = '"+username+"'");
		while(result.next()) {
			getClient.setID(result.getInt("User_ID"));
			getClient.setName(result.getString("Name"));
			getClient.setSurname(result.getString("Surname"));
			getClient.setPersonCode(result.getString("PersonCode"));
			getClient.setEmail(result.getString("Email"));
			getClient.setUsername(result.getString("Username"));
			getClient.setPassword(result.getString("Password"));
			getClient.setUserType(result.getString("UserType"));
		}
		return getClient;
	}
}
