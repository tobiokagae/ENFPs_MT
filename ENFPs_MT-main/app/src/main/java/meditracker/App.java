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
// import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
// import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
// import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
    private Stage stage;
    private ObservableList<String> listPasien;
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;  
        listPasien = FXCollections.observableArrayList(); // Initialize listPasien
    
        // Mengambil data dari database dan memuatnya ke dalam listPasien
        loadDataFromDatabase();
    
        createSceneLogIn();    
        stage.show();
    }

    public void createSceneLogIn() {
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
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db");
                    String sql = "SELECT * FROM tb_dokter WHERE ID = ? AND PIN = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, id);
                    statement.setString(2, pin);

                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        createScenePortal();
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
                PauseTransition pause2 = new PauseTransition(Duration.seconds(1));
                pause2.setOnFinished(event -> {createSceneLogIn();});
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
                
                    //     alert.showAndWait();
                    // } else if (enteredID.equals("ID benar") && enteredPIN.equals("PIN salah")) {
                    //     Alert alert = new Alert(Alert.AlertType.ERROR);
                    //     alert.setTitle("Error");
                    //     alert.setHeaderText(null);
                    //     alert.setContentText("Password salah");
                
                    //     // Mengatur gaya visual Alert
                    //     alert.getDialogPane().setStyle(
                    //             "-fx-background-color: #F8E0E0; " +
                    //             "-fx-border-color: #FF0000; " +
                    //             "-fx-border-width: 2px;" +
                    //             "-fx-font-weight: bold;");
                
                    //     alert.showAndWait();
                    // } else if (enteredID.equals("ID salah") && enteredPIN.equals("PIN sudah benar")) {
                    //     Alert alert = new Alert(Alert.AlertType.ERROR);
                    //     alert.setTitle("Error");
                    //     alert.setHeaderText(null);
                    //     alert.setContentText("ID salah");
                
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
        stage.setTitle("MediTracker");
        stage.setScene(sceneLogIn);
    }
