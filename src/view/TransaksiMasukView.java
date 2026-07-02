package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class TransaksiMasukView extends JFrame {
    private JTextField txtIdMasuk, txtTanggal, txtJumlah, txtSearch;
    private JComboBox<String> cmbBarang, cmbPemasok;
    private JButton btnTambah, btnHapus, btnClear;
    private JTable tableTransaksi;

    public TransaksiMasukView() {
        setTitle("Transaksi Masuk (Restock)");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        initComponents();
    }

    private void initComponents() {
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Form Transaksi Masuk"));

        panelInput.add(new JLabel("ID Transaksi (Auto):"));
        txtIdMasuk = new JTextField(); txtIdMasuk.setEditable(false);
        panelInput.add(txtIdMasuk);

        panelInput.add(new JLabel("Pilih Barang:"));
        cmbBarang = new JComboBox<>(); panelInput.add(cmbBarang);

        panelInput.add(new JLabel("Pilih Pemasok:"));
        cmbPemasok = new JComboBox<>(); panelInput.add(cmbPemasok);

        panelInput.add(new JLabel("Tanggal (Otomatis):"));
        txtTanggal = new JTextField();
        txtTanggal.setText(java.time.LocalDate.now().toString()); // Ambil tanggal hari ini
        txtTanggal.setEditable(false); // Kunci biar ga bisa diedit manual
        panelInput.add(txtTanggal);

        panelInput.add(new JLabel("Jumlah Barang:"));
        txtJumlah = new JTextField(); panelInput.add(txtJumlah);

        JPanel panelTombol = new JPanel();
        btnTambah = new JButton("Tambah Transaksi");
        btnHapus = new JButton("Hapus Data"); btnClear = new JButton("Clear Form");
        panelTombol.add(btnTambah); panelTombol.add(btnHapus); panelTombol.add(btnClear);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        JPanel panelBawah = new JPanel(new BorderLayout());
        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSearch.add(new JLabel("Cari Tanggal:"));
        txtSearch = new JTextField(20);
        panelSearch.add(txtSearch);

        tableTransaksi = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableTransaksi);

        panelBawah.add(panelSearch, BorderLayout.NORTH);
        panelBawah.add(scrollPane, BorderLayout.CENTER);

        // FITUR PEMISAH BISA DIGESER (SPLIT PANE)
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelAtas, panelBawah);
        splitPane.setDividerLocation(230); // Posisi batas awal
        splitPane.setOneTouchExpandable(true);
        add(splitPane, BorderLayout.CENTER);
    }

    public JTextField getTxtIdMasuk() { return txtIdMasuk; }
    public JComboBox<String> getCmbBarang() { return cmbBarang; }
    public JComboBox<String> getCmbPemasok() { return cmbPemasok; }
    public JTextField getTxtTanggal() { return txtTanggal; }
    public JTextField getTxtJumlah() { return txtJumlah; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JButton getBtnTambah() { return btnTambah; }
    public JButton getBtnHapus() { return btnHapus; }
    public JButton getBtnClear() { return btnClear; }
    public JTable getTableTransaksi() { return tableTransaksi; }

    public void setTableModel(DefaultTableModel model) {
        tableTransaksi.setModel(model);
        tableTransaksi.setRowSorter(new TableRowSorter<>(model));
    }
}