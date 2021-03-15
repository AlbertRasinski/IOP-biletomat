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
-- Table structure for table `bilet`
--

DROP TABLE IF EXISTS `bilet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bilet` (
  `idbilet` int NOT NULL AUTO_INCREMENT,
  `idseans` int DEFAULT NULL,
  `rzad` varchar(45) DEFAULT NULL,
  `miejsce` int DEFAULT NULL,
  `oplacone` tinyint DEFAULT NULL,
  `iduzyt` int DEFAULT NULL,
  PRIMARY KEY (`idbilet`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bilet`
--

LOCK TABLES `bilet` WRITE;
/*!40000 ALTER TABLE `bilet` DISABLE KEYS */;
INSERT INTO `bilet` VALUES (1,1,'C',6,1,1),(2,1,'C',7,1,1),(3,1,'C',8,1,1),(4,1,'F',9,1,1),(5,1,'F',8,1,0),(6,1,'F',7,1,0),(7,1,'F',6,1,0),(8,1,'F',5,1,0),(9,1,'G',8,1,0),(10,2,'A',6,1,0),(11,2,'A',7,1,0),(12,2,'A',8,1,0),(13,2,'E',9,1,0),(14,2,'E',8,1,0),(15,2,'F',7,1,0),(16,2,'F',6,1,0),(17,2,'F',5,1,0),(18,2,'G',8,1,0),(19,2,'G',9,1,0),(20,3,'D',1,1,1),(21,3,'D',2,1,1),(22,3,'C',1,1,0),(23,3,'D',2,1,0),(24,3,'E',6,1,1),(25,3,'E',7,1,0),(26,1,'D',7,1,0),(30,1,'E',5,1,0),(31,1,'E',6,1,0),(32,1,'E',7,1,0),(33,2,'E',7,1,0),(34,2,'D',10,1,0),(35,1,'D',6,1,0),(36,2,'G',1,1,0),(37,2,'G',2,1,0),(38,2,'G',3,1,1),(39,2,'G',4,1,1),(40,1,'G',6,1,0);
/*!40000 ALTER TABLE `bilet` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-15 23:29:41