//Scene Portal 
    public void createScenePortal() {
        Image image = new Image("/TABEL.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(620);
        imageView.setFitWidth(620);
        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        Label labelDaftarPasien = new Label("Daftar Pasien");
        labelDaftarPasien.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 28px; -fx-text-color: #000000; -fx-font-weight: bold; -fx-font-weight: bold;");
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
        
        Button btnAdd = new Button("TAMBAH");
        btnAdd.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Arial';-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-background-radius: 42px; -fx-border-radius: 42px; -fx-font-weight: bold;");
        btnAdd.setOnAction(v -> {
            if (!tfName.getText().isEmpty()) {
                String namaPasien = tfName.getText();
                listPasien.add(namaPasien);
                simpanKeDatabase(namaPasien);
            }
        });
        
        Button btnRemove = new Button("HAPUS");
        btnRemove.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Arial';-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-background-radius: 42px; -fx-border-radius: 42px;-fx-font-weight: bold;");        
        btnRemove.setOnAction(v -> {
            int index = listViewPasien.getSelectionModel().getSelectedIndex();
            String selectedPasien = listViewPasien.getSelectionModel().getSelectedItem();
            listPasien.remove(index);
            hapusDariDatabase(selectedPasien);
        });

        Button btnLogout = new Button("LOGOUT");
        btnLogout.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Arial';-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-background-radius: 42px; -fx-border-radius: 42px;-fx-font-weight: bold;");
        btnLogout.setOnAction(v -> {
            createSceneLogIn();
        });
        HBox btnLogoutBox = new HBox(btnLogout);
        btnLogoutBox.setAlignment(Pos.CENTER);

        HBox btn = new HBox(15, btnAdd, btnRemove);
        btn.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(10, daftarPasienBox, listViewPasien, tfName, btn, btnLogoutBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0, 150, 0, 150));
        root.getChildren().add(vBox);
        Scene scene = new Scene(root, 620, 620);
        stage.setTitle("MediTracker");
        stage.setScene(scene);
    }

    private void hapusDariDatabase(String selectedPasien) {
        String url = "jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db";
        String sqlDelete = "DELETE FROM tb_pasien WHERE Nama = ?";
    
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete)) {
    
            stmtDelete.setString(1, selectedPasien);
            stmtDelete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Tangani kesalahan saat menghapus data dari database
        }
    }

    private void simpanKeDatabase(String namaPasien) {
        String url = "jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db"; // Ganti dengan path ke file database SQLite Anda
    
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
        String url = "jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db";
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

// secene DetailData
public void createSceneDetailData(String selectedPasien) {
    Image image = new Image("/DAATA.png");
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(620);
    imageView.setFitWidth(620);
    StackPane root = new StackPane();
    root.getChildren().add(imageView);

    // Mengambil data pasien dari database berdasarkan nama pasien yang dipilih
    Pasien pasien = getDataPasien(selectedPasien);

    // Membuat komponen GUI untuk menampilkan detail data pasien
    Label labelDetail = new Label("Detail Pasien");
    labelDetail.setStyle("-fx-font-family: 'Arial'; -fx-font-size:28px; -fx-text-color: #000000; -fx-font-weight: bold;");
    HBox detailBox = new HBox(labelDetail);
    detailBox.setAlignment(Pos.CENTER);

    Label labelNama = new Label("Nama:");
    labelNama.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 15px; -fx-text-color: #000000; -fx-font-weight: bold;");

    TextField tfNama = new TextField(pasien.getNama());
    tfNama.setStyle("-fx-padding: 1px;-fx-background-color: #FFFAF4;-fx-text-fill:BLACK;-fx-font-size: 15px; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
    tfNama.setPromptText("Masukkan Nama Pasien...");
    tfNama.setAlignment(Pos.CENTER_LEFT);
    tfNama.setMinWidth(500); // Ubah ukuran text field untuk nama
    HBox tfNamaBox = new HBox(tfNama);
    tfNamaBox.setAlignment(Pos.CENTER);

    Label labelUmur = new Label("Umur:");
    labelUmur.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 15px; -fx-text-color: #000000; -fx-font-weight: bold;");

    TextField tfUmur = new TextField(Integer.toString(pasien.getUmur()));
    tfUmur.setStyle("-fx-padding: 1px;-fx-background-color: #FFFAF4;-fx-text-fill:BLACK;-fx-font-size: 15px; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
    tfUmur.setPromptText("Masukkan Umur Pasien...");
    tfUmur.setAlignment(Pos.CENTER_LEFT);
    tfUmur.setMinWidth(500); // Ubah ukuran text field untuk umur
    HBox tfUmurBox = new HBox(tfUmur);
    tfUmurBox.setAlignment(Pos.CENTER);

    Label labelDiagnosis = new Label("Diagnosis:");
    labelDiagnosis.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 15px; -fx-text-color: #000000; -fx-font-weight: bold;");

    TextField tfDiagnosis = new TextField(pasien.getDiagnosis());
    tfDiagnosis.setStyle("-fx-padding: 1px;-fx-background-color: #FFFAF4;-fx-text-fill:BLACK;-fx-font-size: 15px; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
    tfDiagnosis.setPromptText("Masukkan Diagnosis Pasien...");
    tfDiagnosis.setAlignment(Pos.CENTER_LEFT);
    tfDiagnosis.setMinWidth(500); // Ubah ukuran text field untuk diagnosis

    HBox tfDiagnosisBox = new HBox(tfDiagnosis);
    tfDiagnosisBox.setAlignment(Pos.CENTER);

    Label labelObat = new Label("Obat:");
    labelObat.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 15px; -fx-text-color: #000000; -fx-font-weight: bold;");

    TextField tfObat = new TextField(pasien.getObat());
    tfObat.setStyle("-fx-padding: 1px;-fx-background-color: #FFFAF4;-fx-text-fill:BLACK;-fx-font-size: 15px; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 3;");
    tfObat.setPromptText("Masukkan Obat Pasien...");
    tfObat.setAlignment(Pos.CENTER_LEFT);
    tfObat.setMinWidth(500); // Ubah ukuran text field untuk obat
    HBox tfObatBox = new HBox(tfObat);
    tfObatBox.setAlignment(Pos.CENTER);

    Button btnSave = new Button("SIMPAN & KEMBALI");
    btnSave.setStyle("-fx-background-color: #C4DFDF;-fx-font-family: 'Arial';-fx-font-size: 15px; -fx-border-color: BLACK; -fx-border-width: 1px; -fx-background-radius: 42px; -fx-border-radius: 42px; -fx-font-weight: bold;");
    btnSave.setPrefWidth(200); // Ubah lebar tombol menjadi 200 piksel
    btnSave.setPrefHeight(40); // Ubah tinggi tombol menjadi 40 piksel
    HBox btnSaveBox = new HBox(btnSave);
    btnSaveBox.setAlignment(Pos.CENTER);
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
    VBox tfBox = new VBox(4,labelNama, tfNama, labelUmur, tfUmur, labelDiagnosis, tfDiagnosis, labelObat, tfObat);
    VBox vBox = new VBox(20, detailBox,tfBox, btnSave);
    vBox.setAlignment(Pos.CENTER);
    vBox.setPadding(new Insets(0, 210, 0, 210));
    root.getChildren().add(vBox);

    Scene scene = new Scene(root, 620, 620);
    stage.setTitle("MediTracker");
    stage.setScene(scene);
}

    private Pasien getDataPasien(String selectedPasien) {
        String url = "jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db";
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
        String url = "jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db";
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