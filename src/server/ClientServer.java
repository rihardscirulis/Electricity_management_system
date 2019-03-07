package server;

import java.io.IOException;
import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

import classes.UsersData;

public class ClientServer {
	public Client client;
	private ClientListener clientServerListener;
	
	public ClientServer() throws IOException, InterruptedException {
		configureAndStartClient();
	}
	
	private void registerPackets() {
		Kryo kryo = client.getKryo();
		kryo.register(String.class);
		kryo.register(UsersData.class);
		kryo.register(ArrayList.class);
	}
	
	public void configureAndStartClient() throws IOException, InterruptedException {
		client = new Client();
		clientServerListener = new ClientListener();
		client.addListener(clientServerListener);
		registerPackets();
		client.start();
		client.connect(5000, "127.0.0.1", 8001);
		Thread.sleep(15000);
	}
}
