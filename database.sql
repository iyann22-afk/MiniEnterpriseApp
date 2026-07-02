-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 02, 2026 at 04:36 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_toko_plafon`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(11) NOT NULL,
  `id_kategori` int(11) DEFAULT NULL,
  `nama_barang` varchar(150) NOT NULL,
  `stok` int(11) DEFAULT 0,
  `harga` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `id_kategori`, `nama_barang`, `stok`, `harga`) VALUES
(3, 1, 'Plafon PVC Serat Kayu Jati 4m', 249, 15000.00),
(4, 1, 'Plafon PVC Kayu Mahoni 4m', 220, 15500.00),
(5, 2, 'Plafon PVC Putih Polos 4m', 300, 13000.00),
(6, 2, 'Plafon PVC Putih Glossy 4m', 250, 13500.00),
(7, 3, 'Plafon PVC Motif Bunga Mawar 4m', 100, 16000.00),
(8, 3, 'Plafon PVC Motif Bunga Tulip 4m', 90, 16000.00),
(9, 4, 'Plafon PVC Motif Awan Biru 4m', 80, 17000.00),
(10, 5, 'Lis Plafon PVC Emas Klasik 4m', 200, 20000.00),
(11, 5, 'Lis Plafon PVC Emas Ukir 4m', 150, 22000.00),
(12, 6, 'Lis Plafon PVC Coklat Tua 4m', 180, 18000.00),
(13, 6, 'Lis Plafon PVC Coklat Muda 4m', 160, 18000.00),
(14, 7, 'Lis Dinding PVC Putih 4m', 120, 19000.00),
(15, 8, 'Ornamen Lampu Tengah Bulat', 50, 75000.00),
(16, 8, 'Ornamen Lampu Tengah Kotak', 45, 80000.00),
(17, 9, 'Rangka Hollow 2x4 Galvanis', 400, 25000.00),
(18, 9, 'Rangka Hollow 4x4 Galvanis', 350, 35000.00),
(19, 10, 'Paku Beton 2 Inch (Per Dus)', 60, 45000.00),
(20, 10, 'Lem Silikon Plafon Clear', 100, 30000.00),
(21, 10, 'Lem Silikon Plafon Putih', 110, 30000.00),
(23, 1, 'Plafon PVC Serat Kayu Jati 4m', 150, 15000.00),
(24, 1, 'Plafon PVC Kayu Mahoni 4m', 120, 15500.00),
(25, 2, 'Plafon PVC Putih Polos 4m', 300, 13000.00),
(26, 2, 'Plafon PVC Putih Glossy 4m', 250, 13500.00),
(27, 3, 'Plafon PVC Motif Bunga Mawar 4m', 100, 16000.00),
(28, 3, 'Plafon PVC Motif Bunga Tulip 4m', 90, 16000.00),
(29, 4, 'Plafon PVC Motif Awan Biru 4m', 80, 17000.00),
(30, 5, 'Lis Plafon PVC Emas Klasik 4m', 200, 20000.00),
(31, 5, 'Lis Plafon PVC Emas Ukir 4m', 150, 22000.00),
(32, 6, 'Lis Plafon PVC Coklat Tua 4m', 180, 18000.00),
(33, 6, 'Lis Plafon PVC Coklat Muda 4m', 160, 18000.00),
(34, 7, 'Lis Dinding PVC Putih 4m', 120, 19000.00),
(35, 8, 'Ornamen Lampu Tengah Bulat', 50, 75000.00),
(36, 8, 'Ornamen Lampu Tengah Kotak', 45, 80000.00),
(37, 9, 'Rangka Hollow 2x4 Galvanis', 400, 25000.00),
(38, 9, 'Rangka Hollow 4x4 Galvanis', 350, 35000.00),
(39, 10, 'Paku Beton 2 Inch (Per Dus)', 60, 45000.00),
(40, 10, 'Lem Silikon Plafon Clear', 100, 30000.00),
(41, 10, 'Lem Silikon Plafon Putih', 110, 30000.00),
(42, 10, 'Sekrup Gypsum (Per Dus)', 85, 40000.00),
(43, NULL, '.......', 249, 15000.00);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` int(11) NOT NULL,
  `nama_kategori` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`) VALUES
(1, 'Plafon PVC Motif Kayu'),
(2, 'Plafon PVC Putih Polos'),
(3, 'Plafon PVC Motif Bunga'),
(4, 'Plafon PVC Motif Awan'),
(5, 'Lis Plafon PVC Emas'),
(6, 'Lis Plafon PVC Coklat'),
(7, 'Lis Dinding Minimalis'),
(8, 'Ornamen Lampu Tengah'),
(9, 'Rangka Hollow Galvanis'),
(10, 'Lem & Aksesoris'),
(11, 'Plafon PVC Motif Kayu'),
(12, 'Plafon PVC Putih Polos'),
(13, 'Plafon PVC Motif Bunga'),
(14, 'Plafon PVC Motif Awan'),
(15, 'Lis Plafon PVC Emas'),
(16, 'Lis Plafon PVC Coklat'),
(17, 'Lis Dinding Minimalis'),
(18, 'Ornamen Lampu Tengah'),
(19, 'Rangka Hollow Galvanis'),
(20, 'Lem & Aksesoris'),
(21, 'Plafon PVC Motif Kayu'),
(22, 'Plafon PVC Putih Polos'),
(23, 'Plafon PVC Motif Bunga'),
(24, 'Plafon PVC Motif Awan'),
(25, 'Lis Plafon PVC Emas'),
(26, 'Lis Plafon PVC Coklat'),
(27, 'Lis Dinding Minimalis'),
(28, 'Ornamen Lampu Tengah'),
(29, 'Rangka Hollow Galvanis'),
(30, 'Lem & Aksesoris'),
(31, 'Plafon PVC Motif Kayu'),
(32, 'Plafon PVC Putih Polos'),
(33, 'Plafon PVC Motif Bunga'),
(34, 'Plafon PVC Motif Awan'),
(35, 'Lis Plafon PVC Emas'),
(36, 'Lis Plafon PVC Coklat'),
(37, 'Lis Dinding Minimalis'),
(38, 'Ornamen Lampu Tengah'),
(39, 'Rangka Hollow Galvanis'),
(40, 'Lem & Aksesoris'),
(41, 'Plafon PVC Motif Kayu'),
(42, 'Plafon PVC Putih Polos'),
(43, 'Plafon PVC Motif Bunga'),
(44, 'Plafon PVC Motif Awan'),
(45, 'Lis Plafon PVC Emas'),
(46, 'Lis Plafon PVC Coklat'),
(47, 'Lis Dinding Minimalis'),
(48, 'Ornamen Lampu Tengah'),
(49, 'Rangka Hollow Galvanis'),
(50, 'Lem & Aksesoris');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(11) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `alamat`) VALUES
(1, 'Nabila Maharani', 'Jl. Pramuka, Rajabasa'),
(2, 'Budi Santoso', 'Way Halim, Bandar Lampung'),
(3, 'Siti Aminah', 'Liwa, Lampung Barat'),
(4, 'Agus Setiawan', 'Kemiling, Bandar Lampung'),
(5, 'Rina Marlina', 'Sukarame, Bandar Lampung'),
(6, 'Dedi Saputra', 'Jl. ZA Pagar Alam, Rajabasa'),
(7, 'Eka Yulianti', 'Kedaton, Bandar Lampung'),
(8, 'Hendra Gunawan', 'Way Tenong, Lampung Barat'),
(9, 'Indra Lesmana', 'Tanjung Karang, Bandar Lampung'),
(10, 'Joko Susilo', 'Teluk Betung, Bandar Lampung'),
(11, 'Kartika Putri', 'Natar, Lampung Selatan'),
(12, 'Lukman Hakim', 'Sekincau, Lampung Barat'),
(13, 'Maya Sari', 'Jl. Raden Intan, Bandar Lampung'),
(14, 'Nurul Hidayah', 'Bumi Manti, Kampung Baru'),
(15, 'Oki Pratama', 'Pringsewu'),
(16, 'Putri Rahayu', 'Jl. Ki Maja, Way Halim'),
(17, 'Rizky Aditya', 'Sukabumi, Bandar Lampung'),
(18, 'Toni Suhendra', 'Balik Bukit, Lampung Barat'),
(19, 'Umar Dani', 'Labuhan Ratu, Bandar Lampung'),
(20, 'Vina Panduwinata', 'Gedong Tataan, Pesawaran'),
(21, 'Nabila Maharani', 'Jl. Pramuka, Rajabasa'),
(22, 'Budi Santoso', 'Way Halim, Bandar Lampung'),
(23, 'Siti Aminah', 'Liwa, Lampung Barat'),
(24, 'Agus Setiawan', 'Kemiling, Bandar Lampung'),
(25, 'Rina Marlina', 'Sukarame, Bandar Lampung'),
(26, 'Dedi Saputra', 'Jl. ZA Pagar Alam, Rajabasa'),
(27, 'Eka Yulianti', 'Kedaton, Bandar Lampung'),
(28, 'Hendra Gunawan', 'Way Tenong, Lampung Barat'),
(29, 'Indra Lesmana', 'Tanjung Karang, Bandar Lampung'),
(30, 'Joko Susilo', 'Teluk Betung, Bandar Lampung'),
(31, 'Kartika Putri', 'Natar, Lampung Selatan'),
(32, 'Lukman Hakim', 'Sekincau, Lampung Barat'),
(33, 'Maya Sari', 'Jl. Raden Intan, Bandar Lampung'),
(34, 'Nurul Hidayah', 'Bumi Manti, Kampung Baru'),
(35, 'Oki Pratama', 'Pringsewu'),
(36, 'Putri Rahayu', 'Jl. Ki Maja, Way Halim'),
(37, 'Rizky Aditya', 'Sukabumi, Bandar Lampung'),
(38, 'Toni Suhendra', 'Balik Bukit, Lampung Barat'),
(39, 'Umar Dani', 'Labuhan Ratu, Bandar Lampung'),
(40, 'Vina Panduwinata', 'Gedong Tataan, Pesawaran');

-- --------------------------------------------------------

--
-- Table structure for table `pemasok`
--

CREATE TABLE `pemasok` (
  `id_pemasok` int(11) NOT NULL,
  `nama_pemasok` varchar(100) NOT NULL,
  `kontak` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pemasok`
