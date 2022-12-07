-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: kp
-- ------------------------------------------------------
-- Server version	5.7.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `buy_list`
--

DROP TABLE IF EXISTS `buy_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buy_list` (
  `id_buy` bigint(20) NOT NULL AUTO_INCREMENT,
  `buyer_id_user` bigint(20) NOT NULL,
  `goods` bigint(20) NOT NULL,
  `status_buy_status` varchar(255) NOT NULL,
  `buy_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_buy`),
  KEY `FKlb0flv0014061jcsxn8u6p6nw` (`buyer_id_user`),
  KEY `FKcg54dmjfjbwnrp0pp6445dte2` (`goods`),
  KEY `FK68a0jdrjp3ou3n6dh0au7df49` (`status_buy_status`),
  CONSTRAINT `FK68a0jdrjp3ou3n6dh0au7df49` FOREIGN KEY (`status_buy_status`) REFERENCES `buy_status` (`buy_status`),
  CONSTRAINT `FKcg54dmjfjbwnrp0pp6445dte2` FOREIGN KEY (`goods`) REFERENCES `goods_list` (`id_goods`),
  CONSTRAINT `FKlb0flv0014061jcsxn8u6p6nw` FOREIGN KEY (`buyer_id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy_list`
--

LOCK TABLES `buy_list` WRITE;
/*!40000 ALTER TABLE `buy_list` DISABLE KEYS */;
INSERT INTO `buy_list` VALUES (1,9,1,'Получен','2022-12-02 18:18:03'),(2,8,5,'Получен','2022-12-02 18:18:03');
/*!40000 ALTER TABLE `buy_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buy_status`
--

DROP TABLE IF EXISTS `buy_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buy_status` (
  `buy_status` varchar(255) NOT NULL,
  PRIMARY KEY (`buy_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy_status`
--

LOCK TABLES `buy_status` WRITE;
/*!40000 ALTER TABLE `buy_status` DISABLE KEYS */;
INSERT INTO `buy_status` VALUES ('Возврат'),('Получен');
/*!40000 ALTER TABLE `buy_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods_list`
--

DROP TABLE IF EXISTS `goods_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods_list` (
  `id_goods` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_cost` decimal(8,2) NOT NULL,
  `goods_desc` varchar(1500) DEFAULT NULL,
  `goods_details` varchar(500) DEFAULT NULL,
  `goods_name` varchar(100) DEFAULT NULL,
  `seller_id_user` bigint(20) NOT NULL,
  `type_type_name` varchar(255) NOT NULL,
  `selled` bit(1) NOT NULL,
  PRIMARY KEY (`id_goods`),
  KEY `FKswsco5vvhvcsswv1lr8b0gvts` (`seller_id_user`),
  KEY `FK3bvehxuhko5rl344lg7lcha86` (`type_type_name`),
  CONSTRAINT `FK3bvehxuhko5rl344lg7lcha86` FOREIGN KEY (`type_type_name`) REFERENCES `goods_type` (`type_name`),
  CONSTRAINT `FKswsco5vvhvcsswv1lr8b0gvts` FOREIGN KEY (`seller_id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods_list`
--

LOCK TABLES `goods_list` WRITE;
/*!40000 ALTER TABLE `goods_list` DISABLE KEYS */;
INSERT INTO `goods_list` VALUES (1,5000.51,'Продам аккаунт 55 уровня, на котором нет никаких привязок, кроме логина, есть благословение полой луны (68 дней на 24.11.2022)\r\n\r\nВсе персонажи, оружие и детали аккаунта на скриншотах: https://imgur.com/a/58B2sUN\r\n\r\n(„• ᴗ •„)\r\n\r\nКарта закрыта полностью по интерактивной карте мною лично до Пустыни в Сумеру (качала для друга, но аккаунт оказался ненужным)\r\nСобраны все окулусы, есть все награды с первой и второй годовщины (уникальные крылья, декор, тонкий механизм Хранителя облаков и прыг-хлопушка)\r\n\r\nПишите, если заинтересует - буду рада ответить на любые вопросы (даже если не онлайн - пишите, увижу сразу)\r\n\r\n☆゜・。。・゜゜・。。・゜★','examlpe.das;eex123','[Без почты] Личный ☆ Ёимия С2 + Громовой пульс  Нахида  Тигнари С1  Мона  Джин  +3 лег. оружия',8,'Аккаунт',_binary ''),(5,11280.96,'Мамонтенок\r\nПод заказ, спрашивайте перед покупкой о наличии\r\nБыстрая доставка в любой город, кроме карлеона!\r\n\r\nЕсли я онлайн и не отвечаю это значит: я афк, занят или сплю. Будьте терпеливы, я ценю ваше время и отвечу вам как только смогу. ','заходи и пиши на акк - TheMamontOwner1221','Мамонтенок',9,'Предмет',_binary '');
/*!40000 ALTER TABLE `goods_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods_type`
--

DROP TABLE IF EXISTS `goods_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods_type` (
  `type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods_type`
--

LOCK TABLES `goods_type` WRITE;
/*!40000 ALTER TABLE `goods_type` DISABLE KEYS */;
INSERT INTO `goods_type` VALUES ('Аккаунт'),('Предмет'),('Прочее');
/*!40000 ALTER TABLE `goods_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_status`
--

DROP TABLE IF EXISTS `request_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request_status` (
  `request_status` varchar(255) NOT NULL,
  PRIMARY KEY (`request_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_status`
--

LOCK TABLES `request_status` WRITE;
/*!40000 ALTER TABLE `request_status` DISABLE KEYS */;
INSERT INTO `request_status` VALUES ('Закрыто'),('На рассмотрении'),('Отклонено');
/*!40000 ALTER TABLE `request_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requestmrg`
--

DROP TABLE IF EXISTS `requestmrg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requestmrg` (
  `id_requestmrg` bigint(20) NOT NULL AUTO_INCREMENT,
  `requestmrgdesc` varchar(1500) DEFAULT NULL,
  `user_id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id_requestmrg`),
  KEY `FKijuwjfygdvuqf9scan12aj9uq` (`user_id_user`),
  CONSTRAINT `FKijuwjfygdvuqf9scan12aj9uq` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requestmrg`
--

LOCK TABLES `requestmrg` WRITE;
/*!40000 ALTER TABLE `requestmrg` DISABLE KEYS */;
INSERT INTO `requestmrg` VALUES (1,'123',8);
/*!40000 ALTER TABLE `requestmrg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requestts`
--

DROP TABLE IF EXISTS `requestts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requestts` (
  `id_requestts` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_desc` varchar(1500) DEFAULT NULL,
  `request_name` varchar(100) DEFAULT NULL,
  `buy_id_buy` bigint(20) NOT NULL,
  `employee_id_user` bigint(20) DEFAULT NULL,
  `request_status_request_status` varchar(255) NOT NULL,
  PRIMARY KEY (`id_requestts`),
  KEY `FKdsqpu2wn4fabfu3csjik1yyx5` (`buy_id_buy`),
  KEY `FK5q7tx90dt61fwcccq7ttuba6p` (`employee_id_user`),
  KEY `FKm6ivnbwyb81ljcxi1sm25e84t` (`request_status_request_status`),
  CONSTRAINT `FK5q7tx90dt61fwcccq7ttuba6p` FOREIGN KEY (`employee_id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `FKdsqpu2wn4fabfu3csjik1yyx5` FOREIGN KEY (`buy_id_buy`) REFERENCES `buy_list` (`id_buy`),
  CONSTRAINT `FKm6ivnbwyb81ljcxi1sm25e84t` FOREIGN KEY (`request_status_request_status`) REFERENCES `request_status` (`request_status`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requestts`
--

LOCK TABLES `requestts` WRITE;
/*!40000 ALTER TABLE `requestts` DISABLE KEYS */;
/*!40000 ALTER TABLE `requestts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id_user` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `balance` decimal(8,2) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_photo` varchar(1200) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,_binary '',98.90,'thvele@mail.ru','thvele1','$2a$08$n2AOMAF.MwpI.narUb6xk.garXZFpoJVbJchq9f6SvuTljrfRTxrW','/usericons/def.jpg'),(7,_binary '',979154.00,'oadsfo@aodsnfo.s','0ooasdmna','$2a$08$1JlAxxej/3UfXbkClO.EIey0qZeNybFWS1.2PrqWzGcjlvzuirBe6','https://i.pinimg.com/564x/0a/13/f8/0a13f80b997386f2bae191cfa619c20d.jpg'),(8,_binary '',0.00,'kolokol1@maiil.ru','uuu123','$2a$08$cp32IxHGQlkmtOMlU2gceeUrT4t5R4zaU.jg0tijg/uPSfqQc0p56','https://i.pinimg.com/564x/51/ee/90/51ee9074206153007033fc3df4e15637.jpg'),(9,_binary '',0.00,'','123131adsfasdgsadg','$2a$08$T0Gnz8PGAedZkwh33sLS6uMzfyGroXwyQqcy5wZ9Gb6UdrTa25uMG','/usericons/def.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (9,'USER'),(8,'USER'),(8,'SELLER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-08  0:39:41
