-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 10, 2022 at 05:50 PM
-- Server version: 5.7.31
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `feelbetter`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `idAdmin` int(11) NOT NULL,
  `nom` int(11) NOT NULL,
  `prenom` int(11) NOT NULL,
  `adresseMail` int(11) NOT NULL,
  `password` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `titre` varchar(30) NOT NULL,
  `date` varchar(50) NOT NULL,
  `article` varchar(500) NOT NULL DEFAULT 'non',
  `nom_auteur` varchar(30) NOT NULL,
  `id_user` int(100) NOT NULL,
  `approuver` varchar(255) NOT NULL DEFAULT 'Non',
  `theme` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fkk` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`id`, `titre`, `date`, `article`, `nom_auteur`, `id_user`, `approuver`, `theme`) VALUES
(11, 'article1', '17/02/2022', 'CH8elzuz.pdf', 'auteur', 1, 'Oui', 'theme'),
(12, 'titre1', '17/02/2022', 'H69r2Y59.pdf', 'auteur', 1, 'Oui', 'theme2'),
(13, 'article', '10/03/2022', 'EI87SG6g.pdf', 'seif', 1, 'Non', 'theme'),
(14, 'hey', '10/03/2022', 'vbm4E8HT.pdf', 'aa', 1, 'Non', 'aa'),
(15, 'heyyyyyy', '10/03/2022', 'VWgTKC6l.pdf', 'xxxx', 1, 'Non', 'heeeeeeeeeeeu');

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id_commande` int(11) NOT NULL AUTO_INCREMENT,
  `reference` varchar(25) NOT NULL,
  `qte` int(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `idpanier` int(11) NOT NULL,
  PRIMARY KEY (`id_commande`),
  KEY `ID_patient` (`idpanier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contenu` text NOT NULL,
  `date` varchar(55) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idArticle` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fkUser` (`idUser`),
  KEY `fkArticle` (`idArticle`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commentaire`
--

INSERT INTO `commentaire` (`id`, `contenu`, `date`, `idUser`, `idArticle`) VALUES
(1, 'heyx', '10/03/2022 04:20', 1, 12),
(2, 'hey', '10/03/2022 04:20', 1, 11),
(3, 'heeeeeeeeeeeeey', '10/03/2022 04:21', 1, 11),
(4, 'helo', '10/03/2022 17:30', 2, 11),
(5, 'test', '10/03/2022 17:30', 2, 11);

-- --------------------------------------------------------

--
-- Table structure for table `consultation`
--

DROP TABLE IF EXISTS `consultation`;
CREATE TABLE IF NOT EXISTS `consultation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `temps` varchar(11) NOT NULL,
  `idPatient` int(11) NOT NULL,
  `idExpert` int(11) NOT NULL,
  `confirmation` varchar(5) NOT NULL DEFAULT 'NON',
  PRIMARY KEY (`id`),
  KEY `expertfk` (`idExpert`),
  KEY `patient` (`idPatient`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consultation`
--

INSERT INTO `consultation` (`id`, `date`, `temps`, `idPatient`, `idExpert`, `confirmation`) VALUES
(1, '2022-03-07', '11', 1, 2, 'OUI'),
(4, '2022-03-08', '12', 1, 1, 'OUI'),
(5, '2022-03-06', '1', 1, 2, 'NON'),
(6, '2022-03-07', '10h1m', 1, 2, 'NON'),
(7, '2022-03-09', '13h45m', 1, 2, 'NON'),
(8, '2022-03-08', '15h22m', 1, 1, 'OUI');

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom_event` varchar(30) NOT NULL,
  `date` date NOT NULL,
  `description_event` varchar(100) NOT NULL,
  `prix_event` int(11) NOT NULL,
  `nbr_place` int(11) NOT NULL,
  `image` varchar(255) NOT NULL,
  `valide` varchar(255) NOT NULL,
  `participants` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id`, `nom_event`, `date`, `description_event`, `prix_event`, `nbr_place`, `image`, `valide`, `participants`, `id_user`) VALUES
(4, 'aa', '2022-02-07', 'bb', 1, 1, 'test.png', 'Oui', 1, 1),
(5, 'Evenement', '2022-03-09', 'Description', 122, 4, 'test.png', 'Oui', 0, 1),
(6, 'event', '2022-03-08', 'desc', 12, 1, 'test.png', 'Oui', 1, 1),
(7, 'nom', '2022-03-16', 'description', 122, 12, 'test.png', 'Oui', 1, 1),
(8, 'nom', '2022-03-22', 'desc', 12, 122, 'test.png', 'Oui', 1, 1),
(9, 'aa', '2022-03-11', 'aa', 12, 11, 'seifeddine BEN SALAH.png', 'Oui', 0, 1),
(10, 'aa', '2022-03-23', 'aa', 122, 120, 'seifeddine BEN SALAH.png', 'Oui', 0, 1),
(11, 'aa', '2022-03-16', 'aaa', 12, 12, 'dI1hEBdzseifeddine BEN SALAH.png', 'Oui', 0, 1),
(12, 'hey', '2022-03-31', 'hey', 120, 0, 'pK8Si3hmtest.png', 'Oui', 0, 1),
(13, 'aa', '2022-03-30', 'bbbbb', 122, 12, 'pK8Si3hmtest.png', 'Oui', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `expert`
--

DROP TABLE IF EXISTS `expert`;
CREATE TABLE IF NOT EXISTS `expert` (
  `IdExpert` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `password` varchar(32) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `profession` varchar(25) NOT NULL,
  `tel` int(8) NOT NULL,
  `adresseMail` varchar(50) NOT NULL,
  PRIMARY KEY (`IdExpert`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `expert`
--

INSERT INTO `expert` (`IdExpert`, `nom`, `prenom`, `password`, `adresse`, `profession`, `tel`, `adresseMail`) VALUES
(1, 'ahmed', 'sa', 'sa', 'sa', 'sa', 12, 'seifeddine.bensalah@gmail.com'),
(2, 'samir', 'a', 'a', 'a', 'a', 1, 'seifeddine.bensalah@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `livreur`
--

DROP TABLE IF EXISTS `livreur`;
CREATE TABLE IF NOT EXISTS `livreur` (
  `id_livreur` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `adresseMail` varchar(30) NOT NULL,
  `image` varchar(30) NOT NULL,
  `passwd` int(20) NOT NULL,
  PRIMARY KEY (`id_livreur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `messagerie`
--

DROP TABLE IF EXISTS `messagerie`;
CREATE TABLE IF NOT EXISTS `messagerie` (
  `id_message` int(10) NOT NULL AUTO_INCREMENT,
  `date_envoi` date NOT NULL,
  `contenu` varchar(100) NOT NULL,
  `date_recep` date NOT NULL,
  PRIMARY KEY (`id_message`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `panier`
--

DROP TABLE IF EXISTS `panier`;
CREATE TABLE IF NOT EXISTS `panier` (
  `id_panier` int(11) NOT NULL AUTO_INCREMENT,
  `quantite` int(11) NOT NULL,
  `nbre_de_produit` int(11) NOT NULL,
  PRIMARY KEY (`id_panier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `ID_patient` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `password` varchar(32) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `dateNaissance` datetime NOT NULL,
  `etatCivile` varchar(25) NOT NULL,
  `tel` int(8) NOT NULL,
  `adresseMail` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_patient`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`ID_patient`, `nom`, `prenom`, `password`, `adresse`, `dateNaissance`, `etatCivile`, `tel`, `adresseMail`) VALUES
(1, 'wessim', 'ben', '123456', 'mourouj', '2022-02-16 00:00:00', 'mariee', 12345678, 'seifeddine.bensalah@gmail.com'),
(2, 'ali', 'ben', '123456', 'mourouj', '2022-02-16 00:00:00', 'mariee', 12345678, 'seifeddine.bensalah@gmail.com'),
(3, 'samit', 'ben', '123456', 'mourouj', '2022-02-24 00:00:00', 'marieeeee', 12345678, 'seifeddine.bensalah@gmail.com'),
(4, 'wessim', 'ben', '123456', 'mourouj', '2022-02-24 00:00:00', 'marieeeee', 12345678, 'seifeddine.bensalah@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `idproduit` int(32) NOT NULL AUTO_INCREMENT,
  `nom` int(25) NOT NULL,
  `reference` int(30) NOT NULL,
  `quantité` int(25) NOT NULL,
  `prix` int(8) NOT NULL,
  `image` int(70) NOT NULL,
  `discreption` int(100) NOT NULL,
  PRIMARY KEY (`idproduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `rendez_vous`
--

DROP TABLE IF EXISTS `rendez_vous`;
CREATE TABLE IF NOT EXISTS `rendez_vous` (
  `id_rendez_vous` int(10) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `heure` time(6) NOT NULL,
  `titre` varchar(30) NOT NULL,
  PRIMARY KEY (`id_rendez_vous`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id_user` int(11) NOT NULL,
  `id_event` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk1` (`id_event`),
  KEY `fk2` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id_user`, `id_event`, `id`) VALUES
(3, 6, 16),
(1, 7, 17),
(1, 8, 18),
(3, 4, 21);

-- --------------------------------------------------------

--
-- Table structure for table `shopowner`
--

DROP TABLE IF EXISTS `shopowner`;
CREATE TABLE IF NOT EXISTS `shopowner` (
  `idOwner` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `adresseMail` varchar(50) NOT NULL,
  `tel` int(8) NOT NULL,
  `motdepasse` varchar(32) NOT NULL,
  PRIMARY KEY (`idOwner`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `fkArticle` FOREIGN KEY (`idArticle`) REFERENCES `article` (`id`),
  ADD CONSTRAINT `fkUser` FOREIGN KEY (`idUser`) REFERENCES `patient` (`ID_patient`);

--
-- Constraints for table `consultation`
--
ALTER TABLE `consultation`
  ADD CONSTRAINT `expertfk` FOREIGN KEY (`idExpert`) REFERENCES `expert` (`IdExpert`),
  ADD CONSTRAINT `patient` FOREIGN KEY (`idPatient`) REFERENCES `patient` (`ID_patient`);

--
-- Constraints for table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `fk` FOREIGN KEY (`id_user`) REFERENCES `expert` (`IdExpert`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`id_event`) REFERENCES `evenement` (`id`),
  ADD CONSTRAINT `fk2` FOREIGN KEY (`id_user`) REFERENCES `patient` (`ID_patient`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