--

INSERT INTO `pemasok` (`id_pemasok`, `nama_pemasok`, `kontak`) VALUES
(2, 'CV Plafon Jaya', '081122334455'),
(3, 'PT PVC Nusantara', '081133445566'),
(4, 'Toko Material Makmur', '081144556677'),
(5, 'Agen Plafon Indah', '081155667788'),
(6, 'Distributor Hollow Lampung', '081166778899'),
(7, 'Bintang PVC', '081177889900'),
(8, 'Mega Baja & Rangka', '081188990011'),
(9, 'CV Lis Elegan', '081199001122'),
(10, 'Toko Bangunan Sentosa', '081200112233'),
(11, 'PT Ornamen Emas', '081211223344'),
(12, 'Plafon Kuat Abadi', '081222334455'),
(13, 'Grosir PVC Sumatra', '081233445566'),
(14, 'Toko Besi Rajawali', '081244556677'),
(15, 'Agen Paku & Lem', '081255667788'),
(16, 'Sumber Gypsum & PVC', '081266778899'),
(17, 'CV Mitra Bangunan', '081277889900'),
(18, 'PT Rangka Kokoh', '081288990011'),
(19, 'Toko Ornamen Klasik', '081299001122'),
(20, 'Distributor Lis Minimalis', '081300112233'),
(21, 'PVC Mandiri Sejahtera', '081311223344'),
(22, 'CV Plafon Jaya', '081122334455'),
(23, 'PT PVC Nusantara', '081133445566'),
(24, 'Toko Material Makmur', '081144556677'),
(25, 'Agen Plafon Indah', '081155667788'),
(26, 'Distributor Hollow Lampung', '081166778899'),
(27, 'Bintang PVC', '081177889900'),
(28, 'Mega Baja & Rangka', '081188990011'),
(29, 'CV Lis Elegan', '081199001122'),
(30, 'Toko Bangunan Sentosa', '081200112233'),
(31, 'PT Ornamen Emas', '081211223344'),
(32, 'Plafon Kuat Abadi', '081222334455'),
(33, 'Grosir PVC Sumatra', '081233445566'),
(34, 'Toko Besi Rajawali', '081244556677'),
(35, 'Agen Paku & Lem', '081255667788'),
(36, 'Sumber Gypsum & PVC', '081266778899'),
(37, 'CV Mitra Bangunan', '081277889900'),
(38, 'PT Rangka Kokoh', '081288990011'),
(39, 'Toko Ornamen Klasik', '081299001122'),
(40, 'Distributor Lis Minimalis', '081300112233'),
(41, 'PVC Mandiri Sejahtera', '081311223344');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_keluar`
--

CREATE TABLE `transaksi_keluar` (
  `id_keluar` int(11) NOT NULL,
  `id_barang` int(11) DEFAULT NULL,
  `id_pelanggan` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `tanggal` date NOT NULL,
  `jumlah` int(11) NOT NULL,
  `total_harga` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi_keluar`
