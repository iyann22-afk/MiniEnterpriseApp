# Sistem Informasi Toko Bangunan (Plafon PVC)
Proyek Final - Mini Enterprise Application

## 📖 Deskripsi Singkat
Aplikasi desktop berbasis Java untuk mendigitalisasi operasional toko bangunan (spesialis Plafon PVC). Aplikasi ini mengelola data master (Barang, Kategori, Pemasok, Pelanggan) dan mencatat alur transaksi (Barang Masuk/Restock dan Barang Keluar/Penjualan), lengkap dengan fitur pemotongan stok otomatis dan cetak laporan ke CSV.

## 🏗️ Arsitektur & Struktur Proyek
Aplikasi ini dikembangkan dengan prinsip **Object-Oriented Programming (OOP)** dan memisahkan logika antarmuka dengan database, menggunakan struktur package berikut:
* `entity/`: Representasi objek/tabel database (POJO).
* `repository/`: Menangani query SQL dan interaksi langsung dengan database.
* `service/`: Menangani logika bisnis aplikasi.
* `controller/`: Penghubung antara View dan logika data, serta menangani exception handling dan validasi input.
* `view/`: Antarmuka pengguna (GUI) murni tanpa query database di dalamnya.
* `database/`: Konfigurasi koneksi ke server MySQL.
* `utils/`: Kelas utilitas tambahan untuk membantu operasional aplikasi.

## ✨ Fitur Utama
1. **Autentikasi:** Login sistem untuk membatasi akses.
2. **Dashboard:** Menu navigasi utama.
3. **Master Data CRUD:** Kategori, Data Barang, Pemasok, dan Pelanggan (dilengkapi fitur Search & Sorting).
4. **Transaksi:** - Barang Masuk (Otomatis menambah stok).
   - Barang Keluar/Penjualan (Otomatis mengurangi stok).
5. **Laporan:** Ekspor data penjualan ke dalam format `.csv`.

## ⚙️ Persyaratan & Cara Instalasi
1. Pastikan **XAMPP** (Apache & MySQL) dan **Java (JDK 17+)** telah terinstal.
2. Clone repository ini ke dalam direktori lokal.
3. Buka phpMyAdmin, buat database baru, lalu import file `database.sql` yang tersedia di repository ini.
4. Buka proyek menggunakan IDE (IntelliJ IDEA direkomendasikan).
5. Tambahkan library **MySQL Connector/J** (`.jar`) ke dalam *Dependencies* proyek.
6. Jalankan kelas `Main.java` yang berada di dalam folder `src`.
7. Gunakan akun default untuk masuk (Username: `admin`, Password: `admin123`).

## 🔄 Git Workflow
Pengembangan proyek ini mematuhi standar *branching* Git. Tidak ada *commit* yang dilakukan langsung ke branch utama (`main`/`master`). Semua penambahan modul dikerjakan di branch terpisah (seperti `feature-login`, `feature-master`, `feature-report`) dan digabungkan melalui proses **Pull Request**.