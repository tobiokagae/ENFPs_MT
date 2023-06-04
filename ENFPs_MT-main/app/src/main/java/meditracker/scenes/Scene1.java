package meditracker.scenes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Scene1 extends MainScene {
    

    public Scene1(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        Image image = new Image("/fotoo.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(620);
        imageView.setFitWidth(620);
        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        Label mediTracker = new Label("MediTracker");
        mediTracker.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 35px; -fx-text-color: #000000; -fx-font-weight: bold;");
        HBox logInBox = new HBox(mediTracker);
        logInBox.setAlignment(Pos.CENTER);

        Label labelID = new Label("ID :");
        labelID.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 15px; -fx-text-color: #000000; -fx-font-weight: bold;");
        HBox idBox = new HBox(labelID);
        idBox.setAlignment(Pos.CENTER_LEFT);
        idBox.setPadding(new Insets(0, 0, 0, 165));

        TextField idField = new TextField();
        idField.setPromptText("Masukkan ID...");
        idField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3; -fx-font-weight: bold;");
        idField.setAlignment(Pos.CENTER);
        idField.setMinWidth(150);
        HBox idFieldBox = new HBox(idField);
        idFieldBox.setAlignment(Pos.CENTER);

        Label labelPin = new Label("PIN :");
        labelPin.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 15px; -fx-text-color: #000000; -fx-font-weight: bold;");
        HBox pinBox = new HBox(labelPin);
        pinBox.setAlignment(Pos.CENTER_LEFT);
        pinBox.setPadding(new Insets(0, 0, 0, 165));

        PasswordField pinField = new PasswordField();
        pinField.setPromptText("Masukkan PIN...");
        pinField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3; -fx-font-weight: bold;");
        pinField.setAlignment(Pos.CENTER);
        pinField.setMinWidth(150);
        HBox pinFieldBox = new HBox(pinField);
        pinFieldBox.setAlignment(Pos.CENTER);


        Button logInButton = new Button("LOG IN");
        logInButton.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Arial';-fx-padding: 10px 80px;-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-background-radius: 42px; -fx-border-radius: 42px; -fx-font-weight: bold;");
        HBox logInButtonBox = new HBox(logInButton);
        logInButtonBox.setAlignment(Pos.CENTER);
        logInButton.setOnAction(v -> {
            String id = idField.getText();
            String pin = pinField.getText();
            if (!id.isEmpty() && !pin.isEmpty()){
                try {
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:account.db");
                    String sql = "SELECT * FROM tb_dokter WHERE ID = ? AND PIN = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, id);
                    statement.setString(2, pin);

                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        Scene2 scene2 = new Scene2(stage);
                        scene2.show();
                    } else {
                        PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
                        pause2.setOnFinished(event -> {
                            Scene1 scene2 = new Scene1(stage);
                            scene2.show();
                            });
                        pause2.play();
                    }
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
                pause2.setOnFinished(event -> {

                });
                pause2.play();
            }
            logInButton.setOnAction(e -> {
                    String enteredID = idField.getText();
                    String enteredPIN = pinField.getText();
                
                    if (enteredID.isEmpty() || enteredPIN.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Harap Masukkan ID dan PIN");
                
                        // Mengatur gaya visual Alert
                        alert.getDialogPane().setStyle(
                                "-fx-background-color: #F8E0E0; " +
                                "-fx-border-color: #FF0000; " +
                                "-fx-border-width: 2px;" +
                                "-fx-font-weight: bold;");
                
                        // Mengatur gaya visual Alert
                        alert.getDialogPane().setStyle(
                                "-fx-background-color: #F8E0E0; " +
                                "-fx-border-color: #FF0000; " +
                                "-fx-border-width: 2px;" +
                                "-fx-font-weight: bold;");
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("ID dan Password salah");
                
                        // Mengatur gaya visual Alert
                        alert.getDialogPane().setStyle(
                                "-fx-background-color: #F8E0E0; " +
                                "-fx-border-color: #FF0000; " +
                                "-fx-border-width: 2px;" +
                                "-fx-font-weight: bold;");
                        alert.showAndWait();
                    }
                });
                
        });

        VBox inputBox = new VBox(4, idBox, idFieldBox, pinBox, pinFieldBox);
        VBox screenBox = new VBox(20, logInBox, inputBox, logInButtonBox);
        screenBox.setAlignment(Pos.CENTER);
        screenBox.setPadding(new Insets(255, 0, 0, 0));
        root.getChildren().add(screenBox);
        // screenBox.setBackground(null);

        Scene sceneLogIn = new Scene(root, 620, 620);
        // Dialog<ButtonType> stage;
        stage.setTitle("MediTracker");
        stage.setScene(sceneLogIn);
    }

}
