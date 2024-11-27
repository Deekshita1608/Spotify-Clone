-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Nov 07, 2024 at 05:39 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spotify-clone`
--

-- --------------------------------------------------------

--
-- Table structure for table `artists`
--

CREATE TABLE `artists` (
  `ID` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `artists`
--

INSERT INTO `artists` (`ID`, `name`, `image`) VALUES
(1, 'Arijit Singh', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\Arijit Singh.png'),
(2, 'Jaspinder Narula', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\Jaspinder Narula.png'),
(3, 'Kavita Krishnamurthy', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\Kavita Krishnamurthy.png'),
(4, 'KK', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\KK.png'),
(5, 'Mujtaba Aziz Naza', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\Mujtaba Aziz Naza.png'),
(6, 'Osman Mir', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\Osman Mir.png'),
(7, 'Pritam', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\Pritam.png'),
(8, 'Shabab Sabri', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\Shabab Sabri.png'),
(9, 'Shreya Ghoshal', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\Shreya Goshal.png'),
(10, 'Siddharth-Garima', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\Siddharth-Garima.png'),
(11, 'Swaroop Khan', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\Swaroop Khan.png'),
(12, 'Taylor Swift', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\Taylor Swift.png'),
(13, 'Udit Narayan', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\Udit Narayan.png'),
(14, 'Vaishali Made', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\artist_icons\\cropped_images\\Vaishali Made.png');

-- --------------------------------------------------------

--
-- Table structure for table `songs`
--

CREATE TABLE `songs` (
  `ID` int(3) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Artist` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  `album` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL,
  `Duration` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `songs`
--

INSERT INTO `songs` (`ID`, `Name`, `Artist`, `location`, `album`, `image`, `Duration`) VALUES
(1, 'A Broken Heart', 'Unknown', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\a broken heart.mp3', 'Unknown', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\a broken heart.jpeg', '3:42'),
(3, 'Ami Je Tomar (Kartik X Vidya)', 'Pritam,Arijit Singh,Shreya Ghoshal', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\Ami Je Tomar (Kartik X Vidya).mp3', 'Ami Je Tomar (Kartik X Vidya)', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\ami je tomar.jpg', '6:12'),
(4, 'Bairi Piya - From \"Devdas\"', 'Udit Narayan,Shreya Ghoshal', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\Bairi Piya - From _Devdas_.mp3', 'Devdas (Original Motion Picture Soundtrack)', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\bairi piya.jpeg', '5:22'),
(5, 'Bhari Bhari', 'Shreya Ghoshal', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\Bhari Bhari.mp3', 'Mann Bheetar', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\bhari bhari.jpg', '8:44'),
(6, 'Deewani Mastani', 'Shreya Ghoshal,Ganesh Chandanshive,Mujtaba Aziz Naza,Shadab Faridi,Altamash Faridi,Farhan Sabri,Sidd', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\Deewani Mastani.mp3', 'Bajirao Mastani (Original Motion Picture Soundtrack)', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\deewani mastani.jpg', '5:39'),
(7, 'Dola Re Dola - From \"Devdas\"', 'Kavita Krishnamurthy,Shreya Ghoshal,KK', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\Dola Re Dola - From _Devdas_.mp3', 'Devdas (Original Motion Picture Soundtrack)', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\dola re.jpeg', '6:35'),
(8, 'Ghoomar', 'Shreya Ghoshal,Swaroop Khan', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\Ghoomar.mp3', 'Padmaavat', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\ghoomar.jpeg', '4:42'),
(9, 'Mere Dholna (The Sisters)', 'Pritam,Shreya Ghoshal', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\Mere Dholna (The Sisters).mp3', 'Bhool Bhulaiyaa 2', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\mere dholna.jpg', '2:25'),
(10, 'Mere Dholna Revisited', 'Pritam,Shreya Ghoshal', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\Mere Dholna Revisited.mp3', 'Bhool Bhulaiyaa 2', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\mere dholna.jpg', '2:31'),
(11, 'Morey Piya - From \"Devdas\"', 'Jaspinder Narula,Shreya Ghoshal', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\Morey Piya - From _Devdas_.mp3', 'Devdas (Original Motion Picture Soundtrack)', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\morey piya.jpeg', '5:40'),
(12, 'Nagada Sang Dhol', 'Shreya Ghoshal,Osman Mir', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\Nagada Sang Dhol.mp3', 'Ram-Leela (Original Motion Picture Soundtrack)', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\nagada sang.jpg', '4:33'),
(13, 'Nature Sounds', 'Unknown', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\nature sounds.mp3', 'Unknown', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\nature sounds.jpeg', '3:1'),
(14, 'Pinga', 'Shreya Ghoshal,Vaishali Made,Siddharth - Garima', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\Pinga.mp3', 'Bajirao Mastani (Original Motion Picture Soundtrack)', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\pinga.jpeg', '4:15'),
(15, 'Silsila Ye Chahat Ka - From \"Devdas\"', 'Shreya Ghoshal', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\classical_playlist\\Silsila Ye Chahat Ka - From _Devdas_.mp', 'Devdas (Original Motion Picture Soundtrack)', 'C:\\Users\\deeks\\Desktop\\Spotify_clone\\imgs\\songs_icons\\silsila ye.jpeg', '5:7');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `email`, `password`) VALUES
('Deekshita', 'deekshitaathreya@gmail.com', '1234');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
