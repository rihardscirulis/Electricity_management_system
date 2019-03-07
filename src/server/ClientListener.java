package server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

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
			String result = (String) object;
			System.out.println("Message from SERVER: ");
			System.out.println(result);
		}
		
		if(object instanceof UsersData) {
			String result = (String) object;
			System.out.println("Message from SERVER: ");
			System.out.println(result);
		}	
	}
}
