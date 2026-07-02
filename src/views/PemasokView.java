package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class PemasokView extends JFrame {
    private JTextField txtId, txtNama, txtKontak, txtSearch;
    private JButton btnTambah, btnEdit, btnHapus, btnClear;
    private JTable tablePemasok;

    public PemasokView() {
        setTitle("Kelola Data Pemasok");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        initComponents();
    }

    private void initComponents() {
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Form Data Pemasok"));

        panelInput.add(new JLabel("ID Pemasok (Auto):"));
        txtId = new JTextField(); txtId.setEditable(false);
        panelInput.add(txtId);

        panelInput.add(new JLabel("Nama Pemasok:"));
        txtNama = new JTextField(); panelInput.add(txtNama);

        panelInput.add(new JLabel("Kontak (No HP/Telp):"));
        txtKontak = new JTextField(); panelInput.add(txtKontak);

        JPanel panelTombol = new JPanel();
        btnTambah = new JButton("Tambah"); btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus"); btnClear = new JButton("Clear");
        panelTombol.add(btnTambah); panelTombol.add(btnEdit);
        panelTombol.add(btnHapus); panelTombol.add(btnClear);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        JPanel panelBawah = new JPanel(new BorderLayout());
        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSearch.add(new JLabel("Cari Pemasok:"));
        txtSearch = new JTextField(20);
        panelSearch.add(txtSearch);

        tablePemasok = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablePemasok);

        panelBawah.add(panelSearch, BorderLayout.NORTH);
        panelBawah.add(scrollPane, BorderLayout.CENTER);

        add(panelAtas, BorderLayout.NORTH);
        add(panelBawah, BorderLayout.CENTER);
    }

    public JTextField getTxtId() { return txtId; }
    public JTextField getTxtNama() { return txtNama; }
    public JTextField getTxtKontak() { return txtKontak; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JButton getBtnTambah() { return btnTambah; }
    public JButton getBtnEdit() { return btnEdit; }
    public JButton getBtnHapus() { return btnHapus; }
    public JButton getBtnClear() { return btnClear; }
    public JTable getTablePemasok() { return tablePemasok; }

    public void setTableModel(DefaultTableModel model) {
        tablePemasok.setModel(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tablePemasok.setRowSorter(sorter);
    }
}