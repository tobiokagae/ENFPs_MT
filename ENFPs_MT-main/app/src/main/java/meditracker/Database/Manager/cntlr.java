package meditracker.Database.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class cntlr {
    public static void createTable() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db")) {
            String createTbDokterSql = "CREATE TABLE IF NOT EXISTS tb_dokter (ID INTEGER(8), NAMA VARCHAR(255), PIN VARCHAR(255))";
            try (PreparedStatement statement = connection.prepareStatement(createTbDokterSql)) {
                statement.execute();
                System.out.println("Success To Add Table");
            }
            String createTbPasienSql = "CREATE TABLE IF NOT EXISTS tb_pasien (Nama VARCHAR(255), Umur INTEGER(3), Diagnosis VARCHAR(255), Obat VARCHAR(255))";
            try (PreparedStatement statement = connection.prepareStatement(createTbPasienSql)) {
                statement.execute();
                System.out.println("Success To Add Table");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDataDokter(Integer id, String nama, String pin) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db")) {
            String insertDataSQL = "INSERT INTO tb_dokter (ID, NAMA, PIN) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
                statement.setInt(1, id);
                statement.setString(2, nama);
                statement.setString(3, pin);
                statement.executeUpdate();
                System.out.println("Success To Add Data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDataPasien(String nama, Integer umur, String diagnosis, String obat) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db")) {
            String insertDataSQL = "INSERT INTO tb_pasien (Nama, Umur, Diagnosis, Obat) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
                statement.setString(1, nama);
                statement.setInt(2, umur);
                statement.setString(4, diagnosis);
                statement.setString(5, obat);
                statement.executeUpdate();
                System.out.println("Success To Add Data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllDataD() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db")) {
            String deleteAllDataSQL = "DELETE FROM tb_dokter";
            try (PreparedStatement statement = connection.prepareStatement(deleteAllDataSQL)) {
                int rowsDeleted = statement.executeUpdate();
                System.out.println(rowsDeleted + " rows deleted.");
                System.out.println("Success To Delete All Data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllDataP() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db")) {
            String deleteAllDataSQL = "DELETE FROM tb_pasien";
            try (PreparedStatement statement = connection.prepareStatement(deleteAllDataSQL)) {
                int rowsDeleted = statement.executeUpdate();
                System.out.println(rowsDeleted + " rows deleted.");
                System.out.println("Success To Delete All Data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void seeDataDokter() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db")) {
            String selectDataSQL = "SELECT * FROM tb_dokter";
            try (PreparedStatement statement = connection.prepareStatement(selectDataSQL)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("ID");
                    String nama = resultSet.getString("NAMA");
                    String pin  = resultSet.getString("PIN");
                    System.out.println("ID : " + id +" | " + "NAMA : " + nama + " | " + "PIN : " + pin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void seeDataPasien() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db")) {
            String selectDataSQL = "SELECT * FROM tb_pasien";
            try (PreparedStatement statement = connection.prepareStatement(selectDataSQL)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String nama = resultSet.getString("Nama");
                    Integer umur = resultSet.getInt("Umur");
                    String diagnosis = resultSet.getString("Diagnosis");
                    String obat = resultSet.getString("Obat");
                    System.out.println("Nama : " + nama +" | " + "Umur : " + umur + " | " + "Diagnosis : " + diagnosis + " | " + "Obat : " + obat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getDataPasienUsername() {
        ArrayList<String> usernames = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db")) {
            String selectUsernameSQL = "SELECT Username FROM tb_pasien";
            try (PreparedStatement statement = connection.prepareStatement(selectUsernameSQL)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String username = resultSet.getString("Username");
                    usernames.add(username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usernames;
    }

    public static void seeDataPasienUsername() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db")) {
            String selectUsernameSQL = "SELECT Username FROM tb_pasien";
            try (PreparedStatement statement = connection.prepareStatement(selectUsernameSQL)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String username = resultSet.getString("Username");
                    System.out.println("Username: " + username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropTablePasien() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ASUS\\Downloads\\Project\\app\\src\\main\\java\\meditracker\\Database\\Manager\\account.db")) {
            String dropTableSQL = "DROP TABLE IF EXISTS tb_pasien";
            try (PreparedStatement statement = connection.prepareStatement(dropTableSQL)) {
                statement.executeUpdate();
                System.out.println("Success To Drop Table");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // public static void main(String[] args) {
        // createTable();
        // insertDataDokter(71221160, "dr. Lil", "8593");
        // insertDataPasien("Alisha", "alisha@gmail.com", 18, "aclisung", "aclisakit");
        // seeDataDokter();
        // seeDataPasien();
        // dropTablePasien();
    }

