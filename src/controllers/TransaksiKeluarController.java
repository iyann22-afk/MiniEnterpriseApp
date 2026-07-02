package controllers;

import models.TransaksiKeluarModel;
import views.TransaksiKeluarView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.PrintWriter;

public class TransaksiKeluarController {
    private TransaksiKeluarView view;
    private TransaksiKeluarModel model;

    public TransaksiKeluarController(TransaksiKeluarView view, TransaksiKeluarModel model) {
        this.view = view;
        this.model = model;
        initController();
        loadData("");
    }

    private void initController() {
        view.getBtnTambah().addActionListener(e -> {
            try {
                int idBarang = Integer.parseInt(view.getTxtIdBarang().getText());
                int idPelanggan = Integer.parseInt(view.getTxtIdPelanggan().getText());
                String tanggal = view.getTxtTanggal().getText();
                int jumlah = Integer.parseInt(view.getTxtJumlah().getText());
                double total = Double.parseDouble(view.getTxtTotal().getText());

                if (model.tambah(idBarang, idPelanggan, tanggal, jumlah, total)) {
                    JOptionPane.showMessageDialog(view, "Penjualan berhasil, Stok berkurang!");
                    loadData(""); clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Gagal! Cek ID Barang/Pelanggan.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Input angka tidak valid!");
            }
        });

        // Fitur CETAK LAPORAN
        view.getBtnLaporan().addActionListener(e -> {
            try (PrintWriter writer = new PrintWriter(new File("Laporan_Penjualan.csv"))) {
                StringBuilder sb = new StringBuilder();
                sb.append("ID Keluar,ID Barang,ID Pelanggan,Tanggal,Jumlah,Total Harga\n");

                for (int i = 0; i < view.getTableTransaksi().getRowCount(); i++) {
                    for (int j = 0; j < view.getTableTransaksi().getColumnCount(); j++) {
                        sb.append(view.getTableTransaksi().getValueAt(i, j));
                        if (j < view.getTableTransaksi().getColumnCount() - 1) sb.append(",");
                    }
                    sb.append("\n");
                }
                writer.write(sb.toString());
                JOptionPane.showMessageDialog(view, "Laporan berhasil diekspor ke Laporan_Penjualan.csv di folder project!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Gagal mencetak laporan: " + ex.getMessage(), "Error IO", JOptionPane.ERROR_MESSAGE);
            }
        });

        view.getTableTransaksi().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTableTransaksi().getSelectedRow();
                int modelRow = view.getTableTransaksi().convertRowIndexToModel(row);
                view.getTxtIdKeluar().setText(view.getTableTransaksi().getModel().getValueAt(modelRow, 0).toString());
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
        view.getTxtIdKeluar().setText(""); view.getTxtIdBarang().setText("");
        view.getTxtIdPelanggan().setText(""); view.getTxtTanggal().setText("");
        view.getTxtJumlah().setText(""); view.getTxtTotal().setText("");
    }
}