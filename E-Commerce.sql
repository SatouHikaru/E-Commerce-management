CREATE DATABASE  IF NOT EXISTS `ecommerce` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `ecommerce`;
-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account` (
  `userName` varchar(40) NOT NULL,
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `avatar` text NOT NULL,
  `roleID` int(11) NOT NULL,
  PRIMARY KEY (`userName`,`roleID`),
  KEY `fk_account_role1_idx` (`roleID`),
  CONSTRAINT `fk_account_role1` FOREIGN KEY (`roleID`) REFERENCES `role` (`roleid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('admin','admin','admin.jpg',1),('customer1','customer1','Angular.jpg',3),('customer10','customer10','avatar.jpg',3),('customer2','customer2','avatar.jpg',3),('customer3','customer3','avatar.jpg',3),('customer4','customer4','avatar.jpg',3),('customer5','customer5','avatar.jpg',3),('customer6','customer6','avatar.jpg',3),('customer7','customer7','avatar.jpg',3),('customer8','customer8','avatar.jpg',3),('customer9','customer9','avatar.jpg',3),('employee1','employee1','Spring Framework.jpg',2),('employee2','employee2','avatar.jpg',2),('employee3','employee3','avatar.jpg',2),('employee4','employee4','avatar.jpg',2),('employee5','employee5','avatar.jpg',2),('employee6','employee6','avatar.jpg',2),('employee7','employee7','avatar.jpg',2),('employee8','employee8','avatar.jpg',2),('employee9','employee9','avatar.jpg',2),('hikaru','123456','avatar.jpg',3);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `air_conditioner`
--

DROP TABLE IF EXISTS `air_conditioner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `air_conditioner` (
  `productID` bigint(20) NOT NULL,
  `weight` float DEFAULT NULL,
  `roomVolume` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `coldCapacity` varchar(20) NOT NULL,
  `gas` varchar(10) NOT NULL,
  PRIMARY KEY (`productID`),
  CONSTRAINT `fk_table2_product1` FOREIGN KEY (`productID`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `air_conditioner`
--

LOCK TABLES `air_conditioner` WRITE;
/*!40000 ALTER TABLE `air_conditioner` DISABLE KEYS */;
INSERT INTO `air_conditioner` VALUES (63,9,'Dưới 15 m3','9.040 BTU','R32'),(64,9,'Từ 15 - 20 m3','11.500 BTU','R32'),(65,12,'Từ 20 - 30 m3','18.000 BTU','R32'),(66,9,'Dưới 15 m3','8.530 BTU','R32'),(67,8.6,'Dưới 15 m3','9.000 BTU','R-22'),(68,8.6,'Dưới 15 m2','9.000 BTU','R410A'),(69,9.2,'Dưới 15 m2','9.000 BTU','R410A'),(70,NULL,'Dưới 15 m3','9.200 BTU','R32'),(71,10,'Dưới 15 m3','8.500 / 10.900 BTU','R410A'),(72,NULL,'Từ 15 - 21 m3','12.200 BTU','R32');
/*!40000 ALTER TABLE `air_conditioner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Sony'),(2,'Samsung'),(3,'LG'),(4,'TP-LINK'),(5,'Panasonic'),(6,'Toshiba'),(7,'Sharp');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dvd_player`
--

