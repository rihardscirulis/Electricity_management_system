package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

import classes.appClient;
import classes.UsersData;
import classes.addClient;
import database.UserDatabase;

public class ClientApplication extends Application {

	private static ClientServer clientserver; //klienta servera objekts
	private static String loginStatus = ""; //mainigais kas sanem no servera listener atbildi 
	private static appClient logInClient;
	private static String addedUser = "";
	
	Scene loginScene;
	Scene adminScene;
	Scene clientScene;
	Scene addUserScene;
	Scene checkUserScene;
	
	public static void main(String[] args) {
        launch(args);
    }
	
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
        Scene addUserScene = new Scene(grid4, 470, 400);
        grid4.setAlignment(Pos.CENTER);
        grid4.setHgap(10);
        grid4.setVgap(10);
        grid4.setPadding(new Insets(25, 25, 25, 25));
        
        GridPane grid5 = new GridPane();
        Scene checkUserScene = new Scene(grid5, 470, 400);
        grid5.setAlignment(Pos.CENTER);
        grid5.setHgap(10);
        grid5.setVgap(10);
        grid5.setPadding(new Insets(25, 25, 25, 25));
        
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
	        	try {
					clientserver = new ClientServer();
					clientserver.getClient().sendTCP(user);
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	while(loginStatus.equals("")) {
	        		try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        	if(loginStatus.equals("Login successful")) {
	        		if(logInClient.getUserType().equals("admin")) {
	        			primaryStage.setScene(adminScene);
	        		}
	        		else if(logInClient.getUserType().equals("client")) {
	        			primaryStage.setScene(clientScene);
	        		}
	        	}
	        	else if(loginStatus.equals("Login unsuccessful")) {
	        		Label info = new Label("Incorrect username or password!");
					info.setTextFill(Color.web("Red"));
				    grid.add(info, 1, 6);
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
	        });
	        
	     // ---<< Administratora lietotaju pievienoðanas scena >>---
	        Text addUserText = new Text("Add User");
        	addUserText.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
	        grid4.add(addUserText, 0, 0, 3, 1);
	        
	        Label regUserName = new Label("Username:");
	        grid4.add(regUserName, 0, 1);
	
	        TextField regUserNameTextField = new TextField();
	        grid4.add(regUserNameTextField, 1, 1);
	        
	        Label regPassword = new Label("Password:");
	        grid4.add(regPassword, 0, 2);
	        
	        TextField regPasswordTextField = new TextField();
	        grid4.add(regPasswordTextField, 1, 2);
	        
	        Label regName = new Label("Name:");
	        grid4.add(regName, 0, 4);
	        
	        TextField regNameTextField = new TextField();
	        grid4.add(regNameTextField, 1, 4);
	        
	        Label regSurname = new Label("Surname:");
	        grid4.add(regSurname, 0, 5);
	        
	        TextField regSurnameTextField = new TextField();
	        grid4.add(regSurnameTextField, 1, 5);
	        
	        Label regPersonCode = new Label("Person code:");
	        grid4.add(regPersonCode, 0, 6);
	        
	        TextField regPersonCodeTextField_1 = new TextField();
	        grid4.add(regPersonCodeTextField_1, 1, 6);
	        
	        Label personCodeLine = new Label("-");
	        grid4.add(personCodeLine, 2, 6);
	        
	        TextField regPersonCodeTextField_2 = new TextField();
	        grid4.add(regPersonCodeTextField_2, 3, 6);
	        
	        Label regEmail = new Label("Email:");
	        grid4.add(regEmail, 0, 7);
	        
	        TextField regEmailTextField = new TextField();
	        grid4.add(regEmailTextField, 1, 7);
	        
	        Label regUserType = new Label("User type:");
	        grid4.add(regUserType, 0, 9);
	        
	        final ComboBox userTypeComboBox = new ComboBox();
	        userTypeComboBox.getItems().addAll(
	        		"admin",
	        		"client"
	        );
	        userTypeComboBox.setValue("admin");
	        grid4.add(userTypeComboBox, 1, 9);
	        
	        Button addPersonInDB = new Button("Add");
	        HBox hBoxaddPersonInDB = new HBox(10);
	        hBoxaddPersonInDB.setAlignment(Pos.BOTTOM_LEFT);
	        hBoxaddPersonInDB.getChildren().add(addPersonInDB);
	        grid4.add(hBoxaddPersonInDB, 0, 10);
	        addPersonInDB.setOnAction(event -> {
	        	String newUsername = regUserNameTextField.getText();
	        	String newPassword = regPasswordTextField.getText();
	        	String newName = regNameTextField.getText();
	        	String newSurname = regSurnameTextField.getText();
	        	
	        	String newPersonCode_1 = regPersonCodeTextField_1.getText();
	        	String newPersonCode_2 = regPersonCodeTextField_2.getText();
	        	String newPersonCode = newPersonCode_1 + newPersonCode_2;
	        	
	        	String newEmail = regEmailTextField.getText();
	        	String newUserType = (String) userTypeComboBox.getValue();
	        	
	        	addClient addClientInDB = new addClient(newName, newSurname, newPersonCode, newEmail, newUsername, newPassword, newUserType);
	        	clientserver.getClient().sendTCP(addClientInDB);
	        });
        	if(addedUser.equals("New user added to database")) {
        		Label infoAdd = new Label("New user succesfully added in database!");
        		infoAdd.setTextFill(Color.web("Red"));
			    grid4.add(infoAdd, 0, 10);
        	}
        	Button backPreviousScene = new Button("Back");
	        HBox hBoxbackPreviousScene = new HBox(10);
	        hBoxbackPreviousScene.setAlignment(Pos.BOTTOM_LEFT);
	        hBoxbackPreviousScene.getChildren().add(backPreviousScene);
	        grid4.add(hBoxbackPreviousScene, 1, 10);
	        backPreviousScene.setOnAction(event -> {
	        	primaryStage.setScene(adminScene);
	        });
	     // -------------------------------------------------------------------------------------
	        
	        Button checkUser = new Button("Check Users");
	        grid2.add(checkUser, 1, 1);
	        checkUser.setOnAction(event -> {
	        	primaryStage.setScene(checkUserScene);
	        });
	        Text checkUserText = new Text("Check users");
	        checkUserText.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
	        grid5.add(checkUserText, 0, 0, 3, 1);
	        
	        TableView<appClient> allUsersInTable = new TableView<>();
	        TableColumn<addClient, Integer> userIDColumn = new TableColumn<>("User ID");
	        TableColumn<addClient, String> userNameColumn = new TableColumn<>("Name");
	        TableColumn<addClient, String> userSurnameColumn = new TableColumn<>("Surname");
	        TableColumn<addClient, String> userPersonCodeColumn = new TableColumn<>("Person code");
	        TableColumn<addClient, String> userEmailColumn = new TableColumn<>("Email");
	        TableColumn<addClient, String> userUsernameColumn = new TableColumn<>("Username");
	        TableColumn<addClient, String> userPasswordColumn = new TableColumn<>("Password");
	        TableColumn<addClient, String> userUserTypeColumn = new TableColumn<>("User type");
	        
	        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
	        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	        userSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
	        userPersonCodeColumn.setCellValueFactory(new PropertyValueFactory<>("personCode"));
	        userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
	        userUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
	        userPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("pasword"));
	        userUserTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
	        
	        
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
    
    //funkcija, kas tiek klat clientlistener atbildei par login statusu
    public static void setLoginStatus(String status) {
    	loginStatus = status;
    }
    
    //funkcija, kas lauj clientlisteneram tik klat client privatam objektam
    public static void setLogInClient(appClient client) {
    	logInClient = client;
    }
    
    public static void setAddedUserStatus(String status) {
    	addedUser = status;
    }
}
