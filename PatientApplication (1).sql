-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 24, 2024 at 09:43 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `PatientApplication`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `adresses`
--

CREATE TABLE `adresses` (
  `id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `adresses`
--

INSERT INTO `adresses` (`id`, `city`, `street`, `zip_code`) VALUES
(1, 'Kraków', 'Długa 27', '00-000'),
(2, 'Kraków', 'Karmelicka 25', '00-000'),
(3, 'Kraków', 'Długa 27', '00-000'),
(4, 'Kraków', 'Długa 42', '00-000'),
(6, 'Kraków', 'Długa 42', '00-000'),
(7, 'Kraków', 'Długa 42', '32-048'),
(8, 'Kraków', 'Długa 42', '00-000'),
(9, 'Kraków', 'Długa 42', '00-000'),
(10, 'Kraków', 'Długa 42', '00-000'),
(11, 'Kraków', 'Długa 42', '00-000'),
(12, 'Kraków', 'Długa 42', '32-048'),
(13, 'Kraków', 'Długa 42', '32-048'),
(14, 'Kraków', 'Długa 42', '32-048'),
(15, 'Kraków', 'Długa 42', '32-048'),
(16, 'Kraków', 'Długa 42', '32-048'),
(17, 'Kraków', 'Długa 42', '32-048');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `patient`
--

CREATE TABLE `patient` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `medical_history` varchar(255) DEFAULT NULL,
  `pesel` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','MANAGER','PATIENT','CLIENT','WORKER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `email`, `first_name`, `last_name`, `medical_history`, `pesel`, `role`) VALUES
(2, 'test@gmail.com', 'Test', 'Test', '1 Test\r\nTest 2', '1111111111', 'PATIENT');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `patient_address_pivot`
--

CREATE TABLE `patient_address_pivot` (
  `userid` bigint(20) NOT NULL,
  `addressid` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patient_address_pivot`
--

INSERT INTO `patient_address_pivot` (`userid`, `addressid`) VALUES
(2, 11);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `patient_phone_pivot`
--

CREATE TABLE `patient_phone_pivot` (
  `patientid` bigint(20) NOT NULL,
  `phoneid` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patient_phone_pivot`
--

INSERT INTO `patient_phone_pivot` (`patientid`, `phoneid`) VALUES
(2, 11);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `phone_number`
--

CREATE TABLE `phone_number` (
  `id` bigint(20) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `prefix` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phone_number`
--

INSERT INTO `phone_number` (`id`, `phone`, `prefix`) VALUES
(1, '000000000', '+48'),
(2, '111111111', '+67'),
(3, '00000000', '+48'),
(4, '99999999', '+672'),
(6, '567030355', '+486'),
(7, '09090909', '+48'),
(8, '567030355', '+486'),
(9, '567030355', '+486'),
(10, '567030355', '+486'),
(11, '567030355', '+486'),
(12, '09090909', '+48'),
(13, '09090909', '+48'),
(14, '09090909', '+48'),
(15, '09090909', '+48'),
(16, '09090909', '+48'),
(17, '09090909', '+48');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','MANAGER','PATIENT','CLIENT','WORKER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES
(1, 'admin@gmail.com', 'Admin', 'Admin', '$2a$10$W.lXvpa3XX92phA.ChNDsObSeGHkW5nONADFfTz50lzBkb7yd73C.', 'ADMIN'),
(2, 'manager@gmail.com', 'Manger', 'Manager', '$2a$10$W.lXvpa3XX92phA.ChNDsObSeGHkW5nONADFfTz50lzBkb7yd73C.', 'MANAGER'),
(3, 'worker@gmail.com', 'worker', 'worker', '$2a$10$W.lXvpa3XX92phA.ChNDsObSeGHkW5nONADFfTz50lzBkb7yd73C.', 'WORKER'),
(4, 'client@gmail.com', 'Client', 'Client', NULL, 'CLIENT');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_address_pivot`
--

CREATE TABLE `user_address_pivot` (
  `userid` bigint(20) NOT NULL,
  `addressid` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_address_pivot`
--

INSERT INTO `user_address_pivot` (`userid`, `addressid`) VALUES
(2, 2),
(1, 3),
(4, 17);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_phone_pivot`
--

CREATE TABLE `user_phone_pivot` (
  `userid` bigint(20) NOT NULL,
  `phoneid` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_phone_pivot`
--

INSERT INTO `user_phone_pivot` (`userid`, `phoneid`) VALUES
(1, 3),
(2, 2),
(4, 17);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `adresses`
--
ALTER TABLE `adresses`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bawli8xm92f30ei6x9p3h8eju` (`email`),
  ADD UNIQUE KEY `UK_qii4qjiqoi5eoj6q5u00mxprg` (`pesel`);

--
-- Indeksy dla tabeli `patient_address_pivot`
--
ALTER TABLE `patient_address_pivot`
  ADD KEY `FK5ssc44g8a0iq0q67dbb1ypryk` (`addressid`),
  ADD KEY `FKcxn0x3gci3vc9gfqslc5x1myn` (`userid`);

--
-- Indeksy dla tabeli `patient_phone_pivot`
--
ALTER TABLE `patient_phone_pivot`
  ADD UNIQUE KEY `UK_rokue4h7rm95clbaxngus4enc` (`phoneid`),
  ADD KEY `FKqqtcuev5pqqps42xxfkjgdif0` (`patientid`);

--
-- Indeksy dla tabeli `phone_number`
--
ALTER TABLE `phone_number`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- Indeksy dla tabeli `user_address_pivot`
--
ALTER TABLE `user_address_pivot`
  ADD KEY `FK3blwmomqj5gacldtt9u7na9p4` (`addressid`),
  ADD KEY `FK4oqkd1dfg9mm8n5ptsnj1i3sl` (`userid`);

--
-- Indeksy dla tabeli `user_phone_pivot`
--
ALTER TABLE `user_phone_pivot`
  ADD UNIQUE KEY `UK_ggdhqv2kfl55aumsrvvtbro4e` (`phoneid`),
  ADD KEY `FK15547feiwi8b871xg3osa4xn3` (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adresses`
--
ALTER TABLE `adresses`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `phone_number`
--
ALTER TABLE `phone_number`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `patient_address_pivot`
--
ALTER TABLE `patient_address_pivot`
  ADD CONSTRAINT `FK5ssc44g8a0iq0q67dbb1ypryk` FOREIGN KEY (`addressid`) REFERENCES `adresses` (`id`),
  ADD CONSTRAINT `FKcxn0x3gci3vc9gfqslc5x1myn` FOREIGN KEY (`userid`) REFERENCES `patient` (`id`);

--
-- Constraints for table `patient_phone_pivot`
--
ALTER TABLE `patient_phone_pivot`
  ADD CONSTRAINT `FK2mcb0i64o2xbfevrajwqke368` FOREIGN KEY (`phoneid`) REFERENCES `phone_number` (`id`),
  ADD CONSTRAINT `FKqqtcuev5pqqps42xxfkjgdif0` FOREIGN KEY (`patientid`) REFERENCES `patient` (`id`);

--
-- Constraints for table `user_address_pivot`
--
ALTER TABLE `user_address_pivot`
  ADD CONSTRAINT `FK3blwmomqj5gacldtt9u7na9p4` FOREIGN KEY (`addressid`) REFERENCES `adresses` (`id`),
  ADD CONSTRAINT `FK4oqkd1dfg9mm8n5ptsnj1i3sl` FOREIGN KEY (`userid`) REFERENCES `user` (`id`);

--
-- Constraints for table `user_phone_pivot`
--
ALTER TABLE `user_phone_pivot`
  ADD CONSTRAINT `FK15547feiwi8b871xg3osa4xn3` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKq8jv2uh9u2cxnrunm2reddxtr` FOREIGN KEY (`phoneid`) REFERENCES `phone_number` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
