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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClientApplication extends Application {

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
        Scene loginScene = new Scene(grid, 450, 275);
        primaryStage.setScene(loginScene);
        
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 3, 1);

        Label userName = new Label("Username:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label passWord = new Label("Password:");
        grid.add(passWord, 0, 2);

        PasswordField passwordBox = new PasswordField();
        grid.add(passwordBox, 1, 2);
        
        Button loginButton = new Button("Log in");
        HBox hBoxLogInButton = new HBox(10);
        hBoxLogInButton.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxLogInButton.getChildren().add(loginButton);
        grid.add(hBoxLogInButton, 1, 3);
        
        Label info = new Label("If you are first time, please click button below");
        grid.add(info, 1, 4);
        
        Button signInButton = new Button("Sign in");
        HBox hBoxSignInButton = new HBox(10);
        hBoxSignInButton.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxSignInButton.getChildren().add(signInButton);
        grid.add(hBoxSignInButton, 1, 5);
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
    }
}
