/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package meditracker;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    private Stage stage;
    public void start(Stage primaryStage) throws Exception{
        
        stage = primaryStage;  
        SceneAwal();    
        stage.show();

    }

    private void SceneAwal(){
        Label label = new Label("  Welcome To");
        label.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 25px; -fx-text-color: #000000;");
        Label label2 = new Label("AlmShoeStore");
        label2.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 35px; -fx-text-color: #000000;");

        VBox vbox1 =new VBox(label,label2);
        vbox1.setAlignment(Pos.TOP_CENTER);
        
        Label label3 = new Label("Login Member");
        label3.setStyle("-fx-padding: 5px 25px;-fx-font-size: 30px; -fx-font-family: 'Times New Roman'; -fx-text-fill: BLACK;-fx-background-color: #FAEBD7; -fx-border-color: WHITE; -fx-border-width: 1px; -fx-border-radius: 5;");
        Label label4= new Label();
        VBox vbox2 = new VBox(10,label4,label3);
        vbox2.setAlignment(Pos.TOP_CENTER);

        TextField usernameField = new TextField();
        usernameField.setStyle("-fx-padding: 1px 32px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #777777; -fx-border-color: #FAEBD7; -fx-border-width: 1px; -fx-border-radius: 3;");
        usernameField.setPromptText("Username...");

        TextField passwordField = new TextField();
        passwordField.setStyle("-fx-padding: 1px 32px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #777777; -fx-border-color: #FAEBD7; -fx-border-width: 1px; -fx-border-radius: 3;");
        passwordField.setPromptText("Password...");

        HBox hbox1 = new HBox(usernameField);
        hbox1.setAlignment(Pos.CENTER);
        
        HBox hbox2 = new HBox(passwordField);
        hbox2.setAlignment(Pos.CENTER);

        Button button1 = new Button("SIGN IN");
        button1.setStyle("-fx-font-family: 'Cambria';-fx-padding: 10px 95px;-fx-text-fill:#FAEBD7;-fx-font-size: 15px;-fx-background-color: #006400; -fx-border-color: WHITE; -fx-border-width: 1px; -fx-border-radius: 2;");


        Button button4 = new Button("Not Have Account?");
        button4.setStyle("-fx-font-size: 10px; -fx-font-family: 'Times New Roman'; -fx-text-fill: BLACK;");

        Button button2 = new Button("REGISTER");
        button2.setStyle("-fx-font-family: 'Cambria';-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FAEBD7; -fx-border-color: #FAEBD7; -fx-border-width: 1px; -fx-border-radius: 2;");

        Button button3 = new Button("EXIT");
        button3.setStyle("-fx-font-family: 'Cambria';-fx-text-fill:RED;-fx-font-size: 15px;-fx-background-color: #FAEBD7; -fx-border-color: #FAEBD7; -fx-border-width: 1px; -fx-border-radius: 2;");

        HBox hbox3 = new HBox(5,button2,button3);
        hbox3.setAlignment(Pos.CENTER);

        VBox asli = new VBox(10,vbox1,vbox2,hbox1,hbox2,button1,button4,hbox3);
        asli.setAlignment(Pos.CENTER);
        asli.setStyle("-fx-background-color:#DCDCDC;");
        // asli.getChildren().addAll(vbox1,vbox2,vbox3);
        
        Scene scene1 = new Scene(asli,620,620);
        stage.setTitle("AlmShoeStore");
        stage.setScene(scene1);

        passwordField.setOnAction(event -> button1.fire());
}
}
