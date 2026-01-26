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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Arquitectura','Habilidades para diseñar,crear, dirigir y construir proyectos arquitectónicos, que pueden ir desde diseños en pequeña escala (como casas), hasta gran escala (como el planeamiento de una ciudad).'),(2,'Contablidad/Finanzas','Actividades para mantener oportuna y correcta la aplicación del sistema contable y presupuestal, asi como mantener en forma eficiente la programación y pago de los egresos, así como la respectiva creación de pasivos.'),(3,'Computacion/TI','Trabajos de capturistas, manejo de paqueteria de software, diseño de redes, telecomunicaciones, etc.'),(4,'Ingenieria electronica','Trabajos con aplicación en la industria, telecomunicaciones, en el diseño y análisis de instrumentación electrónica, microcontroladores y microprocesadores. '),(5,'Recursos Humanos','Administración de nóminas, pagas extra de los empleados, supervisar el trabajo de los empleados, determinar las necesidades del personal.'),(6,'Servicio y atencion al cliente','Actividades relacionadas con ofrecer servicios y atencion a los clientes de forma efectiva.'),(7,'Logistica/transportes','Trabajos relacionados con capacidad de observación, buena memoria, habilidad numérica y verbal, razonamiento concreto y abstracto, imaginación e inventiva, habilidad para el manejo de instrumentos y material de laboratorio, capacidad de adaptación social y trabajo de campo.'),(8,'Desarrollo de software','Profesionistas capaces de analizar, diseñar y mejorar estratégicamente proyectos de sistemas de software mediante la aplicación de procesos, modelos, herramientas y estándares de calidad en su desarrollo.'),(9,'Diseño','Crear conceptos visuales para publicidad, reunirse con clientes para conocer el presupuesto del proyecto, asesorar a los clientes para crear estrategias de publicidad visual, liderar equipos de trabajo, diseñar logotipos.'),(10,'Comunicaciones','Profesionales que se encargan de escribir las noticias que usualmente vemos en las revistas, periódicos e incluso en la televisión, muchos incluso, se dedican a escribir historias en Blogs y en los diferentes medios de comunicación escrita.'),(11,'Mercadotecnia','Investigación de mercados, programación y desarrollo del producto, fijación de precios, canales de distribución y logística. Comunicación integral: publicidad, comunicación e imagen, relaciones públicas, marketing directo, promoción, etc.'),(12,'Ventas','Habilidades para supervisar y dirigir las actividades de una oficina o de un departamento de Ventas. Coordinar y monitorear el trabajo de los empleados a su cargo.'),(13,'Publicidad','Habilidadeds para planificar, dirigir y coordinar las actividades de publicidad y relaciones públicas de la empresa u organización. Diseñar y planificar campañas publicitarias. Dirigir y gestionar las actividades del personal de publicidad y relaciones públicas.'),(14,'Gerencia/Administracion','Profesionistas capaces para programar, organizar, dirigir, controlar y supervisar las actividades de personal, tesorería, contabilidad y costos, logística y servicios internos y de mantenimiento.'),(15,'Educación','Funciones de docencia de carácter profesional que implica la realización directa de los procesos sistemáticos de enseñanza - aprendizaje, lo cual incluye el diagnóstico, la planificación, la ejecución y la evaluación de los mismos procesos y sus resultados.'),(16,'Blockchain y Bitcoin','Trabajos relaciondos con Bitcoin, Blockchain, desarrollo de sistemas distribuidos, contratos inteligentes y RESTful APIs basadas en entornos de Open Source.'),(17,'Finanzas','Trabajos relacionados con finanzas y contabilidad'),(18,'Finanzas','Trabajos relacionados con finanzas y contabilidad');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-23  8:35:47
