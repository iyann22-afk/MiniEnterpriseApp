package service;

import javax.swing.JTextField;

public class ValidasiService {
    // Layanan untuk memvalidasi form agar aplikasi tidak error jika input kosong
    public static boolean isFormKosong(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                return true; // Mengembalikan true jika ada 1 saja yang kosong
            }
        }
        return false;
    }
}