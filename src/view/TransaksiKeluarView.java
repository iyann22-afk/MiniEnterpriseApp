package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class TransaksiKeluarView extends JFrame {
    private JTextField txtIdKeluar, txtIdBarang, txtIdPelanggan, txtTanggal, txtJumlah, txtTotal, txtSearch;
    private JButton btnTambah, btnHapus, btnClear, btnLaporan;
    private JTable tableTransaksi;

    public TransaksiKeluarView() {
        setTitle("Transaksi Keluar (Penjualan & Laporan)");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        initComponents();
    }

    private void initComponents() {
        JPanel panelInput = new JPanel(new GridLayout(6, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Form Penjualan"));

        panelInput.add(new JLabel("ID Transaksi (Auto):"));
        txtIdKeluar = new JTextField(); txtIdKeluar.setEditable(false);
        panelInput.add(txtIdKeluar);

        panelInput.add(new JLabel("ID Barang:"));
        txtIdBarang = new JTextField(); panelInput.add(txtIdBarang);

        panelInput.add(new JLabel("ID Pelanggan:"));
        txtIdPelanggan = new JTextField(); panelInput.add(txtIdPelanggan);

        panelInput.add(new JLabel("Tanggal (YYYY-MM-DD):"));
        txtTanggal = new JTextField(); panelInput.add(txtTanggal);

        panelInput.add(new JLabel("Jumlah Jual:"));
        txtJumlah = new JTextField(); panelInput.add(txtJumlah);

        panelInput.add(new JLabel("Total Harga:"));
        txtTotal = new JTextField(); panelInput.add(txtTotal);

        JPanel panelTombol = new JPanel();
        btnTambah = new JButton("Catat Penjualan"); btnHapus = new JButton("Hapus Data");
        btnClear = new JButton("Clear Form");
        btnLaporan = new JButton("CETAK LAPORAN (CSV)");
        btnLaporan.setBackground(new Color(39, 174, 96)); btnLaporan.setForeground(Color.WHITE);

        panelTombol.add(btnTambah); panelTombol.add(btnHapus);
        panelTombol.add(btnClear); panelTombol.add(btnLaporan);

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

        add(panelAtas, BorderLayout.NORTH);
        add(panelBawah, BorderLayout.CENTER);
    }

    public JTextField getTxtIdKeluar() { return txtIdKeluar; }
    public JTextField getTxtIdBarang() { return txtIdBarang; }
    public JTextField getTxtIdPelanggan() { return txtIdPelanggan; }
    public JTextField getTxtTanggal() { return txtTanggal; }
    public JTextField getTxtJumlah() { return txtJumlah; }
    public JTextField getTxtTotal() { return txtTotal; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JButton getBtnTambah() { return btnTambah; }
    public JButton getBtnHapus() { return btnHapus; }
    public JButton getBtnClear() { return btnClear; }
    public JButton getBtnLaporan() { return btnLaporan; }
    public JTable getTableTransaksi() { return tableTransaksi; }

    public void setTableModel(DefaultTableModel model) {
        tableTransaksi.setModel(model);
        tableTransaksi.setRowSorter(new TableRowSorter<>(model));
    }
}