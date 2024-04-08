-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 08, 2024 at 06:04 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `demo2`
--

-- --------------------------------------------------------

--
-- Table structure for table `cauhoi`
--

CREATE TABLE `cauhoi` (
  `idcauhoi` int(20) NOT NULL,
  `mamon` varchar(10) NOT NULL,
  `debai` varchar(1000) NOT NULL,
  `dapan1` varchar(1000) NOT NULL,
  `dapan2` varchar(1000) NOT NULL,
  `dapan3` varchar(1000) NOT NULL,
  `dapan4` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cauhoi`
--

INSERT INTO `cauhoi` (`idcauhoi`, `mamon`, `debai`, `dapan1`, `dapan2`, `dapan3`, `dapan4`) VALUES
(1, 'eng', 'I would have visited you before if there _____ quite a lot of people in your house.', 'hadn\'t been', 'hadn\'t', 'wouldn\'t be', 'wasn\'t'),
(2, 'eng', 'If you had caught the bus, you _____ late for work.', 'wouldn\'t have been', 'would have been', 'wouldn’t be', 'would be'),
(3, 'eng', 'If I _____, I would express my feelings.', 'were asked', 'would ask', 'had been asked', 'asked'),
(4, 'eng', 'If _____ as I told her, she would have succeeded.', 'she had done', 'she has done', 'she does', 'she did'),
(5, 'eng', 'Will you be angry if I _____ your pocket dictionary?', 'steal', 'stole', 'have stolen', 'were to steal'),
(6, 'eng', 'You made a mistake by telling her a lie. It _____ better if you _____ to her.', 'would have been/ hadn\'t lied', 'would be/ didn\'t lie', 'will be/ don\'t lie', 'would be/ hadn\'t lied'),
(7, 'eng', 'John would be taking a great risk if he _____ his money in that business.', 'invested', 'would invest', 'had invested', 'invests'),
(8, 'math', 'Hình vuông có mấy cạnh', '4', '3', '1', '2');

-- --------------------------------------------------------

--
-- Table structure for table `dethi`
--

CREATE TABLE `dethi` (
  `made` int(50) NOT NULL,
  `monhoc` varchar(50) NOT NULL,
  `socau` int(11) NOT NULL,
  `thoiluong` int(11) NOT NULL,
  `dscauhoi` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dethi`
--

INSERT INTO `dethi` (`made`, `monhoc`, `socau`, `thoiluong`, `dscauhoi`) VALUES
(3, 'Tieng Anh', 4, 2, '[6, 3, 2, 7]'),
(4, 'Tieng Anh', 5, 2, '[6, 3, 2, 7, 3]');

-- --------------------------------------------------------

--
-- Table structure for table `ketqua`
--

CREATE TABLE `ketqua` (
  `idlanthi` int(11) NOT NULL,
  `made` int(11) NOT NULL,
  `id` varchar(50) NOT NULL,
  `diem` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ketqua`
--

INSERT INTO `ketqua` (`idlanthi`, `made`, `id`, `diem`) VALUES
(1, 0, 'user03', 10),
(2, 3, 'user03', 10),
(3, 3, 'user03', 10),
(4, 4, 'user03', 0);

-- --------------------------------------------------------

--
-- Table structure for table `monhoc`
--

CREATE TABLE `monhoc` (
  `mamon` varchar(50) NOT NULL,
  `tenmon` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `monhoc`
--

INSERT INTO `monhoc` (`mamon`, `tenmon`) VALUES
('eng', 'Tieng Anh'),
('math', 'Toan');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phanquyen` varchar(20) NOT NULL,
  `hoten` varchar(50) NOT NULL,
  `sdt` varchar(10) NOT NULL,
  `quequan` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `phanquyen`, `hoten`, `sdt`, `quequan`) VALUES
('user01', 'admin', '123', 'quantri', 'Nguyễn Văn A', '1234567890', 'Việt Nam'),
('user02', 'teacher', '123', 'giaovien', 'Nguyễn Văn B', '1234567890', 'Thái Lan'),
('user03', 'student', '1234', 'hocsinh', 'Nguyễn Văn C', '1234567890', 'Việt Nam');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cauhoi`
--
ALTER TABLE `cauhoi`
  ADD PRIMARY KEY (`idcauhoi`);

--
-- Indexes for table `dethi`
--
ALTER TABLE `dethi`
  ADD PRIMARY KEY (`made`);

--
-- Indexes for table `ketqua`
--
ALTER TABLE `ketqua`
  ADD PRIMARY KEY (`idlanthi`);

--
-- Indexes for table `monhoc`
--
ALTER TABLE `monhoc`
  ADD PRIMARY KEY (`mamon`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cauhoi`
--
ALTER TABLE `cauhoi`
  MODIFY `idcauhoi` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `dethi`
--
ALTER TABLE `dethi`
  MODIFY `made` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `ketqua`
--
ALTER TABLE `ketqua`
  MODIFY `idlanthi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
