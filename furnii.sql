-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.31 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for furnii
CREATE DATABASE IF NOT EXISTS `furnii` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `furnii`;

-- Dumping structure for table furnii.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `email` varchar(100) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `vcode` varchar(20) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.admin: ~0 rows (approximately)

-- Dumping structure for table furnii.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `qty` int NOT NULL,
  `product_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `fk_cart_product1_idx` (`product_id`),
  KEY `fk_cart_user1_idx` (`user_id`),
  CONSTRAINT `fk_cart_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_cart_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.cart: ~0 rows (approximately)

-- Dumping structure for table furnii.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.category: ~4 rows (approximately)
INSERT INTO `category` (`id`, `name`) VALUES
	(8, 'Chairs'),
	(9, 'Bed'),
	(10, 'Couch'),
	(11, 'Desk'),
	(12, 'Tables');

-- Dumping structure for table furnii.chat
CREATE TABLE IF NOT EXISTS `chat` (
  `chat_id` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `date_time` datetime NOT NULL,
  `status` tinyint NOT NULL,
  `from` int NOT NULL,
  `to` int NOT NULL,
  PRIMARY KEY (`chat_id`),
  KEY `fk_chat_user2_idx` (`to`),
  KEY `fk_chat_user1_idx` (`from`),
  CONSTRAINT `fk_chat_user1` FOREIGN KEY (`from`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_chat_user2` FOREIGN KEY (`to`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.chat: ~0 rows (approximately)

-- Dumping structure for table furnii.city
CREATE TABLE IF NOT EXISTS `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.city: ~61 rows (approximately)
INSERT INTO `city` (`id`, `name`) VALUES
	(9, 'Colombo'),
	(10, 'Dehiwala-Mount Lavinia'),
	(11, 'Moratuwa'),
	(12, 'Negombo'),
	(13, 'Gampaha'),
	(14, 'Kelaniya'),
	(15, 'Wattala'),
	(16, 'Panadura'),
	(17, 'Ja-Ela'),
	(18, 'Ragama'),
	(19, 'Homagama'),
	(20, 'Kandy'),
	(21, 'Matale'),
	(22, 'Nuwara Eliya'),
	(23, 'Hatton'),
	(24, 'Gampola'),
	(25, 'Nawalapitiya'),
	(26, 'Dambulla'),
	(27, 'Talawakelle'),
	(28, 'Kadugannawa'),
	(29, 'Galle'),
	(30, 'Matara'),
	(31, 'Hambantota'),
	(32, 'Tangalle'),
	(33, 'Weligama'),
	(34, 'Ambalangoda'),
	(35, 'Tissamaharama'),
	(36, 'Beliatta'),
	(37, 'Dikwella'),
	(38, 'Jaffna'),
	(39, 'Kilinochchi'),
	(40, 'Mannar'),
	(41, 'Vavuniya'),
	(42, 'Point Pedro'),
	(43, 'Chavakachcheri'),
	(44, 'Mullaitivu'),
	(45, 'Trincomalee'),
	(46, 'Batticaloa'),
	(47, 'Ampara'),
	(48, 'Kalmunai'),
	(49, 'Eravur'),
	(50, 'Kattankudy'),
	(51, 'Kurunegala'),
	(52, 'Puttalam'),
	(53, 'Chilaw'),
	(54, 'Kuliyapitiya'),
	(55, 'Hettipola'),
	(56, 'Anuradhapura'),
	(57, 'Polonnaruwa'),
	(58, 'Hingurakgoda'),
	(59, 'Mihintale'),
	(60, 'Badulla'),
	(61, 'Bandarawela'),
	(62, 'Monaragala'),
	(63, 'Wellawaya'),
	(64, 'Haputale'),
	(65, 'Mahiyanganaya'),
	(66, 'Ratnapura'),
	(67, 'Balangoda'),
	(68, 'Embilipitiya'),
	(69, 'Kegalle');

-- Dumping structure for table furnii.color
CREATE TABLE IF NOT EXISTS `color` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.color: ~6 rows (approximately)
INSERT INTO `color` (`id`, `name`) VALUES
	(1, 'Red'),
	(2, 'Green'),
	(3, 'Brown'),
	(4, 'White'),
	(5, 'Black'),
	(6, 'Blue'),
	(7, 'Grey');

-- Dumping structure for table furnii.feedback
CREATE TABLE IF NOT EXISTS `feedback` (
  `feed_id` int NOT NULL AUTO_INCREMENT,
  `type` int NOT NULL,
  `date` datetime NOT NULL,
  `feed` text,
  `product_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`feed_id`),
  KEY `fk_feedback_product1_idx` (`product_id`),
  KEY `fk_feedback_user1_idx` (`user_id`),
  CONSTRAINT `fk_feedback_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_feedback_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.feedback: ~0 rows (approximately)

-- Dumping structure for table furnii.order_item
CREATE TABLE IF NOT EXISTS `order_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `qty` int NOT NULL,
  `order_status_id` int NOT NULL,
  `product_order_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_items_product1_idx` (`product_id`),
  KEY `fk_order_item_order_status1_idx` (`order_status_id`),
  KEY `fk_order_item_product_order1_idx` (`product_order_id`),
  CONSTRAINT `fk_invoice_items_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_order_item_order_status1` FOREIGN KEY (`order_status_id`) REFERENCES `order_status` (`id`),
  CONSTRAINT `fk_order_item_product_order1` FOREIGN KEY (`product_order_id`) REFERENCES `product_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.order_item: ~33 rows (approximately)
INSERT INTO `order_item` (`id`, `product_id`, `qty`, `order_status_id`, `product_order_id`) VALUES
	(1, 13, 2, 5, 4),
	(2, 18, 1, 5, 4),
	(3, 17, 1, 5, 5),
	(4, 16, 1, 5, 5),
	(5, 19, 1, 5, 6),
	(6, 19, 1, 5, 7),
	(7, 20, 1, 5, 8),
	(8, 18, 1, 5, 9),
	(9, 18, 1, 5, 10),
	(10, 18, 1, 5, 11),
	(11, 18, 2, 5, 12),
	(12, 20, 1, 5, 13),
	(13, 19, 1, 5, 14),
	(14, 18, 1, 5, 14),
	(15, 20, 1, 5, 14),
	(16, 17, 1, 5, 15),
	(17, 18, 1, 5, 16),
	(18, 20, 1, 5, 17),
	(19, 19, 1, 5, 18),
	(20, 20, 1, 5, 19),
	(21, 18, 1, 5, 20),
	(22, 20, 1, 5, 21),
	(23, 20, 1, 5, 22),
	(24, 20, 1, 5, 23),
	(25, 21, 1, 5, 24),
	(26, 20, 2, 5, 25),
	(27, 20, 1, 5, 26),
	(28, 19, 2, 5, 26),
	(29, 22, 2, 5, 27),
	(30, 21, 1, 5, 27),
	(31, 22, 1, 5, 28),
	(32, 20, 1, 5, 28),
	(33, 21, 1, 5, 29),
	(34, 22, 1, 5, 29),
	(35, 22, 1, 5, 30),
	(36, 21, 1, 5, 30),
	(37, 22, 1, 5, 31),
	(38, 19, 1, 5, 31),
	(39, 20, 1, 5, 32),
	(40, 19, 1, 5, 32);

-- Dumping structure for table furnii.order_status
CREATE TABLE IF NOT EXISTS `order_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.order_status: ~4 rows (approximately)
INSERT INTO `order_status` (`id`, `status`) VALUES
	(1, 'Paid'),
	(2, 'Processing'),
	(3, 'Shipped'),
	(4, 'Delivered'),
	(5, 'Pending');

-- Dumping structure for table furnii.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `qty` int NOT NULL,
  `title` varchar(250) NOT NULL,
  `description` text NOT NULL,
  `datetime_added` datetime NOT NULL,
  `delivery_fee` double NOT NULL,
  `user_id` int NOT NULL,
  `status_id` int NOT NULL,
  `condition_id` int NOT NULL,
  `category_id` int NOT NULL,
  `color_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_user1_idx` (`user_id`),
  KEY `fk_product_status1_idx` (`status_id`),
  KEY `fk_product_condition1_idx` (`condition_id`),
  KEY `fk_product_category1_idx` (`category_id`),
  KEY `fk_product_color1_idx` (`color_id`),
  CONSTRAINT `fk_product_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_product_color1` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`),
  CONSTRAINT `fk_product_condition1` FOREIGN KEY (`condition_id`) REFERENCES `product_condition` (`id`),
  CONSTRAINT `fk_product_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_product_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.product: ~9 rows (approximately)
INSERT INTO `product` (`id`, `price`, `qty`, `title`, `description`, `datetime_added`, `delivery_fee`, `user_id`, `status_id`, `condition_id`, `category_id`, `color_id`) VALUES
	(12, 500, 10, 'Black Chairs for dinning rooms', 'This is for dinning room.', '2024-09-11 22:40:20', 6000, 1, 1, 1, 8, 5),
	(13, 30000, 6, 'Wood Bed Double', 'Double bed brown color.', '2024-09-12 15:17:03', 8000, 1, 1, 1, 9, 3),
	(14, 20000, 8, 'Blue Chairs', 'Blue woody chairs for home.', '2024-09-12 15:24:26', 6000, 1, 1, 1, 8, 6),
	(15, 50000, 10, 'ZINUS Alexis Deluxe Wood Platform Bed', 'ZINUS Alexis Deluxe Wood Platform Bed Frame, Solid Wood Foundation, No Box Spring Needed, Wood Slat Support, Easy Assembly, Rustic Pine, Queen.\r\nMaterial :-\r\nSlats, Wood, Plywood, Steel', '2024-09-12 22:46:48', 6000, 1, 1, 1, 9, 3),
	(16, 60000, 9, 'Black Bed', 'Black bed for home. Queen size.', '2024-09-12 22:47:58', 6000, 1, 1, 1, 9, 5),
	(17, 45000, 8, 'ZINUS Judy Upholstered Platform Bed', 'ZINUS Judy Upholstered Platform Bed Frame, Mattress Foundation, Wood Slat Support, No Box Spring Needed, Easy Assembly, Full, Grey \r\nMaterial :-\r\nFoam, Wood, Plywood, Steel, Fabric', '2024-09-12 22:51:03', 6000, 1, 1, 1, 9, 5),
	(18, 500, 6, 'DHP Dakota Upholstered Platform Bed', 'DHP Dakota Upholstered Platform Bed with Diamond Button Tufted Headboard and Footboard, No Box Spring Needed, Full, White Faux Leather.\r\nMaterial :-\r\nFabric', '2024-09-12 22:53:42', 6000, 1, 1, 1, 9, 4),
	(19, 800, 4, 'ZINUS Joseph Metal Platforma Bed ', 'ZINUS Joseph Metal Platforma Bed Frame, Mattress Foundation, Wood Slat Support, No Box Spring Needed, Sturdy Steel Structure, Queen \r\nMaterial :-\r\nWood, Steel', '2024-09-12 22:56:25', 7000, 1, 1, 1, 9, 7),
	(20, 250, 4, 'COLAMY Modern Wingback Living Room Chair', 'COLAMY Modern Wingback Living Room Chair, Upholstered Fabric Accent Armchair, Single Sofa Chair with Lounge Seat and Wood Legs for Bedroom/Office/Reading Spaces, Beige.\r\nMaterial - \r\nTextile, Rubberwood, Fabric', '2024-09-14 16:47:41', 5000, 3, 1, 1, 8, 4),
	(21, 50000, 6, 'Black bed', 'new item', '2024-09-18 10:46:18', 3000, 4, 1, 1, 9, 5),
	(22, 1000, 1, 'Bed New', 'NEw', '2024-09-18 13:09:18', 2000, 5, 1, 1, 9, 5);

-- Dumping structure for table furnii.product_condition
CREATE TABLE IF NOT EXISTS `product_condition` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.product_condition: ~2 rows (approximately)
INSERT INTO `product_condition` (`id`, `name`) VALUES
	(1, 'New'),
	(2, 'Used');

-- Dumping structure for table furnii.product_order
CREATE TABLE IF NOT EXISTS `product_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_time` datetime NOT NULL,
  `user_id` int NOT NULL,
  `user_address_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_user1_idx` (`user_id`),
  KEY `fk_order_user_address1_idx` (`user_address_id`),
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_order_user_address1` FOREIGN KEY (`user_address_id`) REFERENCES `user_address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.product_order: ~27 rows (approximately)
INSERT INTO `product_order` (`id`, `date_time`, `user_id`, `user_address_id`) VALUES
	(4, '2024-09-13 16:04:56', 1, 4),
	(5, '2024-09-14 19:33:49', 1, 4),
	(6, '2024-09-14 19:35:14', 1, 4),
	(7, '2024-09-14 19:46:28', 1, 4),
	(8, '2024-09-14 19:49:07', 1, 4),
	(9, '2024-09-14 20:06:49', 1, 4),
	(10, '2024-09-14 20:16:56', 1, 4),
	(11, '2024-09-14 20:34:07', 3, 5),
	(12, '2024-09-16 12:13:15', 1, 4),
	(13, '2024-09-16 12:59:11', 1, 4),
	(14, '2024-09-16 13:07:00', 1, 4),
	(15, '2024-09-16 13:18:38', 1, 4),
	(16, '2024-09-17 09:59:33', 1, 5),
	(17, '2024-09-17 10:02:24', 1, 5),
	(18, '2024-09-17 10:06:37', 1, 5),
	(19, '2024-09-17 10:10:11', 1, 5),
	(20, '2024-09-17 10:11:50', 1, 5),
	(21, '2024-09-17 10:18:01', 1, 5),
	(22, '2024-09-17 10:19:01', 1, 5),
	(23, '2024-09-17 10:34:13', 1, 5),
	(24, '2024-09-18 10:50:06', 4, 6),
	(25, '2024-09-18 11:02:12', 1, 5),
	(26, '2024-09-18 12:11:54', 3, 7),
	(27, '2024-09-18 13:14:19', 5, 8),
	(28, '2024-09-18 13:15:21', 1, 5),
	(29, '2024-11-19 22:05:47', 1, 5),
	(30, '2024-12-01 11:10:18', 1, 5),
	(31, '2024-12-01 11:12:59', 1, 5),
	(32, '2024-12-01 11:19:42', 1, 5);

-- Dumping structure for table furnii.recent
CREATE TABLE IF NOT EXISTS `recent` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_recent_product1_idx` (`product_id`),
  KEY `fk_recent_user1_idx` (`user_id`),
  CONSTRAINT `fk_recent_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_recent_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.recent: ~0 rows (approximately)

-- Dumping structure for table furnii.status
CREATE TABLE IF NOT EXISTS `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.status: ~2 rows (approximately)
INSERT INTO `status` (`id`, `name`) VALUES
	(1, 'Active'),
	(2, 'Inactive');

-- Dumping structure for table furnii.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `password` varchar(20) NOT NULL,
  `joined_date` datetime NOT NULL,
  `verification_code` varchar(20) DEFAULT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_status1_idx` (`status_id`),
  CONSTRAINT `fk_user_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.user: ~4 rows (approximately)
INSERT INTO `user` (`id`, `email`, `fname`, `lname`, `password`, `joined_date`, `verification_code`, `status_id`) VALUES
	(1, 'sandeepalakruwan@gmail.com', 'Sahan', 'Perera', 'Slbh2001@', '2024-09-11 09:59:12', '751022', 1),
	(3, 'sandeepaherath2001@gmail.com', 'Nadun', 'Bandara', 'Slbh2001@', '2024-09-14 15:41:51', '456345', 1),
	(4, 'subhashanieherath@gmail.com', 'Rajitha', 'Herath', 'Slbh2001@', '2024-09-18 10:41:12', '581375', 1),
	(5, 'worasolutionslk@gmail.com', 'Sandeepa', 'Herath', 'Slbh2001@@', '2024-09-18 13:07:19', '602838', 1);

-- Dumping structure for table furnii.user_address
CREATE TABLE IF NOT EXISTS `user_address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `line1` text NOT NULL,
  `line2` text NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `city_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_has_address_user1_idx` (`user_id`),
  KEY `fk_user_has_address_city1_idx` (`city_id`),
  CONSTRAINT `fk_user_has_address_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `fk_user_has_address_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.user_address: ~5 rows (approximately)
INSERT INTO `user_address` (`id`, `fname`, `lname`, `mobile`, `line1`, `line2`, `postal_code`, `city_id`, `user_id`) VALUES
	(4, 'Sahan', 'Perera', '0771231234', 'Hawelock Road', 'Colombo 5', '00500', 9, 1),
	(5, 'Nadun', 'Bandara', '0701112223', 'No.25, Main Street,', 'Rajagiriya', '00500', 9, 1),
	(6, 'Rajitha', 'Herath', '0701112223', 'No.23, Main Street ,', 'Kurunegala', '60200', 51, 4),
	(7, 'Nadun', 'Bandara', '0771234567', 'Main Road', 'Katugasthota', '80300', 56, 3),
	(8, 'Sandeepa', 'Herath', '0771234567', 'No.2', 'Kurunegala', '60200', 51, 5);

-- Dumping structure for table furnii.watchlist
CREATE TABLE IF NOT EXISTS `watchlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_watchlist_product1_idx` (`product_id`),
  KEY `fk_watchlist_user1_idx` (`user_id`),
  CONSTRAINT `fk_watchlist_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_watchlist_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table furnii.watchlist: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
