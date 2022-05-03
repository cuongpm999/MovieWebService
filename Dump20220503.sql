-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: movie_project
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `count_views`
--

DROP TABLE IF EXISTS `count_views`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `count_views` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `ipAddress` varchar(255) NOT NULL,
  `movieId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn7328g24vex7agckbylplj2wx` (`movieId`),
  CONSTRAINT `FKn7328g24vex7agckbylplj2wx` FOREIGN KEY (`movieId`) REFERENCES `movies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `count_views`
--

LOCK TABLES `count_views` WRITE;
/*!40000 ALTER TABLE `count_views` DISABLE KEYS */;
INSERT INTO `count_views` VALUES (11,'2022-04-26 21:59:43.046000','21.3131.3131.7',28),(12,'2022-04-26 22:00:56.324000','21.3131.3131.4',21),(13,'2022-04-26 22:01:04.903000','21.3131.3131.5',22),(14,'2022-04-26 22:01:32.970000','21.3131.3131.9',28),(15,'2022-04-26 22:01:45.910000','11.3131.3131.8',25);
/*!40000 ALTER TABLE `count_views` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credits`
--

DROP TABLE IF EXISTS `credits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credits` (
  `date` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `paymentId` int NOT NULL,
  PRIMARY KEY (`paymentId`),
  CONSTRAINT `FK1vmvqnufvmxjaevr3owwjtmb1` FOREIGN KEY (`paymentId`) REFERENCES `payments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credits`
--

LOCK TABLES `credits` WRITE;
/*!40000 ALTER TABLE `credits` DISABLE KEYS */;
INSERT INTO `credits` VALUES ('07/23','VISA','1234567890564323',4),('07/23','VISA','1234567890564323',5);
/*!40000 ALTER TABLE `credits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deals`
--

DROP TABLE IF EXISTS `deals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `discount` double NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `status` bit(1) DEFAULT NULL,
  `month` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deals`
--

LOCK TABLES `deals` WRITE;
/*!40000 ALTER TABLE `deals` DISABLE KEYS */;
INSERT INTO `deals` VALUES (5,0,'One',100000,_binary '',1),(6,6,'Anime',300000,_binary '',6),(7,12,'Test',600000,_binary '',12);
/*!40000 ALTER TABLE `deals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `digitalwallets`
--

DROP TABLE IF EXISTS `digitalwallets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `digitalwallets` (
  `name` varchar(255) NOT NULL,
  `paymentId` int NOT NULL,
  PRIMARY KEY (`paymentId`),
  CONSTRAINT `FKl99symg8cmjotj161tgtegra2` FOREIGN KEY (`paymentId`) REFERENCES `payments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `digitalwallets`
--

LOCK TABLES `digitalwallets` WRITE;
/*!40000 ALTER TABLE `digitalwallets` DISABLE KEYS */;
INSERT INTO `digitalwallets` VALUES ('Pay with PayPal',6),('Pay with PayPal',7),('Pay with PayPal',8),('Pay with PayPal',9),('Pay with Paypal',11),('Pay with Paypal',14),('Pay with Paypal',15),('Pay with Paypal',16),('Pay with Paypal',17),('Pay with Paypal',18);
/*!40000 ALTER TABLE `digitalwallets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_links`
--

DROP TABLE IF EXISTS `movie_links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_links` (
  `id` int NOT NULL AUTO_INCREMENT,
  `episodeName` varchar(1000) NOT NULL,
  `url` varchar(1000) NOT NULL,
  `movieId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpbi6nhhx9l93se2mr7t54kt1a` (`movieId`),
  CONSTRAINT `FKpbi6nhhx9l93se2mr7t54kt1a` FOREIGN KEY (`movieId`) REFERENCES `movies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_links`
--

LOCK TABLES `movie_links` WRITE;
/*!40000 ALTER TABLE `movie_links` DISABLE KEYS */;
INSERT INTO `movie_links` VALUES (21,'1','https://firebasestorage.googleapis.com/v0/b/video-443f6.appspot.com/o/Phim%2Falita_battle.mp4?alt=media&token=aba9d53b-a62a-4b71-a7e4-f58b71a7c2c9',21),(22,'1','https://firebasestorage.googleapis.com/v0/b/video-443f6.appspot.com/o/Phim%2FBDSAH.mp4?alt=media&token=070392fa-b9c1-4dab-bafa-3f4c7ac4524e',22),(23,'1','https://firebasestorage.googleapis.com/v0/b/video-443f6.appspot.com/o/Phim%2Fmarvel_s_the_avengers_trailer.mp4?alt=media&token=a2eeea27-4975-48c2-9727-1624a7f9a2b9',23),(24,'1','https://firebasestorage.googleapis.com/v0/b/video-443f6.appspot.com/o/Phim%2Ffast_furious_presents_hobbs_shaw.mp4?alt=media&token=44b8bafc-14bf-4216-a1f9-7f7ba8e49a6f',24),(25,'1','https://firebasestorage.googleapis.com/v0/b/video-443f6.appspot.com/o/Phim%2FKingdom%20Season%201.mp4?alt=media&token=381e24d1-afee-4e06-b379-31911e6cb5d9',25),(26,'2','https://firebasestorage.googleapis.com/v0/b/video-443f6.appspot.com/o/Phim%2FKingdom%20Season%202.mp4?alt=media&token=50360699-3ea8-4787-824e-c5766b6a6b2f',25),(27,'3','https://firebasestorage.googleapis.com/v0/b/video-443f6.appspot.com/o/Phim%2FKingdom%20Season%203.mp4?alt=media&token=5528c56e-f456-4dd5-b4a3-a37f9e814246',25),(28,'4','https://firebasestorage.googleapis.com/v0/b/video-443f6.appspot.com/o/Phim%2Fne_zha_official_trailer.mp4?alt=media&token=14686975-4a0d-4d23-a0d3-9e09764aeb44',25),(29,'5','https://firebasestorage.googleapis.com/v0/b/video-443f6.appspot.com/o/Phim%2FKingdom%205.mp4?alt=media&token=6c163ad8-e993-4565-905b-5d7898ec171a',25),(30,'6','https://firebasestorage.googleapis.com/v0/b/video-443f6.appspot.com/o/Phim%2FKingdom%206.mp4?alt=media&token=7d09db4d-381b-4e82-a752-25089f80bff8',25),(31,'1','https://firebasestorage.googleapis.com/v0/b/video-443f6.appspot.com/o/Phim%2FMission_%20Impossible%20-%20Fallout%20(2018).mp4?alt=media&token=48f1a28f-bb78-4f31-a15a-dcfc73f3f918',26),(32,'1','https://firebasestorage.googleapis.com/v0/b/video-443f6.appspot.com/o/Phim%2FResident%20Evil_%20Vendetta%20.mp4?alt=media&token=c2940ec6-2079-4256-b1c3-e72eb932eafb',27),(33,'1','https://firebasestorage.googleapis.com/v0/b/video-443f6.appspot.com/o/Phim%2Fne_zha_official_trailer.mp4?alt=media&token=14686975-4a0d-4d23-a0d3-9e09764aeb44',28);
/*!40000 ALTER TABLE `movie_links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(1000) NOT NULL,
  `content` text NOT NULL,
  `country` varchar(255) NOT NULL,
  `createdAt` datetime(6) NOT NULL,
  `director` varchar(255) NOT NULL,
  `episodeNumber` int NOT NULL,
  `imdb` double NOT NULL,
  `name` varchar(255) NOT NULL,
  `quality` varchar(255) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  `time` int NOT NULL,
  `trailer` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `year` int NOT NULL,
  `image` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (21,'Phim Hành Động, Phim Tình Cảm-Lãng Mạn, Phim Viễn Tưởng','Alita được ví như một thần chết đến từ địa ngục vì những khả năng vượt qua mọi giới hạn mà cô sở hữu. Cô được tìm thấy bởi Tiến sĩ Ido và không thể nhớ mình là ai hay từ đâu tới. Để có thể tìm ra sự thật, Atila phải dấn thân vào những cuộc chiến đấu nảy lửa.','Mỹ','2022-04-25 23:03:46.373000','Robert Rodriguez',1,7.3,'Alita: Battle Angel','HD',_binary '',122,'w7pYhpJaJW8','Phim lẻ',2019,'https://drive.google.com/uc?export=view&id=1365FddqwV_qly12vt6Y22F-OcKKky30P'),(22,'Phim Hành Động, Phim Tình Cảm-Lãng Mạn, Phim Viễn Tưởng','Biệt Đội Siêu Anh Hùng 4: Tàn Cuộc (Avengers 4: Endgame) ra mắt vào tháng 4/2019 sẽ giải quyết triệt để những khúc mắc đã vạch ra suốt 22 bộ phim trước đó của Vụ trụ điện ảnh Marvel (MCU). Hai tháng sau đó, Người Nhện 2 là khởi đầu hoàn toàn mới dành cho MCU. Trong phần trước, tên Titan điên rồ luôn bị ám ảnh về sứ mệnh phải “cân bằng” vũ trụ. Sau khi thu thập đủ 6 viên đá vô cực, Thanos khiến nửa thế giới tan biến thành cát bụi chỉ bằng một cái búng tay. Mở đầu của teaser trailer Endgame, Tony Stark dùng 1 cái mặt nạ của bộ giáp Iron Man để ghi hình lại tin nhắn video gởi cho người vợ Pepper Potts. Anh cho biết là mình đang trên đường trở về Trái đất nhưng không may bị trôi lạc trong vũ trụ, nước và lương thực thì cạn sạch từ 4 ngày trước, oxy thì qua ngày mai cũng hết, mạng sống bây giờ như chỉ mành treo chuông, thần chết chỉ còn cách vài tiếng. Tiếp theo đó là sự xuất hiện của Black Widow, Captain America, Hulk, Hawkeye, Thor, Nebula… những người trong số 50% dân số vũ trụ còn sống sót sau cái búng tay của Thanos. Và ngay lúc này, Black Widow cùng Cap và Hulk phải lên kế hoạch để giải cứu thế giới, à không, là giải cứu vũ trụ, một lần nữa.','Mỹ','2022-04-25 23:14:01.920000','Anthony Russo, Joe Russo',1,8.4,'Avengers 4: Endgame','HD',_binary '',150,'TcMBFSGVi1c','Phim lẻ',2019,'https://drive.google.com/uc?export=view&id=1b9v_SmXoZIClwqJbepX-OZeKHxc7xbMZ'),(23,'Phim Hành Động, Phim Phiêu Lưu, Phim Viễn Tưởng','Bộ phim là câu chuyện tiếp nối bộ phim The Avengers, khi Steve Rogers phải tìm cách hòa nhập vào thế giới hiện đại và kết hợp với Natasha Romanoff/Black Widow để chiến đấu chống lại một kẻ thù nguy hiểm ở Washington, D.C. Sau cuộc chiến cùng đội The Avengers tại New York, Captain America (hay còn gọi là Steve Rogers) có một cuộc sống khá thầm lặng tại Washington và anh phải vật lộn để thích nghi với cuộc sông ở thế giới hiện đại.','Mỹ','2022-04-25 23:23:44.915000','Anthony Russo, Joe Russo',1,7.8,'Captain America 2: The Winter Soldier','HD',_binary '',136,'7SlILk2WMTI','Phim lẻ',2014,'https://drive.google.com/uc?export=view&id=1wNt-AmBUOPlN0hkAn7wiN_nSZMniRNdK'),(24,'Phim Hành Động, Phim Phiêu Lưu, Phim Viễn Tưởng, Phim Hồi Hộp-Gây Cấn','Sau 8 phim với phần doanh thu chạm mức 5 tỉ đô la Mỹ toàn cầu, giờ đây thương hiệu Fast & Furious sẽ trở lại với một phần ngoại truyện hoàn toàn độc lập với sự tham gia của Dwayne Johnson trong vai Luke Hobbs và Jason Statham trong vai Deckard Shaw trong Hobbs & Shaw.','Mỹ','2022-04-25 23:29:26.358000','David Leitch',1,6.5,'Fast & Furious Presents: Hobbs & Shaw','HD',_binary '',127,'HZ7PAyCDwEg','Phim lẻ',2019,'https://drive.google.com/uc?export=view&id=1G-r9mJMUKNZkaldITiis8tU18NK7Va3_'),(25,'Phim Cổ Trang, Phim Kinh Dị, Phim Viễn Tưởng, Phim Hồi Hộp-Gây Cấn','Lấy bối cảnh hàn quốc thời trung cổ Joseon, một vị hoàng tử được phái đi điều tra vụ án do bệnh dịch hoành hành ở khắp đấy nước. Một sự thật đang đe dọa cả vương quốc, khi ngài phát hiện bệnh dịch tai quái đó chính là do những thây ma gây ra.','Hàn Quốc','2022-04-25 23:39:32.753000','Kim Sung-Hoon',6,8.3,'Kingdom (Season 2)','HD',_binary '',60,'4l-yByZpaaM','Phim bộ',2019,'https://drive.google.com/uc?export=view&id=1wraLkVJVA-ZOJxSP0kjLNSo1g3pl5UVI'),(26,'Phim Hành Động, Phim Phiêu Lưu, Phim Hồi Hộp-Gây Cấn','Những kẻ địch lớn nhất thường quay trở lại để săn đuổi bạn. Nhiệm Vụ Bất Khả Thi: Sụp Đổ với Ethan Hunt (Tom Cruise) và nhóm IMF của anh (Alec Baldwin, Simon Pegg, Ving Rhames) cùng với các đồng minh (Rebecca Ferguson, Michelle Monaghan) trong một cuộc đua với thời gian sau khi nhiệm vụ thất bại. Henry Cavill, Angela Bassett và Vanessa Kirby cũng sẽ tham gia vào dàn diễn viên khủng với nhà làm phim Christopher McQuarrie trở lại với bộ phim.','Mỹ, Trung Quốc','2022-04-25 23:45:47.464000','Christopher Mcquarrie',1,7.7,'Mission: Impossible – Fallout','HD',_binary '',147,'wb49-oV0F78','Phim lẻ',2018,'https://drive.google.com/uc?export=view&id=1K3OpdaaKVWCTCvLDq9dhT-T5WF1ORP3g'),(27,'Phim Hành Động, Phim Kinh Dị, Phim Hồi Hộp-Gây Cấn, Phim Họat Hình','Resident Evil: Vendetta xoay quanh bộ ba Chris Redfield, Leon Scoot Kennedy và Rebecca Chambers có mặt ở thành phố New York để ngăn cản cuộc tấn công khủng bố sinh học đang bùng phát với zombie cũng như nhiều sinh vật đột biến quái dị hơn. Âm mưu phá hoại thành phố được thực hiện bởi nhân vật phản diện mang tên Glenn Arias.','Nhật Bản','2022-04-25 23:50:16.789000','Takanori Tsujimoto',1,6.2,'Resident Evil: Vendetta','HD',_binary '',97,'Fs5g-l0Bcss','Phim lẻ',2017,'https://drive.google.com/uc?export=view&id=1Apt2KNKXvSa02IOIRvrXC7fMXStPqJHs'),(28,'Phim Hành Động, Phim Hài Hước, Phim Phiêu Lưu, Phim Họat Hình','Từ thuở xa xưa, tinh hoa đất trời hội tụ thành một viên ngọc chứa đựng năng lượng khổng lồ. Nguyên Thủy Thiên Tôn đã phân tách viên ngọc này thành 1 viên Linh Châu và 1 viên Ma Hoàn. Viên Linh Châu sẽ đầu thai thành một anh hùng cứu thế, phò trợ nhà Chu. Trong khi đó, Ma Hoàn sẽ tạo ra một Ma Vương tàn sát thiên hạ. Để ngăn chặn thảm họa, Nguyên Thủy Thiên Tôn đã hạ chú để sau 3 năm Ma Vương sẽ bị Thiên kiếp tiêu diệt. Liệu Na Tra có đủ sức để thay đổi Thiên mệnh?','Trung Quốc','2022-04-25 23:57:14.895000','Yu Yang',1,7.4,'Ne Zha','HD',_binary '',110,'oJEwLXhPY7Y','Phim lẻ',2019,'https://drive.google.com/uc?export=view&id=1-fG-hR1tiPDPsVZeBa7ELn_VfuAptI7-');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `endAt` datetime(6) NOT NULL,
  `startAt` datetime(6) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  `dealId` int NOT NULL,
  `paymentId` int NOT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhmihewpna6olbalupabvylsgi` (`dealId`),
  KEY `FK3uouvmvg4j5yov27idsmpw468` (`paymentId`),
  KEY `FK6co8q7ko456baksb6tdjq2dfv` (`userId`),
  CONSTRAINT `FK3uouvmvg4j5yov27idsmpw468` FOREIGN KEY (`paymentId`) REFERENCES `payments` (`id`),
  CONSTRAINT `FK6co8q7ko456baksb6tdjq2dfv` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `FKhmihewpna6olbalupabvylsgi` FOREIGN KEY (`dealId`) REFERENCES `deals` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (4,'2022-05-26 22:14:28.501000','2022-04-26 22:14:28.501000',_binary '',5,4,5),(5,'2022-05-26 22:15:31.717000','2022-04-26 22:15:31.717000',_binary '',6,5,6),(6,'2022-05-26 22:22:54.355000','2022-04-26 22:22:54.355000',_binary '',5,6,6),(7,'2022-05-26 22:23:13.701000','2022-04-26 22:23:13.701000',_binary '',6,7,7),(8,'2022-05-26 22:24:36.930000','2022-04-26 22:24:36.930000',_binary '',7,8,8),(9,'2022-05-26 22:25:10.184000','2022-04-26 22:25:10.184000',_binary '',6,9,9),(11,'2022-06-01 16:22:16.633000','2022-05-02 16:22:16.633000',_binary '',6,11,5),(12,'2022-06-01 22:06:59.149000','2022-05-02 22:06:59.149000',_binary '',5,14,32),(16,'2022-06-01 22:26:06.414000','2022-05-02 22:26:06.414000',_binary '',5,18,33);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `totalMoney` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (4,100000),(5,282000),(6,100000),(7,282000),(8,528000),(9,282000),(11,282000),(14,100000),(15,100000),(16,100000),(17,100000),(18,100000);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dateOfBirth` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `fullName` varchar(255) NOT NULL,
  `image` varchar(1000) NOT NULL,
  `mobile` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL,
  `sex` bit(1) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (5,'2000-12-10','tthuong3042@gmail.com','Thương Trần','https://res.cloudinary.com/cuongpham/image/upload/AndroidApps/Users/6LdpFCKbMzEF92qnTyUk.jpg','03131341','12456','USER',_binary '\0',_binary ''),(6,'2000-04-22','anhldp@gmail.com','Napole Duc Phan Anh','https://res.cloudinary.com/cuongpham/image/upload/AndroidApps/Users/6LdpFCKbMzEF92qnTyUk.jpg','0366155338','12456','USER',_binary '',_binary ''),(7,'2000-12-07','minhduc333@gmail.com','Minh Đức','https://res.cloudinary.com/cuongpham/image/upload/AndroidApps/Users/6LdpFCKbMzEF92qnTyUk.jpg','03131341','12456','USER',_binary '',_binary ''),(8,'2000-12-10','duyduong121@gmail.com','Duy Dương','https://res.cloudinary.com/cuongpham/image/upload/AndroidApps/Users/6LdpFCKbMzEF92qnTyUk.jpg','03131341','12456','USER',_binary '',_binary ''),(9,'2000-12-10','phamcuong121@gmail.com','Phạm Cường','https://res.cloudinary.com/cuongpham/image/upload/AndroidApps/Users/6LdpFCKbMzEF92qnTyUk.jpg','03131341','12456','USER',_binary '',_binary ''),(10,'2021-12-10','test212','Edit','sadasd','0313131','120313','ADMIN',_binary '',_binary '\0'),(11,'2021-12-10','test','Cuong Pham','sadasd','0313131','120313','ADMIN',_binary '',_binary '\0'),(13,'2021-12-10','minhduc0400@gmail.com','Cuong Pham','sadasd','0313131','120313','ADMIN',_binary '\0',_binary '\0'),(16,'2001-01-10','minhduc04001@gmail.com','Nguyễn Minh Đức','','0928831204','123123','ADMIN',_binary '',_binary ''),(17,'2000-02-03','minhduc0401230@gmail.com','Nguyễn Minh Đức','','0928831204','123123','ADMIN',_binary '\0',_binary ''),(18,'2000-04-20','minhduc0400123@gmail.com','Nguyễn Minh Đức','https://res.cloudinary.com/cuongpham/image/upload/v1651396545/MovieProject/User/pab6cdrj66dv9gfnjdzo.png','0928831204','Abc123xyz7','ADMIN',_binary '',_binary ''),(20,'2021-12-10','test1321321@gmail','Cuong Pham','sadasd','0313131','120313','ADMIN',_binary '',_binary ''),(21,'2000-08-21','ngo013210@gmail.com','Duy Duong','https://res.cloudinary.com/cuongpham/image/upload/v1651461870/MovieProject/User/rukjinjiiri1cti7olbx.png','0369356922','132132','USER',_binary '',_binary ''),(23,'2000-08-21','ngocdsadasduy210800@gmail.com','Duy Duong','https://res.cloudinary.com/cuongpham/image/upload/v1651462139/MovieProject/User/t4x5hfietqrj1uxug3yw.png','0369356922','kjlhjkl','USER',_binary '',_binary ''),(24,'2000-08-21','ngocduy210800@gmail.com','Duy Duong','https://res.cloudinary.com/cuongpham/image/upload/v1651462351/MovieProject/User/n0iol9weh4npklpzsgsh.png','0369356922','421341','USER',_binary '',_binary ''),(28,'2000-08-21','ngocduy2108@gmail.com','Duy Duong','https://res.cloudinary.com/cuongpham/image/upload/v1651498153/MovieProject/User/ly9m5bmybgdfvvvvyxab.png','0369356922','123','USER',_binary '',_binary ''),(32,'2000-01-01','leducphananh@gmail.com','B18DCCN017 - Lê Đức Phan Anh','https://lh3.googleusercontent.com/a/AATXAJwEnL0WwxglwJZLlqI1YhHcLylbCNnoLfOPHOb_=s96-c','N/A','N/A','USER',_binary '',_binary ''),(33,'2000-01-01','leducphananhz@gmail.com','Anh Phan','https://lh3.googleusercontent.com/a/AATXAJzJq2MRlfIumwHYTzvK08_X6zpCUnW9Rv5sZlIj=s96-c','N/A','N/A','USER',_binary '',_binary ''),(34,'2000-01-01','doahoangaymoi@yahoo.com','Phan Anh Lê Đức','https://graph.facebook.com/3285251921712575/picture','N/A','N/A','USER',_binary '',_binary '');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-03 10:58:06
