package meditracker.scenes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Scene2 extends MainScene{
    
    private ObservableList<String> listPasien;

    public Scene2(Stage stage) {
        super(stage);
        listPasien = FXCollections.observableArrayList(); 
        loadDataFromDatabase();
    }

    private void hapusDariDatabase(String selectedPasien) {
        String url = "jdbc:sqlite:account.db";
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
        String url = "jdbc:sqlite:account.db"; // Ganti dengan path ke file database SQLite Anda
    
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
        String url = "jdbc:sqlite:account.db";
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

    @Override
    public void show() {
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
                Scene3 scene3 = new Scene3(stage, selectedPasien);
                scene3.show();
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
            Scene1 scene1 = new Scene1(stage);
                scene1.show();
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
}
