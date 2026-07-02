package controller;

import repository.TransaksiKeluarModel;
import view.TransaksiKeluarView;
import database.Database;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFileChooser;

public class TransaksiKeluarController {
    private TransaksiKeluarView view;
    private TransaksiKeluarModel model;

    public TransaksiKeluarController(TransaksiKeluarView view, TransaksiKeluarModel model) {
        this.view = view;
        this.model = model;
        loadComboBoxData();
        initController();
        loadData("");
    }

    private void loadComboBoxData() {
        try (Connection conn = Database.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rsBarang = stmt.executeQuery("SELECT id_barang, nama_barang FROM barang");
            while (rsBarang.next()) view.getCmbBarang().addItem(rsBarang.getInt("id_barang") + " - " + rsBarang.getString("nama_barang"));

            ResultSet rsPelanggan = stmt.executeQuery("SELECT id_pelanggan, nama_pelanggan FROM pelanggan");
            while (rsPelanggan.next()) view.getCmbPelanggan().addItem(rsPelanggan.getInt("id_pelanggan") + " - " + rsPelanggan.getString("nama_pelanggan"));
        } catch (Exception e) { System.out.println("Error Load ComboBox: " + e.getMessage()); }
    }

    private void initController() {
        view.getBtnTambah().addActionListener(e -> {
            try {
                String selectedBarang = view.getCmbBarang().getSelectedItem().toString();
                int idBarang = Integer.parseInt(selectedBarang.split(" - ")[0]);

                String selectedPelanggan = view.getCmbPelanggan().getSelectedItem().toString();
                int idPelanggan = Integer.parseInt(selectedPelanggan.split(" - ")[0]);

                String tanggal = view.getTxtTanggal().getText();
                int jumlah = Integer.parseInt(view.getTxtJumlah().getText());
                double total = Double.parseDouble(view.getTxtTotal().getText());

                if (model.tambah(idBarang, idPelanggan, tanggal, jumlah, total)) {
                    JOptionPane.showMessageDialog(view, "Penjualan berhasil, Stok berkurang!");
                    loadData(""); clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Gagal! Transaksi dibatalkan.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Pastikan angka dimasukkan dengan benar!");
            }
        });

        // Fitur CETAK LAPORAN (Pilih Direktori Sendiri)
        view.getBtnLaporan().addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Simpan Laporan Penjualan (Excel/CSV)");
            // Nama file default
            fileChooser.setSelectedFile(new File("Laporan_Penjualan_Toko.csv"));

            // Munculkan dialog Save
            int userSelection = fileChooser.showSaveDialog(view);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();

                // Otomatis tambah .csv kalau user lupa ngetik ekstensinya
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".csv")) {
                    fileToSave = new File(filePath + ".csv");
                }

                try (PrintWriter writer = new PrintWriter(fileToSave)) {
                    StringBuilder sb = new StringBuilder();
                    // Header Excel
                    sb.append("ID Keluar,ID Barang,ID Pelanggan,Tanggal,Jumlah,Total Harga\n");

                    // Tarik semua data dari tabel di layar
                    for (int i = 0; i < view.getTableTransaksi().getRowCount(); i++) {
                        for (int j = 0; j < view.getTableTransaksi().getColumnCount(); j++) {
                            sb.append(view.getTableTransaksi().getValueAt(i, j));
                            if (j < view.getTableTransaksi().getColumnCount() - 1) sb.append(",");
                        }
                        sb.append("\n");
                    }
                    writer.write(sb.toString());

                    JOptionPane.showMessageDialog(view, "Mantap! Laporan berhasil disimpan di:\n" + fileToSave.getAbsolutePath());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Gagal mencetak laporan: " + ex.getMessage(), "Error IO", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.getTableTransaksi().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTableTransaksi().getSelectedRow();
                int modelRow = view.getTableTransaksi().convertRowIndexToModel(row);
                view.getTxtIdKeluar().setText(view.getTableTransaksi().getModel().getValueAt(modelRow, 0).toString());
                view.getTxtTanggal().setText(view.getTableTransaksi().getModel().getValueAt(modelRow, 3).toString());
                view.getTxtJumlah().setText(view.getTableTransaksi().getModel().getValueAt(modelRow, 4).toString());
                view.getTxtTotal().setText(view.getTableTransaksi().getModel().getValueAt(modelRow, 5).toString());
            }
        });

        view.getBtnHapus().addActionListener(e -> {
            if (view.getTxtIdKeluar().getText().isEmpty()) return;
            if (model.hapus(Integer.parseInt(view.getTxtIdKeluar().getText()))) {
                JOptionPane.showMessageDialog(view, "Data dihapus!"); loadData(""); clearForm();
            }
        });

        view.getBtnClear().addActionListener(e -> clearForm());
        view.getTxtSearch().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { loadData(view.getTxtSearch().getText()); }
        });
    }

    private void loadData(String keyword) { view.setTableModel(model.getTransaksi(keyword)); }
    private void clearForm() {
        view.getTxtIdKeluar().setText("");
        view.getTxtJumlah().setText("");
        view.getTxtTotal().setText("");
        // Pastikan tanggal kembali ke hari ini, bukan kosong
        view.getTxtTanggal().setText(java.time.LocalDate.now().toString());
    }
}