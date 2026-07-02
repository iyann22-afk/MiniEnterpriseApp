package repository;

import database.Database;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PemasokModel {
    public DefaultTableModel getPemasok(String keyword) {
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID Pemasok", "Nama Pemasok", "Kontak"}, 0);
        String query = "SELECT * FROM pemasok WHERE nama_pemasok LIKE ?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id_pemasok"),
                        rs.getString("nama_pemasok"),
                        rs.getString("kontak")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Load Data: " + e.getMessage());
        }
        return tableModel;
    }

    public boolean tambah(String nama, String kontak) {
        String query = "INSERT INTO pemasok (nama_pemasok, kontak) VALUES (?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, kontak);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean update(int id, String nama, String kontak) {
        String query = "UPDATE pemasok SET nama_pemasok=?, kontak=? WHERE id_pemasok=?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, kontak);
            stmt.setInt(3, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean hapus(int id) {
        String query = "DELETE FROM pemasok WHERE id_pemasok=?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}