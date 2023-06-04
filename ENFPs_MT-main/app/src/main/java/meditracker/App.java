package meditracker;


// import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;
// import javafx.util.Duration;
import meditracker.scenes.Scene1;

public class App extends Application {

    // private ObservableList<String> listPasien = FXCollections.observableArrayList();
    // public void start(Stage primaryStage) throws Exception{
    //     stage = primaryStage;  
    // Initialize listPasien
        
        @Override
        public void start(Stage stage) {
            Scene1 scene1 = new Scene1(stage);
            scene1.show();
            stage.setTitle("MediTracker");
            stage.show();
        }
    
    public static void main(String[] args) {
        launch();
    }
}