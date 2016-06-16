CREATE DATABASE  IF NOT EXISTS `rooming_db` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `rooming_db`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: rooming_db
-- ------------------------------------------------------
-- Server version	5.0.51b-community-nt

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
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `amenities`
--

DROP TABLE IF EXISTS `amenities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amenities` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) collate utf8_bin NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amenities`
--

LOCK TABLES `amenities` WRITE;
/*!40000 ALTER TABLE `amenities` DISABLE KEYS */;
/*!40000 ALTER TABLE `amenities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) collate utf8_bin NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facility`
--

DROP TABLE IF EXISTS `facility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facility` (
  `id` int(11) NOT NULL auto_increment,
  `lon` double NOT NULL,
  `lan` double NOT NULL,
  `country` varchar(255) collate utf8_bin NOT NULL,
  `city` varchar(255) collate utf8_bin NOT NULL,
  `street` varchar(255) collate utf8_bin NOT NULL,
  `building_number` int(11) NOT NULL,
  `postal_code` int(11) NOT NULL,
  `description` varchar(255) collate utf8_bin NOT NULL,
  `room_advertisor_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_facility__apartment_advertisor` (`room_advertisor_id`),
  CONSTRAINT `fk_facility_room_advertiser` FOREIGN KEY (`room_advertisor_id`) REFERENCES `room_advertisers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility`
--

LOCK TABLES `facility` WRITE;
/*!40000 ALTER TABLE `facility` DISABLE KEYS */;
/*!40000 ALTER TABLE `facility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facility_amenities`
--

DROP TABLE IF EXISTS `facility_amenities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facility_amenities` (
  `id` int(11) NOT NULL auto_increment,
  `facility_id` int(11) default NULL,
  `amenities_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_facility_amenities__amenities` (`amenities_id`),
  KEY `idx_facility_amenities__facility` (`facility_id`),
  CONSTRAINT `fk_facility_amenities__facility` FOREIGN KEY (`facility_id`) REFERENCES `facility` (`id`),
  CONSTRAINT `fk_facility_amenities__amenities` FOREIGN KEY (`amenities_id`) REFERENCES `amenities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility_amenities`
--

LOCK TABLES `facility_amenities` WRITE;
/*!40000 ALTER TABLE `facility_amenities` DISABLE KEYS */;
/*!40000 ALTER TABLE `facility_amenities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facility_images`
--

DROP TABLE IF EXISTS `facility_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facility_images` (
  `id` int(11) NOT NULL auto_increment,
  `facility_id` int(11) NOT NULL,
  `image` varchar(255) collate utf8_bin NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_facility_images__facility` (`facility_id`),
  CONSTRAINT `fk_facility_images__facility` FOREIGN KEY (`facility_id`) REFERENCES `facility` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility_images`
--

LOCK TABLES `facility_images` WRITE;
/*!40000 ALTER TABLE `facility_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `facility_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lookup`
--

DROP TABLE IF EXISTS `lookup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lookup` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) collate utf8_bin NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_lookup__category` (`category_id`),
  CONSTRAINT `fk_lookup__category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lookup`
--

LOCK TABLES `lookup` WRITE;
/*!40000 ALTER TABLE `lookup` DISABLE KEYS */;
/*!40000 ALTER TABLE `lookup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` int(11) NOT NULL auto_increment,
  `area` varchar(255) collate utf8_bin NOT NULL,
  `price` decimal(12,2) NOT NULL,
  `advertise_date` date NOT NULL,
  `duration` int(11) NOT NULL,
  `expandable` tinyint(1) NOT NULL,
  `facility_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_room__facility` (`facility_id`),
  CONSTRAINT `fk_room__facility` FOREIGN KEY (`facility_id`) REFERENCES `facility` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_advertisers`
--

