package server;

import java.util.ArrayList;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class EMS_ServerListener extends Listener {
	public static ArrayList<Connection> connections = new ArrayList<>();
	
	public EMS_ServerListener() {
		
	}
	
	@Override
	public void connected(Connection c) {
		System.out.println("SERVER >> New connection: "+ c.getID());
		connections.add(c);
	}
	
	@Override
	public void disconnected(Connection c) {
		System.out.println("SERVER >> Disconnected: "+ c.getID());
		connections.remove(c);
	}
	
	@Override
	public void received(Connection c, Object o) {
		
	}
}
