package models;

import utils.Database;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BarangModel {
    // Fungsi Get Data & Search
    public DefaultTableModel getBarang(String keyword) {
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID Barang", "Nama Barang", "Stok", "Harga"}, 0);
        String query = "SELECT id_barang, nama_barang, stok, harga FROM barang WHERE nama_barang LIKE ?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id_barang"),
                        rs.getString("nama_barang"),
                        rs.getInt("stok"),
                        rs.getDouble("harga")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Load Data: " + e.getMessage());
        }
        return tableModel;
    }

    public boolean tambah(String nama, int stok, double harga) {
        String query = "INSERT INTO barang (nama_barang, stok, harga) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setInt(2, stok);
            stmt.setDouble(3, harga);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean update(int id, String nama, int stok, double harga) {
        String query = "UPDATE barang SET nama_barang=?, stok=?, harga=? WHERE id_barang=?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setInt(2, stok);
            stmt.setDouble(3, harga);
            stmt.setInt(4, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean hapus(int id) {
        String query = "DELETE FROM barang WHERE id_barang=?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}