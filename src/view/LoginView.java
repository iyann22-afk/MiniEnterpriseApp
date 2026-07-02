package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Login Sistem Plafon PVC");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Posisi di tengah layar
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        formPanel.add(txtUsername);

        formPanel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        formPanel.add(txtPassword);

        JPanel buttonPanel = new JPanel();
        btnLogin = new JButton("Login");
        buttonPanel.add(btnLogin);

        add(new JLabel("Silakan Login", SwingConstants.CENTER), BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getUsername() { return txtUsername.getText(); }
    public String getPassword() { return new String(txtPassword.getPassword()); }
    public JButton getBtnLogin() { return btnLogin; }
}