--

INSERT INTO `transaksi_keluar` (`id_keluar`, `id_barang`, `id_pelanggan`, `id_user`, `tanggal`, `jumlah`, `total_harga`) VALUES
(1, 3, 1, 1, '2026-07-02', 1, 15000.00);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_masuk`
--

CREATE TABLE `transaksi_masuk` (
  `id_masuk` int(11) NOT NULL,
  `id_barang` int(11) DEFAULT NULL,
  `id_pemasok` int(11) DEFAULT NULL,
  `tanggal` date NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi_masuk`
--

INSERT INTO `transaksi_masuk` (`id_masuk`, `id_barang`, `id_pemasok`, `tanggal`, `jumlah`) VALUES
(41, 3, 2, '2026-07-02', 100),
(42, 4, 3, '2026-07-02', 100);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Admin','Kasir') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin123', 'Admin'),
(2, 'yandri', 'yandri123', 'Admin'),
(3, 'lala', 'lala123', 'Kasir'),
(4, 'budi', 'budi123', 'Kasir');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD KEY `id_kategori` (`id_kategori`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `pemasok`
--
ALTER TABLE `pemasok`
  ADD PRIMARY KEY (`id_pemasok`);

--
-- Indexes for table `transaksi_keluar`
--
ALTER TABLE `transaksi_keluar`
  ADD PRIMARY KEY (`id_keluar`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `transaksi_masuk`
--
ALTER TABLE `transaksi_masuk`
  ADD PRIMARY KEY (`id_masuk`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_pemasok` (`id_pemasok`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id_kategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `pemasok`
--
ALTER TABLE `pemasok`
  MODIFY `id_pemasok` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `transaksi_keluar`
--
ALTER TABLE `transaksi_keluar`
  MODIFY `id_keluar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `transaksi_masuk`
--
ALTER TABLE `transaksi_masuk`
  MODIFY `id_masuk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`) ON DELETE CASCADE;

--
-- Constraints for table `transaksi_keluar`
--
ALTER TABLE `transaksi_keluar`
  ADD CONSTRAINT `transaksi_keluar_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`),
  ADD CONSTRAINT `transaksi_keluar_ibfk_2` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`),
  ADD CONSTRAINT `transaksi_keluar_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);

--
-- Constraints for table `transaksi_masuk`
--
ALTER TABLE `transaksi_masuk`
  ADD CONSTRAINT `transaksi_masuk_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`),
  ADD CONSTRAINT `transaksi_masuk_ibfk_2` FOREIGN KEY (`id_pemasok`) REFERENCES `pemasok` (`id_pemasok`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
