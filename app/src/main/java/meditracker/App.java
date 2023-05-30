package meditracker;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    protected Stage stage;
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;  
        createScene1();    
        stage.show();
    }

    private void createScene1(){
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);

        Label label1 = new Label("MediTracker");
        label1.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 35px; -fx-text-color: #000000; -fx-font-weight: bold;");
        vBox.getChildren().add(label1);

        ImageView logo = new ImageView(new Image("/meditrckr.png"));
        logo.setFitHeight(150);
        logo.setFitWidth(150);
        vBox.getChildren().add(logo);

        Button button1 = new Button("LOG IN");
        button1.setStyle("-fx-font-family: 'Cambria';-fx-padding: 10px 80px;-fx-font-size: 15px;-fx-background-color: #C4DFDF; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        button1.setOnAction(v -> {createScenePortal();});
        vBox.getChildren().add(button1);

        Button button2 = new Button("SIGN UP");
        button2.setStyle("-fx-font-family: 'Cambria';-fx-padding: 10px 76px;-fx-font-size: 15px;-fx-background-color: #C4DFDF; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        button2.setOnAction(v -> {createSceneSignUp();});
        vBox.getChildren().add(button2);

        HBox hBox1 = new HBox(10,vBox);
        hBox1.setAlignment(Pos.CENTER);
        
        Scene scene1 = new Scene(hBox1,620,620);
        stage.setTitle("MediTracker");
        stage.setScene(scene1);
    }

    private void createSceneLogInDokter() {
        Label label1 = new Label("Log-In");
        label1.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 35px; -fx-text-color: #000000; -fx-font-weight: bold;");
        HBox hBox1 = new HBox(label1);
        hBox1.setAlignment(Pos.CENTER);

        Text textId = new Text("ID:");
        textId.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox2 = new HBox(textId);
        hBox2.setAlignment(Pos.CENTER);

        TextField textField3 = new TextField();
        textField3.setStyle("-fx-padding: 1px 80px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        textField3.setPromptText("Masukkan ID...");
        HBox hBox3 = new HBox(textField3);
        hBox3.setAlignment(Pos.CENTER);

        Text textPassword = new Text("Password:");
        textPassword.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox4 = new HBox(textPassword);
        hBox4.setAlignment(Pos.CENTER);

        TextField textField4 = new TextField();
        textField4.setStyle("-fx-padding: 1px 80px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        textField4.setPromptText("Masukkan Password...");
        HBox hBox5 = new HBox(textField4);
        hBox5.setAlignment(Pos.CENTER);

        Button button1 = new Button("MASUK");
        button1.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Cambria';-fx-padding: 10px 80px;-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        button1.setOnAction(v -> {createScenePortalDokter();});
        HBox hBox6 = new HBox(button1);
        hBox6.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox(5, hBox2, hBox3, hBox4, hBox5);
        vBox1.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox(15, hBox1, vBox1, hBox6);
        vBox2.setAlignment(Pos.CENTER);

        Scene sceneLogInDokter = new Scene(vBox2, 620, 620);
        stage.setTitle("MediTracker");
        stage.setScene(sceneLogInDokter);
    }

    private void createScenePortal() {
        Label label = new Label("Pilih Portal");
        label.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 35px; -fx-text-color: #000000; -fx-font-weight: bold;");
        HBox hBox = new HBox(label);
        hBox.setAlignment(Pos.CENTER);

        Text text = new Text("Halo! Apakah kamu seorang dokter atau pasien?");
        text.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px; -fx-text-color: #C4DFDF;");
        HBox hBox1 = new HBox(text);
        hBox1.setAlignment(Pos.CENTER);

        Button button1 = new Button("PORTAL DOKTER");
        button1.setStyle("-fx-font-family: 'Cambria';-fx-padding: 10px 76px;-fx-font-size: 15px;-fx-background-color: #C4DFDF; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        button1.setOnAction(v -> {createSceneLogInDokter();});
        HBox hBox2 = new HBox(button1);
        hBox2.setAlignment(Pos.CENTER);

        Button button2 = new Button("PORTAL PASIEN");
        button2.setStyle("-fx-font-family: 'Cambria';-fx-padding: 10px 80px;-fx-font-size: 15px;-fx-background-color: #C4DFDF; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        button2.setOnAction(v -> {createSceneLogInPasien();});
        HBox hBox3 = new HBox(button2);
        hBox3.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox(5, hBox, hBox1);
        vBox2.setAlignment(Pos.CENTER);

        VBox vBox3 = new VBox(7, hBox2, hBox3);
        vBox3.setAlignment(Pos.CENTER);

        VBox vBox4 = new VBox(20, vBox2, vBox3);
        vBox4.setAlignment(Pos.CENTER);

        Scene scenePortal = new Scene(vBox4, 620, 620);
        stage.setTitle("MediTracker");
        stage.setScene(scenePortal);

    }

    private void createSceneLogInPasien() {
        Label label1 = new Label("Log-In");
        label1.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 35px; -fx-text-color: #000000; -fx-font-weight: bold;");
        HBox hBox1 = new HBox(label1);
        hBox1.setAlignment(Pos.CENTER);

        Text textUsername = new Text("Username:");
        textUsername.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox2 = new HBox(textUsername);
        hBox2.setAlignment(Pos.CENTER);

        TextField textField3 = new TextField();
        textField3.setStyle("-fx-padding: 1px 80px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        textField3.setPromptText("Masukkan Username...");
        HBox hBox3 = new HBox(textField3);
        hBox3.setAlignment(Pos.CENTER);

        Text textPassword = new Text("Password:");
        textPassword.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox4 = new HBox(textPassword);
        hBox4.setAlignment(Pos.CENTER);

        TextField textField4 = new TextField();
        textField4.setStyle("-fx-padding: 1px 80px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        textField4.setPromptText("Masukkan Password...");
        HBox hBox5 = new HBox(textField4);
        hBox5.setAlignment(Pos.CENTER);

        Button button1 = new Button("MASUK");
        button1.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Cambria';-fx-padding: 10px 80px;-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        button1.setOnAction(v -> {createScenePortalPasien();});
        HBox hBox6 = new HBox(button1);
        hBox6.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox(5, hBox2, hBox3, hBox4, hBox5);
        vBox1.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox(15, hBox1, vBox1, hBox6);
        vBox2.setAlignment(Pos.CENTER);

        Scene sceneLogInPasien = new Scene(vBox2, 620, 620);
        stage.setTitle("MediTracker");
        stage.setScene(sceneLogInPasien);
    }

    private void createScenePortalPasien() {
    }

    private void createScenePortalDokter() {
    }

    private void createSceneSignUp() {
        Label label1 = new Label("Sign-Up");
        label1.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 35px; -fx-text-color: #000000; -fx-font-weight: bold;");
        HBox hBox1 = new HBox(label1);
        hBox1.setAlignment(Pos.CENTER);

        Text textNama = new Text("Nama:");
        textNama.setFill(Color.web("#526D82"));
        textNama.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox2 = new HBox(textNama);
        hBox2.setAlignment(Pos.CENTER);

        TextField textField1 = new TextField();
        textField1.setStyle("-fx-padding: 1px 80px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        textField1.setPromptText("Masukkan Nama...");
        HBox hBox3 = new HBox(textField1);
        hBox3.setAlignment(Pos.CENTER);

        Text textEmail = new Text("E-Mail:");
        textEmail.setFill(Color.web("#526D82"));
        textEmail.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox4 = new HBox(textEmail);
        hBox4.setAlignment(Pos.CENTER);

        TextField textField2 = new TextField();
        textField2.setStyle("-fx-padding: 1px 80px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        textField2.setPromptText("Masukkan E-Mail...");
        HBox hBox5 = new HBox(textField2);
        hBox5.setAlignment(Pos.CENTER);

        Text textUsername = new Text("Username:");
        textUsername.setFill(Color.web("#526D82"));
        textUsername.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox6 = new HBox(textUsername);
        hBox6.setAlignment(Pos.CENTER);

        TextField textField3 = new TextField();
        textField3.setStyle("-fx-padding: 1px 80px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        textField3.setPromptText("Masukkan Username...");
        HBox hBox7 = new HBox(textField3);
        hBox7.setAlignment(Pos.CENTER);

        Text textPassword = new Text("Password:");
        textPassword.setFill(Color.web("#526D82"));
        textPassword.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox8 = new HBox(textPassword);
        hBox8.setAlignment(Pos.CENTER);

        TextField textField4 = new TextField();
        textField4.setStyle("-fx-padding: 1px 80px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        textField4.setPromptText("Masukkan Password...");
        HBox hBox9 = new HBox(textField4);
        hBox9.setAlignment(Pos.CENTER);

        Text textUmur = new Text("Umur: (*dalam angka)");
        textUmur.setFill(Color.web("#526D82"));
        textUmur.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox10 = new HBox(textUmur);
        hBox10.setAlignment(Pos.CENTER);

        TextField textField5 = new TextField();
        textField5.setStyle("-fx-padding: 1px 80px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        textField5.setPromptText("Masukkan Umur...");
        HBox hBox11 = new HBox(textField5);
        hBox11.setAlignment(Pos.CENTER);

        Button button1 = new Button("NEXT");
        button1.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Cambria';-fx-padding: 10px 80px;-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        button1.setOnAction(v -> {createSceneBerhasil();});
        HBox hBox12 = new HBox(button1);
        hBox12.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox(10, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7, hBox8, hBox9, hBox10, hBox11);
        vBox1.setAlignment(Pos.CENTER);
        
        VBox vBox2 = new VBox(15, hBox1, vBox1, hBox12);
        vBox2.setAlignment(Pos.CENTER);

        Scene sceneSignUp = new Scene(vBox2, 620, 620);
        stage.setTitle("MediTracker");
        stage.setScene(sceneSignUp);
    }

    private void createSceneBerhasil() {
        Label label = new Label("SELAMAT!!!");
        label.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 40px; -fx-text-color: #000000; -fx-font-weight: bold;");
        
        Text text = new Text("Anda telah berhasil membuat akun! \nsSilahkan kembali ke halaman utama");
        text.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");

        Button button = new Button("KEMBALI");
        button.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Cambria';-fx-font-size: 13px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        button.setOnAction(v -> {createScene1();});
        HBox hBox = new HBox(button);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox(7, label, text);
        vBox1.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(15, vBox1, hBox);
        vBox.setAlignment(Pos.CENTER);

        Scene sceneBerhasil = new Scene(vBox, 620, 620);
        stage.setTitle("MediTracker");
        stage.setScene(sceneBerhasil);
    }
}
