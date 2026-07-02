package controllers;

import models.UserModel;
import views.LoginView;
import views.DashboardView;
import javax.swing.*;

public class LoginController {
    private LoginView view;
    private UserModel model;

    public LoginController(LoginView view, UserModel model) {
        this.view = view;
        this.model = model;

        // Event saat tombol login diklik
        this.view.getBtnLogin().addActionListener(e -> prosesLogin());
    }

    private void prosesLogin() {
        String username = view.getUsername();
        String password = view.getPassword();

        // Validasi Input (Syarat Ketentuan)
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Username dan Password tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Cek ke Database
        if (model.authenticate(username, password)) {
            JOptionPane.showMessageDialog(view, "Login Berhasil!");
            view.dispose(); // Tutup layar login

            // Buka Dashboard
            DashboardView dashboard = new DashboardView();
            dashboard.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(view, "Username atau Password salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}