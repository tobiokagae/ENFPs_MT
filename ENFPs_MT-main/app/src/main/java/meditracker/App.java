package meditracker;
import javafx.application.Application;
import javafx.geometry.Pos;
// import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application{

    private Stage stage;
    public void start (Stage primaryStage) throws Exception{
        stage = primaryStage;
        showScene2();
        stage.show();
    }

// Scene 2: Menu Utama
/**
 * 
 */
public void showScene2() {
    Label title = new Label("NAMA PASIEN");
    title.getStyleClass().add("title-label");

    Button elDataButton = new Button("EL");
    elDataButton.getStyleClass().add("menu-button");
    // elDatabuttton.setFont(Font.font("Courier New", 14));
    elDataButton.setOnAction(action -> showDataEl());

    Button ChacahDataButton = new Button("CHACHA");
    ChacahDataButton.getStyleClass().add("menu-button");
    ChacahDataButton.setOnAction(action -> showDataChacha());

    Button AcliDataButton = new Button("ACLI");
    AcliDataButton.getStyleClass().add("menu-button");
    AcliDataButton.setOnAction(action -> showDataAcli());

    VBox layout = new VBox(10);
    layout.setAlignment(Pos.CENTER);
    layout.getStyleClass().add("scene2-layout");
    layout.getChildren().addAll(title, elDataButton, AcliDataButton, ChacahDataButton);

    Scene scene2 = new Scene(layout, 400, 400);
    // scene2.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

    stage.setScene(scene2);
}


// Scene 3.1: El
public void showDataEl() {
    Label title = new Label("DATA PASIEN");
    title.getStyleClass().add("title-label");

    Label namaEl = new Label("Nama : EL");
    Label umurEl = new Label("Nama : 19");
    Label ObatEl = new Label("Obat : Paracetamol");
    Label DiagnoseEl = new Label("Catatan : Sakit");

    Button backButton = new Button("Back to Menu");
    backButton.getStyleClass().add("back-button");
    backButton.setOnAction(action -> showScene2());

    VBox layout = new VBox(10,namaEl, umurEl, ObatEl, DiagnoseEl);
    layout.setAlignment(Pos.CENTER);
    layout.getStyleClass().add("scene3-layout");
    layout.getChildren().addAll(title, backButton);

    Scene scene3 = new Scene(layout, 400, 400);
    // scene3.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
    
    stage.setScene(scene3);
    }

// Scene 3.1: Chacah
public void showDataChacha() {
    Label title = new Label("DATA PASIEN");
    title.getStyleClass().add("title-label");

    Label namaChacha = new Label("Nama : chacha");
    Label umurChacha = new Label("Nama : 19");
    Label obatChacha = new Label("Obat : Paracetamol");
    Label DiagnoseChacha = new Label("Catatan : Sakit");

    Button backButton = new Button("Back to Menu");
    backButton.getStyleClass().add("back-button");
    backButton.setOnAction(action -> showScene2());

    VBox layout = new VBox(10, namaChacha, umurChacha, obatChacha, DiagnoseChacha);
    layout.setAlignment(Pos.CENTER);
    layout.getStyleClass().add("scene3-layout");
    layout.getChildren().addAll(title, backButton);

    Scene scene3 = new Scene(layout, 400, 400);
    // scene3.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
    
    stage.setScene(scene3);
    }

// Scene 3.1: Acli
public void showDataAcli() {
    Label title = new Label("DATA PASIEN");
    title.getStyleClass().add("title-label");

    Label namaAcli = new Label("Nama : acli");
    Label umurAcli = new Label("Nama : 19");
    Label ObatAcli = new Label("Obat : Paracetamol");
    Label DiagnoseAcli = new Label("Catatan : Sakit");

    Button backButton = new Button("Back to Menu");
    backButton.getStyleClass().add("back-button");
    backButton.setOnAction(action -> showScene2());

    VBox layout = new VBox(10, namaAcli, umurAcli,ObatAcli, DiagnoseAcli);
    layout.setAlignment(Pos.CENTER);
    // layout.getStyleClass().add("scene3-layout");
    layout.getChildren().addAll(title, backButton);

    Scene scene3 = new Scene(layout, 400, 400);
    // scene3.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
    
    stage.setScene(scene3);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
