# Host: localhost  (Version 5.5.5-10.4.11-MariaDB)
# Date: 2020-11-12 12:19:31
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "cart"
#

DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state` int(11) NOT NULL,
  `ins_date` datetime NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "cart"
#


#
# Structure for table "catalog"
#

DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "catalog"
#

INSERT INTO `catalog` VALUES (1,'Phone'),(2,'Tablet'),(3,'Напитки');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "person"
#

INSERT INTO `person` VALUES (1,'2020-10-19 09:20:38','petvask@mail.com','Vasya','Petrov'),(2,'2020-10-19 09:21:00','vaspet@mail.com','Petya','Vasilev'),(3,'2020-10-20 13:39:35','donjoe@mail.com','Don','Joe'),(4,'2020-10-21 10:03:24','dj@mail.com','Dona','Joeina');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

#
# Data for table "msisdn"
#

INSERT INTO `msisdn` VALUES (1,'998935101400','2020-10-19 09:21:14',1),(2,'998935101402','2020-10-19 09:21:18',1),(3,'998933881400','2020-10-19 09:21:29',2),(4,'998933881414','2020-10-20 06:28:24',2),(5,'998933881717','2020-10-20 06:31:57',2),(8,'998933881720','2020-10-20 13:37:48',2),(9,'998931800001','2020-10-20 13:40:01',3);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "product"
#

INSERT INTO `product` VALUES (1,'китай фон','Chinaphone 1',1,'2020-11-04 12:47:03',1),(3,'Планшет кампунг','Calaty TAB 1',1,'2020-11-04 12:48:00',2),(4,'Мобила 2000х','Samsung x900',1,'2020-11-12 07:09:19',1),(5,'Мобила 2000х','Samsung x100',1,'2020-11-12 07:09:25',1),(6,'Завод кола','Coca Cola',1,'2020-11-12 07:11:01',3),(7,'Завод кола','Fanta',1,'2020-11-12 07:11:05',3),(8,'Завод кола','Sprite',1,'2020-11-12 07:11:13',3);
