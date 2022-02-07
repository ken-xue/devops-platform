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
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COMMENT='应用';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_info`
--

LOCK TABLES `application_info` WRITE;
/*!40000 ALTER TABLE `application_info` DISABLE KEYS */;
INSERT INTO `application_info` VALUES (1,'1e635672d5524c96ae85dc8c0ae153a5','app1','Java','https://gitee.com/ken_xue/pipeline.git','https://gitee.com/ken_xue/pipeline.git','SSH','super_admin','super_admin','2021-12-28 14:38:57','2021-12-28 14:38:57','0'),(2,'dc23660d50624981ae346ee77a04d8d3','app2','Go','https://gitee.com/ken_xue/pipeline.git','https://gitee.com/ken_xue/pipeline.git','Https','super_admin','super_admin','2021-12-28 14:46:36','2021-12-28 14:46:36','0'),(4,'3040264d22e74d88abdd8c893218a1db','app3','NodeJS','https://gitee.com/ken_xue/pipeline.git','https://gitee.com/ken_xue/pipeline.git','SVN','super_admin','super_admin','2021-12-28 14:48:09','2021-12-28 14:48:09','0'),(5,'7b08517e94054c938c94ff5d4f4620fe','app4','Java','https://gitee.com/ken_xue/ddd-framework.git','https://gitee.com/ken_xue/ddd-framework.git','Https','super_admin','super_admin','2021-12-28 15:01:14','2021-12-28 15:01:14','0'),(6,'c7c12b28718340a29a28d955de29f66e','app5','Java','https://gitee.com/ken_xue/pipeline.git','https://gitee.com/ken_xue/pipeline.git','SVN','admin','admin','2022-02-06 14:16:09','2022-02-06 14:16:09','0');
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
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统验证码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_captcha`
--

