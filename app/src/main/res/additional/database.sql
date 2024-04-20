-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: utsanmp
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `released_date` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'NBA Finals Preview: Lakers vs. Nets','John Smith','A comprehensive preview of the NBA Finals matchup between the Los Angeles Lakers and the Brooklyn Nets.','https://upload.wikimedia.org/wikipedia/en/thumb/4/44/NBA_Finals_logo_%282022%29.svg/1200px-NBA_Finals_logo_%282022%29.svg.png','2024-04-16 17:14:38'),(2,'Top 10 Plays of the Week','Emma Johnson','Check out the most exciting and jaw-dropping plays from this week\'s NBA games.','https://www.sportsnet.ca/wp-content/uploads/2023/08/Milwaukee-Bucks-forward-Giannis-Antetokounmpo-1040x572.jpg','2024-04-16 17:15:14'),(3,'2023 NBA Draft Recap','Michael Brown','Analysis and breakdown of the top picks and surprises from the 2023 NBA Draft.','https://cdn.vox-cdn.com/thumbor/dr4yaKCD8S03Ozy6IdWAUxVZwdg=/0x0:7792x5195/920x613/filters:focal(3197x839:4443x2085):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/72847841/1780588445.0.jpg','2024-04-16 17:16:08'),(4,'Golden State Warriors Season Review','Emily Thompson','A look back at the highs and lows of the Golden State Warriors\' season.','https://imgsrv2.voi.id/REKGtQrIWFSwcXCqsI16ZQfkRHeKgz4bmtK55-zo9XY/auto/1200/675/sm/1/bG9jYWw6Ly8vcHVibGlzaGVycy8xODAxNjkvMjAyMjA2MTcxMzM2LW1haW4uY3JvcHBlZF8xNjU1NDQ3NzgyLmpwZw.jpg','2024-04-16 17:17:03'),(5,'NCAA March Madness Predictions','Daniel Wilson','Predictions and analysis for the upcoming NCAA March Madness tournament.','https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/March_Madness_logo.svg/1200px-March_Madness_logo.svg.png','2024-04-16 17:17:32'),(6,'LeBron James: The King\'s Legacy','Sarah Davis','A tribute to LeBron James and his impact on the game of basketball.','https://img.olympics.com/images/image/private/t_social_share_thumb/f_auto/v1672757509/primary/n8oqmhtvrf9lc6hkszlj','2024-04-16 17:18:07'),(7,'Chicago Bulls: A Resurgence?','Andrew Clark','Can the Chicago Bulls make a comeback and reclaim their former glory?','https://www.si.com/.image/ar_16:9%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_xy_center%2Cq_auto:good%2Cw_620%2Cx_2261%2Cy_820/MTk2ODcwNzIyODgxMDcwMzM5/usatsi_20324798.jpg','2024-04-16 17:18:36'),(8,'WNBA: Rising Stars','Jessica Lee','Spotlight on the emerging talents in the Women\'s National Basketball Association.','https://www.sportsnet.ca/wp-content/uploads/2022/03/Griner-1040x572.jpg','2024-04-16 17:19:07'),(9,'Houston Rockets: Rebuilding Phase','David Miller','Analyzing the Houston Rockets\' strategy as they enter a rebuilding phase.','https://images2.minutemediacdn.com/image/upload/c_crop,w_4436,h_2495,x_0,y_0/c_fill,w_700,f_auto,q_auto,g_auto/images/ImagnImages/mmsport/si-temp/01hvha3k046yq492s94q','2024-04-16 17:19:37'),(10,'European Basketball: The Next Big Thing?','Sophia Garcia','Exploring the growing popularity and talent pool in European basketball leagues.','https://www.sportscasting.com/wp-content/uploads/2019/09/Luka-Doncic.jpg','2024-04-16 17:20:13');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `descr` varchar(255) NOT NULL,
  `news_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `page_ibfk_1` (`news_id`),
  CONSTRAINT `page_ibfk_1` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page`
--

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;
INSERT INTO `page` VALUES (1,'NBA Finals','LeBron James leads Lakers to finals',1),(2,'NBA Finals 2','Kevin Durant\'s comeback for the Nets',1),(3,'First Play','Dunk by Giannis Antetokounmpo',2),(4,'Second Play','Steph Curry\'s incredible buzzer beater',2),(5,'NBA Draft 1','Cade Cunningham goes first overall',3),(6,'NBA Draft 2','Rising stars in the second round',3),(7,'GSW Review 1','Steph Curry\'s MVP-worthy performance',4),(8,'GSW Review 2','Injuries plague the team',4),(9,'NCAA March Madness 1','Top contenders for the championship',5),(10,'NCAA March Madness 2','Cinderella teams to watch out for',5),(11,'Lebron James 1','LeBron\'s journey from high school to the NBA',6),(12,'Lebron James 2','Off-court philanthropy and activism',6),(13,'Zach Lavine?','Zach LaVine\'s All-Star performance',7),(14,'Zach Lavine as the center','Building a strong team around him',7),(15,'WNBA 1','Young players making their mark',8),(16,'WNBA 2','Impact of veterans on team success',8),(17,'Rockets Coming Back?','Draft prospects for the Rockets',9),(18,'Rockets','Trade rumors and potential moves',9),(19,'Luka \'Magic\' Doncic','Top European prospects for the NBA',10),(20,'Europe Players','Impact of international players in the league',10);
/*!40000 ALTER TABLE `page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'xarco','Archie','Oematan','s160421050@student.ubaya.ac','2');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'utsanmp'
--

--
-- Dumping routines for database 'utsanmp'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-20 17:18:17
