-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 03 juin 2024 à 01:16
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gradebook`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  `last_name` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`, `name`, `last_name`) VALUES
(1, 'admin', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', 'taha ', 'taabani'),
(26, 'zaki', 'f557e0d3cabb6d8ca3368a7c00f826f6b89601bff28b443fade2bdc79810bfb7', 'zaki', 'zaki');

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE `classe` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `filliere` varchar(50) NOT NULL,
  `grade` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`id`, `name`, `filliere`, `grade`) VALUES
(2, 'LEI', 'Informatique', '2eme annee'),
(3, 'LEI', 'Physique', '3eme annee'),
(4, 'Master', 'Math applique', 'M1'),
(5, 'Master', 'MQL', 'M2'),
(6, 'PhD', 'Algebre', 'D1'),
(7, 'PhD', 'cyber security', 'D2'),
(55, 'info', 'informatique', 's6'),
(56, 'null', 'lmr9a', 'null');

-- --------------------------------------------------------

--
-- Structure de la table `classe_module`
--

CREATE TABLE `classe_module` (
  `classe_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `classe_module`
--

INSERT INTO `classe_module` (`classe_id`, `module_id`) VALUES
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 8),
(4, 2),
(5, 4),
(6, 5),
(7, 6),
(55, 1),
(55, 2);

-- --------------------------------------------------------

--
-- Structure de la table `class_student`
--

CREATE TABLE `class_student` (
  `class_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `class_student`
--

INSERT INTO `class_student` (`class_id`, `student_id`) VALUES
(2, 5),
(2, 7),
(3, 10),
(3, 13),
(4, 8),
(4, 11),
(5, 1),
(5, 2),
(5, 3),
(5, 24),
(6, 14),
(7, 9),
(7, 12);

-- --------------------------------------------------------

--
-- Structure de la table `emploisclasses`
--

CREATE TABLE `emploisclasses` (
  `classe_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL,
  `day_of_week` varchar(50) NOT NULL,
  `start_time` int(11) NOT NULL,
  `end_time` int(11) NOT NULL,
  `salle_id` int(11) NOT NULL,
  `prof_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `name` varchar(70) NOT NULL,
  `nbr_heures` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `module`
--

INSERT INTO `module` (`id`, `name`, `nbr_heures`) VALUES
(1, 'POO', 4),
(2, 'DEV WEB', 4),
(3, 'OS Linux', 4),
(4, 'Algorithme', 0),
(5, 'Analyse 1', 0),
(6, 'Algebre 1', 0),
(7, 'Analyse 2', 0),
(8, 'Mecanique', 0);

-- --------------------------------------------------------

--
-- Structure de la table `prof`
--

CREATE TABLE `prof` (
  `id` int(11) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `name` varchar(70) NOT NULL,
  `last_name` varchar(70) NOT NULL,
  `address` varchar(70) NOT NULL,
  `sex` varchar(70) NOT NULL,
  `age` int(11) NOT NULL,
  `cne_prof` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `prof`
--

INSERT INTO `prof` (`id`, `username`, `password`, `name`, `last_name`, `address`, `sex`, `age`, `cne_prof`) VALUES
(28, '477e6e09f183ed2f55c0a491beb78a8f2cfcaf1cbb78de753c4a93008fd5d3c8', '616cfbbd28b49666655899a092416e90d106df3727a292ab870428a5b4ead28c', 'mouad', 'tantaoui', 'qenitra', 'Homme', 40, 'p2323'),
(29, '6c6dab33994f8b98bb9db20ef4ed62d66b5f267cbcfc575b582e317fe2d58c62', '6c6dab33994f8b98bb9db20ef4ed62d66b5f267cbcfc575b582e317fe2d58c62', 'med', 'med', 'med', 'Homme', 55, '56vnvn'),
(30, '1b16b1df538ba12dc3f97edbb85caa7050d46c148134290feba80f8236c83db9', '1b16b1df538ba12dc3f97edbb85caa7050d46c148134290feba80f8236c83db9', 'n', 'n', 'n', 'Femme', 22, '23ghg');

-- --------------------------------------------------------

--
-- Structure de la table `prof_module`
--

CREATE TABLE `prof_module` (
  `professor_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `salles`
--

CREATE TABLE `salles` (
  `id_salle` int(11) NOT NULL,
  `nom_salle` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `salles`
--

INSERT INTO `salles` (`id_salle`, `nom_salle`) VALUES
(1, 'A1'),
(2, 'A2'),
(3, 'A3'),
(4, 'A4'),
(5, 'A5'),
(6, 'B1'),
(7, 'B2'),
(8, 'B3'),
(9, 'B4'),
(10, 'B5'),
(11, 'C1'),
(12, 'C2'),
(13, 'C3'),
(14, 'C4'),
(15, 'C5'),
(16, 'D1'),
(17, 'D2'),
(18, 'D3'),
(19, 'D4'),
(20, 'D5');

-- --------------------------------------------------------

--
-- Structure de la table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `name` varchar(70) NOT NULL,
  `last_name` varchar(70) NOT NULL,
  `address` varchar(70) NOT NULL,
  `sex` varchar(70) NOT NULL,
  `age` int(11) NOT NULL,
  `cne_student` varchar(70) NOT NULL,
  `note_finale` float DEFAULT NULL,
  `abscence_hours` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `student`
--

INSERT INTO `student` (`id`, `username`, `password`, `name`, `last_name`, `address`, `sex`, `age`, `cne_student`, `note_finale`, `abscence_hours`) VALUES
(1, 'taha', 'taha', 'Taha Taabani', 'taabani', 'MEKNES - MARJANE 2', 'Homme', 21, 'M12345', 18, 4),
(2, 'zakaria', 'zakaria', 'Zakaria', 'El Omari', 'MEKNES - TOULAL', 'Homme', 20, 'M12435', 19, 3),
(3, 'mohammed', 'mohammed', 'Charlie', 'Doe', 'MEKENS - OUISLANE', 'Homme', 20, 'M15432', 19, 4),
(4, 'amine', 'amine', 'Amine', 'El aach', 'Rachidia', 'Homme', 21, 'M32415', 15, 5),
(5, 'aouni', 'aouni', 'ayoub', 'aouni', 'MEKNES - SIDI BOUZKRI', 'Homme', 20, 'M54321', 14, 1),
(6, 'manare', 'manare', 'Manare', 'Ramli', 'MEKNES - TOULAL', 'Femme', 20, 'M32451', 14, 6),
(7, 'nouhayla', 'nouhayla', 'Nouhayla', 'Ajidad', 'IFRANE', 'Femme', 21, 'R56789', 14.75, 1),
(8, 'marouane', 'marouane', 'Marouane', 'Ait Benchikh', 'OUJDA', 'Homme', 20, 'K342523', 16, 2),
(9, 'mehdi', 'mehdi', 'Mehdi', 'Ben Mekki', 'MRIRT', 'Homme', 20, 'C123145', 14, 2),
(10, 'khadija', 'khadija', 'Khadija', 'Abioui', 'MEKNES - EL HAJEB', 'Femme', 21, 'V098763', 15, 3),
(11, 'ibrahim', 'ibrahim', 'Ibrahim', 'Belkass', 'MEKNES - SBAA AYOUNE', 'Homme', 22, 'A353443', 16, 6),
(12, 'lahmar', 'lahmar', 'Zakaria', 'Lahmar', 'HED KOURT', 'Homme', 21, 'G343465', 14, 7),
(13, 'boubkri', 'boubkri', 'Mohammed', 'Boubkri', 'MEKNES', 'Homme', 21, 'D32432', 15, 8),
(14, 'meryeme', 'meryeme', 'Meryeme', 'Chaouchi', 'FES', 'Femme', 21, 'F4352435', 14, 1),
(24, '140f4b62d94c3a8ef01bf6f6214be4f38acdfeba9fd05d3934dce70b6230345a', '296986496b3dbdb365de1b66bba0118cb101f6c92d61c309442228a6d7ec4155', 'taabani', 'taha yassine', 'marjane', 'Homme', 21, 'D595236', NULL, 0),
(25, 'zaki', 'f557e0d3cabb6d8ca3368a7c00f826f6b89601bff28b443fade2bdc79810bfb7', 'zaki', 'zaki', 'ggghhhhhhh', 'Homme', 58, 'hhhhhhh', NULL, 2);

-- --------------------------------------------------------

--
-- Structure de la table `student_module`
--

CREATE TABLE `student_module` (
  `student_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL,
  `note_module` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `student_module`
--

INSERT INTO `student_module` (`student_id`, `module_id`, `note_module`) VALUES
(1, 4, 18),
(2, 4, 19),
(3, 4, 19),
(4, 4, 15),
(4, 5, 14),
(4, 6, 15),
(4, 7, 16),
(5, 1, 16),
(5, 2, 12),
(5, 3, 11),
(5, 4, 17),
(6, 4, 12),
(6, 5, 15),
(6, 6, 19),
(6, 7, 10),
(7, 1, 17),
(7, 2, 12),
(7, 3, 14),
(7, 4, 16),
(8, 2, 16),
(9, 6, 14),
(10, 5, 14),
(10, 8, 16),
(11, 2, 16),
(12, 6, 14),
(13, 5, 15),
(13, 8, 15),
(14, 5, 14);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `classe_module`
--
ALTER TABLE `classe_module`
  ADD PRIMARY KEY (`classe_id`,`module_id`),
  ADD KEY `classe_module_ibfk_2` (`module_id`);

--
-- Index pour la table `class_student`
--
ALTER TABLE `class_student`
  ADD PRIMARY KEY (`class_id`,`student_id`),
  ADD KEY `class_student_ibfk_2` (`student_id`);

--
-- Index pour la table `emploisclasses`
--
ALTER TABLE `emploisclasses`
  ADD KEY `classe_id` (`classe_id`),
  ADD KEY `module_id` (`module_id`),
  ADD KEY `prof_id` (`prof_id`),
  ADD KEY `salle_id` (`salle_id`);

--
-- Index pour la table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `prof`
--
ALTER TABLE `prof`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `prof_module`
--
ALTER TABLE `prof_module`
  ADD PRIMARY KEY (`professor_id`,`module_id`),
  ADD KEY `prof_module_ibfk_2` (`module_id`);

--
-- Index pour la table `salles`
--
ALTER TABLE `salles`
  ADD PRIMARY KEY (`id_salle`);

--
-- Index pour la table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `student_module`
--
ALTER TABLE `student_module`
  ADD PRIMARY KEY (`student_id`,`module_id`),
  ADD KEY `student_module_ibfk_2` (`module_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT pour la table `classe`
--
ALTER TABLE `classe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT pour la table `module`
--
ALTER TABLE `module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT pour la table `prof`
--
ALTER TABLE `prof`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT pour la table `salles`
--
ALTER TABLE `salles`
  MODIFY `id_salle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `classe_module`
--
ALTER TABLE `classe_module`
  ADD CONSTRAINT `classe_module_ibfk_1` FOREIGN KEY (`classe_id`) REFERENCES `classe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `classe_module_ibfk_2` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `class_student`
--
ALTER TABLE `class_student`
  ADD CONSTRAINT `class_student_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `classe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `class_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `emploisclasses`
--
ALTER TABLE `emploisclasses`
  ADD CONSTRAINT `classe_id` FOREIGN KEY (`classe_id`) REFERENCES `classe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `module_id` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prof_id` FOREIGN KEY (`prof_id`) REFERENCES `prof` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `salle_id` FOREIGN KEY (`salle_id`) REFERENCES `salles` (`id_salle`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `prof_module`
--
ALTER TABLE `prof_module`
  ADD CONSTRAINT `prof_module_ibfk_1` FOREIGN KEY (`professor_id`) REFERENCES `prof` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prof_module_ibfk_2` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `student_module`
--
ALTER TABLE `student_module`
  ADD CONSTRAINT `student_module_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_module_ibfk_2` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
