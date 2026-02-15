-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: laliga
-- ------------------------------------------------------
-- Server version	8.0.40

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Luis Esparza Gomez','luis@itinajero.net','luis','$2a$10$mKhH.yuSfcqv9PJZoFDwgOsYl0AE4vRhNB09Yf8W.EWEnCTPcXWxa',1,NULL),(2,'Marisol Salinas Rodarte','marisol@itinajero.net','marisol','$2a$10$d7RRN50rVCUlhSE0D.j/cOjCMae/B8aW/FrwUnX7SuilRcs7alOpW',1,NULL),(3,'Beatriz Ruelas Acosta','beatriz@example.com','beatriz','$2a$10$XFZ5yGIUXWJ.am2TRxOVveZunwkO/7SPT75nQjYsjOAJ4YW/8VmrO',1,'2023-02-19'),(4,'Antonio Muro Salinas','antonio@example.com','antonio','$2a$10$XFZ5yGIUXWJ.am2TRxOVveZunwkO/7SPT75nQjYsjOAJ4YW/8VmrO',1,'2023-02-20'),(5,'Daniel Martinez Garcia','daniel@example.com','daniel','$2a$10$XFZ5yGIUXWJ.am2TRxOVveZunwkO/7SPT75nQjYsjOAJ4YW/8VmrO',1,'2023-02-20'),(6,'Marisol Hernandez Rodriguez','marisol@example.com','marhernandez','$2a$10$XFZ5yGIUXWJ.am2TRxOVveZunwkO/7SPT75nQjYsjOAJ4YW/8VmrO',1,'2023-02-21'),(7,'Mary Cruz Ibarra Dante','mary@example.com','mary','$2a$10$XFZ5yGIUXWJ.am2TRxOVveZunwkO/7SPT75nQjYsjOAJ4YW/8VmrO',1,'2023-02-21'),(8,'rafael','rafa05@gmail.com','rafa21','$2a$10$YmCcpLXcwrtVE6W7i2jovevEIJKmhq7VlXSaGlXYJcf/kNaPvcF6a',1,'2026-02-01'),(9,'fabian12','fabi1@gmail.com','fab','$2a$10$AqTn2XEksD131t5VgoIwMe0Chg0WQTGRtjigiE.YiTR0y7ow8R9he',1,'2026-02-03'),(10,'rafael','rafael21@gmail.com','rafita','$2a$10$GARwaNh6reC56GfQ8G.5WOvsTanZCDnSyrW5RO3eUf2mQT4Wpaxui',1,'2026-02-15'),(11,'prueba','p@gmail.com','prueba','$2a$10$Vx5Ni5oMhupo5v5ddJDXietZQ7rkEaOWx5lU9OTQfc0p0xN.1ZAjG',1,'2026-02-15');
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

-- Dump completed on 2026-02-15 23:51:39
