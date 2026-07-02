package repository;

import database.Database;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KategoriModel {
    public DefaultTableModel getKategori(String keyword) {
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID Kategori", "Nama Kategori"}, 0);
        String query = "SELECT * FROM kategori WHERE nama_kategori LIKE ?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id_kategori"),
                        rs.getString("nama_kategori")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Load Data: " + e.getMessage());
        }
        return tableModel;
    }

    public boolean tambah(String nama) {
        String query = "INSERT INTO kategori (nama_kategori) VALUES (?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean update(int id, String nama) {
        String query = "UPDATE kategori SET nama_kategori=? WHERE id_kategori=?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean hapus(int id) {
        String query = "DELETE FROM kategori WHERE id_kategori=?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}