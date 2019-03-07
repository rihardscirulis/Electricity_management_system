package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.ClientListener;
import server.ClientServer;
import server.EMS_Server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

import classes.UsersData;
import database.UserDatabase;

public class ClientApplication extends Application {

	Scene loginScene;
	Scene adminScene;
	Scene clientScene;
	Scene addUserScene;
	
	public static void main(String[] args) {
        launch(args);
    }
	
	//Funkcija, kas atïauj klientam pievienoties serverim
	/*public Client connectToServer() throws IOException {
		//1. izveido klientu
		Client client = new Client();
		
		//2. pievieno klausîtâju
		ClientListener clientListener = new ClientListener();
		client.addListener(clientListener);
		
		//3. reìistrç klases
		Kryo kryo = client.getKryo();
		kryo.register(String.class);
		//kryo.register(ArrayList.class);
		kryo.register(UsersData.class);
		
		//4. palaiþ klientu
		client.start();
		
		//5. pievienojas serverim
		client.connect(5000, "127.0.0.1", 8001);
		return client;
	}*/
	
    @Override
    public void start(Stage primaryStage) {
    	
        primaryStage.setTitle("Electricity Management System");
        primaryStage.show();
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        GridPane grid2 = new GridPane();
        Scene adminScene = new Scene(grid2, 350, 200);
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(25, 25, 25, 25));
        
        GridPane grid3 = new GridPane();
        Scene clientScene = new Scene(grid3, 350, 200);
        grid3.setAlignment(Pos.CENTER);
        grid3.setHgap(10);
        grid3.setVgap(10);
        grid3.setPadding(new Insets(25, 25, 25, 25));
        
        GridPane grid4 = new GridPane();
        Scene addUserScene = new Scene(grid4, 350, 200);
        grid4.setAlignment(Pos.CENTER);
        grid4.setHgap(10);
        grid4.setVgap(10);
        grid4.setPadding(new Insets(25, 25, 25, 25));
        
        // ---<< Pierakstîðanâs logs >>---
        Scene loginScene = new Scene(grid, 450, 275);
        
	        Text scenetitle_1 = new Text("Electricity Management System");
	        scenetitle_1.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
	        grid.add(scenetitle_1, 0, 0, 3, 1);
	
	        Label userName = new Label("Username:");
	        grid.add(userName, 0, 1);
	
	        TextField usernameTextField = new TextField();
	        grid.add(usernameTextField, 1, 1);
	
	        Label passwordName = new Label("Password:");
	        grid.add(passwordName, 0, 2);
	
	        PasswordField passwordTextField = new PasswordField();
	        grid.add(passwordTextField, 1, 2);
	        
	        Button loginButton = new Button("Log in");
	        HBox hBoxLogInButton = new HBox(10);
	        hBoxLogInButton.setAlignment(Pos.BOTTOM_RIGHT);
	        hBoxLogInButton.getChildren().add(loginButton);
	        grid.add(hBoxLogInButton, 1, 3);
        
	        //Pierakstîðanâs pogas darbîba
	        loginButton.setOnAction(event -> {
	        	String username = usernameTextField.getText();
	        	String password = passwordTextField.getText();
	        	UsersData user = new UsersData(username, password);
	        	UserDatabase db = new UserDatabase();
	        	try {
					db.createConnection();
				} 
	        	catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
	        	try {
					ClientServer clientServer = new ClientServer();
					Client client = new Client();
					clientServer.client.sendTCP(user);
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	
	        	//try {
	        		//boolean correctLogin = false;
	        		//correctLogin = db.checkLoginDataValidation(username, password);
					//if(correctLogin == true) {
						//Client client = new Client();
						//client = connectToServer();
						//client.sendTCP(user); //nosûta lietotâju uz serveri
						boolean userType;
						/*if(userType = db.checkAdminOrUser(username, password, "admin") == true) {
							primaryStage.setScene(adminScene);
						}
						else if(userType = db.checkAdminOrUser(username, password, "client") == true){
							primaryStage.setScene(clientScene);
						}*/
					//}
					//else {
					//	Label info = new Label("Incorrect username or password!");
					//	info.setTextFill(Color.web("Red"));
				    //    grid.add(info, 1, 6);
					//}
				//} 
	        	//catch (SQLException e) {
				//	e.printStackTrace();
				//}
	        	try {
					db.endConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    	});
	        
	        
	    // -------------------------------------------------------------------------------------
	        
	    // ---<< Administratora scenas logs >>---
	        Text adminText = new Text("Administrator interface");
	        adminText.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
	        grid2.add(adminText, 0, 0, 3, 1);
	        
	        Button addUser = new Button("Add User");
	        grid2.add(addUser, 0, 1);
	        addUser.setOnAction(event -> {
	        	primaryStage.setScene(addUserScene);
	        	Text addUserText = new Text("Add User");
	        	addUserText.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		        grid4.add(addUserText, 0, 0, 3, 1);
	        });
	        
	        Button checkUser = new Button("Check User");
	        grid2.add(checkUser, 1, 1);
	        
	        Label measureLabelAdmin = new Label("Measurement:");
	        grid2.add(measureLabelAdmin, 0, 2);
	
	        TextField measurementAdmin = new TextField();
	        grid2.add(measurementAdmin, 0, 3); 
	        
	        Button acceptMeasureAdmin = new Button("Accept");
	        grid2.add(acceptMeasureAdmin, 0, 4);
	        
	        Button getDocumentAdmin = new Button("Get Document");
	        grid2.add(getDocumentAdmin, 1, 4);
	    // -------------------------------------------------------------------------------------
	        
	    // ---<< Klienta scenas logs >>---
	        Text clientText = new Text("Client interface");
	        clientText.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
	        grid3.add(clientText, 0, 0, 2, 1);
	        
	        Label measureLabelClient = new Label("Measurement:");
	        grid3.add(measureLabelClient, 0, 1);
	        
	        TextField measurementClient = new TextField();
	        grid3.add(measurementClient, 0, 2);
	        
	        Button acceptMeasureClient = new Button("Accept");
	        grid3.add(acceptMeasureClient, 0, 3);
	        
	        Button getDocumentClient = new Button("Get Document");
	        grid3.add(getDocumentClient, 1, 3);
	    // -------------------------------------------------------------------------------------
	        primaryStage.setScene(loginScene);
	        primaryStage.show();
    }
}
