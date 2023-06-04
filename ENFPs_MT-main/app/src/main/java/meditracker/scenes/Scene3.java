package meditracker.scenes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import meditracker.Pasien;

public class Scene3 extends MainScene{
    public Scene3(Stage stage, String selectedPasien) {
        super(stage);
        this.selectedPasien = selectedPasien;
    }


    private String selectedPasien; 
        private Pasien getDataPasien(String selectedPasien) {
            String url = "jdbc:sqlite:account.db";
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
            String url = "jdbc:sqlite:account.db";
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

        @Override
        public void show() {
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
                Scene2 scene2 = new Scene2(stage);
                scene2.show();
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
    }

