package controller;

import repository.PemasokModel;
import view.PemasokView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PemasokController {
    private PemasokView view;
    private PemasokModel model;

    public PemasokController(PemasokView view, PemasokModel model) {
        this.view = view;
        this.model = model;

        initController();
        loadData("");
    }

    private void initController() {
        view.getBtnTambah().addActionListener(e -> {
            String nama = view.getTxtNama().getText();
            String kontak = view.getTxtKontak().getText();

            if (nama.isEmpty() || kontak.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Data tidak boleh kosong!");
                return;
            }

            if (model.tambah(nama, kontak)) {
                JOptionPane.showMessageDialog(view, "Pemasok berhasil ditambah!");
                loadData(""); clearForm();
            }
        });

        view.getTablePemasok().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.getTablePemasok().getSelectedRow();
                int modelRow = view.getTablePemasok().convertRowIndexToModel(row);

                view.getTxtId().setText(view.getTablePemasok().getModel().getValueAt(modelRow, 0).toString());
                view.getTxtNama().setText(view.getTablePemasok().getModel().getValueAt(modelRow, 1).toString());
                view.getTxtKontak().setText(view.getTablePemasok().getModel().getValueAt(modelRow, 2).toString());
            }
        });

        view.getBtnEdit().addActionListener(e -> {
            if (view.getTxtId().getText().isEmpty()) return;
            int id = Integer.parseInt(view.getTxtId().getText());
            String nama = view.getTxtNama().getText();
            String kontak = view.getTxtKontak().getText();

            if (model.update(id, nama, kontak)) {
                JOptionPane.showMessageDialog(view, "Pemasok diupdate!");
                loadData(""); clearForm();
            }
        });

        view.getBtnHapus().addActionListener(e -> {
            if (view.getTxtId().getText().isEmpty()) return;
            int id = Integer.parseInt(view.getTxtId().getText());
            if (model.hapus(id)) {
                JOptionPane.showMessageDialog(view, "Pemasok dihapus!");
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
        view.setTableModel(model.getPemasok(keyword));
    }

    private void clearForm() {
        view.getTxtId().setText("");
        view.getTxtNama().setText("");
        view.getTxtKontak().setText("");
        view.getTablePemasok().clearSelection();
    }
}