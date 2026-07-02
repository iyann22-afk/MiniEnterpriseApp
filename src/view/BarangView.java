package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class BarangView extends JFrame {
    private JTextField txtId, txtNama, txtStok, txtHarga, txtSearch;
    private JButton btnTambah, btnEdit, btnHapus, btnClear;
    private JTable tableBarang;

    public BarangView() {
        setTitle("Kelola Data Barang (Plafon PVC)");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        initComponents();
    }

    private void initComponents() {
        // Panel Input (Atas)
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Form Data Barang"));

        panelInput.add(new JLabel("ID Barang (Otomatis):"));
        txtId = new JTextField(); txtId.setEditable(false);
        panelInput.add(txtId);

        panelInput.add(new JLabel("Nama Barang:"));
        txtNama = new JTextField(); panelInput.add(txtNama);

        panelInput.add(new JLabel("Stok:"));
        txtStok = new JTextField(); panelInput.add(txtStok);

        panelInput.add(new JLabel("Harga:"));
        txtHarga = new JTextField(); panelInput.add(txtHarga);

        // Panel Tombol
        JPanel panelTombol = new JPanel();
        btnTambah = new JButton("Tambah"); btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus"); btnClear = new JButton("Clear");
        panelTombol.add(btnTambah); panelTombol.add(btnEdit);
        panelTombol.add(btnHapus); panelTombol.add(btnClear);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        // Panel Tabel & Search (Bawah)
        JPanel panelBawah = new JPanel(new BorderLayout());
        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSearch.add(new JLabel("Cari Barang:"));
        txtSearch = new JTextField(20);
        panelSearch.add(txtSearch);

        tableBarang = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableBarang);

        panelBawah.add(panelSearch, BorderLayout.NORTH);
        panelBawah.add(scrollPane, BorderLayout.CENTER);

        add(panelAtas, BorderLayout.NORTH);
        add(panelBawah, BorderLayout.CENTER);
    }

    // Getter untuk diakses Controller
    public JTextField getTxtId() { return txtId; }
    public JTextField getTxtNama() { return txtNama; }
    public JTextField getTxtStok() { return txtStok; }
    public JTextField getTxtHarga() { return txtHarga; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JButton getBtnTambah() { return btnTambah; }
    public JButton getBtnEdit() { return btnEdit; }
    public JButton getBtnHapus() { return btnHapus; }
    public JButton getBtnClear() { return btnClear; }
    public JTable getTableBarang() { return tableBarang; }

    // Metode untuk Sorting Tabel
    public void setTableModel(DefaultTableModel model) {
        tableBarang.setModel(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tableBarang.setRowSorter(sorter); // Ini memenuhi syarat Sorting otomatis
    }
}