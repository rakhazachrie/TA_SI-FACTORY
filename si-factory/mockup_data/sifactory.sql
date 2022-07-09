--
-- Database: `sifactory`
--

-- --------------------------------------------------------

--
-- Dumping data for table `kategori`
--

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id_role`, `base_wages`, `nama_role`) VALUES
(1, 50000, 'STAFF_ GUDANG'),
(2, 43000, 'STAFF_KURIR'),
(3, 37000, 'STAFF_OPERASIONAL'),
(4, 88000, 'FACTORY_MANAGER'),
(5, 50000, 'ADMIN');

INSERT INTO `kategori` (`id_kategori`, `kategori`) VALUES
(1, 'BUKU'),
(2, 'DAPUR'),
(3, 'MAKANAN & MINUMAN'),
(4, 'ELEKTRONIK'),
(5, 'FASHION'),
(6, 'KECANTIKAN & PERAWATAN DIRI'),
(7, 'FILM & MUSIK'),
(8, 'GAMING'),
(9, 'GADGET'),
(10, 'KESEHATAN'),
(11, 'RUMAH TANGGA'),
(12, 'FURNITURE'),
(13, 'ALAT & PERANGKAT KERAS'),
(14, 'WEDDING');

--
-- Dumping data for table `mesin`
--

INSERT INTO `mesin` (`id_mesin`, `kapasitas`, `nama`, `tanggal_dibuat`, `id_kategori`) VALUES
(1, 98, 'Fuji Xerox Ultimate 999', '2020-10-02', 1),
(2, 100, 'Epson LQ 2190', '2020-11-03', 1),
(3, 100, 'Oxone Ultra', '2021-09-05', 2),
(4, 100, 'Maxim 199', '2021-09-07', 2),
(5, 100, 'Mito MO-888', '2019-09-07', 3),
(6, 100, 'Sekai Grill V 211', '2020-12-07', 3),
(7, 100, 'Kirin KBO 100M', '2020-10-18', 3),
(8, 100, 'Marktech X 232', '2018-10-09', 4),
(9, 100, 'Indoteknik', '2020-11-11', 4),
(10, 100, 'Ezren EZ-2021', '2020-10-11', 4),
(11, 100, 'Ossel GT1103', '2017-10-12', 4),
(12, 99, 'Swinger SW2000', '2020-01-12', 5),
(13, 100, 'Juki Sewing Machine', '2015-08-12', 5),
(14, 100, 'SureLab SL27', '2020-03-21', 6),
(15, 100, 'DigiMax Lab', '2020-10-16', 6),
(16, 100, 'Seagate 111', '2020-10-02', 7),
(17, 100, 'Sony 1909', '2020-11-03', 7),
(18, 99, 'GC001', '2021-09-05', 8),
(19, 100, 'GC002', '2021-09-07', 8),
(20, 100, 'GG001', '2019-09-07', 9),
(21, 100, 'GG002', '2020-12-07', 9),
(22, 100, 'LotusMed', '2020-10-18', 10),
(23, 100, 'Nesco', '2018-10-09', 10),
(24, 100, 'Roche', '2020-11-11', 10),
(25, 99, 'RT001', '2020-10-11', 11),
(26, 100, 'RT002', '2017-10-12', 11),
(27, 100, 'Futura FT99', '2020-01-12', 12),
(28, 100, 'VIP 123', '2015-08-12', 12),
(29, 99, 'Krisbow', '2020-03-21', 13),
(30, 100, 'Maxis', '2020-10-16', 13),
(31, 100, 'Souvenir Machine', '2020-11-01', 14);

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`id_pegawai`, `counter`, `email`, `nama`, `password`, `username`, `id_role`, `tanggal_lahir`) VALUES
(1, 1, 'alkhadrinazahra01@gmail.com', 'Zahra', '$2a$10$TBcCWe6rL0GtIc9k6AjMy.wQ0KLSy5TFIQyjF9LRi3VSujEMh/zOi', 'zahra', 1, '2021-12-09'),
(2, 1, 'alkhadrina.rasyidah@ui.ac.id', 'abc', '$2a$10$phax1mO8bax0OO0fiQYzN.6HQEHr0jE92oq9TUFUxgTIcYJ4LVRbm', 'abc', 5, '2021-12-08'),
(3, 5, 'user@gmail.com', 'user', '$2a$10$89T6girKMzBDkaBBrd2IMeuD8Zw4GpbpysD.IzJA38ldSQba18.8S', 'user', 5, '2021-12-09'),
(4, 0, 'alkhadrinazahra01@gmail.com', 'aaaa', '$2a$10$JKBDLTS1DLFTErjyI4/ze.kLmqowtfyKxjgQ0fMrTQGOVtGpTKgY2', 'seirin3005', 4, '2021-12-10'),
(5, 0, 'papaapap@gmail.com', 'love', '$2a$10$SaUeu9Uey0FMXJocaCBKP.xqz2JTkR9XYEO24DTkbtj3CV0UIbPwm', 'love', 5, '2021-12-10');

--
-- Dumping data for table `produksi`
--

INSERT INTO `produksi` (`id_produksi`, `id_item`, `id_kategori`, `tambahan_stok`, `tanggal_produksi`, `id_mesin`, `id_pegawai`, `id_request_update_item`) VALUES
(1, '2c9035127d6a9d36017d6eb9ccc00007', 8, 10, '2021-12-09', 18, 3, NULL),
(2, '2c9035127d6a9d36017d6eb9ccc00007', 8, 10, '2021-12-09', 18, 3, NULL),
(3, '2c9035127d6a9d36017d6eb9ccc00007', 8, 10, '2021-12-09', 18, 3, NULL),
(4, '2c9140227d9d2d00017d9f87bffb0021', 1, 10, '2021-12-09', 1, 3, NULL),
(5, '2c9035127d6a9d36017d6ebe1b900011', 13, 10, '2021-12-09', 29, 3, NULL),
(6, '2c9140227d9d2d00017da01653770035', 1, 1, '2021-12-10', 1, 3, NULL),
(7, '2c9140227d9d2d00017d9fc47b9f0023', 5, 10, '2021-12-10', 12, 3, NULL),
(8, '2c9140227d9d2d00017d9feacc4f002e', 11, 1, '2021-12-10', 25, 1, NULL);