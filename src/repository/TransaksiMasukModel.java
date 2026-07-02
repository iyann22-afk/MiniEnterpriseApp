package repository;

import database.Database;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransaksiMasukModel {
    public DefaultTableModel getTransaksi(String keyword) {
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID Masuk", "ID Barang", "ID Pemasok", "Tanggal", "Jumlah"}, 0);
        // Mengambil data dengan JOIN sederhana bisa dilakukan, tapi untuk cepat kita pakai ID dulu
        String query = "SELECT * FROM transaksi_masuk WHERE tanggal LIKE ?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id_masuk"),
                        rs.getInt("id_barang"),
                        rs.getInt("id_pemasok"),
                        rs.getString("tanggal"),
                        rs.getInt("jumlah")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error Load Data: " + e.getMessage());
        }
        return tableModel;
    }

    public boolean tambah(int idBarang, int idPemasok, String tanggal, int jumlah) {
        Connection conn = null;
        try {
            conn = Database.getConnection();
            conn.setAutoCommit(false); // Mulai Transaksi DB

            // 1. Insert ke tabel transaksi_masuk
            String query1 = "INSERT INTO transaksi_masuk (id_barang, id_pemasok, tanggal, jumlah) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt1 = conn.prepareStatement(query1)) {
                stmt1.setInt(1, idBarang);
                stmt1.setInt(2, idPemasok);
                stmt1.setString(3, tanggal);
                stmt1.setInt(4, jumlah);
                stmt1.executeUpdate();
            }

            // 2. Update stok di tabel barang (Stok Bertambah)
            String query2 = "UPDATE barang SET stok = stok + ? WHERE id_barang = ?";
            try (PreparedStatement stmt2 = conn.prepareStatement(query2)) {
                stmt2.setInt(1, jumlah);
                stmt2.setInt(2, idBarang);
                stmt2.executeUpdate();
            }

            conn.commit(); // Simpan permanen jika sukses berdua
            return true;
        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) {} // Batalkan semua jika ada error
            return false;
        } finally {
            try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException ex) {}
        }
    }

    // Untuk efisiensi waktu, fitur Hapus kita buat simpel menghapus catatannya saja
    public boolean hapus(int id) {
        String query = "DELETE FROM transaksi_masuk WHERE id_masuk=?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}