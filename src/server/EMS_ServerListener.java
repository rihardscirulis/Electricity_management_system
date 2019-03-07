package server;

import java.sql.SQLException;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import classes.UsersData;
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
			System.out.println("Server received user object: ");
			UsersData receivedFromClient = (UsersData) object;
			System.out.println(receivedFromClient.toString());
			
			String checkUsersUsername = receivedFromClient.getUsername();
			String checkUsersPassword = receivedFromClient.getPassword();
			
			UserDatabase db = new UserDatabase();
			try {
				db.createConnection();
				if(db.checkLoginDataValidation(checkUsersUsername, checkUsersPassword) == true) {
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
	}
}
