package server;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import application.ClientApplication;
import classes.appClient;
import classes.Measurement;
import classes.UsersData;
import classes.addClient;

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
			System.out.println("Logged in ID: "+tempClient.getID());
			System.out.println("Username: "+tempClient.getSurname());
			System.out.println("Name: "+tempClient.getName());
		}
		if(object instanceof ArrayList<?>) {
			if(((ArrayList<?>) object).get(0) instanceof addClient) {
				ArrayList<addClient> listOfAllUsers = (ArrayList<addClient>) object;
				ClientApplication.setUsersListFromServer(listOfAllUsers);
				System.out.println("CLIENT >> Received all users list with size:" +listOfAllUsers.size());
			}
			if(((ArrayList<?>) object).get(0) instanceof Measurement) {
				ArrayList<Measurement> listOfAllMeasurements = (ArrayList<Measurement>) object;
				ClientApplication.setAllUserMeasurementListFromServer(listOfAllMeasurements);
				System.out.println("CLIENT >> Received all users list with size: " +listOfAllMeasurements.size());
			}
		}
	}
}
