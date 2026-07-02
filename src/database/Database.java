package database; // Ganti tulisan 'database' jadi 'utils' kalau nama package-mu belum di-rename

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/db_toko_plafon";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // KUNCI PERBAIKAN: Kita langsung return koneksi baru.
            // Tidak ada lagi pengecekan if (connection == null) yang bikin error!
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Koneksi Database Gagal: " + e.getMessage(),
                    "Error Database", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}