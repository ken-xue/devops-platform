-- MySQL dump 10.13  Distrib 5.7.28, for macos10.14 (x86_64)
--
-- Host: 116.198.160.39    Database: cicd-platform
-- ------------------------------------------------------
-- Server version	8.0.27

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
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `application_uuid` varchar(32) NOT NULL COMMENT '应用uuid',
  `config_context` text NOT NULL COMMENT '应用配置',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='流水线';
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
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `application_name` varchar(100) NOT NULL COMMENT '应用名称',
  `application_type` varchar(100) NOT NULL COMMENT '应用类型',
  `repository` varchar(200) DEFAULT NULL COMMENT '代码仓库',
  `access_key` varchar(255) DEFAULT NULL COMMENT '访问密钥',
  `access_way` varchar(20) DEFAULT NULL COMMENT '访问方式',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `application_info_application_name_uindex` (`application_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COMMENT='应用';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_info`
--

LOCK TABLES `application_info` WRITE;
/*!40000 ALTER TABLE `application_info` DISABLE KEYS */;
INSERT INTO `application_info` VALUES (1,'1e635672d5524c96ae85dc8c0ae153a5','app1','Java',NULL,NULL,NULL,'super_admin','super_admin','2021-12-28 14:38:57','2021-12-28 14:38:57','0'),(2,'dc23660d50624981ae346ee77a04d8d3','app2','Go',NULL,NULL,NULL,'super_admin','super_admin','2021-12-28 14:46:36','2021-12-28 14:46:36','0'),(4,'3040264d22e74d88abdd8c893218a1db','app3','NodeJS',NULL,NULL,NULL,'super_admin','super_admin','2021-12-28 14:48:09','2021-12-28 14:48:09','0'),(5,'7b08517e94054c938c94ff5d4f4620fe','app4','Java','https://gitee.com/ken_xue/ddd-framework.git','https://gitee.com/ken_xue/ddd-framework.git','Https','super_admin','super_admin','2021-12-28 15:01:14','2021-12-28 15:01:14','0');
/*!40000 ALTER TABLE `application_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_of_user`
--

DROP TABLE IF EXISTS `application_of_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_of_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `application_uuid` varchar(32) NOT NULL COMMENT '应用uuid',
  `user_uuid` varchar(32) NOT NULL COMMENT '用户uuid',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='应用关联用户';
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
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='流水线';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_pipeline`
--

LOCK TABLES `application_pipeline` WRITE;
/*!40000 ALTER TABLE `application_pipeline` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_pipeline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machine_info`
--

DROP TABLE IF EXISTS `machine_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `machine_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(32) DEFAULT NULL COMMENT '服务器ip',
  `access_username` varchar(32) DEFAULT NULL COMMENT '服务器登陆用户名',
  `access_password` varchar(32) DEFAULT NULL COMMENT '服务器登陆密码',
  `access_key` varchar(32) DEFAULT NULL COMMENT '服务器登陆密钥',
  `master_node` tinyint(1) DEFAULT '0' COMMENT '是否是master节点',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='服务器节点';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine_info`
--

LOCK TABLES `machine_info` WRITE;
/*!40000 ALTER TABLE `machine_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `machine_info` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统验证码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_captcha`
--

LOCK TABLES `sys_captcha` WRITE;
/*!40000 ALTER TABLE `sys_captcha` DISABLE KEYS */;
INSERT INTO `sys_captcha` VALUES ('7b73de7d-f2f6-433d-8f29-c1fbbc30f416','xdpwf','2021-12-28 13:57:30');
/*!40000 ALTER TABLE `sys_captcha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dictionary`
--

DROP TABLE IF EXISTS `sys_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dictionary` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `key` varchar(200) DEFAULT NULL COMMENT '查询的key',
  `value` text COMMENT '字典值',
  `fixed` tinyint(1) DEFAULT '1' COMMENT '是否可更改',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
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
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `execute_time` bigint NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `occur_time` timestamp NULL DEFAULT NULL COMMENT '发生时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (1,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"creator\":\"admin\",\"deleted\":false,\"gmtCreate\":1638345181000,\"gmtModified\":1638345181000,\"id\":50,\"menuIcon\":\"excel\",\"menuName\":\"表单生成\",\"menuOrder\":0,\"menuParentName\":\"开发工具\",\"menuParentUuid\":\"93f101be493d11ecb7c2543344556776\",\"menuPerms\":\"aaaaa\",\"menuType\":1,\"menuUrl\":\"dev/form\",\"modifier\":\"admin\",\"uuid\":\"4a6ddb331d464050ac87f8c13f57facc\"},\"needsOperator\":false}]',76,'127.0.0.1','2021-12-09 06:42:39'),(2,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"deleted\":false,\"gmtCreate\":1637357736000,\"gmtModified\":1637357736000,\"id\":23,\"menuIcon\":\"list\",\"menuName\":\"菜单管理\",\"menuOrder\":1,\"menuParentName\":\"系统管理\",\"menuParentUuid\":\"93f101be493d11ecb7c254334455677\",\"menuType\":1,\"menuUrl\":\"sys/menu\",\"uuid\":\"93ebbcb2493d11ecb7c254e1ad134d77\"},\"needsOperator\":false}]',26,'127.0.0.1','2021-12-09 06:42:41'),(3,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"deleted\":false,\"gmtCreate\":1638316144000,\"gmtModified\":1638316144000,\"id\":45,\"menuIcon\":\"user\",\"menuName\":\"用户管理\",\"menuOrder\":6,\"menuParentName\":\"系统管理\",\"menuParentUuid\":\"93f101be493d11ecb7c254334455677\",\"menuType\":1,\"menuUrl\":\"sys/user\",\"uuid\":\"0bb9b97e51f511eca3306106f8cc1608\"},\"needsOperator\":false}]',8,'127.0.0.1','2021-12-09 06:42:43'),(4,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"deleted\":false,\"gmtCreate\":1638374380000,\"gmtModified\":1638374380000,\"id\":51,\"menuIcon\":\"documentation\",\"menuName\":\"系统日志\",\"menuOrder\":10,\"menuParentName\":\"系统管理\",\"menuParentUuid\":\"93f101be493d11ecb7c254334455677\",\"menuType\":1,\"menuUrl\":\"sys/log\",\"uuid\":\"a2fd81e4527c11ecb2ccd76ea2e07fd8\"},\"needsOperator\":false}]',15,'127.0.0.1','2021-12-09 06:42:44'),(5,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"deleted\":false,\"gmtCreate\":1637357729000,\"gmtModified\":1637357729000,\"id\":18,\"menuIcon\":\"peoples\",\"menuName\":\"角色管理\",\"menuOrder\":0,\"menuParentName\":\"系统管理\",\"menuParentUuid\":\"93f101be493d11ecb7c254334455677\",\"menuType\":1,\"menuUrl\":\"sys/role\",\"uuid\":\"8fc52771493d11ecb7c254e1ad134d77\"},\"needsOperator\":false}]',9,'127.0.0.1','2021-12-09 06:42:46'),(6,'admin','添加菜单','io.dev.admin.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"menuIcon\":\"skill\",\"menuName\":\"代码生成\",\"menuOrder\":0,\"menuParentName\":\"开发工具\",\"menuParentUuid\":\"93f101be493d11ecb7c2543344556776\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"dev/code\",\"uuid\":\"\"},\"needsOperator\":false}]',7,'127.0.0.1','2021-12-09 06:42:48'),(7,'mikey','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"menuIcon\":\"component\",\"menuName\":\"应用管理\",\"menuOrder\":1,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',137,'127.0.0.1','2021-12-26 10:23:20'),(8,'super_admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1637357736000,\"gmtModified\":1637357736000,\"id\":28,\"menuIcon\":\"example\",\"menuName\":\"系统管理\",\"menuOrder\":2,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuType\":0,\"uuid\":\"93f101be493d11ecb7c254334455677\"},\"needsOperator\":false}]',141,'127.0.0.1','2021-12-26 11:33:55'),(9,'super_admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1640510773000,\"gmtModified\":1640510773000,\"id\":62,\"menuIcon\":\"component\",\"menuName\":\"应用\",\"menuOrder\":6,\"menuParentName\":\"应用管理\",\"menuParentUuid\":\"eaf11a0343d0429ea54a7d12644e65bc\",\"menuType\":1,\"menuUrl\":\"/application/applicationinfo\",\"uuid\":\"ddf781e8662d11eca73c0242ac110004\"},\"needsOperator\":false}]',161,'127.0.0.1','2021-12-26 11:34:42'),(10,'super_admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1640510773000,\"gmtModified\":1640510773000,\"id\":62,\"menuIcon\":\"component\",\"menuName\":\"应用\",\"menuOrder\":6,\"menuParentName\":\"应用管理\",\"menuParentUuid\":\"eaf11a0343d0429ea54a7d12644e65bc\",\"menuType\":1,\"menuUrl\":\"/application/app\",\"uuid\":\"ddf781e8662d11eca73c0242ac110004\"},\"needsOperator\":false}]',141,'127.0.0.1','2021-12-26 11:36:42'),(11,'super_admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1640510728000,\"gmtModified\":1640510728000,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"应用管理\",\"menuParentUuid\":\"eaf11a0343d0429ea54a7d12644e65bc\",\"menuType\":1,\"menuUrl\":\"/application/applicationconfig\",\"uuid\":\"c33bc3ca662d11eca73c0242ac110004\"},\"needsOperator\":false}]',147,'127.0.0.1','2021-12-26 12:09:09'),(12,'super_admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1640510886000,\"gmtModified\":1640510886000,\"id\":67,\"menuIcon\":\"component\",\"menuName\":\"应用关联用户\",\"menuOrder\":6,\"menuParentName\":\"应用管理\",\"menuParentUuid\":\"eaf11a0343d0429ea54a7d12644e65bc\",\"menuType\":1,\"menuUrl\":\"/application/applicationofuser\",\"uuid\":\"21dde6d0662e11eca73c0242ac110004\"},\"needsOperator\":false}]',135,'127.0.0.1','2021-12-26 12:09:17'),(13,'super_admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1640510933000,\"gmtModified\":1640510933000,\"id\":72,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"应用管理\",\"menuParentUuid\":\"eaf11a0343d0429ea54a7d12644e65bc\",\"menuType\":1,\"menuUrl\":\"/application/applicationpipeline\",\"uuid\":\"3dc52a32662e11eca73c0242ac110004\"},\"needsOperator\":false}]',145,'127.0.0.1','2021-12-26 12:09:39'),(14,'super_admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1640510728000,\"gmtModified\":1640510728000,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线配置\",\"menuOrder\":6,\"menuParentName\":\"应用管理\",\"menuParentUuid\":\"eaf11a0343d0429ea54a7d12644e65bc\",\"menuType\":1,\"menuUrl\":\"/application/applicationconfig\",\"uuid\":\"c33bc3ca662d11eca73c0242ac110004\"},\"needsOperator\":false}]',136,'127.0.0.1','2021-12-26 12:09:56');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT 'uuid',
  `menu_parent_uuid` varchar(32) DEFAULT NULL COMMENT '父菜单uuid',
  `menu_name` varchar(64) DEFAULT NULL COMMENT '菜单名',
  `menu_url` varchar(200) DEFAULT NULL COMMENT '菜单url',
  `menu_perms` varchar(200) DEFAULT NULL COMMENT '授权标识',
  `menu_type` varchar(1) DEFAULT NULL COMMENT '0:目录 1:菜单 2:按钮',
  `menu_icon` varchar(20) DEFAULT NULL COMMENT '图标',
  `menu_order` int DEFAULT NULL COMMENT '排序',
  `menu_remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_menu_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (8,'87351d5e493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','用户关联角色','sys/userofrole',NULL,'1','icon',6,NULL,NULL,'admin','2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(9,'87362493493d11ecb7c254e1ad134d77','87351d5e493d11ecb7c254e1ad134d77','查看',NULL,'sys:userofrole:info,sys:userofrole:page','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(10,'87383f93493d11ecb7c254e1ad134d77','87351d5e493d11ecb7c254e1ad134d77','新增',NULL,'sys:userofrole:add','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(11,'87393320493d11ecb7c254e1ad134d77','87351d5e493d11ecb7c254e1ad134d77','修改',NULL,'sys:userofrole:update','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(12,'873a1d50493d11ecb7c254e1ad134d77','87351d5e493d11ecb7c254e1ad134d77','删除',NULL,'sys:userofrole:delete','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(13,'8b916d0f493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','角色关联菜单','sys/roleofmenu',NULL,'1','icon',6,NULL,NULL,'admin','2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(14,'8b93f7dc493d11ecb7c254e1ad134d77','8b916d0f493d11ecb7c254e1ad134d77','查看',NULL,'sys:roleofmenu:info,sys:roleofmenu:page','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(15,'8b94d734493d11ecb7c254e1ad134d77','8b916d0f493d11ecb7c254e1ad134d77','新增',NULL,'sys:roleofmenu:add','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(16,'8b95bc40493d11ecb7c254e1ad134d77','8b916d0f493d11ecb7c254e1ad134d77','修改',NULL,'sys:roleofmenu:update','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(17,'8b96995a493d11ecb7c254e1ad134d77','8b916d0f493d11ecb7c254e1ad134d77','删除',NULL,'sys:roleofmenu:delete','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(18,'8fc52771493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','角色管理','sys/role',NULL,'1','peoples',0,NULL,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(19,'8fc6eb1a493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','查看',NULL,'sys:role:info,sys:role:page,sys:role:list','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(20,'8fc89835493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','新增',NULL,'sys:role:add','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(21,'8fc99a2d493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','修改',NULL,'sys:role:update','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(22,'8fcaa825493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','删除',NULL,'sys:role:delete','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(23,'93ebbcb2493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','菜单管理','sys/menu',NULL,'1','list',1,NULL,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(24,'93ed48f8493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','查看',NULL,'sys:menu:info,sys:menu:page,sys:menu:list,sys:menu:select','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(25,'93ee29b6493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','新增',NULL,'sys:menu:add','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(26,'93f00a10493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','修改',NULL,'sys:menu:update','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(27,'93f101be493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','删除',NULL,'sys:menu:delete','2',NULL,6,NULL,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(28,'93f101be493d11ecb7c254334455677','0','系统管理',NULL,NULL,'0','example',2,NULL,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(29,'93f101be493d11ecb7c2543344556776','0','开发工具',NULL,NULL,'0','bug',2,NULL,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(45,'0bb9b97e51f511eca3306106f8cc1608','93f101be493d11ecb7c254334455677','用户管理','sys/user',NULL,'1','user',6,NULL,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(46,'0bbe311651f511eca3306106f8cc1608','0bb9b97e51f511eca3306106f8cc1608','查看',NULL,'sys:user:page,sys:user:info','2',NULL,6,NULL,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(47,'0bbf3e9e51f511eca3306106f8cc1608','0bb9b97e51f511eca3306106f8cc1608','新增',NULL,'sys:user:add','2',NULL,6,NULL,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(48,'0bc18ab451f511eca3306106f8cc1608','0bb9b97e51f511eca3306106f8cc1608','修改',NULL,'sys:user:update','2',NULL,6,NULL,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(49,'0bc60ce251f511eca3306106f8cc1608','0bb9b97e51f511eca3306106f8cc1608','删除',NULL,'sys:user:delete','2',NULL,6,NULL,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(50,'4a6ddb331d464050ac87f8c13f57facc','93f101be493d11ecb7c2543344556776','表单生成','dev/form','aaaaa','1','excel',0,NULL,'admin','admin','2021-12-01 07:53:01','2021-12-01 07:53:01','0'),(51,'a2fd81e4527c11ecb2ccd76ea2e07fd8','93f101be493d11ecb7c254334455677','系统日志','sys/log',NULL,'1','documentation',10,NULL,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(52,'a329b368527c11ecb2ccd76ea2e07fd8','a2fd81e4527c11ecb2ccd76ea2e07fd8','查看',NULL,'sys:log:page,sys:log:info','2',NULL,6,NULL,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(53,'a32e88ca527c11ecb2ccd76ea2e07fd8','a2fd81e4527c11ecb2ccd76ea2e07fd8','新增',NULL,'sys:log:save','2',NULL,6,NULL,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(54,'a330ec3c527c11ecb2ccd76ea2e07fd8','a2fd81e4527c11ecb2ccd76ea2e07fd8','修改',NULL,'sys:log:update','2',NULL,6,NULL,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(55,'a3332a60527c11ecb2ccd76ea2e07fd8','a2fd81e4527c11ecb2ccd76ea2e07fd8','删除',NULL,'sys:log:delete','2',NULL,6,NULL,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(56,'8331d5f0900444109edc6cd83e54032e','93f101be493d11ecb7c2543344556776','代码生成','dev/code','','1','skill',0,NULL,'admin','admin','2021-12-09 06:21:28','2021-12-09 06:21:28','0'),(57,'c33bc3ca662d11eca73c0242ac110004','eaf11a0343d0429ea54a7d12644e65bc','流水线配置','application/config',NULL,'1','component',6,NULL,NULL,NULL,'2021-12-26 09:25:28','2021-12-26 09:25:28','0'),(58,'c3d7e101662d11eca73c0242ac110004','c33bc3ca662d11eca73c0242ac110004','查看',NULL,'application:applicationconfig:page,application:applicationconfig:info','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:25:29','2021-12-26 09:25:29','0'),(59,'c493bfe5662d11eca73c0242ac110004','c33bc3ca662d11eca73c0242ac110004','新增',NULL,'application:applicationconfig:add','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:25:30','2021-12-26 09:25:30','0'),(60,'c534ede9662d11eca73c0242ac110004','c33bc3ca662d11eca73c0242ac110004','修改',NULL,'application:applicationconfig:update','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:25:31','2021-12-26 09:25:31','0'),(61,'c5cb668c662d11eca73c0242ac110004','c33bc3ca662d11eca73c0242ac110004','删除',NULL,'application:applicationconfig:delete','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:25:32','2021-12-26 09:25:32','0'),(62,'ddf781e8662d11eca73c0242ac110004','eaf11a0343d0429ea54a7d12644e65bc','应用列表','application/app',NULL,'1','component',6,NULL,NULL,NULL,'2021-12-26 09:26:13','2021-12-26 09:26:13','0'),(63,'de8ef7e7662d11eca73c0242ac110004','ddf781e8662d11eca73c0242ac110004','查看',NULL,'application:applicationinfo:page,application:applicationinfo:info','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:26:14','2021-12-26 09:26:14','0'),(64,'df27f129662d11eca73c0242ac110004','ddf781e8662d11eca73c0242ac110004','新增',NULL,'application:applicationinfo:add','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:26:15','2021-12-26 09:26:15','0'),(65,'dfbeb067662d11eca73c0242ac110004','ddf781e8662d11eca73c0242ac110004','修改',NULL,'application:applicationinfo:update','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:26:15','2021-12-26 09:26:15','0'),(66,'e0579d8d662d11eca73c0242ac110004','ddf781e8662d11eca73c0242ac110004','删除',NULL,'application:applicationinfo:delete','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:26:17','2021-12-26 09:26:17','0'),(67,'21dde6d0662e11eca73c0242ac110004','eaf11a0343d0429ea54a7d12644e65bc','应用关联用户','application/appofuser',NULL,'1','component',6,NULL,NULL,NULL,'2021-12-26 09:28:06','2021-12-26 09:28:06','0'),(68,'227bccf1662e11eca73c0242ac110004','21dde6d0662e11eca73c0242ac110004','查看',NULL,'application:applicationofuser:page,application:applicationofuser:info','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:28:07','2021-12-26 09:28:07','0'),(69,'2312798d662e11eca73c0242ac110004','21dde6d0662e11eca73c0242ac110004','新增',NULL,'application:applicationofuser:add','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:28:08','2021-12-26 09:28:08','0'),(70,'23aa082e662e11eca73c0242ac110004','21dde6d0662e11eca73c0242ac110004','修改',NULL,'application:applicationofuser:update','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:28:09','2021-12-26 09:28:09','0'),(71,'2443d3f9662e11eca73c0242ac110004','21dde6d0662e11eca73c0242ac110004','删除',NULL,'application:applicationofuser:delete','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:28:10','2021-12-26 09:28:10','0'),(72,'3dc52a32662e11eca73c0242ac110004','eaf11a0343d0429ea54a7d12644e65bc','流水线','application/pipeline',NULL,'1','component',6,NULL,NULL,NULL,'2021-12-26 09:28:53','2021-12-26 09:28:53','0'),(73,'3e598296662e11eca73c0242ac110004','3dc52a32662e11eca73c0242ac110004','查看',NULL,'application:applicationpipeline:page,application:applicationpipeline:info','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:28:54','2021-12-26 09:28:54','0'),(74,'3ee88db3662e11eca73c0242ac110004','3dc52a32662e11eca73c0242ac110004','新增',NULL,'application:applicationpipeline:add','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:28:55','2021-12-26 09:28:55','0'),(75,'3f80f22d662e11eca73c0242ac110004','3dc52a32662e11eca73c0242ac110004','修改',NULL,'application:applicationpipeline:update','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:28:56','2021-12-26 09:28:56','0'),(76,'4017d3f0662e11eca73c0242ac110004','3dc52a32662e11eca73c0242ac110004','删除',NULL,'application:applicationpipeline:delete','2',NULL,6,NULL,NULL,NULL,'2021-12-26 09:28:57','2021-12-26 09:28:57','0'),(77,'eaf11a0343d0429ea54a7d12644e65bc','0','应用管理','','','0','component',1,NULL,'mikey','mikey','2021-12-26 10:23:20','2021-12-26 10:23:20','0');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
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
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `role_uuid` varchar(32) DEFAULT NULL COMMENT '角色UUID',
  `menu_uuid` varchar(32) DEFAULT NULL COMMENT '菜单UUID',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色关联菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_of_menu`
--

LOCK TABLES `sys_role_of_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_of_menu` DISABLE KEYS */;
INSERT INTO `sys_role_of_menu` VALUES (85,'edc36fbe44b346309633f6a63540eeaa','0caadea8ae91427eb3d869df43d0fe41','93f101be493d11ecb7c254334455677','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(86,'341f066eb9b74c3ca91525eb51ff3349','0caadea8ae91427eb3d869df43d0fe41','8fc52771493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(87,'8382665c44704e2ba00e5716e530350f','0caadea8ae91427eb3d869df43d0fe41','8fc6eb1a493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(88,'7582c9a49261487891bb3d357c20369a','0caadea8ae91427eb3d869df43d0fe41','8fc89835493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(89,'6e50c43e8a934bd78116d7dafe763fd3','0caadea8ae91427eb3d869df43d0fe41','8fc99a2d493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(90,'353de15fd3b942238e24b067c43a64e5','0caadea8ae91427eb3d869df43d0fe41','8fcaa825493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(91,'32be576f04d54f9a8d0723b66991d259','0caadea8ae91427eb3d869df43d0fe41','93ebbcb2493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(92,'00935ece12a248dda6f4c45cb8d78548','0caadea8ae91427eb3d869df43d0fe41','93ed48f8493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(93,'ab645494c8334636bcc31d4011ab5d27','0caadea8ae91427eb3d869df43d0fe41','93ee29b6493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(94,'31d1250e836449f484826b8fee0b91db','0caadea8ae91427eb3d869df43d0fe41','93f00a10493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(95,'1de65d702d734f9fb53591f1f71e5c35','0caadea8ae91427eb3d869df43d0fe41','93f101be493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(96,'72af74fd48f84871a3daab03a187eda0','0caadea8ae91427eb3d869df43d0fe41','0bb9b97e51f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(97,'b69b420baf4a4ecfb022dd6182337058','0caadea8ae91427eb3d869df43d0fe41','0bbe311651f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(98,'f50c633ba6034537895f074d9e53ef1f','0caadea8ae91427eb3d869df43d0fe41','0bbf3e9e51f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(99,'747994f0108c48e2bb700f755a3ec9dd','0caadea8ae91427eb3d869df43d0fe41','0bc18ab451f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(100,'fab2d2a63f8f43ff873fd6c6c75c932d','0caadea8ae91427eb3d869df43d0fe41','0bc60ce251f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(101,'850daeaac10b4cca89efe67673d61580','0caadea8ae91427eb3d869df43d0fe41','a2fd81e4527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(102,'ffe45ebe32ed4cb48a0e0108cc5d713b','0caadea8ae91427eb3d869df43d0fe41','a329b368527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(103,'3207f4b395d6439eba466e4ded9b01ed','0caadea8ae91427eb3d869df43d0fe41','a32e88ca527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(104,'33d27be81f68459e86adc90f9f493c3e','0caadea8ae91427eb3d869df43d0fe41','a330ec3c527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(105,'eb951d36ebab4cfab0b7c446856900c8','0caadea8ae91427eb3d869df43d0fe41','a3332a60527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(107,'cdd7be6839c141118c7374d237e08445','0caadea8ae91427eb3d869df43d0fe41','638a1e2c527d11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(108,'a5e2ea99983d4f109e825ca88c4c58f0','0caadea8ae91427eb3d869df43d0fe41','638b5742527d11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(109,'a6e057847282429f82478c989b9138e4','0caadea8ae91427eb3d869df43d0fe41','638c3d9c527d11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(110,'5cdf81bcf80041f4a735aefb55b45d66','0caadea8ae91427eb3d869df43d0fe41','638d62b2527d11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(111,'81ac9f6f388741a398d70f7f0ac48b85','0caadea8ae91427eb3d869df43d0fe41','93f101be493d11ecb7c2543344556776','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(112,'22d909855d374be69c473da2bf71eb38','0caadea8ae91427eb3d869df43d0fe41','4a6ddb331d464050ac87f8c13f57facc','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(137,'504380e324b24fc3a3f3bda6a0e6da74','93f101be493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','mikey','mikey','2021-12-26 10:24:18','2021-12-26 10:24:18','0'),(138,'7c5fa4b0f8b54ee99496898341da3899','93f101be493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','mikey','mikey','2021-12-26 10:24:18','2021-12-26 10:24:18','0'),(139,'04ee8ee11ba34bd6b14e1c41e77257c7','93f101be493d11ecb7c254e1ad134d77','8fc6eb1a493d11ecb7c254e1ad134d77','mikey','mikey','2021-12-26 10:24:18','2021-12-26 10:24:18','0'),(140,'88124595e09641deb9b511881ae64a88','93f101be493d11ecb7c254e1ad134d77','8fc89835493d11ecb7c254e1ad134d77','mikey','mikey','2021-12-26 10:24:18','2021-12-26 10:24:18','0'),(141,'a17c1071d6b84846b5e3f681f550560e','93f101be493d11ecb7c254e1ad134d77','8fc99a2d493d11ecb7c254e1ad134d77','mikey','mikey','2021-12-26 10:24:18','2021-12-26 10:24:18','0'),(142,'494d2c7efc4a469ea7c9f34f286b671a','93f101be493d11ecb7c254e1ad134d77','8fcaa825493d11ecb7c254e1ad134d77','mikey','mikey','2021-12-26 10:24:19','2021-12-26 10:24:19','0'),(143,'ab0fdc8c0a9a4e72aa89df4c5795063f','93f101be493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','mikey','mikey','2021-12-26 10:24:19','2021-12-26 10:24:19','0'),(144,'8578dcac8d604a849346770190c05836','93f101be493d11ecb7c254e1ad134d77','93ed48f8493d11ecb7c254e1ad134d77','mikey','mikey','2021-12-26 10:24:19','2021-12-26 10:24:19','0'),(145,'311577b689554ed3bef8234197ee4bb3','93f101be493d11ecb7c254e1ad134d77','93ee29b6493d11ecb7c254e1ad134d77','mikey','mikey','2021-12-26 10:24:19','2021-12-26 10:24:19','0'),(146,'36559199b04a49c59e4aa0f3be731baf','93f101be493d11ecb7c254e1ad134d77','93f00a10493d11ecb7c254e1ad134d77','mikey','mikey','2021-12-26 10:24:19','2021-12-26 10:24:19','0'),(147,'4d8ea2725ba34642b3555c3e30ebd5e3','93f101be493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254e1ad134d77','mikey','mikey','2021-12-26 10:24:19','2021-12-26 10:24:19','0'),(148,'e690f8f85014471987f92c6e2d9dece3','93f101be493d11ecb7c254e1ad134d77','0bb9b97e51f511eca3306106f8cc1608','mikey','mikey','2021-12-26 10:24:19','2021-12-26 10:24:19','0'),(149,'352f735200de45d2b2f9548b6c7acf63','93f101be493d11ecb7c254e1ad134d77','0bbe311651f511eca3306106f8cc1608','mikey','mikey','2021-12-26 10:24:19','2021-12-26 10:24:19','0'),(150,'b9c1ff6eef9244efac8ad03e27c32be6','93f101be493d11ecb7c254e1ad134d77','0bbf3e9e51f511eca3306106f8cc1608','mikey','mikey','2021-12-26 10:24:19','2021-12-26 10:24:19','0'),(151,'15f37103222d494696bb7e702e773794','93f101be493d11ecb7c254e1ad134d77','0bc18ab451f511eca3306106f8cc1608','mikey','mikey','2021-12-26 10:24:19','2021-12-26 10:24:19','0'),(152,'45f4fb207cbf44e698a697cff9994aa1','93f101be493d11ecb7c254e1ad134d77','0bc60ce251f511eca3306106f8cc1608','mikey','mikey','2021-12-26 10:24:19','2021-12-26 10:24:19','0'),(153,'9613310056d04193a9d1f511923e02f4','93f101be493d11ecb7c254e1ad134d77','a2fd81e4527c11ecb2ccd76ea2e07fd8','mikey','mikey','2021-12-26 10:24:20','2021-12-26 10:24:20','0'),(154,'002c16633b9c4a7fadb1866da1430f4b','93f101be493d11ecb7c254e1ad134d77','a329b368527c11ecb2ccd76ea2e07fd8','mikey','mikey','2021-12-26 10:24:20','2021-12-26 10:24:20','0'),(155,'85235a11081a4966aba7006f8a56775c','93f101be493d11ecb7c254e1ad134d77','a32e88ca527c11ecb2ccd76ea2e07fd8','mikey','mikey','2021-12-26 10:24:20','2021-12-26 10:24:20','0'),(156,'5a55c300bd404628b9d24af46e76045e','93f101be493d11ecb7c254e1ad134d77','a330ec3c527c11ecb2ccd76ea2e07fd8','mikey','mikey','2021-12-26 10:24:20','2021-12-26 10:24:20','0'),(157,'716bd0619284429fb1259cdf84ce443e','93f101be493d11ecb7c254e1ad134d77','a3332a60527c11ecb2ccd76ea2e07fd8','mikey','mikey','2021-12-26 10:24:20','2021-12-26 10:24:20','0'),(158,'044e0c4f81c14320b6489a2fa81eadf0','93f101be493d11ecb7c254e1ad134d77','93f101be493d11ecb7c2543344556776','mikey','mikey','2021-12-26 10:24:20','2021-12-26 10:24:20','0'),(159,'c89db934ffd546dc9d01d334434810d4','93f101be493d11ecb7c254e1ad134d77','4a6ddb331d464050ac87f8c13f57facc','mikey','mikey','2021-12-26 10:24:20','2021-12-26 10:24:20','0'),(160,'fd9b9d339aca40f0a43c34a453e51142','93f101be493d11ecb7c254e1ad134d77','8331d5f0900444109edc6cd83e54032e','mikey','mikey','2021-12-26 10:24:20','2021-12-26 10:24:20','0'),(161,'4ef13d62daac4e7796e1f0aec078a5dc','93f101be493d11ecb7c254e1ad134d77','c33bc3ca662d11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:20','2021-12-26 10:24:20','0'),(162,'0034b848bf52445184411bc87f0509b4','93f101be493d11ecb7c254e1ad134d77','c3d7e101662d11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:20','2021-12-26 10:24:20','0'),(163,'6f3da88aa501446895124b07dd04f8e0','93f101be493d11ecb7c254e1ad134d77','c493bfe5662d11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:21','2021-12-26 10:24:21','0'),(164,'ea6731550dda415d9c08bad5842819bb','93f101be493d11ecb7c254e1ad134d77','c534ede9662d11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:21','2021-12-26 10:24:21','0'),(165,'12d52c9be74a44848cb07fe463f8063f','93f101be493d11ecb7c254e1ad134d77','c5cb668c662d11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:21','2021-12-26 10:24:21','0'),(166,'f7202557432e4cddbc7b7351cb239515','93f101be493d11ecb7c254e1ad134d77','ddf781e8662d11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:21','2021-12-26 10:24:21','0'),(167,'cfcdc2414f804ba0903239a15d6c27cd','93f101be493d11ecb7c254e1ad134d77','de8ef7e7662d11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:21','2021-12-26 10:24:21','0'),(168,'947efbea7b0e468389646a7118f77eef','93f101be493d11ecb7c254e1ad134d77','df27f129662d11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:21','2021-12-26 10:24:21','0'),(169,'e4542d90ac124419abcfe31205441ec9','93f101be493d11ecb7c254e1ad134d77','dfbeb067662d11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:21','2021-12-26 10:24:21','0'),(170,'4cda5d08980a4dd8a4b8ec191e9c9e4e','93f101be493d11ecb7c254e1ad134d77','e0579d8d662d11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:21','2021-12-26 10:24:21','0'),(171,'c816906283e94d169c4f4f9d658f472a','93f101be493d11ecb7c254e1ad134d77','21dde6d0662e11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:21','2021-12-26 10:24:21','0'),(172,'abbd93460f364ae5bd773f65fc9e9d83','93f101be493d11ecb7c254e1ad134d77','227bccf1662e11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:21','2021-12-26 10:24:21','0'),(173,'1d56aa5a8b034bfe9c1d97bc4e04ff98','93f101be493d11ecb7c254e1ad134d77','2312798d662e11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:21','2021-12-26 10:24:21','0'),(174,'2ff065d7280e4f11a2714910aa2d5129','93f101be493d11ecb7c254e1ad134d77','23aa082e662e11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:22','2021-12-26 10:24:22','0'),(175,'5fd72fd85c7b4f8183b4cfa1b5f739dc','93f101be493d11ecb7c254e1ad134d77','2443d3f9662e11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:22','2021-12-26 10:24:22','0'),(176,'633680369e194d85abac31a489ca9981','93f101be493d11ecb7c254e1ad134d77','3dc52a32662e11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:22','2021-12-26 10:24:22','0'),(177,'28b997824c4048aebff493f43bae1d86','93f101be493d11ecb7c254e1ad134d77','3e598296662e11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:22','2021-12-26 10:24:22','0'),(178,'0a15e1ac465b42709d8fd3477fd5ecc6','93f101be493d11ecb7c254e1ad134d77','3ee88db3662e11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:22','2021-12-26 10:24:22','0'),(179,'802f9d00ee8b4eebad5950f71964ec7f','93f101be493d11ecb7c254e1ad134d77','3f80f22d662e11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:22','2021-12-26 10:24:22','0'),(180,'438b4dcb964a4bc3a6e2c95cfd5addba','93f101be493d11ecb7c254e1ad134d77','4017d3f0662e11eca73c0242ac110004','mikey','mikey','2021-12-26 10:24:22','2021-12-26 10:24:22','0'),(181,'c5e91c2ebe42434b9e86adfb32f59e2b','93f101be493d11ecb7c254e1ad134d77','eaf11a0343d0429ea54a7d12644e65bc','mikey','mikey','2021-12-26 10:24:22','2021-12-26 10:24:22','0');
/*!40000 ALTER TABLE `sys_role_of_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `user_id` varchar(64) DEFAULT NULL COMMENT '账号',
  `user_password` varchar(64) DEFAULT NULL COMMENT '密码',
  `user_name` varchar(64) DEFAULT NULL COMMENT '名字',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `status` int DEFAULT '0' COMMENT '角色',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1000,'9df9de08354f456c97fae0cdb3df214f','super_admin','$2a$10$7CQ/9w8BucRDnS3/cG2lXOA3y.9eEJUY6pidZGiNFQCU5Vu2q6ZwO','admin','devcloudadmin@qq.com',0,NULL,'admin','2021-11-12 22:48:15','2021-11-12 22:48:15','0',NULL),(1002,'26220d63bfd345dabb2f114287965313','mikey','$2a$10$ZnuPGCEusk5tiKxAB/1lreynJxuvh4mqu8So6vUok/PCBCYP34.gi','mikey','mikey',0,NULL,NULL,'2021-12-01 09:39:53','2021-12-01 09:39:53','0',NULL),(1003,'b8c1e673060c437ba0925f119538bdb6','fasf','$2a$10$ILTzdnK9HtI4BiTXneTWOOgiLid.QHTuDK0iV42EvH142xhuSheqy','fasdf','asfd@qq.com',0,'mikey','admin','2021-12-01 09:46:07','2021-12-01 09:46:07','1',NULL),(1009,'5555555','7',NULL,NULL,NULL,0,NULL,NULL,'2021-12-23 08:07:59','2021-12-23 08:07:59','0',NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_of_role`
--

DROP TABLE IF EXISTS `sys_user_of_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_of_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(32) DEFAULT NULL COMMENT '唯一标示做关联',
  `user_uuid` varchar(32) DEFAULT NULL COMMENT '用户UUID',
  `role_uuid` varchar(32) DEFAULT NULL COMMENT '角色UUID',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户关联角色表';
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

-- Dump completed on 2021-12-28 23:02:25
