package utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatRupiah {
    // Kelas utilitas untuk memformat nominal angka menjadi format mata uang Rupiah
    public static String format(double nominal) {
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return formatRupiah.format(nominal);
    }
}