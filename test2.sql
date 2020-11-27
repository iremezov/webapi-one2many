# Host: localhost  (Version 5.5.5-10.4.11-MariaDB)
# Date: 2020-11-24 14:55:15
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "catalog"
#

DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "catalog"
#

INSERT INTO `catalog` VALUES (1,'Fast food'),(2,'Первые блюда'),(3,'Вторые блюда'),(4,'Напитки');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "person"
#

INSERT INTO `person` VALUES (1,'2020-11-24 07:55:15','ir@mail.com','Ivan','Remezov'),(2,'2020-11-24 07:56:14','joe.dom@mail.com','Joe','Don'),(3,'2020-11-24 09:46:16','jd2@mail.com','Joeana','Doana');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "msisdn"
#

INSERT INTO `msisdn` VALUES (1,'998935101400','2020-11-24 07:55:32',1),(2,'998933906091','2020-11-24 07:55:39',1),(3,'998931811400','2020-11-24 07:56:36',2);

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

INSERT INTO `cart` VALUES (1,1,'2020-11-24 09:44:11',1),(2,1,'2020-11-24 09:44:44',2);

#
# Structure for table "product"
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
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

INSERT INTO `product` VALUES (1,'','Хот дог',8000,1,'2020-11-24 07:59:35',1),(2,'гамбургер с катлетой','Гамбургер',14000,1,'2020-11-24 07:59:58',1),(3,'милий таомлар','Сомса с картошкой',3000,1,'2020-11-24 08:01:09',2),(4,'милий таомлар','Сомса с мясом',4500,1,'2020-11-24 08:01:20',2),(5,'милий таомлар','Нарын',17000,1,'2020-11-24 08:02:00',2),(6,'милий таомлар','Плов',15000,1,'2020-11-24 08:02:12',3),(7,'','Кока кола',3000,1,'2020-11-24 08:04:22',4),(8,'','Фанта',3000,1,'2020-11-24 08:04:34',4),(9,'','Спрайт',3000,1,'2020-11-24 08:04:41',4),(10,'','Чай',2000,1,'2020-11-24 08:05:50',4);

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

INSERT INTO `cart_product` VALUES (1,7),(1,2),(1,2),(2,10),(2,6),(2,4),(2,4);
