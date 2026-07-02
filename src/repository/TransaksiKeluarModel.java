package repository;

import database.Database;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransaksiKeluarModel {
    public DefaultTableModel getTransaksi(String keyword) {
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID Keluar", "ID Barang", "ID Pelanggan", "Tanggal", "Jumlah", "Total Harga"}, 0);
        String query = "SELECT id_keluar, id_barang, id_pelanggan, tanggal, jumlah, total_harga FROM transaksi_keluar WHERE tanggal LIKE ?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id_keluar"), rs.getInt("id_barang"), rs.getInt("id_pelanggan"),
                        rs.getString("tanggal"), rs.getInt("jumlah"), rs.getDouble("total_harga")
                });
            }
        } catch (SQLException e) { System.out.println("Error Load Data: " + e.getMessage()); }
        return tableModel;
    }

    public boolean tambah(int idBarang, int idPelanggan, String tanggal, int jumlah, double totalHarga) {
        Connection conn = null;
        try {
            conn = Database.getConnection();
            conn.setAutoCommit(false); // Transaksi Database (Rollback jika gagal)

            // 1. Catat Penjualan (id_user diset 1 untuk default Admin)
            String query1 = "INSERT INTO transaksi_keluar (id_barang, id_pelanggan, id_user, tanggal, jumlah, total_harga) VALUES (?, ?, 1, ?, ?, ?)";
            try (PreparedStatement stmt1 = conn.prepareStatement(query1)) {
                stmt1.setInt(1, idBarang); stmt1.setInt(2, idPelanggan);
                stmt1.setString(3, tanggal); stmt1.setInt(4, jumlah); stmt1.setDouble(5, totalHarga);
                stmt1.executeUpdate();
            }

            // 2. Kurangi Stok Barang
            String query2 = "UPDATE barang SET stok = stok - ? WHERE id_barang = ?";
            try (PreparedStatement stmt2 = conn.prepareStatement(query2)) {
                stmt2.setInt(1, jumlah); stmt2.setInt(2, idBarang);
                stmt2.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) {}
            return false;
        } finally {
            try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException ex) {}
        }
    }

    public boolean hapus(int id) {
        String query = "DELETE FROM transaksi_keluar WHERE id_keluar=?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id); return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}