LOCK TABLES `sys_captcha` WRITE;
/*!40000 ALTER TABLE `sys_captcha` DISABLE KEYS */;
INSERT INTO `sys_captcha` VALUES ('b00dc863-740f-4c40-8cec-7b1b66934b06','pe6cd','2022-02-07 02:23:36'),('43776016-fe92-48f1-8cc7-5adfdb8bbf97','nnxww','2022-02-07 02:24:44');
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `execute_time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `occur_time` timestamp NULL DEFAULT NULL COMMENT '发生时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (1,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"creator\":\"admin\",\"deleted\":false,\"gmtCreate\":1638345181000,\"gmtModified\":1638345181000,\"id\":50,\"menuIcon\":\"excel\",\"menuName\":\"表单生成\",\"menuOrder\":0,\"menuParentName\":\"开发工具\",\"menuParentUuid\":\"93f101be493d11ecb7c2543344556776\",\"menuPerms\":\"aaaaa\",\"menuType\":1,\"menuUrl\":\"dev/form\",\"modifier\":\"admin\",\"uuid\":\"4a6ddb331d464050ac87f8c13f57facc\"},\"needsOperator\":false}]',76,'127.0.0.1','2021-12-09 06:42:39'),(2,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"deleted\":false,\"gmtCreate\":1637357736000,\"gmtModified\":1637357736000,\"id\":23,\"menuIcon\":\"list\",\"menuName\":\"菜单管理\",\"menuOrder\":1,\"menuParentName\":\"系统管理\",\"menuParentUuid\":\"93f101be493d11ecb7c254334455677\",\"menuType\":1,\"menuUrl\":\"sys/menu\",\"uuid\":\"93ebbcb2493d11ecb7c254e1ad134d77\"},\"needsOperator\":false}]',26,'127.0.0.1','2021-12-09 06:42:41'),(3,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"deleted\":false,\"gmtCreate\":1638316144000,\"gmtModified\":1638316144000,\"id\":45,\"menuIcon\":\"user\",\"menuName\":\"用户管理\",\"menuOrder\":6,\"menuParentName\":\"系统管理\",\"menuParentUuid\":\"93f101be493d11ecb7c254334455677\",\"menuType\":1,\"menuUrl\":\"sys/user\",\"uuid\":\"0bb9b97e51f511eca3306106f8cc1608\"},\"needsOperator\":false}]',8,'127.0.0.1','2021-12-09 06:42:43'),(4,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"deleted\":false,\"gmtCreate\":1638374380000,\"gmtModified\":1638374380000,\"id\":51,\"menuIcon\":\"documentation\",\"menuName\":\"系统日志\",\"menuOrder\":10,\"menuParentName\":\"系统管理\",\"menuParentUuid\":\"93f101be493d11ecb7c254334455677\",\"menuType\":1,\"menuUrl\":\"sys/log\",\"uuid\":\"a2fd81e4527c11ecb2ccd76ea2e07fd8\"},\"needsOperator\":false}]',15,'127.0.0.1','2021-12-09 06:42:44'),(5,'mikey','修改菜单','io.dev.admin.adapter.rest.sys.MenuController.update()','[{\"menuCO\":{\"deleted\":false,\"gmtCreate\":1637357729000,\"gmtModified\":1637357729000,\"id\":18,\"menuIcon\":\"peoples\",\"menuName\":\"角色管理\",\"menuOrder\":0,\"menuParentName\":\"系统管理\",\"menuParentUuid\":\"93f101be493d11ecb7c254334455677\",\"menuType\":1,\"menuUrl\":\"sys/role\",\"uuid\":\"8fc52771493d11ecb7c254e1ad134d77\"},\"needsOperator\":false}]',9,'127.0.0.1','2021-12-09 06:42:46'),(6,'admin','添加菜单','io.dev.admin.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"menuIcon\":\"skill\",\"menuName\":\"代码生成\",\"menuOrder\":0,\"menuParentName\":\"开发工具\",\"menuParentUuid\":\"93f101be493d11ecb7c2543344556776\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"dev/code\",\"uuid\":\"\"},\"needsOperator\":false}]',7,'127.0.0.1','2021-12-09 06:42:48'),(7,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609644000,\"gmtModified\":1642609644000,\"id\":67,\"menuIcon\":\"component\",\"menuName\":\"应用\",\"menuOrder\":6,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuType\":1,\"menuUrl\":\"/application/app\",\"uuid\":\"a0e7d110790111ec9f0679def01aad17\"},\"needsOperator\":false}]',10,'127.0.0.1','2022-01-19 00:34:53'),(8,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"menuIcon\":\"dashboard\",\"menuName\":\"持续集成\",\"menuOrder\":3,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',10,'127.0.0.1','2022-02-05 23:57:33'),(9,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609627000,\"gmtModified\":1642609627000,\"id\":62,\"menuIcon\":\"component\",\"menuName\":\"应用关联用户\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"/application/applicationofuser\",\"uuid\":\"972bff98790111ec9f0679def01aad17\"},\"needsOperator\":false}]',12,'127.0.0.1','2022-02-05 23:59:21'),(10,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609657000,\"gmtModified\":1642609657000,\"id\":72,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"/application/applicationconfig\",\"uuid\":\"a87ccf3e790111ec9f0679def01aad17\"},\"needsOperator\":false}]',11,'127.0.0.1','2022-02-05 23:59:30'),(11,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609605000,\"gmtModified\":1642609605000,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"/application/pipeline\",\"uuid\":\"89dd6034790111ec9f0679def01aad17\"},\"needsOperator\":false}]',12,'127.0.0.1','2022-02-05 23:59:44'),(12,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609644000,\"gmtModified\":1642609644000,\"id\":67,\"menuIcon\":\"component\",\"menuName\":\"应用\",\"menuOrder\":1,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"application/app\",\"uuid\":\"a0e7d110790111ec9f0679def01aad17\"},\"needsOperator\":false}]',11,'127.0.0.1','2022-02-06 00:03:17'),(13,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"creator\":\"admin\",\"deleted\":false,\"gmtCreate\":1644134253000,\"gmtModified\":1644134253000,\"id\":77,\"menuIcon\":\"dashboard\",\"menuName\":\"持续集成\",\"menuOrder\":2,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"modifier\":\"admin\",\"uuid\":\"1f03c833858643cc8bccfc97ead0d4b3\"},\"needsOperator\":false}]',9,'127.0.0.1','2022-02-06 06:57:24'),(14,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"creator\":\"admin\",\"deleted\":false,\"gmtCreate\":1644134253000,\"gmtModified\":1644134253000,\"id\":77,\"menuIcon\":\"dashboard\",\"menuName\":\"持续集成\",\"menuOrder\":1,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"modifier\":\"admin\",\"uuid\":\"1f03c833858643cc8bccfc97ead0d4b3\"},\"needsOperator\":false}]',12,'127.0.0.1','2022-02-06 06:57:43'),(15,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609605000,\"gmtModified\":1642609605000,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"应用\",\"menuParentUuid\":\"a0e7d110790111ec9f0679def01aad17\",\"menuType\":1,\"menuUrl\":\"application/pipeline\",\"uuid\":\"89dd6034790111ec9f0679def01aad17\"},\"needsOperator\":false}]',7,'127.0.0.1','2022-02-06 06:59:08'),(16,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609605000,\"gmtModified\":1642609605000,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"application/pipeline\",\"uuid\":\"89dd6034790111ec9f0679def01aad17\"},\"needsOperator\":false}]',8,'127.0.0.1','2022-02-06 07:02:06'),(17,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609644000,\"gmtModified\":1642609644000,\"id\":67,\"menuIcon\":\"component\",\"menuName\":\"应用管理\",\"menuOrder\":1,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"application/app\",\"uuid\":\"a0e7d110790111ec9f0679def01aad17\"},\"needsOperator\":false}]',8,'127.0.0.1','2022-02-06 07:37:31'),(18,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609627000,\"gmtModified\":1642609627000,\"id\":62,\"menuIcon\":\"component\",\"menuName\":\"应用用户\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"application/applicationofuser\",\"uuid\":\"972bff98790111ec9f0679def01aad17\"},\"needsOperator\":false}]',6,'127.0.0.1','2022-02-06 07:38:20'),(19,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609605000,\"gmtModified\":1642609605000,\"hide\":true,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"application/pipeline\",\"uuid\":\"89dd6034790111ec9f0679def01aad17\"},\"needsOperator\":false}]',14,'127.0.0.1','2022-02-06 08:11:04'),(20,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609605000,\"gmtModified\":1642609605000,\"hide\":true,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"应用管理\",\"menuParentUuid\":\"a0e7d110790111ec9f0679def01aad17\",\"menuType\":1,\"menuUrl\":\"application/pipeline\",\"uuid\":\"89dd6034790111ec9f0679def01aad17\"},\"needsOperator\":false}]',11,'127.0.0.1','2022-02-06 18:36:03'),(21,'admin','修改菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.update()','[{\"menuDTO\":{\"deleted\":false,\"gmtCreate\":1642609605000,\"gmtModified\":1642609605000,\"hide\":true,\"id\":57,\"menuIcon\":\"component\",\"menuName\":\"流水线\",\"menuOrder\":6,\"menuParentName\":\"持续集成\",\"menuParentUuid\":\"1f03c833858643cc8bccfc97ead0d4b3\",\"menuType\":1,\"menuUrl\":\"application/pipeline\",\"uuid\":\"89dd6034790111ec9f0679def01aad17\"},\"needsOperator\":false}]',21,'127.0.0.1','2022-02-06 18:38:50'),(22,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"table\",\"menuName\":\"基建管理\",\"menuOrder\":3,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',12,'127.0.0.1','2022-02-06 18:59:04'),(23,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"guide\",\"menuName\":\"集群管理\",\"menuOrder\":4,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":0,\"menuUrl\":\"\",\"uuid\":\"\"},\"needsOperator\":false}]',9,'127.0.0.1','2022-02-06 19:00:08'),(24,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"password\",\"menuName\":\"zookeeper\",\"menuOrder\":0,\"menuParentName\":\"基建管理\",\"menuParentUuid\":\"4d1b9a3cf79c4637ba9b74a812393544\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"base/zk\",\"uuid\":\"\"},\"needsOperator\":false}]',7,'127.0.0.1','2022-02-06 19:01:39'),(25,'admin','添加菜单','io.kenxue.cicd.adapter.rest.sys.MenuController.add()','[{\"menuDTO\":{\"hide\":false,\"menuIcon\":\"example\",\"menuName\":\"kubernetes\",\"menuOrder\":0,\"menuParentName\":\"一级菜单\",\"menuParentUuid\":\"0\",\"menuPerms\":\"\",\"menuType\":1,\"menuUrl\":\"cluster/kubernetes\",\"uuid\":\"\"},\"needsOperator\":false}]',5,'127.0.0.1','2022-02-06 19:04:14');
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
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (8,'87351d5e493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','用户关联角色','sys/userofrole',NULL,'1','icon',6,NULL,0,NULL,'admin','2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(9,'87362493493d11ecb7c254e1ad134d77','87351d5e493d11ecb7c254e1ad134d77','查看',NULL,'sys:userofrole:info,sys:userofrole:page','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(10,'87383f93493d11ecb7c254e1ad134d77','87351d5e493d11ecb7c254e1ad134d77','新增',NULL,'sys:userofrole:add','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(11,'87393320493d11ecb7c254e1ad134d77','87351d5e493d11ecb7c254e1ad134d77','修改',NULL,'sys:userofrole:update','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(12,'873a1d50493d11ecb7c254e1ad134d77','87351d5e493d11ecb7c254e1ad134d77','删除',NULL,'sys:userofrole:delete','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:15','2021-11-19 21:35:15','1'),(13,'8b916d0f493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','角色关联菜单','sys/roleofmenu',NULL,'1','icon',6,NULL,0,NULL,'admin','2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(14,'8b93f7dc493d11ecb7c254e1ad134d77','8b916d0f493d11ecb7c254e1ad134d77','查看',NULL,'sys:roleofmenu:info,sys:roleofmenu:page','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(15,'8b94d734493d11ecb7c254e1ad134d77','8b916d0f493d11ecb7c254e1ad134d77','新增',NULL,'sys:roleofmenu:add','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(16,'8b95bc40493d11ecb7c254e1ad134d77','8b916d0f493d11ecb7c254e1ad134d77','修改',NULL,'sys:roleofmenu:update','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(17,'8b96995a493d11ecb7c254e1ad134d77','8b916d0f493d11ecb7c254e1ad134d77','删除',NULL,'sys:roleofmenu:delete','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:22','2021-11-19 21:35:22','1'),(18,'8fc52771493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','角色管理','sys/role',NULL,'1','peoples',0,NULL,0,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(19,'8fc6eb1a493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','查看',NULL,'sys:role:info,sys:role:page,sys:role:list','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(20,'8fc89835493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','新增',NULL,'sys:role:add','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(21,'8fc99a2d493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','修改',NULL,'sys:role:update','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(22,'8fcaa825493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','删除',NULL,'sys:role:delete','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:29','2021-11-19 21:35:29','0'),(23,'93ebbcb2493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','菜单管理','sys/menu',NULL,'1','list',1,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(24,'93ed48f8493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','查看',NULL,'sys:menu:info,sys:menu:page,sys:menu:list,sys:menu:select','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(25,'93ee29b6493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','新增',NULL,'sys:menu:add','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(26,'93f00a10493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','修改',NULL,'sys:menu:update','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(27,'93f101be493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','删除',NULL,'sys:menu:delete','2',NULL,6,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(28,'93f101be493d11ecb7c254334455677','0','系统管理',NULL,NULL,'0','example',1,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(29,'93f101be493d11ecb7c2543344556776','0','开发工具',NULL,NULL,'0','bug',2,NULL,0,NULL,NULL,'2021-11-19 21:35:36','2021-11-19 21:35:36','0'),(45,'0bb9b97e51f511eca3306106f8cc1608','93f101be493d11ecb7c254334455677','用户管理','sys/user',NULL,'1','user',6,NULL,0,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(46,'0bbe311651f511eca3306106f8cc1608','0bb9b97e51f511eca3306106f8cc1608','查看',NULL,'sys:user:page,sys:user:info','2',NULL,6,NULL,0,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(47,'0bbf3e9e51f511eca3306106f8cc1608','0bb9b97e51f511eca3306106f8cc1608','新增',NULL,'sys:user:add','2',NULL,6,NULL,0,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(48,'0bc18ab451f511eca3306106f8cc1608','0bb9b97e51f511eca3306106f8cc1608','修改',NULL,'sys:user:update','2',NULL,6,NULL,0,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(49,'0bc60ce251f511eca3306106f8cc1608','0bb9b97e51f511eca3306106f8cc1608','删除',NULL,'sys:user:delete','2',NULL,6,NULL,0,NULL,NULL,'2021-11-30 23:49:04','2021-11-30 23:49:04','0'),(50,'4a6ddb331d464050ac87f8c13f57facc','93f101be493d11ecb7c2543344556776','表单生成','dev/form','aaaaa','1','excel',0,NULL,0,'admin','admin','2021-12-01 07:53:01','2021-12-01 07:53:01','0'),(51,'a2fd81e4527c11ecb2ccd76ea2e07fd8','93f101be493d11ecb7c254334455677','系统日志','sys/log',NULL,'1','documentation',10,NULL,0,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(52,'a329b368527c11ecb2ccd76ea2e07fd8','a2fd81e4527c11ecb2ccd76ea2e07fd8','查看',NULL,'sys:log:page,sys:log:info','2',NULL,6,NULL,0,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(53,'a32e88ca527c11ecb2ccd76ea2e07fd8','a2fd81e4527c11ecb2ccd76ea2e07fd8','新增',NULL,'sys:log:save','2',NULL,6,NULL,0,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(54,'a330ec3c527c11ecb2ccd76ea2e07fd8','a2fd81e4527c11ecb2ccd76ea2e07fd8','修改',NULL,'sys:log:update','2',NULL,6,NULL,0,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(55,'a3332a60527c11ecb2ccd76ea2e07fd8','a2fd81e4527c11ecb2ccd76ea2e07fd8','删除',NULL,'sys:log:delete','2',NULL,6,NULL,0,NULL,NULL,'2021-12-01 15:59:40','2021-12-01 15:59:40','0'),(56,'8331d5f0900444109edc6cd83e54032e','93f101be493d11ecb7c2543344556776','代码生成','dev/code','','1','skill',0,NULL,0,'admin','admin','2021-12-09 06:21:28','2021-12-09 06:21:28','0'),(57,'89dd6034790111ec9f0679def01aad17','1f03c833858643cc8bccfc97ead0d4b3','流水线','application/pipeline',NULL,'1','component',6,NULL,1,NULL,NULL,'2022-01-19 16:26:45','2022-01-19 16:26:45','0'),(58,'89e61116790111ec9f0679def01aad17','89dd6034790111ec9f0679def01aad17','查看',NULL,'application:applicationpipeline:page,application:applicationpipeline:info','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:26:45','2022-01-19 16:26:45','0'),(59,'89eca53a790111ec9f0679def01aad17','89dd6034790111ec9f0679def01aad17','新增',NULL,'application:applicationpipeline:add','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:26:45','2022-01-19 16:26:45','0'),(60,'89f10ae4790111ec9f0679def01aad17','89dd6034790111ec9f0679def01aad17','修改',NULL,'application:applicationpipeline:update','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:26:45','2022-01-19 16:26:45','0'),(61,'89f4eff6790111ec9f0679def01aad17','89dd6034790111ec9f0679def01aad17','删除',NULL,'application:applicationpipeline:delete','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:26:45','2022-01-19 16:26:45','0'),(62,'972bff98790111ec9f0679def01aad17','1f03c833858643cc8bccfc97ead0d4b3','应用用户','application/applicationofuser',NULL,'1','component',6,NULL,1,NULL,NULL,'2022-01-19 16:27:07','2022-01-19 16:27:07','0'),(63,'972f478e790111ec9f0679def01aad17','972bff98790111ec9f0679def01aad17','查看',NULL,'application:applicationofuser:page,application:applicationofuser:info','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:08','2022-01-19 16:27:08','0'),(64,'97332390790111ec9f0679def01aad17','972bff98790111ec9f0679def01aad17','新增',NULL,'application:applicationofuser:add','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:08','2022-01-19 16:27:08','0'),(65,'9737e678790111ec9f0679def01aad17','972bff98790111ec9f0679def01aad17','修改',NULL,'application:applicationofuser:update','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:08','2022-01-19 16:27:08','0'),(66,'973cb9fa790111ec9f0679def01aad17','972bff98790111ec9f0679def01aad17','删除',NULL,'application:applicationofuser:delete','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:08','2022-01-19 16:27:08','0'),(67,'a0e7d110790111ec9f0679def01aad17','1f03c833858643cc8bccfc97ead0d4b3','应用管理','application/app',NULL,'1','component',1,NULL,0,NULL,NULL,'2022-01-19 16:27:24','2022-01-19 16:27:24','0'),(68,'a0ec3764790111ec9f0679def01aad17','a0e7d110790111ec9f0679def01aad17','查看',NULL,'application:applicationinfo:page,application:applicationinfo:info','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:24','2022-01-19 16:27:24','0'),(69,'a0f02914790111ec9f0679def01aad17','a0e7d110790111ec9f0679def01aad17','新增',NULL,'application:applicationinfo:add','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:24','2022-01-19 16:27:24','0'),(70,'a0f452aa790111ec9f0679def01aad17','a0e7d110790111ec9f0679def01aad17','修改',NULL,'application:applicationinfo:update','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:24','2022-01-19 16:27:24','0'),(71,'a0f87e98790111ec9f0679def01aad17','a0e7d110790111ec9f0679def01aad17','删除',NULL,'application:applicationinfo:delete','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:24','2022-01-19 16:27:24','0'),(72,'a87ccf3e790111ec9f0679def01aad17','1f03c833858643cc8bccfc97ead0d4b3','应用配置','application/applicationconfig',NULL,'1','component',6,NULL,1,NULL,NULL,'2022-01-19 16:27:37','2022-01-19 16:27:37','0'),(73,'a8810ac2790111ec9f0679def01aad17','a87ccf3e790111ec9f0679def01aad17','查看',NULL,'application:applicationconfig:page,application:applicationconfig:info','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:37','2022-01-19 16:27:37','0'),(74,'a8847c84790111ec9f0679def01aad17','a87ccf3e790111ec9f0679def01aad17','新增',NULL,'application:applicationconfig:add','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:37','2022-01-19 16:27:37','0'),(75,'a8883e8c790111ec9f0679def01aad17','a87ccf3e790111ec9f0679def01aad17','修改',NULL,'application:applicationconfig:update','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:37','2022-01-19 16:27:37','0'),(76,'a88d2212790111ec9f0679def01aad17','a87ccf3e790111ec9f0679def01aad17','删除',NULL,'application:applicationconfig:delete','2',NULL,6,NULL,0,NULL,NULL,'2022-01-19 16:27:37','2022-01-19 16:27:37','0'),(77,'1f03c833858643cc8bccfc97ead0d4b3','0','持续集成','','','0','dashboard',1,NULL,0,'admin','admin','2022-02-06 07:57:33','2022-02-06 07:57:33','0'),(78,'4d1b9a3cf79c4637ba9b74a812393544','0','基建管理','','','0','table',3,NULL,0,'admin','admin','2022-02-07 02:59:04','2022-02-07 02:59:04','0'),(79,'0e78eeeb6043422b82a472fe5c008291','0','集群管理','','','0','guide',4,NULL,0,'admin','admin','2022-02-07 03:00:08','2022-02-07 03:00:08','0'),(80,'434074f1392c4abe9143bb7d1bba7c2e','4d1b9a3cf79c4637ba9b74a812393544','zookeeper','base/zk','','1','password',0,NULL,0,'admin','admin','2022-02-07 03:01:39','2022-02-07 03:01:39','0'),(81,'efdbdd6b7a1e4b74be715257290ac79a','0e78eeeb6043422b82a472fe5c008291','kubernetes','cluster/kubernetes','','1','example',0,NULL,0,'admin','admin','2022-02-07 03:04:14','2022-02-07 03:04:14','0');
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
) ENGINE=InnoDB AUTO_INCREMENT=416 DEFAULT CHARSET=utf8mb4 COMMENT='角色关联菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_of_menu`
--

