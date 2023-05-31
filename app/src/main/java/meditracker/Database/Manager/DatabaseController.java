package meditracker.Database.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\USER\\OneDrive\\Documents\\JavaFX\\ENFPs_MT\\app\\src\\main\\java\\meditracker\\Database\\DB.FILE/account.db";

    public static Connection connect() {
        Connection connection = null;
        try {
            // Memuat driver JDBC SQLite
            Class.forName("org.sqlite.JDBC");

            // Membuka koneksi ke database SQLite
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Berhasil terhubung ke database SQLite");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Gagal terhubung ke database SQLite: " + e.getMessage());
        }
        return connection;
    }

    public static void createTables() {
        try (Connection connection = connect()) {
            if (connection != null) {
                // Membuat tabel tb_dokter
                String createTbDokterSql = "CREATE TABLE IF NOT EXISTS tb_dokter (ID INTEGER, NAMA TEXT, PIN TEXT)";
                executeUpdate(connection, createTbDokterSql);
                System.out.println("Tabel tb_dokter berhasil dibuat");

                // Membuat tabel tb_pasien
                String createTbPasienSql = "CREATE TABLE IF NOT EXISTS tb_pasien (Username TEXT PRIMARY KEY, Password TEXT)";
                executeUpdate(connection, createTbPasienSql);
                System.out.println("Tabel tb_pasien berhasil dibuat");
            }
        } catch (SQLException e) {
            System.out.println("Gagal membuat tabel: " + e.getMessage());
        }
    }

    public static void insertDataTbDokter(int id, String nama, String pin) {
        try (Connection connection = connect()) {
            if (connection != null) {
                // Menambahkan data ke tabel tb_dokter
                String insertDataSql = "INSERT INTO tb_dokter (ID, NAMA, PIN) VALUES (" + id + ", '" + nama + "', '" + pin + "')";
                executeUpdate(connection, insertDataSql);
                System.out.println("Data berhasil ditambahkan ke tabel tb_dokter");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data ke tabel tb_dokter: " + e.getMessage());
        }
    }

    public static void insertDataTbPasien(String username, String password) {
        try (Connection connection = connect()) {
            if (connection != null) {
                // Menambahkan data ke tabel tb_pasien
                String insertDataSql = "INSERT INTO tb_pasien (Username, Password) VALUES ('" + username + "', '" + password + "')";
                executeUpdate(connection, insertDataSql);
                System.out.println("Data berhasil ditambahkan ke tabel tb_pasien");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data ke tabel tb_pasien: " + e.getMessage());
        }
    }

    public static void deleteAllDataTbDokter() {
        try (Connection connection = connect()) {
            if (connection != null) {
                // Menghapus semua data dari tabel tb_dokter
                String deleteDataSql = "DELETE FROM tb_dokter";
                executeUpdate(connection, deleteDataSql);
                System.out.println("Semua data dihapus dari tabel tb_dokter");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menghapus semua data dari tabel tb_dokter: " + e.getMessage());
        }
    }

    public static void deleteAllDataTbPasien() {
        try (Connection connection = connect()) {
            if (connection != null) {
                // Menghapus semua data dari tabel tb_pasien
                String deleteDataSql = "DELETE FROM tb_pasien";
                executeUpdate(connection, deleteDataSql);
                System.out.println("Semua data dihapus dari tabel tb_pasien");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menghapus semua data dari tabel tb_pasien: " + e.getMessage());
        }
    }

    public static void selectAllDataTbDokter() {
        try (Connection connection = connect()) {
            if (connection != null) {
                // Mengeksekusi query untuk memilih semua data dari tabel tb_dokter
                String selectAllDataSql = "SELECT * FROM tb_dokter";
                executeQueryDokter(connection, selectAllDataSql);
            }
        } catch (SQLException e) {
            System.out.println("Gagal memilih data dari tabel tb_dokter: " + e.getMessage());
        }
    }

    public static void selectAllDataTbPasien() {
        try (Connection connection = connect()) {
            if (connection != null) {
                // Mengeksekusi query untuk memilih semua data dari tabel tb_pasien
                String selectAllDataSql = "SELECT * FROM tb_pasien";
                executeQueryPasien(connection, selectAllDataSql);
            }
        } catch (SQLException e) {
            System.out.println("Gagal memilih data dari tabel tb_pasien: " + e.getMessage());
        }
    }

    private static void executeUpdate(Connection connection, String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    private static void executeQueryDokter(Connection connection, String sql) throws SQLException {
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                // Mendapatkan nilai kolom dari setiap baris
                int id = resultSet.getInt("ID");
                String nama = resultSet.getString("NAMA");
                String pin = resultSet.getString("PIN");
                System.out.println("ID : " + id + ", Nama : " + nama + ", PIN : " + pin);
            }
        }
    }

    private static void executeQueryPasien(Connection connection, String sql) throws SQLException {
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                // Mendapatkan nilai kolom dari setiap baris
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                System.out.println("Username : " + username + ", Password : " + password);
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        // Menghubungkan ke database
        // connect();

        // Membuat tabel tb_dokter dan tb_pasien
        // createTables();

        // Menambahkan data ke tabel tb_dokter
        // insertDataTbDokter(71221160, "dr. Lil", "8539");

        // Menambahkan data ke tabel tb_pasien
        // insertDataTbPasien("aclisung", "aclisakit");

        // Memilih semua data dari tabel tb_dokter
        selectAllDataTbDokter();
        System.out.println();
        selectAllDataTbPasien();

        // Menghapus semua data dari tabel tb_dokter
        // deleteAllDataTbDokter();

        // Menghapus semua data dari tabel tb_pasien
        // deleteAllDataTbPasien();
    }
}
