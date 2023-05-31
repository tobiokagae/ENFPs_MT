package meditracker;

import java.lang.ModuleLayer.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import meditracker.Database.Manager.cntlr;

public class App extends Application {
    protected Stage stage;
    // private cntlr controller = new cntlr();
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;  
        createScene1();    
        stage.show();
    }

    private void createScene1(){
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);

        Label label = new Label("MediTracker");
        label.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 35px; -fx-text-color: #000000; -fx-font-weight: bold;");
        vBox.getChildren().add(label);

        ImageView logo = new ImageView(new Image("/meditrckr.png"));
        logo.setFitHeight(150);
        logo.setFitWidth(150);
        vBox.getChildren().add(logo);

        Button buttonLogin = new Button("LOG IN");
        buttonLogin.setStyle("-fx-font-family: 'Cambria';-fx-padding: 10px 80px;-fx-font-size: 15px;-fx-background-color: #C4DFDF; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        buttonLogin.setOnAction(v -> {createScenePortal();});
        vBox.getChildren().add(buttonLogin);

        Button buttonSignup = new Button("SIGN UP");
        buttonSignup.setStyle("-fx-font-family: 'Cambria';-fx-padding: 10px 76px;-fx-font-size: 15px;-fx-background-color: #C4DFDF; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        buttonSignup.setOnAction(v -> {createSceneSignUp();});
        vBox.getChildren().add(buttonSignup);

        HBox hBox1 = new HBox(10,vBox);
        hBox1.setAlignment(Pos.CENTER);
        
        Scene scene1 = new Scene(hBox1,480, 720);
        stage.setTitle("MediTracker");
        stage.setScene(scene1);
    }

    private void createSceneLogInDokter() {
        Label label = new Label("Log-In");
        label.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 35px; -fx-text-color: #000000; -fx-font-weight: bold;");
        HBox hBox1 = new HBox(label);
        hBox1.setAlignment(Pos.CENTER);

        // Label label1 = new Label();
        // label1.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        // VBox hBoxxx = new VBox(label);
        // hBoxxx.setAlignment(Pos.CENTER);

        Text textId = new Text("ID :");
        textId.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        textId.setFill(Color.web("#526D82"));
        HBox hBox2 = new HBox(textId);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.setPadding(new Insets(0, 0, 0, 95));

        TextField idField = new TextField();
        idField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        idField.setPromptText("Masukkan ID...");
        idField.setAlignment(Pos.CENTER);
        HBox hBox3 = new HBox(idField);
        hBox3.setAlignment(Pos.CENTER);

        Text textPin = new Text("Pin :");
        textPin.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        textPin.setFill(Color.web("#526D82"));
        HBox hBox4 = new HBox(textPin);
        hBox4.setAlignment(Pos.CENTER_LEFT);
        hBox4.setPadding(new Insets(0, 0, 0, 95));

        PasswordField pinField = new PasswordField();
        pinField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        pinField.setPromptText("Masukkan Password...");
        pinField.setAlignment(Pos.CENTER);
        HBox hBox5 = new HBox(pinField);
        hBox5.setAlignment(Pos.CENTER);

        Button buttonSignin = new Button("SIGN IN");
        buttonSignin.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Cambria';-fx-padding: 10px 80px;-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        // buttonSignin.setOnAction(v -> {createScenePortalDokter();});
        buttonSignin.setOnAction(a -> {
            String id = idField.getText();
            String pin = pinField.getText();
            if (!id.isEmpty() && !pin.isEmpty()){
                try {
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\USER\\OneDrive\\Documents\\JavaFX\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager/account.db");
                    String sql = "SELECT * FROM tb_dokter WHERE ID = ? AND PIN = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, id);
                    statement.setString(2, pin);

                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        // Kembali ke scene toko setelah beberapa waktu
                        PauseTransition pause = new PauseTransition(Duration.seconds(2));
                        pause.setOnFinished(event -> createScenePortalDokter());
                        pause.play();
                    } else {
                        PauseTransition pause2 = new PauseTransition(Duration.seconds(2));
                        pause2.setOnFinished(event -> {createSceneLogInDokter();});
                        pause2.play();
                    }
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                PauseTransition pause2 = new PauseTransition(Duration.seconds(2));
                pause2.setOnFinished(event -> {createSceneLogInDokter();});
                pause2.play();
            }
        });
        HBox hBox6 = new HBox(buttonSignin);
        hBox6.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox(5, hBox2, hBox3, hBox4, hBox5);
        vBox1.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox(15, hBox1, vBox1, hBox6);
        vBox2.setAlignment(Pos.CENTER);

        Scene sceneLogInDokter = new Scene(vBox2, 480, 720);
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

        Button buttonPortalD = new Button("PORTAL DOKTER");
        buttonPortalD.setStyle("-fx-font-family: 'Cambria';-fx-padding: 10px 76px;-fx-font-size: 15px;-fx-background-color: #C4DFDF; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        buttonPortalD.setOnAction(v -> {createSceneLogInDokter();});
        HBox hBox2 = new HBox(buttonPortalD);
        hBox2.setAlignment(Pos.CENTER);

        Button buttonPortalP = new Button("PORTAL PASIEN");
        buttonPortalP.setStyle("-fx-font-family: 'Cambria';-fx-padding: 10px 80px;-fx-font-size: 15px;-fx-background-color: #C4DFDF; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        buttonPortalP.setOnAction(v -> {createSceneLogInPasien();});
        HBox hBox3 = new HBox(buttonPortalP);
        hBox3.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox(5, hBox, hBox1);
        vBox2.setAlignment(Pos.CENTER);

        VBox vBox3 = new VBox(7, hBox2, hBox3);
        vBox3.setAlignment(Pos.CENTER);

        VBox vBox4 = new VBox(20, vBox2, vBox3);
        vBox4.setAlignment(Pos.CENTER);

        Scene scenePortal = new Scene(vBox4, 480, 720);
        stage.setTitle("MediTracker");
        stage.setScene(scenePortal);

    }

    private void createSceneLogInPasien() {
        Label label = new Label("Log-In");
        label.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 35px; -fx-text-color: #000000; -fx-font-weight: bold;");
        HBox hBox1 = new HBox(label);
        hBox1.setAlignment(Pos.CENTER);

        Text textUsername = new Text("Username :");
        textUsername.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        textUsername.setFill(Color.web("#526D82"));
        HBox hBox2 = new HBox(textUsername);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.setPadding(new Insets(0, 0, 0, 95));

        TextField usernameField = new TextField();
        usernameField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        usernameField.setPromptText("Masukkan Username...");
        usernameField.setAlignment(Pos.CENTER);
        HBox hBox3 = new HBox(usernameField);
        hBox3.setAlignment(Pos.CENTER);

        Text textPassword = new Text("Password :");
        textPassword.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        textPassword.setFill(Color.web("#526D82"));
        HBox hBox4 = new HBox(textPassword);
        hBox4.setAlignment(Pos.CENTER_LEFT);
        hBox4.setPadding(new Insets(0, 0, 0, 95));

        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        passwordField.setPromptText("Masukkan Password...");
        passwordField.setAlignment(Pos.CENTER);
        HBox hBox5 = new HBox(passwordField);
        hBox5.setAlignment(Pos.CENTER);

        Button buttonSignin = new Button("SIGN IN");
        buttonSignin.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Cambria';-fx-padding: 10px 80px;-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        buttonSignin.setOnAction(v -> {createScenePortalPasien();});
        HBox hBox6 = new HBox(buttonSignin);
        hBox6.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox(5, hBox2, hBox3, hBox4, hBox5);
        vBox1.setAlignment(Pos.CENTER);

        VBox vBox2 = new VBox(15, hBox1, vBox1, hBox6);
        vBox2.setAlignment(Pos.CENTER);

        Scene sceneLogInPasien = new Scene(vBox2, 480, 720);
        stage.setTitle("MediTracker");
        stage.setScene(sceneLogInPasien);
    }

    private void createScenePortalPasien() {
        Text textNamaDokter = new Text("Nama Dokter :");
        textNamaDokter.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        textNamaDokter.setFill(Color.web("#526D82"));
        HBox hBox1 = new HBox(textNamaDokter);
        hBox1.setAlignment(Pos.CENTER_LEFT);
        hBox1.setPadding(new Insets(0, 0, 0, 95));

        Text textDiagnosis = new Text("Diagnosis/Riwayat Penyakit :");
        textDiagnosis.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        textDiagnosis.setFill(Color.web("#526D82"));
        HBox hBox2 = new HBox(textDiagnosis);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.setPadding(new Insets(0, 0, 0, 95));

        Text textObat = new Text("Diagnosis/Riwayat Penyakit :");
        textObat.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        textObat.setFill(Color.web("#526D82"));
        HBox hBox3 = new HBox(textObat);
        hBox3.setAlignment(Pos.CENTER_LEFT);
        hBox3.setPadding(new Insets(0, 0, 0, 95));

        VBox vBox = new VBox(10, hBox1);
        vBox.setAlignment(Pos.CENTER);

        Scene scenePortalP = new Scene(vBox, 480, 720);
        stage.setTitle("MediTracker");
        stage.setScene(scenePortalP);
    }

    private void createScenePortalDokter() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(50, 0, 0, 0));
        vBox.setSpacing(10);

        HBox hBox1 = new HBox();
        hBox1.setSpacing(20);

        Label label = new Label("Data Pasien");
        label.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 30px; -fx-text-color: #000000; -fx-font-weight: bold;");
        // HBox hBox = new HBox(label);
        // hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(label);
        hBox1.getChildren().add(vBox);
        // ImageView pasienLogo = new ImageView(new Image("/pasien.png"));
        for (int i = 0; i < 7; i++) {
            ImageView pasienLogo = new ImageView(new Image("/pasien.png"));
            pasienLogo.setFitHeight(50);
            pasienLogo.setFitWidth(50);
            vBox.getChildren().add(pasienLogo);
            for (String username : cntlr.getDataPasienUsername()) {
                Label label1 = new Label(username);
                label1.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 20px;");
                hBox1.getChildren().add(label1);
            }
            // cntlr.getDataPasienUsername();
            // vBox.getChildren().add();
        }
        // for (String username : cntlr.getDataPasienUsername()) {
        //     Label label1 = new Label(username);
        //     label1.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 20px;");
        // }


        ScrollPane scrollPortalD = new ScrollPane();
        scrollPortalD.setFitToWidth(true);
        scrollPortalD.setFitToHeight(true);
        scrollPortalD.setContent(vBox);

        Scene scenePortalD = new Scene(scrollPortalD, 480, 720);
        stage.setTitle("MediTracker");
        stage.setScene(scenePortalD);
    }

    private void createSceneSignUp() {
        Label label = new Label("Sign-Up");
        label.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 35px; -fx-text-color: #000000; -fx-font-weight: bold;");
        HBox hBox1 = new HBox(label);
        hBox1.setAlignment(Pos.CENTER);

        Text textNama = new Text("Nama :");
        textNama.setFill(Color.web("#526D82"));
        textNama.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox2 = new HBox(textNama);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.setPadding(new Insets(0, 0, 0, 95));

        TextField namaField = new TextField();
        namaField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        namaField.setPromptText("Masukkan Nama...");
        namaField.setAlignment(Pos.CENTER);
        HBox hBox3 = new HBox(namaField);
        hBox3.setAlignment(Pos.CENTER);

        Text textEmail = new Text("E-Mail :");
        textEmail.setFill(Color.web("#526D82"));
        textEmail.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox4 = new HBox(textEmail);
        hBox4.setAlignment(Pos.CENTER_LEFT);
        hBox4.setPadding(new Insets(0, 0, 0, 95));

        TextField emailField = new TextField();
        emailField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        emailField.setPromptText("Masukkan E-Mail...");
        emailField.setAlignment(Pos.CENTER);
        HBox hBox5 = new HBox(emailField);
        hBox5.setAlignment(Pos.CENTER);

        Text textUsername = new Text("Username :");
        textUsername.setFill(Color.web("#526D82"));
        textUsername.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox6 = new HBox(textUsername);
        hBox6.setAlignment(Pos.CENTER_LEFT);
        hBox6.setPadding(new Insets(0, 0, 0, 95));

        TextField usernameField = new TextField();
        usernameField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        usernameField.setPromptText("Masukkan Username...");
        usernameField.setAlignment(Pos.CENTER);
        HBox hBox7 = new HBox(usernameField);
        hBox7.setAlignment(Pos.CENTER);

        Text textPassword = new Text("Password :");
        textPassword.setFill(Color.web("#526D82"));
        textPassword.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox8 = new HBox(textPassword);
        hBox8.setAlignment(Pos.CENTER_LEFT);
        hBox8.setPadding(new Insets(0, 0, 0, 95));

        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        passwordField.setPromptText("Masukkan Password...");
        passwordField.setAlignment(Pos.CENTER);
        HBox hBox9 = new HBox(passwordField);
        hBox9.setAlignment(Pos.CENTER);

        Text textKonfirmPass = new Text("Konfirmasi Password : ");
        textKonfirmPass.setFill(Color.web("#526D82"));
        textKonfirmPass.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox10 = new HBox(textKonfirmPass);
        hBox10.setAlignment(Pos.CENTER_LEFT);
        hBox10.setPadding(new Insets(0, 0, 0, 95));

        PasswordField konfirmPassField = new PasswordField();
        konfirmPassField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        konfirmPassField.setPromptText("Masukkan Password (lagi)");
        konfirmPassField.setAlignment(Pos.CENTER);
        HBox hBox11 = new HBox(konfirmPassField);
        hBox11.setAlignment(Pos.CENTER);

        Text textUmur = new Text("Umur : (dalam angka)");
        textUmur.setFill(Color.web("#526D82"));
        textUmur.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");
        HBox hBox12 = new HBox(textUmur);
        hBox12.setAlignment(Pos.CENTER_LEFT);
        hBox12.setPadding(new Insets(0, 0, 0, 95));

        TextField umurField = new TextField();
        umurField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        umurField.setPromptText("Masukkan Umur...");
        umurField.setAlignment(Pos.CENTER);
        HBox hBox13 = new HBox(umurField);
        hBox13.setAlignment(Pos.CENTER);

        Button buttonSignup = new Button("SIGN UP");
        buttonSignup.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Cambria';-fx-padding: 10px 80px;-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        buttonSignup.setOnAction(v -> {createSceneBerhasil();});
        HBox hBox14 = new HBox(buttonSignup);
        hBox14.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox(10, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7, hBox8, hBox9, hBox10, hBox11, hBox12, hBox13);
        vBox1.setAlignment(Pos.CENTER);
        
        VBox vBox2 = new VBox(15, hBox1, vBox1, hBox14);
        vBox2.setAlignment(Pos.CENTER);

        Scene sceneSignUp = new Scene(vBox2, 480, 720);
        stage.setTitle("MediTracker");
        stage.setScene(sceneSignUp);
    }

    private void createSceneBerhasil() {
        Label label = new Label("SELAMAT!!!");
        label.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 37px; -fx-text-color: #000000; -fx-font-weight: bold;");
        
        Text text = new Text("Anda telah berhasil membuat akun! \nSilahkan kembali ke halaman utama");
        text.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px;");

        Button buttonBack = new Button("BACK");
        buttonBack.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Cambria';-fx-font-size: 13px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-border-radius: 3;");
        buttonBack.setOnAction(v -> {createScene1();});
        HBox hBox = new HBox(buttonBack);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox(7, label, text);
        vBox1.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(15, vBox1, hBox);
        vBox.setAlignment(Pos.CENTER);

        Scene sceneBerhasil = new Scene(vBox, 480, 720);
        stage.setTitle("MediTracker");
        stage.setScene(sceneBerhasil);
    }
}
