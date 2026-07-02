package controller;

import repository.TransaksiMasukModel;
import view.TransaksiMasukView;
import database.Database;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TransaksiMasukController {
    private TransaksiMasukView view;
    private TransaksiMasukModel model;

    public TransaksiMasukController(TransaksiMasukView view, TransaksiMasukModel model) {
        this.view = view;
        this.model = model;

        loadComboBoxData(); // Panggil data dropdown
        initController();
        loadData("");
    }

    private void loadComboBoxData() {
        try (Connection conn = Database.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rsBarang = stmt.executeQuery("SELECT id_barang, nama_barang FROM barang");
            while (rsBarang.next()) view.getCmbBarang().addItem(rsBarang.getInt("id_barang") + " - " + rsBarang.getString("nama_barang"));

            ResultSet rsPemasok = stmt.executeQuery("SELECT id_pemasok, nama_pemasok FROM pemasok");
            while (rsPemasok.next()) view.getCmbPemasok().addItem(rsPemasok.getInt("id_pemasok") + " - " + rsPemasok.getString("nama_pemasok"));
        } catch (Exception e) { System.out.println("Error Load ComboBox: " + e.getMessage()); }
    }

    private void initController() {
        view.getBtnTambah().addActionListener(e -> {
            try {
                // Memisahkan ID dari teks "1 - Nama Barang"
                String selectedBarang = view.getCmbBarang().getSelectedItem().toString();
                int idBarang = Integer.parseInt(selectedBarang.split(" - ")[0]);

                String selectedPemasok = view.getCmbPemasok().getSelectedItem().toString();
                int idPemasok = Integer.parseInt(selectedPemasok.split(" - ")[0]);

                String tanggal = view.getTxtTanggal().getText();
                int jumlah = Integer.parseInt(view.getTxtJumlah().getText());

                if (tanggal.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Tanggal tidak boleh kosong!");
                    return;
                }

                if (model.tambah(idBarang, idPemasok, tanggal, jumlah)) {
                    JOptionPane.showMessageDialog(view, "Transaksi Masuk berhasil!");
                    loadData(""); clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Gagal menambahkan data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Isi form dengan benar!", "Validasi Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        view.getTableTransaksi().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTableTransaksi().getSelectedRow();
                int modelRow = view.getTableTransaksi().convertRowIndexToModel(row);
                view.getTxtIdMasuk().setText(view.getTableTransaksi().getModel().getValueAt(modelRow, 0).toString());
                view.getTxtTanggal().setText(view.getTableTransaksi().getModel().getValueAt(modelRow, 3).toString());
                view.getTxtJumlah().setText(view.getTableTransaksi().getModel().getValueAt(modelRow, 4).toString());
            }
        });

        view.getBtnHapus().addActionListener(e -> {
            if (view.getTxtIdMasuk().getText().isEmpty()) return;
            if (model.hapus(Integer.parseInt(view.getTxtIdMasuk().getText()))) {
                JOptionPane.showMessageDialog(view, "Riwayat dihapus!"); loadData(""); clearForm();
            }
        });

        view.getBtnClear().addActionListener(e -> clearForm());
        view.getTxtSearch().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { loadData(view.getTxtSearch().getText()); }
        });
    }

    private void loadData(String keyword) { view.setTableModel(model.getTransaksi(keyword)); }
    private void clearForm() {
        view.getTxtIdMasuk().setText("");
        view.getTxtJumlah().setText("");
        view.getTableTransaksi().clearSelection();
        // Pastikan tanggal kembali ke hari ini, bukan kosong
        view.getTxtTanggal().setText(java.time.LocalDate.now().toString());
    }
}