# Host: localhost  (Version 5.5.5-10.4.11-MariaDB)
# Date: 2020-10-22 14:53:52
# Generator: MySQL-Front 6.1  (Build 1.26)


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
  KEY `FKhhhf19galokxtnk5t9qee0kua` (`person_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

#
# Data for table "msisdn"
#

/*!40000 ALTER TABLE `msisdn` DISABLE KEYS */;
INSERT INTO `msisdn` VALUES (1,'998935101400','2020-10-19 09:21:14',1),(2,'998935101402','2020-10-19 09:21:18',1),(3,'998933881400','2020-10-19 09:21:29',2),(4,'998933881414','2020-10-20 06:28:24',2),(5,'998933881717','2020-10-20 06:31:57',2),(8,'998933881720','2020-10-20 13:37:48',2),(9,'998931800001','2020-10-20 13:40:01',3);
/*!40000 ALTER TABLE `msisdn` ENABLE KEYS */;

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
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

#
# Data for table "person"
#

/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'2020-10-19 09:20:38','petvask@mail.com','Vasya','Petrov'),(2,'2020-10-19 09:21:00','vaspet@mail.com','Petya','Vasilev'),(3,'2020-10-20 13:39:35','donjoe@mail.com','Don','Joe'),(4,'2020-10-21 10:03:24','dj@mail.com','Dona','Joeina');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
