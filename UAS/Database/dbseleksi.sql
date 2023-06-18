-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 11 Jun 2023 pada 06.13
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbseleksi`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbakun`
--

CREATE TABLE `tbakun` (
  `Username` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL,
  `Role` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tbakun`
--

INSERT INTO `tbakun` (`Username`, `Password`, `Role`) VALUES
('0021943212', 'IPS', 'user'),
('0021978490', 'BAHASA', 'user'),
('0031247819', 'IPA', 'user'),
('0031278901', 'BAHASA', 'user'),
('0031290870', 'IPA', 'user'),
('0031387201', 'IPS', 'user'),
('0031398705', 'IPA', 'user'),
('0031762890', 'BAHASA', 'user'),
('0031792011', 'IPA', 'user'),
('0071262802', 'IPS', 'user'),
('0071292205', 'BAHASA', 'user'),
('admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbsiswa`
--

CREATE TABLE `tbsiswa` (
  `NISN` varchar(10) NOT NULL,
  `Nama Lengkap` varchar(50) NOT NULL,
  `Jurusan` varchar(10) NOT NULL DEFAULT 'Laki-Laki',
  `Jenis Kelamin` varchar(10) NOT NULL,
  `Agama` int(3) NOT NULL,
  `Pendidikan Kewarganegaraan` int(3) NOT NULL,
  `Bahasa Indonesia` int(3) NOT NULL,
  `Bahasa Inggris` int(3) NOT NULL,
  `Matematika` int(3) NOT NULL,
  `IPA` int(3) NOT NULL,
  `IPS` int(3) NOT NULL,
  `SKOR AKHIR` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tbsiswa`
--

INSERT INTO `tbsiswa` (`NISN`, `Nama Lengkap`, `Jurusan`, `Jenis Kelamin`, `Agama`, `Pendidikan Kewarganegaraan`, `Bahasa Indonesia`, `Bahasa Inggris`, `Matematika`, `IPA`, `IPS`, `SKOR AKHIR`) VALUES
('0021943212', 'Aulian Abila', 'IPS', 'Perempuan', 90, 86, 88, 80, 76, 79, 82, 581),
('0021978490', 'Egaru ', 'BAHASA', 'Laki-Laki', 96, 90, 94, 91, 91, 88, 85, 635),
('0031247819', 'Alexander Van', 'IPA', 'Laki-Laki', 87, 79, 83, 85, 93, 88, 86, 601),
('0031278901', 'Baskara Lingga', 'BAHASA', 'Laki-Laki', 92, 90, 85, 96, 89, 94, 82, 628),
('0031290870', 'Kalya Kana Tabita', 'IPA', 'Perempuan', 88, 79, 83, 89, 91, 90, 72, 592),
('0031387201', 'Aoki Yagara', 'IPS', 'Laki-Laki', 91, 74, 83, 88, 86, 80, 81, 583),
('0031398705', 'Harlin Ajeng Ukirasih', 'IPA', 'Perempuan', 88, 82, 86, 94, 92, 98, 90, 630),
('0031762890', 'Vanya Calista', 'BAHASA', 'Perempuan', 94, 90, 90, 92, 89, 88, 80, 623),
('0031792011', 'Asta Dewangga', 'IPA', 'Laki-Laki', 89, 90, 93, 93, 91, 92, 89, 637),
('0071262802', 'Felisya ', 'IPS', 'Perempuan', 88, 86, 89, 84, 81, 80, 84, 592),
('0071292205', 'Ruby Hoshino', 'BAHASA', 'Perempuan', 88, 80, 97, 93, 78, 83, 82, 601);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tbakun`
--
ALTER TABLE `tbakun`
  ADD PRIMARY KEY (`Username`);

--
-- Indeks untuk tabel `tbsiswa`
--
ALTER TABLE `tbsiswa`
  ADD UNIQUE KEY `NISN` (`NISN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
