package controllers;

import models.PelangganModel;
import views.PelangganView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PelangganController {
    private PelangganView view;
    private PelangganModel model;

    public PelangganController(PelangganView view, PelangganModel model) {
        this.view = view;
        this.model = model;

        initController();
        loadData("");
    }

    private void initController() {
        view.getBtnTambah().addActionListener(e -> {
            String nama = view.getTxtNama().getText();
            String alamat = view.getTxtAlamat().getText();

            if (nama.isEmpty() || alamat.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Data tidak boleh kosong!");
                return;
            }

            if (model.tambah(nama, alamat)) {
                JOptionPane.showMessageDialog(view, "Pelanggan berhasil ditambah!");
                loadData(""); clearForm();
            }
        });

        view.getTablePelanggan().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTablePelanggan().getSelectedRow();
                int modelRow = view.getTablePelanggan().convertRowIndexToModel(row);

                view.getTxtId().setText(view.getTablePelanggan().getModel().getValueAt(modelRow, 0).toString());
                view.getTxtNama().setText(view.getTablePelanggan().getModel().getValueAt(modelRow, 1).toString());
                view.getTxtAlamat().setText(view.getTablePelanggan().getModel().getValueAt(modelRow, 2).toString());
            }
        });

        view.getBtnEdit().addActionListener(e -> {
            if (view.getTxtId().getText().isEmpty()) return;
            int id = Integer.parseInt(view.getTxtId().getText());
            String nama = view.getTxtNama().getText();
            String alamat = view.getTxtAlamat().getText();

            if (model.update(id, nama, alamat)) {
                JOptionPane.showMessageDialog(view, "Pelanggan diupdate!");
                loadData(""); clearForm();
            }
        });

        view.getBtnHapus().addActionListener(e -> {
            if (view.getTxtId().getText().isEmpty()) return;
            int id = Integer.parseInt(view.getTxtId().getText());
            if (model.hapus(id)) {
                JOptionPane.showMessageDialog(view, "Pelanggan dihapus!");
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
        view.setTableModel(model.getPelanggan(keyword));
    }

    private void clearForm() {
        view.getTxtId().setText("");
        view.getTxtNama().setText("");
        view.getTxtAlamat().setText("");
        view.getTablePelanggan().clearSelection();
    }
}