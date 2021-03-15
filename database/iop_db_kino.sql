-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: iop_db
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `kino`
--

DROP TABLE IF EXISTS `kino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kino` (
  `idkino` int NOT NULL AUTO_INCREMENT,
  `idSieciKina` int DEFAULT NULL,
  `Miasto` varchar(45) DEFAULT NULL,
  `Adres` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idkino`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kino`
--

LOCK TABLES `kino` WRITE;
/*!40000 ALTER TABLE `kino` DISABLE KEYS */;
INSERT INTO `kino` VALUES (1,6,'Warszawa','wesoła 5'),(2,1,'Kraków','11-go Listopada 434'),(3,2,'Białystok','3-go maja 54'),(4,3,'Poznań','konstytucji 67'),(5,4,'Kraków','dluga 1'),(6,5,'Szczecin','krotka 23'),(7,6,'Kraków','słoneczna 55'),(8,1,'Katowice','strazacka 65'),(9,2,'Łódź ','ełcka 64 '),(10,3,'Wrocław','warszawska 120'),(11,4,'Wrocław','szkolna 5'),(12,5,'Gdańsk','cmentarna 14 '),(13,6,'Warszawa','szpitalna 15'),(14,1,'Warszawa','zamiany 22'),(15,2,'Gdańsk','kwitnaca 844'),(16,3,'Gdynia','akacjowa 254 '),(17,4,'Sopot','spadkowa 29'),(18,6,'Poznań','pszczólki mai 78 '),(19,1,'Toruń','szafarzy 100'),(20,2,'Wrocław','sztuk pieknych 15'),(21,3,'Łódź ','WOŚP 21'),(22,4,'Wrocław','Piruet 68 '),(23,5,'Katowice','rogowska 6');
/*!40000 ALTER TABLE `kino` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-15 23:29:40
