package controllers;

import models.BarangModel;
import views.BarangView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BarangController {
    private BarangView view;
    private BarangModel model;

    public BarangController(BarangView view, BarangModel model) {
        this.view = view;
        this.model = model;

        initController();
        loadData(""); // Load awal
    }

    private void initController() {
        // Event Tombol Tambah
        view.getBtnTambah().addActionListener(e -> {
            try {
                String nama = view.getTxtNama().getText();
                int stok = Integer.parseInt(view.getTxtStok().getText());
                double harga = Double.parseDouble(view.getTxtHarga().getText());

                if (model.tambah(nama, stok, harga)) {
                    JOptionPane.showMessageDialog(view, "Data berhasil ditambah!");
                    loadData(""); clearForm();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Stok dan Harga harus berupa angka valid!", "Validasi Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Event Klik Tabel (untuk Edit/Hapus)
        view.getTableBarang().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTableBarang().getSelectedRow();
                // Mengkonversi indeks baris dari view ke model (karena ada Sorting)
                int modelRow = view.getTableBarang().convertRowIndexToModel(row);

                view.getTxtId().setText(view.getTableBarang().getModel().getValueAt(modelRow, 0).toString());
                view.getTxtNama().setText(view.getTableBarang().getModel().getValueAt(modelRow, 1).toString());
                view.getTxtStok().setText(view.getTableBarang().getModel().getValueAt(modelRow, 2).toString());
                view.getTxtHarga().setText(view.getTableBarang().getModel().getValueAt(modelRow, 3).toString());
            }
        });

        // Event Tombol Edit
        view.getBtnEdit().addActionListener(e -> {
            if (view.getTxtId().getText().isEmpty()) return;
            int id = Integer.parseInt(view.getTxtId().getText());
            String nama = view.getTxtNama().getText();
            int stok = Integer.parseInt(view.getTxtStok().getText());
            double harga = Double.parseDouble(view.getTxtHarga().getText());

            if (model.update(id, nama, stok, harga)) {
                JOptionPane.showMessageDialog(view, "Data berhasil diupdate!");
                loadData(""); clearForm();
            }
        });

        // Event Tombol Hapus
        view.getBtnHapus().addActionListener(e -> {
            if (view.getTxtId().getText().isEmpty()) return;
            int id = Integer.parseInt(view.getTxtId().getText());
            if (model.hapus(id)) {
                JOptionPane.showMessageDialog(view, "Data berhasil dihapus!");
                loadData(""); clearForm();
            }
        });

        // Event Clear
        view.getBtnClear().addActionListener(e -> clearForm());

        // Event Search (Mencari saat diketik)
        view.getTxtSearch().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                loadData(view.getTxtSearch().getText());
            }
        });
    }

    private void loadData(String keyword) {
        view.setTableModel(model.getBarang(keyword));
    }

    private void clearForm() {
        view.getTxtId().setText("");
        view.getTxtNama().setText("");
        view.getTxtStok().setText("");
        view.getTxtHarga().setText("");
        view.getTableBarang().clearSelection();
    }
}