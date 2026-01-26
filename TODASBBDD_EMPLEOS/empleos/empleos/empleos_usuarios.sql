CREATE DATABASE  IF NOT EXISTS `empleos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `empleos`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: empleos
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `estatus` int NOT NULL DEFAULT '1',
  `fechaRegistro` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (2,'Luis Esparza Gomez','luis@itinajero.net','luis','$2a$10$030/vtqQxOZvB1cAbP/q0ePvVZqNWJgCliu5qdtLB//refPYdsYFe',1,'2019-06-10'),(3,'Marisol Salinas Rodarte','marisol@itinajero.net','marisol','$2a$10$SGZWfNSLPqyFY61vKrgYHuyqdcPfGfXB3fmWUApAbyKTOukj66ndG',1,'2019-06-10'),(5,'Daniel Lopez','daniel@gmail.com','dlopez','$2a$10$toryw/qd.E4KK0UMABE2RuHCxgAd70EUKaAwifSTGTGTeSIo/Lzie',1,'2019-06-14'),(6,'Miguel Marquez Hernandez','miguel@itinajero.net','miguel','$2a$10$9.wEEzaj8F55wovbrINuh.iwBQX3rRHSuRwafmMC9Bmw/K1bWeJqy',1,'2019-06-19'),(7,'maria','maria@gmail.com','maria','$2a$10$ZaV2KhwWrrQ3lOKw24sqQuiabccgf7W60ccixxSTj7iWo0atOYW0y',1,'2024-05-09'),(8,'alberto','alberto@gmail.com','alberto','$2a$10$ip9KUlFC7QHk8/tFNxhDx.ZMIKLgNKYNN1.hd0bgOnsfIM0r7/9Sa',1,'2024-05-14'),(9,'jose manuel rayo ortiguela','rayoortiguela@gmail.com','jmrayo','$2a$10$uSIhE6HYRGn.YmwEVQ5y.Oi34MiyrLRowmhWwAz3SEN9X1CBvvHGe',1,'2026-01-18'),(10,'Rayosoft','aaa@aaa.com','Rayosoft','rayo123',1,'2026-01-22');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-23  8:35:48
