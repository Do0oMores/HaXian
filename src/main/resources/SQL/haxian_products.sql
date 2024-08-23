-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: haxian
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `origin` varchar(100) DEFAULT NULL,
  `production_date` date DEFAULT NULL,
  `support` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `shelf_life` int DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'海南妃子笑荔枝','早期上市，口感微酸，妃子笑荔枝，将初夏的所有水润都馈赠给了舌尖',5.99,80,'海南','2024-06-11','坤哥水果','2024-06-11 19:52:45',5,'水果','https://img.picui.cn/free/2024/06/12/6668f6551c813.jpg'),(2,'梅州果林散养土鸡蛋','与工厂饲养方式不同，自热散养所产土鸡蛋个头会有大有小',9.99,0,'广东梅州','2024-06-13','晨初山林','2024-06-13 19:37:23',30,'肉蛋奶','https://img.picui.cn/free/2024/06/13/666ad9cb4a742.jpg'),(3,'普罗旺斯粉西红柿','鲜嫩多汁',2.99,91,'山东','2024-06-12','坤哥蔬菜','2024-06-13 20:17:09',2,'蔬菜','https://img.picui.cn/free/2024/06/13/666ae31f81b6f.png'),(4,'德亚脱脂纯牛奶德国原装进口','德亚坚信，一杯好牛奶，可以让你的生活更加美好。数年来，坚持原装进口，精选点滴自然纯粹，只为让消费者享受到一杯好牛奶',7.99,49,'德国','2023-09-01','德亚','2024-06-13 20:23:49',365,'肉蛋奶','https://img.picui.cn/free/2024/06/13/666ae4a78f47d.jpg'),(5,'山东头茬绿宝石甜瓜','一口爆汁甜蜜的味道，沉浸在香甜如蜜的汁水中。颜色犹如绿宝石般夺目，散发着淡淡的瓜香。',4.99,10,'山东','2024-06-17','坤哥水果','2024-06-17 20:24:06',5,'水果','https://img.picui.cn/free/2024/06/17/66702aab2232f.png');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-21  9:53:19
