package controllers;

import models.KategoriModel;
import views.KategoriView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KategoriController {
    private KategoriView view;
    private KategoriModel model;

    public KategoriController(KategoriView view, KategoriModel model) {
        this.view = view;
        this.model = model;

        initController();
        loadData("");
    }

    private void initController() {
        view.getBtnTambah().addActionListener(e -> {
            String nama = view.getTxtNama().getText();
            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Nama kategori tidak boleh kosong!");
                return;
            }
            if (model.tambah(nama)) {
                JOptionPane.showMessageDialog(view, "Kategori ditambah!");
                loadData(""); clearForm();
            }
        });

        view.getTableKategori().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTableKategori().getSelectedRow();
                int modelRow = view.getTableKategori().convertRowIndexToModel(row);

                view.getTxtId().setText(view.getTableKategori().getModel().getValueAt(modelRow, 0).toString());
                view.getTxtNama().setText(view.getTableKategori().getModel().getValueAt(modelRow, 1).toString());
            }
        });

        view.getBtnEdit().addActionListener(e -> {
            if (view.getTxtId().getText().isEmpty()) return;
            int id = Integer.parseInt(view.getTxtId().getText());
            String nama = view.getTxtNama().getText();

            if (model.update(id, nama)) {
                JOptionPane.showMessageDialog(view, "Kategori diupdate!");
                loadData(""); clearForm();
            }
        });

        view.getBtnHapus().addActionListener(e -> {
            if (view.getTxtId().getText().isEmpty()) return;
            int id = Integer.parseInt(view.getTxtId().getText());
            if (model.hapus(id)) {
                JOptionPane.showMessageDialog(view, "Kategori dihapus!");
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
        view.setTableModel(model.getKategori(keyword));
    }

    private void clearForm() {
        view.getTxtId().setText("");
        view.getTxtNama().setText("");
        view.getTableKategori().clearSelection();
    }
}