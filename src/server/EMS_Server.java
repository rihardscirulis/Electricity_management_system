package server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;

public class EMS_Server {
	Server server;
	EMS_ServerListener ems_ServerListener;
	
	public EMS_Server() throws IOException {
		server = new Server();
		ems_ServerListener = new EMS_ServerListener();
		server.addListener(ems_ServerListener);
		server.bind(5000);
		registerPackets();
	}
	
	private void registerPackets() {
		Kryo kryo = server.getKryo();
		kryo.register(String.class);
	}
}
