package server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import application.ClientApplication;
import classes.appClient;
import classes.UsersData;

public class ClientListener extends Listener{
	
	@Override
	public void connected(Connection connection) {
		System.out.println("CLIENT >> New connection from server: "+connection.getID());
	}
	
	@Override
	public void disconnected(Connection connection) {
		System.out.println("CLIENT >> Disconnected from server: "+connection.getID());
	}
	
	@Override
	public void received(Connection connection, Object object) {
		if(object instanceof String) {
			if(object.equals("Login successful")) {
				ClientApplication.setLoginStatus("Login successful");
			}
			if(object.equals("Login unsuccessful")) {
				ClientApplication.setLoginStatus("Login unsuccessful");
			}
			if(object.equals("New user added to database")) {
				ClientApplication.setAddedUserStatus("New user added to database");
			}
		}
		if(object instanceof appClient) {
			appClient tempClient = (appClient) object;
			ClientApplication.setLogInClient(tempClient);
		}
	}
}
