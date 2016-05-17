-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: simpletesting
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Browsers`
--

DROP TABLE IF EXISTS `Browsers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Browsers` (
  `id` int(2) NOT NULL,
  `name` varchar(15) NOT NULL,
  `version` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Browsers`
--

LOCK TABLES `Browsers` WRITE;
/*!40000 ALTER TABLE `Browsers` DISABLE KEYS */;
INSERT INTO `Browsers` VALUES (1,'Firefox','35.0.1'),(2,'Chrome','50.0'),(3,'Internet Explor','10');
/*!40000 ALTER TABLE `Browsers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `InisTests`
--

DROP TABLE IF EXISTS `InisTests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `InisTests` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `suite` bigint(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `browser` int(2) NOT NULL DEFAULT '1',
  `description` varchar(240) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `InisTests_fk0` (`suite`),
  KEY `InisTests_fk1` (`browser`),
  CONSTRAINT `InisTests_fk0` FOREIGN KEY (`suite`) REFERENCES `Suites` (`id`),
  CONSTRAINT `InisTests_fk1` FOREIGN KEY (`browser`) REFERENCES `Browsers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `InisTests`
--

LOCK TABLES `InisTests` WRITE;
/*!40000 ALTER TABLE `InisTests` DISABLE KEYS */;
INSERT INTO `InisTests` VALUES (1,1,'Test 1',1,'This is a description of a Test ....'),(2,2,'End to end user experience test',2,'Validate user can go somewhere');
/*!40000 ALTER TABLE `InisTests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Projects`
--

DROP TABLE IF EXISTS `Projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Projects` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `created_on` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Projects`
--

LOCK TABLES `Projects` WRITE;
/*!40000 ALTER TABLE `Projects` DISABLE KEYS */;
INSERT INTO `Projects` VALUES (1,'SimpleApp','11/05/2016');
/*!40000 ALTER TABLE `Projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Steps`
--

DROP TABLE IF EXISTS `Steps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Steps` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `test` bigint(100) NOT NULL,
  `action` varchar(100) NOT NULL,
  `action_data_1` varchar(100) NOT NULL,
  `action_data_2` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Steps_fk0` (`test`),
  CONSTRAINT `Steps_fk0` FOREIGN KEY (`test`) REFERENCES `InisTests` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Steps`
--

LOCK TABLES `Steps` WRITE;
/*!40000 ALTER TABLE `Steps` DISABLE KEYS */;
INSERT INTO `Steps` VALUES (1,1,'Go to URL','http:expertsoftwaretest.ie','test'),(2,1,'Validate Title','Software Test Experts',''),(3,1,'Click Link','/html/body/footer/div[1]/div/div/div/div/ul/li[1]/a','test'),(4,1,'Validate Title','Inis Software Consulting - ISC Ltd.',''),(5,2,'Go to URL','http:expertsoftwaretest.ie','test'),(6,2,'Click link','/html/body/footer/div[1]/div/div/div/div/ul/li[1]/a',''),(7,2,'Validate Title','Software Test Experts',''),(8,2,'Validate Title','Inis Software Consulting - ISC Ltd.','');
/*!40000 ALTER TABLE `Steps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Suites`
--

DROP TABLE IF EXISTS `Suites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Suites` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project` bigint(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(240) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Suites_fk0` (`project`),
  CONSTRAINT `Suites_fk0` FOREIGN KEY (`project`) REFERENCES `Projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Suites`
--

LOCK TABLES `Suites` WRITE;
/*!40000 ALTER TABLE `Suites` DISABLE KEYS */;
INSERT INTO `Suites` VALUES (1,1,'Suite 1','Initial Suite at DB creation'),(2,1,'Suite 2','SEcond Suite for functional tests');
/*!40000 ALTER TABLE `Suites` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-17 21:40:10
