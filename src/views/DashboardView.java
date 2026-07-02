package views;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JLabel lblWelcome;

    public DashboardView() {
        setTitle("Dashboard - Sistem Toko Plafon PVC");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        // Sidebar Kiri (Menu Navigasi)
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setPreferredSize(new Dimension(200, 600));
        sidebarPanel.setBackground(new Color(44, 62, 80));

        String[] menus = {"Dashboard", "Data Barang", "Pelanggan", "Pemasok", "Transaksi Masuk", "Transaksi Keluar", "Logout"};
        for (String menu : menus) {
            JButton btn = new JButton(menu);
            btn.setMaximumSize(new Dimension(200, 40));
            btn.setBackground(new Color(52, 73, 94));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

            // --- TAMBAHKAN LOGIKA KLIK DI SINI ---
            if (menu.equals("Kategori")) {
                btn.addActionListener(e -> {
                    views.KategoriView kView = new views.KategoriView();
                    models.KategoriModel kModel = new models.KategoriModel();
                    new controllers.KategoriController(kView, kModel);
                    kView.setVisible(true);
                });
            } else if (menu.equals("Data Barang")) {
                btn.addActionListener(e -> {
                    // Buka MVC Barang
                    views.BarangView bView = new views.BarangView();
                    models.BarangModel bModel = new models.BarangModel();
                    new controllers.BarangController(bView, bModel);
                    bView.setVisible(true);
                });
            } else if (menu.equals("Pelanggan")) {
                btn.addActionListener(e -> {
                    views.PelangganView pView = new views.PelangganView();
                    models.PelangganModel pModel = new models.PelangganModel();
                    new controllers.PelangganController(pView, pModel);
                    pView.setVisible(true);
                });
            } else if (menu.equals("Pemasok")) {
                btn.addActionListener(e -> {
                    views.PemasokView supView = new views.PemasokView();
                    models.PemasokModel supModel = new models.PemasokModel();
                    new controllers.PemasokController(supView, supModel);
                    supView.setVisible(true);
                });
            } else if (menu.equals("Transaksi Masuk")) {
                btn.addActionListener(e -> {
                    views.TransaksiMasukView tmView = new views.TransaksiMasukView();
                    models.TransaksiMasukModel tmModel = new models.TransaksiMasukModel();
                    new controllers.TransaksiMasukController(tmView, tmModel);
                    tmView.setVisible(true);
                });
            } else if (menu.equals("Transaksi Keluar")) {
            } else if (menu.equals("Logout")) {
                btn.addActionListener(e -> {
                    this.dispose(); // Tutup dashboard
                    // Kembali ke halaman utama/login
                    new controllers.LoginController(new views.LoginView(), new models.UserModel());
                });
            }
            // -------------------------------------

            sidebarPanel.add(btn);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        // Konten Utama Kanan
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        lblWelcome = new JLabel("Selamat Datang di Sistem Informasi Toko", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(lblWelcome, BorderLayout.CENTER);

        // Tambahkan ke Frame
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }
}