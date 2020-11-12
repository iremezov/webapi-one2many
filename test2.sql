# Host: localhost  (Version 5.5.5-10.4.11-MariaDB)
# Date: 2020-11-12 15:16:11
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "catalog"
#

DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "catalog"
#

INSERT INTO `catalog` VALUES (1,'Еда'),(3,'Напитки');

#
# Structure for table "person"
#

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `email_address` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "person"
#

INSERT INTO `person` VALUES (1,'2020-11-12 09:30:57','dj@mail.com','Don111a','Joeina'),(2,'2020-11-12 09:35:05','don.joe@mail.com','Dona','Joe');

#
# Structure for table "msisdn"
#

DROP TABLE IF EXISTS `msisdn`;
CREATE TABLE `msisdn` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msisdn` varchar(255) NOT NULL,
  `ins_date` datetime NOT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_amxg8u82vtb4p66ji776qarge` (`msisdn`),
  KEY `FKhhhf19galokxtnk5t9qee0kua` (`person_id`),
  CONSTRAINT `FKhhhf19galokxtnk5t9qee0kua` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "msisdn"
#

INSERT INTO `msisdn` VALUES (1,'998931800001','2020-11-12 09:35:17',2);

#
# Structure for table "cart"
#

DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state` int(11) NOT NULL,
  `ins_date` datetime NOT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo3jsgdxo6ax3cwml53qyems2j` (`person_id`),
  CONSTRAINT `FKo3jsgdxo6ax3cwml53qyems2j` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "cart"
#

INSERT INTO `cart` VALUES (1,1,'2020-11-12 09:30:37',NULL),(2,1,'2020-11-12 09:31:48',1),(3,1,'2020-11-12 09:32:05',1),(4,1,'2020-11-12 09:36:44',2),(5,1,'2020-11-12 09:36:50',2);

#
# Structure for table "product"
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `state` int(11) NOT NULL,
  `ins_date` datetime NOT NULL,
  `catalog_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8oaejqr5kmuivpskpr3fxcg1m` (`catalog_id`),
  CONSTRAINT `FK8oaejqr5kmuivpskpr3fxcg1m` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "product"
#

INSERT INTO `product` VALUES (1,'test 123','test',1,'2020-11-12 09:29:03',1),(2,'test 123','testawdada',1,'2020-11-12 09:29:11',1),(3,'Ташкент кола','Кола',1,'2020-11-12 09:36:07',3),(4,'Ташкент кола','Фанта',1,'2020-11-12 09:36:12',3),(5,'Ташкент кола','Спрайт',1,'2020-11-12 09:36:16',3);

#
# Structure for table "cart_product"
#

DROP TABLE IF EXISTS `cart_product`;
CREATE TABLE `cart_product` (
  `cart_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  KEY `FK2kdlr8hs2bwl14u8oop49vrxi` (`product_id`),
  KEY `FKlv5x4iresnv4xspvomrwd8ej9` (`cart_id`),
  CONSTRAINT `FK2kdlr8hs2bwl14u8oop49vrxi` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKlv5x4iresnv4xspvomrwd8ej9` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "cart_product"
#

INSERT INTO `cart_product` VALUES (1,1),(2,1),(3,2),(4,3),(5,2);
