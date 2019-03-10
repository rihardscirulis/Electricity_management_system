package server;

import java.sql.SQLException;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import classes.UsersData;
import classes.addClient;
import classes.appClient;
import database.UserDatabase;

public class EMS_ServerListener extends Listener {
	public static ArrayList<Connection> connections = new ArrayList<>();
	
	public EMS_ServerListener() {
		
	}
	
	@Override
	public void connected(Connection connect) {
		System.out.println("SERVER >> New connection from client: "+ connect.getID());
		connections.add(connect);
	}
	
	@Override
	public void disconnected(Connection connect) {
		System.out.println("SERVER >> Disconnected client: "+ connect.getID());
		connections.remove(connect);
	}
	
	@Override
	public void received(Connection connect, Object object) {
		/*if(object instanceof String) {
			String receivedFromClient = String() object;
			System.out.println("Message from client: "+receivedFromClient);
		}*/
		if(object instanceof UsersData) {
			System.out.println("Server received user object about check login data: ");
			UsersData receivedFromClient_1 = (UsersData) object;
			System.out.println(receivedFromClient_1.toString());
			
			String checkUsersUsername = receivedFromClient_1.getUsername();
			String checkUsersPassword = receivedFromClient_1.getPassword();
			
			UserDatabase db = new UserDatabase();
			try {
				db.createConnection();
				if(db.checkLoginDataValidation(checkUsersUsername, checkUsersPassword) == true) {
					appClient logInClient = new appClient();
					logInClient = db.getClientByUsername(checkUsersUsername);
					connect.sendTCP(logInClient);
					connect.sendTCP("Login successful");
				}
				else {
					connect.sendTCP("Login unsuccessful");
				}
				db.endConnection();
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(object instanceof addClient) {
			System.out.println("Server received user object about add new user: ");
			addClient receivedFromClient = (addClient) object;
			int newID = receivedFromClient.getID();
			String addNewName = receivedFromClient.getName();
			String addNewSurname = receivedFromClient.getSurname();
			String addNewPersonCode = receivedFromClient.getPersonCode();
			String addNewEmail = receivedFromClient.getEmail();
			String addNewUsername = receivedFromClient.getUsername();
			String addNewPassword = receivedFromClient.getPassword();
			String addNewUserType = receivedFromClient.getUserType();
			
			UserDatabase db = new UserDatabase();
			try {
				db.createConnection();
				db.insertNewRecord(newID, addNewName, addNewSurname, addNewPersonCode, addNewEmail, addNewUsername, addNewPassword, addNewUserType);
				connect.sendTCP("New user added to database");
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
