package controllers;

import models.TransaksiMasukModel;
import views.TransaksiMasukView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransaksiMasukController {
    private TransaksiMasukView view;
    private TransaksiMasukModel model;

    public TransaksiMasukController(TransaksiMasukView view, TransaksiMasukModel model) {
        this.view = view;
        this.model = model;

        initController();
        loadData("");
    }

    private void initController() {
        view.getBtnTambah().addActionListener(e -> {
            try {
                int idBarang = Integer.parseInt(view.getTxtIdBarang().getText());
                int idPemasok = Integer.parseInt(view.getTxtIdPemasok().getText());
                String tanggal = view.getTxtTanggal().getText();
                int jumlah = Integer.parseInt(view.getTxtJumlah().getText());

                if (tanggal.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Tanggal tidak boleh kosong!");
                    return;
                }

                if (model.tambah(idBarang, idPemasok, tanggal, jumlah)) {
                    JOptionPane.showMessageDialog(view, "Transaksi Masuk berhasil, Stok bertambah!");
                    loadData(""); clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Transaksi Gagal. Pastikan ID Barang/Pemasok valid.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "ID dan Jumlah harus berupa angka!", "Validasi Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        view.getTableTransaksi().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTableTransaksi().getSelectedRow();
                int modelRow = view.getTableTransaksi().convertRowIndexToModel(row);

                view.getTxtIdMasuk().setText(view.getTableTransaksi().getModel().getValueAt(modelRow, 0).toString());
                view.getTxtIdBarang().setText(view.getTableTransaksi().getModel().getValueAt(modelRow, 1).toString());
                view.getTxtIdPemasok().setText(view.getTableTransaksi().getModel().getValueAt(modelRow, 2).toString());
                view.getTxtTanggal().setText(view.getTableTransaksi().getModel().getValueAt(modelRow, 3).toString());
                view.getTxtJumlah().setText(view.getTableTransaksi().getModel().getValueAt(modelRow, 4).toString());
            }
        });

        view.getBtnHapus().addActionListener(e -> {
            if (view.getTxtIdMasuk().getText().isEmpty()) return;
            int id = Integer.parseInt(view.getTxtIdMasuk().getText());
            if (model.hapus(id)) {
                JOptionPane.showMessageDialog(view, "Riwayat Transaksi dihapus!");
                loadData(""); clearForm();
            }
        });

        view.getBtnClear().addActionListener(e -> clearForm());

        view.getTxtSearch().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                loadData(view.getTxtSearch().getText());
            }
        });
    }

    private void loadData(String keyword) {
        view.setTableModel(model.getTransaksi(keyword));
    }

    private void clearForm() {
        view.getTxtIdMasuk().setText("");
        view.getTxtIdBarang().setText("");
        view.getTxtIdPemasok().setText("");
        view.getTxtTanggal().setText("");
        view.getTxtJumlah().setText("");
        view.getTableTransaksi().clearSelection();
    }
}