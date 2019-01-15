-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jan 15, 2019 at 09:27 AM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `ecom_bd`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `id_categorie` int(11) NOT NULL,
  `lib_categorie` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`id_categorie`, `lib_categorie`) VALUES
(1, 'Science-fiction'),
(2, 'Philosophie'),
(3, 'Policier'),
(4, 'Roman Autobiographique'),
(5, 'Bande dessinee');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `login` varchar(150) NOT NULL,
  `nom_client` varchar(150) NOT NULL,
  `prenom_client` varchar(150) NOT NULL,
  `email_client` varchar(150) NOT NULL,
  `date_inscription` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `motdepasse` varchar(150) NOT NULL,
  `sexe` varchar(150) NOT NULL,
  `date_naissance` varchar(150) NOT NULL,
  `adresse` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`login`, `nom_client`, `prenom_client`, `email_client`, `date_inscription`, `motdepasse`, `sexe`, `date_naissance`, `adresse`) VALUES
('logintest', 'Chaar', 'Ismail', 'ismail.chaar@gmail.com', '1997-11-12 00:00:00.000000', 'azerty06', 'homme', '1997-11-12', 'ensias');

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE `commande` (
  `id_cmd` int(11) NOT NULL,
  `date_cmd` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `etat_cmd` varchar(150) NOT NULL DEFAULT 'non valide',
  `login` varchar(150) NOT NULL,
  `num_facture` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `commande`
--

INSERT INTO `commande` (`id_cmd`, `date_cmd`, `etat_cmd`, `login`, `num_facture`) VALUES
(1, '2019-01-15 09:16:22.917206', 'valide', 'logintest', 'F1');

-- --------------------------------------------------------

--
-- Table structure for table `facture`
--

CREATE TABLE `facture` (
  `num_facture` varchar(150) NOT NULL,
  `id_cmd` int(11) NOT NULL,
  `prix_total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `facture`
--

INSERT INTO `facture` (`num_facture`, `id_cmd`, `prix_total`) VALUES
('F1', 1, 150);

-- --------------------------------------------------------

--
-- Table structure for table `panier`
--

CREATE TABLE `panier` (
  `id_panier` int(11) NOT NULL,
  `login` varchar(150) NOT NULL,
  `prix_total_panier` float NOT NULL,
  `date_enregistrement` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `panier`
--

INSERT INTO `panier` (`id_panier`, `login`, `prix_total_panier`, `date_enregistrement`) VALUES
(3, 'logintest', 100, '2019-01-15 09:20:22.063322'),
(4, 'logintest', 100, '2019-01-15 09:22:16.390947'),
(5, 'logintest', 100, '2019-01-15 09:23:20.606302'),
(6, 'logintest', 100, '2019-01-15 09:26:19.833550');

-- --------------------------------------------------------

--
-- Table structure for table `panier_produit`
--

CREATE TABLE `panier_produit` (
  `model` varchar(150) NOT NULL,
  `id_panier` int(11) NOT NULL,
  `quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `panier_produit`
--

INSERT INTO `panier_produit` (`model`, `id_panier`, `quantite`) VALUES
('MOD176', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `id_categorie` int(11) NOT NULL,
  `lib_produit` varchar(300) NOT NULL,
  `model` varchar(150) NOT NULL,
  `prix` int(11) NOT NULL,
  `image` varchar(300) NOT NULL,
  `date_ajout` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `qte_stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id_categorie`, `lib_produit`, `model`, `prix`, `image`, `date_ajout`, `qte_stock`) VALUES
(4, 'Le premier homme', 'MOD043', 150, 'css/images/8.jpg', '2019-01-13 23:40:06.252798', 29),
(2, 'Propos sur le bonheur', 'MOD098', 150, 'css/images/4.jpg', '2019-01-13 23:09:07.347802', 30),
(2, 'Discours de la servitude volontaire', 'MOD175', 150, 'css/images/5.jpg', '2019-01-13 23:14:30.537488', 40),
(1, 'Les mille et une nuits', 'MOD176', 100, 'css/images/7.jpg', '2019-01-13 23:39:13.661608', 40),
(3, 'Les Aventures de Sherlock Holmes', 'MOD384', 150, 'css/images/6.jpg', '2019-01-13 23:22:16.217337', 50),
(5, 'Tintin au Congo', 'MOD421', 100, 'css/images/9.jpg', '2019-01-13 23:40:59.674190', 50),
(1, 'La planete des singes', 'MOD467', 100, 'css/images/2.jpg', '2019-01-13 22:55:01.281864', 50),
(2, 'Le Monde de Sophie', 'MOD643', 100, 'css/images/3.jpg', '2019-01-13 23:06:27.955078', 40);

-- --------------------------------------------------------

--
-- Table structure for table `produit_commande`
--

CREATE TABLE `produit_commande` (
  `model` varchar(150) NOT NULL,
  `id_cmd` int(11) NOT NULL,
  `quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produit_commande`
--

INSERT INTO `produit_commande` (`model`, `id_cmd`, `quantite`) VALUES
('MOD043', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserId` varchar(300) NOT NULL,
  `FirstName` varchar(300) NOT NULL,
  `LastName` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserId`, `FirstName`, `LastName`) VALUES
('test', 'test', 'test'),
('test1', 'test1', 'test1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id_categorie`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`login`);

--
-- Indexes for table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_cmd`);

--
-- Indexes for table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`num_facture`);

--
-- Indexes for table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id_panier`);

--
-- Indexes for table `panier_produit`
--
ALTER TABLE `panier_produit`
  ADD PRIMARY KEY (`id_panier`,`model`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`model`);

--
-- Indexes for table `produit_commande`
--
ALTER TABLE `produit_commande`
  ADD PRIMARY KEY (`id_cmd`,`model`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id_categorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_cmd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `panier`
--
ALTER TABLE `panier`
  MODIFY `id_panier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;