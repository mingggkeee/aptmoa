-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: aptmoa
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `noticeno` int NOT NULL AUTO_INCREMENT,
  `userId` varchar(30) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `hit` int DEFAULT '0',
  `regtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`noticeno`),
  KEY `notice_to_member_fk` (`userId`),
  CONSTRAINT `notice_to_member_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (58,'admin','Aptmoa 이용수칙 안내드립니다.','1. 로그인은 꼭 해주셔야합니다. \n2. 카카오 연동로그인으로 귀찮은 회원가입을 생략해보세요!\n',0,'2022-05-24 12:33:55'),(59,'admin','뉴스 모아보기 업데이트 완료 안내드립니다.','지금부터 부동산 관련 뉴스를 크롤링해서 제공해드립니다. \n오늘의 뉴스 가져오기 버튼 클릭시에 오늘의 뉴스를 가져옵니다.',0,'2022-05-24 12:35:01'),(60,'admin','관심 아파트 등록하기 기능 업데이트 완료','관심 있는 아파트를 등록해서 그 아파트의 가격변동 차트를 알아보세요!',0,'2022-05-24 12:35:36');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qna`
--

DROP TABLE IF EXISTS `qna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qna` (
  `qnano` int NOT NULL AUTO_INCREMENT,
  `userId` varchar(30) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `hit` int DEFAULT '0',
  `regtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`qnano`),
  KEY `qna_to_member_fk` (`userId`),
  CONSTRAINT `qna_to_member_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna`
--

LOCK TABLES `qna` WRITE;
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
INSERT INTO `qna` VALUES (49,'mksu234','좋은 웹사이트네요!!','부동산 실거래가를 정확히 알고 시세를 파악할수있어 너무 간편하고 좋게되어있네요! 앞으로 자주 이용할것같아요 ^^',0,'2022-05-24 12:22:47'),(50,'mksu234','개선점 요구합니다.','실거래 정보를 쉽게 알수있어서 좋습니다. \n다만 요즘 갭투기가 성행하는데 몇동 몇호도 표시가 되면 좀 더 좋지 않을까합니다. \n호가도 표시해서 실거래가와 차이도 알려주면 더 세밀해질것 같아요. 그리고 개선점이 좀 보이지만 다 쓰기는 어려울것 같네요.',0,'2022-05-24 12:23:49'),(51,'ssafy','로그인 제약 불편해요.','로그인을 해야 볼 수 있다거나, 리뷰를 써야 뭘 할 수 있다거나 제약을 두는 게 불편합니다. ',0,'2022-05-24 12:25:15'),(52,'ssafy','뉴스를 모아볼 수 있어서 좋아요!','굿굿!',0,'2022-05-24 12:25:39'),(53,'ssafy','관심있는 아파트 지정하면 가격변동 차트를 보는게 편해요!','앞으로도 자주 이용할 것 같아요!',0,'2022-05-24 12:26:19');
/*!40000 ALTER TABLE `qna` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-25 16:03:13