LOCK TABLES `sys_role_of_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_of_menu` DISABLE KEYS */;
INSERT INTO `sys_role_of_menu` VALUES (85,'edc36fbe44b346309633f6a63540eeaa','0caadea8ae91427eb3d869df43d0fe41','93f101be493d11ecb7c254334455677','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(86,'341f066eb9b74c3ca91525eb51ff3349','0caadea8ae91427eb3d869df43d0fe41','8fc52771493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(87,'8382665c44704e2ba00e5716e530350f','0caadea8ae91427eb3d869df43d0fe41','8fc6eb1a493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(88,'7582c9a49261487891bb3d357c20369a','0caadea8ae91427eb3d869df43d0fe41','8fc89835493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(89,'6e50c43e8a934bd78116d7dafe763fd3','0caadea8ae91427eb3d869df43d0fe41','8fc99a2d493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(90,'353de15fd3b942238e24b067c43a64e5','0caadea8ae91427eb3d869df43d0fe41','8fcaa825493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(91,'32be576f04d54f9a8d0723b66991d259','0caadea8ae91427eb3d869df43d0fe41','93ebbcb2493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(92,'00935ece12a248dda6f4c45cb8d78548','0caadea8ae91427eb3d869df43d0fe41','93ed48f8493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(93,'ab645494c8334636bcc31d4011ab5d27','0caadea8ae91427eb3d869df43d0fe41','93ee29b6493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(94,'31d1250e836449f484826b8fee0b91db','0caadea8ae91427eb3d869df43d0fe41','93f00a10493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(95,'1de65d702d734f9fb53591f1f71e5c35','0caadea8ae91427eb3d869df43d0fe41','93f101be493d11ecb7c254e1ad134d77','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(96,'72af74fd48f84871a3daab03a187eda0','0caadea8ae91427eb3d869df43d0fe41','0bb9b97e51f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(97,'b69b420baf4a4ecfb022dd6182337058','0caadea8ae91427eb3d869df43d0fe41','0bbe311651f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(98,'f50c633ba6034537895f074d9e53ef1f','0caadea8ae91427eb3d869df43d0fe41','0bbf3e9e51f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(99,'747994f0108c48e2bb700f755a3ec9dd','0caadea8ae91427eb3d869df43d0fe41','0bc18ab451f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(100,'fab2d2a63f8f43ff873fd6c6c75c932d','0caadea8ae91427eb3d869df43d0fe41','0bc60ce251f511eca3306106f8cc1608','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(101,'850daeaac10b4cca89efe67673d61580','0caadea8ae91427eb3d869df43d0fe41','a2fd81e4527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(102,'ffe45ebe32ed4cb48a0e0108cc5d713b','0caadea8ae91427eb3d869df43d0fe41','a329b368527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(103,'3207f4b395d6439eba466e4ded9b01ed','0caadea8ae91427eb3d869df43d0fe41','a32e88ca527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(104,'33d27be81f68459e86adc90f9f493c3e','0caadea8ae91427eb3d869df43d0fe41','a330ec3c527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(105,'eb951d36ebab4cfab0b7c446856900c8','0caadea8ae91427eb3d869df43d0fe41','a3332a60527c11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(107,'cdd7be6839c141118c7374d237e08445','0caadea8ae91427eb3d869df43d0fe41','638a1e2c527d11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(108,'a5e2ea99983d4f109e825ca88c4c58f0','0caadea8ae91427eb3d869df43d0fe41','638b5742527d11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(109,'a6e057847282429f82478c989b9138e4','0caadea8ae91427eb3d869df43d0fe41','638c3d9c527d11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(110,'5cdf81bcf80041f4a735aefb55b45d66','0caadea8ae91427eb3d869df43d0fe41','638d62b2527d11ecb2ccd76ea2e07fd8','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(111,'81ac9f6f388741a398d70f7f0ac48b85','0caadea8ae91427eb3d869df43d0fe41','93f101be493d11ecb7c2543344556776','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(112,'22d909855d374be69c473da2bf71eb38','0caadea8ae91427eb3d869df43d0fe41','4a6ddb331d464050ac87f8c13f57facc','admin','admin','2021-12-01 08:57:50','2021-12-01 08:57:50','0'),(367,'5da9d36ff381490b9d6d7404ea476638','93f101be493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254334455677','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(368,'430cef03ccac46b7a6789bd0998e2dc1','93f101be493d11ecb7c254e1ad134d77','8fc52771493d11ecb7c254e1ad134d77','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(369,'d3b38231c5f740f0b297ef1846c9da06','93f101be493d11ecb7c254e1ad134d77','8fc6eb1a493d11ecb7c254e1ad134d77','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(370,'1dbfca0adbbe4525918266981a714fe3','93f101be493d11ecb7c254e1ad134d77','8fc89835493d11ecb7c254e1ad134d77','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(371,'faed344cbb134107ab7277dde6c9ed6d','93f101be493d11ecb7c254e1ad134d77','8fc99a2d493d11ecb7c254e1ad134d77','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(372,'aaf109fb83dc4f19ae75a845bced48d6','93f101be493d11ecb7c254e1ad134d77','8fcaa825493d11ecb7c254e1ad134d77','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(373,'84e526e6655f4f0ab1e09da8749f936a','93f101be493d11ecb7c254e1ad134d77','93ebbcb2493d11ecb7c254e1ad134d77','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(374,'e335ce9eca094157823829ffb7eb38d5','93f101be493d11ecb7c254e1ad134d77','93ed48f8493d11ecb7c254e1ad134d77','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(375,'6cda6a8399bc41928674c5a7987ae071','93f101be493d11ecb7c254e1ad134d77','93ee29b6493d11ecb7c254e1ad134d77','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(376,'369e6fda496d4fdfb881c5455a21509d','93f101be493d11ecb7c254e1ad134d77','93f00a10493d11ecb7c254e1ad134d77','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(377,'655c29e6ef144a69a2e7caa02fddc80b','93f101be493d11ecb7c254e1ad134d77','93f101be493d11ecb7c254e1ad134d77','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(378,'4e52ee4373054d27b1cef58ef827753c','93f101be493d11ecb7c254e1ad134d77','0bb9b97e51f511eca3306106f8cc1608','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(379,'893ecdf80c27490eb6254fa21f835a8e','93f101be493d11ecb7c254e1ad134d77','0bbe311651f511eca3306106f8cc1608','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(380,'d7c6fbb19c96495fa29479a093d49638','93f101be493d11ecb7c254e1ad134d77','0bbf3e9e51f511eca3306106f8cc1608','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(381,'2ffa43c3407b447d85297a00398bf59d','93f101be493d11ecb7c254e1ad134d77','0bc18ab451f511eca3306106f8cc1608','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(382,'0e4ad0b7f0d947eb81fc25646c42c530','93f101be493d11ecb7c254e1ad134d77','0bc60ce251f511eca3306106f8cc1608','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(383,'faeefd565cd34521bfe2c2c30710702c','93f101be493d11ecb7c254e1ad134d77','a2fd81e4527c11ecb2ccd76ea2e07fd8','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(384,'30f5635f034c424aaa8edd46cc6869e5','93f101be493d11ecb7c254e1ad134d77','a329b368527c11ecb2ccd76ea2e07fd8','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(385,'372025129cc74c3bae450bd88f17b482','93f101be493d11ecb7c254e1ad134d77','a32e88ca527c11ecb2ccd76ea2e07fd8','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(386,'f51ae4ebef0042a486458760b3f30a8b','93f101be493d11ecb7c254e1ad134d77','a330ec3c527c11ecb2ccd76ea2e07fd8','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(387,'6156a130591445208ea3b1c1784e59f3','93f101be493d11ecb7c254e1ad134d77','a3332a60527c11ecb2ccd76ea2e07fd8','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(388,'82705e47084f4a6b96205a788bc4929d','93f101be493d11ecb7c254e1ad134d77','93f101be493d11ecb7c2543344556776','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(389,'fbbd7d7fbfc740318e8baea7e657cc2b','93f101be493d11ecb7c254e1ad134d77','4a6ddb331d464050ac87f8c13f57facc','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(390,'1049c49aa6904e2abee20dc0cbc131f6','93f101be493d11ecb7c254e1ad134d77','8331d5f0900444109edc6cd83e54032e','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(391,'adc65b037bb740979bcbd0a5e042493a','93f101be493d11ecb7c254e1ad134d77','1f03c833858643cc8bccfc97ead0d4b3','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(392,'accca025bf814fad9add5038c42335e5','93f101be493d11ecb7c254e1ad134d77','89dd6034790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(393,'cea644444e1743a7b8d47ab459964ea2','93f101be493d11ecb7c254e1ad134d77','89e61116790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(394,'366e6c754d57427fba5a340c1e9d5180','93f101be493d11ecb7c254e1ad134d77','89eca53a790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(395,'022270f032974ada9ba5e28c175d8bf4','93f101be493d11ecb7c254e1ad134d77','89f10ae4790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(396,'4254ffc8e9df427f823a3656eab2f270','93f101be493d11ecb7c254e1ad134d77','89f4eff6790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(397,'274f93c26dcd492db109395f27b856ad','93f101be493d11ecb7c254e1ad134d77','972bff98790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(398,'ae873d2e32374c97b977004169210fe2','93f101be493d11ecb7c254e1ad134d77','972f478e790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(399,'daa6211b218a4445894e142b4f312d86','93f101be493d11ecb7c254e1ad134d77','97332390790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(400,'40cd3d9f024e45ddbedbb48f3aa6e924','93f101be493d11ecb7c254e1ad134d77','9737e678790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(401,'ca9b5cd592684a0aa79a642d46b79c45','93f101be493d11ecb7c254e1ad134d77','973cb9fa790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(402,'494ba6ab5f76416884b3dd5825982599','93f101be493d11ecb7c254e1ad134d77','a0e7d110790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(403,'6c608629fd27454290a876deff3d3b00','93f101be493d11ecb7c254e1ad134d77','a0ec3764790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(404,'f9a0025ddea240a9b00e98e43efb9c69','93f101be493d11ecb7c254e1ad134d77','a0f02914790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(405,'3a8ebdd2929d4e4eaa7279f1d5abdd20','93f101be493d11ecb7c254e1ad134d77','a0f452aa790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(406,'449280706d14406797fd5dc1eea1b6db','93f101be493d11ecb7c254e1ad134d77','a0f87e98790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(407,'9b6f713aad7242328550125337a85d53','93f101be493d11ecb7c254e1ad134d77','a87ccf3e790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(408,'7ca32eddcafa4b4a80182ec3c3ede6e4','93f101be493d11ecb7c254e1ad134d77','a8810ac2790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(409,'d05f4df4b7364da29ada0e14d46b9d07','93f101be493d11ecb7c254e1ad134d77','a8847c84790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(410,'a959508b2e244e10a3ba1d06999226c0','93f101be493d11ecb7c254e1ad134d77','a8883e8c790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(411,'c308fbd70722445db593f64aaab9bf8b','93f101be493d11ecb7c254e1ad134d77','a88d2212790111ec9f0679def01aad17','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(412,'f052ec151a694607908f01b16a9937fe','93f101be493d11ecb7c254e1ad134d77','4d1b9a3cf79c4637ba9b74a812393544','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(413,'90345db514b3400b8226104086216270','93f101be493d11ecb7c254e1ad134d77','434074f1392c4abe9143bb7d1bba7c2e','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(414,'4ee93c1ffb434cdd931524b2035a0828','93f101be493d11ecb7c254e1ad134d77','0e78eeeb6043422b82a472fe5c008291','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0'),(415,'15eeb2d63ec3444ba0b6dbc59881878b','93f101be493d11ecb7c254e1ad134d77','efdbdd6b7a1e4b74be715257290ac79a','admin','admin','2022-02-07 03:06:33','2022-02-07 03:06:33','0');
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

-- Dump completed on 2022-02-07 11:08:39
