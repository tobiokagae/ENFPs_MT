package meditracker.scenes;

import javafx.stage.Stage;

public abstract class MainScene {
    public Stage stage;
    public MainScene(Stage stage){
        this.stage = stage;
    }

    abstract public void show();
}
