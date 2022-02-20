-- MySQL dump 10.13  Distrib 5.7.28, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: cicd_platform
-- ------------------------------------------------------
-- Server version	5.7.28

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
-- Table structure for table `application_config`
--

DROP TABLE IF EXISTS `application_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `application_uuid` varchar(32) NOT NULL COMMENT '应用uuid',
  `config_context` text NOT NULL COMMENT '应用配置',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流水线';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_config`
--

LOCK TABLES `application_config` WRITE;
/*!40000 ALTER TABLE `application_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_info`
--

DROP TABLE IF EXISTS `application_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '唯一标示做关联',
  `project_uuid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '关联项目uuid',
  `application_name` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '应用名称',
  `application_type` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '应用类型',
  `repository` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '代码仓库',
  `access_key` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '访问密钥',
  `access_way` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '访问方式',
  `creator` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) CHARACTER SET utf8mb4 NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `application_info_application_name_uindex` (`application_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='应用';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_info`
--

LOCK TABLES `application_info` WRITE;
/*!40000 ALTER TABLE `application_info` DISABLE KEYS */;
INSERT INTO `application_info` VALUES (1,'1e635672d5524c96ae85dc8c0ae153a5','07c697c30f5f4d6280e34a6b1d46af42','app1','Java','https://gitee.com/ken_xue/pipeline.git','https://gitee.com/ken_xue/pipeline.git','SSH','super_admin','super_admin','2021-12-28 14:38:57','2021-12-28 14:38:57','0'),(2,'dc23660d50624981ae346ee77a04d8d3','be408f98e96249a6a930a560cf2514b4','app2','Go','https://gitee.com/ken_xue/pipeline.git','https://gitee.com/ken_xue/pipeline.git','Https','super_admin','super_admin','2021-12-28 14:46:36','2021-12-28 14:46:36','0'),(4,'3040264d22e74d88abdd8c893218a1db','a3d912c1c56f4fc19d6a1239a216b786','app3','NodeJS','https://gitee.com/ken_xue/pipeline.git','https://gitee.com/ken_xue/pipeline.git','SVN','super_admin','super_admin','2021-12-28 14:48:09','2021-12-28 14:48:09','0'),(5,'7b08517e94054c938c94ff5d4f4620fe','ec5d0c3ecc7c4fe1939ac42f57c9c664','app4','Java','https://gitee.com/ken_xue/ddd-framework.git','https://gitee.com/ken_xue/ddd-framework.git','Https','super_admin','super_admin','2021-12-28 15:01:14','2021-12-28 15:01:14','0'),(6,'c7c12b28718340a29a28d955de29f66e','a3d912c1c56f4fc19d6a1239a216b786','app5','Java','https://gitee.com/ken_xue/pipeline.git','https://gitee.com/ken_xue/pipeline.git','SVN','admin','admin','2022-02-06 14:16:09','2022-02-06 14:16:09','0'),(7,'ec773baf443844b8b4e9aecf5ab94305','be408f98e96249a6a930a560cf2514b4','熔断服务','Go','https://github.com/Nepxion/Discovery.git','https://github.com/Nepxion/Discovery.git','Https','admin','admin','2022-02-07 05:46:06','2022-02-07 05:46:06','0'),(8,'ea44eb076b85411a8760c20a052f0944','be408f98e96249a6a930a560cf2514b4','系统中台化app1','Java','https://gitee.com/ken_xue/cicd-platform.git','https://gitee.com/ken_xue/cicd-platform.git','Https','admin','admin','2022-02-20 03:26:01','2022-02-20 03:26:01','0');
/*!40000 ALTER TABLE `application_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_of_user`
--

DROP TABLE IF EXISTS `application_of_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_of_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `application_uuid` varchar(32) NOT NULL COMMENT '应用uuid',
  `user_uuid` varchar(32) NOT NULL COMMENT '用户uuid',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用关联用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_of_user`
--

LOCK TABLES `application_of_user` WRITE;
/*!40000 ALTER TABLE `application_of_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_of_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_pipeline`
--

DROP TABLE IF EXISTS `application_pipeline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_pipeline` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `application_uuid` varchar(32) NOT NULL COMMENT '应用uuid',
  `pipeline_name` varchar(100) NOT NULL COMMENT '流水线名称',
  `pipeline_context` text NOT NULL COMMENT '应用类型',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='流水线';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_pipeline`
--

LOCK TABLES `application_pipeline` WRITE;
/*!40000 ALTER TABLE `application_pipeline` DISABLE KEYS */;
INSERT INTO `application_pipeline` VALUES (1,'1382fc62a7bd4b2aae14cb2cbb772a60','1e635672d5524c96ae85dc8c0ae153a5','dev-1.0.0','fdfasdfa','admin','admin','2022-02-07 07:08:56','2022-02-07 07:08:56','0'),(2,'6d21d4a2c829409fb87ba57e5abb3f41','1e635672d5524c96ae85dc8c0ae153a5','dev-1.0.1','gmtModified','admin','admin','2022-02-07 07:12:14','2022-02-07 07:12:14','0'),(3,'faf3c4ef41be4fe9921a48dac79385a5','ec773baf443844b8b4e9aecf5ab94305','release-1.0.0','dddd','admin','admin','2022-02-07 08:31:30','2022-02-07 08:31:30','0');
/*!40000 ALTER TABLE `application_pipeline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machine_info`
--

DROP TABLE IF EXISTS `machine_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `machine_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '服务器名',
  `ip` varchar(32) DEFAULT NULL COMMENT '服务器ip',
  `port` int(11) DEFAULT '22' COMMENT '连接端口',
  `access_username` varchar(32) DEFAULT NULL COMMENT '服务器登陆用户名',
  `access_way` varchar(30) DEFAULT '0' COMMENT '访问方式',
  `access_password` varchar(32) DEFAULT NULL COMMENT '服务器登陆密码',
  `access_key` varchar(32) DEFAULT NULL COMMENT '服务器登陆密钥',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='服务器节点';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine_info`
--

LOCK TABLES `machine_info` WRITE;
/*!40000 ALTER TABLE `machine_info` DISABLE KEYS */;
INSERT INTO `machine_info` VALUES (1,'刘架构服务器','116.198.160.39',22,'root','PASSWORD','',NULL,'f1944396bf43402aaa501b5856f67379','admin','admin','2022-02-07 14:17:55','2022-02-07 14:17:55','0'),(2,'上海机房-334-20','123.123.123.123',22,'admin','ACCESS_KEY','admin','admin','6751ea8b327d4f0cb26b051005321123','admin','admin','2022-02-07 13:32:49','2022-02-07 13:32:49','0'),(3,'杭州机房3-23','13.32.12.3',22,'root','PASSWORD','123456',NULL,'152554e6d4c14de58ce4eb98ee80b98f','admin','admin','2022-02-07 13:13:15','2022-02-07 13:13:15','0'),(4,'上海机房-334-20','12.123.123.123',22,'admin','ACCESS_KEY','admin','admin','6456784b327d4f0cb26b051005acc4be','admin','admin','2022-02-07 13:32:49','2022-02-07 13:32:49','0'),(5,'杭州机房3-23','123.32.12.3',22,'root','PASSWORD','123456',NULL,'252554e6d4c14de58ce4eb98ee80b98f','admin','admin','2022-02-07 13:13:15','2022-02-07 13:13:15','0'),(6,'上海机房-334-20','123.123.123.123',22,'admin','ACCESS_KEY','admin','admin','6751ea8b33334f0cb26b051005acc4be','admin','admin','2022-02-07 13:32:49','2022-02-07 13:32:49','0'),(7,'杭州机房3-23','123.32.12.3',22,'root','PASSWORD','123456',NULL,'352554e6d4c14de58ce4eb98ee80b98f','admin','admin','2022-02-07 13:13:15','2022-02-07 13:13:15','0'),(8,'上海机房-334-20','123.123.123.123',22,'admin','ACCESS_KEY','admin','admin','6751ea8b327d4f0cb26b051005acc4be','admin','admin','2022-02-07 13:32:49','2022-02-07 13:32:49','0'),(10,'杭州机房3-23','123.32.12.3',22,'root','PASSWORD','123456','mikey','852554e6d4c14de58ce4eb98ee80b98f','admin','admin','2022-02-07 13:13:15','2022-02-07 13:13:15','0');
/*!40000 ALTER TABLE `machine_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `middleware_kafka`
--

DROP TABLE IF EXISTS `middleware_kafka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `middleware_kafka` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='基建中间件kafka';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `middleware_kafka`
--

LOCK TABLES `middleware_kafka` WRITE;
/*!40000 ALTER TABLE `middleware_kafka` DISABLE KEYS */;
/*!40000 ALTER TABLE `middleware_kafka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `middleware_mysql`
--

DROP TABLE IF EXISTS `middleware_mysql`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `middleware_mysql` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='基建中间件mysql';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `middleware_mysql`
--

LOCK TABLES `middleware_mysql` WRITE;
/*!40000 ALTER TABLE `middleware_mysql` DISABLE KEYS */;
/*!40000 ALTER TABLE `middleware_mysql` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `middleware_redis`
--

DROP TABLE IF EXISTS `middleware_redis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `middleware_redis` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='基建中间件redis';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `middleware_redis`
--

LOCK TABLES `middleware_redis` WRITE;
/*!40000 ALTER TABLE `middleware_redis` DISABLE KEYS */;
/*!40000 ALTER TABLE `middleware_redis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `middleware_zookeeper`
--

DROP TABLE IF EXISTS `middleware_zookeeper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `middleware_zookeeper` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='基建中间件zk';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `middleware_zookeeper`
--

LOCK TABLES `middleware_zookeeper` WRITE;
/*!40000 ALTER TABLE `middleware_zookeeper` DISABLE KEYS */;
/*!40000 ALTER TABLE `middleware_zookeeper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_info`
--

DROP TABLE IF EXISTS `project_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '唯一标示做关联',
  `project_name` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '项目名称',
  `user_uuid` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '负责人',
  `project_desc` varchar(3000) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `creator` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) CHARACTER SET utf8mb4 NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `project_info_project_name_uindex` (`project_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='项目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_info`
--

LOCK TABLES `project_info` WRITE;
/*!40000 ALTER TABLE `project_info` DISABLE KEYS */;
INSERT INTO `project_info` VALUES (1,'07c697c30f5f4d6280e34a6b1d46af42','云原生一期','26220d63bfd345dabb2f114287965313','云原生一期','admin','admin','2022-02-19 04:10:02','2022-02-19 04:10:02','0'),(2,'be408f98e96249a6a930a560cf2514b4','系统中台化一期','9df9de08354f456c97fae0cdb3df214f','系统中台化一期系统中台化一期系统中台化一期系统中台化一期','admin','admin','2022-02-19 06:42:06','2022-02-19 06:42:06','0'),(3,'a3d912c1c56f4fc19d6a1239a216b786','测试添加','26220d63bfd345dabb2f114287965313','测试添加','admin','admin','2022-02-20 02:52:20','2022-02-20 02:52:20','0'),(4,'ec5d0c3ecc7c4fe1939ac42f57c9c664','项目22','26220d63bfd345dabb2f114287965313','medium','admin','admin','2022-02-20 02:53:51','2022-02-20 02:53:51','0');
/*!40000 ALTER TABLE `project_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_of_user`
--

DROP TABLE IF EXISTS `project_of_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_of_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `project_uuid` varchar(32) NOT NULL COMMENT '项目uuid',
  `user_uuid` varchar(32) NOT NULL COMMENT '用户uuid',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='项目关联用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_of_user`
--

LOCK TABLES `project_of_user` WRITE;
/*!40000 ALTER TABLE `project_of_user` DISABLE KEYS */;
INSERT INTO `project_of_user` VALUES (1,'07c697c30f5f4d6280e34a6b1d46af4d','07c697c30f5f4d6280e34a6b1d46af42','9df9de08354f456c97fae0cdb3df214f',NULL,NULL,'2022-02-19 16:54:50','2022-02-19 16:54:55','0');
/*!40000 ALTER TABLE `project_of_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_captcha`
--

DROP TABLE IF EXISTS `sys_captcha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统验证码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_captcha`
--

LOCK TABLES `sys_captcha` WRITE;
/*!40000 ALTER TABLE `sys_captcha` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_captcha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dictionary`
--

DROP TABLE IF EXISTS `sys_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(200) DEFAULT NULL COMMENT '查询的key',
  `value` text COMMENT '字典值',
  `fixed` tinyint(1) DEFAULT '1' COMMENT '是否可更改',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dictionary`
--

LOCK TABLES `sys_dictionary` WRITE;
/*!40000 ALTER TABLE `sys_dictionary` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `execute_time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `occur_time` timestamp NULL DEFAULT NULL COMMENT '发生时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (1,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"creator\":\"admin\",\"deleted\":false,\"gmtCreate\":1638345181000,\"gmtModified\":1638345181000,\"id\":50,\"menuIcon\":\"excel\",\"menuName\":\"表单生成\",\"menuOrder\":0,\"menuParentName\":\"开发工具\",\"menuParentUuid\":\"93f101be493d11ecb7c2543344556776\",\"menuPerms\":\"aaaaa\",\"menuType\":1,\"menuUrl\":\"dev/form\",\"modifier\":\"admin\",\"uuid\":\"4a6ddb331d464050ac87f8c13f57facc\"},\"needsOperator\":false}]',76,'127.0.0.1','2021-12-09 06:42:39'),(2,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"deleted\":false,\"gmtCreate\":1637357736000,\"gmtModified\":1637357736000,\"id\":23,\"menuIcon\":\"list\",\"menuName\":\"菜单管理\",\"menuOrder\":1,\"menuParentName\":\"系统管理\",\"menuParentUuid\":\"93f101be493d11ecb7c254334455677\",\"menuType\":1,\"menuUrl\":\"sys/menu\",\"uuid\":\"93ebbcb2493d11ecb7c254e1ad134d77\"},\"needsOperator\":false}]',26,'127.0.0.1','2021-12-09 06:42:41'),(3,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"deleted\":false,\"gmtCreate\":1638316144000,\"gmtModified\":1638316144000,\"id\":45,\"menuIcon\":\"user\",\"menuName\":\"用户管理\",\"menuOrder\":6,\"menuParentName\":\"系统管理\",\"menuParentUuid\":\"93f101be493d11ecb7c254334455677\",\"menuType\":1,\"menuUrl\":\"sys/user\",\"uuid\":\"0bb9b97e51f511eca3306106f8cc1608\"},\"needsOperator\":false}]',8,'127.0.0.1','2021-12-09 06:42:43'),(4,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"deleted\":false,\"gmtCreate\":1638374380000,\"gmtModified\":1638374380000,\"id\":51,\"menuIcon\":\"documentation\",\"menuName\":\"系统日志\",\"menuOrder\":10,\"menuParentName\":\"系统管理\",\"menuParentUuid\":\"93f101be493d11ecb7c254334455677\",\"menuType\":1,\"menuUrl\":\"sys/log\",\"uuid\":\"a2fd81e4527c11ecb2ccd76ea2e07fd8\"},\"needsOperator\":false}]',15,'127.0.0.1','2021-12-09 06:42:44'),(5,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"deleted\":false,\"gmtCreate\":1637357729000,\"gmtModified\":1637357729000,\"id\":18,\"menuIcon\":\"peoples\",\"menuName\":\"角色管理\",\"menuOrder\":0,\"menuParentName\":\"系统管理\",\"menuParentUuid\":\"93f101be493d11ecb7c254334455677\",\"menuType\":1,\"menuUrl\":\"sys/role\",\"uuid\":\"8fc52771493d11ecb7c254e1ad134d77\"},\"needsOperator\":false}]',9,'127.0.0.1','2021-12-09 06:42:46'),(6,'admin','添加菜单','io.dev.admin.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"menuIcon\":\"skill\",\"menuName\":\"代码生成\",\"menuOrder\":0,\"menuParentName\":\"开发工具\",\"menuParentUuid\":\"93f101be493d11ecb7c2543344556776\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"dev/code\",\"uuid\":\"\"},\"needsOperator\":false}]',7,'127.0.0.1','2021-12-09 06:42:48'),(7,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609644000,\"gmtModified\":1642609644000,\"id\":67,\"menuIcon\":\"component\",\"menuName\":\"应用\",\"menuOrder\":6,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuType\":1,\"menuUrl\":\"/application/app\",\"uuid\":\"a0e7d110790111ec9f0679def01aad17\"},\"needsOperator\":false}]',10,'127.0.0.1','2022-01-19 00:34:53'),(8,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"menuIcon\":\"dashboard\",\"menuName\":\"持续集成\",\"menuOrder\":3,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',10,'127.0.0.1','2022-02-05 23:57:33'),(9,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609627000,\"gmtModified\":1642609627000,\"id\":62,\"menuIcon\":\"component\",\"menuName\":\"应用关联用户\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"/application/applicationofuser\",\"uuid\":\"972bff98790111ec9f0679def01aad17\"},\"needsOperator\":false}]',12,'127.0.0.1','2022-02-05 23:59:21'),(10,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609657000,\"gmtModified\":1642609657000,\"id\":72,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"/application/applicationconfig\",\"uuid\":\"a87ccf3e790111ec9f0679def01aad17\"},\"needsOperator\":false}]',11,'127.0.0.1','2022-02-05 23:59:30'),(11,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609605000,\"gmtModified\":1642609605000,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"/application/pipeline\",\"uuid\":\"89dd6034790111ec9f0679def01aad17\"},\"needsOperator\":false}]',12,'127.0.0.1','2022-02-05 23:59:44'),(12,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609644000,\"gmtModified\":1642609644000,\"id\":67,\"menuIcon\":\"component\",\"menuName\":\"应用\",\"menuOrder\":1,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"application/app\",\"uuid\":\"a0e7d110790111ec9f0679def01aad17\"},\"needsOperator\":false}]',11,'127.0.0.1','2022-02-06 00:03:17'),(13,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"creator\":\"admin\",\"deleted\":false,\"gmtCreate\":1644134253000,\"gmtModified\":1644134253000,\"id\":77,\"menuIcon\":\"dashboard\",\"menuName\":\"持续集成\",\"menuOrder\":2,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"modifier\":\"admin\",\"uuid\":\"1f03c833858643cc8bccfc97ead0d4b3\"},\"needsOperator\":false}]',9,'127.0.0.1','2022-02-06 06:57:24'),(14,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"creator\":\"admin\",\"deleted\":false,\"gmtCreate\":1644134253000,\"gmtModified\":1644134253000,\"id\":77,\"menuIcon\":\"dashboard\",\"menuName\":\"持续集成\",\"menuOrder\":1,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"modifier\":\"admin\",\"uuid\":\"1f03c833858643cc8bccfc97ead0d4b3\"},\"needsOperator\":false}]',12,'127.0.0.1','2022-02-06 06:57:43'),(15,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609605000,\"gmtModified\":1642609605000,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"应用\",\"menuParentUuid\":\"a0e7d110790111ec9f0679def01aad17\",\"menuType\":1,\"menuUrl\":\"application/pipeline\",\"uuid\":\"89dd6034790111ec9f0679def01aad17\"},\"needsOperator\":false}]',7,'127.0.0.1','2022-02-06 06:59:08'),(16,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609605000,\"gmtModified\":1642609605000,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"application/pipeline\",\"uuid\":\"89dd6034790111ec9f0679def01aad17\"},\"needsOperator\":false}]',8,'127.0.0.1','2022-02-06 07:02:06'),(17,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609644000,\"gmtModified\":1642609644000,\"id\":67,\"menuIcon\":\"component\",\"menuName\":\"应用管理\",\"menuOrder\":1,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"application/app\",\"uuid\":\"a0e7d110790111ec9f0679def01aad17\"},\"needsOperator\":false}]',8,'127.0.0.1','2022-02-06 07:37:31'),(18,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609627000,\"gmtModified\":1642609627000,\"id\":62,\"menuIcon\":\"component\",\"menuName\":\"应用用户\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"application/applicationofuser\",\"uuid\":\"972bff98790111ec9f0679def01aad17\"},\"needsOperator\":false}]',6,'127.0.0.1','2022-02-06 07:38:20'),(19,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609605000,\"gmtModified\":1642609605000,\"hide\":true,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"application/pipeline\",\"uuid\":\"89dd6034790111ec9f0679def01aad17\"},\"needsOperator\":false}]',14,'127.0.0.1','2022-02-06 08:11:04'),(20,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609605000,\"gmtModified\":1642609605000,\"hide\":true,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"应用管理\",\"menuParentUuid\":\"a0e7d110790111ec9f0679def01aad17\",\"menuType\":1,\"menuUrl\":\"application/pipeline\",\"uuid\":\"89dd6034790111ec9f0679def01aad17\"},\"needsOperator\":false}]',11,'127.0.0.1','2022-02-06 18:36:03'),(21,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609605000,\"gmtModified\":1642609605000,\"hide\":true,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"application/pipeline\",\"uuid\":\"89dd6034790111ec9f0679def01aad17\"},\"needsOperator\":false}]',21,'127.0.0.1','2022-02-06 18:38:50'),(22,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"table\",\"menuName\":\"基建管理\",\"menuOrder\":3,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',12,'127.0.0.1','2022-02-06 18:59:04'),(23,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"guide\",\"menuName\":\"集群管理\",\"menuOrder\":4,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',9,'127.0.0.1','2022-02-06 19:00:08'),(24,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"password\",\"menuName\":\"zookeeper\",\"menuOrder\":0,\"menuParentName\":\"基建管理\",\"menuParentUuid\":\"4d1b9a3cf79c4637ba9b74a812393544\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"base/zk\",\"uuid\":\"\"},\"needsOperator\":false}]',7,'127.0.0.1','2022-02-06 19:01:39'),(25,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"example\",\"menuName\":\"kubernetes\",\"menuOrder\":0,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"cluster/kubernetes\",\"uuid\":\"\"},\"needsOperator\":false}]',5,'127.0.0.1','2022-02-06 19:04:14'),(26,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609657000,\"gmtModified\":1642609657000,\"hide\":false,\"id\":73,\"menuName\":\"查看\",\"menuOrder\":6,\"menuParentName\":\"应用配置\",\"menuParentUuid\":\"a87ccf3e790111ec9f0679def01aad17\",\"menuPerms\":\"application:applicationconfig:page,application:applicationconfig:info,application:applicationinfo:list\",\"menuType\":2,\"uuid\":\"a8810ac2790111ec9f0679def01aad17\"},\"needsOperator\":false}]',21,'127.0.0.1','2022-02-06 19:36:02'),(27,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"email\",\"menuName\":\"kafka\",\"menuOrder\":0,\"menuParentName\":\"基建管理\",\"menuParentUuid\":\"4d1b9a3cf79c4637ba9b74a812393544\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"base/kafka\",\"uuid\":\"\"},\"needsOperator\":false}]',9,'127.0.0.1','2022-02-06 19:50:04'),(28,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"table\",\"menuName\":\"机器管理\",\"menuOrder\":0,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',8,'127.0.0.1','2022-02-06 19:52:15'),(29,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"list\",\"menuName\":\"机器列表\",\"menuOrder\":0,\"menuParentName\":\"机器管理\",\"menuParentUuid\":\"e70e2d6323f14036b75d409bc8489ca9\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',6,'127.0.0.1','2022-02-06 19:52:54'),(30,'admin','删除菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.delete()','[{\"ids\":[84],\"needsOperator\":false}]',12,'127.0.0.1','2022-02-06 19:53:46'),(31,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"list\",\"menuName\":\"机器列表\",\"menuOrder\":0,\"menuParentName\":\"机器管理\",\"menuParentUuid\":\"e70e2d6323f14036b75d409bc8489ca9\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"server/machine\",\"uuid\":\"\"},\"needsOperator\":false}]',6,'127.0.0.1','2022-02-06 19:54:37'),(32,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1637357736000,\"gmtModified\":1637357736000,\"hide\":false,\"id\":28,\"menuIcon\":\"example\",\"menuName\":\"系统管理\",\"menuOrder\":100,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuType\":0,\"uuid\":\"93f101be493d11ecb7c254334455677\"},\"needsOperator\":false}]',10,'127.0.0.1','2022-02-06 20:00:00'),(33,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"nested\",\"menuName\":\"redis\",\"menuOrder\":0,\"menuParentName\":\"基建管理\",\"menuParentUuid\":\"4d1b9a3cf79c4637ba9b74a812393544\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"base/redis\",\"uuid\":\"\"},\"needsOperator\":false}]',11,'127.0.0.1','2022-02-06 20:09:32'),(34,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"creator\":\"admin\",\"deleted\":false,\"gmtCreate\":1644205804000,\"gmtModified\":1644205804000,\"hide\":false,\"id\":82,\"menuIcon\":\"email\",\"menuName\":\"kafka\",\"menuOrder\":3,\"menuParentName\":\"基建管理\",\"menuParentUuid\":\"4d1b9a3cf79c4637ba9b74a812393544\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"base/kafka\",\"modifier\":\"admin\",\"uuid\":\"3061229ebe99457bba1cad4a144fb090\"},\"needsOperator\":false}]',12,'127.0.0.1','2022-02-07 00:43:58'),(35,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1644257047000,\"gmtModified\":1644257047000,\"hide\":false,\"id\":87,\"menuIcon\":\"component\",\"menuName\":\"服务器节点\",\"menuOrder\":6,\"menuParentName\":\"机器管理\",\"menuParentUuid\":\"e70e2d6323f14036b75d409bc8489ca9\",\"menuType\":1,\"menuUrl\":\"machine\",\"uuid\":\"49c28e6a87fd11ecb10f6cbd6ff47a87\"},\"needsOperator\":false}]',23,'127.0.0.1','2022-02-07 02:04:57'),(36,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"link\",\"menuName\":\"项目协作\",\"menuOrder\":6,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',11,'127.0.0.1','2022-02-08 04:29:18'),(37,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"skill\",\"menuName\":\"知识管理\",\"menuOrder\":7,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',7,'127.0.0.1','2022-02-08 04:29:37'),(38,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"list\",\"menuName\":\"项目列表\",\"menuOrder\":1,\"menuParentName\":\"项目协作\",\"menuParentUuid\":\"f658abddf9924ab7ac8dfdf333a4d9d2\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"item/list\",\"uuid\":\"\"},\"needsOperator\":false}]',10,'127.0.0.1','2022-02-08 04:31:14'),(39,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"clipboard\",\"menuName\":\"我的文档\",\"menuOrder\":1,\"menuParentName\":\"知识管理\",\"menuParentUuid\":\"00d6979bb89a470ba3781428bf374fe9\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"knowledge/doc\",\"uuid\":\"\"},\"needsOperator\":false}]',6,'127.0.0.1','2022-02-08 04:33:33'),(40,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"chart\",\"menuName\":\"测试管理\",\"menuOrder\":5,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',12,'127.0.0.1','2022-02-08 04:39:10'),(41,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"excel\",\"menuName\":\"我的项目\",\"menuOrder\":1,\"menuParentName\":\"测试管理\",\"menuParentUuid\":\"c468ccc0ef814e79b35db9f47c29f4b8\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"test/project\",\"uuid\":\"\"},\"needsOperator\":false}]',7,'127.0.0.1','2022-02-08 04:40:17'),(42,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"documentation\",\"menuName\":\"代码管理\",\"menuOrder\":2,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',15,'127.0.0.1','2022-02-12 07:30:20'),(43,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"star\",\"menuName\":\"我的仓库\",\"menuOrder\":0,\"menuParentName\":\"代码管理\",\"menuParentUuid\":\"0b610bf32d9c41cb97c93cd42d4653a7\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',8,'127.0.0.1','2022-02-12 07:30:56'),(44,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"edit\",\"menuName\":\"在线开发\",\"menuOrder\":0,\"menuParentName\":\"代码管理\",\"menuParentUuid\":\"0b610bf32d9c41cb97c93cd42d4653a7\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',11,'127.0.0.1','2022-02-12 07:31:24'),(45,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"creator\":\"admin\",\"deleted\":false,\"gmtCreate\":1644679856000,\"gmtModified\":1644679856000,\"hide\":false,\"id\":99,\"menuIcon\":\"star\",\"menuName\":\"我的仓库\",\"menuOrder\":0,\"menuParentName\":\"代码管理\",\"menuParentUuid\":\"0b610bf32d9c41cb97c93cd42d4653a7\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"code/repository\",\"modifier\":\"admin\",\"uuid\":\"e3ce81baa4eb442fb3211ba84f69db13\"},\"needsOperator\":false}]',15,'127.0.0.1','2022-02-12 07:33:50'),(46,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"creator\":\"admin\",\"deleted\":false,\"gmtCreate\":1644679884000,\"gmtModified\":1644679884000,\"hide\":false,\"id\":100,\"menuIcon\":\"edit\",\"menuName\":\"在线开发\",\"menuOrder\":0,\"menuParentName\":\"代码管理\",\"menuParentUuid\":\"0b610bf32d9c41cb97c93cd42d4653a7\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"code/ide\",\"modifier\":\"admin\",\"uuid\":\"841161346c96475ea553f205a5e82226\"},\"needsOperator\":false}]',11,'127.0.0.1','2022-02-12 07:34:09'),(47,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1645264948000,\"gmtModified\":1645264948000,\"hide\":true,\"id\":106,\"menuIcon\":\"component\",\"menuName\":\"项目关联用户\",\"menuOrder\":6,\"menuParentName\":\"项目协作\",\"menuParentUuid\":\"f658abddf9924ab7ac8dfdf333a4d9d2\",\"menuType\":1,\"menuUrl\":\"/project/projectofuser\",\"uuid\":\"fd508184912711ec81a9d4aff2ad8cb6\"},\"needsOperator\":false}]',10,'127.0.0.1','2022-02-18 18:15:17'),(48,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"creator\":\"admin\",\"deleted\":false,\"gmtCreate\":1644323358000,\"gmtModified\":1644323358000,\"hide\":false,\"id\":92,\"menuIcon\":\"link\",\"menuName\":\"项目管理\",\"menuOrder\":2,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"modifier\":\"admin\",\"uuid\":\"f658abddf9924ab7ac8dfdf333a4d9d2\"},\"needsOperator\":false}]',14,'127.0.0.1','2022-02-19 19:27:04'),(49,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"creator\":\"admin\",\"deleted\":false,\"gmtCreate\":1644323358000,\"gmtModified\":1644323358000,\"hide\":false,\"id\":92,\"menuIcon\":\"link\",\"menuName\":\"项目管理\",\"menuOrder\":0,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"modifier\":\"admin\",\"uuid\":\"f658abddf9924ab7ac8dfdf333a4d9d2\"},\"needsOperator\":false}]',10,'127.0.0.1','2022-02-19 19:27:25');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT 'uuid',
  `menu_parent_uuid` varchar(32) DEFAULT NULL COMMENT '父菜单uuid',
  `menu_name` varchar(64) DEFAULT NULL COMMENT '菜单名',
  `menu_url` varchar(200) DEFAULT NULL COMMENT '菜单url',
  `menu_perms` varchar(200) DEFAULT NULL COMMENT '授权标识',
  `menu_type` varchar(1) DEFAULT NULL COMMENT '0:目录 1:菜单 2:按钮',
  `menu_icon` varchar(20) DEFAULT NULL COMMENT '图标',
  `menu_order` int(11) DEFAULT NULL COMMENT '排序',
  `menu_remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `hide` tinyint(1) DEFAULT '0' COMMENT '是否隐藏',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_menu_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (8,'87351d5e493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','用户关联角色','sys/userofrole',NULL,'1','icon',6,NULL,0,NULL,'admin','2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(9,'87362493493d11ecb7c254e1ad134d77','87351d5e493d11ecb7c254e1ad134d77','查看',NULL,'sys:userofrole:info,sys:userofrole:page','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(10,'87383f93493d11ecb7c254e1ad134d77','87351d5e493d11ecb7c254e1ad134d77','新增',NULL,'sys:userofrole:add','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(11,'87393320493d11ecb7c254e1ad134d77','87351d5e493d11ecb7c254e1ad134d77','修改',NULL,'sys:userofrole:update','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(12,'873a1d50493d11ecb7c254e1ad134d77','87351d5e493d11ecb7c254e1ad134d77','删除',NULL,'sys:userofrole:delete','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(13,'8b916d0f493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','角色关联菜单','sys/roleofmenu',NULL,'1','icon',6,NULL,0,NULL,'admin','2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(14,'8b93f7dc493d11ecb7c254e1ad134d77','8b916d0f493d11ecb7c254e1ad134d77','查看',NULL,'sys:roleofmenu:info,sys:roleofmenu:page','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(15,'8b94d734493d11ecb7c254e1ad134d77','8b916d0f493d11ecb7c254e1ad134d77','新增',NULL,'sys:roleofmenu:add','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(16,'8b95bc40493d11ecb7c254e1ad134d77','8b916d0f493d11ecb7c254e1ad134d77','修改',NULL,'sys:roleofmenu:update','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(17,'8b96995a493d11ecb7c254e1ad134d77','8b916d0f493d11ecb7c254e1ad134d77','删除',NULL,'sys:roleofmenu:delete','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(18,'8fc52771493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','角色管理','sys/role',NULL,'1','peoples',0,NULL,0,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(19,'8fc6eb1a493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','查看',NULL,'sys:role:info,sys:role:page,sys:role:list','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(20,'8fc89835493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','新增',NULL,'sys:role:add','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(21,'8fc99a2d493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','修改',NULL,'sys:role:update','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(22,'8fcaa825493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','删除',NULL,'sys:role:delete','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(23,'93ebbcb2493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','菜单管理','sys/menu',NULL,'1','list',1,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(24,'93ed48f8493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','查看',NULL,'sys:menu:info,sys:menu:page,sys:menu:list,sys:menu:select','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(25,'93ee29b6493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','新增',NULL,'sys:menu:add','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(26,'93f00a10493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','修改',NULL,'sys:menu:update','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(27,'93f101be493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','删除',NULL,'sys:menu:delete','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(28,'93f101be493d11ecb7c254334455677','0','系统管理',NULL,NULL,'0','example',1000,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(29,'93f101be493d11ecb7c2543344556776','0','开发工具',NULL,NULL,'0','bug',999,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(45,'0bb9b97e51f511eca3306106f8cc1608','93f101be493d11ecb7c254334455677','用户管理','sys/user',NULL,'1','user',6,NULL,0,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(46,'0bbe311651f511eca3306106f8cc1608','0bb9b97e51f511eca3306106f8cc1608','查看',NULL,'sys:user:page,sys:user:info,sys:user:list','2',NULL,6,NULL,0,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(47,'0bbf3e9e51f511eca3306106f8cc1608','0bb9b97e51f511eca3306106f8cc1608','新增',NULL,'sys:user:add','2',NULL,6,NULL,0,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(48,'0bc18ab451f511eca3306106f8cc1608','0bb9b97e51f511eca3306106f8cc1608','修改',NULL,'sys:user:update','2',NULL,6,NULL,0,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(49,'0bc60ce251f511eca3306106f8cc1608','0bb9b97e51f511eca3306106f8cc1608','删除',NULL,'sys:user:delete','2',NULL,6,NULL,0,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(50,'4a6ddb331d464050ac87f8c13f57facc','93f101be493d11ecb7c2543344556776','表单生成','dev/form','aaaaa','1','excel',0,NULL,0,'admin','admin','2021-12-01 07:53:01','2021-12-01 07:53:01','0'),(51,'a2fd81e4527c11ecb2ccd76ea2e07fd8','93f101be493d11ecb7c254334455677','系统日志','sys/log',NULL,'1','documentation',10,NULL,0,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(52,'a329b368527c11ecb2ccd76ea2e07fd8','a2fd81e4527c11ecb2ccd76ea2e07fd8','查看',NULL,'sys:log:page,sys:log:info','2',NULL,6,NULL,0,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(53,'a32e88ca527c11ecb2ccd76ea2e07fd8','a2fd81e4527c11ecb2ccd76ea2e07fd8','新增',NULL,'sys:log:save','2',NULL,6,NULL,0,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(54,'a330ec3c527c11ecb2ccd76ea2e07fd8','a2fd81e4527c11ecb2ccd76ea2e07fd8','修改',NULL,'sys:log:update','2',NULL,6,NULL,0,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(55,'a3332a60527c11ecb2ccd76ea2e07fd8','a2fd81e4527c11ecb2ccd76ea2e07fd8','删除',NULL,'sys:log:delete','2',NULL,6,NULL,0,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(56,'8331d5f0900444109edc6cd83e54032e','93f101be493d11ecb7c2543344556776','代码生成','dev/code','','1','skill',0,NULL,0,'admin','admin','2021-12-09 06:21:28','2021-12-09 06:21:28','0'),(57,'89dd6034790111ec9f0679def01aad17','1f03c833858643cc8bccfc97ead0d4b3','流水线','application/pipeline',NULL,'1','component',6,NULL,1,NULL,NULL,'2022-01-19 16:26:45','2022-01-19 16:26:45','0'),(58,'89e61116790111ec9f0679def01aad17','89dd6034790111ec9f0679def01aad17','查看',NULL,'application:applicationpipeline:page,application:applicationpipeline:info','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:26:45','2022-01-19 16:26:45','0'),(59,'89eca53a790111ec9f0679def01aad17','89dd6034790111ec9f0679def01aad17','新增',NULL,'application:applicationpipeline:add','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:26:45','2022-01-19 16:26:45','0'),(60,'89f10ae4790111ec9f0679def01aad17','89dd6034790111ec9f0679def01aad17','修改',NULL,'application:applicationpipeline:update','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:26:45','2022-01-19 16:26:45','0'),(61,'89f4eff6790111ec9f0679def01aad17','89dd6034790111ec9f0679def01aad17','删除',NULL,'application:applicationpipeline:delete','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:26:45','2022-01-19 16:26:45','0'),(62,'972bff98790111ec9f0679def01aad17','1f03c833858643cc8bccfc97ead0d4b3','应用用户','application/applicationofuser',NULL,'1','component',6,NULL,1,NULL,NULL,'2022-01-19 16:27:07','2022-01-19 16:27:07','0'),(63,'972f478e790111ec9f0679def01aad17','972bff98790111ec9f0679def01aad17','查看',NULL,'application:applicationofuser:page,application:applicationofuser:info','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:08','2022-01-19 16:27:08','0'),(64,'97332390790111ec9f0679def01aad17','972bff98790111ec9f0679def01aad17','新增',NULL,'application:applicationofuser:add','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:08','2022-01-19 16:27:08','0'),(65,'9737e678790111ec9f0679def01aad17','972bff98790111ec9f0679def01aad17','修改',NULL,'application:applicationofuser:update','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:08','2022-01-19 16:27:08','0'),(66,'973cb9fa790111ec9f0679def01aad17','972bff98790111ec9f0679def01aad17','删除',NULL,'application:applicationofuser:delete','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:08','2022-01-19 16:27:08','0'),(67,'a0e7d110790111ec9f0679def01aad17','1f03c833858643cc8bccfc97ead0d4b3','应用管理','application/app',NULL,'1','component',1,NULL,0,NULL,NULL,'2022-01-19 16:27:24','2022-01-19 16:27:24','0'),(68,'a0ec3764790111ec9f0679def01aad17','a0e7d110790111ec9f0679def01aad17','查看',NULL,'application:applicationinfo:page,application:applicationinfo:info','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:24','2022-01-19 16:27:24','0'),(69,'a0f02914790111ec9f0679def01aad17','a0e7d110790111ec9f0679def01aad17','新增',NULL,'application:applicationinfo:add','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:24','2022-01-19 16:27:24','0'),(70,'a0f452aa790111ec9f0679def01aad17','a0e7d110790111ec9f0679def01aad17','修改',NULL,'application:applicationinfo:update','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:24','2022-01-19 16:27:24','0'),(71,'a0f87e98790111ec9f0679def01aad17','a0e7d110790111ec9f0679def01aad17','删除',NULL,'application:applicationinfo:delete','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:24','2022-01-19 16:27:24','0'),(72,'a87ccf3e790111ec9f0679def01aad17','1f03c833858643cc8bccfc97ead0d4b3','应用配置','application/applicationconfig',NULL,'1','component',6,NULL,1,NULL,NULL,'2022-01-19 16:27:37','2022-01-19 16:27:37','0'),(73,'a8810ac2790111ec9f0679def01aad17','a87ccf3e790111ec9f0679def01aad17','查看',NULL,'application:applicationconfig:page,application:applicationconfig:info,application:applicationinfo:list','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:37','2022-01-19 16:27:37','0'),(74,'a8847c84790111ec9f0679def01aad17','a87ccf3e790111ec9f0679def01aad17','新增',NULL,'application:applicationconfig:add','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:37','2022-01-19 16:27:37','0'),(75,'a8883e8c790111ec9f0679def01aad17','a87ccf3e790111ec9f0679def01aad17','修改',NULL,'application:applicationconfig:update','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:37','2022-01-19 16:27:37','0'),(76,'a88d2212790111ec9f0679def01aad17','a87ccf3e790111ec9f0679def01aad17','删除',NULL,'application:applicationconfig:delete','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:37','2022-01-19 16:27:37','0'),(77,'1f03c833858643cc8bccfc97ead0d4b3','0','持续集成','','','0','dashboard',1,NULL,0,'admin','admin','2022-02-06 07:57:33','2022-02-06 07:57:33','0'),(78,'4d1b9a3cf79c4637ba9b74a812393544','0','基建管理','','','0','table',3,NULL,0,'admin','admin','2022-02-07 02:59:04','2022-02-07 02:59:04','0'),(79,'0e78eeeb6043422b82a472fe5c008291','0','集群管理','','','0','guide',4,NULL,0,'admin','admin','2022-02-07 03:00:08','2022-02-07 03:00:08','0'),(80,'434074f1392c4abe9143bb7d1bba7c2e','4d1b9a3cf79c4637ba9b74a812393544','zk','base/zk','','1','password',0,NULL,0,'admin','admin','2022-02-07 03:01:39','2022-02-07 03:01:39','0'),(81,'efdbdd6b7a1e4b74be715257290ac79a','0e78eeeb6043422b82a472fe5c008291','kubernetes','cluster/kubernetes','','1','example',0,NULL,0,'admin','admin','2022-02-07 03:04:14','2022-02-07 03:04:14','0'),(82,'3061229ebe99457bba1cad4a144fb090','4d1b9a3cf79c4637ba9b74a812393544','kafka','base/kafka','','1','email',3,NULL,0,'admin','admin','2022-02-07 03:50:04','2022-02-07 03:50:04','0'),(83,'e70e2d6323f14036b75d409bc8489ca9','0','机器管理','','','0','table',0,NULL,0,'admin','admin','2022-02-07 03:52:15','2022-02-07 03:52:15','0'),(86,'fccad4fa95a04f4abe1d0824fb0c8c51','4d1b9a3cf79c4637ba9b74a812393544','redis','base/redis','','1','nested',0,NULL,0,'admin','admin','2022-02-07 04:09:32','2022-02-07 04:09:32','0'),(87,'49c28e6a87fd11ecb10f6cbd6ff47a87','e70e2d6323f14036b75d409bc8489ca9','机器列表','machine',NULL,'1','component',6,NULL,0,NULL,NULL,'2022-02-07 18:04:07','2022-02-07 18:04:07','0'),(88,'49c6f3b087fd11ecb10f6cbd6ff47a87','49c28e6a87fd11ecb10f6cbd6ff47a87','查看',NULL,'application:machineinfo:page,application:machineinfo:info,application:machineinfo:list','2',NULL,6,NULL,0,NULL,NULL,'2022-02-07 18:04:07','2022-02-07 18:04:07','0'),(89,'49cbce3087fd11ecb10f6cbd6ff47a87','49c28e6a87fd11ecb10f6cbd6ff47a87','新增',NULL,'application:machineinfo:add','2',NULL,6,NULL,0,NULL,NULL,'2022-02-07 18:04:07','2022-02-07 18:04:07','0'),(90,'49cf4c3687fd11ecb10f6cbd6ff47a87','49c28e6a87fd11ecb10f6cbd6ff47a87','修改',NULL,'application:machineinfo:update','2',NULL,6,NULL,0,NULL,NULL,'2022-02-07 18:04:07','2022-02-07 18:04:07','0'),(91,'49d36e9c87fd11ecb10f6cbd6ff47a87','49c28e6a87fd11ecb10f6cbd6ff47a87','删除',NULL,'application:machineinfo:delete','2',NULL,6,NULL,0,NULL,NULL,'2022-02-07 18:04:07','2022-02-07 18:04:07','0'),(92,'f658abddf9924ab7ac8dfdf333a4d9d2','0','项目管理','','','0','link',0,NULL,0,'admin','admin','2022-02-08 12:29:18','2022-02-08 12:29:18','0'),(93,'00d6979bb89a470ba3781428bf374fe9','0','知识管理','','','0','skill',7,NULL,0,'admin','admin','2022-02-08 12:29:37','2022-02-08 12:29:37','0'),(94,'4b3903aaf889464abe0c1cd70f79f7e0','f658abddf9924ab7ac8dfdf333a4d9d2','项目列表','project','','1','list',1,NULL,0,'admin','admin','2022-02-08 12:31:14','2022-02-08 12:31:14','0'),(95,'a27be66741d0425996b43d9146362372','00d6979bb89a470ba3781428bf374fe9','我的文档','knowledge/doc','','1','clipboard',1,NULL,0,'admin','admin','2022-02-08 12:33:33','2022-02-08 12:33:33','0'),(96,'c468ccc0ef814e79b35db9f47c29f4b8','0','测试管理','','','0','chart',5,NULL,0,'admin','admin','2022-02-08 12:39:10','2022-02-08 12:39:10','0'),(97,'bf9739f4e31a4a58848462f05e7d7df9','c468ccc0ef814e79b35db9f47c29f4b8','我的项目','test/project','','1','excel',1,NULL,0,'admin','admin','2022-02-08 12:40:17','2022-02-08 12:40:17','0'),(98,'0b610bf32d9c41cb97c93cd42d4653a7','0','代码管理','','','0','documentation',2,NULL,0,'admin','admin','2022-02-12 15:30:20','2022-02-12 15:30:20','0'),(99,'e3ce81baa4eb442fb3211ba84f69db13','0b610bf32d9c41cb97c93cd42d4653a7','我的仓库','code/repository','','1','star',0,NULL,0,'admin','admin','2022-02-12 15:30:56','2022-02-12 15:30:56','0'),(100,'841161346c96475ea553f205a5e82226','0b610bf32d9c41cb97c93cd42d4653a7','在线开发','code/ide','','1','edit',0,NULL,0,'admin','admin','2022-02-12 15:31:24','2022-02-12 15:31:24','0'),(102,'0b43314490cc11ec81a9d4aff2ad8cb6','f658abddf9924ab7ac8dfdf333a4d9d2','查看',NULL,'project:projectinfo:page,project:projectinfo:info,project:projectinfo:list','2',NULL,6,NULL,0,NULL,NULL,'2022-02-18 23:04:17','2022-02-18 23:04:17','0'),(103,'0b475af890cc11ec81a9d4aff2ad8cb6','f658abddf9924ab7ac8dfdf333a4d9d2','新增',NULL,'project:projectinfo:add','2',NULL,6,NULL,0,NULL,NULL,'2022-02-18 23:04:17','2022-02-18 23:04:17','0'),(104,'0b4b90be90cc11ec81a9d4aff2ad8cb6','f658abddf9924ab7ac8dfdf333a4d9d2','修改',NULL,'project:projectinfo:update','2',NULL,6,NULL,0,NULL,NULL,'2022-02-18 23:04:17','2022-02-18 23:04:17','0'),(105,'0b50348490cc11ec81a9d4aff2ad8cb6','f658abddf9924ab7ac8dfdf333a4d9d2','删除',NULL,'project:projectinfo:delete','2',NULL,6,NULL,0,NULL,NULL,'2022-02-18 23:04:17','2022-02-18 23:04:17','0'),(106,'fd508184912711ec81a9d4aff2ad8cb6','f658abddf9924ab7ac8dfdf333a4d9d2','项目关联用户','/project/projectofuser',NULL,'1','component',6,NULL,1,NULL,NULL,'2022-02-19 10:02:28','2022-02-19 10:02:28','0'),(107,'fd55fd12912711ec81a9d4aff2ad8cb6','fd508184912711ec81a9d4aff2ad8cb6','查看',NULL,'project:projectofuser:page,project:projectofuser:info,project:projectofuser:list','2',NULL,6,NULL,0,NULL,NULL,'2022-02-19 10:02:28','2022-02-19 10:02:28','0'),(108,'fd5aa4fc912711ec81a9d4aff2ad8cb6','fd508184912711ec81a9d4aff2ad8cb6','新增',NULL,'project:projectofuser:add','2',NULL,6,NULL,0,NULL,NULL,'2022-02-19 10:02:28','2022-02-19 10:02:28','0'),(109,'fd5fcf90912711ec81a9d4aff2ad8cb6','fd508184912711ec81a9d4aff2ad8cb6','修改',NULL,'project:projectofuser:update','2',NULL,6,NULL,0,NULL,NULL,'2022-02-19 10:02:28','2022-02-19 10:02:28','0'),(110,'fd63791a912711ec81a9d4aff2ad8cb6','fd508184912711ec81a9d4aff2ad8cb6','删除',NULL,'project:projectofuser:delete','2',NULL,6,NULL,0,NULL,NULL,'2022-02-19 10:02:28','2022-02-19 10:02:28','0');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT 'uuid',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名',
  `role_remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'93f101be493d11ecb7c254e1ad134d77','管理员','管理员','SYS','SYS','2021-11-19 13:51:37','2021-11-19 13:51:39','0'),(2,'0caadea8ae91427eb3d869df43d0fe41','开发人员','开发人员','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_of_menu`
--

DROP TABLE IF EXISTS `sys_role_of_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_of_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `role_uuid` varchar(32) DEFAULT NULL COMMENT '角色UUID',
  `menu_uuid` varchar(32) DEFAULT NULL COMMENT '菜单UUID',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1075 DEFAULT CHARSET=utf8mb4 COMMENT='角色关联菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_of_menu`
--

LOCK TABLES `sys_role_of_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_of_menu` DISABLE KEYS */;
INSERT INTO `sys_role_of_menu` VALUES (85,'edc36fbe44b346309633f6a63540eeaa','0caadea8ae91427eb3d869df43d0fe41','93f101be493d11ecb7c254334455677','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(86,'341f066eb9b74c3ca91525eb51ff3349','0caadea8ae91427eb3d869df43d0fe41','8fc52771493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(87,'8382665c44704e2ba00e5716e530350f','0caadea8ae91427eb3d869df43d0fe41','8fc6eb1a493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(88,'7582c9a49261487891bb3d357c20369a','0caadea8ae91427eb3d869df43d0fe41','8fc89835493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(89,'6e50c43e8a934bd78116d7dafe763fd3','0caadea8ae91427eb3d869df43d0fe41','8fc99a2d493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(90,'353de15fd3b942238e24b067c43a64e5','0caadea8ae91427eb3d869df43d0fe41','8fcaa825493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(91,'32be576f04d54f9a8d0723b66991d259','0caadea8ae91427eb3d869df43d0fe41','93ebbcb2493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(92,'00935ece12a248dda6f4c45cb8d78548','0caadea8ae91427eb3d869df43d0fe41','93ed48f8493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(93,'ab645494c8334636bcc31d4011ab5d27','0caadea8ae91427eb3d869df43d0fe41','93ee29b6493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(94,'31d1250e836449f484826b8fee0b91db','0caadea8ae91427eb3d869df43d0fe41','93f00a10493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(95,'1de65d702d734f9fb53591f1f71e5c35','0caadea8ae91427eb3d869df43d0fe41','93f101be493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(96,'72af74fd48f84871a3daab03a187eda0','0caadea8ae91427eb3d869df43d0fe41','0bb9b97e51f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(97,'b69b420baf4a4ecfb022dd6182337058','0caadea8ae91427eb3d869df43d0fe41','0bbe311651f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(98,'f50c633ba6034537895f074d9e53ef1f','0caadea8ae91427eb3d869df43d0fe41','0bbf3e9e51f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(99,'747994f0108c48e2bb700f755a3ec9dd','0caadea8ae91427eb3d869df43d0fe41','0bc18ab451f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(100,'fab2d2a63f8f43ff873fd6c6c75c932d','0caadea8ae91427eb3d869df43d0fe41','0bc60ce251f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(101,'850daeaac10b4cca89efe67673d61580','0caadea8ae91427eb3d869df43d0fe41','a2fd81e4527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(102,'ffe45ebe32ed4cb48a0e0108cc5d713b','0caadea8ae91427eb3d869df43d0fe41','a329b368527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(103,'3207f4b395d6439eba466e4ded9b01ed','0caadea8ae91427eb3d869df43d0fe41','a32e88ca527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(104,'33d27be81f68459e86adc90f9f493c3e','0caadea8ae91427eb3d869df43d0fe41','a330ec3c527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(105,'eb951d36ebab4cfab0b7c446856900c8','0caadea8ae91427eb3d869df43d0fe41','a3332a60527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(107,'cdd7be6839c141118c7374d237e08445','0caadea8ae91427eb3d869df43d0fe41','638a1e2c527d11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(108,'a5e2ea99983d4f109e825ca88c4c58f0','0caadea8ae91427eb3d869df43d0fe41','638b5742527d11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(109,'a6e057847282429f82478c989b9138e4','0caadea8ae91427eb3d869df43d0fe41','638c3d9c527d11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(110,'5cdf81bcf80041f4a735aefb55b45d66','0caadea8ae91427eb3d869df43d0fe41','638d62b2527d11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(111,'81ac9f6f388741a398d70f7f0ac48b85','0caadea8ae91427eb3d869df43d0fe41','93f101be493d11ecb7c2543344556776','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(112,'22d909855d374be69c473da2bf71eb38','0caadea8ae91427eb3d869df43d0fe41','4a6ddb331d464050ac87f8c13f57facc','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(1000,'3698b26140da4ebbadecc47c0cc049d8','93f101be493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1001,'ca5cee87f536431b9bc4dea1f6272b18','93f101be493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1002,'1ed7433640c94f34a34c04aca558980f','93f101be493d11ecb7c254e1ad134d77','8fc6eb1a493d11ecb7c254e1ad134d77','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1003,'abd4a5a5b5ee44978723145ffdeef3c4','93f101be493d11ecb7c254e1ad134d77','8fc89835493d11ecb7c254e1ad134d77','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1004,'2a19634370704fe5bf70acbfaab380e5','93f101be493d11ecb7c254e1ad134d77','8fc99a2d493d11ecb7c254e1ad134d77','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1005,'31d7ebdf72f64462925ad0f132d1165b','93f101be493d11ecb7c254e1ad134d77','8fcaa825493d11ecb7c254e1ad134d77','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1006,'a906064728a2449abc9872a91c3ec7f1','93f101be493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1007,'a1a9854d0ef143d09e6fce0da7020b28','93f101be493d11ecb7c254e1ad134d77','93ed48f8493d11ecb7c254e1ad134d77','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1008,'678e35f257904f02af4c2deecacf980b','93f101be493d11ecb7c254e1ad134d77','93ee29b6493d11ecb7c254e1ad134d77','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1009,'b2a741c2e501495eaae84d3692efd45d','93f101be493d11ecb7c254e1ad134d77','93f00a10493d11ecb7c254e1ad134d77','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1010,'b9991baf18b1478680193a76dbebea9b','93f101be493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254e1ad134d77','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1011,'bfc242243196417081672f7799e10857','93f101be493d11ecb7c254e1ad134d77','0bb9b97e51f511eca3306106f8cc1608','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1012,'ffd33bd228064f74b0c14546fbc67ab2','93f101be493d11ecb7c254e1ad134d77','0bbe311651f511eca3306106f8cc1608','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1013,'2615ef29654a4ba2be9926d883c6e6c2','93f101be493d11ecb7c254e1ad134d77','0bbf3e9e51f511eca3306106f8cc1608','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1014,'9ab8f62ef0f741458a064ff0e221dd71','93f101be493d11ecb7c254e1ad134d77','0bc18ab451f511eca3306106f8cc1608','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1015,'4152e9c954a8495398d5ff95c8f0ab0e','93f101be493d11ecb7c254e1ad134d77','0bc60ce251f511eca3306106f8cc1608','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1016,'9a1b31af8bb442b4ac9fb321ab402482','93f101be493d11ecb7c254e1ad134d77','a2fd81e4527c11ecb2ccd76ea2e07fd8','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1017,'b438bee9a5d74c9ca8924f1cca2245bd','93f101be493d11ecb7c254e1ad134d77','a329b368527c11ecb2ccd76ea2e07fd8','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1018,'a075dc49c02241aba2f3e720a31426c9','93f101be493d11ecb7c254e1ad134d77','a32e88ca527c11ecb2ccd76ea2e07fd8','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1019,'4582c97b3612424692bdd390b8847caa','93f101be493d11ecb7c254e1ad134d77','a330ec3c527c11ecb2ccd76ea2e07fd8','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1020,'159dde6ecf4b47919bfcc682053bfecf','93f101be493d11ecb7c254e1ad134d77','a3332a60527c11ecb2ccd76ea2e07fd8','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1021,'13279c8cada04195b505991106b41355','93f101be493d11ecb7c254e1ad134d77','93f101be493d11ecb7c2543344556776','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1022,'e7b8be76163a4e77a94308f71810742a','93f101be493d11ecb7c254e1ad134d77','4a6ddb331d464050ac87f8c13f57facc','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1023,'c13075d10c2742678ec45a45420391eb','93f101be493d11ecb7c254e1ad134d77','8331d5f0900444109edc6cd83e54032e','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1024,'f5fbb3241af54283ad64c84aa73ab7d7','93f101be493d11ecb7c254e1ad134d77','1f03c833858643cc8bccfc97ead0d4b3','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1025,'7da7cdfdf430492ca75c210e0327d0f2','93f101be493d11ecb7c254e1ad134d77','89dd6034790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1026,'bfcf55d693da47a2a199ad3318f3cb25','93f101be493d11ecb7c254e1ad134d77','89e61116790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1027,'4168f950b2e94acfa612d55f61c224aa','93f101be493d11ecb7c254e1ad134d77','89eca53a790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1028,'6fa5dbd93b2c4d348dba7fa2e40dee11','93f101be493d11ecb7c254e1ad134d77','89f10ae4790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1029,'6588c1c3e36b4215b6320565c65dbb74','93f101be493d11ecb7c254e1ad134d77','89f4eff6790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1030,'d2343e15647d4a648ee27679d1130ed0','93f101be493d11ecb7c254e1ad134d77','972bff98790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1031,'272e54ba4c754bf08dc54d1424250fef','93f101be493d11ecb7c254e1ad134d77','972f478e790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1032,'082bc0f8cf1845df9b93397bc7298d7b','93f101be493d11ecb7c254e1ad134d77','97332390790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1033,'8174a62699404e3babaa443d7ff2c640','93f101be493d11ecb7c254e1ad134d77','9737e678790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1034,'50d8e48456b644d1823eac438f37237c','93f101be493d11ecb7c254e1ad134d77','973cb9fa790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1035,'3aab2fc10bba4af192830ec417cfceb3','93f101be493d11ecb7c254e1ad134d77','a0e7d110790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1036,'3f845861cb7c4a5b8839d010285d1f7c','93f101be493d11ecb7c254e1ad134d77','a0ec3764790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1037,'785ef332e8024865877284617ad14685','93f101be493d11ecb7c254e1ad134d77','a0f02914790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1038,'70307b0665704502bffc5fa5c978aa1f','93f101be493d11ecb7c254e1ad134d77','a0f452aa790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1039,'272ead23bac04ecb91606f7bac824374','93f101be493d11ecb7c254e1ad134d77','a0f87e98790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1040,'0a3d53f6a1cf45b19c5a4f3ab9c651f7','93f101be493d11ecb7c254e1ad134d77','a87ccf3e790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1041,'86740522b3b14fd18549d23cf4953106','93f101be493d11ecb7c254e1ad134d77','a8810ac2790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1042,'6a9c55ae35a943c390695236ae5b0c86','93f101be493d11ecb7c254e1ad134d77','a8847c84790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1043,'1db3cb69301740d9acf4f5d6cf70b177','93f101be493d11ecb7c254e1ad134d77','a8883e8c790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1044,'0800c8872d2f41cfabb3f63570995574','93f101be493d11ecb7c254e1ad134d77','a88d2212790111ec9f0679def01aad17','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1045,'9100b5ef10054f119d48fbb5cf563043','93f101be493d11ecb7c254e1ad134d77','4d1b9a3cf79c4637ba9b74a812393544','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1046,'b489f483ff1c44a19dfbd0bc0b74731b','93f101be493d11ecb7c254e1ad134d77','434074f1392c4abe9143bb7d1bba7c2e','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1047,'1fa5e4d8ff4e408e9624676d2a38a7cb','93f101be493d11ecb7c254e1ad134d77','3061229ebe99457bba1cad4a144fb090','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1048,'2c277439e05e4d9dadcba8a80f58f168','93f101be493d11ecb7c254e1ad134d77','fccad4fa95a04f4abe1d0824fb0c8c51','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1049,'d4e1838a3efd45b9a50a3a86248de233','93f101be493d11ecb7c254e1ad134d77','0e78eeeb6043422b82a472fe5c008291','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1050,'063d1105d6844c939479bf44a8d3236e','93f101be493d11ecb7c254e1ad134d77','efdbdd6b7a1e4b74be715257290ac79a','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1051,'6d476179dc8543bea3f05164be24b32f','93f101be493d11ecb7c254e1ad134d77','e70e2d6323f14036b75d409bc8489ca9','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1052,'b92e1d6d2e8447ba99d655b7f12aa3cf','93f101be493d11ecb7c254e1ad134d77','49c28e6a87fd11ecb10f6cbd6ff47a87','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1053,'bcd8768c8d1044149f7b213bd7ec80de','93f101be493d11ecb7c254e1ad134d77','49c6f3b087fd11ecb10f6cbd6ff47a87','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1054,'74fc309ddc8f45358a0b2434dc26b46e','93f101be493d11ecb7c254e1ad134d77','49cbce3087fd11ecb10f6cbd6ff47a87','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1055,'e692621fc8f24bf095a674cd9261dbd8','93f101be493d11ecb7c254e1ad134d77','49cf4c3687fd11ecb10f6cbd6ff47a87','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1056,'27c7fa59716244cea8b86d3877890201','93f101be493d11ecb7c254e1ad134d77','49d36e9c87fd11ecb10f6cbd6ff47a87','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1057,'9a0cf0e8a5bf4e1fa10b07bb17b3b23f','93f101be493d11ecb7c254e1ad134d77','f658abddf9924ab7ac8dfdf333a4d9d2','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1058,'bf10eb90b3834d908fbffdb6ac6fc322','93f101be493d11ecb7c254e1ad134d77','4b3903aaf889464abe0c1cd70f79f7e0','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1059,'0f0a99f5af604c578ea1906e7e903a8c','93f101be493d11ecb7c254e1ad134d77','0b43314490cc11ec81a9d4aff2ad8cb6','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1060,'78759ebfde0b4617b7db4176cc26e361','93f101be493d11ecb7c254e1ad134d77','0b475af890cc11ec81a9d4aff2ad8cb6','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1061,'b8affebb5e8147758c9a5850405b355f','93f101be493d11ecb7c254e1ad134d77','0b4b90be90cc11ec81a9d4aff2ad8cb6','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1062,'306da9bd9c86414eb8b700fe1bd6c70b','93f101be493d11ecb7c254e1ad134d77','0b50348490cc11ec81a9d4aff2ad8cb6','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1063,'acd47712b3904cb7a7a6e3a67853940e','93f101be493d11ecb7c254e1ad134d77','fd508184912711ec81a9d4aff2ad8cb6','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1064,'af760896fc464461ae720a32e2ca7043','93f101be493d11ecb7c254e1ad134d77','fd55fd12912711ec81a9d4aff2ad8cb6','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1065,'d2157b2dc7df48a9b9b622d0a14a142f','93f101be493d11ecb7c254e1ad134d77','fd5aa4fc912711ec81a9d4aff2ad8cb6','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1066,'4e08d463edee4bb4a543b9436e1a7f2e','93f101be493d11ecb7c254e1ad134d77','fd5fcf90912711ec81a9d4aff2ad8cb6','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1067,'4fd2d71793524204b9dd9602bcdb2409','93f101be493d11ecb7c254e1ad134d77','fd63791a912711ec81a9d4aff2ad8cb6','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1068,'abc78dd5d2ac4369838e228a8bae2a6c','93f101be493d11ecb7c254e1ad134d77','00d6979bb89a470ba3781428bf374fe9','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1069,'77942d198a9047bb8f637ab63aaa66e3','93f101be493d11ecb7c254e1ad134d77','a27be66741d0425996b43d9146362372','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1070,'971abf76a47b48ba9b4d5f4b5356a16c','93f101be493d11ecb7c254e1ad134d77','c468ccc0ef814e79b35db9f47c29f4b8','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1071,'88d826d29a8043eaac5410dfc45638b9','93f101be493d11ecb7c254e1ad134d77','bf9739f4e31a4a58848462f05e7d7df9','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1072,'343fdbefca8e4c6a843cdb93806b5b6d','93f101be493d11ecb7c254e1ad134d77','0b610bf32d9c41cb97c93cd42d4653a7','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1073,'58eaea91d7e0424d8ff7a994529f7c93','93f101be493d11ecb7c254e1ad134d77','e3ce81baa4eb442fb3211ba84f69db13','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0'),(1074,'f4e76cc6bf9a4099a7fed88a85862a6f','93f101be493d11ecb7c254e1ad134d77','841161346c96475ea553f205a5e82226','admin','admin','2022-02-19 02:15:28','2022-02-19 02:15:28','0');
/*!40000 ALTER TABLE `sys_role_of_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `user_id` varchar(64) DEFAULT NULL COMMENT '账号',
  `user_password` varchar(64) DEFAULT NULL COMMENT '密码',
  `user_name` varchar(64) DEFAULT NULL COMMENT '名字',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `status` int(1) DEFAULT '0' COMMENT '角色',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1000,'9df9de08354f456c97fae0cdb3df214f','admin','$2a$10$7CQ/9w8BucRDnS3/cG2lXOA3y.9eEJUY6pidZGiNFQCU5Vu2q6ZwO','admin','devcloudadmin@qq.com',0,NULL,'admin','2021-11-12 22:48:15','2021-11-12 22:48:15','0',NULL),(1002,'26220d63bfd345dabb2f114287965313','mikey','$2a$10$ZnuPGCEusk5tiKxAB/1lreynJxuvh4mqu8So6vUok/PCBCYP34.gi','mikey','mikey',0,NULL,NULL,'2021-12-01 09:39:53','2021-12-01 09:39:53','0',NULL),(1003,'b8c1e673060c437ba0925f119538bdb6','fasf','$2a$10$ILTzdnK9HtI4BiTXneTWOOgiLid.QHTuDK0iV42EvH142xhuSheqy','fasdf','asfd@qq.com',0,'mikey','admin','2021-12-01 09:46:07','2021-12-01 09:46:07','1',NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_of_role`
--

DROP TABLE IF EXISTS `sys_user_of_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_of_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `user_uuid` varchar(32) DEFAULT NULL COMMENT '用户UUID',
  `role_uuid` varchar(32) DEFAULT NULL COMMENT '角色UUID',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='用户关联角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_of_role`
--

LOCK TABLES `sys_user_of_role` WRITE;
/*!40000 ALTER TABLE `sys_user_of_role` DISABLE KEYS */;
INSERT INTO `sys_user_of_role` VALUES (1,'93f101be493d11ecb7c254e1ad134d78','9df9de08354f456c97fae0cdb3df214f','93f101be493d11ecb7c254e1ad134d77','SYS','SYS','2021-11-19 13:52:12','2021-11-19 13:52:13','0'),(4,'dbd90a6a2c824d6284651e609aed8d80','b8c1e673060c437ba0925f119538bdb6','93f101be493d11ecb7c254e1ad134d77','mikey','mikey','2021-12-01 09:46:07','2021-12-01 09:46:07','0'),(5,'5a157d23f39c435faffc36c81758d660','b8c1e673060c437ba0925f119538bdb6','0caadea8ae91427eb3d869df43d0fe41','mikey','mikey','2021-12-01 09:46:07','2021-12-01 09:46:07','0'),(12,'9f35f44349074d05b476257e140978f4','b8c1e673060c437ba0925f119538bdb6','0caadea8ae91427eb3d869df43d0fe41','mikey','mikey','2021-12-02 03:42:46','2021-12-02 03:42:46','0'),(13,'8a9551b14bb347cd9f7112a4928848bb','b8c1e673060c437ba0925f119538bdb6','0caadea8ae91427eb3d869df43d0fe41','mikey','mikey','2021-12-02 03:42:57','2021-12-02 03:42:57','0'),(14,'2b2f93dbdee74266b4db6cf633d2d49a','26220d63bfd345dabb2f114287965313','0caadea8ae91427eb3d869df43d0fe41','mikey','mikey','2021-12-02 03:49:31','2021-12-02 03:49:31','0');
/*!40000 ALTER TABLE `sys_user_of_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-20 14:09:27
