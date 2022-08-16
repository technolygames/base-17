-- MariaDB dump 10.19  Distrib 10.4.20-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: tienda
-- ------------------------------------------------------
-- Server version	10.4.20-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `almacen`
--

DROP TABLE IF EXISTS `almacen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `almacen` (
  `codigo_alm` int(5) NOT NULL,
  `codigo_prod` int(5) NOT NULL,
  `codigo_prov` int(5) NOT NULL,
  `nombre_prod` varchar(25) NOT NULL,
  `nombre_prov` varchar(20) NOT NULL,
  `marca_prod` varchar(20) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `stock` char(20) NOT NULL,
  `fecha_ingreso` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `almacen`
--

LOCK TABLES `almacen` WRITE;
/*!40000 ALTER TABLE `almacen` DISABLE KEYS */;
INSERT INTO `almacen` VALUES (10901,85735,26486,'Takis Guacamole','Raúl','Barcel',10,'Llegada','2021-03-13');
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleados` (
  `password` varchar(80) NOT NULL,
  `codigo_emp` int(11) NOT NULL,
  `nombre_emp` varchar(55) NOT NULL,
  `apellidop_emp` varchar(30) NOT NULL,
  `apellidom_emp` varchar(30) NOT NULL,
  `puesto` char(15) NOT NULL,
  `experiencia` int(3) NOT NULL,
  `grado_estudios` char(20) NOT NULL,
  `contacto` int(12) NOT NULL,
  `edad` int(3) NOT NULL,
  `estado` varchar(10) NOT NULL,
  `datos_extra` varchar(500) NOT NULL,
  `foto` blob DEFAULT NULL,
  `fecha_registro` date NOT NULL,
  `fecha_sesion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES ('Gtaswag5563',99876,'Prueba','Prueba','Prueba','Programador',0,'Bachillerato',0,18,'','',NULL,'2020-08-10','2021-06-25'),('test',12345,'Prueba','Prueba','Prueba','Empleado',0,'Bachillerato',0,19,'','prueba de datos extra',NULL,'2021-06-27','2021-06-27');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lib`
--

DROP TABLE IF EXISTS `lib`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lib` (
  `url` tinyblob NOT NULL,
  `name_lib` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lib`
--

LOCK TABLES `lib` WRITE;
/*!40000 ALTER TABLE `lib` DISABLE KEYS */;
/*!40000 ALTER TABLE `lib` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `codigo_prod` int(5) NOT NULL,
  `nombre_prod` varchar(20) NOT NULL,
  `marca_prod` varchar(25) NOT NULL,
  `cantidad` int(5) NOT NULL,
  `precio` int(4) NOT NULL,
  `ganancia` int(10) NOT NULL,
  `fecha_compra` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (78945,'Takis','Barcel',10,2,20,'2021-06-17'),(15479,'Takis','Barcel',10,2,20,'2021-06-17'),(92671,'takis','barcel',10,2,20,'2021-06-17'),(54321,'Prueba','Prueba',5,2,10,'2021-07-07'),(0,'null','null',0,0,0,'2021-07-07'),(54321,'Prueba','Prueba',10,2,20,'2021-07-07');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promociones`
--

DROP TABLE IF EXISTS `promociones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promociones` (
  `id_prom` varchar(20) NOT NULL,
  `nombre_prom` varchar(60) NOT NULL,
  `datos_prom` varchar(150) NOT NULL,
  `descuento` int(3) NOT NULL,
  `inicio` date NOT NULL,
  `num_usos` int(8) NOT NULL,
  `fin` date NOT NULL,
  PRIMARY KEY (`id_prom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promociones`
--

LOCK TABLES `promociones` WRITE;
/*!40000 ALTER TABLE `promociones` DISABLE KEYS */;
/*!40000 ALTER TABLE `promociones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `codigo_prov` int(11) NOT NULL,
  `nombre_prov` varchar(35) NOT NULL,
  `apellidop_prov` varchar(45) NOT NULL,
  `apellidom_prov` varchar(45) NOT NULL,
  `empresa` varchar(30) NOT NULL,
  `contacto` int(12) NOT NULL,
  `foto` blob DEFAULT NULL,
  `fecha_ingreso` date NOT NULL,
  `fecha_uentrega` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (99876,'Prueba','Prueba','Prueba','Prueba',0,NULL,'2021-06-29','2021-06-29');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socios`
--

DROP TABLE IF EXISTS `socios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socios` (
  `codigo_part` int(11) NOT NULL,
  `nombre_part` varchar(25) NOT NULL,
  `apellidop_part` varchar(30) NOT NULL,
  `apellidom_part` varchar(30) NOT NULL,
  `tipo_socio` varchar(35) NOT NULL,
  `datos_extra` varchar(300) NOT NULL,
  `foto` blob DEFAULT NULL,
  `fecha_ingreso` date NOT NULL,
  `fecha_ucompra` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socios`
--

LOCK TABLES `socios` WRITE;
/*!40000 ALTER TABLE `socios` DISABLE KEYS */;
INSERT INTO `socios` VALUES (99876,'Prueba','','Prueba Prueba','tipoSocio','datos extra de prueba',NULL,'2021-06-25','2021-06-25'),(99875,'Prueba','','Prueba Prueba','tipoSocio','prueba de datos extra',NULL,'2021-06-25','2021-06-25'),(99874,'Prueba','','Prueba Prueba','0','prueba de datos extra',NULL,'2021-06-25','2021-06-25'),(99873,'Prueba','','Prueba Prueba','Item 1','prueba de datos extra',NULL,'2021-06-25','2021-06-25'),(99873,'Prueba','Prueba','Prueba','Item 1','prueba de datos \nextra',NULL,'2021-06-27','2021-06-27');
/*!40000 ALTER TABLE `socios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suggestions`
--

DROP TABLE IF EXISTS `suggestions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suggestions` (
  `nombre` varchar(100) NOT NULL,
  `fecha` date NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `datos` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suggestions`
--

LOCK TABLES `suggestions` WRITE;
/*!40000 ALTER TABLE `suggestions` DISABLE KEYS */;
/*!40000 ALTER TABLE `suggestions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `contraseña` int(5) NOT NULL,
  `usuario` char(15) DEFAULT NULL,
  `fecha_creacion` varchar(15) DEFAULT NULL,
  `fecha_ingreso` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`contraseña`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (55089,'\"Arelí\"','04/08/2019','14/09/2019'),(55239,'\"Armando\"','12/09/2019','15/09/2019'),(55376,'\"Pablo\"','07/09/2019','14/09/2019'),(55685,'\"Saúl\"','09/09/2019','15/09/2019'),(55986,'\"Carmen\"','01/09/2019','15/09/2019');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-28 17:59:09
