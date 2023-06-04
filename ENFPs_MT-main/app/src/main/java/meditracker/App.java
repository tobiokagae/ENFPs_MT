package meditracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
// import meditracker.Database.Manager.cntlr;

public class App extends Application {
    protected Stage stage;
    private ObservableList<String> listPasien;
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;  
        listPasien = FXCollections.observableArrayList(); // Initialize listPasien
    
        // Mengambil data dari database dan memuatnya ke dalam listPasien
        loadDataFromDatabase();
    
        createSceneLogIn();    
        stage.show();
    }

    private void createSceneLogIn() {
        Label mediTracker = new Label("MediTracker");
        mediTracker.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 35px; -fx-text-color: #000000; -fx-font-weight: bold;");
        HBox logInBox = new HBox(mediTracker);
        logInBox.setAlignment(Pos.CENTER);
        
        ImageView logo = new ImageView(new Image("/meditrckr.png"));
        logo.setFitHeight(150);
        logo.setFitWidth(150);
        HBox logoBox = new HBox(logo);
        logoBox.setAlignment(Pos.CENTER);

        Label labelID = new Label("ID :");
        labelID.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px; -fx-text-color: #000000;");
        HBox idBox = new HBox(labelID);
        idBox.setAlignment(Pos.CENTER_LEFT);
        idBox.setPadding(new Insets(0, 0, 0, 165));

        TextField idField = new TextField();
        idField.setPromptText("Masukkan ID...");
        idField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        idField.setAlignment(Pos.CENTER);
        HBox idFieldBox = new HBox(idField);
        idFieldBox.setAlignment(Pos.CENTER);

        Label labelPin = new Label("PIN :");
        labelPin.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 15px; -fx-text-color: #000000;");
        HBox pinBox = new HBox(labelPin);
        pinBox.setAlignment(Pos.CENTER_LEFT);
        pinBox.setPadding(new Insets(0, 0, 0, 165));

        PasswordField pinField = new PasswordField();
        pinField.setPromptText("Masukkan PIN...");
        pinField.setStyle("-fx-padding: 1px 60px;-fx-text-fill:BLACK;-fx-font-size: 15px;-fx-background-color: #FFFAF4; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
        pinField.setAlignment(Pos.CENTER);
        HBox pinFieldBox = new HBox(pinField);
        pinFieldBox.setAlignment(Pos.CENTER);

        Button logInButton = new Button("LOG IN");
        logInButton.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Cambria';-fx-padding: 10px 80px;-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-background-radius: 42px; -fx-border-radius: 42px;");
        HBox logInButtonBox = new HBox(logInButton);
        logInButtonBox.setAlignment(Pos.CENTER);
        logInButton.setOnAction(v -> {
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
                        PauseTransition pause = new PauseTransition(Duration.seconds(1));
                        pause.setOnFinished(event -> createScenePortal());
                        pause.play();
                    } else {
                        PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
                        pause2.setOnFinished(event -> {createSceneLogIn();});
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
                pause2.setOnFinished(event -> {createSceneLogIn();});
                pause2.play();
            }
        });

        VBox inputBox = new VBox(4, idBox, idFieldBox, pinBox, pinFieldBox);
        VBox screenBox = new VBox(20, logInBox, logoBox, inputBox, logInButtonBox);
        screenBox.setAlignment(Pos.CENTER);

        Scene sceneLogIn = new Scene(screenBox, 620, 620);
        stage.setTitle("MediTracker");
        stage.setScene(sceneLogIn);
    }

    public void createScenePortal() {
        Label labelDaftarPasien = new Label("Daftar Pasien");
        labelDaftarPasien.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 35px; -fx-text-color: #000000; -fx-font-weight: bold;");
        HBox daftarPasienBox = new HBox(labelDaftarPasien);
        daftarPasienBox.setAlignment(Pos.CENTER);

        ListView<String> listViewPasien = new ListView<>();

        EventHandler<MouseEvent> listViewPasienClicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Mendapatkan nama pasien yang dipilih
                String selectedPasien = listViewPasien.getSelectionModel().getSelectedItem();
        
                // Panggil metode atau fungsi untuk melanjutkan ke scene berikutnya
                createSceneDetailData(selectedPasien);
            }
        };
        
        listViewPasien.setItems(listPasien);
        listViewPasien.setOnMouseClicked(listViewPasienClicked);

        TextField tfName = new TextField();
        tfName.setPromptText("Masukkan Nama Pasien...");
        
        Button btnAdd = new Button("Tambah");
        btnAdd.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Cambria';-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-background-radius: 42px; -fx-border-radius: 42px;");
        btnAdd.setOnAction(v -> {
            String namaPasien = tfName.getText();
            listPasien.add(namaPasien);
            simpanKeDatabase(namaPasien);
        });
        
        Button btnRemove = new Button("Hapus");
        btnRemove.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Cambria';-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-background-radius: 42px; -fx-border-radius: 42px;");        
        btnRemove.setOnAction(v -> {
            int index = listViewPasien.getSelectionModel().getSelectedIndex();
            listPasien.remove(index);
        });

        HBox btn = new HBox(15, btnAdd, btnRemove);
        btn.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(10, daftarPasienBox, listViewPasien, tfName, btn);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0, 50, 0, 50));
        Scene scene = new Scene(vBox, 620, 620);
        stage.setTitle("MediTracker");
        stage.setScene(scene);
    }

    private void simpanKeDatabase(String namaPasien) {
        String url = "jdbc:sqlite:C:\\Users\\USER\\OneDrive\\Documents\\JavaFX\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager/account.db"; // Ganti dengan path ke file database SQLite Anda
    
        String sqlInsert = "INSERT INTO tb_pasien (Nama) VALUES (?)";
        String sqlSelect = "SELECT Nama FROM tb_pasien";
    
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert);
            PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {
    
            // Insert data pasien baru
            stmtInsert.setString(1, namaPasien);
            stmtInsert.executeUpdate();
    
            // Ambil semua data pasien dari database
            ResultSet rs = stmtSelect.executeQuery();
            listPasien.clear();
            while (rs.next()) {
                String nmPasien = rs.getString("Nama");
                listPasien.add(nmPasien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Tangani kesalahan penyimpanan ke database
        }
    }    

    private void loadDataFromDatabase() {
        String url = "jdbc:sqlite:C:\\Users\\USER\\OneDrive\\Documents\\JavaFX\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager/account.db";
        String sqlSelect = "SELECT Nama FROM tb_pasien";
    
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect);
            ResultSet rs = stmtSelect.executeQuery()) {
    
            listPasien.clear();
            while (rs.next()) {
                String namaPasien = rs.getString("Nama");
                listPasien.add(namaPasien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Tangani kesalahan saat mengambil data dari database
        }
    }
    
    private void createSceneDetailData(String selectedPasien) {
        // Mengambil data pasien dari database berdasarkan nama pasien yang dipilih
        Pasien pasien = getDataPasien(selectedPasien);
    
        // Membuat komponen GUI untuk menampilkan detail data pasien
        Label labelDetail = new Label("Detail Pasien");
        labelDetail.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 35px; -fx-text-color: #000000; -fx-font-weight: bold;");
        HBox detailBox = new HBox(labelDetail);
        detailBox.setAlignment(Pos.CENTER);
    
        Label labelNama = new Label("Nama:");
        TextField tfNama = new TextField(pasien.getNama());
    
        Label labelUmur = new Label("Umur:");
        TextField tfUmur = new TextField(Integer.toString(pasien.getUmur()));
    
        Label labelDiagnosis = new Label("Diagnosis:");
        TextField tfDiagnosis = new TextField(pasien.getDiagnosis());
    
        Label labelObat = new Label("Obat:");
        TextField tfObat = new TextField(pasien.getObat());
    
        Button btnSave = new Button("Simpan");
        btnSave.setOnAction(event -> {
            // Menyimpan perubahan data pasien ke database
            pasien.setNama(tfNama.getText());
            pasien.setUmur(Integer.parseInt(tfUmur.getText()));
            pasien.setDiagnosis(tfDiagnosis.getText());
            pasien.setObat(tfObat.getText());
            simpanPerubahanPasien(pasien);
    
            // Kembali ke halaman portal
            createScenePortal();
        });
    
        VBox vBox = new VBox(10, detailBox, labelNama, tfNama, labelUmur, tfUmur, labelDiagnosis, tfDiagnosis, labelObat, tfObat, btnSave);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0, 50, 0, 50));
    
        Scene scene = new Scene(vBox, 620, 620);
        stage.setTitle("MediTracker");
        stage.setScene(scene);
    }

    private Pasien getDataPasien(String selectedPasien) {
        String url = "jdbc:sqlite:C:\\Users\\USER\\OneDrive\\Documents\\JavaFX\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager/account.db";
        String sqlSelect = "SELECT * FROM tb_pasien WHERE Nama = ?";
        Pasien pasien = null;
    
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {
    
            stmtSelect.setString(1, selectedPasien);
            ResultSet rs = stmtSelect.executeQuery();
    
            if (rs.next()) {
                String nama = rs.getString("Nama");
                int umur = rs.getInt("Umur");
                String diagnosis = rs.getString("Diagnosis");
                String obat = rs.getString("Obat");
    
                pasien = new Pasien(nama, umur, diagnosis, obat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Tangani kesalahan saat mengambil data dari database
        }
    
        return pasien;
    }
    

    private void simpanPerubahanPasien(Pasien pasien) {
        String url = "jdbc:sqlite:C:\\Users\\USER\\OneDrive\\Documents\\JavaFX\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager/account.db";
        String sqlUpdate = "UPDATE tb_pasien SET Nama = ?, Umur = ?, Diagnosis = ?, Obat = ? WHERE Nama = ?";
    
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
    
            stmtUpdate.setString(1, pasien.getNama());
            stmtUpdate.setInt(2, pasien.getUmur());
            stmtUpdate.setString(3, pasien.getDiagnosis());
            stmtUpdate.setString(4, pasien.getObat());
            stmtUpdate.setString(5, pasien.getNama());
    
            stmtUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Tangani kesalahan penyimpanan ke database
        }
    }
}