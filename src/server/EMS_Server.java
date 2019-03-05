package server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

import classes.UsersData;

import java.io.IOException;
import java.util.ArrayList;

public class EMS_Server {
	Server server;
	EMS_ServerListener ems_ServerListener;
	
	public EMS_Server() throws IOException {
		server = new Server();
		ems_ServerListener = new EMS_ServerListener();
		server.addListener(ems_ServerListener);
		try {
			server.bind(5000);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		registerPackets();
		server.start();
	}
	
	private void registerPackets() {
		Kryo kryo = server.getKryo();
		kryo.register(String.class);
		kryo.register(UsersData.class);
		kryo.register(ArrayList.class);
	}
	
	public static void main(String[] args) throws IOException {
		Server server;
		EMS_ServerListener ems_ServerListener;
		server = new Server();
		ems_ServerListener = new EMS_ServerListener();
		server.addListener(ems_ServerListener);
		server.bind(8001);
		Kryo kryo = server.getKryo();
		kryo.register(String.class);
		//kryo.register(ArrayList.class);
		kryo.register(UsersData.class);
		server.start();
	}
}