DROP TABLE IF EXISTS `room_advertisers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_advertisers` (
  `id` int(11) NOT NULL auto_increment,
  `first_name` varchar(255) collate utf8_bin NOT NULL,
  `last_name` varchar(255) collate utf8_bin NOT NULL,
  `birthday` date default NULL,
  `country` varchar(255) collate utf8_bin default NULL,
  `phone_number` int(11) default NULL,
  `city` varchar(255) collate utf8_bin default NULL,
  `building_number` int(11) default NULL,
  `street` varchar(255) collate utf8_bin default NULL,
  `floor_number` int(11) default NULL,
  `profile_image` varchar(255) collate utf8_bin default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_advertisers`
--

LOCK TABLES `room_advertisers` WRITE;
/*!40000 ALTER TABLE `room_advertisers` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_advertisers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_images`
--

DROP TABLE IF EXISTS `room_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_images` (
  `id` int(11) NOT NULL auto_increment,
  `room_id` int(11) NOT NULL,
  `image` varchar(255) collate utf8_bin NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_room_images__room` (`room_id`),
  CONSTRAINT `fk_room_images__room` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_images`
--

LOCK TABLES `room_images` WRITE;
/*!40000 ALTER TABLE `room_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_intersts`
--

DROP TABLE IF EXISTS `room_intersts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_intersts` (
  `id` int(11) NOT NULL auto_increment,
  `room_id` int(11) NOT NULL,
  `interest_lookup_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_room_intersts__lookup` (`interest_lookup_id`),
  KEY `idx_room_intersts__room` (`room_id`),
  CONSTRAINT `fk_room_intersts__lookup` FOREIGN KEY (`interest_lookup_id`) REFERENCES `lookup` (`id`),
  CONSTRAINT `fk_room_intersts__room` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_intersts`
--

LOCK TABLES `room_intersts` WRITE;
/*!40000 ALTER TABLE `room_intersts` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_intersts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_roles`
--

DROP TABLE IF EXISTS `room_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_roles` (
  `id` int(11) NOT NULL auto_increment,
  `room_id` int(11) NOT NULL,
  `role_lookup_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_room_roles__lookup` (`role_lookup_id`),
  KEY `idx_room_roles__room` (`room_id`),
  CONSTRAINT `fk_room_roles__lookup` FOREIGN KEY (`role_lookup_id`) REFERENCES `lookup` (`id`),
  CONSTRAINT `fk_room_roles__room` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_roles`
--

LOCK TABLES `room_roles` WRITE;
/*!40000 ALTER TABLE `room_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_seekers`
--

DROP TABLE IF EXISTS `room_seekers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_seekers` (
  `id` int(11) NOT NULL auto_increment,
  `email` varchar(255) collate utf8_bin default NULL,
  `password` varchar(255) collate utf8_bin default NULL,
  `facebook_token` varchar(255) collate utf8_bin default NULL,
  `country` varchar(255) collate utf8_bin default NULL,
  `city` varchar(255) collate utf8_bin default NULL,
  `address` varchar(255) collate utf8_bin default NULL,
  `phone_number` int(11) default NULL,
  `mobile_number` int(11) default NULL,
  `birthdate` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_seekers`
--

LOCK TABLES `room_seekers` WRITE;
/*!40000 ALTER TABLE `room_seekers` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_seekers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `searcher_interests`
--

DROP TABLE IF EXISTS `searcher_interests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searcher_interests` (
  `id` int(11) NOT NULL auto_increment,
  `lookup_id` int(11) default NULL,
  `apartment_searcher_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_searcher_interests__apartment_searcher` (`apartment_searcher_id`),
  KEY `idx_searcher_interests__lookup` (`lookup_id`),
  CONSTRAINT `fk_searcher_interests__lookup` FOREIGN KEY (`lookup_id`) REFERENCES `lookup` (`id`),
  CONSTRAINT `fk_searcher_interests__apartment_searcher` FOREIGN KEY (`apartment_searcher_id`) REFERENCES `room_seekers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searcher_interests`
--

LOCK TABLES `searcher_interests` WRITE;
/*!40000 ALTER TABLE `searcher_interests` DISABLE KEYS */;
/*!40000 ALTER TABLE `searcher_interests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_rooms`
--

DROP TABLE IF EXISTS `users_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_rooms` (
  `id` int(11) NOT NULL auto_increment,
  `apartment_searcher_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_users_rooms__apartment_searcher` (`apartment_searcher_id`),
  KEY `idx_users_rooms__room` (`room_id`),
  CONSTRAINT `fk_users_rooms__room` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
  CONSTRAINT `fk_users_rooms__apartment_searcher` FOREIGN KEY (`apartment_searcher_id`) REFERENCES `room_seekers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_rooms`
--

LOCK TABLES `users_rooms` WRITE;
/*!40000 ALTER TABLE `users_rooms` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_rooms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-01 20:40:35
