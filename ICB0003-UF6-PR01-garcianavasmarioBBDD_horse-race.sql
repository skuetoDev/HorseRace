-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.32-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para horse_race
CREATE DATABASE IF NOT EXISTS `horse_race` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `horse_race`;

-- Volcando estructura para tabla horse_race.config
CREATE TABLE IF NOT EXISTS `config` (
  `id` int(11) NOT NULL,
  `game` int(11) DEFAULT NULL,
  `winner` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla horse_race.config: ~1 rows (aproximadamente)
REPLACE INTO `config` (`id`, `game`, `winner`) VALUES
	(1, 1, 0);

-- Volcando estructura para tabla horse_race.logs_game1
CREATE TABLE IF NOT EXISTS `logs_game1` (
  `id_round` int(11) NOT NULL,
  `value_card` varchar(8) DEFAULT NULL,
  `card_suit` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id_round`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla horse_race.logs_game1: ~23 rows (aproximadamente)
REPLACE INTO `logs_game1` (`id_round`, `value_card`, `card_suit`) VALUES
	(1, '7', 'CUPS'),
	(2, '6', 'SWORDS'),
	(3, '8', 'GOLD'),
	(4, '3', 'SWORDS'),
	(5, '9', 'CUPS'),
	(6, '7', 'SWORDS'),
	(7, '7', 'SWORDS'),
	(8, '9', 'SWORDS'),
	(9, '3', 'GOLD'),
	(10, 'JACK', 'CLUBS'),
	(11, '9', 'CLUBS'),
	(12, '3', 'CUPS'),
	(13, '3', 'CLUBS'),
	(14, '5', 'CUPS'),
	(15, '9', 'CUPS'),
	(16, '9', 'SWORDS'),
	(17, '6', 'SWORDS'),
	(18, 'JACK', 'CUPS'),
	(19, '3', 'CLUBS'),
	(20, '9', 'CLUBS'),
	(21, '3', 'CUPS'),
	(22, '9', 'CUPS'),
	(23, '7', 'CLUBS');

-- Volcando estructura para tabla horse_race.players_game1
CREATE TABLE IF NOT EXISTS `players_game1` (
  `id_player` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `bet` int(11) DEFAULT NULL,
  `suit` varchar(8) DEFAULT NULL,
  `layoutX_position` decimal(10,0) DEFAULT 31,
  `isWinner` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id_player`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Volcando datos para la tabla horse_race.players_game1: ~4 rows (aproximadamente)
REPLACE INTO `players_game1` (`id_player`, `name`, `bet`, `suit`, `layoutX_position`, `isWinner`) VALUES
	(1, 'mario', 34, 'GOLD', 231, 0),
	(2, 'Daenerys', 53, 'CUPS', 531, 0),
	(3, 'John', 28, 'SWORDS', 731, 0),
	(4, 'Aria', 89, 'CLUBS', 431, 0);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
