-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: quiz3
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `danhsachhocvien`
--

DROP TABLE IF EXISTS `danhsachhocvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danhsachhocvien` (
  `Stt` int NOT NULL AUTO_INCREMENT,
  `TenHocVien` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `QueQuan` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `NgaySinh` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `NgayGiaNhap` datetime NOT NULL,
  `User` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Stt`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhsachhocvien`
--

LOCK TABLES `danhsachhocvien` WRITE;
/*!40000 ALTER TABLE `danhsachhocvien` DISABLE KEYS */;
INSERT INTO `danhsachhocvien` VALUES (3,'abc','bien hoa','01/11/2000','Nu','2020-06-17 00:00:00','vananh','789'),(5,'phuong','tphcm','01/10/2000','nu','2020-06-18 00:00:00','lanphuong','123'),(6,'khue','tphcm','05/10/2000','nu','2020-06-18 00:00:00','minhkhue','789'),(12,'van xuyen','tphcm','1/10/2000','nu','2020-06-20 00:00:00','xuyen0000','123'),(14,'xuyen','bh','11/1/2000','nu','2020-06-21 00:00:00','xuyen000000','1');
/*!40000 ALTER TABLE `danhsachhocvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dscauhoi`
--

DROP TABLE IF EXISTS `dscauhoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dscauhoi` (
  `iddscauhoi` int NOT NULL AUTO_INCREMENT,
  `NoiDungCauHoi` text COLLATE utf8_unicode_ci NOT NULL,
  `MaDoanVan` int NOT NULL,
  `DapAn` text COLLATE utf8_unicode_ci NOT NULL,
  `GhiChu` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`iddscauhoi`),
  KEY `fk_dscauhoi_idx` (`MaDoanVan`),
  CONSTRAINT `fk_dscauhoi` FOREIGN KEY (`MaDoanVan`) REFERENCES `incomplete_conversation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dscauhoi`
--

LOCK TABLES `dscauhoi` WRITE;
/*!40000 ALTER TABLE `dscauhoi` DISABLE KEYS */;
INSERT INTO `dscauhoi` VALUES (7,'(1)',2,'canceled','null'),(8,'(2)',2,'preference','null'),(15,'cau 1',5,'what','null'),(16,'cau 2',5,'trick','null'),(17,'cau 1',6,'preference','null'),(18,'cau 2',6,'a','null');
/*!40000 ALTER TABLE `dscauhoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dsphuongan`
--

DROP TABLE IF EXISTS `dsphuongan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dsphuongan` (
  `iddsphuongan` int NOT NULL AUTO_INCREMENT,
  `MaCauHoi` int NOT NULL,
  `NoiDungPhuongAn` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`iddsphuongan`),
  KEY `fk_dscauhoi_dsphuongan_idx` (`MaCauHoi`),
  CONSTRAINT `fk_dscauhoi_dsphuongan` FOREIGN KEY (`MaCauHoi`) REFERENCES `dscauhoi` (`iddscauhoi`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dsphuongan`
--

LOCK TABLES `dsphuongan` WRITE;
/*!40000 ALTER TABLE `dsphuongan` DISABLE KEYS */;
INSERT INTO `dsphuongan` VALUES (1,7,'canceled'),(2,7,'purchased'),(3,7,'fulfilled'),(4,7,'received'),(5,8,'prefer'),(6,8,'preferred'),(7,8,'preferential'),(8,8,'preference'),(25,15,'why'),(26,15,'what'),(27,15,'where'),(28,15,'which'),(29,16,'treat'),(30,16,'trick'),(31,16,'try'),(32,16,'tried'),(33,17,'fulfilled'),(34,17,'canceled'),(35,17,'purchased'),(36,17,'received'),(37,18,'a'),(38,18,'c'),(39,18,'d'),(40,18,'b');
/*!40000 ALTER TABLE `dsphuongan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incomplete_conversation`
--

DROP TABLE IF EXISTS `incomplete_conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incomplete_conversation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Incomplete` text COLLATE utf8_unicode_ci NOT NULL,
  `Conversation` text COLLATE utf8_unicode_ci NOT NULL,
  `DoKho` enum('EASY','MEDIUM','HARD') COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incomplete_conversation`
--

LOCK TABLES `incomplete_conversation` WRITE;
/*!40000 ALTER TABLE `incomplete_conversation` DISABLE KEYS */;
INSERT INTO `incomplete_conversation` VALUES (2,'We have(1) ... your fax order for a case of hanging file folders. We have in stock the style of folders you requested, but you did not specify a color on the order form. We currently carry the Pro Stock Hanging File Folder in black, navy blue, light gree, and orange.If you could please get back to me with your(2) ... before the end of the day today, I will make sure that your order is processed in time for delivery by the end of the week.Please let me know if you are in need of any other office products at this time. You may respond to this e-mail or call me(3) ...','null','HARD'),(5,'abc','null','EASY'),(6,'null','We have(1) ... your fax order for a case of hanging file folders. We have in stock the style of folders you requested, but you did not specify a color on the order form. We currently carry the Pro Stock Hanging File Folder in black, navy blue, light gree, and orange.If you could please get back to me with your(2) ... before the end of the day today, I will make sure that your order is processed in time for delivery by the end of the week.Please let me know if you are in need of any other office products at this time. You may respond to this e-mail or call me(3) ...','EASY');
/*!40000 ALTER TABLE `incomplete_conversation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multiple`
--

DROP TABLE IF EXISTS `multiple`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `multiple` (
  `idMultiple` int NOT NULL AUTO_INCREMENT,
  `CauHoi` text COLLATE utf8_unicode_ci NOT NULL,
  `DoKho` enum('EASY','MEDIUM','HARD') COLLATE utf8_unicode_ci DEFAULT NULL,
  `DapAn` text COLLATE utf8_unicode_ci NOT NULL,
  `GhiChu` text COLLATE utf8_unicode_ci,
  `DanhMuc` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`idMultiple`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multiple`
--

LOCK TABLES `multiple` WRITE;
/*!40000 ALTER TABLE `multiple` DISABLE KEYS */;
INSERT INTO `multiple` VALUES (17,'I...a student','EASY','am','đáp án a','động từ'),(18,'She ...a doctor','EASY','is','đáp án b','động từ');
/*!40000 ALTER TABLE `multiple` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multiple_dsphuongan`
--

DROP TABLE IF EXISTS `multiple_dsphuongan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `multiple_dsphuongan` (
  `idmultiple_dsphuongan` int NOT NULL AUTO_INCREMENT,
  `MaCauHoi` int NOT NULL,
  `NoiDungPhuongAn` text COLLATE utf8_unicode_ci NOT NULL,
  `GhiChu` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`idmultiple_dsphuongan`),
  KEY `fk_multiple_dsphuongan_idx` (`MaCauHoi`),
  CONSTRAINT `fk_multiple_dsphuongan` FOREIGN KEY (`MaCauHoi`) REFERENCES `multiple` (`idMultiple`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multiple_dsphuongan`
--

LOCK TABLES `multiple_dsphuongan` WRITE;
/*!40000 ALTER TABLE `multiple_dsphuongan` DISABLE KEYS */;
INSERT INTO `multiple_dsphuongan` VALUES (25,17,'am',NULL),(26,17,'is',NULL),(27,17,'are',NULL),(28,17,'was',NULL),(29,18,'is',NULL),(30,18,'are',NULL),(31,18,'was',NULL),(32,18,'am',NULL);
/*!40000 ALTER TABLE `multiple_dsphuongan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thongke`
--

DROP TABLE IF EXISTS `thongke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thongke` (
  `Stt` int NOT NULL AUTO_INCREMENT,
  `User` text COLLATE utf8_unicode_ci NOT NULL,
  `Password` text COLLATE utf8_unicode_ci NOT NULL,
  `TenHocVien` text COLLATE utf8_unicode_ci NOT NULL,
  `Diem` double NOT NULL,
  `NgayLamBai` date NOT NULL,
  PRIMARY KEY (`Stt`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thongke`
--

LOCK TABLES `thongke` WRITE;
/*!40000 ALTER TABLE `thongke` DISABLE KEYS */;
INSERT INTO `thongke` VALUES (28,'vananh','789','Anh',5,'2020-06-20'),(29,'vananh','789','Anh',10,'2020-06-20'),(32,'VANANH0000','1','vân anh',5,'2020-06-20'),(34,'xuyen0000','123','van xuyen',5,'2020-06-20'),(35,'xuyen0000','123','van xuyen',1,'2020-06-20'),(36,'xuyen0000','123','van xuyen',10,'2020-06-20'),(37,'xuyen0000','123','van xuyen',3,'2020-06-20'),(38,'xuyen0000','123','van xuyen',3,'2020-06-20'),(39,'xuyen0000','123','van xuyen',10,'2020-06-20'),(40,'xuyen0000','123','van xuyen',5,'2020-06-20'),(41,'xuyen0000','123','van xuyen',1,'2020-06-20'),(42,'xuyen0000','123','van xuyen',3,'2020-06-20'),(43,'xuyen0000','123','van xuyen',5,'2020-06-20'),(44,'xuyen0000','123','van xuyen',5,'2020-06-21');
/*!40000 ALTER TABLE `thongke` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-13 20:30:53
