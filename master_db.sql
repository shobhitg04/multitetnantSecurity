-- MySQL dump 10.13  Distrib 5.7.32, for osx10.15 (x86_64)
--
-- Host: localhost    Database: master_db
-- ------------------------------------------------------
-- Server version	5.7.32

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
-- Table structure for table `tbl_tenant_master`
--

DROP TABLE IF EXISTS `tbl_tenant_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_tenant_master` (
  `tenant_client_id` int(10) unsigned NOT NULL,
  `db_name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `url` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `user_name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `driver_class` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `status` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`tenant_client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_tenant_master`
--

LOCK TABLES `tbl_tenant_master` WRITE;
/*!40000 ALTER TABLE `tbl_tenant_master` DISABLE KEYS */;
INSERT INTO `tbl_tenant_master` VALUES (1,'tenant1','jdbc:mysql://localhost:3306/tenant1?useSSL=false','root','root','com.mysql.cj.jdbc.Driver','ACTIVE'),(2,'tenant2','jdbc:mysql://localhost:3306/tenant2?useSSL=false','root','root','com.mysql.cj.jdbc.Driver','ACTIVE');
/*!40000 ALTER TABLE `tbl_tenant_master` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-19 19:10:34