DROP TABLE IF EXISTS `dvd_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dvd_player` (
  `productID` bigint(20) NOT NULL,
  `usb` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `weight` float DEFAULT NULL,
  `size` varchar(20) NOT NULL,
  PRIMARY KEY (`productID`),
  CONSTRAINT `fk_dvd_player_product1` FOREIGN KEY (`productID`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dvd_player`
--

LOCK TABLES `dvd_player` WRITE;
/*!40000 ALTER TABLE `dvd_player` DISABLE KEYS */;
INSERT INTO `dvd_player` VALUES (28,'Có',1.5,'430 x 43 x 207 mm'),(29,'Có',1.6,'430 x 43 x 207 mm'),(30,'Có',1.1,'300 x 43 x 208 mm'),(31,'Có',NULL,'360 x 40 x 207 mm');
/*!40000 ALTER TABLE `dvd_player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text NOT NULL,
  `dateCreated` datetime NOT NULL,
  `customerID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`customerID`),
  KEY `fk_feedback_customer1_idx` (`customerID`),
  CONSTRAINT `fk_feedback_customer1` FOREIGN KEY (`customerID`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateCreated` datetime NOT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `customerID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`customerID`),
  KEY `fk_order_customer1_idx` (`customerID`),
  CONSTRAINT `fk_order_customer1` FOREIGN KEY (`customerID`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'2018-12-29 13:02:23','Đã thanh toán',3),(2,'2018-12-30 06:02:23','Đã thanh toán',3),(3,'2019-01-01 04:40:23','Đã thanh toán',4),(4,'2019-01-02 04:40:23','Đã thanh toán',5),(5,'2019-01-02 04:40:23','Đã thanh toán',6),(6,'2019-01-05 04:40:23','Đã thanh toán',7),(7,'2019-01-06 04:40:23','Đã thanh toán',7),(8,'2019-01-06 04:40:23','Đã thanh toán',8),(9,'2019-01-10 04:40:23','Đã thanh toán',8),(10,'2019-01-10 04:40:23','Đã thanh toán',9),(11,'2019-01-15 00:00:00','Đặt hàng',3);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_detail` (
  `orderID` bigint(20) NOT NULL,
  `productID` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`orderID`,`productID`),
  KEY `fk_order_detail_product1_idx` (`productID`),
  CONSTRAINT `fk_order_detail_order1` FOREIGN KEY (`orderID`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_detail_product1` FOREIGN KEY (`productID`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,1,2),(1,2,1),(2,2,4),(2,23,1),(2,75,1),(3,10,5),(4,55,1),(5,36,3),(6,50,4),(7,40,2),(8,65,6),(9,71,1),(10,44,1),(11,75,3);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `productType` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` float NOT NULL,
  `quantity` int(11) NOT NULL,
  `image` text NOT NULL,
  `brandID` int(11) NOT NULL,
  `description` text,
  `countryOfOrigin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`,`brandID`),
  KEY `fk_product_brand1_idx` (`brandID`),
  CONSTRAINT `fk_product_brand1` FOREIGN KEY (`brandID`) REFERENCES `brand` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'INTERNET TIVI SONY KD-43X7000F 43 INCH','Ti vi',12900000,29,'43X7000F.png',1,NULL,NULL),(2,'INTERNET TIVI SONY KD-49X7000F 49 INCH','Ti vi',17500000,30,'49X7000F.png',1,NULL,NULL),(3,'ANDROID TIVI SONY 4K KD-49X9000F 49 INCH','Ti vi',27900000,30,'49X9000F.jpg',1,NULL,'Malaysia'),(4,'ANDROID TIVI SONY 4K KD-43X7500F 43 INCH','Ti vi',14500000,30,'KD-43X7500F.jpg',1,NULL,'Malaysia'),(5,'ANDROID TIVI SONY 4K KD-65A1 65 INCH','Ti vi',99900000,30,'KD-65A1.jpg',1,NULL,NULL),(6,'SMART TIVI SONY KDL-32W610F 32 INCH','Ti vi',8290000,30,'KDL-32W610F.jpg',1,NULL,'Malaysia'),(7,'SMART TIVI SONY KDL-43W660F 43 INCH','Ti vi',10900000,30,'KDL-43W660F.jpg',1,NULL,'Malaysia'),(8,'ANDROID TIVI SONY KDL-43W800F 43 INCH','Ti vi',12890000,30,'KDL-43W800F.jpg',1,NULL,'Malaysia'),(9,'ANDROID TIVI SONY KDL-49W800F 49 INCH','Ti vi',16900000,30,'KDL-49W800F.jpg',1,NULL,'Malaysia'),(10,'SMART TIVI SONY KDL-50W660F 50 INCH','Ti vi',14900000,30,'KDL-50W660F.jpg',1,NULL,'Malaysia'),(11,'SMART TIVI SAMSUNG UHD 4K 43MU6100 43 INCH','Ti vi',11900000,30,'43MU6100.jpg',2,NULL,'Việt Nam'),(12,'1.2.9.	SMART TIVI MÀN HÌNH CONG SAMSUNG UHD 4K 43NU7400','Ti vi',12890000,30,'43NU7400.jpg',2,NULL,'Việt Nam'),(13,'SMART TIVI SAMSUNG UHD 4K 49MU6100 50 INCH','Ti vi',17990000,30,'49MU6100.jpg',2,NULL,'Việt Nam'),(14,'SMART TIVI SAMSUNG UHD 4K 49NU7100 49 INCH','Ti vi',16390000,30,'49NU7100.jpg',2,NULL,'Việt Nam'),(15,'SMART TIVI SAMSUNG 55M5503 55 INCH','Ti vi',18990000,30,'55M5503.jpg',2,NULL,'Việt Nam'),(16,'SMART TIVI SAMSUNG PREMIUM UHD 4K 55MU7000 55 INCH','Ti vi',26900000,30,'55MU7000.jpg',2,NULL,'Việt Nam'),(17,'SMART TIVI MÀN HÌNH CONG SAMSUNG PREMIUM UHD 4K 55MU8000 49 INCH','Ti vi',22990000,30,'55MU8000.jpg',2,NULL,'Việt Nam'),(18,'SMART TIVI MÀN HÌNH CONG SAMSUNG PREMIUM UHD 4K 55MU9000 55 INCH','Ti vi',39900000,30,'55MU9000.jpg',2,NULL,'Việt Nam'),(19,'SMART TIVI QLED SAMSUNG UHD 4K QA49Q7F 49 INCH','Ti vi',29900000,30,'QA49Q7F.jpg',2,NULL,'Việt Nam'),(20,'SMART TIVI QLED SAMSUNG UHD 4K QA55Q7F 55 INCH','Ti vi',45990000,30,'QA55Q7F.jpg',2,NULL,'Việt Nam'),(21,'SMART TIVI LG 32LJ550D 32 INCH','Ti vi',5990000,30,'32LJ550D.jpg',3,NULL,NULL),(22,'SMART TIVI LG 49LK5700PTA 49 INCH','Ti vi',14900000,30,'49LK5700.jpg',3,NULL,NULL),(23,'SMART TIVI LG 43LK5400PTA 43 INCH','Ti vi',8890000,29,'43LK5400PTA.jpg',3,NULL,NULL),(28,'ĐẦU ĐĨA DVD SONY DVP-NS638P','Đầu đĩa DVD',790000,30,'DVP-NS638P.jpg',1,NULL,NULL),(29,'ĐẦU ĐĨA DVD SONY DVP-NS648P','Đầu đĩa DVD',1090000,30,'DVP-NS648P.jpg',1,NULL,NULL),(30,'ĐẦU ĐĨA DVD SAMSUNG E360','Đầu đĩa DVD',750000,30,'E360.jpg',2,NULL,NULL),(31,'ĐẦU ĐĨA DVD LG DP432','Đầu đĩa DVD',790000,30,'DP432.jpg',3,NULL,NULL),(32,'TP-LINK - TL-WR740N','Bộ định tuyến',310000,20,'TL-WR740N.jpg',4,NULL,NULL),(33,'MÁY GIẶT PANASONIC NA-F80VS9GRV 8 KG','Máy giặt',8190000,30,'NA-F80VS9GRV.jpg',5,NULL,'Việt Nam'),(34,'MÁY GIẶT PANASONIC NA-F85A4GRV 8,5 KG','Máy giặt',5790000,30,'NA-F85A4HRV.jpg',5,NULL,'Việt Nam'),(35,'MÁY GIẶT PANASONIC NA-F90V5LMX 9 KG','Máy giặt',7790000,30,'NA-F90V5LMX.jpg',5,NULL,'Việt Nam'),(36,'MÁY GIẶT PANASONIC NA-F100V5LRV 10 KG','Máy giặt',8890000,30,'NA-F100V5LRV.jpg',5,NULL,'Việt Nam'),(37,'MÁY GIẶT PANASONIC NA-F115V5LRV 11,5 KG','Máy giặt',11100000,30,'NA-F115V5LRV.jpg',5,NULL,'Việt Nam'),(38,'Máy giặt Samsung Inverter WA14N6780CV-SV 14 k','Máy giặt',13490000,30,'WA14N6780CV.jpg',2,NULL,'Thái Lan'),(39,'MÁY GIẶT SAMSUNG WA90M5120SW-SV 9 KG','Máy giặt',5190000,30,'WA90M5120SW.jpg',2,NULL,'Thái Lan'),(40,'MÁY GIẶT SAMSUNG INVERTER WW75J42G0KW-SV 7,5 KG','Máy giặt',8790000,30,'WW75J42G0KW.png',2,NULL,'Thái Lan'),(41,'MÁY GIẶT SAMSUNG INVERTER WW75J4233IW 7,5 KG','Máy giặt',9090000,30,'WW75J4233IW.jpg',2,NULL,'Thái Lan'),(42,'MÁY GIẶT SAMSUNG ADDWASH WW85K54E0UX-SV 8,5 KG','Máy giặt',12990000,20,'WW85K54E0UX.jpg',2,NULL,'Thái Lan'),(43,'MÁY GIẶT TOSHIBA 7KG AW-A800SV','Máy giặt',4190000,30,'AW-A800SV.png',6,NULL,'Thái Lan'),(44,'MÁY GIẶT TOSHIBA 9KG AW-B1000GV','Máy giặt',6190000,30,'AW-B1000GV.png',6,NULL,'Thái Lan'),(45,'MÁY GIẶT TOSHIBA INVERTER AW-DG1500WV 14 KG','Máy giặt',13490000,30,'AW-DG1500WV.jpg',6,NULL,'Thái Lan'),(46,'MÁY GIẶT TOSHIBA INVERTER AW-DG1600WV 15 KG','Máy giặt',14490000,30,'AW-DG1600WV.jpg',6,NULL,'Thái Lan'),(47,'MÁY GIẶT TOSHIBA 8,2KG AW-E920LV','Máy giặt',5290000,30,'AW-E920LV.png',6,NULL,'Thái Lan'),(48,'TỦ LẠNH PANASONIC NR-BA188VSV1 152 LÍT','Tủ lạnh',5990000,30,'NR-BA188VSV1.jpg',5,NULL,'Nhật Bản'),(49,'TỦ LẠNH PANASONIC INVERTER NR-BL267VSV1 234 LÍT','Tủ lạnh',6990000,30,'NR-BL267VSV1.jpg',5,NULL,'Nhật Bản'),(50,'TỦ LẠNH PANASONIC INVERTER NR-BV288GKVN 255 LÍT','Tủ lạnh',11690000,30,'NR-BV288GKVN.jpg',5,NULL,'Nhật Bản'),(51,'TỦ LẠNH PANASONIC INVERTER NR-BV288XSVN 255 LÍT','Tủ lạnh',96900000,30,'NR-BV288XSVN.jpg',5,NULL,'Nhật Bản'),(52,'TỦ LẠNH PANASONIC INVERTER NR-BV328GKVN 290 LÍT','Tủ lạnh',11490000,30,'NR-BV328GKVN.jpg',5,NULL,'Nhật Bản'),(53,'TỦ LẠNH SAMSUNG INVERTER RT19M300BGS-SV 208 LÍT','Tủ lạnh',4790000,30,'RT19M300BGS.jpg',2,NULL,'Việt Nam'),(54,'TỦ LẠNH SAMSUNG INVERTER RT22M4033S8-SV 236 LÍT','Tủ lạnh',6990000,30,'RT22M4033S8.jpg',2,NULL,'Việt Nam'),(55,'TỦ LẠNH SAMSUNG INVERTER RT25M4033UT-SV 256 LÍT','Tủ lạnh',7990000,30,'RT25M4033UT.jpg',2,NULL,'Việt Nam'),(56,'TỦ LẠNH SAMSUNG INVERTER RT32K5932S8-SV 319 LÍT','Tủ lạnh',11390000,30,'RT32K5932S8.jpg',2,NULL,'Thái Lan'),(57,'TỦ LẠNH SAMSUNG RT58K7100BS 586 LÍT','Tủ lạnh',19090000,30,'RT58K7100BS-1.jpg',2,NULL,'Thái Lan'),(58,'TỦ LẠNH TOSHIBA GR-A36VUBZ DS 305 LÍT','Tủ lạnh',8990000,30,'GR-A36VUBZ DS.png',6,NULL,NULL),(59,'TỦ LẠNH TOSHIBA GR-M25VBZ-S 186 LÍT','Tủ lạnh',6190000,30,'GR-M25VBZ.jpg',6,NULL,NULL),(60,'TỦ LẠNH TOSHIBA GR-M25VBZ-DS 186 LÍT','Tủ lạnh',6490000,30,'GR-M25VBZ-DS.jpg',6,NULL,NULL),(61,'TỦ LẠNH TOSHIBA INVERTER GR-M28VBZ-S 226 LÍT','Tủ lạnh',7790000,30,'GR-M28VBZ-S.jpg',6,NULL,NULL),(62,'TỦ LẠNH TOSHIBA INVERTER GR-M28VUBZ-UK 226 LÍT','Tủ lạnh',7790000,30,'GR-M28VUBZ-UK.jpg',6,NULL,NULL),(63,'ĐIỀU HÒA PANASONIC 1 CHIỀU CU-CS-N9SKH-8 1 NGỰA','Điều hòa',7990000,30,'CU-CS-N9SKH-8.jpg',5,NULL,'Malaysia'),(64,'ĐIỀU HÒA PANASONIC 1 CHIỀU CU-CS-N12SKH-8 1,5 NGỰA','Điều hòa',10390000,30,'CU-CS-N12SKH-8.jpg',5,NULL,'Malaysia'),(65,'ĐIỀU HÒA PANASONIC 1 CHIỀU CU-CS-N18TKH-8 2 NG','Điều hòa',15890000,30,'CU-CS-N18TKH-8.jpg',5,NULL,'Malaysia'),(66,'ĐIỀU HÒA PANASONIC 2 CHIỀU INVERTER CU-CS-Z9TKH-8 1 NGỰA','Điều hòa',15100000,30,'CU-CS-Z9TKH-8.jpg',5,NULL,'Malaysia'),(67,'ĐIỀU HÒA SAMSUNG 1 CHIỀU AR09MCFHAWKNSV 1 NGỰA','Điều hòa',6990000,30,'AR09MCFHAWKNSV.jpg',2,NULL,'Thái Lan'),(68,'ĐIỀU HÒA SAMSUNG 1 CHIỀU AR09MCFTBURN 1 NGỰA','Điều hòa',5990000,30,'AR09MCFTBURN.jpg',2,NULL,'Thái Lan'),(69,'ĐIỀU HÒA SAMSUNG 1 CHIỀU INVERTER AR10MVFSCURNSV 1 NGỰA','Điều hòa',7990000,30,'AR10MVFSCURNSV.jpg',2,NULL,'Thái Lan'),(70,'ĐIỀU HÒA TOSHIBA 1 CHIỀU INVERTER RAS-H10PKCVG-V 1 NGỰA','Điều hòa',9490000,30,'RAS-H10PKCVG-V.jpg',6,NULL,'Thái Lan'),(71,'ĐIỀU HÒA TOSHIBA 2 CHIỀU INVERTER RAS-H10S3KV-V 1 NGỰA','Điều hòa',11990000,30,'RAS-H10S3KV-V.jpg',6,NULL,'Thái Lan'),(72,'ĐIỀU HÒA TOSHIBA 1 CHIỀU INVERTER RAS-H13PKCVG-V 1,5 NGỰA','Điều hòa',14590000,30,'RAS-H13PKCVG-V.jpg',6,NULL,'Thái Lan'),(73,'NỒI CƠM ĐIỆN PANASONIC SR-W18GSLRA 1,8 LÍT','Nồi cơm điện',890000,30,'SR-W18GSLRA.jpg',5,NULL,'Ấn Độ'),(74,'NỒI CƠM ĐIỆN PANASONIC SR-W18GSRRA 1,8 LÍT','Nồi cơm điện',890000,30,'SR-W18GSRRA.jpg',5,NULL,'Ấn Độ'),(75,'NỒI CƠM ĐIỆN PANASONIC SR-ZE185WRAM 1,8 LÍT','Nồi cơm điện',2100000,28,'SR-ZE185WRAM.jpg',5,NULL,'Malaysia'),(76,'NỒI CƠM ĐIỆN PANASONIC SR-ZS185TRAM 1,8 LÍT','Nồi cơm điện',2400000,30,'SR-ZS185TRAM.jpg',5,NULL,'Malaysia'),(77,'NỒI CƠM ĐIỆN SHARP KSH-1010V 10,0L','Nồi cơm điện',2900000,30,'KSH-1010V.jpg',7,NULL,'Thái Lan'),(78,'NỒI CƠM ĐIỆN TỬ SHARP KS-TH18 1,8L','Nồi cơm điện',1700000,30,'KS-TH18.jpg',7,NULL,'Thái Lan'),(79,'NỒI CƠM ĐIỆN TỬ SHARP KS-ZT18 1,8L','Nồi cơm điện',1750000,30,'KS-ZT18.jpg',7,NULL,'Thái Lan'),(80,'NỒI CƠM ĐIỆN SHARP KS-N1802V 1,8L','Nồi cơm điện',1100000,30,'KS-N1802V-2.jpg',7,NULL,'Thái Lan');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refrigerator`
--

DROP TABLE IF EXISTS `refrigerator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `refrigerator` (
  `productID` bigint(20) NOT NULL,
  `size` varchar(30) NOT NULL,
  `weight` float DEFAULT NULL,
  `maxVolumetric` float NOT NULL,
  `freezerVolumetric` float DEFAULT NULL,
  `iceCubesVolumetric` float DEFAULT NULL,
  `coolingTechnology` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`productID`),
  CONSTRAINT `fk_refrigerator_product1` FOREIGN KEY (`productID`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refrigerator`
--

LOCK TABLES `refrigerator` WRITE;
/*!40000 ALTER TABLE `refrigerator` DISABLE KEYS */;
INSERT INTO `refrigerator` VALUES (48,'520 - 570 - 1260',NULL,167,114,53,'Panorama'),(49,'600 x 600 x 1.450',NULL,234,159,75,'Panorama'),(50,'656 x 1505 x 601 mm',60,255,170,85,'Panorama'),(51,'656 x 1500 x 601 mm',54,255,170,53,'Panorama'),(52,'656 x 1640 x 601 mm',64,290,205,85,'Panorama'),(53,'555 x 1,445 x 637',NULL,208,150,58,'Công nghệ All-around Cooling'),(54,'572 x 1.600 x 664',50,236,181,53,'Công nghệ luồng khí lạnh đa chiều'),(55,'572 x 1.698 x 664',55,256,202,56,'Công nghệ luồng khí lạnh đa chiều'),(56,'600 x 1.715 x 672',72,319,NULL,NULL,'Công nghệ Twin Cooling Plus'),(57,'178.7 - 83.6 - 78.8',96,586,426,160,'2 dàn lạnh độc lập Twin Cooling Plus™'),(58,'600 x 717 x 1620',56,350,218,87,'Gián tiếp (Quạt) - Không đóng tuyết'),(59,'547 x 652 x 1355',42,186,128,58,'Gián tiếp (quạt) - Không đóng tuyết'),(60,'547 x 652 x 1355',42,186,128,58,'Gián tiếp (quạt) - Không đóng tuyết'),(61,'547 x 652 x 1545',44,226,168,58,'Gián tiếp (quạt) - Không đóng tuyết'),(62,'547 x 652 x 1545',44,226,168,58,'Gián tiếp (quạt) - Không đóng tuyết');
/*!40000 ALTER TABLE `refrigerator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rice_cooker`
--

DROP TABLE IF EXISTS `rice_cooker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rice_cooker` (
  `productID` bigint(20) NOT NULL,
  `volumetric` float NOT NULL,
  `timer` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `standardTechnology` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`productID`),
  CONSTRAINT `fk_table3_product1` FOREIGN KEY (`productID`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rice_cooker`
--

LOCK TABLES `rice_cooker` WRITE;
/*!40000 ALTER TABLE `rice_cooker` DISABLE KEYS */;
INSERT INTO `rice_cooker` VALUES (73,1.8,'0','Nhật Bản'),(74,1.8,'0','Nhật Bản'),(75,1.8,'1','Nhật Bản'),(76,1.8,'1','Nhật Bản'),(77,10,'1','Nhật Bản'),(78,1.8,'1','Nhật Bản'),(79,1.8,'1','Nhật Bản'),(80,1.8,'0','Nhật Bản');
/*!40000 ALTER TABLE `rice_cooker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `roleID` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`roleID`),
  UNIQUE KEY `roleName_UNIQUE` (`roleName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(3,'customer'),(2,'employee');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `television`
--

DROP TABLE IF EXISTS `television`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `television` (
  `productID` bigint(20) NOT NULL,
  `screenSize` varchar(10) NOT NULL,
  `screenResolution` varchar(40) NOT NULL,
  `imageQuality` text,
  PRIMARY KEY (`productID`),
  CONSTRAINT `fk_electric_product1` FOREIGN KEY (`productID`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `television`
--

LOCK TABLES `television` WRITE;
/*!40000 ALTER TABLE `television` DISABLE KEYS */;
INSERT INTO `television` VALUES (1,'43 inch','Full HD (1920 x 1080 px)',NULL),(2,'49 inch','Full HD (1920 x 1080 px)',NULL),(3,'49 inch','Ultra HD 4K','Motionflow™ XR 200 Hz'),(4,'43 inch','Ultra HD 4K','Motionflow™ XR 200 Hz'),(5,'65 inch','Ultra HD 4K (3840 x 2160px)',NULL),(6,'32 inch','HD','Motionflow™ XR 200 Hz'),(7,'43 inch','Full HD','Motionflow™ XR 200 Hz'),(8,'43 inch','Full HD','Motionflow™ XR 200 Hz'),(9,'49 inch','Full HD','Motionflow™ XR 200 Hz'),(10,'50 inch','Full HD','Motionflow™ XR 200 Hz'),(11,'43 inch','4K UHD ( 3840 x 2160px )','1.300 Hz'),(12,'43 inch','4K Ultra HD (3840 x 2160px)',''),(13,'50 inch','4K UHD (3840 x 2160px)','1.300 Hz'),(14,'49 inch','4K Ultra HD (3840 x 2160px)',''),(15,'55 inch','Full HD (1920 x 1080px)','800 Hz'),(16,'55 inch','4K UHD (3840 x 2160px)','2.300 Hz'),(17,'49 inch','4K UHD (3840 x 2160px)','2.400 Hz'),(18,'55 inch','4K UHD (3840 x 2160px)','2.700 Hz'),(19,'49 inch','4K UHD (3840 x 2160px)','3.100 Hz'),(20,'55 inch','4K UHD (3840 x 2160px)','3.100 Hz'),(21,'32 inch','HD (1366 x 768px)',NULL),(22,'49 inch','Full HD (1920 x 1080px)',NULL),(23,'43 inch','Full HD (1920 x 1080px)',NULL);
/*!40000 ALTER TABLE `television` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthday` date NOT NULL,
  `phone` varchar(13) NOT NULL,
  `email` varchar(45) NOT NULL,
  `userName` varchar(40) NOT NULL,
  PRIMARY KEY (`id`,`userName`),
  KEY `fk_customer_account1_idx` (`userName`),
  CONSTRAINT `fk_customer_account1` FOREIGN KEY (`userName`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Nguyễn Lương Minh','Nam','1993-01-01','0363829364','minhln@gmail.com','employee1'),(2,'Trần Thị Thuỷ','Nữ','1992-05-12','0371237674','thuynt@gmail.com','employee2'),(3,'Phạm Minh Hiếu','Nam','1995-12-12','0333783234','hieupm@gmail.com','customer1'),(4,'Đỗ Văn Hoà','Nam','1995-08-01','0385478462','hoadv@gmail.com','customer2'),(5,'Đoàn Thanh Hằng','Nữ','1990-05-01','0129324923','hangdt@gmail.com','employee3'),(6,'Nguyễn Tường Vi','Nữ','1989-11-23','0977455634','vint@gmail.com','employee4'),(7,'Trần Anh Khoa','Nam','1987-03-22','0785674354','khoata@gmail.com','employee5'),(8,'Lê Trung Hiếu','Nam','1995-01-01','0457356363','hieult@gmail.com','employee6'),(9,'Hoàng Trung Khánh','Nam','1983-11-11','0989889665','khanhht@gmail.com','employee7'),(10,'Nguyễn Thu Trang','Nữ','1988-12-12','0342343254','trangnt@gmail.com','employee8'),(11,'Lê Minh Nhật','Nam','1982-09-06','0934385754','nhatlm@gmail.com','employee9'),(13,'Đoàn Thị Ánh','Nữ','1982-12-15','0354465465','anhdt@gmail.com','customer3'),(14,'Đỗ Minh Ngọc','Nữ','1984-03-24','0779651313','ngocdm@gmail.com','customer4'),(15,'Nguyễn Thị Thu Hiền','Nữ','1980-11-23','0321314644','hienntt@gmail.com','customer5'),(16,'Trần Nam Khánh','Nam','1988-12-20','0322154654','khanhtn@gmail.com','customer6'),(17,'Nguyễn Minh Nhật','Nam','1987-11-09','0321999566','nhatnm@gmail.com','customer7'),(18,'Võ Thị An','Nữ','1985-03-21','0798985132','anvt@gmail.com','customer8'),(19,'Nguyễn Tuấn Minh','Nam','1986-01-18','0987161313','minhnt@gmail.com','customer9'),(20,'Lê Thị Thanh','Nữ','1990-02-18','0908315654','thanhlt@gmail.com','customer10'),(21,'Satou Hikaru','Nam','1997-01-22','0365514723','sakaru0122@gmail.com','hikaru');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `washer`
--

DROP TABLE IF EXISTS `washer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `washer` (
  `productID` bigint(20) NOT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `laundryCage` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `spinSpeed` int(11) NOT NULL,
  `engine` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `size` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `weight` float DEFAULT NULL,
  `standardTechnology` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`productID`),
  CONSTRAINT `fk_washer_product1` FOREIGN KEY (`productID`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `washer`
--

LOCK TABLES `washer` WRITE;
/*!40000 ALTER TABLE `washer` DISABLE KEYS */;
INSERT INTO `washer` VALUES (33,'Cửa trên','Lồng đứng',700,'Dây Curoa','595 x 626 x 995 mm',34,'Nhật Bản'),(34,'Cửa trên','Lồng đứng',700,'Dây Curoa','595 x 651 x 1015 mm',35,'Nhật Bản'),(35,'Cửa trên','Lồng đứng',700,'Dây Curoa','595 x 658 x 1050 mm',NULL,'Nhật Bản'),(36,'Cửa trên','Lồng đứng',700,'Dây Curoa','595 × 658 × 1070 mm',39,'Nhật Bản'),(37,'Cửa trên','Lồng đứng',700,'Dây Curoa','681 x 715 x 1060 mm',48,'Nhật Bản'),(38,'Cửa trên','Lồng đứng',720,'Truyền động trực tiếp bền & êm','Cao 108 cm - Ngang 61 cm - Sâu 68 cm',52,'Hàn Quốc'),(39,'Cửa trên','Lồng đứng',700,'Dây Curoa','610 x 968 x 654 mm',40,'Hàn Quốc'),(40,'Cửa trước','Lồng ngang',1200,'Dây Curoa','Cao 84.5 cm - Ngang 60 cm - Sâu 61 cm',61,'Hàn Quốc'),(41,'Cửa trước','Lồng ngang',1200,'Dây Curoa','600x850x550 mm',59,'Hàn Quốc'),(42,'Cửa trước','Lồng ngang',1400,'Dây Curoa','Cao 85 cm - Ngang 60 cm - Sâu 55 cm ',63,'Hàn Quốc'),(43,'Cửa trên ','Lồng đứng',700,'Dây Curoa','555 x 905 x 590 mm',27,'Nhật Bản'),(44,'Cửa trên ','Lồng đứng',860,'Dây Curoa','620 x 1031x 645 mm',40,'Nhật Bản'),(45,'Cửa trên ','Lồng đứng',700,'Truyền động trực tiếp','685 x 710 x 1061 mm',53,'Nhật Bản'),(46,'Cửa trên ','Lồng đứng',700,'Truyền động trực tiếp','685 x 710 x 1061 mm',53,'Nhật Bản'),(47,'Cửa trên ','Lồng đứng',700,'Dây Curoa','585 x 990 x 605 mm',31,'Nhật Bản');
/*!40000 ALTER TABLE `washer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wireless_router`
--

DROP TABLE IF EXISTS `wireless_router`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `wireless_router` (
  `productID` bigint(20) NOT NULL,
  PRIMARY KEY (`productID`),
  CONSTRAINT `fk_table1_product1` FOREIGN KEY (`productID`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wireless_router`
--

LOCK TABLES `wireless_router` WRITE;
/*!40000 ALTER TABLE `wireless_router` DISABLE KEYS */;
INSERT INTO `wireless_router` VALUES (32);
/*!40000 ALTER TABLE `wireless_router` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ecommerce'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_AddAirConditioner` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AddAirConditioner`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20),
	IN trongLuong FLOAT, IN theTichPhongSuDung VARCHAR(20), IN congSuatLanh VARCHAR(20), IN ga VARCHAR(10))
BEGIN
	INSERT ecommerce.product (name, productType, price, quantity,image, brandID, description, countryOfOrigin)
    VALUES (tenSP, 'Điều hoà', gia, soLuong, hinhAnh, hang, moTa, xuatXu);
    
    SELECT @productID := id FROM ecommerce.product WHERE image = hinhAnh;
    INSERT ecommerce.air_conditioner (productID, weight, roomVolume, coldCapacity, gas)
    VALUES (@productID, trongLuong, theTichPhongSuDung, congSuatLanh, ga);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_AddDVDPlayer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AddDVDPlayer`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20),
	IN usb VARCHAR(10), IN trongLuong FLOAT, IN kichThuoc VARCHAR(10))
BEGIN
	INSERT ecommerce.product (name, productType, price, quantity,image, brandID, description, countryOfOrigin)
    VALUES (tenSP, 'Đầu đĩa DVD', gia, soLuong, hinhAnh, hang, moTa, xuatXu);
    
    SELECT @productID := id FROM ecommerce.product WHERE image = hinhAnh;
    INSERT ecommerce.dvd_player (productID, usb, weight, size)
    VALUES (@productID, usb, trongLuong, kichThuoc);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_AddNewProductToOrder` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AddNewProductToOrder`(IN maDH BIGINT, IN maSP BIGINT, IN soLuong INT)
BEGIN
	SELECT @count := COUNT(*) FROM ecommerce.order_detail
    WHERE orderID = maDH AND productID = maSP;
    
    IF @count > 0 THEN
		UPDATE ecommerce.order_detail
		SET quantity = quantity + soLuong
		WHERE orderID = maDH AND productID = maSP;
	ELSE
		INSERT ecommerce.order_detail VALUES (maDH, maSP, soLuong);
        
        UPDATE ecommerce.product
		SET quantity = quantity - soLuong
		WHERE id = maSP;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_AddProductToOrder` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AddProductToOrder`(IN ngayLap DATETIME, IN maKH BIGINT,
	IN maSP BIGINT, IN soLuong INT)
BEGIN
	INSERT ecommerce.order (dateCreated, status, customerID) VALUES (ngayLap, 'Đặt hàng', maKH);
    SELECT @orderID := id FROM ecommerce.order WHERE dateCreated = ngayLap;
    INSERT ecommerce.order_detail VALUES (@orderID, maSP, soLuong);
    
    UPDATE ecommerce.product
    SET quantity = quantity - soLuong
    WHERE id = maSP;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_AddRefrigerator` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AddRefrigerator`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20),
	IN kichThuoc VARCHAR(30), IN trongLuong FLOAT, IN tongDungTich FLOAT, IN dungTichNganLanh FLOAT,
	IN dungTichNganDa FLOAT, IN congNgheLamLanh VARCHAR(50))
BEGIN
	INSERT ecommerce.product (name, productType, price, quantity,image, brandID, description, countryOfOrigin)
    VALUES (tenSP, 'Tủ lạnh', gia, soLuong, hinhAnh, hang, moTa, xuatXu);
    
    SELECT @productID := id FROM ecommerce.product WHERE image = hinhAnh;
    INSERT ecommerce.refrigerator (productID, size, weight, maxVolumetric, freezerVolumetric, iceCubesVolumetric, coolingTechnology)
    VALUES (@productID, kichThuoc, trongLuong, tongDungTich, dungTichNganLanh, dungTichNganDa, congNgheLamLanh);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_AddRiceCooker` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AddRiceCooker`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20),
	IN theTich FLOAT, IN henGio VARCHAR(10), IN tieuChuanCongNghe VARCHAR(20))
BEGIN
	INSERT ecommerce.product (name, productType, price, quantity,image, brandID, description, countryOfOrigin)
    VALUES (tenSP, 'Nồi cơm điện', gia, soLuong, hinhAnh, hang, moTa, xuatXu);
    
    SELECT @productID := id FROM ecommerce.product WHERE image = hinhAnh;
    INSERT ecommerce.rice_cooker (productID, volumetric, timer, standardTechnology)
    VALUES (@productID, theTich, henGio, tieuChuanCongNghe);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_AddTelevision` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AddTelevision`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20),
	IN kichCoManHinh VARCHAR(10), IN doPhanGiai VARCHAR(40), IN chatLuongHinhAnh TEXT)
BEGIN
	INSERT ecommerce.product (name, productType, price, quantity,image, brandID, description, countryOfOrigin)
    VALUES (tenSP, 'Ti vi', gia, soLuong, hinhAnh, hang, moTa, xuatXu);
    
    SELECT @productID := id FROM ecommerce.product WHERE image = hinhAnh;
    INSERT ecommerce.television (productID, screenSize, screenResolution, imageQuality)
    VALUES (@productID, kichCoManHinh, doPhanGiai, chatLuongHinhAnh);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_AddWasher` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AddWasher`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20),
	IN loai VARCHAR(20), IN longGiat VARCHAR(20), IN tocDoQuay INT, IN dongCo VARCHAR(50),
	IN kichThuoc VARCHAR(50), IN trongLuong FLOAT, IN congNgheTieuChuan VARCHAR(20))
BEGIN
	INSERT ecommerce.product (name, productType, price, quantity,image, brandID, description, countryOfOrigin)
    VALUES (tenSP, 'Máy giặt', gia, soLuong, hinhAnh, hang, moTa, xuatXu);
    
    SELECT @productID := id FROM ecommerce.product WHERE image = hinhAnh;
    INSERT ecommerce.washer (productID, type, laundryCage, spinSpeed, engine, size, weight, standardTechnology)
    VALUES (@productID, loai, longGiat, tocDoQuay, dongCo, kichThuoc, trongLuong, congNgheTieuChuan);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_AddWirelessRouter` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AddWirelessRouter`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20))
BEGIN
	INSERT ecommerce.product (name, productType, price, quantity,image, brandID, description, countryOfOrigin)
    VALUES (tenSP, 'Bộ định tuyến', gia, soLuong, hinhAnh, hang, moTa, xuatXu);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_DeleteProductFromOrder` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_DeleteProductFromOrder`(IN maDH BIGINT, IN maSP BIGINT, IN soLuong INT)
BEGIN
	DELETE FROM ecommerce.order_detail WHERE orderID = maDH AND productID = maSP;
    
    UPDATE ecommerce.product
    SET quantity = quantity + soLuong
    WHERE id = maDH;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_UpdateAirConditioner` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_UpdateAirConditioner`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20), IN maSP BIGINT,
	IN trongLuong FLOAT, IN theTichPhongSuDung VARCHAR(20), IN congSuatLanh VARCHAR(20), IN ga VARCHAR(10))
BEGIN
	UPDATE ecommerce.product
    SET name = tenSP, price = gia, quantity = soLuong, image = hinhAnh, brandID = hang,
		description = moTa, countryOfOrigin = xuatXu
	WHERE id = maSP;
    
    UPDATE ecommerce.air_conditioner
    SET weight = trongLuong, roomVolume = theTichPhongSuDung, coldCapacity = congSuatLanh, gas = ga
	WHERE productID = maSP;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_UpdateDVDPlayer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_UpdateDVDPlayer`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20), IN maSP BIGINT,
    IN usb VARCHAR(10), IN trongLuong FLOAT, IN kichThuoc VARCHAR(10))
BEGIN
	UPDATE ecommerce.product
    SET name = tenSP, price = gia, quantity = soLuong, image = hinhAnh, brandID = hang,
		description = moTa, countryOfOrigin = xuatXu
	WHERE id = maSP;
    
    UPDATE ecommerce.dvd_player
    SET usb = kichCoManHinh, weight = trongLuong, size = kichThuoc
	WHERE productID = maSP;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_UpdateRefrigerator` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_UpdateRefrigerator`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20), IN maSP BIGINT,
    IN kichThuoc VARCHAR(30), IN trongLuong FLOAT, IN tongDungTich FLOAT, IN dungTichNganLanh FLOAT,
	IN dungTichNganDa FLOAT, IN congNgheLamLanh VARCHAR(50))
BEGIN
	UPDATE ecommerce.product
    SET name = tenSP, price = gia, quantity = soLuong, image = hinhAnh, brandID = hang,
		description = moTa, countryOfOrigin = xuatXu
	WHERE id = maSP;
    
    UPDATE ecommerce.refrigerator
    SET size = kichThuoc, weight = trongLuong, maxVolumetric = tongDungTich, freezerVolumetric = dungTichNganLanh,
		iceCubesVolumetric = dungTichNganDa, coolingTechnology = congNgheLamLanh
	WHERE productID = maSP;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_UpdateRiceCooker` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_UpdateRiceCooker`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20), IN maSP BIGINT,
    IN theTich FLOAT, IN henGio VARCHAR(10), IN tieuChuanCongNghe VARCHAR(20))
BEGIN
	UPDATE ecommerce.product
    SET name = tenSP, price = gia, quantity = soLuong, image = hinhAnh, brandID = hang,
		description = moTa, countryOfOrigin = xuatXu
	WHERE id = maSP;
    
    UPDATE ecommerce.rice_cooker
    SET volumetric = theTich, timer = henGio, standardTechnology = tieuChuanCongNghe
	WHERE productID = maSP;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_UpdateTelevision` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_UpdateTelevision`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20), IN maSP BIGINT,
	IN kichCoManHinh VARCHAR(10), IN doPhanGiai VARCHAR(40), IN chatLuongHinhAnh TEXT)
BEGIN
	UPDATE ecommerce.product
    SET name = tenSP, price = gia, quantity = soLuong, image = hinhAnh, brandID = hang,
		description = moTa, countryOfOrigin = xuatXu
	WHERE id = maSP;
    
    UPDATE ecommerce.television
    SET screenSize = kichCoManHinh, screenResolution = doPhanGiai, imageQuality = chatLuongHinhAnh
	WHERE productID = maSP;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_UpdateWasher` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_UpdateWasher`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20), IN maSP BIGINT,
	IN loai VARCHAR(20), IN longGiat VARCHAR(20), IN tocDoQuay INT, IN dongCo VARCHAR(50),
	IN kichThuoc VARCHAR(50), IN trongLuong FLOAT, IN congNgheTieuChuan VARCHAR(20))
BEGIN
	UPDATE ecommerce.product
    SET name = tenSP, price = gia, quantity = soLuong, image = hinhAnh, brandID = hang,
		description = moTa, countryOfOrigin = xuatXu
	WHERE id = maSP;
    
    UPDATE ecommerce.washer
    SET type = loai, laundryCage = longGiat, spinSpeed = tocDoQuay, engine = dongCo,
		size = kichThuoc, weight = trongLuong, standardTechnology = congNgheTieuChuan
	WHERE productID = maSP;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_UpdateWirelessRouter` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_UpdateWirelessRouter`(IN tenSP VARCHAR(100), IN gia FLOAT, IN soLuong INT,
	IN hinhAnh TEXT, IN hang INT, IN moTa TEXT, IN xuatXu VARCHAR(20), IN maSP BIGINT)
BEGIN
	UPDATE ecommerce.product
    SET name = tenSP, price = gia, quantity = soLuong, image = hinhAnh, brandID = hang,
		description = moTa, countryOfOrigin = xuatXu
	WHERE id = maSP;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-21 16:53:31
