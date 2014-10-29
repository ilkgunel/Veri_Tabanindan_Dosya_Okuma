-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 29 Eki 2014, 21:03:28
-- Sunucu sürümü: 5.6.17
-- PHP Sürümü: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Veritabanı: `insankaynaklari`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cvler`
--

CREATE TABLE IF NOT EXISTS `cvler` (
  `Ad` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `CV` varchar(100) COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `cvler`
--

INSERT INTO `cvler` (`Ad`, `CV`) VALUES
('Fatih Çal', 'C:\\Users\\ilkay\\Documents\\NetBeansProjects\\JSFDosyaVeriTabani\\Dosyalar\\Fatih Çal CV.docx'),
('Serhat Altınevlek', 'C:\\Users\\ilkay\\Documents\\NetBeansProjects\\JSFDosyaVeriTabani\\Dosyalar\\Serhat Altınevlek CV.docx'),
('İlkay Günel', 'C:\\Users\\ilkay\\Documents\\NetBeansProjects\\JSFDosyaVeriTabani\\Dosyalar\\İLKAY GÜNEL CV.docx'),
('Muhammed Özdemir', 'C:\\Users\\ilkay\\Documents\\NetBeansProjects\\JSFDosyaVeriTabani\\Dosyalar\\Muhammed Özdemir CV.docx');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
