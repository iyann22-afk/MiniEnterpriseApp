package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/db_toko_plafon";
    private static final String USER = "root";
    private static final String PASS = ""; // Sesuaikan jika XAMPP kamu pakai password
    private static Connection connection;

    // Exception Handling & Singleton OOP Pattern
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Memuat driver MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Koneksi Database Berhasil!");
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Koneksi Database Gagal: " + e.getMessage(),
                        "Error Database", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
        return connection;
    }
}