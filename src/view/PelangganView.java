package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class PelangganView extends JFrame {
    private JTextField txtId, txtNama, txtAlamat, txtSearch;
    private JButton btnTambah, btnEdit, btnHapus, btnClear;
    private JTable tablePelanggan;

    public PelangganView() {
        setTitle("Kelola Data Pelanggan");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        initComponents();
    }

    private void initComponents() {
        // Panel Input
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Form Data Pelanggan"));

        panelInput.add(new JLabel("ID Pelanggan (Auto):"));
        txtId = new JTextField(); txtId.setEditable(false);
        panelInput.add(txtId);

        panelInput.add(new JLabel("Nama Pelanggan:"));
        txtNama = new JTextField(); panelInput.add(txtNama);

        panelInput.add(new JLabel("Alamat:"));
        txtAlamat = new JTextField(); panelInput.add(txtAlamat);

        // Panel Tombol
        JPanel panelTombol = new JPanel();
        btnTambah = new JButton("Tambah"); btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus"); btnClear = new JButton("Clear");
        panelTombol.add(btnTambah); panelTombol.add(btnEdit);
        panelTombol.add(btnHapus); panelTombol.add(btnClear);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        // Panel Tabel & Search
        JPanel panelBawah = new JPanel(new BorderLayout());
        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSearch.add(new JLabel("Cari Pelanggan:"));
        txtSearch = new JTextField(20);
        panelSearch.add(txtSearch);

        tablePelanggan = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablePelanggan);

        panelBawah.add(panelSearch, BorderLayout.NORTH);
        panelBawah.add(scrollPane, BorderLayout.CENTER);

        add(panelAtas, BorderLayout.NORTH);
        add(panelBawah, BorderLayout.CENTER);
    }

    public JTextField getTxtId() { return txtId; }
    public JTextField getTxtNama() { return txtNama; }
    public JTextField getTxtAlamat() { return txtAlamat; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JButton getBtnTambah() { return btnTambah; }
    public JButton getBtnEdit() { return btnEdit; }
    public JButton getBtnHapus() { return btnHapus; }
    public JButton getBtnClear() { return btnClear; }
    public JTable getTablePelanggan() { return tablePelanggan; }

    public void setTableModel(DefaultTableModel model) {
        tablePelanggan.setModel(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tablePelanggan.setRowSorter(sorter);
    }
}