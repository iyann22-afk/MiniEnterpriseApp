package models;

import utils.Database;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PelangganModel {
    public DefaultTableModel getPelanggan(String keyword) {
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID Pelanggan", "Nama Pelanggan", "Alamat"}, 0);
        String query = "SELECT * FROM pelanggan WHERE nama_pelanggan LIKE ?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id_pelanggan"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("alamat")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Load Data: " + e.getMessage());
        }
        return tableModel;
    }

    public boolean tambah(String nama, String alamat) {
        String query = "INSERT INTO pelanggan (nama_pelanggan, alamat) VALUES (?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, alamat);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean update(int id, String nama, String alamat) {
        String query = "UPDATE pelanggan SET nama_pelanggan=?, alamat=? WHERE id_pelanggan=?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, alamat);
            stmt.setInt(3, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean hapus(int id) {
        String query = "DELETE FROM pelanggan WHERE id_pelanggan=?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}