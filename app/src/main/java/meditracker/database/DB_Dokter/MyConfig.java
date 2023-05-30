package meditracker.database.DB_Dokter;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyConfig {

    private static final String URL = "jdbc:mysql://localhost:3306/db_account";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection connect;
    private static Statement statement;
    private static ResultSet resultSet;
    
    /* Connect */
    public static void connection() {
        
        try {
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected");

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential errors during the connection
        }
    }

    /* GET DATABASE */
    public static void getDatabase() {
        connection();
        try { String query = "SELECT * FROM `tb_dokter`";
            connection();
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("ID          > " + resultSet.getInt("ID"));
                System.out.println("NAMA        > " + resultSet.getString("NAMA_DOKTER"));
                System.out.println("PIN         > " + resultSet.getInt("PIN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ADD DATA */
    public static void AddData(Integer id, String name, Integer pin) {
        connection();
        try {
            String query = "INSERT INTO tb_dokter (ID, NAMA_DOKTER, PIN) VALUES (?,?,?)";
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, pin);
            statement.executeUpdate();
            System.out.println("Data Dokter Berhasil Ditambahkan");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}


