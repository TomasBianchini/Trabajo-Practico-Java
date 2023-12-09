-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: jatp
-- ------------------------------------------------------
-- Server version	8.0.35

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


-- Data Base
drop database if exists jatp; 
create database jatp; 
USE jatp;
--
-- Table structure for table `aeropuerto`
--

DROP TABLE IF EXISTS `aeropuerto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aeropuerto` (
  `idaeropuerto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `descaeropuerto` varchar(255) DEFAULT NULL,
  `codPostal` varchar(40) NOT NULL,
  PRIMARY KEY (`idaeropuerto`),
  KEY `FK_aeropueto_ciudad_idx` (`codPostal`),
  CONSTRAINT `FK_aeropueto_ciudad` FOREIGN KEY (`codPostal`) REFERENCES `ciudad` (`codPostal`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aeropuerto`
--

LOCK TABLES `aeropuerto` WRITE;
/*!40000 ALTER TABLE `aeropuerto` DISABLE KEYS */;
INSERT INTO `aeropuerto` VALUES (6,'Ezeiza','Internacional','1228'),(7,'Jorge Newbery','Nacional','1228'),(8,'Islas Malvinas','Nacional','2000'),(10,'Caracas Airport','Internacional','1000'),(11,'Toronto City','Internacional','437212'),(21,'Abuja Airport Station','Internacional','900108'),(22,'Aeropuerto Brandeburgo Willy Brandt','Internacional','10115'),(23,'Aeropuerto Domingo Faustino Sarmiento','Nacional','456'),(24,'Aeropuerto de Charles de Gaulle','Internacional','70123'),(25,'Aeropuerto Internacional de Daxing','Internacional','065001'),(26,'Hartsfield-Jackson','Internacional','15000'),(28,'Fort Worth','Internacional','15002');
/*!40000 ALTER TABLE `aeropuerto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asiento`
--

DROP TABLE IF EXISTS `asiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asiento` (
  `idavion` int NOT NULL,
  `fila` char(1) NOT NULL,
  `numero` varchar(3) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`fila`,`numero`,`idavion`),
  KEY `FK_asiento_avion_idx` (`idavion`),
  CONSTRAINT `FK_asiento_avion` FOREIGN KEY (`idavion`) REFERENCES `avion` (`idavion`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asiento`
--

LOCK TABLES `asiento` WRITE;
/*!40000 ALTER TABLE `asiento` DISABLE KEYS */;
INSERT INTO `asiento` VALUES (4,'a','1','Economico'),(6,'a','1','Economico'),(37,'a','1','Economico'),(38,'a','1','Economico'),(39,'a','1','Economico'),(4,'a','2','Economico'),(6,'a','2','Economico'),(37,'a','2','Economico'),(38,'a','2','Economico'),(39,'a','2','Economico'),(4,'a','3','Economico'),(6,'a','3','Economico'),(37,'a','3','Economico'),(38,'a','3','Economico'),(39,'a','3','Economico'),(4,'b','1','Ejecutivo'),(6,'b','1','Ejecutivo'),(37,'b','1','Ejecutivo'),(38,'b','1','Ejecutivo'),(39,'b','1','Economico'),(4,'b','2','Ejecutivo'),(6,'b','2','Ejecutivo'),(37,'b','2','Ejecutivo'),(38,'b','2','Ejecutivo'),(39,'b','2','Economico'),(4,'b','3','Ejecutivo'),(6,'b','3','Ejecutivo'),(37,'b','3','Ejecutivo'),(38,'b','3','Ejecutivo'),(39,'b','3','Economico'),(37,'c','1','Ejecutivo'),(38,'c','1','Ejecutivo'),(39,'d','1','Ejecutivo'),(39,'d','2','Ejecutivo');
/*!40000 ALTER TABLE `asiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avion`
--

DROP TABLE IF EXISTS `avion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avion` (
  `idavion` int NOT NULL AUTO_INCREMENT,
  `marca` varchar(255) NOT NULL,
  `modelo` varchar(255) NOT NULL,
  `anio` varchar(45) NOT NULL,
  PRIMARY KEY (`idavion`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avion`
--

LOCK TABLES `avion` WRITE;
/*!40000 ALTER TABLE `avion` DISABLE KEYS */;
INSERT INTO `avion` VALUES (4,'Boeging','747','2018'),(6,'Airbus','320','2020'),(37,'Bombardier','534','2017'),(38,'Embraer','867','2022'),(39,'Boeing','856','2015'),(40,'Bombardier','634','2023');
/*!40000 ALTER TABLE `avion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudad` (
  `codPostal` varchar(40) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idpais` int NOT NULL,
  PRIMARY KEY (`codPostal`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `FK_prov_pais_idx` (`idpais`),
  CONSTRAINT `FK_prov_pais` FOREIGN KEY (`idpais`) REFERENCES `pais` (`idpais`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES ('065001','Pekin',33),('1000','Caracas',11),('10115','Berlin',32),('1228','Buenos Aires',1),('15000','Atlanta',36),('15001','Chicago',36),('15002','Dallas',36),('2000','Rosario',1),('437212','Toronto',19),('456','San juan',1),('70123','Paris',31),('900108','Abuya',35);
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pais` (
  `idpais` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`idpais`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (32,'Alemania'),(1,'Argentina'),(34,'Australia'),(16,'Brasil'),(19,'Canada'),(33,'China'),(10,'Colombia'),(36,'Estados Unidos'),(31,'Francia'),(35,'Nigeria'),(11,'Venezuela');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasaje`
--

DROP TABLE IF EXISTS `pasaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pasaje` (
  `idpasaje` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(45) NOT NULL,
  `fila` char(1) NOT NULL,
  `numero` varchar(3) NOT NULL,
  `idAvion` int NOT NULL,
  `idvuelo` int NOT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`idpasaje`),
  KEY `FK_pasaje_vuelo_idx` (`idvuelo`),
  KEY `FK_pasaje_asiento_idx` (`fila`,`numero`,`idAvion`),
  KEY `FK_pasaje_usuario_idx` (`idUsuario`),
  CONSTRAINT `FK_pasaje_asiento` FOREIGN KEY (`fila`, `numero`, `idAvion`) REFERENCES `asiento` (`fila`, `numero`, `idavion`) ON UPDATE CASCADE,
  CONSTRAINT `FK_pasaje_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idusuario`) ON UPDATE CASCADE,
  CONSTRAINT `FK_pasaje_vuelo` FOREIGN KEY (`idvuelo`) REFERENCES `vuelo` (`idvuelo`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=295 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasaje`
--

LOCK TABLES `pasaje` WRITE;
/*!40000 ALTER TABLE `pasaje` DISABLE KEYS */;
INSERT INTO `pasaje` VALUES (284,'Cancelado','a','1',4,1005,14),(285,'Finalizado','a','1',4,1006,14),(286,'Finalizado','b','2',4,1006,14),(287,'Confirmado','a','2',4,1006,14),(288,'Finalizado','b','1',4,1006,14),(289,'Confirmado','b','1',4,1005,6),(290,'Cancelado','a','2',4,1005,6),(291,'Finalizado','a','2',4,5456,6),(292,'Cancelado','a','1',4,1005,14),(293,'Confirmado','b','2',4,1005,6);
/*!40000 ALTER TABLE `pasaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `contrasenia` varchar(255) NOT NULL,
  `nroDocumento` varchar(45) NOT NULL,
  `tipoDocumento` varchar(45) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (3,'Tomas','Bianchini','tomassbianchini@gmail.com','vuelos316','42555120','DNI','2000-03-01','admin'),(6,'Tomas','Bianchini','tomasbianchini@hotmail.com','vuelos316','4255512','DNI','2000-03-01','user'),(14,'Fausto','Saludas','fausaludas14@gmail.com','1','40756561','dni','2000-03-02','user');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vuelo`
--

DROP TABLE IF EXISTS `vuelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vuelo` (
  `idvuelo` int NOT NULL,
  `fechaHoraSalida` datetime NOT NULL,
  `idAeropuertoOrigen` int NOT NULL,
  `idAeropuertoDestino` int NOT NULL,
  `idavion` int NOT NULL,
  `fechaHoraLlegada` datetime NOT NULL,
  `precioPrimeraClase` double DEFAULT NULL,
  `precioGeneral` double NOT NULL,
  PRIMARY KEY (`idvuelo`),
  KEY `FK_vuelo_avion_idx` (`idavion`),
  KEY `FK_aero_destino_vuelo_idx` (`idAeropuertoDestino`),
  KEY `FK_aero_origen_vuelo_idx` (`idAeropuertoOrigen`),
  CONSTRAINT `FK_aero_destino_vuelo` FOREIGN KEY (`idAeropuertoDestino`) REFERENCES `aeropuerto` (`idaeropuerto`) ON UPDATE CASCADE,
  CONSTRAINT `FK_aero_origen_vuelo` FOREIGN KEY (`idAeropuertoOrigen`) REFERENCES `aeropuerto` (`idaeropuerto`) ON UPDATE CASCADE,
  CONSTRAINT `FK_vuelo_avion` FOREIGN KEY (`idavion`) REFERENCES `avion` (`idavion`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vuelo`
--

LOCK TABLES `vuelo` WRITE;
/*!40000 ALTER TABLE `vuelo` DISABLE KEYS */;
INSERT INTO `vuelo` VALUES (99,'2023-12-14 15:15:00',11,22,37,'2023-12-14 21:30:00',750,500),(152,'2023-12-11 14:00:00',26,11,6,'2023-12-11 18:00:00',750,500),(566,'2023-12-15 19:30:00',25,11,6,'2023-12-16 00:30:00',3800,1200),(1005,'2023-12-27 17:00:00',6,7,4,'2023-12-27 23:00:00',100000,50000),(1006,'2023-12-08 00:30:00',6,7,4,'2023-12-27 23:00:00',100000,50000),(1009,'2023-12-26 13:00:00',10,22,6,'2023-12-27 20:00:00',100000,50000),(1016,'2023-12-18 15:27:00',6,21,4,'2023-12-19 13:27:00',90000,50000),(1020,'2023-12-21 22:30:00',23,24,37,'2023-12-22 06:30:00',115000,80000),(1076,'2023-12-27 12:30:00',25,7,37,'2023-12-28 23:30:00',200000,160000),(4555,'2023-12-11 19:30:00',6,8,4,'2023-12-11 22:30:00',750,500),(4559,'2023-12-13 12:00:00',25,26,38,'2023-12-13 14:00:00',15000,9500),(5456,'2023-12-08 12:30:00',7,6,4,'2023-12-08 12:59:00',100000,12005),(9556,'2023-12-15 20:00:00',25,21,38,'2023-12-16 00:00:00',3800,500),(45232,'2023-12-12 08:00:00',28,24,38,'2023-12-12 13:00:00',3800,500);
/*!40000 ALTER TABLE `vuelo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-09 16:10:34
